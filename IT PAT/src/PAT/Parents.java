package PAT;

/**
 *
 * @author eliznab74
 */
public class Parents
{
    private String StudentID;
    private String Firstname;
    private String Lastname;
    private String Gender;
    private String Relationship;
    private String Cellnumber;
    
    public Parents(String SID, String fn, String ln, String gen, String rel, String cn)
    {
        StudentID = SID;
        Firstname = fn;
        Lastname = ln;
        Gender = gen;
        Relationship = rel;
        Cellnumber = cn;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getRelationship() {
        return Relationship;
    }

    public void setRelationship(String Relationship) {
        this.Relationship = Relationship;
    }

    public String getCellnumber() {
        return Cellnumber;
    }

    public void setCellnumber(String Cellnumber) {
        this.Cellnumber = Cellnumber;
    }

    @Override
    public String toString()
    {
        return "Parents{" + "StudentID=" + StudentID + ", Firstname=" + Firstname + ", Lastname=" + Lastname + ", Gender=" + Gender + ", Relationship=" + Relationship + ", Cellnumber=" + Cellnumber + '}';
    }
}
