package dev.yaghito.blocksandbeyond.block;

import dev.yaghito.blocksandbeyond.BlocksAndBeyond;
import dev.yaghito.blocksandbeyond.block.custom.IronPole;
import dev.yaghito.blocksandbeyond.block.custom.NoTransitSign;
import dev.yaghito.blocksandbeyond.block.custom.StackedStonesBlock;
import dev.yaghito.blocksandbeyond.block.custom.TrafficCone;
import dev.yaghito.blocksandbeyond.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    public static final DeferredBlock<NoTransitSign> NO_TRANSIT_SIGN = registerBlock("no_transit_sign",
            () -> new
                    NoTransitSign(BlockBehaviour.Properties.of()
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion() // Importante: evita che Minecraft non renderizzi i blocchi adiacenti dietro al cartello custom
            ));

    public static final DeferredBlock<IronPole> IRON_POLE = registerBlock("iron_pole",
            () -> new IronPole(BlockBehaviour.Properties.of()
                    .strength(0.7f, 4.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .isViewBlocking((state, reader, pos) -> false)
                    .isSuffocating((state, reader, pos) -> false)
                    .lightLevel(state -> 0)
            ));
    public static final DeferredBlock<TrafficCone> TRAFFIC_CONE = registerBlock("traffic_cone",
            () -> new TrafficCone(BlockBehaviour.Properties.of()
                    .strength(0.1f, 4.0f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .isViewBlocking((state, reader, pos) -> false)
                    .isSuffocating((state, reader, pos) -> false)
                    .lightLevel(state -> 0)




            )


    );







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
