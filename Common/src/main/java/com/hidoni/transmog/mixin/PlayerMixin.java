package com.hidoni.transmog.mixin;

import com.hidoni.transmog.RenderUtils;
import com.hidoni.transmog.TransmogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow
    public abstract boolean isLocalPlayer();

    protected PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Inject(method = "getItemBySlot", at = @At("RETURN"), cancellable = true)
    private void transmogItemBySlot(EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack returnValue = cir.getReturnValue();
        if (TransmogUtils.isItemStackTransmogged(returnValue) && RenderUtils.INSTANCE.isCalledForRendering()) {
            cir.setReturnValue(TransmogUtils.getAppearanceStackOrOriginal(returnValue));
        }
    }

    @Inject(method = "attack", at = @At("HEAD"))
    private void attack(Entity target, CallbackInfo ci) {
        if (this.isLocalPlayer() && target instanceof Player) {
            TransmogUtils.startPvP();
        }
    }
}
