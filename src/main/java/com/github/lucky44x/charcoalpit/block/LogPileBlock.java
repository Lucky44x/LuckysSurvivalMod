package com.github.lucky44x.charcoalpit.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;

public class LogPileBlock extends RotatedPillarBlock implements EntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public LogPileBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, AXIS);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ActivePileTile(false,pos,state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type,ModTileEntities.ActivePile,ActivePileTile::tick);
    }
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> entitytype, BlockEntityTicker<? super E> ticker) {
        return type == entitytype ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        if (worldIn.getBlockState(fromPos).getBlock() == Blocks.FIRE) {
            if (!state.getValue(LIT))
                igniteLogs(worldIn, pos);
        } else if (state.getValue(LIT)) {
            ((ActivePileTile) worldIn.getBlockEntity(pos)).isValid = false;
        }
    }

    public static void igniteLogs(LevelAccessor world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == ModBlocks.LogPile && !state.getValue(BlockStateProperties.LIT)) {
            world.setBlock(pos, state.setValue(LIT, true), 2);
            Direction[] neighbors = Direction.values();
            for (int i = 0; i < neighbors.length; i++) {
                igniteLogs(world, pos.relative(neighbors[i]));
            }
        }
    }
}
