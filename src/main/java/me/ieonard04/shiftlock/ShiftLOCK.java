package me.ieonard04.shiftlock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ShiftLOCK implements ClientModInitializer {

    public static KeyBinding SHIFT_LOCK;
    public final static String MOD_ID = "shift_lock";

    @Override
    public void onInitializeClient() {
        SHIFT_LOCK = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "existing_stacks.move",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_CAPS_LOCK, // Default keybinding is Caps Lock Key
                        "existing_stacks.category"
                )
        );
    }
}
