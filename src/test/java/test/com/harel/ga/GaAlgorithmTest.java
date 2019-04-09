package test.com.harel.ga;

import com.harel.ga.*;
import com.harel.ga.crossover.CrossoverServiceImpl;
import com.harel.ga.mutation.ClassicMutationService;
import com.harel.ga.selection.SelectionMethodology;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GaAlgorithmTest {

    private final SelectionMethodology selectionMethodology = mock(SelectionMethodology.class);
    private final ChromosomeFactory chromosomeFactory = mock(ChromosomeFactory.class);
    private final FitnessScoreCalculator fitnessScoreCalculator = mock(FitnessScoreCalculator.class);
    private final CrossoverServiceImpl crossoverService = mock(CrossoverServiceImpl.class);
    private final ClassicMutationService mutationService = mock(ClassicMutationService.class);

    private final GaAlgorithm gaAlgorithm = new GaAlgorithm(
            chromosomeFactory,
            fitnessScoreCalculator,
            selectionMethodology,
            crossoverService,
            mutationService, 100, 50);

    @Test
    public void shouldCalculateFitnessScoreForPopulation() {
    //    gaAlgorithm.run();
        //verify(fitnessScoreCalculator).calc(population.getChromosomes(), problemDefinition);
    }
}
