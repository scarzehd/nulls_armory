package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.components.ModComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ShieldsHudMixin {
    private static final Identifier SHIELD_HEARTS_TEXTURE = Identifier.of(NullsArmory.MOD_ID, "textures/gui/shield_hearts.png");

    private static final Identifier OVERFLOW_HEARTS_TEXTURE = Identifier.of(NullsArmory.MOD_ID, "textures/gui/overflow_hearts.png");


    @Inject(method = "render", at = @At("HEAD"))
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.options.hudHidden) return;
        if (!client.interactionManager.hasStatusBars()) return;

        PlayerEntity player = client.player;

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        int x;

        int renderedHealth = (int)Math.ceil((player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) + player.getAbsorptionAmount()) / 20);

        int y;
        int baseY = height - 40 - renderedHealth * 8;

        if (player.getAttributeValue(EntityAttributes.GENERIC_ARMOR) > 0) {
            baseY -= 10;
        }

        int maxShields = (int)Math.ceil(player.getAttributeValue(ModAttributes.MAX_SHIELDS) / 2);

        double shields = Math.ceil(ModComponents.SHIELDS.get(player).getCurrentShields());

        int overflow = 0;

        if (shields < 0) {
            overflow = (int)Math.ceil(-shields / 2);


            for (int i = 0; i < overflow; i++) {
                x = width / 2 - 91 + (i % 10) * 8;
                y = baseY - (int) (double) (i / 10) * 9;
                drawOverflowContainer(context, x, y, false);
            }

            if (-shields % 2 == 1) {
                x = width / 2 - 91 + (((int)-shields / 2) % 10) * 8;
                y = baseY - (int)Math.floor(-shields / 20) * 9;
                drawOverflowContainer(context, x, y, true);
            }
        }

        if (overflow > maxShields) return;
        for (int i = overflow; i < maxShields; i++) {
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
    }

    private void drawShieldsContainer(DrawContext context, int x, int y) {
        context.drawTexture(SHIELD_HEARTS_TEXTURE, x, y, 20, 0, 9, 9, 29, 9);
    }

    private void drawOverflowContainer(DrawContext context, int x, int y, boolean half) {
        context.drawTexture(OVERFLOW_HEARTS_TEXTURE, x, y, half ? 9 : 0, 0, 9, 9, 18, 9);
    }

    private void drawShieldsHeart(DrawContext context, int x, int y, boolean half) {
        context.drawTexture(SHIELD_HEARTS_TEXTURE, x, y, half ? 10 : 0, 0, 9, 9, 29, 9);
    }
}
