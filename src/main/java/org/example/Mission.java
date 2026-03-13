package org.example;

import java.util.List;

public class Mission {

    private String missionId;
    private String outcome;
    private String curse;
    private List<String> sorcerers;
    private List<Technique> techniques;
    private String comment;

    public Mission() {}

    public String getMissionId() { return missionId; }
    public String getOutcome() { return outcome; }
    public String getCurse() { return curse; }
    public List<String> getSorcerers() { return sorcerers; }
    public List<Technique> getTechniques() { return techniques; }
    public String getComment() { return comment; }

    public void setMissionId(String missionId) { this.missionId = missionId; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public void setCurse(String curse) { this.curse = curse; }
    public void setSorcerers(List<String> sorcerers) { this.sorcerers = sorcerers; }
    public void setTechniques(List<Technique> techniques) { this.techniques = techniques; }
    public void setComment(String comment) { this.comment = comment; }
}
