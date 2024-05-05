package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanRabbitVariants extends BlueprintRabbitVariants {
	private static final int UNIQUE_OFFSET = 3421;

	public static final BlueprintRabbitVariant STRAWBERRY = BlueprintRabbitVariants.register(UNIQUE_OFFSET, new ResourceLocation(Neapolitan.MOD_ID, "strawberry"), context -> getBiome(context).is(NeapolitanBiomeTags.SPAWNS_STRAWBERRY_RABBITS));
}