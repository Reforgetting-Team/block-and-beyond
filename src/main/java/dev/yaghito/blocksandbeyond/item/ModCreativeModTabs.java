package dev.yaghito.blocksandbeyond.item;

import dev.yaghito.blocksandbeyond.BlocksAndBeyond;
import dev.yaghito.blocksandbeyond.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlocksAndBeyond.MODID);

    public static final Supplier<CreativeModeTab> BLOCKS_AND_BEYOND_TAB = CREATIVE_MODE_TAB.register("blocks_and_beyond_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.STACKED_STONES_BLOCK.get()))
            .title(Component.translatable("creativetab.blocksandbeyond.main_tab"))
            .displayItems((itemDisplayParameters, output) -> {

              output.accept(ModBlocks.STACKED_STONES_BLOCK);
              output.accept(ModBlocks.NO_TRANSIT_SIGN_BLOCK);

            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
