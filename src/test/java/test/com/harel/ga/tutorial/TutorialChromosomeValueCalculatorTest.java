package test.com.harel.ga.tutorial;

import com.harel.ga.tuturial.TutorialChromosomeValueCalculator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.harel.ga.tuturial.TutorialGene.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TutorialChromosomeValueCalculatorTest {

    private final TutorialChromosomeValueCalculator calculator = new TutorialChromosomeValueCalculator();

    @Test
    public void shouldCalcOnSingleGeneWithNumber() {
        assertThat(calculator.calc(Collections.singletonList(ONE)), is(1.0));
    }

    @Test
    public void shouldIgnoreNonNumeric() {
        assertThat(calculator.calc(Collections.singletonList(PLUS)), is(0.0));
        assertThat(calculator.calc(Collections.singletonList(NA1)), is(0.0));
    }

    @Test
    public void shouldCalcOperators() {
        assertThat(calculator.calc(Arrays.asList(THREE, PLUS, THREE)), is(6.0));
        assertThat(calculator.calc(Arrays.asList(THREE, MINUS, THREE)), is(0.0));
        assertThat(calculator.calc(Arrays.asList(THREE, MULTIPLE, THREE)), is(9.0));
        assertThat(calculator.calc(Arrays.asList(THREE, DIV, THREE)), is(1.0));
    }

    @Test
    public void ignoreSequenceOfNumbers() {
        assertThat(calculator.calc(Arrays.asList(THREE, FOUR, FIVE, PLUS, THREE)), is(6.0));
    }

    @Test
    public void ignoreSequenceOfOperators() {
        assertThat(calculator.calc(Arrays.asList(THREE, PLUS, MULTIPLE, DIV, THREE)), is(6.0));
    }

    @Test
    public void ignoreOperatorIfThereIsNoNumberAfterIt() {
        assertThat(calculator.calc(Arrays.asList(THREE, PLUS, MULTIPLE, DIV, NA1)), is(3.0));
    }

    @Test
    public void ignoreWhenOperatorWasNotFound() {
        assertThat(calculator.calc(Arrays.asList(THREE, FOUR, FIVE, NA1)), is(3.0));
    }

    @Test
    public void calcSequenceOfOperators() {
        assertThat(calculator.calc(Arrays.asList(THREE, PLUS, FIVE, MULTIPLE, FOUR)), is(32.0));
    }

    @Test
    public void sanityCalculation() {
        assertThat(calculator.calc(Arrays.asList(NA1, THREE, NINE, PLUS, DIV, FIVE, NA2, SIX, MULTIPLE, FOUR, MULTIPLE, NA1)), is(32.0));
    }

    @Test
    public void calcResultWhenOperatorIsLast() {
        assertThat(calculator.calc(Arrays.asList(MULTIPLE, MULTIPLE, NINE, SIX, TWO, SEVEN, FOUR, SIX, MINUS)), is(9.0));
    }

    @Test
    public void guardFromDividingByZero() {
        assertThat(calculator.calc(Arrays.asList(MINUS, ONE, MULTIPLE, NINE, NA1, NINE, DIV, ZERO, NA2)), is(9.0));
    }

    @Test
    public void calcDoubleValue() {
        assertThat(calculator.calc(Arrays.asList(ONE, DIV, TWO)), is(0.5));
    }
}
