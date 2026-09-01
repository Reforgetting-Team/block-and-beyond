package dev.yaghito.blocksandbeyond.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;

public class StackedStonesBlock extends Block {
    public static final MapCodec<StackedStonesBlock> CODEC = simpleCodec(StackedStonesBlock::new);

    public StackedStonesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }
}
