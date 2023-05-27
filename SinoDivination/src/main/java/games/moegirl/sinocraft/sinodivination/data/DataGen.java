package games.moegirl.sinocraft.sinodivination.data;

import games.moegirl.sinocraft.sinodivination.SinoDivination;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = SinoDivination.MODID)
public class DataGen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        event.getGenerator().addProvider(true, new ProviderBlockState(event));
        event.getGenerator().addProvider(true, new ProviderItemModel(event));
        generator.addProvider(true, new ProviderLanguageEn(generator));
        generator.addProvider(true, new ProviderLanguageZh(generator));
        generator.addProvider(true, new ProviderLanguageLzh(generator));
        generator.addProvider(true, new ProviderLootTable(generator));
        generator.addProvider(true, new ProviderDatapack(event));
        generator.addProvider(true, new ProviderRecipe(event.getGenerator()));
        ProviderTagBlock provider;
        generator.addProvider(true, provider = new ProviderTagBlock(event));
        generator.addProvider(true, new ProviderTagItem(event, provider));
        generator.addProvider(true, new ProviderTagBiome(event));
        generator.addProvider(true, new ProviderBiomeModifier(event));
    }
}
