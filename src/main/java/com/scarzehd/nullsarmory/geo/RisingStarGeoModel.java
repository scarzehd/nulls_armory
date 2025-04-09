package com.scarzehd.nullsarmory.geo;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.custom.ModelArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RisingStarGeoModel extends GeoModel<ModelArmorItem> {
    @Override
    public Identifier getModelResource(ModelArmorItem item) {
        return item.model;
    }

    @Override
    public Identifier getTextureResource(ModelArmorItem item) {
        return item.texture;
    }

    @Override
    public Identifier getAnimationResource(ModelArmorItem geoAnimatable) {
        return null;
    }
}
