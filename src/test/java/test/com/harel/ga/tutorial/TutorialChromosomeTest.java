package test.com.harel.ga.tutorial;

import com.harel.ga.tuturial.TutorialChromosome;
import com.harel.ga.tuturial.TutorialGene;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.harel.ga.tuturial.TutorialGene.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TutorialChromosomeTest {

    @Test(expected = TutorialChromosome.ChromosomeCreationFailed.class)
    public void failToCreateIfTooManyGenes() {
        final List<TutorialGene> moreThenAllowedGenes = Arrays.asList(ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE);
        TutorialChromosome.from(moreThenAllowedGenes);
    }

    @Test(expected = TutorialChromosome.ChromosomeCreationFailed.class)
    public void failToCreateIfFewGenes() {
        TutorialChromosome.from(Collections.emptyList());
    }

    @Test
    public void createChromosome() {
        final List<TutorialGene> genes = Arrays.asList(ONE, PLUS, ONE, DIV, TWO, MULTIPLE, THREE, PLUS, FIVE);

        TutorialChromosome chromosome = TutorialChromosome.from(genes);
        assertThat(chromosome.getGenes(), is(genes));
    }

    @Test
    public void mutateGene() {
        final List<TutorialGene> genes = Arrays.asList(ONE, PLUS, ONE, DIV, TWO, MULTIPLE, THREE, PLUS, FIVE);
        TutorialChromosome chromosome = TutorialChromosome.from(genes);

    }
}
