package com.harel.ga.tuturial;

import com.harel.ga.Gene;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.harel.ga.tuturial.TutorialGene.Type.*;

@Getter
public enum TutorialGene implements Gene {
    ZERO(Arrays.asList(0, 0, 0, 0), 0, NUMBER),
    ONE(Arrays.asList(0, 0, 0, 1), 1, NUMBER),
    TWO(Arrays.asList(0, 0, 1, 0), 2, NUMBER),
    THREE(Arrays.asList(0, 0, 1, 1), 3, NUMBER),
    FOUR(Arrays.asList(0, 1, 0, 0), 4, NUMBER),
    FIVE(Arrays.asList(0, 1, 0, 1), 5, NUMBER),
    SIX(Arrays.asList(0, 1, 1, 0), 6, NUMBER),
    SEVEN(Arrays.asList(0, 1, 1, 1), 7, NUMBER),
    EIGHT(Arrays.asList(1, 0, 0, 0), 8, NUMBER),
    NINE(Arrays.asList(1, 0, 0, 1), 9, NUMBER),
    PLUS(Arrays.asList(1, 0, 1, 0), 10, OPERATOR),
    MINUS(Arrays.asList(1, 0, 1, 1), 11, OPERATOR),
    MULTIPLE(Arrays.asList(1, 1, 0, 0), 12, OPERATOR),
    DIV(Arrays.asList(1, 1, 0, 1), 13, OPERATOR),
    NA1(Arrays.asList(1, 1, 1, 0), 14, NA),
    NA2(Arrays.asList(1, 1, 1, 1), 15, NA);

    private final List<Integer> bits;
    private final int index;
    private final Type type;

    TutorialGene(List<Integer> bits, int index, Type type) {
        this.bits = bits;
        this.index = index;
        this.type = type;
    }

    public static TutorialGene from(List<Integer> bits) {
        TutorialGene retVal = null;
        for (TutorialGene tutorialGene : TutorialGene.values()) {
            if (tutorialGene.getBits().equals(bits)) {
                retVal = tutorialGene;
            }
        }
        if (retVal == null) {
            throw new UnknownGeneTye(bits);
        }
        return retVal;
    }

    public static TutorialGene from(int index) {
        TutorialGene retVal = null;
        for (TutorialGene tutorialGene : TutorialGene.values()) {
            if (tutorialGene.getIndex() == index) {
                retVal = tutorialGene;
            }
        }
        if (retVal == null) {
            throw new UnknownGeneTye(index);
        }
        return retVal;
    }

    @Override
    public Gene duplicateWithMutationAt(int bitIndex) {
        List<Integer> bits = new ArrayList<>(getBits());
        Integer revertBit = (bits.get(bitIndex) == 1) ? 0 : 1;
        bits.set(bitIndex, revertBit);
        return TutorialGene.from(bits);
    }

    public enum Type {
        NUMBER,
        OPERATOR,
        NA;
    }

    public static class UnknownGeneTye extends RuntimeException {
        UnknownGeneTye(List<Integer> bits) {
            super(String.format("Cannot create gene from bits: %s", bits.toString()));
        }

        UnknownGeneTye(int index) {
            super(String.format("Cannot create gene from index: %s", index));
        }
    }
}