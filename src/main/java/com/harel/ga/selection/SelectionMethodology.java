package com.harel.ga.selection;

import com.harel.ga.Chromosome;

import java.util.List;

public interface SelectionMethodology {
    void init(List<Chromosome> chromosomes);

    Chromosome pick();
}
