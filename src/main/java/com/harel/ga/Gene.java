package com.harel.ga;

import java.util.List;

public interface Gene {
    List<Integer> getBits();

    Gene duplicateWithMutationAt(int bitIndex);
}
