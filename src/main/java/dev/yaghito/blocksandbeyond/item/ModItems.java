package dev.yaghito.blocksandbeyond.item;

import dev.yaghito.blocksandbeyond.BlocksAndBeyond;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlocksAndBeyond.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
