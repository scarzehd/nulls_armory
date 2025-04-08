package com.scarzehd.nullsarmory.components;

import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<IShieldsComponent> SHIELDS = ComponentRegistry.getOrCreate(Identifier.of(NullsArmory.MOD_ID, "shields"), IShieldsComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(LivingEntity.class, SHIELDS).impl(ShieldsComponent.class).end(ShieldsComponent::new);
    }
}
