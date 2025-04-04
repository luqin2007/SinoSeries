package games.moegirl.sinocraft.sinotest.network;

import games.moegirl.sinocraft.sinocore.network.NetworkManager;
import games.moegirl.sinocraft.sinocore.registry.IRegRef;
import games.moegirl.sinocraft.sinotest.registry.TestRegistry;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.world.item.Item;

public class TestNetwork {

    public static IRegRef<TestNetworkItem> ITEM;

    public static void registerAll() {

        NetworkManager.playPacket(C2SHelloPacket.TYPE)
                .codec(C2SHelloPacket.STREAM_CODEC)
                .serverHandler(C2SHelloPacket::handle)
                .register();

        NetworkManager.playPacket(S2CHelloPacket.TYPE)
                .codec(S2CHelloPacket.STREAM_CODEC)
                .clientHandler(S2CHelloPacket::handle)
                .register();

        ITEM = TestRegistry.ITEMS.register("test_network", TestNetworkItem::new);
    }
}
