package test.com.harel.ga.tsp;

import com.harel.ga.ChromosomeFactory;
import com.harel.ga.tsp.City;
import com.harel.ga.tsp.Route;
import com.harel.ga.tsp.TspChromosomeFactory;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TspChromosomeFactoryTest {
    private final City firstCity = City.builder().xCoordinate(1).yCoordinate(1).build();
    private final City secondCity = City.builder().xCoordinate(2).yCoordinate(2).build();
    private final City thirdCity = City.builder().xCoordinate(3).yCoordinate(3).build();
    private final List<City> cities = Arrays.asList(firstCity, secondCity, thirdCity);

    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final TspChromosomeFactory chromosomeFactory = new TspChromosomeFactory(randomNumberProvider, cities);

    @Test
    public void createRoute() {
        doReturn(1).when(randomNumberProvider).random(cities.size());
        doReturn(0).when(randomNumberProvider).random(cities.size() - 1);
        Route expectedRoute = Route.from(Arrays.asList(secondCity, firstCity, thirdCity));
        assertThat(chromosomeFactory.create(), is(expectedRoute));
    }

    @Test
    public void createFromCities() {
        Route expectedRoute = Route.from(Arrays.asList(secondCity, firstCity, thirdCity));
        assertThat(chromosomeFactory.create(Arrays.asList(secondCity, firstCity, thirdCity)), is(expectedRoute));
    }

    @Test(expected = TspChromosomeFactory.DuplicateCitiesInRouteException.class)
    public void failIfDuplicateCitiesInRoute() {
        chromosomeFactory.create(Arrays.asList(firstCity, secondCity, secondCity));
    }

    @Test(expected = TspChromosomeFactory.MissingCitiesException.class)
    public void failIfThereMissingCities() {
        chromosomeFactory.create(Arrays.asList(firstCity, secondCity));
    }
}
