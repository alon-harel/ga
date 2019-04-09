package com.harel.ga.tuturial.utils;

import com.harel.ga.ProblemDefinition;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TutorialProblemDefinition implements ProblemDefinition {
    private double target;
}
