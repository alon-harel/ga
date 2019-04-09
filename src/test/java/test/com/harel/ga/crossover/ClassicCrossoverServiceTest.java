package test.com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import com.harel.ga.crossover.ClassicCrossover;
import com.harel.ga.crossover.CrossoverService;
import com.harel.ga.crossover.CrossoverServiceImpl;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Test;
import test.com.harel.ga.support.MockChromosome;
import test.com.harel.ga.support.MockChromosomeFactory;
import test.com.harel.ga.support.MockGene;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClassicCrossoverServiceTest {
    private static final double CROSS_OVER_THRESHOLD = 0.7;
    private static final double ABOVE_CROSS_OVER_THRESHOLD = 0.8;
    private static final double BELLOW_CROSS_OVER_THRESHOLD = 0.6;

    private final MockChromosome onChromosome = new MockChromosome(Arrays.asList(MockGene.on(), MockGene.on(), MockGene.on(), MockGene.on()));
    private final MockChromosome offChromosome = new MockChromosome(Arrays.asList(MockGene.off(), MockGene.off(), MockGene.off(), MockGene.off()));
    private final int mockChromosomeLength = onChromosome.getGenes().size();
    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final MockChromosomeFactory mockChromosomeFactory = new MockChromosomeFactory();
    private final CrossoverServiceImpl crossoverService = new ClassicCrossover(mockChromosomeFactory, randomNumberProvider, CROSS_OVER_THRESHOLD);

    @Test
    public void shouldNotPerformCrossOver() {
        doReturn(ABOVE_CROSS_OVER_THRESHOLD).when(randomNumberProvider).draw();
        Chromosome firstChromosome = mock(Chromosome.class);
        Chromosome secondChromosome = mock(Chromosome.class);
        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstChromosome, secondChromosome);

        assertThat(offsprings.getFirstOffspring(), is(firstChromosome));
        assertThat(offsprings.getSecondOffspring(), is(secondChromosome));
    }

    @Test
    public void createTwoOffSprings() {
        final int positionToCrossover = 2;
        when(randomNumberProvider.random(mockChromosomeLength + 1)).thenReturn(positionToCrossover);
        when(randomNumberProvider.draw()).thenReturn(BELLOW_CROSS_OVER_THRESHOLD);

        final CrossoverService.Offsprings offsprings = crossoverService.perform(onChromosome, offChromosome);
        assertThat(offsprings.getFirstOffspring(), is(new MockChromosome(Arrays.asList(MockGene.on(), MockGene.on(), MockGene.off(), MockGene.off()))));
        assertThat(offsprings.getSecondOffspring(), is(new MockChromosome(Arrays.asList(MockGene.off(), MockGene.off(), MockGene.on(), MockGene.on()))));
    }

    @Test
    public void createTwoOffSpringsVerification() {
        final int positionToCrossover = 3;
        when(randomNumberProvider.random(mockChromosomeLength + 1)).thenReturn(positionToCrossover);
        when(randomNumberProvider.draw()).thenReturn(BELLOW_CROSS_OVER_THRESHOLD);

        final CrossoverService.Offsprings offsprings = crossoverService.perform(onChromosome, offChromosome);
        assertThat(offsprings.getFirstOffspring(), is(new MockChromosome(Arrays.asList(MockGene.on(), MockGene.on(), MockGene.on(), MockGene.off()))));
        assertThat(offsprings.getSecondOffspring(), is(new MockChromosome(Arrays.asList(MockGene.off(), MockGene.off(), MockGene.off(), MockGene.on()))));
    }

    @Test
    public void createTwoOffSpringsWhenPositionIsFirstGene() {
        final int positionToCrossover = 0;
        when(randomNumberProvider.random(mockChromosomeLength + 1)).thenReturn(positionToCrossover);
        when(randomNumberProvider.draw()).thenReturn(BELLOW_CROSS_OVER_THRESHOLD);

        final CrossoverService.Offsprings offsprings = crossoverService.perform(onChromosome, offChromosome);
        assertThat(offsprings.getFirstOffspring(), is(new MockChromosome(Arrays.asList(MockGene.off(), MockGene.off(), MockGene.off(), MockGene.off()))));
        assertThat(offsprings.getSecondOffspring(), is(new MockChromosome(Arrays.asList(MockGene.on(), MockGene.on(), MockGene.on(), MockGene.on()))));
    }

    @Test
    public void createTwoOffSpringsWhenPositionIsLastGene() {
        final int positionToCrossover = mockChromosomeLength;
        when(randomNumberProvider.random(mockChromosomeLength + 1)).thenReturn(positionToCrossover);
        when(randomNumberProvider.draw()).thenReturn(BELLOW_CROSS_OVER_THRESHOLD);

        final CrossoverService.Offsprings offsprings = crossoverService.perform(onChromosome, offChromosome);
        assertThat(offsprings.getFirstOffspring(), is(new MockChromosome(Arrays.asList(MockGene.on(), MockGene.on(), MockGene.on(), MockGene.on()))));
        assertThat(offsprings.getSecondOffspring(), is(new MockChromosome(Arrays.asList(MockGene.off(), MockGene.off(), MockGene.off(), MockGene.off()))));
    }
}
