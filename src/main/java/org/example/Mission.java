package org.example;

import java.util.List;

public class Mission {
    private String missionId;
    private String outcome;
    private String date;
    private String location;
    private int damageCost;
    private Curse curse;
    private List<Sorcerer> sorcerers;
    private List<Technique> techniques;
    private String comment;

    public Mission() {}

    public String getMissionId() { return missionId; }
    public String getOutcome() { return outcome; }
    public Curse getCurse() { return curse; }
    public List<Sorcerer> getSorcerers() { return sorcerers; }
    public List<Technique> getTechniques() { return techniques; }
    public String getComment() { return comment; }
    public int getDamageCost() { return damageCost;}
    public String getLocation() { return location;}
    public String getDate() { return date; }

    public void setMissionId(String missionId) { this.missionId = missionId; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public void setCurse(String name, String threatLevel) { this.curse = new Curse(name, threatLevel); }

    public void setSorcerers(List<Sorcerer> sorcerers) { this.sorcerers = sorcerers; }
    public void setTechniques(List<Technique> techniques) { this.techniques = techniques; }
    public void setComment(String comment) { this.comment = comment; }
    public void setDamageCost(int damageCost) { this.damageCost = damageCost; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(String date) { this.date = date;}
}
