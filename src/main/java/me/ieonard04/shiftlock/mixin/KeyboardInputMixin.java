package me.ieonard04.shiftlock.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.ieonard04.shiftlock.ShiftLOCK;
import me.ieonard04.shiftlock.ShiftLOCKConfig;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {
    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(boolean slowDown, float slowDownFactor, CallbackInfo ci){
        // Handles if not both are active, since XOR and OR are the same for this case
        this.sneaking ^= ShiftLOCK.IS_SHIFT_LOCKED;
        // Handles if both are active
        this.sneaking |= ShiftLOCKConfig.OR & (this.sneaking || ShiftLOCK.IS_SHIFT_LOCKED);
    }
}
