package me.ieonard04.shiftlock.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {

}
