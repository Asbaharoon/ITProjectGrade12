package PAT;

/**
 *
 * @author Banzile
 */

public class User
{
    private String studentID;
    private String firstName;
    private String lastName;
    private String gender;
    private int grade;
    private String dateOfBirth;

    public User(String SID, String FN, String LN, String GEN, int GRD, String DOB)
    {
        studentID = SID;
        firstName = FN;
        lastName = LN;
        gender = GEN;
        grade = GRD;
        dateOfBirth = DOB;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String stuID) {
        studentID = stuID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String FName) {
        firstName = FName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LName) {
        lastName = LName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String Gndr) {
        gender = Gndr;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int Gra) {
        grade = Gra;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOB) {
        dateOfBirth = dateOB;
    }

    @Override
    public String toString()
    {
        return "Student ID: " + getStudentID() 
                + "\nFirst Name: " + getFirstName()
                + "\nLast Name: " + getLastName()
                + "\nGender: " + gender 
                + "\nGrade: " + grade 
                + "\nDate Of Birth: " + dateOfBirth;
    }
}
