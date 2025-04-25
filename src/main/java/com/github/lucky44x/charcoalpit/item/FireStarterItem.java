package com.github.lucky44x.charcoalpit.item;

import com.github.lucky44x.charcoalpit.config.CharcoalpitConfig;
import com.github.lucky44x.charcoalpit.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FireStarterItem extends Item {
    public FireStarterItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntityIn, ItemStack stack, int countLeft) {
        if (livingEntityIn instanceof final Player player) {
            final BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

            final BlockPos pos = result.getBlockPos();
            final BlockPos abovePos = pos.above();

            if (level.isClientSide) {
                Vec3 location = result.getLocation();
                makeEffects(level, player, location.x(), location.y(), location.z(), countLeft, getUseDuration(stack), level.random);
            }
            else if (countLeft == 1) {
                if (!player.isCreative()) {
                    stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                }

                if(CampfireBlock.canLight(level.getBlockState(pos))) {
                    level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1F, player.getRandom().nextFloat()*0.4F+0.8F);
                    level.setBlock(pos, level.getBlockState(pos).setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                } else if (BaseFireBlock.canBePlacedAt(level, abovePos, Direction.UP)) {
                    BlockState state = BaseFireBlock.getState(level, abovePos);
                    level.setBlockAndUpdate(abovePos, state);
                    level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1F, player.getRandom().nextFloat()*0.4F+0.8F);
                } else {
                    player.releaseUsingItem();
                }
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final Player player = context.getPlayer();
        if (player != null) {
            player.startUsingItem(context.getHand());
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 30;
    }

    private void makeEffects(Level world, Player player, double x, double y, double z, int countLeft, int total, RandomSource random) {
        int count = total - countLeft;
        if (random.nextFloat() + 0.3 > count / (double)total) {
            world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0F, 0.1F, 0.0F);
        }

        if (countLeft < 10 && random.nextFloat() + 0.3 > count / (double)total){
            world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0F, 0.1F, 0.0F);
        }

        if (count % 3 == 1) {
            player.playSound(ModSounds.FIRESTARTER.get(), 0.5F, 0.05F);
        }
    }
}
