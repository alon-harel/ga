package test.com.harel.ga.selection;

import com.harel.ga.Chromosome;
import com.harel.ga.Population;
import com.harel.ga.selection.BestOutOfRandomNChromosomesSelection;
import com.harel.ga.utils.RandomNumberProvider;
import lombok.Builder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BestOutOfRandomNChromosomesSelectionTest {

    private final Chromosome firstChromosome = mock(Chromosome.class);
    private final Chromosome secondChromosome = mock(Chromosome.class);
    private final Chromosome thirdChromosome = mock(Chromosome.class);
    private final Chromosome fourthChromosome = mock(Chromosome.class);
    private final List<Chromosome> chromosomes =
            Arrays.asList(firstChromosome, secondChromosome, thirdChromosome, fourthChromosome);
    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);


    private final BestOutOfRandomNChromosomesSelection outOf3Selection =
            new BestOutOfRandomNChromosomesSelection(3, randomNumberProvider);

    @Before
    public void setup() {
        doReturn(100.0).when(firstChromosome).getFitnessScore();
        doReturn(90.0).when(firstChromosome).getFitnessScore();
        doReturn(80.0).when(firstChromosome).getFitnessScore();
        doReturn(70.0).when(firstChromosome).getFitnessScore();

        when(randomNumberProvider.random(chromosomes.size())).thenReturn(3, 2, 0);

        outOf3Selection.init(chromosomes);
    }


    @Test
    public void pickChromosome() {
        assertThat(outOf3Selection.pick(), is(firstChromosome));
    }
}
