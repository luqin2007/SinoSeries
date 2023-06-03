package games.moegirl.sinocraft.sinodivination;

import games.moegirl.sinocraft.sinocore.item.tab.TabsRegistry;
import games.moegirl.sinocraft.sinodivination.block.SDBlocks;
import games.moegirl.sinocraft.sinodivination.blockentity.SDBlockEntities;
import games.moegirl.sinocraft.sinodivination.client.screen.SDScreens;
import games.moegirl.sinocraft.sinodivination.item.SDItems;
import games.moegirl.sinocraft.sinodivination.menu.SDMenus;
import games.moegirl.sinocraft.sinodivination.recipe.SDRecipes;
import games.moegirl.sinocraft.sinodivination.tree.SDTrees;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(SinoDivination.MODID)
public class SinoDivination {
    public static final String MODID = "sinodivination";
    public static final String NAME = "SinoDivination";
    public static final String VERSION = "@version@";

    public static ResourceLocation TAB;

    private static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public SinoDivination() {
        LOGGER.info("Loading SinoDivination. Ver: " + VERSION);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        TabsRegistry tab = TabsRegistry.register(new ResourceLocation(MODID, "tab"), bus);
        TAB = tab.name();

//        SDNetworks.register();
        SDBlocks.REGISTRY.register(bus);
        SDItems.register(bus);
        SDBlockEntities.REGISTRY.register(bus);
//        SDEntities.REGISTRY.register(bus);
        SDMenus.REGISTRY.register(bus);
        SDRecipes.REGISTRY_SERIALIZER.register(bus);
        SDScreens.register(bus);
        SDTrees.register(bus);

        tab.add(SDItems.REGISTRY);

        LOGGER.info("Reverence for heaven and earth, respect ghosts and gods.");
    }
}
