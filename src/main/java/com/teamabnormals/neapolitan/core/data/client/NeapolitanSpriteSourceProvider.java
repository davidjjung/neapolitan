package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanTrimPatterns;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public final class NeapolitanSpriteSourceProvider extends SpriteSourceProvider {

	public NeapolitanSpriteSourceProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, helper, Neapolitan.MOD_ID);
	}

	@Override
	protected void addSources() {
		this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.patternPermutationsOfVanillaMaterials(NeapolitanTrimPatterns.PRIMAL));
	}
}