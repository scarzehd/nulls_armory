package com.scarzehd.nullsarmory.components;

import com.scarzehd.nullsarmory.NullsArmory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<IShieldsComponent> SHIELDS = ComponentRegistry.getOrCreate(new Identifier(NullsArmory.MOD_ID, "shields"), IShieldsComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(LivingEntity.class, SHIELDS).impl(ShieldsComponent.class).end(ShieldsComponent::new);
    }
}
