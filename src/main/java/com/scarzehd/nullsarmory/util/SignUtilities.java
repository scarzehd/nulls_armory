package com.scarzehd.nullsarmory.util;

import net.minecraft.block.entity.SignText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SignUtilities {
    public static SignText splitStringAcrossLines(String string) {

        List<Text> texts = new ArrayList<>();

        while (string.length() > 15) {

            List<String> words = new ArrayList<>(Arrays.stream(string.split(" ")).toList());
            words.removeAll(Collections.singletonList(""));

            StringBuilder part = new StringBuilder();

            int partLength = 0;

            for (String word : words) {
                int wordLength = word.length();
                if (partLength + wordLength + 1 > 15) {
                    texts.add(Text.of(part.toString()));
                    break;
                }
                partLength += wordLength + 1;

                part.append(" ").append(word);
            }

            string = string.substring(partLength);
        }

        texts.add(Text.of(string)); // Add the leftover part

        SignText signText = new SignText();

        for (int i = 0; i < texts.size(); i++) {
            if (i > 3) break;
            signText = signText.withMessage(i, texts.get(i));
        }

        return signText;
    }
}
