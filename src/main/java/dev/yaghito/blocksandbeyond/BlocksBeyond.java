package dev.yaghito.blocksandbeyond;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(BlocksBeyond.MODID)
public class BlocksBeyond {
    public static final String MODID = "blocksandbeyond";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BlocksBeyond(IEventBus modEventBus, ModContainer modContainer) {
    }
}
