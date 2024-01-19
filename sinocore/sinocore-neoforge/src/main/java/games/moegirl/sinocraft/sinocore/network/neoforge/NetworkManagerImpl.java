package games.moegirl.sinocraft.sinocore.network.neoforge;

import games.moegirl.sinocraft.sinocore.network.INetworkChannel;
import net.minecraft.resources.ResourceLocation;

public class NetworkManagerImpl {

    public static INetworkChannel _create(ResourceLocation id) {
        return new NeoForgeNetworkChannelImpl(id);
    }
}
