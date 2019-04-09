package test.com.harel.ga.tsp;

import com.harel.ga.crossover.CrossoverService;
import com.harel.ga.tsp.City;
import com.harel.ga.tsp.Route;
import com.harel.ga.tsp.TspChromosomeFactory;
import com.harel.ga.tsp.TspCrossoverService;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class TspCrossoverServiceTest {
    private final City one = City.builder().xCoordinate(1).yCoordinate(1).build();
    private final City two = City.builder().xCoordinate(2).yCoordinate(2).build();
    private final City three = City.builder().xCoordinate(3).yCoordinate(3).build();
    private final City four = City.builder().xCoordinate(4).yCoordinate(4).build();
    private final City five = City.builder().xCoordinate(5).yCoordinate(5).build();
    private final City six = City.builder().xCoordinate(6).yCoordinate(6).build();

    private final TspChromosomeFactory tspChromosomeFactory = mock(TspChromosomeFactory.class);
    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final TspCrossoverService crossoverService = new TspCrossoverService(tspChromosomeFactory, randomNumberProvider, 1.0);

    @Before
    public void setup() {
        doAnswer(invocation -> {
            List<City> cityList = (List<City>) invocation.getArguments()[0];
            return Route.from(cityList);
        }).when(tspChromosomeFactory).create(anyListOf(City.class));
    }

    @Test
    public void createOffSprings() {
        int routeLength = 6;
        when(randomNumberProvider.random(routeLength)).thenReturn(2, 4);

        Route firstRoute = Route.from(Arrays.asList(five, one, four, two, three, six));
        Route secondRoute = Route.from(Arrays.asList(six, three, two, four, one, five));

        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstRoute, secondRoute);

        assertThat(offsprings.getFirstOffspring(), is(Route.from(Arrays.asList(six, one, four, two, three, five))));
        assertThat(offsprings.getSecondOffspring(), is(Route.from(Arrays.asList(five, three, two, four, one, six))));
    }

    @Test
    public void createOffSpringsWhenDrawingInReverseOrder() {
        int routeLength = 6;
        when(randomNumberProvider.random(routeLength)).thenReturn(4, 2);

        Route firstRoute = Route.from(Arrays.asList(five, one, four, two, three, six));
        Route secondRoute = Route.from(Arrays.asList(six, three, two, four, one, five));

        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstRoute, secondRoute);

        assertThat(offsprings.getFirstOffspring(), is(Route.from(Arrays.asList(six, one, four, two, three, five))));
        assertThat(offsprings.getSecondOffspring(), is(Route.from(Arrays.asList(five, three, two, four, one, six))));
    }

    @Test
    public void createOffspringWhenChoosingSingleCity() {
        int routeLength = 6;
        when(randomNumberProvider.random(routeLength)).thenReturn(4, 4);

        Route firstRoute = Route.from(Arrays.asList(five, one, four, two, three, six));
        Route secondRoute = Route.from(Arrays.asList(six, three, two, four, one, five));

        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstRoute, secondRoute);

        assertThat(offsprings.getFirstOffspring(), is(Route.from(Arrays.asList(six, two, four, one, three, five))));
        assertThat(offsprings.getSecondOffspring(), is(Route.from(Arrays.asList(five, four, two, three, one, six))));
    }

    @Test
    public void createOffspringWhenChoosingFirstCity() {
        int routeLength = 6;
        when(randomNumberProvider.random(routeLength)).thenReturn(0, 0);

        Route firstRoute = Route.from(Arrays.asList(five, one, four, two, three, six));
        Route secondRoute = Route.from(Arrays.asList(six, three, two, four, one, five));

        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstRoute, secondRoute);

        assertThat(offsprings.getFirstOffspring(), is(Route.from(Arrays.asList(five, six, three, two, four, one))));
        assertThat(offsprings.getSecondOffspring(), is(Route.from(Arrays.asList(six, five, one, four, two, three))));
    }

    @Test
    public void createOffspringWhenChoosingLastCity() {
        int routeLength = 6;
        when(randomNumberProvider.random(routeLength)).thenReturn(5, 5);

        Route firstRoute = Route.from(Arrays.asList(five, one, four, two, three, six));
        Route secondRoute = Route.from(Arrays.asList(six, three, two, four, one, five));

        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstRoute, secondRoute);

        assertThat(offsprings.getFirstOffspring(), is(Route.from(Arrays.asList(three, two, four, one, five, six))));
        assertThat(offsprings.getSecondOffspring(), is(Route.from(Arrays.asList(one, four, two, three, six, five))));
    }

    @Test
    public void createSameOffspringsAsParent() {
        int routeLength = 6;
        when(randomNumberProvider.random(routeLength)).thenReturn(0, 5);

        Route firstRoute = Route.from(Arrays.asList(five, one, four, two, three, six));
        Route secondRoute = Route.from(Arrays.asList(six, three, two, four, one, five));

        final CrossoverService.Offsprings offsprings = crossoverService.perform(firstRoute, secondRoute);

        assertThat(offsprings.getFirstOffspring(), is(firstRoute));
        assertThat(offsprings.getSecondOffspring(), is(secondRoute));
    }
}
