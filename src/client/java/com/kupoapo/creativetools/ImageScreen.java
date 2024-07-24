package com.kupoapo.creativetools;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.io.File;

import static com.kupoapo.creativetools.CreativeToolsClient.*;

@Environment(EnvType.CLIENT)
public class ImageScreen extends Screen {
    protected ImageScreen() {
        super(Text.literal("Image Menu"));
    }

    protected void addButton(String text, int x, int y, int width, int height, String tooltip, ButtonWidget.PressAction onPress) {
        var button = ButtonWidget.builder(Text.literal(text), onPress)
                .dimensions(x, y, width, height)
                .tooltip(Tooltip.of(Text.literal(tooltip)))
                .build();
        addDrawableChild(button);
    }

    @Override
    protected void init() {
        Screen thisScreen = this;
        File folder = new File(System.getProperty("user.dir") + "/images/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (var f = 0; f < listOfFiles.length; f++) {
                var file = listOfFiles[f];
                if (file.isFile()) {
                    addButton(file.getName(), (width / 2) - 100, f * 20, 200, 20, "Click to build image", b -> {
                        assert client != null;
                        ImageToBlocks imageToBlocks = new ImageToBlocks(file, client);
                        imageToBlocks.start();
                        thisScreen.close();
                    });
                }
            }
        }
        if (isRunning) {
            addButton("Stop", width - 80, 0, 80, 20, "Stops all running builds", b -> {
                isRunning = false;
            });
        }
        addButton(getImageTypeLabel(), 0, 0, 80, 20, "Image type being generated", b -> {
            isMap = !isMap;
            b.setMessage(Text.literal(getImageTypeLabel()));
        });
        addButton(getHighlightLabel(), 0, 20, 80, 20, "Highlight the starting block", b -> {
            isHighlight = !isHighlight;
            b.setMessage(Text.literal(getHighlightLabel()));
        });
        addButton(getSleepTimeLabel(), 0, 40, 80, 20, "Time per block placement", b -> {
            sleepIndex = (sleepIndex + 1) % SLEEP_TIMES.length;
            b.setMessage(Text.literal(getSleepTimeLabel()));
        });
    }

    private String getImageTypeLabel() {
        return isMap ? "Map" : "Portrait";
    }

    private String getHighlightLabel() {
        return isHighlight ? "Enabled" : "Disabled";
    }

    private String getSleepTimeLabel() {
        return Integer.toString(SLEEP_TIMES[sleepIndex]);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}
