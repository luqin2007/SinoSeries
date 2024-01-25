package games.moegirl.sinocraft.sinotest;

import games.moegirl.sinocraft.sinotest.data.TestDataItem;
import games.moegirl.sinocraft.sinotest.network.TestNetwork;
import games.moegirl.sinocraft.sinotest.registry.TestRegistry;

public class SinoTest {
    public static final String MODID = "sinotest";

    public static void registerAll() {
        TestRegistry.registerAll();
        TestNetwork.registerAll();
        TestDataItem.registerAll();
    }
}
