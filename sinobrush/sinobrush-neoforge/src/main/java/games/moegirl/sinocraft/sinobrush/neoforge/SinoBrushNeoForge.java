package games.moegirl.sinocraft.sinobrush.neoforge;

import games.moegirl.sinocraft.sinobrush.SinoBrush;
import games.moegirl.sinocraft.sinobrush.client.SinoBrushClient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLLoader;

@Mod(SinoBrush.MODID)
public class SinoBrushNeoForge {
    public SinoBrushNeoForge(IEventBus bus, ModContainer modContainer) {
        SinoBrush.init();
        if (FMLLoader.getDist().isClient()) {
            SinoBrushClient.initClient();
        }

        bus.addListener(this::setupClient);
    }

    private void setupClient(FMLClientSetupEvent event) {
        SinoBrushClient.setupClient();
    }
}
