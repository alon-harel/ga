package test.com.harel.ga;

import com.harel.ga.Chromosome;
import com.harel.ga.ChromosomeFactory;
import com.harel.ga.Population;
import lombok.ToString;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class PopulationTest {
    private final int POPULATION_SIZE = 10;
    private final ChromosomeFactory chromosomeFactory = mock(ChromosomeFactory.class);
    private final Population population = Population.init(POPULATION_SIZE, chromosomeFactory);

    @Test
    public void initPopulation() {
        assertThat(population.getChromosomes().size(), is(POPULATION_SIZE));
    }

    @Test
    public void createChromosomesAsManyAsNeeded() {
        verify(chromosomeFactory, times(POPULATION_SIZE)).create();
    }

    @Test
    public void createEmptyPopulation() {
        assertThat(Population.emptyPopulation().getChromosomes().isEmpty(), is(true));
    }

    @Test
    public void shouldAddChromosome() {
        Chromosome chromosome = mock(Chromosome.class);
        Population population = Population.emptyPopulation();
        population.addChromosome(chromosome);

        assertThat(population.getChromosomes().size(), is(1));
        assertThat(population.getChromosomes().get(0), is(chromosome));
    }
}
