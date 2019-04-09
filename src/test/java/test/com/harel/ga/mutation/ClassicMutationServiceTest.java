package test.com.harel.ga.mutation;

import com.harel.ga.Chromosome;
import com.harel.ga.mutation.ClassicMutationService;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Test;
import test.com.harel.ga.support.MockChromosome;
import test.com.harel.ga.support.MockGene;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ClassicMutationServiceTest {
    private static final double MUTATION_PROBABILITY = 0.001;
    private static final int PERFORM_MUTATION_VALUE = 0;
    private static final int SKIP_MUTATION_VALUE = 500;


    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final ClassicMutationService mutationService = new ClassicMutationService(randomNumberProvider, MUTATION_PROBABILITY);

    @Test
    public void shouldReturnSameChromosomeIfNoMutationWasPerformed() {
        Chromosome chromosome = mock(Chromosome.class);
        doReturn(SKIP_MUTATION_VALUE).when(randomNumberProvider).random(1000);
        assertThat(mutationService.mutate(chromosome), is(chromosome));
    }

    @Test
    public void shouldMutateChromosome() {
        MockChromosome chromosome = new MockChromosome(Arrays.asList(MockGene.off(), MockGene.off()));
        doReturn(PERFORM_MUTATION_VALUE).when(randomNumberProvider).random(1000);

        MockChromosome mutatedChromosome = new MockChromosome(Arrays.asList(MockGene.on(), MockGene.on()));
        assertThat(mutationService.mutate(chromosome), is(mutatedChromosome));
    }

}
