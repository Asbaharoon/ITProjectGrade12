package PAT;

/**
 *
 * @author eliznab74
 */

public class Offence
{
    private String offenceID;
    private String description;
    private int level;
    
    public Offence(String OID, String d, int l)
    {
        offenceID = OID;
        description = d;
        level = l;
    }

    public String getOffenceID() {
        return offenceID;
    }

    public void setOffenceID(String offenceID) {
        this.offenceID = offenceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        return "Offence{" + "offenceID=" + offenceID + ", description=" + description + ", level=" + level + '}';
    }

}
