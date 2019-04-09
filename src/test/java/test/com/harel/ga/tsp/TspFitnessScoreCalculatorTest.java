package test.com.harel.ga.tsp;

import com.harel.ga.tsp.City;
import com.harel.ga.tsp.Route;
import com.harel.ga.tsp.TspFitnessScoreCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TspFitnessScoreCalculatorTest {
    private static final double DISTANCE_BETWEEN_CITIES = 10;

    private final City firstCity = mock(City.class);
    private final City secondCity = mock(City.class);
    private final City thirdCity = mock(City.class);
    private final TspFitnessScoreCalculator fitnessScoreCalculator = new TspFitnessScoreCalculator();

    @Before
    public void setup() {
        doReturn(DISTANCE_BETWEEN_CITIES).when(secondCity).distanceFrom(firstCity);
        doReturn(DISTANCE_BETWEEN_CITIES).when(thirdCity).distanceFrom(secondCity);
    }

    @Test
    public void calculateFitnessScoreForTwoCities() {
        Route route = Route.from(Arrays.asList(firstCity, secondCity));
        fitnessScoreCalculator.calc(Collections.singletonList(route));

        assertThat(route.getFitnessScore(), is(1 / DISTANCE_BETWEEN_CITIES));
    }

    @Test
    public void calculateFitnessScoreForArrayOfCities() {
        Route route = Route.from(Arrays.asList(firstCity, secondCity, thirdCity));
        fitnessScoreCalculator.calc(Collections.singletonList(route));

        assertThat(route.getFitnessScore(), is(1 / (DISTANCE_BETWEEN_CITIES + DISTANCE_BETWEEN_CITIES)));
    }

}
