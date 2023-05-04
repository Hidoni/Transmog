package com.hidoni.transmog.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    // This function has the actual DamageSource, but calls this.hurt with damageSources.generic() so we can't see what got hurt at that point, so we do it here!
    @Inject(method = "handleDamageEvent", at=@At("HEAD"))
    private void handlePlayerDamageEvent(DamageSource source, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && this.is(player) && source.getEntity() instanceof Player) {
            TransmogUtils.startPvP();
        }
    }
}
