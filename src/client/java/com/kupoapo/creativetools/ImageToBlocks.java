package com.kupoapo.creativetools;

import com.mojang.datafixers.util.Function3;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.kupoapo.creativetools.CreativeToolsClient.isRunning;
import static com.kupoapo.creativetools.CreativeToolsClient.isMap;

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
    public void start() {
        Thread placeThread = getBuildThread();
        placeThread.start();
    }
    private Thread getBuildThread() {
        if(isMap){
            return new Thread(this::buildMap);
        }
        return new Thread(this::buildPortrait);
    }
    private BufferedImage readFileImage(File file) {
        BufferedImage bufferedImage;
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
                int inverseY = bufferedImage.getHeight() - h - 1;
                Color color = new Color(bufferedImage.getRGB(w, inverseY), true);
                if(color.getAlpha() != 0) {
                    String blockID = colorUtils.getColorNameFromColor(color);
                    imageBlocks[h][w] = blockID;
                }
            }
        }
        return imageBlocks;
    }
    private void loopBlocks (Function3<Integer, Integer, String, Void> callback) {
        isRunning = true;
        for(int h = 0; h < this.imageBlocks.length; h++){
            for(int w = 0; w < this.imageBlocks[h].length; w++) {
                if(!isRunning) break;
                String blockID = this.imageBlocks[h][w];
                if(blockID != null) {
                    callback.apply(w, h, blockID);
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
    public void buildPortrait() {
        loopBlocks((x, y, blockID) -> {
            assert client.player != null;
            client.player.networkHandler.sendChatCommand("setblock " + (this.clientPosition[0] + x + 2) + " " + (this.clientPosition[1] + y) + " " + this.clientPosition[2] + " " + blockID);
            return null;
        });
    }
    public void buildMap() {
        loopBlocks((x, y, blockID) -> {
            assert client.player != null;
            client.player.networkHandler.sendChatCommand("setblock " + (this.clientPosition[0] - x - 1) + " " + this.clientPosition[1] + " " + (this.clientPosition[2] - y) + " " + blockID);
            return null;
        });
    }
}
