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

import static com.kupoapo.creativetools.CreativeToolsClient.*;

@Environment(EnvType.CLIENT)
public class ImageToBlocks {

    MinecraftClient client;
    int[] startPos;
    String[][] imageBlocks;
    int offset = 0;
    final int worldTop = 319;
    final int mapSize = 128;

    public ImageToBlocks(File file, MinecraftClient client) {
        this.client = client;
        this.startPos = new int[]{(int) client.player.getX(), (int) client.player.getY(), (int) client.player.getZ()};
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
    private int roundToMapChunk(int value) {
        var mapRadius = mapSize / 2;
        return ((int) Math.floor((double) ((value + mapRadius) / mapSize) * mapSize)) + mapRadius;
    }
    private void loopBlocks (Function3<Integer, Integer, String, Void> callback) {
        isRunning = true;
        int width = this.imageBlocks[0].length;
        int height = this.imageBlocks.length;
        for(int w = 0; w < width; w++){
            offset = 0;
            for(int h = 0; h < height; h++) {
                if(!isRunning) break;
                String blockID = this.imageBlocks[h][w];
                if(blockID != null) {
                    callback.apply(w, h, blockID);
                    try {
                        Thread.sleep(SLEEP_TIMES[sleepIndex]);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        isRunning = false;
    }
    public void buildPortrait() {
        loopBlocks((x, y, blockID) -> {
            assert client.player != null;
            var portraitX = (this.startPos[0] + x + 1);
            var portraitY = (this.startPos[1] + y);
            var portraitZ = this.startPos[2];
            client.player.networkHandler.sendChatCommand("setblock " + portraitX + " " + portraitY + " " + portraitZ + " " + blockID);
            return null;
        });
    }
    public void buildMap() {
        var mapStartX = roundToMapChunk(this.startPos[0]);
        var mapStartY = (int)(Math.floor((double) worldTop / 2));
        var mapStartZ = roundToMapChunk(this.startPos[2]);
        loopBlocks((x, y, blockID) -> {
            assert client.player != null;
            var mapX = mapStartX - x - 1;
            var mapY = mapStartY + offset;
            var mapZ = mapStartZ - y - 1;
            String[] blockNameAndPos =  { blockID, "0" };
            if(isStaircase) {
                blockNameAndPos = blockID.split("~");
            }
            client.player.networkHandler.sendChatCommand("setblock " + mapX + " " + mapY + " " + mapZ + " " + blockNameAndPos[0]);
            offset += Integer.parseInt(blockNameAndPos[1]);
            var currentY = mapStartY + offset;
            if(currentY < this.startPos[1]) offset = 0;
            if(currentY > worldTop) offset = 0;
            return null;
        });
    }
}
