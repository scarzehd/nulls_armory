package com.scarzehd.otherworldldlyweapons.item;

import com.scarzehd.otherworldldlyweapons.OtherworldlyWeapons;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static void registerItems() { }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OtherworldlyWeapons.MOD_ID, name), item);
    }
}
