package PAT;

/**
 *
 * @author Banzile Nhlebela
 */

public class Login
{
    private String username;
    private String password;
    private String position;
    
    public Login(String un, String pass, String pos)
    {
        username = un;
        password = pass;
        position = pos;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String un)
    {
        username = un;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String pass)
    {
        password = pass;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public void setPosition(String pos)
    {
        position = pos;
    }
    
    @Override
    public String toString()
    {
        return "Username: " + getUsername() 
                + "\nPassword: " + getPassword() 
                + "\nPosition: " + getPosition();
    }
}
