package test.com.harel.ga.support;

import com.harel.ga.ChromosomeFactory;

import java.util.List;

public class MockChromosomeFactory implements ChromosomeFactory<MockChromosome, MockGene> {
    @Override
    public MockChromosome create() {
        return null;
    }

    @Override
    public MockChromosome create(List<MockGene> genes) {
        return new MockChromosome(genes);
    }
}
