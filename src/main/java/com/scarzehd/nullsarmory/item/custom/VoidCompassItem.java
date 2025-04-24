package com.scarzehd.nullsarmory.item.custom;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.block.ModBlocks;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CompassItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VoidCompassItem extends CompassItem {
    public VoidCompassItem() {
        super(new Settings().maxCount(1));
    }

    private int searchDistance = 64;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));



        List<BlockPos> hits = BlockPos.streamOutwards(user.getBlockPos(), searchDistance, 384, searchDistance).filter(blockPos -> {
            boolean isLoaded = world.isPosLoaded(blockPos.getX(), blockPos.getZ());
            boolean isVoidOre = world.getBlockState(blockPos).getBlock().equals(ModBlocks.VOID_ORE);

            return isLoaded && isVoidOre;
        }).map(BlockPos::toImmutable).toList();

        ItemStack stack = user.getStackInHand(hand);

        if (hits.isEmpty()) return TypedActionResult.fail(stack);

        BlockPos closest = hits.getFirst();

        GlobalPos globalPos = GlobalPos.create(world.getRegistryKey(), closest);

        LodestoneTrackerComponent lodestoneTrackerComponent = new LodestoneTrackerComponent(Optional.of(globalPos), true);

        stack.set(DataComponentTypes.LODESTONE_TRACKER, lodestoneTrackerComponent);

        return TypedActionResult.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}

    @Override
    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey();
    }
}
