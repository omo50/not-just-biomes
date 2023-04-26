package dev.worldgen.njb.worldgen;

import dev.worldgen.njb.NotJustBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.Arrays;
import java.util.List;

public class NJBBiomeModifications {
    private static final TagKey<Biome> OAK_FOREST = TagKey.of(Registry.BIOME_KEY, new Identifier(NotJustBiomes.MOD_ID, "oak_forest"));
    private static final List<String> birchFeatures = Arrays.asList("fallen_log","block_patch", "rock");
    private static final List<String> forestFeatures = Arrays.asList("fallen_log","block_patch", "rock");
    private static final List<String> taigaFeatures = Arrays.asList("fallen_log","block_patch", "rock");
    private static final RegistryKey<PlacedFeature> DESERT_DUNGEON = getRegistryKeyWithPath("dungeon/desert");
    public static final RegistryKey<PlacedFeature> JUNGLE_DUNGEON = getRegistryKeyWithPath("dungeon/jungle");
    public static final RegistryKey<PlacedFeature> BADLANDS_DUNGEON = getRegistryKeyWithPath("dungeon/badlands");
    public static final RegistryKey<PlacedFeature> MOUNTAIN_DUNGEON = getRegistryKeyWithPath("dungeon/mountain");
    public static RegistryKey<PlacedFeature> getRegistryKeyWithPath(String path) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(NotJustBiomes.MOD_ID, path));
    }

    public static RegistryKey<PlacedFeature> getRegistryKeyWithModuleAndPath(String moduleFolder, String path) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(NotJustBiomes.MOD_ID, moduleFolder.concat(path)));
    }

    public static void placeModuleFeatures(List<String> features, TagKey<Biome> biomeTagKey, String moduleFolder) {
        for (String feature : features)
            BiomeModifications.addFeature(
                BiomeSelectors.tag(biomeTagKey),
                GenerationStep.Feature.VEGETAL_DECORATION,
                getRegistryKeyWithModuleAndPath(moduleFolder, feature)
            );
    }
    public static void placeFeaturesInWorld() {
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.DESERT), GenerationStep.Feature.UNDERGROUND_STRUCTURES, DESERT_DUNGEON);
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.JUNGLE), GenerationStep.Feature.UNDERGROUND_STRUCTURES, JUNGLE_DUNGEON);
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BADLANDS), GenerationStep.Feature.UNDERGROUND_STRUCTURES, BADLANDS_DUNGEON);
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.MOUNTAIN), GenerationStep.Feature.UNDERGROUND_STRUCTURES, MOUNTAIN_DUNGEON);

        placeModuleFeatures(birchFeatures, ConventionalBiomeTags.BIRCH_FOREST, "birch_forest/");
        placeModuleFeatures(forestFeatures, OAK_FOREST, "forest/");
        placeModuleFeatures(taigaFeatures, ConventionalBiomeTags.TAIGA, "taiga/");
    }
}
