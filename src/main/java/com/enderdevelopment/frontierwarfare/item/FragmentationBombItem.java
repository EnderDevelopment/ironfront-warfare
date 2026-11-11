package com.enderdevelopment.frontierwarfare.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public final class FragmentationBombItem extends Item {
    private static final float BLAST_POWER = 3.5F;

    public FragmentationBombItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack bomb = player.getItemInHand(hand);
        if (!level.isClientSide) {
            var start = player.getEyePosition();
            var end = start.add(player.getLookAngle().scale(12.0));
            BlockHitResult hit = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE, player));
            BlockPos detonation = hit.getType() == HitResult.Type.MISS
                    ? BlockPos.containing(end) : hit.getBlockPos().relative(hit.getDirection());
            level.explode(player, detonation.getX() + 0.5, detonation.getY() + 0.5, detonation.getZ() + 0.5,
                    BLAST_POWER, Level.ExplosionInteraction.TNT);
            if (!player.getAbilities().instabuild) bomb.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(bomb, level.isClientSide);
    }
}
