package dev.yaghito.blocksandbeyond.block;

import com.mojang.serialization.MapCodec;
import dev.yaghito.blocksandbeyond.BlocksAndBeyond;
import dev.yaghito.blocksandbeyond.block.custom.NoTransitSignBlock;
import dev.yaghito.blocksandbeyond.block.custom.StackedStonesBlock;
import dev.yaghito.blocksandbeyond.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(BlocksAndBeyond.MODID);

    public static final DeferredBlock<StackedStonesBlock> STACKED_STONES_BLOCK = registerBlock("stacked_stones_block",
            () -> new StackedStonesBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<NoTransitSignBlock> NO_TRANSIT_SIGN_BLOCK = registerBlock("no_transit_sign_block",
            () -> new NoTransitSignBlock(BlockBehaviour.Properties.of()
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion() // Importante: evita che Minecraft non renderizzi i blocchi adiacenti dietro al cartello custom
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
