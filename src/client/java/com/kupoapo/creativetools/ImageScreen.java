package com.kupoapo.creativetools;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.kupoapo.creativetools.CreativeToolsClient.delay;
import static com.kupoapo.creativetools.CreativeToolsClient.isRunning;

@Environment(EnvType.CLIENT)
public class ImageScreen extends Screen {
    protected ImageScreen() {
        super(Text.literal("Image Menu"));
    }

    @Override
    protected void init() {
        Screen thisScreen = this;
        File folder = new File(System.getProperty("user.dir") + "/images/");
        File[] listOfFiles = folder.listFiles();
        System.out.println(folder.getPath());

        if(listOfFiles != null) {
            for (var f = 0; f < listOfFiles.length; f++) {
                var file = listOfFiles[f];
                if (file.isFile()) {
                    var button = ButtonWidget.builder(Text.literal(file.getName()), b -> {
                                BufferedImage bufferedImage = null;
                                try {
                                    bufferedImage = ImageIO.read(file);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                BufferedImage finalBufferedImage = bufferedImage;
                                ColorUtils colorUtils = new ColorUtils();
                                int[] position = {(int)client.player.getX(), (int)client.player.getY(), (int)client.player.getZ()};
                                isRunning = true;
                                Thread placeThread = new Thread(){
                                    public void run(){
                                        runner:
                                        for(int h = 0; h < finalBufferedImage.getHeight(); h++){
                                            for(int w = 0; w < finalBufferedImage.getWidth(); w++){
                                                if(!isRunning) break runner;
                                                Color color = new Color(finalBufferedImage.getRGB(w, (finalBufferedImage.getHeight() - 1) - h), true);
                                                if(color.getAlpha() != 0) {
                                                    try {
                                                        Thread.sleep(10);
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                    String colorName = colorUtils.getColorNameFromColor(color);
                                                    client.player.networkHandler.sendChatCommand("setblock " + (position[0] + w + 2) + " " + (position[1] + h) + " " + position[2] + " " + colorName);
                                                }
                                            }
                                        }
                                        isRunning = false;
                                    }
                                };
                                placeThread.start();
                                thisScreen.close();
                            })
                            .dimensions((width / 2) - 100, f * 20, 200, 20)
                            .tooltip(Tooltip.of(Text.literal("Click to generate the blocks")))
                            .build();
                    addDrawableChild(button);
                }
            }
        }
        if(isRunning){
            var button = ButtonWidget.builder(Text.literal("Stop"), b -> {
                        isRunning = false;
                        thisScreen.close();
                    })
                    .dimensions(width - 150, 0, 150, 20)
                    .tooltip(Tooltip.of(Text.literal("Stops all running builds")))
                    .build();
            addDrawableChild(button);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}
