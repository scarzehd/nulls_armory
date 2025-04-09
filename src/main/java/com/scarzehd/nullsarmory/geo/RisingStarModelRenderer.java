package com.scarzehd.nullsarmory.geo;

import com.scarzehd.nullsarmory.item.custom.ModelArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class RisingStarModelRenderer extends GeoArmorRenderer<ModelArmorItem> {
    public RisingStarModelRenderer(Identifier model, Identifier texture) {
        super((DefaultedItemGeoModel)new DefaultedItemGeoModel<>(model).withAltTexture(texture));
    }
}
