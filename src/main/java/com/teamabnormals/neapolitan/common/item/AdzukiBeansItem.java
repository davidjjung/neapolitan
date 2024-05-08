package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AdzukiBeansItem extends Item {

	public AdzukiBeansItem(Properties builder) {
		super(builder);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = world.getBlockState(pos);
		Direction face = context.getClickedFace();
		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();

		if ((state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK)) && world.getBlockState(pos.above()).isAir() && face == Direction.UP) {
			if (!world.isClientSide())
				world.setBlockAndUpdate(pos, NeapolitanBlocks.ADZUKI_SOIL.get().defaultBlockState());
			world.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
			if (player instanceof ServerPlayer) {
				CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, pos, stack);
			}
			if (player != null && !player.getAbilities().instabuild) stack.shrink(1);
			return InteractionResult.sidedSuccess(world.isClientSide);
		}

		return InteractionResult.PASS;
	}
}