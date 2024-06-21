package com.kupoapo.creativetools;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.kupoapo.creativetools.CreativeToolsClient.isRunning;

@Environment(EnvType.CLIENT)
public class ImageToBlocks {

    MinecraftClient client;
    int[] clientPosition;
    String[][] imageBlocks;

    public ImageToBlocks(File file, MinecraftClient client) {
        this.client = client;
        this.clientPosition = new int[]{(int) client.player.getX(), (int) client.player.getY(), (int) client.player.getZ()};
        this.imageBlocks = fileToBlocks(file);
    }
    private BufferedImage readFileImage(File file) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bufferedImage;
    }
    private String[][] fileToBlocks(File file) {
        ColorUtils colorUtils = new ColorUtils();
        BufferedImage bufferedImage = readFileImage(file);
        String[][] imageBlocks = new String[bufferedImage.getHeight()][bufferedImage.getWidth()];
        for(int h = 0; h < bufferedImage.getHeight(); h++){
            for(int w = 0; w < bufferedImage.getWidth(); w++){
                Color color = new Color(bufferedImage.getRGB(w, (bufferedImage.getHeight() - 1) - h), true);
                if(color.getAlpha() != 0) {
                    String blockID = colorUtils.getColorNameFromColor(color);
                    imageBlocks[h][w] = blockID;
                }
            }
        }
        return imageBlocks;
    }
    public void buildPortrait() {
        isRunning = true;
        for(int h = 0; h < this.imageBlocks.length; h++){
            for(int w = 0; w < this.imageBlocks[h].length; w++){
                if(!isRunning) break;
                String blockID = this.imageBlocks[w][h];
                if(blockID != null){
                    assert client.player != null;
                    client.player.networkHandler.sendChatCommand("setblock " + (this.clientPosition[0] + w + 2) + " " + (this.clientPosition[1] + h) + " " + this.clientPosition[2] + " " + blockID);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        isRunning = false;
    }
}
