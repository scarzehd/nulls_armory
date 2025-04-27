package com.scarzehd.nullsarmory.client;

import com.scarzehd.nullsarmory.item.ModItems;
import com.scarzehd.nullsarmory.sound.RechargeSoundHandler;
import com.scarzehd.nullsarmory.sound.client.ClientRechargeSoundHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.item.CompassItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.GlobalPos;

public class NullsArmoryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(ModItems.VOID_COMPASS, Identifier.ofVanilla("angle"), new CompassAnglePredicateProvider((world, stack, entity) -> {
            LodestoneTrackerComponent lodestoneTrackerComponent = stack.get(DataComponentTypes.LODESTONE_TRACKER);
            return lodestoneTrackerComponent != null ? (GlobalPos)lodestoneTrackerComponent.target().orElse(null) : CompassItem.createSpawnPos(world);
        }));

        RechargeSoundHandler.instance = new ClientRechargeSoundHandler();
    }
}
