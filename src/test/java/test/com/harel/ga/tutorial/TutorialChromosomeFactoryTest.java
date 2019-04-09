package test.com.harel.ga.tutorial;

import com.harel.ga.tuturial.TutorialChromosome;
import com.harel.ga.tuturial.TutorialChromosomeFactory;
import com.harel.ga.tuturial.TutorialGene;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.harel.ga.tuturial.TutorialGene.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TutorialChromosomeFactoryTest {
    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final TutorialChromosomeFactory factory = new TutorialChromosomeFactory(randomNumberProvider);

    @Test
    public void createChromosome() {
        when(randomNumberProvider.random(16)).thenReturn(1, 10, 1, 13, 2, 12, 3, 10, 5);
        final List<TutorialGene> genes = Arrays.asList(ONE, PLUS, ONE, DIV, TWO, MULTIPLE, THREE, PLUS, FIVE);
        TutorialChromosome chromosome = factory.create();

        assertThat(chromosome.getGenes(), is(genes));
    }
}
