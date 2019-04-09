package test.com.harel.ga.tutorial;

import com.harel.ga.tuturial.TutorialChromosome;
import com.harel.ga.tuturial.TutorialFitnessScoreCalculator;
import com.harel.ga.tuturial.utils.TutorialProblemDefinition;
import org.junit.Test;

import java.util.Arrays;

import static com.harel.ga.tuturial.TutorialGene.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TutorialFitnessScoreCalculatorTest {
    private final TutorialProblemDefinition solution = new TutorialProblemDefinition(10);
    private final TutorialFitnessScoreCalculator calculator = new TutorialFitnessScoreCalculator(solution);

    @Test
    public void setFitnessForChromosome() {
        TutorialChromosome chromosome1 = TutorialChromosome.from(Arrays.asList(ONE, PLUS, ONE, ONE, ONE, ONE, ONE, ONE, ONE));
        TutorialChromosome chromosome2 = TutorialChromosome.from(Arrays.asList(NINE, PLUS, NINE, NA1, NA1, NA1, NA1, NA1, NA1));
        TutorialChromosome chromosome3 = TutorialChromosome.from(Arrays.asList(FIVE, MULTIPLE, TWO, NA2, NA2, NA2, NA2, NA2, NA2));

        calculator.calc(Arrays.asList(chromosome1, chromosome2, chromosome3));
        assertThat(chromosome1.getFitnessScore(), is(1.25));
        assertThat(chromosome2.getFitnessScore(), is(1.25));
        assertThat(chromosome3.getFitnessScore(), is(100000.0));
    }
}
