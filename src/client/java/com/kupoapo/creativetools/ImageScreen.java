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
    protected void addSettingButton(Settings setting, int x, int y, String tooltip, String enabledLabel, String disabledLabel) {
        var settingText = getSetting(setting) ? enabledLabel : disabledLabel;
        addButton(settingText, x, y, 80, 20, tooltip, b -> {
            setSetting(setting, !getSetting(setting));
            b.setMessage(Text.literal(getSetting(setting) ? enabledLabel : disabledLabel));
        });
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
        addSettingButton(Settings.Map, 0, 0, "Sets building type", "Map", "Portrait");
        addSettingButton(Settings.Staircase, 0, 20, "Sets map to staircase", "Staircase", "Flat");
        addButton(getSleepTimeLabel(), 0, 40, 80, 20, "Time per block placement", b -> {
            sleepIndex = (sleepIndex + 1) % SLEEP_TIMES.length;
            b.setMessage(Text.literal(getSleepTimeLabel()));
        });
//        var nameInput = new TextFieldWidget(this.textRenderer, width - 100, 0, 100, 20, Text.literal(""));
//        var colorInput = new TextFieldWidget(this.textRenderer, width - 100, 20, 100, 20, Text.literal(""));
//        var styleInput = new TextFieldWidget(this.textRenderer, width - 100, 40, 100, 20, Text.literal(""));
//        nameInput.setTooltip(Tooltip.of(Text.literal("Name")));
//        colorInput.setTooltip(Tooltip.of(Text.literal("Color")));
//        styleInput.setTooltip(Tooltip.of(Text.literal("Style")));
//        addDrawableChild(nameInput);
//        addDrawableChild(colorInput);
//        addDrawableChild(styleInput);
//        addButton("Summon", width - 100, 60, 100, 20, "Click to summon armor stand", b -> {
//            assert client != null;
//            assert client.player != null;
//            var styleType = !Objects.equals(styleInput.getText(), "") ? "\"" + styleInput.getText() + "\":true," : "";
//            client.player.networkHandler.sendChatCommand("summon armor_stand ~ ~ ~ {ShowArms:1b,Invisible:1b,NoBasePlate:1b,CustomName:'[{\"text\":\"" + nameInput.getText() + "\"," + styleType + "\"color\":\"" + colorInput.getText() + "\"}]',CustomNameVisible:1b,Invulnerable:1b,NoAI:1b,NoGravity:1b,PersistenceRequired:1b,Silent:1b}");
//        });
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
