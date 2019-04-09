package test.com.harel.ga.tsp;

import com.harel.ga.tsp.City;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CityTest {

    @Test
    public void calcDistanceBetweenTwoCities() {
        City firstCity = City.builder().xCoordinate(10).yCoordinate(10).build();
        City secondCity = City.builder().xCoordinate(6).yCoordinate(7).build();

        assertThat(secondCity.distanceFrom(firstCity), is(5.0));
    }
}
