package me.ieonard04.shiftlock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import me.ieonard04.shiftlock.ShiftLOCKConfig;

public class ShiftLOCK implements ClientModInitializer {
    public static boolean IS_SHIFT_LOCKED;

    public static KeyBinding SHIFT_LOCK;
    public final static String MOD_ID = "shift_lock";

    @Override
    public void onInitializeClient() {
        IS_SHIFT_LOCKED = false;

        SHIFT_LOCK = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "shift_lock.toggle",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_Z, // Default keybinding is Z
                        "shift_lock.category"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(SHIFT_LOCK.wasPressed()){
                IS_SHIFT_LOCKED = !IS_SHIFT_LOCKED;
            }
        });

        ShiftLOCKConfig.load();
    }
}
