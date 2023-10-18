package br.feevale.domain;

public enum Priority {

    LOW(2), AVERAGE(5), HIGH(10);

    private final Integer points;

    Priority(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }
}
