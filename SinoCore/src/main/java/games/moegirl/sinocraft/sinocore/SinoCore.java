package games.moegirl.sinocraft.sinocore;

import games.moegirl.sinocraft.sinocore.old.gui.SCMenus;
import games.moegirl.sinocraft.sinocore.old.utility.json.JsonUtils;
import games.moegirl.sinocraft.sinocore.old.utility.json.serializer.FluidStackSerializer;
import games.moegirl.sinocraft.sinocore.old.utility.json.serializer.IngredientSerializer;
import games.moegirl.sinocraft.sinocore.old.utility.json.serializer.ItemStackSerializer;
import games.moegirl.sinocraft.sinocore.old.utility.json.serializer.NonNullListSerializer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(SinoCore.MODID)
public class SinoCore {
    public static final Logger LOGGER = LoggerFactory.getLogger("SinoCore");

    // Todo: qyl27: use sinoseries.debug instead.
    public static final boolean DEBUG = System.getProperty("sinoseries.debug", "false").equalsIgnoreCase("true");

    public static final String MODID = "sinocore";

    private static SinoCore INSTANCE = null;

    public SinoCore() {
        LOGGER.info("Loading SinoCore.");

        INSTANCE = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SCMenus.register(bus);

        bus.addListener(this::onSetup);
        bus.addListener(this::onClientSetup);

//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, QuizModelConfig.SPEC, "sinoseries/sinocore/quiz.toml");

        JsonUtils.INSTANCE
                .registerAdapter(Ingredient.class, new IngredientSerializer())
                .registerAdapter(ItemStack.class, new ItemStackSerializer())
                .registerAdapter(FluidStack.class, new FluidStackSerializer())
                .registerAdapter(NonNullList.class, new NonNullListSerializer());

        LOGGER.info("SinoCore loaded!");
    }

    public static SinoCore getInstance() {
        return INSTANCE;
    }

    private void onSetup(FMLCommonSetupEvent event) {
//        if (QuizModelConfig.CONFIG.ENABLED.get()) {
//            QuizModel.fetch();
//        }
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        if (DEBUG) {
            // Fixme: qyl27: Not working for debug show highlighted block shape box.
//            MinecraftForge.EVENT_BUS.register(new DebugBlockHighlighter());
        }
    }
}
