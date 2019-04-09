package test.com.harel.ga.tsp;

import com.harel.ga.tsp.City;
import com.harel.ga.tsp.TspProblemDefinition;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Test;

import static com.harel.ga.tsp.TspProblemDefinition.MAX_COORDINATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class TspProblemDefinitionTest {

    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final TspProblemDefinition problemDefinition = new TspProblemDefinition(randomNumberProvider);

    @Test
    public void shouldCreateCitiesToTravelIn() {
        doReturn(50).when(randomNumberProvider).random(MAX_COORDINATE);
        final int citiesNumber = 1;
        problemDefinition.init(citiesNumber);
        assertThat(problemDefinition.getCitiesToTravel().size(), is(citiesNumber));
    }

    @Test
    public void avoidCreatingCitiesWithSameCoordinates() {
        City firstCity = City.builder().xCoordinate(50).yCoordinate(50).build();
        City secondCity = City.builder().xCoordinate(80).yCoordinate(80).build();

        when(randomNumberProvider.random(MAX_COORDINATE)).thenReturn(firstCity.getXCoordinate(), firstCity.getYCoordinate(),
                firstCity.getXCoordinate(), firstCity.getYCoordinate(),
                secondCity.getXCoordinate(), secondCity.getYCoordinate());

        final int citiesNumber = 2;
        problemDefinition.init(citiesNumber);
        assertThat(problemDefinition.getCitiesToTravel().size(), is(citiesNumber));
        assertThat(problemDefinition.getCitiesToTravel().contains(firstCity), is(true));
        assertThat(problemDefinition.getCitiesToTravel().contains(secondCity), is(true));
    }

}
