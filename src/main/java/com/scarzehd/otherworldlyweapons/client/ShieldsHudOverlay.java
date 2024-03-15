package com.scarzehd.otherworldlyweapons.client;

import com.scarzehd.otherworldlyweapons.OtherworldlyWeapons;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class ShieldsHudOverlay implements HudRenderCallback {
    public static final Identifier SHIELD_HEARTS_TEXTURE = new Identifier(OtherworldlyWeapons.MOD_ID, "textures/gui/shield_hearts.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

    }
}
