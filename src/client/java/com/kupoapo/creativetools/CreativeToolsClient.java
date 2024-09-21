package com.kupoapo.creativetools;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.*;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.util.EnumMap;

@Environment(EnvType.CLIENT)
public class CreativeToolsClient implements ClientModInitializer {
	public enum Settings {
		Map,
		Highlight,
		Staircase,
	}
	private static KeyBinding imageKeyBinding;
	public static EnumMap<Settings, Boolean> imageSettings = new EnumMap<>(Settings.class);
	public static boolean isRunning = false;
	public static int sleepIndex = 2;
	public static final int[] SLEEP_TIMES = { 1, 5, 10, 15, 20, 30 };

	@Override
	public void onInitializeClient() {
		setSetting(Settings.Map, false);
		setSetting(Settings.Highlight, false);
		setSetting(Settings.Staircase, false);

		(new File("images")).mkdir();

		imageKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.creativetools",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"category.creativetools"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(imageKeyBinding.wasPressed()) {
				client.setScreen(new ImageScreen());
			}
		});
	}

	static public void setSetting(Settings setting, Boolean value) {
		imageSettings.put(setting, value);
	}

	static public Boolean getSetting(Settings setting) {
		return imageSettings.get(setting);
	}
}