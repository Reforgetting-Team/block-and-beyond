package dev.yaghito.blocksandbeyond;

import dev.yaghito.blocksandbeyond.block.ModBlocks;

import dev.yaghito.blocksandbeyond.item.ModCreativeModTabs;
import dev.yaghito.blocksandbeyond.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(dev.yaghito.blocksandbeyond.BlocksAndBeyond.MODID)
public class BlocksAndBeyond {
    public static final String MODID = "blocksandbeyond";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BlocksAndBeyond(IEventBus modEventBus, ModContainer modContainer, Dist dist) {

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modEventBus.addListener(Config::onLoad);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        if (dist.isClient()) {
            modEventBus.addListener(ClientModEvents::onClientSetup);
        }
    }


    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.STACKED_STONES_BLOCK);
        }
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }


    public static class ClientModEvents {
        public static void onClientSetup(final FMLClientSetupEvent event) {

        }
    }
}
