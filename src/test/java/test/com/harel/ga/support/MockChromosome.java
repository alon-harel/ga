package test.com.harel.ga.support;

import com.harel.ga.Chromosome;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class MockChromosome implements Chromosome<MockGene> {
    private final List<MockGene> genes;
    public MockChromosome(List<MockGene> mockGenes) {
        this.genes = mockGenes;
    }

    @Override
    public double getFitnessScore() {
        return 0;
    }

    @Override
    public void setFitnessScore(double fitnessScore) {

    }
}
