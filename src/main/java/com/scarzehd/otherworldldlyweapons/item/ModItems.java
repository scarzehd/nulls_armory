package com.scarzehd.otherworldldlyweapons.item;

import com.scarzehd.otherworldldlyweapons.OtherworldlyWeapons;
import com.scarzehd.otherworldldlyweapons.item.custom.TrueKnifeSwordItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static void registerItems() {
        OtherworldlyWeapons.LOGGER.info("Registering Mod Items");
    }

    public static Item TEST_SWORD = registerItem("test_sword", new SwordItem(ToolMaterials.DIAMOND, 20, 2.5f, new FabricItemSettings().maxCount(1).maxDamage(1000)));

    public static Item TRUE_KNIFE = registerItem("true_knife", new TrueKnifeSwordItem(ToolMaterials.DIAMOND, 1, 2, new FabricItemSettings().maxCount(1).maxDamage(1000)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OtherworldlyWeapons.MOD_ID, name), item);
    }
}
