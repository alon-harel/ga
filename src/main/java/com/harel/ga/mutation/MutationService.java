package com.harel.ga.mutation;

import com.harel.ga.Chromosome;

public interface MutationService<C extends Chromosome> {

    Chromosome mutate(C chromosome);
}