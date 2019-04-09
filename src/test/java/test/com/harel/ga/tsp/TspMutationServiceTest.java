package test.com.harel.ga.tsp;

import com.harel.ga.tsp.City;
import com.harel.ga.tsp.Route;
import com.harel.ga.tsp.TspMutationService;
import com.harel.ga.utils.RandomNumberProvider;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TspMutationServiceTest {

    private final City one = City.builder().xCoordinate(1).yCoordinate(1).build();
    private final City two = City.builder().xCoordinate(2).yCoordinate(2).build();
    private final City three = City.builder().xCoordinate(3).yCoordinate(3).build();
    private final City four = City.builder().xCoordinate(4).yCoordinate(4).build();
    private final RandomNumberProvider randomNumberProvider = mock(RandomNumberProvider.class);
    private final TspMutationService mutationService = new TspMutationService(randomNumberProvider);

    @Test
    public void mutate() {
        Route route = Route.from(Arrays.asList(one, two, three, four));
        when(randomNumberProvider.random(route.getGenes().size())).thenReturn(1, 2);
        assertThat(mutationService.mutate(route), is(Route.from(Arrays.asList(one, three, two, four))));
    }
}
