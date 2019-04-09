package test.com.harel.ga.tsp;

import com.harel.ga.tsp.City;
import com.harel.ga.tsp.Route;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RouteTest {

    @Test
    public void shouldAddCityToRoute() {
        Route route = new Route();
        assertThat(route.getGenes().size(), is(0));
        route.addCity(City.builder().build());
        assertThat(route.getGenes().size(), is(1));
    }


}
