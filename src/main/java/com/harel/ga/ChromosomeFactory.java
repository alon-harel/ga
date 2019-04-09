package com.harel.ga;

import java.util.List;

public interface ChromosomeFactory<C extends Chromosome, G extends Gene> {
    C create();
    C create(List<G> genes);
}
