package games.moegirl.sinocraft.sinocore.data.gen;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractBiomeModifierProvider implements DataProvider {
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    protected final PackOutput output;
    protected final String modid;

    protected final List<Feature> features = new ArrayList<>();

    public AbstractBiomeModifierProvider(PackOutput output, String modid) {
        this.output = output;
        this.modid = modid;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        registerBiomeModifiers();

        var path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve(modid)
                .resolve("forge")
                .resolve("biome_modifier");

        var futuresBuilder = ImmutableList.<CompletableFuture<?>>builder();

        features.forEach(f -> {
            JsonObject json = new JsonObject();
            json.addProperty("type", "forge:add_features");
            json.add("biomes", GSON.toJsonTree(f.hasBiomes()
                    ? Arrays.stream(f.biomes()).map(k -> k.location().toString())
                    : f.hasTag() ? "#" + f.biomesTag().location() : null));
            json.addProperty("features", f.feature().location().toString());
            json.addProperty("step", f.step().getName());

            var p = path.resolve(f.feature().location().getPath() + ".json");
            futuresBuilder.add(DataProvider.saveStable(cachedOutput, json, p));
        });

        return CompletableFuture.allOf(futuresBuilder.build().toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return modid + ": Biome Modifiers";
    }

    protected abstract void registerBiomeModifiers();

    protected void add(Feature f) {
        features.add(f);
    }

    public record Feature(ResourceKey<ConfiguredFeature<?, ?>> feature, GenerationStep.Decoration step,
                           TagKey<Biome> biomesTag, ResourceKey<Biome>... biomes) {
        @SafeVarargs
        public Feature {
        }

        public boolean hasTag() {
            return biomesTag != null;
        }

        public boolean hasBiomes() {
            return biomes != null && biomes.length >= 1;
        }
    }
}
