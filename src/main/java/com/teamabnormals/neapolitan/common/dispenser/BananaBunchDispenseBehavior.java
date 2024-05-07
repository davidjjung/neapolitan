package com.teamabnormals.neapolitan.common.dispenser;

import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public class BananaBunchDispenseBehavior extends DefaultDispenseItemBehavior {

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
		Position position = DispenserBlock.getDispensePosition(source);

		float yaw = direction.toYRot();
		float pitch = direction.getStepY() * -90.0F;
		
		BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(source.getLevel());
		bananapeel.moveTo(position.x(), position.y(), position.z(), yaw, 0.0F);
		bananapeel.throwPeel(pitch, yaw, 0.0D, 0.0D, 0.0D, 6.0F);
		source.getLevel().addFreshEntity(bananapeel);

		stack.shrink(1);
		spawnItem(source.getLevel(), new ItemStack(NeapolitanItems.BANANA.get(), 1 + source.getLevel().random.nextInt(3)), 6, direction, position);

		return stack;
	}
}