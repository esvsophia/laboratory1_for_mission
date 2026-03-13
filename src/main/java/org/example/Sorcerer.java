package org.example;

public class Sorcerer {
    private String name;
    private String rank;

    public Sorcerer(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }
    public String getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return name + " [" + rank + "]";
    }
}
