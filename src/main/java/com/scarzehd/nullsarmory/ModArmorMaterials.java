package com.scarzehd.nullsarmory;

import com.scarzehd.nullsarmory.item.ModItems;
import com.scarzehd.nullsarmory.sound.ModSounds;
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

    public static final RegistryEntry<ArmorMaterial> STARMAN = registerMaterial("starman",
            Map.of(
                    ArmorItem.Type.HELMET, 0,
                    ArmorItem.Type.CHESTPLATE, 0,
                    ArmorItem.Type.LEGGINGS, 0,
                    ArmorItem.Type.BOOTS, 0
            ),
            5,
            RegistryEntry.of(ModSounds.SHIELDS_ARMOR_EQUIP),
            () -> Ingredient.ofItems(ModItems.VOIDSHELL_INGOT),
            0.0f,
            0.0f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> TRENCH = registerMaterial("trench",
            Map.of(
                    ArmorItem.Type.HELMET, 0,
                    ArmorItem.Type.CHESTPLATE, 0,
                    ArmorItem.Type.LEGGINGS, 0,
                    ArmorItem.Type.BOOTS, 0
            ),
            5,
            RegistryEntry.of(ModSounds.SHIELDS_ARMOR_EQUIP),
            () -> Ingredient.ofItems(ModItems.VOIDSHELL_INGOT),
            0.0f,
            0.1f,
            false

    );
    public static final RegistryEntry<ArmorMaterial> SCOUT = registerMaterial("scout",
            Map.of(
                    ArmorItem.Type.HELMET, 0,
                    ArmorItem.Type.CHESTPLATE, 0,
                    ArmorItem.Type.LEGGINGS, 0,
                    ArmorItem.Type.BOOTS, 0
            ),
            5,
            RegistryEntry.of(ModSounds.SHIELDS_ARMOR_EQUIP),
            () -> Ingredient.ofItems(ModItems.VOIDSHELL_INGOT),
            0.0f,
            0.0f,
            false

    );
}
