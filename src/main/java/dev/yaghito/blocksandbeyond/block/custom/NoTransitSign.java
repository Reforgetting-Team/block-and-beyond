
package dev.yaghito.blocksandbeyond.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NoTransitSign extends HorizontalDirectionalBlock {
    public static final MapCodec<NoTransitSign> CODEC = simpleCodec(NoTransitSign::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WALL_MOUNTED = BooleanProperty.create("wall_mounted");

    public static final VoxelShape NORTH_SOUTH = Block.box(0, 0, 7, 16, 16, 9);
    public static final VoxelShape EAST_WEST = Block.box(7, 0, 0, 9, 16, 16);

    public NoTransitSign(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WALL_MOUNTED, false));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(WALL_MOUNTED, context.getClickedFace().getAxis().isHorizontal());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WALL_MOUNTED);
    }

    private VoxelShape translatedShape(BlockState state) {
        VoxelShape shape = switch (state.getValue(FACING)) {
            case NORTH, SOUTH -> NORTH_SOUTH;
            case EAST, WEST -> EAST_WEST;
            default -> NORTH_SOUTH;
        };

        if (!state.getValue(WALL_MOUNTED)) {
            return shape;
        }

        return switch (state.getValue(FACING)) {
            case NORTH -> shape.move(0, 0, -0.45);
            case SOUTH -> shape.move(0, 0, 0.45);
            case WEST -> shape.move(-0.45, 0, 0);
            case EAST -> shape.move(0.45, 0, 0);
            default -> shape;
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return translatedShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return translatedShape(state);
    }
}
