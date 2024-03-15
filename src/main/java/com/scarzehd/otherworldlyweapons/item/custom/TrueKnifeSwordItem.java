package com.scarzehd.otherworldlyweapons.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;

import java.util.UUID;

public class TrueKnifeSwordItem extends SwordItem {
    public TrueKnifeSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private static final UUID modifierId = UUID.fromString("9c783f24-a1ca-4608-b51c-eb4a3cee9085");
    private static final String modifierName = "monster_killer";

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                NbtCompound stackNbt = stack.getOrCreateNbt();

                if (!stackNbt.contains("AttributeModifiers", NbtElement.LIST_TYPE)) {
                    stackNbt.put("AttributeModifiers", new NbtList());
                }

                NbtList modifierNbt = stackNbt.getList("AttributeModifiers", NbtElement.COMPOUND_TYPE);

                NbtList newModifierNbt = new NbtList();

                double value = (double)Math.round(Math.log(player.experienceLevel + 1) * 2) / 2;

                for (int i = 0; i < modifierNbt.size(); i++) {
                    NbtCompound element = modifierNbt.getCompound(i);
                    if (!element.getUuid("UUID").equals(modifierId)) {
//                            OtherworldlyWeapons.LOGGER.info(element.getUuid("UUID").equals(modifierId) + "");
                        newModifierNbt.add(element);
                    }
                }

                NbtCompound modifier = new EntityAttributeModifier(modifierId, modifierName, value, EntityAttributeModifier.Operation.ADDITION).toNbt();
                modifier.putString("AttributeName", Registries.ATTRIBUTE.getId(EntityAttributes.GENERIC_ATTACK_DAMAGE).toString());
                modifier.putString("Slot", EquipmentSlot.MAINHAND.getName());

                newModifierNbt.add(modifier);
                stackNbt.put("AttributeModifiers", newModifierNbt);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
