package com.kupoapo.creativetools;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.image.BufferedImage;
import java.io.File;

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
                                assert client != null;
                                ImageToBlocks imageToBlocks = new ImageToBlocks(file, client);
                                Thread placeThread = new Thread(imageToBlocks::buildPortrait);
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
