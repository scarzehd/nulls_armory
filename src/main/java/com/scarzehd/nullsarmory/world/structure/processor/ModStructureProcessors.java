package com.scarzehd.nullsarmory.world.structure.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;

public class ModStructureProcessors {
    public static void registerStructureProcessors() {}

    public static final StructureProcessorType<SignProcessor> SIGN_PROCESSOR = register("sign_processor", SignProcessor.CODEC);

    static <P extends StructureProcessor> StructureProcessorType<P> register(String id, MapCodec<P> codec) {
        return Registry.register(Registries.STRUCTURE_PROCESSOR, Identifier.of(NullsArmory.MOD_ID, id), () -> codec);
    }
}
