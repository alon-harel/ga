package test.com.harel.ga.selection;

import com.harel.ga.Chromosome;
import com.harel.ga.selection.RouletteWheelSelection;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RouletteWheelSelectionTest {

    private final Chromosome chromosome1 = createChromosome(20);
    private final Chromosome chromosome2 = createChromosome(50);
    private final Chromosome chromosome3 = createChromosome(30);
    private final List<Chromosome> chromosomes = Arrays.asList(chromosome1, chromosome2, chromosome3);

    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(randomNumberProvider);

    @Before
    public void setup() {
        rouletteWheelSelection.init(chromosomes);
    }

    @Test
    public void shouldInitWithChromosomes() {
        assertThat(rouletteWheelSelection.getChromosomes(), is(chromosomes));
    }

    @Test
    public void pickFirstChromosome() {
        doReturn(0.1).when(randomNumberProvider).draw();
        assertThat(rouletteWheelSelection.pick(), is(chromosome1));
    }

    @Test
    public void pickSecondChromosome() {
        doReturn(0.4).when(randomNumberProvider).draw();
        assertThat(rouletteWheelSelection.pick(), is(chromosome2));
    }

    @Test
    public void pickThirdChromosome() {
        doReturn(0.9).when(randomNumberProvider).draw();
        assertThat(rouletteWheelSelection.pick(), is(chromosome3));
    }

    @Test
    public void pickThirdChromosomeWhenDrawNumberIsMaximum() {
        doReturn(1.0).when(randomNumberProvider).draw();
        assertThat(rouletteWheelSelection.pick(), is(chromosome3));
    }

    private Chromosome createChromosome(double fitnessScore) {
        final Chromosome chromosome = mock(Chromosome.class);
        doReturn(fitnessScore).when(chromosome).getFitnessScore();
        return chromosome;
    }
}
