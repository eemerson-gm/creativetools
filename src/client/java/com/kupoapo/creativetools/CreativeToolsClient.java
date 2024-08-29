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

@Environment(EnvType.CLIENT)
public class CreativeToolsClient implements ClientModInitializer {

	private static KeyBinding imageKeyBinding;
	public static boolean isRunning = false;
	public static boolean isMap = false;
	public static boolean isHighlight = false;
	public static boolean isStaircase = false;
	public static int sleepIndex = 2;
	public static final int[] SLEEP_TIMES = { 1, 5, 10, 15, 20, 30 };

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

		WorldRenderEvents.END.register(context -> {
			if (!isHighlight) return;

			Vec3d cameraPos = context.camera().getPos();
			ClientPlayerEntity player = context.gameRenderer().getClient().player;

			Vector4f color = new Vector4f(1f,0f,0f, 1);

            assert player != null;
			float offset = isMap ? -2 : 2;
			Vector3d boxMin = new Vector3d(player.getX() + offset - 0.5, player.getY() + 0.45, player.getZ() - 0.5);
			Vector3d boxMax = new Vector3d((player.getX() + 1) + offset - 0.5, (player.getY() + 1) + 0.45, (player.getZ() + 1) - 0.5);

			MatrixStack matrixStack = context.matrixStack();
			matrixStack.push();
			matrixStack.translate(-cameraPos.getX(), -cameraPos.getY(), -cameraPos.getZ());

			Tessellator tessellator = RenderSystem.renderThreadTesselator();
			BufferBuilder bufferBuilder = tessellator.getBuffer();

			RenderSystem.setShader(GameRenderer::getPositionColorProgram);
			RenderSystem.enableDepthTest();

			bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
			boxMin.round();
			boxMax.round();

			WorldRenderer.drawBox(
					matrixStack,
					bufferBuilder,
					new Box(boxMin.x, boxMin.y, boxMin.z, boxMax.x, boxMax.y, boxMax.z), color.x, color.y, color.z, color.w
			);

			tessellator.draw();
			matrixStack.pop();
			RenderSystem.disableDepthTest();
		});
	}
}