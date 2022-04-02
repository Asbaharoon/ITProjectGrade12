package PAT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.text.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.table.*;

/**
 *
 * @author Banzile Nhlebela
 */
public class StudentLog extends javax.swing.JFrame {

    /*
     * Creates new form StudentLog
     */
    private Connection con = null;
    
    private PreparedStatement pst = null;
    private PreparedStatement pst2 = null;
    private PreparedStatement pst3 = null;
    
    private String gender;
    
    public StudentLog() 
    {
        initComponents();
        Toolkit toolkit = getToolkit();
        //code to make form appear in center of device screen
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2, size.height/2-getHeight()/2);
        setColours();
        Default();
        check();
    }
    
    private void setColours()
    {
        Color bl = new Color(0, 0, 153);
        
        jTDisplay.setBackground(Color.black);
        jTDisplay.setForeground(bl);
        jTDisplay.getTableHeader().setForeground(bl);
        jTDisplay.getTableHeader().setBackground(Color.BLACK);
        jTDisplay.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
    }
    
    //method used for sorting
    private void Sort(String order /*Either ASC or DESC*/, String value /*what user wants to sort by*/)
    {
        //clear jtable 
        jTDisplay.setModel(new DefaultTableModel(null, new String[]{"Student ID", "First Name", "Last Name","Gender","Grade","Date of Birth"}));
        ArrayList<Student> userList = new ArrayList<>();
        //use conditions to obtain data from database and place in array list
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
            String query = "SELECT* FROM studentLog ORDER BY " + value + " " + order;
            ResultSet rs = (con.createStatement()).executeQuery(query);
            Student user;
            
            while(rs.next())
            {
                user = new Student(rs.getString("studentID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getInt("grade"), rs.getString("dateOfBirth"));
                userList.add(user);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        //display data from array list on jtable
        ArrayList<Student> List = userList;
        DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
        Object[] row = new Object[6];
        
        for(int i = 0; i < List.size(); i++)
        {
            row[0] = List.get(i).getStudentID();
            row[1] = List.get(i).getFirstName();
            row[2] = List.get(i).getLastName();
            row[3] = List.get(i).getGender();
            row[4] = List.get(i).getGrade();
            row[5] = List.get(i).getDateOfBirth();
            model.addRow(row);
        }
    }
    
    //method used for filtering data
    private void Filter(String filter /*what you want to filter*/, String value/*filter condition*/)
    {
        //clear data in jTable
        jTDisplay.setModel(new DefaultTableModel(null, new String[]{"Student ID", "First Name", "Last Name","Gender","Grade","Date of Birth"}));
        ArrayList<Student> userList = new ArrayList<>();
        //get data from database and store in array list
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
            String query;
            
            if(filter.equals("Grade"))
            {
                query = "SELECT * FROM studentLog WHERE " + filter + " = '" + value + "'" + "ORDER BY Gender";
            }
            else
            {
                query = "SELECT * FROM studentLog WHERE " + filter + " = '" + value + "'" + "ORDER BY Grade DESC";
            }
            
            ResultSet rs = (con.createStatement()).executeQuery(query);
            Student user;
            
            while(rs.next())
            {
                user = new Student(rs.getString("studentID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getInt("grade"), rs.getString("dateOfBirth"));
                userList.add(user);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        //display data in array list on jTable
        ArrayList<Student> List = userList;
        DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
        Object[] row = new Object[6];
        
        for(int i = 0; i < List.size(); i++)
        {
            row[0] = List.get(i).getStudentID();
            row[1] = List.get(i).getFirstName();
            row[2] = List.get(i).getLastName();
            row[3] = List.get(i).getGender();
            row[4] = List.get(i).getGrade();
            row[5] = List.get(i).getDateOfBirth();
            model.addRow(row);
        }
    }
    
    //method used for searching
    private void Search(String item /*what you will search by*/, String value/*what you want to search for*/)
    {
        jTDisplay.setModel(new DefaultTableModel(null, new String[]{"Student ID", "First Name", "Last Name","Gender","Grade","Date of Birth"}));
        Student[] userList = new Student[400];
        int num = 0;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
            String query = "SELECT * FROM studentLog WHERE " + item + " = '" + value + "' " + "ORDER BY Grade";
            ResultSet rs = (con.createStatement()).executeQuery(query);
            
                while(rs.next())
                {
                    userList[num] = new Student(rs.getString("studentID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getInt("grade"), rs.getString("dateOfBirth"));
                    num++;
                }
                
            DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
            Object[] row = new Object[6];

            for(int i = 0; i < num; i++)
            {
                row[0] = userList[i].getStudentID();
                row[1] = userList[i].getFirstName();
                row[2] = userList[i].getLastName();
                row[3] = userList[i].getGender();
                row[4] = userList[i].getGrade();
                row[5] = userList[i].getDateOfBirth();
                model.addRow(row);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void Default()
    {
        ArrayList<Student> userList = new ArrayList<>();
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
            String query = "SELECT* FROM studentLog";
            ResultSet rs = (con.createStatement()).executeQuery(query);
            Student user;
            
            while(rs.next())
            {
                user = new Student(rs.getString("studentID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getInt("grade"), rs.getString("dateOfBirth"));
                userList.add(user);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        ArrayList<Student> List = userList;
        DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
        Object[] row = new Object[6];
        
        for(int i = 0; i < List.size(); i++)
        {
            row[0] = List.get(i).getStudentID();
            row[1] = List.get(i).getFirstName();
            row[2] = List.get(i).getLastName();
            row[3] = List.get(i).getGender();
            row[4] = List.get(i).getGrade();
            row[5] = List.get(i).getDateOfBirth();
            model.addRow(row);
        }
    }
    
    private void check()
    {
        try
        {
            try(Scanner sc = new Scanner(new FileReader("temp.txt")))
            {
                String line = sc.nextLine();
                try(Scanner ar = new Scanner(line).useDelimiter("#"))
                {
                    String un = ar.next();
                    String pass = ar.next();
                    String pos = ar.next();
                    
                    Login log = new Login(un, pass, pos);
                    if(log.getPosition().equals("Prefect") || log.getPosition().equals("Head Prefect"))
                    {
                        txtStudentID.setEnabled(false);
                        cbMale.setEnabled(false);
                        cbFemale.setEnabled(false);
                        cmbGrade.setEnabled(false);
                        btnRegister.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                    else if(log.getPosition().equals("Head of School") || log.getPosition().equals("Deputy Head of School"))
                    {
                        btnDelete.setEnabled(false);
                        btnRegister.setEnabled(false);
                        btnUpdate.setEnabled(false);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void changeColor(String s, Color g, Border bb)
    {
        lblSIDerr.setText(s);
        lblFNerr.setText(s);
        lblLNerr.setText(s);
        lblGENerr.setText(s);
        lblGRDerr.setText(s);
        lblDOBerr.setText(s);
        
        lblSIDerr.setForeground(g);
        lblFNerr.setForeground(g);
        lblLNerr.setForeground(g);
        lblGENerr.setForeground(g);
        lblGRDerr.setForeground(g);
        lblDOBerr.setForeground(g);
        
        txtStudentID.setForeground(g);
        txtFName.setForeground(g);
        txtLName.setForeground(g);
        cbMale.setForeground(g);
        cbFemale.setForeground(g);
        
        txtStudentID.setBorder(bb);
        txtFName.setBorder(bb);
        txtLName.setBorder(bb);
        
        txtStudentID.requestFocus();
    }
    
    private void clear(String s, Color g, Border bb)
    {
        txtStudentID.setText(s);
        txtFName.setText(s);
        txtLName.setText(s);
        cmbGrade.setSelectedIndex(0);
        jdcDOB.setDate(null);
        cbMale.setSelected(false);
        cbFemale.setSelected(false);
        
        changeColor(s, g, bb);
    }

    private void val()
    {
        Color r = new Color(156, 0, 0);
        Color g = new Color(15, 166, 7);
        
        Border a = BorderFactory.createMatteBorder(1, 1, 3, 1, g);
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, r);
        
            //Student ID Checks
            //Presence Check
            //if student id field is empty
            if(txtStudentID.getText().isEmpty())
            {
                //display error message
                lblSIDerr.setText("Student ID must be entered*");
                lblSIDerr.setForeground(r);
                txtStudentID.setBorder(b);
                txtStudentID.setForeground(r);
            }
            //if field is not empty
            else
            {
                //analyse each letter of input
                for(int i = 0; i < txtStudentID.getText().length(); i++)
                {
                    //Type and Logical check. Only letters and numbers should be used
                    //if character is letter or number
                    if(Character.isLetter(txtStudentID.getText().charAt(i)) || Character.isDigit(txtStudentID.getText().charAt(i)))
                    {
                        //Length check
                        //if input greater than 10 characters
                        if(txtStudentID.getText().length() > 10)
                        {
                            //display message indicating error
                            lblSIDerr.setText("Ensure input has less than 10 characters*");
                            lblSIDerr.setForeground(r);
                            txtStudentID.setBorder(b);
                            txtStudentID.setForeground(r);
                        }
                        //if input less than 5 characters
                        else if(txtStudentID.getText().length() < 5)
                        {
                            //display message indicating error
                            lblSIDerr.setText("Ensure input has more than 5 characters*");
                            lblSIDerr.setForeground(r);
                            txtStudentID.setBorder(b);
                            txtStudentID.setForeground(r);
                        }
                        //if no errors present
                        else
                        {
                            //display message indicating success
                            lblSIDerr.setText("****");
                            lblSIDerr.setForeground(g);
                            txtStudentID.setBorder(a);
                            txtStudentID.setForeground(g);
                        }
                    }
                    //if input does not consist of letters and numbers
                    else
                    {
                        lblSIDerr.setText("Enter only letters and/or numbers*");
                        lblSIDerr.setForeground(r);
                        txtStudentID.setBorder(b);
                        txtStudentID.setForeground(r);
                    }
                }
            }

            //First Name Validation Checks
            //Presence Check
            //If field is empty
            if(txtFName.getText().isEmpty())
            {
                //error message displayed in first name field is empty
                lblFNerr.setText("First Name must be entered*");
                //set labels and fields to red indicating error
                lblFNerr.setForeground(r);
                txtFName.setBorder(b);
                txtFName.setForeground(r);
            }
            else
            {
                //Ensures that first letter is a capital
                //if first letter is not capital letter
                if(!Character.isUpperCase(txtFName.getText().charAt(0)))
                {
                    //error message displayed if first letter is not a capital letter
                    lblFNerr.setText("First letter must be capitalized*");
                    //set label and field to red indicating error
                    lblFNerr.setForeground(r);
                    txtFName.setBorder(b);
                    txtFName.setForeground(r);
                }
                //if first letter is in uppercase
                else
                {
                    //analyse each character to check if it is a letter
                    for(int i = 0; i < txtFName.getText().length(); i++)
                    {
                        //Type and Logical check. Only letters should be used
                        //if letter at certain position is letter
                        if(Character.isLetter(txtFName.getText().charAt(i)))
                        {
                            //Length check
                            //if input gerater than 50 characters
                            if(txtFName.getText().length() > 50)
                            {
                                //error message to be displayed if first name greater than 50 characters
                                lblFNerr.setText("Ensure input has less than 50 characters*");
                                //set label and field to red indicating error
                                lblFNerr.setForeground(r);
                                txtFName.setBorder(b);
                                txtFName.setForeground(r);
                            }
                            //if input less than 3 characters
                            else if(txtFName.getText().length() < 3)
                            {
                                //error message to be displayed if first name less than 3 characters
                                lblFNerr.setText("Ensure input has more than 3 characters*");
                                //set label and field to red indicating error
                                lblFNerr.setForeground(r);
                                txtFName.setBorder(b);
                                txtFName.setForeground(r);
                            }
                            //if there are no errors
                            else
                            {
                                //Display stars indicating correct input
                                lblFNerr.setText("****");
                                //set label and field to indicate correct input
                                lblFNerr.setForeground(g);
                                txtFName.setBorder(a);
                                txtFName.setForeground(g);
                            }
                        }
                        //if charactr other than letters are found
                        else
                        {
                            //error message displayed if there are characters other than letters in input
                            lblFNerr.setText("Enter only letters*");
                            //set label and field to red indicating error
                            lblFNerr.setForeground(r);
                            txtFName.setBorder(b);
                            txtFName.setForeground(r);
                        }
                    }
                }
            }

            //Last Name Validation Checks
            //Presence Check 
            //if field is empty
            if(txtLName.getText().isEmpty())
            {
                //error message to be displayed if last name field is empty
                lblLNerr.setText("Last Name must be entered*");
                //set label and field to red to indicate error
                lblLNerr.setForeground(r);
                txtLName.setBorder(b);
                txtLName.setForeground(r);
            }
            //if field is not empty
            else
            {
                //Ensures that first letter is a capital
                //if first letter of input is not in uppercase
                if(!Character.isUpperCase(txtLName.getText().charAt(0)))
                {
                    //error message to be displayed if first letter of input is lower case
                    lblLNerr.setText("First letter must be capitalized*");
                    //set label and field to red to indicate error
                    lblLNerr.setForeground(r);
                    txtLName.setBorder(b);
                    txtLName.setForeground(r);
                }
                //if first letter of input is in upper case
                else
                {
                    //analyse each letter of input
                    for(int i = 0; i < txtLName.getText().length(); i++)
                    {
                        //Type and Logical check. Only letters should be used
                        //if letter at i is a letter
                        if(Character.isLetter(txtLName.getText().charAt(i)))
                        {
                            //Length check
                            //if input is greater than 50 characters
                            if(txtLName.getText().length() > 50)
                            {
                                //error message to be displayed if input greater than 50 characters
                                lblLNerr.setText("Ensure input has less than 50 characters*");
                                //set label and field to red to indicate error
                                lblLNerr.setForeground(r);
                                txtLName.setBorder(b);
                                txtLName.setForeground(r);
                            }
                            //if input is less than 3 characters
                            else if(txtLName.getText().length() < 3)
                            {
                                //error message to be displayed if input is less than 3 characters
                                lblLNerr.setText("Ensure input has more than 3 characters*");
                                //set label and field to red to indicate error
                                lblLNerr.setForeground(r);
                                txtLName.setBorder(b);
                                txtLName.setForeground(r);
                            }
                            //if none of the above errors are found
                            else
                            {
                                //display stars indicating correct input
                                lblLNerr.setText("****");
                                //set label and field to green to indicate correct input
                                lblLNerr.setForeground(g);
                                txtLName.setBorder(a);
                                txtLName.setForeground(g);
                            }
                        }
                        //if chaaracter at i is not a letter
                        else
                        {
                            //error message to be displayed if character(s) not letters
                            lblLNerr.setText("Enter only letters*");
                            //set label and field to red to indicate error
                            lblLNerr.setForeground(r);
                            txtLName.setBorder(b);
                            txtLName.setForeground(r);
                        }
                    }
                }
            }

            //Grade Checks
            //Presence Check
            //if grade not selected
            if("Grade".equals(cmbGrade.getSelectedItem().toString()))
            {
                //display message indicating error
                lblGRDerr.setText("Grade must be selected*");
                lblGRDerr.setForeground(r);
            }
            //if selected
            else
            {
                //display message indicating error
                lblGRDerr.setText("****");
                lblGRDerr.setForeground(g);
            }

            //Gender Validation Checks
            //Presence Check
            //if neither of the check boxes have been selected
            if(!cbMale.isSelected() && !cbFemale.isSelected())
            {
                //error message to be displayed if both check boxes have not been selected
                lblGENerr.setText("Gender must be selected*");
                //set label and fields to red to indicate error
                lblGENerr.setForeground(r);
                cbMale.setForeground(r);
                cbFemale.setForeground(r);
            }
            //if both of the check boxes have been selected
            else if(cbMale.isSelected() && cbFemale.isSelected())
            {
                //error messgae to be displayed if both check boxes have been selected
                lblGENerr.setText("You may only select one option*");
                //set label and fields to red to indicate error
                lblGENerr.setForeground(r);
                cbMale.setForeground(r);
                cbFemale.setForeground(r);
            }
            //if only one of the two has been selected
            else
            {
                //display stars to indicate correct input
                lblGENerr.setText("****");
                //set label and fields to green to indicate correct input
                lblGENerr.setForeground(g);
                cbMale.setForeground(g);
                cbFemale.setForeground(g);
            }

            //Date of Birth Validation Checks
            //Presence Check
            //if date of birth has not been entered
            if(jdcDOB.getDate() == null)
            {
                //error message to be displayed if field has been left empty
                lblDOBerr.setText("Date of Birth must be entered*");
                //set label to red to indicate error
                lblDOBerr.setForeground(r);
            }
            //if the field is not empty
            else
            {
                //Year Logic Checks
                //initialise a birthday variable
                LocalDate birthday = jdcDOB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //initialise a current date variable
                LocalDate currentDate = LocalDate.now();

                //Logic Check
                //if birthday is after current date
                if(birthday.isAfter(currentDate))
                {
                    //message to be displayed if date of birth is in the future
                    lblDOBerr.setText("Date of birth cannot be in the future*");
                    //set label to red to indicate error
                    lblDOBerr.setForeground(r);
                }
                //if date of birth is before current date
                else
                {
                    //initialise age variable
                    Period age = Period.between(birthday, currentDate);

                    //Range Check
                    //begine swtch
                    switch(cmbGrade.getSelectedItem().toString())
                    {
                        //if in grade 8
                        case "8":
                            //if age < 13 or > 15
                            if(age.getYears() < 13 || age.getYears() > 15)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 8*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                        break;
                        //if in grade 9
                        case "9":
                            if(age.getYears() < 14 || age.getYears() > 16)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 9*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                        break;
                        //if in grade 10
                        case "10":
                            if(age.getYears() < 15 || age.getYears() > 17)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 10*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                        break;
                        //if in grade 11
                        case "11":
                            if(age.getYears() < 16 || age.getYears() > 18)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 11*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                        break;
                        //if in grade 12
                        default: 
                            if(age.getYears() < 17 || age.getYears() > 19)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 12*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                        break;
                    }
                }
            }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        lblStudentID = new javax.swing.JLabel();
        lblFName = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblGrade = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        txtFName = new javax.swing.JTextField();
        txtLName = new javax.swing.JTextField();
        cmbGrade = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        lblSIDerr = new javax.swing.JLabel();
        lblFNerr = new javax.swing.JLabel();
        lblLNerr = new javax.swing.JLabel();
        lblGENerr = new javax.swing.JLabel();
        lblGRDerr = new javax.swing.JLabel();
        cbMale = new javax.swing.JCheckBox();
        cbFemale = new javax.swing.JCheckBox();
        jdcDOB = new com.toedter.calendar.JDateChooser();
        lblDOBerr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDisplay = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFilter = new javax.swing.JMenu();
        sep3 = new javax.swing.JPopupMenu.Separator();
        filterGEN = new javax.swing.JMenu();
        sep5 = new javax.swing.JPopupMenu.Separator();
        GEN_M = new javax.swing.JMenuItem();
        sep6 = new javax.swing.JPopupMenu.Separator();
        GEN_F = new javax.swing.JMenuItem();
        sep7 = new javax.swing.JPopupMenu.Separator();
        sep1 = new javax.swing.JPopupMenu.Separator();
        fliterGrade = new javax.swing.JMenu();
        sep12 = new javax.swing.JPopupMenu.Separator();
        GRD8 = new javax.swing.JMenuItem();
        sep13 = new javax.swing.JPopupMenu.Separator();
        GRD9 = new javax.swing.JMenuItem();
        sep14 = new javax.swing.JPopupMenu.Separator();
        GRD10 = new javax.swing.JMenuItem();
        sep15 = new javax.swing.JPopupMenu.Separator();
        GRD11 = new javax.swing.JMenuItem();
        sep16 = new javax.swing.JPopupMenu.Separator();
        GRD12 = new javax.swing.JMenuItem();
        sep17 = new javax.swing.JPopupMenu.Separator();
        sep4 = new javax.swing.JPopupMenu.Separator();
        menuSort = new javax.swing.JMenu();
        sep26 = new javax.swing.JPopupMenu.Separator();
        menuASC = new javax.swing.JMenu();
        sep18 = new javax.swing.JPopupMenu.Separator();
        ASC_SID = new javax.swing.JMenuItem();
        sep19 = new javax.swing.JPopupMenu.Separator();
        ASC_GRD = new javax.swing.JMenuItem();
        sep20 = new javax.swing.JPopupMenu.Separator();
        sep21 = new javax.swing.JPopupMenu.Separator();
        menuDESC = new javax.swing.JMenu();
        sep23 = new javax.swing.JPopupMenu.Separator();
        DESC_SID = new javax.swing.JMenuItem();
        sep24 = new javax.swing.JPopupMenu.Separator();
        DESC_GRD = new javax.swing.JMenuItem();
        sep25 = new javax.swing.JPopupMenu.Separator();
        sep22 = new javax.swing.JPopupMenu.Separator();
        menuSearch = new javax.swing.JMenu();
        sep8 = new javax.swing.JPopupMenu.Separator();
        searchFN = new javax.swing.JMenuItem();
        sep9 = new javax.swing.JPopupMenu.Separator();
        searchLN = new javax.swing.JMenuItem();
        sep10 = new javax.swing.JPopupMenu.Separator();
        menuRestore = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Log");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Log");

        lblHelp.setForeground(new java.awt.Color(255, 255, 255));
        lblHelp.setText("Help");
        lblHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHelpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHelp)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHelp))
                .addContainerGap())
        );

        lblStudentID.setForeground(new java.awt.Color(255, 255, 255));
        lblStudentID.setText("Student ID");

        lblFName.setForeground(new java.awt.Color(255, 255, 255));
        lblFName.setText("First Name");

        lblLName.setForeground(new java.awt.Color(255, 255, 255));
        lblLName.setText("Last Name");

        lblGender.setForeground(new java.awt.Color(255, 255, 255));
        lblGender.setText("Gender");

        lblGrade.setForeground(new java.awt.Color(255, 255, 255));
        lblGrade.setText("Grade");

        lblDOB.setForeground(new java.awt.Color(255, 255, 255));
        lblDOB.setText("Date of Birth");

        txtStudentID.setBackground(new java.awt.Color(0, 0, 0));
        txtStudentID.setForeground(new java.awt.Color(0, 0, 153));
        txtStudentID.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(0, 0, 102)));
        txtStudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentIDActionPerformed(evt);
            }
        });
        txtStudentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStudentIDKeyTyped(evt);
            }
        });

        txtFName.setBackground(new java.awt.Color(0, 0, 0));
        txtFName.setForeground(new java.awt.Color(0, 0, 153));
        txtFName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(0, 0, 102)));
        txtFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFNameActionPerformed(evt);
            }
        });

        txtLName.setBackground(new java.awt.Color(0, 0, 0));
        txtLName.setForeground(new java.awt.Color(0, 0, 153));
        txtLName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(0, 0, 102)));
        txtLName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLNameActionPerformed(evt);
            }
        });

        cmbGrade.setBackground(new java.awt.Color(0, 0, 0));
        cmbGrade.setForeground(new java.awt.Color(0, 0, 153));
        cmbGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Grade", "8", "9", "10", "11", "12" }));
        cmbGrade.setBorder(null);
        cmbGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGradeActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(0, 0, 0));
        btnRegister.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        lblSIDerr.setForeground(new java.awt.Color(156, 0, 0));

        lblFNerr.setForeground(new java.awt.Color(156, 0, 0));

        lblLNerr.setForeground(new java.awt.Color(156, 0, 0));

        lblGENerr.setForeground(new java.awt.Color(156, 0, 0));

        lblGRDerr.setForeground(new java.awt.Color(156, 0, 0));

        cbMale.setBackground(new java.awt.Color(0, 0, 0));
        cbMale.setForeground(new java.awt.Color(0, 0, 153));
        cbMale.setText("Male");
        cbMale.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 102)));
        cbMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaleActionPerformed(evt);
            }
        });

        cbFemale.setBackground(new java.awt.Color(0, 0, 0));
        cbFemale.setForeground(new java.awt.Color(0, 0, 153));
        cbFemale.setText("Female");
        cbFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFemaleActionPerformed(evt);
            }
        });

        jdcDOB.setBackground(new java.awt.Color(0, 0, 0));
        jdcDOB.setForeground(new java.awt.Color(0, 0, 153));

        jTDisplay.setBackground(new java.awt.Color(0, 0, 0));
        jTDisplay.setBorder(new javax.swing.border.MatteBorder(null));
        jTDisplay.setForeground(new java.awt.Color(255, 255, 255));
        jTDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "First Name", "Last Name", "Gender", "Grade", "Date of Birth"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTDisplay.setGridColor(new java.awt.Color(0, 0, 153));
        jTDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTDisplayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTDisplay);

        btnUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReturn.setBackground(new java.awt.Color(0, 0, 0));
        btnReturn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnReturn.setText("Return");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 0, 0));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegister)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLName, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDOB)
                                    .addComponent(lblFName, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbMale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbFemale))
                                    .addComponent(cmbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDOBerr)
                                    .addComponent(lblGRDerr)
                                    .addComponent(lblGENerr)
                                    .addComponent(lblLNerr)
                                    .addComponent(lblFNerr)
                                    .addComponent(lblSIDerr)
                                    .addComponent(txtFName)
                                    .addComponent(txtLName)
                                    .addComponent(jdcDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtStudentID))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStudentID)
                            .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSIDerr)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFName)
                            .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFNerr)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLName)
                            .addComponent(txtLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLNerr)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGender)
                            .addComponent(cbMale)
                            .addComponent(cbFemale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGENerr)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGrade)
                            .addComponent(cmbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGRDerr)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDOB)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jdcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDOBerr))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReturn)
                    .addComponent(btnRegister)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setForeground(new java.awt.Color(0, 0, 0));

        menuFilter.setBackground(new java.awt.Color(0, 0, 102));
        menuFilter.setForeground(new java.awt.Color(0, 0, 0));
        menuFilter.setText("Filter");
        menuFilter.add(sep3);

        filterGEN.setText("Gender");
        filterGEN.add(sep5);

        GEN_M.setText("Male");
        GEN_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GEN_MActionPerformed(evt);
            }
        });
        filterGEN.add(GEN_M);
        filterGEN.add(sep6);

        GEN_F.setText("Female");
        GEN_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GEN_FActionPerformed(evt);
            }
        });
        filterGEN.add(GEN_F);
        filterGEN.add(sep7);

        menuFilter.add(filterGEN);
        menuFilter.add(sep1);

        fliterGrade.setText("Grade");
        fliterGrade.add(sep12);

        GRD8.setText("8");
        GRD8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRD8ActionPerformed(evt);
            }
        });
        fliterGrade.add(GRD8);
        fliterGrade.add(sep13);

        GRD9.setText("9");
        GRD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRD9ActionPerformed(evt);
            }
        });
        fliterGrade.add(GRD9);
        fliterGrade.add(sep14);

        GRD10.setText("10");
        GRD10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRD10ActionPerformed(evt);
            }
        });
        fliterGrade.add(GRD10);
        fliterGrade.add(sep15);

        GRD11.setText("11");
        GRD11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRD11ActionPerformed(evt);
            }
        });
        fliterGrade.add(GRD11);
        fliterGrade.add(sep16);

        GRD12.setText("12");
        GRD12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRD12ActionPerformed(evt);
            }
        });
        fliterGrade.add(GRD12);
        fliterGrade.add(sep17);

        menuFilter.add(fliterGrade);
        menuFilter.add(sep4);

        jMenuBar1.add(menuFilter);

        menuSort.setBackground(new java.awt.Color(0, 0, 102));
        menuSort.setForeground(new java.awt.Color(0, 0, 0));
        menuSort.setText("Sort");
        menuSort.add(sep26);

        menuASC.setText("Ascending Order");
        menuASC.add(sep18);

        ASC_SID.setText("Student ID");
        ASC_SID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASC_SIDActionPerformed(evt);
            }
        });
        menuASC.add(ASC_SID);
        menuASC.add(sep19);

        ASC_GRD.setText("Grade");
        ASC_GRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASC_GRDActionPerformed(evt);
            }
        });
        menuASC.add(ASC_GRD);
        menuASC.add(sep20);

        menuSort.add(menuASC);
        menuSort.add(sep21);

        menuDESC.setText("Descending Order");
        menuDESC.add(sep23);

        DESC_SID.setText("Student ID");
        DESC_SID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DESC_SIDActionPerformed(evt);
            }
        });
        menuDESC.add(DESC_SID);
        menuDESC.add(sep24);

        DESC_GRD.setText("Grade");
        DESC_GRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DESC_GRDActionPerformed(evt);
            }
        });
        menuDESC.add(DESC_GRD);
        menuDESC.add(sep25);

        menuSort.add(menuDESC);
        menuSort.add(sep22);

        jMenuBar1.add(menuSort);

        menuSearch.setBackground(new java.awt.Color(0, 0, 102));
        menuSearch.setForeground(new java.awt.Color(0, 0, 0));
        menuSearch.setText("Search");
        menuSearch.add(sep8);

        searchFN.setText("First Name");
        searchFN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFNActionPerformed(evt);
            }
        });
        menuSearch.add(searchFN);
        menuSearch.add(sep9);

        searchLN.setText("Last Name");
        searchLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLNActionPerformed(evt);
            }
        });
        menuSearch.add(searchLN);
        menuSearch.add(sep10);

        jMenuBar1.add(menuSearch);

        menuRestore.setBackground(new java.awt.Color(0, 0, 102));
        menuRestore.setForeground(new java.awt.Color(0, 0, 0));
        menuRestore.setText("Restore");
        menuRestore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRestoreMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuRestore);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        
        Color r = new Color(156, 0, 0);
        Color g = new Color(15, 166, 7);
        Color bl = new Color(0, 0, 153);
        
        Border a = BorderFactory.createMatteBorder(1, 1, 3, 1, g);
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, r);
        Border c = BorderFactory.createMatteBorder(1, 1, 3, 1, bl);
    
        int opt = JOptionPane.showConfirmDialog(null, "You are about to Insert a new record. Are you sure of this action?", "Insert", JOptionPane.YES_NO_OPTION);
        if(opt == 0)
        {
            //Student ID Checks
            //Presence Check
            //if student id field is empty
            if(txtStudentID.getText().isEmpty())
            {
                //display error message
                lblSIDerr.setText("Student ID must be entered*");
                lblSIDerr.setForeground(r);
                txtStudentID.setBorder(b);
                txtStudentID.setForeground(r);
            }
            //if field is not empty
            else
            {
                //analyse each letter of input
                for(int i = 0; i < txtStudentID.getText().length(); i++)
                {
                    //Type and Logical check. Only letters and numbers should be used
                    //if character is letter or number
                    if(Character.isLetter(txtStudentID.getText().charAt(i)) || Character.isDigit(txtStudentID.getText().charAt(i)))
                    {
                        //Length check
                        //if input greater than 10 characters
                        if(txtStudentID.getText().length() > 10)
                        {
                            //display message indicating error
                            lblSIDerr.setText("Ensure input has less than 10 characters*");
                            lblSIDerr.setForeground(r);
                            txtStudentID.setBorder(b);
                            txtStudentID.setForeground(r);
                        }
                        //if input less than 5 characters
                        else if(txtStudentID.getText().length() < 5)
                        {
                            //display message indicating error
                            lblSIDerr.setText("Ensure input has more than 5 characters*");
                            lblSIDerr.setForeground(r);
                            txtStudentID.setBorder(b);
                            txtStudentID.setForeground(r);
                        }
                        //if no errors present
                        else
                        {
                            //display message indicating success
                            lblSIDerr.setText("****");
                            lblSIDerr.setForeground(g);
                            txtStudentID.setBorder(a);
                            txtStudentID.setForeground(g);
                        }
                    }
                    //if input does not consist of letters and numbers
                    else
                    {
                        lblSIDerr.setText("Enter only letters and/or numbers*");
                        lblSIDerr.setForeground(r);
                        txtStudentID.setBorder(b);
                        txtStudentID.setForeground(r);
                    }
                }
                
                //begin try-catch
                try
                {
                    String query = "SELECT * FROM studentlog WHERE `StudentID` = '" + txtStudentID.getText() + "';";
                    //get connection to database
                    con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                    pst = con.prepareStatement(query);

                    ResultSet rs = pst.executeQuery(query);
                    //if student ID found
                    if(rs.next())
                    {
                        //display message indicating error
                        lblSIDerr.setText("Student ID already exists*");
                        lblSIDerr.setForeground(r);
                        txtStudentID.setBorder(b);
                        txtStudentID.setForeground(r);
                    }
                    //if not found
                    else
                    {
                        //display message indicating correct input
                        lblSIDerr.setText("****");
                        lblSIDerr.setForeground(g);
                        txtStudentID.setBorder(a);
                        txtStudentID.setForeground(g);
                    }
                }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
                //end of try-catch
            }

            //First Name Validation Checks
            //Presence Check
            //If field is empty
            if(txtFName.getText().isEmpty())
            {
                //error message displayed in first name field is empty
                lblFNerr.setText("First Name must be entered*");
                //set labels and fields to red indicating error
                lblFNerr.setForeground(r);
                txtFName.setBorder(b);
                txtFName.setForeground(r);
            }
            else
            {
                //Ensures that first letter is a capital
                //if first letter is not capital letter
                if(!Character.isUpperCase(txtFName.getText().charAt(0)))
                {
                    //error message displayed if first letter is not a capital letter
                    lblFNerr.setText("First letter must be capitalized*");
                    //set label and field to red indicating error
                    lblFNerr.setForeground(r);
                    txtFName.setBorder(b);
                    txtFName.setForeground(r);
                }
                //if first letter is in uppercase
                else
                {
                    //analyse each character to check if it is a letter
                    for(int i = 0; i < txtFName.getText().length(); i++)
                    {
                        //Type and Logical check. Only letters should be used
                        //if letter at certain position is letter
                        if(Character.isLetter(txtFName.getText().charAt(i)))
                        {
                            //Length check
                            //if input gerater than 50 characters
                            if(txtFName.getText().length() > 50)
                            {
                                //error message to be displayed if first name greater than 50 characters
                                lblFNerr.setText("Ensure input has less than 50 characters*");
                                //set label and field to red indicating error
                                lblFNerr.setForeground(r);
                                txtFName.setBorder(b);
                                txtFName.setForeground(r);
                            }
                            //if input less than 3 characters
                            else if(txtFName.getText().length() < 3)
                            {
                                //error message to be displayed if first name less than 3 characters
                                lblFNerr.setText("Ensure input has more than 3 characters*");
                                //set label and field to red indicating error
                                lblFNerr.setForeground(r);
                                txtFName.setBorder(b);
                                txtFName.setForeground(r);
                            }
                            //if there are no errors
                            else
                            {
                                //Display stars indicating correct input
                                lblFNerr.setText("****");
                                //set label and field to indicate correct input
                                lblFNerr.setForeground(g);
                                txtFName.setBorder(a);
                                txtFName.setForeground(g);
                            }
                        }
                        //if charactr other than letters are found
                        else
                        {
                            //error message displayed if there are characters other than letters in input
                            lblFNerr.setText("Enter only letters*");
                            //set label and field to red indicating error
                            lblFNerr.setForeground(r);
                            txtFName.setBorder(b);
                            txtFName.setForeground(r);
                        }
                    }
                }
            }

            //Last Name Validation Checks
            //Presence Check 
            //if field is empty
            if(txtLName.getText().isEmpty())
            {
                //error message to be displayed if last name field is empty
                lblLNerr.setText("Last Name must be entered*");
                //set label and field to red to indicate error
                lblLNerr.setForeground(r);
                txtLName.setBorder(b);
                txtLName.setForeground(r);
            }
            //if field is not empty
            else
            {
                //Ensures that first letter is a capital
                //if first letter of input is not in uppercase
                if(!Character.isUpperCase(txtLName.getText().charAt(0)))
                {
                    //error message to be displayed if first letter of input is lower case
                    lblLNerr.setText("First letter must be capitalized*");
                    //set label and field to red to indicate error
                    lblLNerr.setForeground(r);
                    txtLName.setBorder(b);
                    txtLName.setForeground(r);
                }
                //if first letter of input is in upper case
                else
                {
                    //analyse each letter of input
                    for(int i = 0; i < txtLName.getText().length(); i++)
                    {
                        //Type and Logical check. Only letters should be used
                        //if letter at i is a letter
                        if(Character.isLetter(txtLName.getText().charAt(i)))
                        {
                            //Length check
                            //if input is greater than 50 characters
                            if(txtLName.getText().length() > 50)
                            {
                                //error message to be displayed if input greater than 50 characters
                                lblLNerr.setText("Ensure input has less than 50 characters*");
                                //set label and field to red to indicate error
                                lblLNerr.setForeground(r);
                                txtLName.setBorder(b);
                                txtLName.setForeground(r);
                            }
                            //if input is less than 3 characters
                            else if(txtLName.getText().length() < 3)
                            {
                                //error message to be displayed if input is less than 3 characters
                                lblLNerr.setText("Ensure input has more than 3 characters*");
                                //set label and field to red to indicate error
                                lblLNerr.setForeground(r);
                                txtLName.setBorder(b);
                                txtLName.setForeground(r);
                            }
                            //if none of the above errors are found
                            else
                            {
                                //display stars indicating correct input
                                lblLNerr.setText("****");
                                //set label and field to green to indicate correct input
                                lblLNerr.setForeground(g);
                                txtLName.setBorder(a);
                                txtLName.setForeground(g);
                            }
                        }
                        //if chaaracter at i is not a letter
                        else
                        {
                            //error message to be displayed if character(s) not letters
                            lblLNerr.setText("Enter only letters*");
                            //set label and field to red to indicate error
                            lblLNerr.setForeground(r);
                            txtLName.setBorder(b);
                            txtLName.setForeground(r);
                        }
                    }
                }
            }

            //Grade Checks
            //Presence Check
            //if grade not selected
            if("Grade".equals(cmbGrade.getSelectedItem().toString()))
            {
                //display message indicating error
                lblGRDerr.setText("Grade must be selected*");
                lblGRDerr.setForeground(r);
            }
            //if selected
            else
            {
                //display message indicating error
                lblGRDerr.setText("****");
                lblGRDerr.setForeground(g);
            }

            //Gender Validation Checks
            //Presence Check
            //if neither of the check boxes have been selected
            if(!cbMale.isSelected() && !cbFemale.isSelected())
            {
                //error message to be displayed if both check boxes have not been selected
                lblGENerr.setText("Gender must be selected*");
                //set label and fields to red to indicate error
                lblGENerr.setForeground(r);
                cbMale.setForeground(r);
                cbFemale.setForeground(r);
            }
            //if both of the check boxes have been selected
            else if(cbMale.isSelected() && cbFemale.isSelected())
            {
                //error messgae to be displayed if both check boxes have been selected
                lblGENerr.setText("You may only select one option*");
                //set label and fields to red to indicate error
                lblGENerr.setForeground(r);
                cbMale.setForeground(r);
                cbFemale.setForeground(r);
            }
            //if only one of the two has been selected
            else
            {
                //display stars to indicate correct input
                lblGENerr.setText("****");
                //set label and fields to green to indicate correct input
                lblGENerr.setForeground(g);
                cbMale.setForeground(g);
                cbFemale.setForeground(g);
            }

            //Date of Birth Validation Checks
            //Presence Check
            //if date of birth has not been entered
            if(jdcDOB.getDate() == null)
            {
                //error message to be displayed if field has been left empty
                lblDOBerr.setText("Date of Birth must be entered*");
                //set label to red to indicate error
                lblDOBerr.setForeground(r);
            }
            //if the field is not empty
            else
            {
                //Year Logic Checks
                //initialise a birthday variable
                LocalDate birthday = jdcDOB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //initialise a current date variable
                LocalDate currentDate = LocalDate.now();

                //Logic Check
                //if birthday is after current date
                if(birthday.isAfter(currentDate))
                {
                    //message to be displayed if date of birth is in the future
                    lblDOBerr.setText("Date of birth cannot be in the future*");
                    //set label to red to indicate error
                    lblDOBerr.setForeground(r);
                }
                //if date of birth is before current date
                else
                {
                    //initialise age variable
                    Period age = Period.between(birthday, currentDate);

                    //Range Check
                    //begine swtch
                    switch(cmbGrade.getSelectedItem().toString())
                    {
                        //if in grade 8
                        case "8":
                            //if age < 13 or > 15
                            if(age.getYears() < 13 || age.getYears() > 15)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 8*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                            break;
                            //if in grade 9
                        case "9":
                            if(age.getYears() < 14 || age.getYears() > 16)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 9*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                            break;
                            //if in grade 10
                        case "10":
                            if(age.getYears() < 15 || age.getYears() > 17)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 10*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                            break;
                            //if in grade 11
                        case "11":
                            if(age.getYears() < 16 || age.getYears() > 18)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 11*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                            break;
                        //if in grade 12
                        default: 
                            if(age.getYears() < 17 || age.getYears() > 19)
                            {
                                //display error message indicating error
                                lblDOBerr.setText("Inappropriate age for Grade 12*");
                                lblDOBerr.setForeground(r);
                            }
                            else
                            {
                                //display error message indicating correct input
                                lblDOBerr.setText("****");
                                lblDOBerr.setForeground(g);
                            }
                            break;
                    }
                }
            }
            
            
            
            //If all data is valid
            if(lblSIDerr.getText().equals("****") && lblFNerr.getText().equals("****") && lblLNerr.getText().equals("****") && lblGRDerr.getText().equals("****") && lblGENerr.getText().equals("****") && lblDOBerr.getText().equals("****"))
            {
                //begin try-catch
                try
                {
                    String query = "INSERT INTO `studentLog`(`StudentID`, `FirstName`, `LastName`, `Gender`, `Grade`, `DateOfBirth`) VALUES (?, ?, ?, ?, ?, ?)";
                    //get connection to database
                    con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                    pst = con.prepareStatement(query);
                    //insert data into database
                    pst.setString(1, txtStudentID.getText());
                    pst.setString(2, txtFName.getText());
                    pst.setString(3, txtLName.getText());
                    if(cbMale.isSelected())
                    {
                        gender = "Male";
                    }
                    else if(cbFemale.isSelected())
                    {
                        gender = "Female";
                    }
                    pst.setString(4, gender);
                    pst.setInt(5, Integer.valueOf(cmbGrade.getSelectedItem().toString()));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    pst.setString(6, sdf.format(jdcDOB.getDate()));
                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
                    model.setRowCount(0);
                    Default();
                    //display data on jtable
                    JOptionPane.showMessageDialog(null, "Registered Successfully");
                    clear("", bl, c);
                }
                catch(HeadlessException | SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
                //end of try-catch
            }
            else
            {
                //Message to be displayed if not all data is valid
                JOptionPane.showMessageDialog(null, "Check that all fields input correctly", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtStudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIDActionPerformed

    private void txtFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFNameActionPerformed

    private void txtLNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLNameActionPerformed

    private void cmbGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGradeActionPerformed

    private void cbMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMaleActionPerformed

    private void cbFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFemaleActionPerformed

    //display student information upon selecting a row
    private void jTDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTDisplayMouseClicked
        // TODO add your handling code here:
        Color bl = new Color(0, 0, 153);
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, bl);
        clear("", bl, b);
        
        int i = jTDisplay.getSelectedRow();
        TableModel model = jTDisplay.getModel();
        
        txtStudentID.setText(model.getValueAt(i, 0).toString());
        txtFName.setText(model.getValueAt(i, 1).toString());
        txtLName.setText(model.getValueAt(i, 2).toString());
        String gen = model.getValueAt(i, 3).toString();
        if(gen.equals("Male"))
        {
            cbMale.setSelected(true);
            cbFemale.setSelected(false);
        }
        else
        {
            cbFemale.setSelected(true);
            cbMale.setSelected(false);
        }
        
        String grade = model.getValueAt(i, 4).toString();
        switch (grade) 
        {
            case "8":
                cmbGrade.setSelectedIndex(1);
                break;
            case "9":
                cmbGrade.setSelectedIndex(2);
                break;
            case "10":
                cmbGrade.setSelectedIndex(3);
                break;
            case "11":
                cmbGrade.setSelectedIndex(4);
                break;
            default:
                cmbGrade.setSelectedIndex(5);
                break;
        }
        
        try
        {
            int r = jTDisplay.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(r, 5));
            jdcDOB.setDate(date);
        }
        catch(ParseException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTDisplayMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        
        Color bl = new Color(0, 0, 153);
        Border c = BorderFactory.createMatteBorder(1, 1, 3, 1, bl);
        
        //confirmation message asking user if they want to update
        int opt = JOptionPane.showConfirmDialog(null, "You are about to Update one row of data. Are you sure of this action?", "Update", JOptionPane.YES_NO_CANCEL_OPTION);
        //if user clicks yes
        if(opt == 0)
        {
            //validate data
            val();
            
            //if all data is valid
            if(lblSIDerr.getText().equals("****") && lblFNerr.getText().equals("****") && lblLNerr.getText().equals("****") && lblGRDerr.getText().equals("****") && lblGENerr.getText().equals("****") && lblDOBerr.getText().equals("****"))
            {
                //begin try-catch
                try
                {
                    //get connection to database
                    con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                    int num = jTDisplay.getSelectedRow();
                    String value = jTDisplay.getModel().getValueAt(num, 0).toString();
                    String query = "UPDATE studentLog SET `StudentID` = ?, `FirstName` = ?, `LastName` = ?, `Gender` = ?, `Grade` = ?, `DateOfBirth` = ? WHERE `StudentID` = '" + value + "'";
                    
                    String query2 = "UPDATE parents SET `StudentID` = '" + txtStudentID.getText() + "' WHERE `StudentID = '" + value + "'";
                    String query3 = "UPDATE conLog SET `StudentID` = '" + txtStudentID.getText() + "' WHERE `StudentID = '" + value + "'";
                            
                    pst = con.prepareStatement(query);
                    pst2 = con.prepareStatement(query2);
                    pst2 = con.prepareStatement(query3);
                    
                    //update database
                    pst.setString(1, txtStudentID.getText());
                    pst.setString(2, txtFName.getText());
                    pst.setString(3, txtLName.getText());
                    if(cbMale.isSelected())
                    {
                       gender = "Male";
                    }
                    else if(cbFemale.isSelected())
                    {
                       gender = "Female";
                    }
                    pst.setString(4, gender);
                    pst.setInt(5, Integer.valueOf(cmbGrade.getSelectedItem().toString()));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    pst.setString(6, sdf.format(jdcDOB.getDate()));
                    pst.executeUpdate();
                    pst2.executeUpdate();
                    pst3.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
                    model.setRowCount(0);
                    Default();
                    //display message indicating success
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    clear("", bl, c);
                }
                catch(HeadlessException | SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
                //end of try-catch
            }
            //if not all data valid
            else
            {
                //display message indicating error
                JOptionPane.showMessageDialog(null, "Check that all data is valid", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //if user clicks no
        else
        {
            //display message indicating abort
            JOptionPane.showMessageDialog(null, "Update Action Aborted");
            clear("", bl, c);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        Color bl = new Color(0, 0, 153);
        Color r = new Color(156, 0, 0);
        
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, r);
        Border c = BorderFactory.createMatteBorder(1, 1, 3, 1, bl);
        
        //change colour of fields to red
        changeColor("****", r, b);
        //confirmation message asking user if they want to delete row
        int opt = JOptionPane.showConfirmDialog(null, "You are about to Delete one row of data. Are you sure of this action?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //if user clicks yes
        if(opt == 0)
        {
            if(txtStudentID.getText().isEmpty())
            {
                changeColor("", bl, c);
                //error message to be displayed if character(s) not letters
                lblSIDerr.setText("Select data from table*");
                //set label and field to red to indicate error
                lblSIDerr.setForeground(r);
                txtStudentID.setBorder(b);
                txtStudentID.setForeground(r);
            }
            else
            {
                try
                {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                    String query = "SELECT * FROM studentLog WHERE StudentID = '" + txtStudentID.getText() + "'";
                    pst = con.prepareStatement(query);
                    ResultSet rs = pst.executeQuery(query); 
                    
                    if(rs.next())
                    {
                        //begin try-catch
                        try
                        {
                            //get connection to database
                            con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                            int num = jTDisplay.getSelectedRow();
                            String value = jTDisplay.getModel().getValueAt(num, 0).toString();
                            String query2 = "DELETE FROM studentlog WHERE StudentID = '" + value + "'";
                            String query3 = "DELETE FROM parents WHERE StudentID = '" + value + "'";
                            String query4 = "DELETE FROM conlog WHERE StudentID = '" + value + "'";
                            
                            pst = con.prepareStatement(query2);
                            pst2 = con.prepareStatement(query3);
                            pst3 = con.prepareStatement(query4);
                            
                            //delete data from database
                            pst.executeUpdate();
                            pst2.executeUpdate();
                            pst3.executeUpdate();

                            DefaultTableModel model = (DefaultTableModel)jTDisplay.getModel();
                            model.setRowCount(0);
                            Default();
                            clear("", bl, c);
                            //display message indicating success
                            JOptionPane.showMessageDialog(null, "Deleted Successfully");
                        }
                        catch(HeadlessException | SQLException ex)
                        {
                            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        //end of try-catch
                    }
                    else
                    {
                        changeColor("", bl, c);
                        //error message to be displayed if character(s) not letters
                        lblSIDerr.setText("SELECT DATA FROM TABLE*");
                        //set label and field to red to indicate error
                        lblSIDerr.setForeground(r);
                        txtStudentID.setBorder(b);
                        txtStudentID.setForeground(r);
                    }
                }
                catch(HeadlessException | SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        //if user clicks no
        else
        {
            //display message indicating abort
            JOptionPane.showMessageDialog(null, "Delete Action Aborted");
            clear("", bl, c);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        //display main menu 
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
        //close current screen
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void lblHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseClicked
        // TODO add your handling code here:
        //display help screen
        Help h = new Help();
        h.setVisible(true);
    }//GEN-LAST:event_lblHelpMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        Color bl = new Color(0, 0, 153);
        Border c = BorderFactory.createMatteBorder(1, 1, 3, 1, bl);
        clear("", bl, c);
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtStudentIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentIDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIDKeyTyped

    private void menuRestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRestoreMouseClicked
        // TODO add your handling code here:
        //restore original data to jtable
        jTDisplay.setModel(new DefaultTableModel(null, new String[]{"Student ID", "First Name", "Last Name","Gender","Grade","Date of Birth"}));
        Default();
    }//GEN-LAST:event_menuRestoreMouseClicked

    private void GEN_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GEN_FActionPerformed
        // TODO add your handling code here:
        Filter("Gender", "Female");
    }//GEN-LAST:event_GEN_FActionPerformed

    private void GEN_MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GEN_MActionPerformed
        // TODO add your handling code here:
        Filter("Gender", "Male");
    }//GEN-LAST:event_GEN_MActionPerformed

    private void GRD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRD8ActionPerformed
        // TODO add your handling code here:
        Filter("Grade", "8");
    }//GEN-LAST:event_GRD8ActionPerformed

    private void GRD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRD9ActionPerformed
        // TODO add your handling code here:
        Filter("Grade", "9");
    }//GEN-LAST:event_GRD9ActionPerformed

    private void GRD10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRD10ActionPerformed
        // TODO add your handling code here:
        Filter("Grade", "10");
    }//GEN-LAST:event_GRD10ActionPerformed

    private void GRD11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRD11ActionPerformed
        // TODO add your handling code here:
        Filter("Grade", "11");
    }//GEN-LAST:event_GRD11ActionPerformed

    private void GRD12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRD12ActionPerformed
        // TODO add your handling code here:
        Filter("Grade", "12");
    }//GEN-LAST:event_GRD12ActionPerformed

    private void ASC_SIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASC_SIDActionPerformed
        // TODO add your handling code here:
        Sort("ASC", "StudentID");
    }//GEN-LAST:event_ASC_SIDActionPerformed

    private void ASC_GRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASC_GRDActionPerformed
        // TODO add your handling code here:
        Sort("ASC", "Grade");
    }//GEN-LAST:event_ASC_GRDActionPerformed

    private void DESC_SIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DESC_SIDActionPerformed
        // TODO add your handling code here:
        Sort("DESC", "StudentID");
    }//GEN-LAST:event_DESC_SIDActionPerformed

    private void DESC_GRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DESC_GRDActionPerformed
        // TODO add your handling code here:
        Sort("DESC", "Grade");
    }//GEN-LAST:event_DESC_GRDActionPerformed

    private void searchFNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFNActionPerformed
        // TODO add your handling code here:
        String v = JOptionPane.showInputDialog(null, "Enter the First Name");
        Search("Firstname", v);
    }//GEN-LAST:event_searchFNActionPerformed

    private void searchLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchLNActionPerformed
        // TODO add your handling code here:
        String v = JOptionPane.showInputDialog(null, "Enter the Last Name");
        Search("Lastname", v);
    }//GEN-LAST:event_searchLNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new StudentLog().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ASC_GRD;
    private javax.swing.JMenuItem ASC_SID;
    private javax.swing.JMenuItem DESC_GRD;
    private javax.swing.JMenuItem DESC_SID;
    private javax.swing.JMenuItem GEN_F;
    private javax.swing.JMenuItem GEN_M;
    private javax.swing.JMenuItem GRD10;
    private javax.swing.JMenuItem GRD11;
    private javax.swing.JMenuItem GRD12;
    private javax.swing.JMenuItem GRD8;
    private javax.swing.JMenuItem GRD9;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbFemale;
    private javax.swing.JCheckBox cbMale;
    private javax.swing.JComboBox<String> cmbGrade;
    private javax.swing.JMenu filterGEN;
    private javax.swing.JMenu fliterGrade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTDisplay;
    private com.toedter.calendar.JDateChooser jdcDOB;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDOBerr;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblFNerr;
    private javax.swing.JLabel lblGENerr;
    private javax.swing.JLabel lblGRDerr;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblGrade;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblLName;
    private javax.swing.JLabel lblLNerr;
    private javax.swing.JLabel lblSIDerr;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JMenu menuASC;
    private javax.swing.JMenu menuDESC;
    private javax.swing.JMenu menuFilter;
    private javax.swing.JMenu menuRestore;
    private javax.swing.JMenu menuSearch;
    private javax.swing.JMenu menuSort;
    private javax.swing.JMenuItem searchFN;
    private javax.swing.JMenuItem searchLN;
    private javax.swing.JPopupMenu.Separator sep1;
    private javax.swing.JPopupMenu.Separator sep10;
    private javax.swing.JPopupMenu.Separator sep12;
    private javax.swing.JPopupMenu.Separator sep13;
    private javax.swing.JPopupMenu.Separator sep14;
    private javax.swing.JPopupMenu.Separator sep15;
    private javax.swing.JPopupMenu.Separator sep16;
    private javax.swing.JPopupMenu.Separator sep17;
    private javax.swing.JPopupMenu.Separator sep18;
    private javax.swing.JPopupMenu.Separator sep19;
    private javax.swing.JPopupMenu.Separator sep20;
    private javax.swing.JPopupMenu.Separator sep21;
    private javax.swing.JPopupMenu.Separator sep22;
    private javax.swing.JPopupMenu.Separator sep23;
    private javax.swing.JPopupMenu.Separator sep24;
    private javax.swing.JPopupMenu.Separator sep25;
    private javax.swing.JPopupMenu.Separator sep26;
    private javax.swing.JPopupMenu.Separator sep3;
    private javax.swing.JPopupMenu.Separator sep4;
    private javax.swing.JPopupMenu.Separator sep5;
    private javax.swing.JPopupMenu.Separator sep6;
    private javax.swing.JPopupMenu.Separator sep7;
    private javax.swing.JPopupMenu.Separator sep8;
    private javax.swing.JPopupMenu.Separator sep9;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextField txtStudentID;
    // End of variables declaration//GEN-END:variables
}
