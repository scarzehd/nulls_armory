package com.scarzehd.nullsarmory.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.components.ModComponents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class ShieldsHudOverlay implements HudRenderCallback {
    public static final Identifier SHIELD_HEARTS_TEXTURE = new Identifier(NullsArmory.MOD_ID, "textures/gui/shield_hearts.png");

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.options.hudHidden) return;
        if (!client.interactionManager.hasStatusBars()) return;

        PlayerEntity player = client.player;

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        int x;

        int renderedHealth = (int)Math.ceil((player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) + player.getAbsorptionAmount()) / 10);

        int y;
        int baseY = height - 29 - renderedHealth * 10;

        if (player.getAttributeValue(EntityAttributes.GENERIC_ARMOR) > 0) {
            baseY -= 10;
        }

        int maxShields = (int)Math.ceil(player.getAttributeValue(ModAttributes.MAX_SHIELDS)) / 2;

        double shields = Math.ceil(ModComponents.SHIELDS.get(player).getCurrentShields());

        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();

        for (int i = 0; i < maxShields; i++) {
            x = width / 2 - 91 + (i % 10) * 8;
            y = baseY - (int) (double) (i / 10) * 10;
            drawShieldsContainer(context, x, y);
        }

        for (int i = 0; i < Math.floor(shields / 2); i++) {
            x = width / 2 - 91 + (i % 10) * 8;
            y = baseY - (int) (double) (i / 10) * 10;
            drawShieldsHeart(context, x, y, false);
        }

        if (shields % 2 == 1) {
            x = width / 2 - 91 + (((int)shields / 2) % 10) * 8;
            y = baseY - (int)Math.floor(shields / 20) * 10;
            drawShieldsHeart(context, x, y, true);
        }

        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
    }

    private void drawShieldsContainer(DrawContext context, int x, int y) {
        context.drawTexture(SHIELD_HEARTS_TEXTURE, x, y, 18, 0, 9, 9, 27, 9);
    }

    private void drawShieldsHeart(DrawContext context, int x, int y, boolean half) {
        context.drawTexture(SHIELD_HEARTS_TEXTURE, x, y, half ? 9 : 0, 0, 9, 9, 27, 9);
    }
}
