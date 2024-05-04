package com.teamabnormals.neapolitan.core.other;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures.*;

public class NeapolitanGeneration {

	public static void strawberryFields(BiomeGenerationSettings.Builder generation) {
		OverworldBiomes.globalOverworldGeneration(generation);
		BiomeDefaultFeatures.addPlainGrass(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, TREES_STRAWBERRY_FIELDS);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLOWER_STRAWBERRY_FIELDS);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, SINGLE_CORNFLOWER);
		generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_STRAWBERRY_BUSH_COMMON);
		generation.addFeature(Decoration.VEGETAL_DECORATION, PATCH_STRAWBERRY_BUSH_SMALL);
	}
}