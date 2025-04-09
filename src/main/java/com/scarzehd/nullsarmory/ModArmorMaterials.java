package com.scarzehd.nullsarmory;

import com.scarzehd.nullsarmory.item.ModItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static void registerArmorMaterials() {}

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.of(NullsArmory.MOD_ID, id), "", dyeable));

        ArmorMaterial material = new ArmorMaterial(defense, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);

        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(NullsArmory.MOD_ID, id), material);

        return RegistryEntry.of(material);
    }

    public static final RegistryEntry<ArmorMaterial> RISING_STAR = registerMaterial("rising_star",
            Map.of(
                    ArmorItem.Type.HELMET, 0,
                    ArmorItem.Type.CHESTPLATE, 0,
                    ArmorItem.Type.LEGGINGS, 0,
                    ArmorItem.Type.BOOTS, 0
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            () -> Ingredient.ofItems(ModItems.VOIDGEL_ORB),
            0.0f,
            0.0f,
            false
    );
}
