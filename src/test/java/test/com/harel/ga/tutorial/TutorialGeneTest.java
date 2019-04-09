package test.com.harel.ga.tutorial;

import com.harel.ga.tuturial.TutorialGene;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.harel.ga.tuturial.TutorialGene.*;
import static com.harel.ga.tuturial.TutorialGene.Type.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TutorialGeneTest {
    @Test
    public void testGenes() {
        verify(ZERO, Arrays.asList(0, 0, 0, 0), 0, NUMBER);
        verify(ONE, Arrays.asList(0, 0, 0, 1), 1, NUMBER);
        verify(TWO, Arrays.asList(0, 0, 1, 0), 2, NUMBER);
        verify(THREE, Arrays.asList(0, 0, 1, 1), 3, NUMBER);
        verify(FOUR, Arrays.asList(0, 1, 0, 0), 4, NUMBER);
        verify(FIVE, Arrays.asList(0, 1, 0, 1), 5, NUMBER);
        verify(SIX, Arrays.asList(0, 1, 1, 0), 6, NUMBER);
        verify(SEVEN, Arrays.asList(0, 1, 1, 1), 7, NUMBER);
        verify(EIGHT, Arrays.asList(1, 0, 0, 0), 8, NUMBER);
        verify(NINE, Arrays.asList(1, 0, 0, 1), 9, NUMBER);
        verify(PLUS, Arrays.asList(1, 0, 1, 0), 10, OPERATOR);
        verify(MINUS, Arrays.asList(1, 0, 1, 1), 11, OPERATOR);
        verify(MULTIPLE, Arrays.asList(1, 1, 0, 0), 12, OPERATOR);
        verify(DIV, Arrays.asList(1, 1, 0, 1), 13, OPERATOR);
        verify(NA1, Arrays.asList(1, 1, 1, 0), 14, NA);
        verify(NA2, Arrays.asList(1, 1, 1, 1), 15, NA);
    }

    private void verify(TutorialGene gene, List<Integer> bits, int index, Type type) {
        assertThat(gene.getBits(), is(bits));
        assertThat(TutorialGene.from(bits), is(gene));
        assertThat(TutorialGene.from(index), is(gene));
        assertThat(TutorialGene.from(index).getType(), is(type));
    }

    @Test(expected = UnknownGeneTye.class)
    public void failOnUnknownBits() {
        TutorialGene.from(Collections.singletonList(3));
    }

    @Test(expected = UnknownGeneTye.class)
    public void failOnUnknownIndex() {
        TutorialGene.from(19);
    }

    @Test
    public void mutateGene() {
        assertThat(TWO.duplicateWithMutationAt(0), is(PLUS));
    }
}
