package com.enderdevelopment.frontierwarfare.item;

import com.enderdevelopment.frontierwarfare.FrontierWarfare;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public final class FieldRifleItem extends Item {
    private static final double RANGE = 64.0;
    private static final float DAMAGE = 9.0F;

    public FieldRifleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack rifle = player.getItemInHand(hand);
        if (!hasCartridge(player)) {
            return InteractionResultHolder.fail(rifle);
        }
        if (!level.isClientSide) {
            consumeCartridge(player);
            player.getCooldowns().addCooldown(this, 12);
            var start = player.getEyePosition();
            var end = start.add(player.getLookAngle().scale(RANGE));
            BlockHitResult blockHit = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE, player));
            double maxDistance = blockHit.getType() == HitResult.Type.MISS ? RANGE
                    : start.distanceTo(blockHit.getLocation());
            Entity target = level.getEntities(player, player.getBoundingBox().expandTowards(
                            player.getLookAngle().scale(maxDistance)).inflate(1.0), entity -> entity instanceof LivingEntity)
                    .stream().filter(entity -> entity.getBoundingBox().inflate(0.25).clip(start, end)
                            .map(point -> start.distanceToSqr(point) <= maxDistance * maxDistance).orElse(false))
                    .min((a, b) -> Double.compare(a.distanceToSqr(start), b.distanceToSqr(start))).orElse(null);
            if (target != null && (blockHit.getType() == HitResult.Type.MISS
                    || target.distanceToSqr(start) < start.distanceToSqr(blockHit.getLocation()))) {
                DamageSource source = level.damageSources().playerAttack(player);
                target.hurt(source, DAMAGE);
            }
            level.playSound(null, player.blockPosition(), SoundEvents.GENERIC_EXPLODE,
                    SoundSource.PLAYERS, 0.55F, 1.35F);
            rifle.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
        }
        return InteractionResultHolder.sidedSuccess(rifle, level.isClientSide);
    }

    private static boolean hasCartridge(Player player) {
        return player.getAbilities().instabuild || player.getInventory().contains(new ItemStack(FrontierWarfare.CARTRIDGE));
    }

    private static void consumeCartridge(Player player) {
        if (player.getAbilities().instabuild) return;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.is(FrontierWarfare.CARTRIDGE)) {
                stack.shrink(1);
                return;
            }
        }
    }
}
