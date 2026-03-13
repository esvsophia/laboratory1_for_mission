package org.example;

public class Curse {
    private String name;
    private String threatLevel;

    public Curse(String name, String threatLevel) {
        this.name = name;
        this.threatLevel = threatLevel;
    }

    public String getName() {
        return name;
    }
    public String getThreatLevel() {
        return threatLevel;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    @Override
    public String toString() {
        return name + " (угроза: " + threatLevel + ")";
    }
}
