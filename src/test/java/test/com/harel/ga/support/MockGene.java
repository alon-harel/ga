package test.com.harel.ga.support;

import com.harel.ga.Gene;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;


@Getter
@EqualsAndHashCode
@ToString
public class MockGene implements Gene {
    private final List<Integer> bits;

    private MockGene(int bit) {
        this.bits = Collections.singletonList(bit);
    }

    public static MockGene on() {
        return new MockGene(1);
    }

    public static MockGene off() {
        return new MockGene(0);
    }

    @Override
    public Gene duplicateWithMutationAt(int bitIndex) {
       return (bits.get(0) == 1) ? off() : on();
    }
}
