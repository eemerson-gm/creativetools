package com.kupoapo.creativetools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;

public class CreativeToolsClient implements ClientModInitializer {

	private static KeyBinding imageKeyBinding;
	public static boolean isRunning = false;
	public static boolean isMap = false;

	@Override
	public void onInitializeClient() {

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
}