package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BananaBunchItem extends Item {

	public BananaBunchItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getClickedFace() == Direction.UP) {
			Level world = context.getLevel();
			this.placeBanana(world, context.getClickLocation().x(), context.getClickLocation().y(), context.getClickLocation().z(), context.getRotation());
			this.handleOpening(world, context.getPlayer(), context.getHand(), context.getItemInHand());
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (!target.getType().is(NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING)) {
			Level world = player.level();
			this.placeBanana(world, target.getX(), target.getY(), target.getZ(), player.getViewYRot(1.0F));
			this.handleOpening(world, player, hand, stack);
			return InteractionResult.sidedSuccess(world.isClientSide);
		} else {
			return super.interactLivingEntity(stack, player, target, hand);
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		this.throwPeel(level, player, player.getViewXRot(1.0F), player.getViewYRot(1.0F));
		this.handleOpening(level, player, hand, player.getItemInHand(hand));
		return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
	}

	private void placeBanana(Level level, double posX, double posY, double posZ, float yaw) {
		if (!level.isClientSide) {
			BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(level);
			bananapeel.moveTo(posX, posY, posZ, yaw, 0.0F);
			level.addFreshEntity(bananapeel);
		}
	}

	private void throwPeel(Level level, Player player, float pitch, float yaw) {
		if (!level.isClientSide) {
			BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(level);
			bananapeel.moveTo(player.getX(), player.getEyeY() - (double) 0.1F, player.getZ(), yaw, 0.0F);
			Vec3 vec3 = player.getDeltaMovement();
			bananapeel.throwPeel(pitch, yaw, vec3.x, player.onGround() ? 0.0D : vec3.y, vec3.z, 1.0F);
			level.addFreshEntity(bananapeel);
		}
	}

	private void handleOpening(Level level, Player player, InteractionHand hand, ItemStack stack) {
		if (!level.isClientSide) {
			player.getCooldowns().addCooldown(this, 5);

			if (!player.getAbilities().instabuild) {
				stack.shrink(1);
			}

			if (player instanceof ServerPlayer serverplayerentity) {
				CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
				serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
			}

			if (stack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(NeapolitanItems.BANANA.get(), 1 + level.random.nextInt(3)));
			} else {
				ItemStack itemstack = new ItemStack(NeapolitanItems.BANANA.get(), 1 + level.random.nextInt(3));
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}
		}

		level.playSound(player, player.blockPosition(), NeapolitanSoundEvents.BANANA_BUNCH_OPEN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
	}
}
