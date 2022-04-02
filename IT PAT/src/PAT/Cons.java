package PAT;

/**
 *
 * @author Banzile Nhlebela
 */

public class Cons
{
    private String StudentID;
    private String Offence;
    private String Sanction;
    private String DateOfOffence;
    private String Told;
    private String Subject;

    public Cons(String SID, String Off, String San, String Sub, String T, String DOO)
    {
        StudentID = SID;
        Offence = Off;
        Sanction = San;
        Subject = Sub;
        Told = T;
        DateOfOffence = DOO;
    }
    
    public String getStudentID() 
    {
        return StudentID;
    }

    public void setStudentID(String StudentID) 
    {
        this.StudentID = StudentID;
    }

    public String getOffence() 
    {
        return Offence;
    }

    public void setOffence(String Offence) 
    {
        this.Offence = Offence;
    }

    public String getSanction() 
    {
        return Sanction;
    }

    public void setSanction(String Sanction) 
    {
        this.Sanction = Sanction;
    }

    public String getDateOfOffence() 
    {
        return DateOfOffence;
    }

    public void setDateOfOffence(String DateOfOffence) 
    {
        this.DateOfOffence = DateOfOffence;
    }

    public String getTold() 
    {
        return Told;
    }

    public void setTold(String Told) 
    {
        this.Told = Told;
    }

    public String getSubject() 
    {
        return Subject;
    }

    public void setSubject(String Subject) 
    {
        this.Subject = Subject;
    }

    @Override
    public String toString() {
        return "StudentID: " + getStudentID() 
                + "\nOffence: " + getOffence() 
                + "\nSanction: " + getSanction() 
                + "\nDateOfOffence: " + getDateOfOffence() 
                + "\nTold: " + getTold() 
                + "\nSubject: " + getSubject();
    }
}
