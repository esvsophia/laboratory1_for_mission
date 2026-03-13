package org.example;

public class Technique {
    private String name;
    private String type;
    private String owner;
    private int damage;

    public Technique() {}

    public Technique(String name, String type, String owner, int damage) {
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.damage = damage;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getOwner() { return owner; }
    public int getDamage() { return damage; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setDamage(int damage) { this.damage = damage; }
}
