package com.kupoapo.creativetools;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Java Code to get a color name from rgb/hex value/awt color
 *
 * The part of looking up a color name from the rgb values is edited from
 * https://gist.github.com/nightlark/6482130#file-gistfile1-java (that has some errors) by Ryan Mast (nightlark)
 *
 * @author Xiaoxiao Li
 *
 */
public class ColorUtils {

    /**
     * Initialize the color list that we have.
     */
    private ArrayList<ColorName> initColorList() {
        ArrayList<ColorName> colorList = new ArrayList<ColorName>();
        colorList.add(new ColorName("minecraft:acacia_log", 0x5C, 0x56, 0x4D));
        colorList.add(new ColorName("minecraft:acacia_planks", 0xA0, 0x56, 0x2F));
        colorList.add(new ColorName("minecraft:amethyst_block", 0x6B, 0x4D, 0xA5));
        colorList.add(new ColorName("minecraft:andesite", 0x9D, 0x9E, 0x9A));
        colorList.add(new ColorName("minecraft:bedrock", 0x3F, 0x3F, 0x3F));
        colorList.add(new ColorName("minecraft:beehive", 0xBC, 0x98, 0x60));
        colorList.add(new ColorName("minecraft:birch_planks", 0xD1, 0xC0, 0x83));
        colorList.add(new ColorName("minecraft:black_concrete", 0x08, 0x0A, 0x0F));
        colorList.add(new ColorName("minecraft:black_concrete_powder", 0x12, 0x14, 0x1A));
        colorList.add(new ColorName("minecraft:black_glazed_terracotta", 0x18, 0x18, 0x1B));
        colorList.add(new ColorName("minecraft:black_terracotta", 0x25, 0x16, 0x10));
        colorList.add(new ColorName("minecraft:black_wool", 0x1F, 0x1F, 0x23));
        colorList.add(new ColorName("minecraft:blackstone", 0x20, 0x18, 0x19));
        colorList.add(new ColorName("minecraft:blue_concrete", 0x2D, 0x2F, 0x8F));
        colorList.add(new ColorName("minecraft:blue_concrete_powder", 0x47, 0x4A, 0xA7));
        colorList.add(new ColorName("minecraft:blue_glazed_terracotta", 0x29, 0x2A, 0x7D));
        colorList.add(new ColorName("minecraft:blue_ice", 0x6B, 0x9F, 0xFB));
        colorList.add(new ColorName("minecraft:blue_terracotta", 0x4C, 0x3D, 0x5C));
        colorList.add(new ColorName("minecraft:blue_wool", 0x30, 0x32, 0x94));
        colorList.add(new ColorName("minecraft:bookshelf", 0xB3, 0x92, 0x5F));
        colorList.add(new ColorName("minecraft:bricks", 0xA6, 0x67, 0x57));
        colorList.add(new ColorName("minecraft:brown_concrete", 0x61, 0x3C, 0x20));
        colorList.add(new ColorName("minecraft:brown_concrete_powder", 0x76, 0x4F, 0x31));
        colorList.add(new ColorName("minecraft:brown_glazed_terracotta", 0x71, 0x48, 0x29));
        colorList.add(new ColorName("minecraft:brown_mushroom_block", 0x96, 0x72, 0x51));
        colorList.add(new ColorName("minecraft:brown_terracotta", 0x4E, 0x35, 0x24));
        colorList.add(new ColorName("minecraft:brown_wool", 0x6D, 0x43, 0x25));
        colorList.add(new ColorName("minecraft:budding_amethyst", 0x62, 0x44, 0x9B));
        colorList.add(new ColorName("minecraft:calcite", 0xE9, 0xE9, 0xE3));
        colorList.add(new ColorName("minecraft:chiseled_deepslate", 0x28, 0x28, 0x28));
        colorList.add(new ColorName("minecraft:chiseled_nether_bricks", 0x29, 0x15, 0x18));
        colorList.add(new ColorName("minecraft:chiseled_polished_blackstone", 0x4E, 0x4B, 0x54));
        colorList.add(new ColorName("minecraft:chiseled_quartz_block", 0xDC, 0xD8, 0xCA));
        colorList.add(new ColorName("minecraft:chiseled_red_sandstone", 0x9E, 0x4E, 0x0B));
        colorList.add(new ColorName("minecraft:chiseled_sandstone", 0xD3, 0xC2, 0x93));
        colorList.add(new ColorName("minecraft:chiseled_stone_bricks", 0x5A, 0x5A, 0x5A));
        colorList.add(new ColorName("minecraft:clay", 0x9C, 0xA2, 0xAC));
        colorList.add(new ColorName("minecraft:coal_block", 0x08, 0x08, 0x08));
        colorList.add(new ColorName("minecraft:coal_ore", 0x7F, 0x7F, 0x7F));
        colorList.add(new ColorName("minecraft:coarse_dirt", 0xA2, 0x76, 0x51));
        colorList.add(new ColorName("minecraft:cobbled_deepslate", 0x3C, 0x3C, 0x42));
        colorList.add(new ColorName("minecraft:cobblestone", 0xA8, 0xA8, 0xA8));
        colorList.add(new ColorName("minecraft:copper_block", 0xD2, 0x79, 0x5C));
        colorList.add(new ColorName("minecraft:copper_ore", 0x67, 0x74, 0x70));
        colorList.add(new ColorName("minecraft:cracked_deepslate_bricks", 0x47, 0x47, 0x49));
        colorList.add(new ColorName("minecraft:cracked_deepslate_tiles", 0x28, 0x28, 0x28));
        colorList.add(new ColorName("minecraft:cracked_nether_bricks", 0x3B, 0x1B, 0x21));
        colorList.add(new ColorName("minecraft:cracked_polished_blackstone_bricks", 0x1F, 0x16, 0x18));
        colorList.add(new ColorName("minecraft:cracked_stone_bricks", 0x8D, 0x8B, 0x8D));
        colorList.add(new ColorName("minecraft:crimson_planks", 0x7A, 0x39, 0x53));
        colorList.add(new ColorName("minecraft:crimson_stem", 0x4A, 0x1F, 0x27));
        colorList.add(new ColorName("minecraft:crying_obsidian", 0x06, 0x04, 0x0C));
        colorList.add(new ColorName("minecraft:cut_copper", 0xA6, 0x5A, 0x40));
        colorList.add(new ColorName("minecraft:cut_red_sandstone", 0xBA, 0x65, 0x21));
        colorList.add(new ColorName("minecraft:cut_sandstone", 0xE5, 0xE0, 0xB8));
        colorList.add(new ColorName("minecraft:cyan_concrete", 0x15, 0x76, 0x86));
        colorList.add(new ColorName("minecraft:cyan_concrete_powder", 0x23, 0x8A, 0x97));
        colorList.add(new ColorName("minecraft:cyan_glazed_terracotta", 0x15, 0x84, 0x8E));
        colorList.add(new ColorName("minecraft:cyan_terracotta", 0x55, 0x59, 0x59));
        colorList.add(new ColorName("minecraft:cyan_wool", 0x15, 0x8D, 0x92));
        colorList.add(new ColorName("minecraft:dark_oak_log", 0x33, 0x28, 0x15));
        colorList.add(new ColorName("minecraft:dark_oak_planks", 0x4E, 0x32, 0x18));
        colorList.add(new ColorName("minecraft:dark_prismarine", 0x39, 0x62, 0x52));
        colorList.add(new ColorName("minecraft:deepslate", 0x56, 0x56, 0x56));
        colorList.add(new ColorName("minecraft:deepslate_bricks", 0x61, 0x61, 0x61));
        colorList.add(new ColorName("minecraft:deepslate_coal_ore", 0x63, 0x63, 0x63));
        colorList.add(new ColorName("minecraft:deepslate_copper_ore", 0x49, 0x5E, 0x58));
        colorList.add(new ColorName("minecraft:deepslate_diamond_ore", 0x45, 0x46, 0x49));
        colorList.add(new ColorName("minecraft:deepslate_emerald_ore", 0x6B, 0x6D, 0x6C));
        colorList.add(new ColorName("minecraft:deepslate_gold_ore", 0x48, 0x48, 0x4B));
        colorList.add(new ColorName("minecraft:deepslate_iron_ore", 0x49, 0x49, 0x4C));
        colorList.add(new ColorName("minecraft:deepslate_lapis_ore", 0x4E, 0x4E, 0x50));
        colorList.add(new ColorName("minecraft:deepslate_redstone_ore", 0x44, 0x41, 0x44));
        colorList.add(new ColorName("minecraft:deepslate_tiles", 0x2A, 0x2A, 0x2A));
        colorList.add(new ColorName("minecraft:diamond_block", 0x3D, 0xDE, 0xDB));
        colorList.add(new ColorName("minecraft:diamond_ore", 0x94, 0xA1, 0xA2));
        colorList.add(new ColorName("minecraft:diorite", 0xAD, 0xAC, 0xAD));
        colorList.add(new ColorName("minecraft:dirt", 0x8D, 0x65, 0x45));
        colorList.add(new ColorName("minecraft:dripstone_block", 0x80, 0x61, 0x55));
        colorList.add(new ColorName("minecraft:emerald_block", 0x19, 0xB1, 0x38));
        colorList.add(new ColorName("minecraft:emerald_ore", 0x93, 0x98, 0x95));
        colorList.add(new ColorName("minecraft:end_stone", 0xCE, 0xCE, 0x8E));
        colorList.add(new ColorName("minecraft:end_stone_bricks", 0xE7, 0xF2, 0xB1));
        colorList.add(new ColorName("minecraft:exposed_copper", 0xB4, 0x7F, 0x6F));
        colorList.add(new ColorName("minecraft:exposed_cut_copper", 0xA2, 0x7D, 0x65));
        colorList.add(new ColorName("minecraft:gilded_blackstone", 0x22, 0x1A, 0x1B));
        colorList.add(new ColorName("minecraft:glowstone", 0x78, 0x4E, 0x27));
        colorList.add(new ColorName("minecraft:gold_block", 0xED, 0xBB, 0x2A));
        colorList.add(new ColorName("minecraft:gold_ore", 0x6A, 0x6A, 0x6A));
        colorList.add(new ColorName("minecraft:granite", 0x83, 0x59, 0x49));
        colorList.add(new ColorName("minecraft:gravel", 0x78, 0x73, 0x72));
        colorList.add(new ColorName("minecraft:gray_concrete", 0x36, 0x39, 0x3D));
        colorList.add(new ColorName("minecraft:gray_concrete_powder", 0x49, 0x4C, 0x50));
        colorList.add(new ColorName("minecraft:gray_glazed_terracotta", 0x3B, 0x3F, 0x43));
        colorList.add(new ColorName("minecraft:gray_terracotta", 0x39, 0x29, 0x23));
        colorList.add(new ColorName("minecraft:gray_wool", 0x45, 0x4C, 0x4F));
        colorList.add(new ColorName("minecraft:green_concrete", 0x49, 0x5A, 0x24));
        colorList.add(new ColorName("minecraft:green_concrete_powder", 0x5C, 0x70, 0x2E));
        colorList.add(new ColorName("minecraft:green_glazed_terracotta", 0x5D, 0x7B, 0x1E));
        colorList.add(new ColorName("minecraft:green_terracotta", 0x4C, 0x53, 0x2A));
        colorList.add(new ColorName("minecraft:green_wool", 0x57, 0x71, 0x18));
        colorList.add(new ColorName("minecraft:honeycomb_block", 0xD6, 0x7A, 0x06));
        colorList.add(new ColorName("minecraft:ice", 0x89, 0xB1, 0xFC));
        colorList.add(new ColorName("minecraft:iron_block", 0xE8, 0xE8, 0xE8));
        colorList.add(new ColorName("minecraft:iron_ore", 0x76, 0x75, 0x74));
        colorList.add(new ColorName("minecraft:jungle_log", 0x6C, 0x54, 0x21));
        colorList.add(new ColorName("minecraft:jungle_planks", 0xB4, 0x83, 0x61));
        colorList.add(new ColorName("minecraft:lapis_block", 0x1C, 0x3F, 0x83));
        colorList.add(new ColorName("minecraft:lapis_ore", 0x73, 0x73, 0x73));
        colorList.add(new ColorName("minecraft:light_blue_concrete", 0x23, 0x87, 0xC5));
        colorList.add(new ColorName("minecraft:light_blue_concrete_powder", 0x4C, 0xB8, 0xD6));
        colorList.add(new ColorName("minecraft:light_blue_glazed_terracotta", 0x2A, 0x72, 0xB5));
        colorList.add(new ColorName("minecraft:light_blue_terracotta", 0x72, 0x6C, 0x89));
        colorList.add(new ColorName("minecraft:light_blue_wool", 0x45, 0xBD, 0xE0));
        colorList.add(new ColorName("minecraft:light_gray_concrete", 0x7C, 0x7C, 0x72));
        colorList.add(new ColorName("minecraft:light_gray_concrete_powder", 0x92, 0x92, 0x8A));
        colorList.add(new ColorName("minecraft:light_gray_glazed_terracotta", 0xC2, 0xC5, 0xC7));
        colorList.add(new ColorName("minecraft:light_gray_terracotta", 0x87, 0x6B, 0x62));
        colorList.add(new ColorName("minecraft:light_gray_wool", 0x99, 0x99, 0x93));
        colorList.add(new ColorName("minecraft:lime_concrete", 0x5D, 0xA6, 0x18));
        colorList.add(new ColorName("minecraft:lime_concrete_powder", 0x78, 0xB6, 0x27));
        colorList.add(new ColorName("minecraft:lime_glazed_terracotta", 0x5D, 0xA5, 0x17));
        colorList.add(new ColorName("minecraft:lime_terracotta", 0x65, 0x74, 0x33));
        colorList.add(new ColorName("minecraft:lime_wool", 0x63, 0xAC, 0x18));
        colorList.add(new ColorName("minecraft:magenta_concrete", 0xA6, 0x2F, 0x9C));
        colorList.add(new ColorName("minecraft:magenta_concrete_powder", 0xC2, 0x56, 0xBA));
        colorList.add(new ColorName("minecraft:magenta_glazed_terracotta", 0xBB, 0x43, 0xB2));
        colorList.add(new ColorName("minecraft:magenta_terracotta", 0x92, 0x56, 0x6B));
        colorList.add(new ColorName("minecraft:magenta_wool", 0xB2, 0x3A, 0xA8));
        colorList.add(new ColorName("minecraft:magma_block", 0x56, 0x1F, 0x1F));
        colorList.add(new ColorName("minecraft:moss_block", 0x4A, 0x60, 0x29));
        colorList.add(new ColorName("minecraft:mossy_cobblestone", 0x5C, 0x6C, 0x40));
        colorList.add(new ColorName("minecraft:mossy_stone_bricks", 0x82, 0x81, 0x82));
        colorList.add(new ColorName("minecraft:mushroom_stem", 0xCF, 0xC8, 0xC0));
        colorList.add(new ColorName("minecraft:nether_bricks", 0x27, 0x14, 0x17));
        colorList.add(new ColorName("minecraft:nether_gold_ore", 0x58, 0x1F, 0x1F));
        colorList.add(new ColorName("minecraft:nether_quartz_ore", 0x59, 0x20, 0x20));
        colorList.add(new ColorName("minecraft:nether_wart_block", 0x65, 0x01, 0x00));
        colorList.add(new ColorName("minecraft:netherite_block", 0x4D, 0x49, 0x4D));
        colorList.add(new ColorName("minecraft:netherrack", 0x76, 0x35, 0x35));
        colorList.add(new ColorName("minecraft:note_block", 0x2A, 0x28, 0x1F));
        colorList.add(new ColorName("minecraft:oak_log", 0x80, 0x64, 0x3B));
        colorList.add(new ColorName("minecraft:oak_planks", 0xB7, 0x94, 0x5C));
        colorList.add(new ColorName("minecraft:obsidian", 0x03, 0x01, 0x06));
        colorList.add(new ColorName("minecraft:orange_concrete", 0xE0, 0x61, 0x00));
        colorList.add(new ColorName("minecraft:orange_concrete_powder", 0xDD, 0x7B, 0x17));
        colorList.add(new ColorName("minecraft:orange_glazed_terracotta", 0xEE, 0x77, 0x13));
        colorList.add(new ColorName("minecraft:orange_terracotta", 0x9E, 0x52, 0x24));
        colorList.add(new ColorName("minecraft:orange_wool", 0xE9, 0x6A, 0x09));
        colorList.add(new ColorName("minecraft:oxidized_copper", 0x5A, 0xB2, 0x93));
        colorList.add(new ColorName("minecraft:oxidized_cut_copper", 0x5A, 0xAD, 0x8D));
        colorList.add(new ColorName("minecraft:packed_ice", 0x80, 0xA9, 0xF5));
        colorList.add(new ColorName("minecraft:pink_concrete", 0xD5, 0x65, 0x8E));
        colorList.add(new ColorName("minecraft:pink_concrete_powder", 0xDE, 0x8A, 0xAA));
        colorList.add(new ColorName("minecraft:pink_glazed_terracotta", 0xE9, 0xB4, 0xC5));
        colorList.add(new ColorName("minecraft:pink_terracotta", 0x9E, 0x4C, 0x4D));
        colorList.add(new ColorName("minecraft:pink_wool", 0xF3, 0x95, 0xB1));
        colorList.add(new ColorName("minecraft:polished_andesite", 0x9D, 0x9E, 0x9D));
        colorList.add(new ColorName("minecraft:polished_blackstone", 0x3F, 0x3C, 0x48));
        colorList.add(new ColorName("minecraft:polished_blackstone_bricks", 0x21, 0x1B, 0x18));
        colorList.add(new ColorName("minecraft:polished_deepslate", 0x2D, 0x2D, 0x2D));
        colorList.add(new ColorName("minecraft:polished_diorite", 0xD8, 0xD6, 0xD8));
        colorList.add(new ColorName("minecraft:polished_granite", 0xA7, 0x77, 0x65));
        colorList.add(new ColorName("minecraft:prismarine", 0x52, 0x95, 0x84));
        colorList.add(new ColorName("minecraft:prismarine_bricks", 0x5D, 0xA8, 0x9D));
        colorList.add(new ColorName("minecraft:purple_concrete", 0x65, 0x20, 0x9C));
        colorList.add(new ColorName("minecraft:purple_concrete_powder", 0x7F, 0x35, 0xAE));
        colorList.add(new ColorName("minecraft:purple_glazed_terracotta", 0x8F, 0x3B, 0xC3));
        colorList.add(new ColorName("minecraft:purple_terracotta", 0x74, 0x44, 0x54));
        colorList.add(new ColorName("minecraft:purple_wool", 0x6E, 0x23, 0xA2));
        colorList.add(new ColorName("minecraft:purpur_block", 0x9C, 0x6E, 0x9C));
        colorList.add(new ColorName("minecraft:purpur_pillar", 0xA6, 0x76, 0xA5));
        colorList.add(new ColorName("minecraft:quartz_bricks", 0xEA, 0xE2, 0xDA));
        colorList.add(new ColorName("minecraft:quartz_pillar", 0xEF, 0xEC, 0xE9));
        colorList.add(new ColorName("minecraft:raw_copper_block", 0xBE, 0x6E, 0x54));
        colorList.add(new ColorName("minecraft:raw_gold_block", 0xC3, 0x83, 0x1F));
        return colorList;
    }

    /**
     * Get the closest color name from our list
     *
     * @param r
     * @param g
     * @param b
     * @return
     */
    public String getColorNameFromRgb(int r, int g, int b) {
        ArrayList<ColorName> colorList = initColorList();
        ColorName closestMatch = null;
        int minMSE = Integer.MAX_VALUE;
        int mse;
        for (ColorName c : colorList) {
            mse = c.computeMSE(r, g, b);
            if (mse < minMSE) {
                minMSE = mse;
                closestMatch = c;
            }
        }

        if (closestMatch != null) {
            return closestMatch.getName();
        } else {
            return "No matched color name.";
        }
    }

    /**
     * Convert hexColor to rgb, then call getColorNameFromRgb(r, g, b)
     *
     * @param hexColor
     * @return
     */
    public String getColorNameFromHex(int hexColor) {
        int r = (hexColor & 0xFF0000) >> 16;
        int g = (hexColor & 0xFF00) >> 8;
        int b = (hexColor & 0xFF);
        return getColorNameFromRgb(r, g, b);
    }

    public int colorToHex(Color c) {
        return Integer.decode("0x"
                + Integer.toHexString(c.getRGB()).substring(2));
    }

    public String getColorNameFromColor(Color color) {
        return getColorNameFromRgb(color.getRed(), color.getGreen(),
                color.getBlue());
    }

    /**
     * SubClass of ColorUtils. In order to lookup color name
     *
     * @author Xiaoxiao Li
     *
     */
    public class ColorName {
        public int r, g, b;
        public String name;

        public ColorName(String name, int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.name = name;
        }

        public int computeMSE(int pixR, int pixG, int pixB) {
            return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
                    * (pixB - b)) / 3);
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        public String getName() {
            return name;
        }
    }
}