package games.moegirl.sinocraft.sinofoundation.world;

import games.moegirl.sinocraft.sinocore.world.features.FeaturesHelper;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import static games.moegirl.sinocraft.sinofoundation.SinoFoundation.MODID;

/**
 * @author luqin2007
 */
public class SFDFeatures {

    private static final RuleTest ORE_REPLACEABLE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static final RuleTest DEEPSLATE_ORE_REPLACEABLE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    private static final RuleTest NETHER_ORE_REPLACEABLE = new TagMatchTest(Tags.Blocks.NETHERRACK);

    public static final ResourceKey<ConfiguredFeature<?, ?>> SULPHUR = FeaturesHelper.createKey(new ResourceLocation(MODID, "sulphur"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SULPHUR = FeaturesHelper.createKey(new ResourceLocation(MODID, "nether_sulphur"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_SULPHUR = FeaturesHelper.createKey(new ResourceLocation(MODID, "deepslate_sulphur"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> NITER = FeaturesHelper.createKey(new ResourceLocation(MODID, "niter"));

//    public static final ResourceKey<ConfiguredFeature<?, ?>> RICE = FeatureUtils.createKey(MODID + ":rice");
//    public static final ResourceKey<ConfiguredFeature<?, ?>> REHMANNIA = FeatureUtils.createKey(MODID + ":rehmannia");
//    public static final ResourceKey<ConfiguredFeature<?, ?>> DRAGONLIVER_MELON = FeatureUtils.createKey(MODID + ":dragonliver_melon");

    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_SULPHUR;
    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_NETHER_SULPHUR;
    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_DEEPSLATE_SULPHUR;
    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_NITER;

//    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_RICE;
//    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_REHMANNIA;
//    public static Holder.Reference<ConfiguredFeature<?, ?>> HOLDER_DRAGONLIVER_MELON;

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HOLDER_SULPHUR = context.register(SFDFeatures.SULPHUR, overworldStoneOre(SFDBlocks.SULPHUR_ORE, 6));
        HOLDER_NETHER_SULPHUR = context.register(SFDFeatures.NETHER_SULPHUR, netherrackOre(SFDBlocks.NETHER_SULPHUR_ORE, 6));
        HOLDER_DEEPSLATE_SULPHUR = context.register(SFDFeatures.DEEPSLATE_SULPHUR, overworldDeepslateOre(SFDBlocks.DEEP_SLATE_SULPHUR_ORE, 5));
        HOLDER_NITER = context.register(SFDFeatures.NITER, overworldStoneOre(SFDBlocks.NITER_ORE, 8));
//        HOLDER_RICE = context.register(SFDFeatures.RICE, cropConfiguration(SFDBlocks.RICE));
//        HOLDER_REHMANNIA = context.register(SFDFeatures.REHMANNIA, cropConfiguration(SFDBlocks.REHMANNIA));
//        HOLDER_DRAGONLIVER_MELON = context.register(SFDFeatures.DRAGONLIVER_MELON, cropConfiguration(SFDBlocks.DRAGONLIVER_MELON));
    }

    /**
     * 主世界石头矿石生成配置
     *
     * @param ore  矿物方块
     * @param size 每一组生成的矿石平均个数
     * @return 生成配置
     */
    private static ConfiguredFeature<OreConfiguration, OreFeature> overworldStoneOre(RegistryObject<? extends Block> ore, int size) {
        OreConfiguration configuration = new OreConfiguration(ORE_REPLACEABLE, ore.get().defaultBlockState(), size);
        return new ConfiguredFeature<>((OreFeature) Feature.ORE, configuration);
    }

    /**
     * 主世界深板岩矿石生成配置
     *
     * @param ore  矿物方块
     * @param size 每一组生成的矿石平均个数
     * @return 生成配置
     */
    private static ConfiguredFeature<OreConfiguration, OreFeature> overworldDeepslateOre(RegistryObject<? extends Block> ore, int size) {
        OreConfiguration configuration = new OreConfiguration(DEEPSLATE_ORE_REPLACEABLE, ore.get().defaultBlockState(), size);
        return new ConfiguredFeature<>((OreFeature) Feature.ORE, configuration);
    }

    /**
     * 下界地狱岩矿石生成配置
     *
     * @param ore  矿物方块
     * @param size 每一组生成的矿石平均个数
     * @return 生成配置
     */
    private static ConfiguredFeature<OreConfiguration, OreFeature> netherrackOre(RegistryObject<? extends Block> ore, int size) {
        OreConfiguration configuration = new OreConfiguration(NETHER_ORE_REPLACEABLE, ore.get().defaultBlockState(), size);
        return new ConfiguredFeature<>((OreFeature) Feature.ORE, configuration);
    }


    /**
     * 作物每次生成的配置
     *
     * @param block 作物方块
     * @return 生成配置
     */
    private static ConfiguredFeature<RandomPatchConfiguration, RandomPatchFeature> cropConfiguration(RegistryObject<? extends Block> block) {
        Holder<PlacedFeature> placed = PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block.get())));
        RandomPatchConfiguration configuration = new RandomPatchConfiguration(30, 5, 2, placed);
        return new ConfiguredFeature<>((RandomPatchFeature) Feature.RANDOM_PATCH, configuration);
    }
}
