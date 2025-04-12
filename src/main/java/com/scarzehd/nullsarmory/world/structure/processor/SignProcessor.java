package com.scarzehd.nullsarmory.world.structure.processor;

import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.util.SignUtilities;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignText;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SignProcessor extends StructureProcessor {
//    public static final MapCodec<SignProcessor> CODEC = RecordCodecBuilder.mapCodec(
//            instance -> instance.group(
//                    SignText.CODEC.fieldOf("text").forGetter(processor -> processor.text)
//            ).apply(instance, SignProcessor::new)
//    );

    public static final MapCodec<SignProcessor> CODEC = MapCodec.unit((() -> SignProcessor.INSTANCE));
    public static final SignProcessor INSTANCE = new SignProcessor();

    private static List<String> PART_A;
    private static List<String> PART_B;

//    private final SignText text;

//    public SignProcessor(SignText text) {
//        this.text = text;
//    }

    public SignProcessor() {
        PART_A = new ArrayList<>();
        PART_B = new ArrayList<>();

        PART_A.add("There is a place which");
        PART_A.add("In this lull, we honor he who");
        PART_B.add("bears no soul");
        PART_B.add("reached the end");
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.SIGN_PROCESSOR;
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(
            WorldView world,
            BlockPos pos,
            BlockPos pivot,
            StructureTemplate.StructureBlockInfo originalBlockInfo,
            StructureTemplate.StructureBlockInfo currentBlockInfo,
            StructurePlacementData data
    ) {
        Block block = currentBlockInfo.state().getBlock();

        if (block instanceof AbstractSignBlock) {

            DynamicOps<NbtElement> dynamicOps = world.getRegistryManager().getOps(NbtOps.INSTANCE);
            NbtCompound nbt = currentBlockInfo.nbt();
            String lull = PART_A.get(Random.create().nextBetween(0, PART_A.size() - 1)) + " " + PART_B.get(Random.create().nextBetween(0, PART_B.size() - 1));
            SignText.CODEC.encodeStart(dynamicOps, SignUtilities.splitStringAcrossLines(lull)).resultOrPartial(NullsArmory.LOGGER::error).ifPresent(frontText -> nbt.put("front_text", frontText));
        }

        return currentBlockInfo;
    }
}
