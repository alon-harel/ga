package com.harel.ga;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class ChromosomeImpl<T extends Gene> implements Chromosome {
   private double fitnessScore;
}
