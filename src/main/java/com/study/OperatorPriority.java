package com.study;

public enum OperatorPriority {
    LEVEL_ONE(1),
    LEVEL_TWO(2);

    private final int level;

    OperatorPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
