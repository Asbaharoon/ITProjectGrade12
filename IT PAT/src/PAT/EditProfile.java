package PAT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author Banzile Nhlebela
 */
public class EditProfile extends javax.swing.JFrame {

    /**
     * Creates new form EditProfile
     */
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private PreparedStatement ps = null;
    private String gender;
    
    
    public EditProfile()
    {
        initComponents();
        //code to make screen appear in center of device screen
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2, size.height/2-getHeight()/2);
        //call set and check methods
        set();
        check();
    }
    
    //method to check position of user controlling what each user has access to
    private void check()
    {
        //begin try-catch
        try
        {
            //read temp.txt file
            try(Scanner sc = new Scanner(new FileReader("temp.txt")))
            {
                String line = sc.nextLine();
                try(Scanner ar = new Scanner(line).useDelimiter("#"))
                {
                    String un = ar.next();
                    String pass = ar.next();
                    String pos = ar.next();
                    
                    Login log = new Login(un, pass, pos);
                    //if position is Head Prefect or Prefect
                    if(log.getPosition().equals("Prefect") || log.getPosition().equals("Head Prefect"))
                    {
                        //Disable position editing ability
                        cmbPos.setEnabled(false);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        //end of try-catch
    }
    
    //method to display all user information in different fields
    private void set()
    {
        //begin try-catch
        try
        {   
            //read temp.txt file
            try(Scanner sc = new Scanner(new FileReader("temp.txt")))
            {
                String line = sc.nextLine();
                try(Scanner ar = new Scanner(line).useDelimiter("#"))
                {
                    String un = ar.next();
                    String pass = ar.next();
                    String pos = ar.next();
                    //create login object
                    Login log = new Login(un, pass, pos);
                    
                    //get connection to database
                    con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                    //search for data using current username
                    String query = "SELECT * FROM accountdetails WHERE `Username` = '" + log.getUsername() + "'";
                    pst = con.prepareStatement(query);
                    //get data of user
                    ResultSet r = pst.executeQuery(query);

                    //if user data found
                    if(r.next())
                    {
                        //display all user data
                        lblUNerr.setText("");
                        txtFName.setText(r.getString("Firstname"));
                        txtLName.setText(r.getString("Lastname"));

                        if(r.getString("Gender").equals("Male"))
                        {
                            cbMale.setSelected(true);
                        }
                        else
                        {
                            cbFemale.setSelected(true);
                        }

                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("DateOfBirth"));
                        jdcDOB.setDate(date);

                        switch (r.getString("Position")) 
                        {
                            case "Head of School":
                                cmbPos.setSelectedIndex(1);
                                break;
                            case "Deputy Head of School":
                                cmbPos.setSelectedIndex(2);
                                break;
                            case "Head of Department":
                                cmbPos.setSelectedIndex(3);
                                break;
                            case "Teacher":
                                cmbPos.setSelectedIndex(4);
                                break;
                            case "Head Prefect":
                                cmbPos.setSelectedIndex(5);
                                break;
                            case "Prefect":
                                cmbPos.setSelectedIndex(6);
                                break;
                            default:
                                    break;
                        }

                        txtUN.setText(r.getString("Username"));
                        pfPass.setText(r.getString("Password"));
                    }
                }
            }
        }
        catch(SQLException | ParseException | FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        //end of try-catch
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
        JPbackground = new javax.swing.JPanel();
        JPblackband = new javax.swing.JPanel();
        lblFName = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtFName = new javax.swing.JTextField();
        cmbPos = new javax.swing.JComboBox<>();
        pfPass = new javax.swing.JPasswordField();
        jdcDOB = new com.toedter.calendar.JDateChooser();
        cbMale = new javax.swing.JCheckBox();
        cbFemale = new javax.swing.JCheckBox();
        txtLName = new javax.swing.JTextField();
        txtUN = new javax.swing.JTextField();
        btnChange = new javax.swing.JToggleButton();
        lblFNerr = new javax.swing.JLabel();
        lblLNerr = new javax.swing.JLabel();
        lblGENerr = new javax.swing.JLabel();
        lblDOBerr = new javax.swing.JLabel();
        lblUNerr = new javax.swing.JLabel();
        lblPWDerr = new javax.swing.JLabel();
        lblPOSerr = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblEye = new javax.swing.JLabel();
        JPblueband = new javax.swing.JPanel();
        WhiteBand = new javax.swing.JPanel();
        lblSchoolName = new javax.swing.JLabel();
        lblSchoolMotto = new javax.swing.JLabel();
        lblBadge = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        JPyellowband = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Profile ");

        JPbackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPblackband.setBackground(new java.awt.Color(0, 0, 0));
        JPblackband.setForeground(new java.awt.Color(0, 0, 0));
        JPblackband.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFName.setForeground(new java.awt.Color(255, 255, 255));
        lblFName.setText("First Name");
        JPblackband.add(lblFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 32, -1, -1));

        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Username");
        JPblackband.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, -1, -1));

        lblLName.setForeground(new java.awt.Color(255, 255, 255));
        lblLName.setText("Last Name");
        JPblackband.add(lblLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 130, -1, -1));

        lblDOB.setForeground(new java.awt.Color(255, 255, 255));
        lblDOB.setText("Date of Birth");
        JPblackband.add(lblDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 334, -1, -1));

        lblPosition.setForeground(new java.awt.Color(255, 255, 255));
        lblPosition.setText("Position");
        JPblackband.add(lblPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 23, -1, -1));

        lblGender.setForeground(new java.awt.Color(255, 255, 255));
        lblGender.setText("Gender");
        JPblackband.add(lblGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 236, -1, -1));

        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        JPblackband.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 248, -1, -1));

        txtFName.setBackground(new java.awt.Color(0, 0, 0));
        txtFName.setForeground(new java.awt.Color(255, 204, 0));
        txtFName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(255, 204, 0)));
        JPblackband.add(txtFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 58, 224, -1));

        cmbPos.setBackground(new java.awt.Color(0, 0, 0));
        cmbPos.setForeground(new java.awt.Color(255, 204, 0));
        cmbPos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Position", "Head of School", "Deputy Head of School", "Head of Department", "Teacher", "Head Prefect", "Prefect" }));
        JPblackband.add(cmbPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 51, 210, -1));

        pfPass.setBackground(new java.awt.Color(0, 0, 0));
        pfPass.setForeground(new java.awt.Color(255, 204, 0));
        pfPass.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(255, 204, 0)));
        pfPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfPassKeyTyped(evt);
            }
        });
        JPblackband.add(pfPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 210, -1));

        jdcDOB.setBackground(new java.awt.Color(0, 0, 0));
        jdcDOB.setForeground(new java.awt.Color(0, 0, 153));
        JPblackband.add(jdcDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 356, 224, -1));

        cbMale.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(cbMale);
        cbMale.setForeground(new java.awt.Color(255, 204, 0));
        cbMale.setText("Male");
        cbMale.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 204, 0)));
        JPblackband.add(cbMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 257, -1, -1));

        cbFemale.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(cbFemale);
        cbFemale.setForeground(new java.awt.Color(255, 204, 0));
        cbFemale.setText("Female");
        cbFemale.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 204, 0)));
        JPblackband.add(cbFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 257, -1, -1));

        txtLName.setBackground(new java.awt.Color(0, 0, 0));
        txtLName.setForeground(new java.awt.Color(255, 204, 0));
        txtLName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(255, 204, 0)));
        JPblackband.add(txtLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 152, 224, -1));

        txtUN.setBackground(new java.awt.Color(0, 0, 0));
        txtUN.setForeground(new java.awt.Color(255, 204, 0));
        txtUN.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(255, 204, 0)));
        JPblackband.add(txtUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 152, 210, -1));

        btnChange.setBackground(new java.awt.Color(0, 0, 0));
        btnChange.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnChange.setForeground(new java.awt.Color(255, 204, 0));
        btnChange.setText("Update");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });
        JPblackband.add(btnChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, -1, -1));
        JPblackband.add(lblFNerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 88, -1, -1));
        JPblackband.add(lblLNerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 182, -1, -1));
        JPblackband.add(lblGENerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 287, -1, -1));
        JPblackband.add(lblDOBerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 391, -1, -1));
        JPblackband.add(lblUNerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 182, -1, -1));
        JPblackband.add(lblPWDerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 298, -1, -1));
        JPblackband.add(lblPOSerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        btnReturn.setBackground(new java.awt.Color(0, 0, 0));
        btnReturn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 204, 0));
        btnReturn.setText("Return");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        JPblackband.add(btnReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, -1, -1));

        btnDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 204, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        JPblackband.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, -1, -1));

        lblEye.setIcon(new javax.swing.ImageIcon("C:\\Users\\eliznab74\\Documents\\NetBeansProjects\\IT PAT\\closed.png")); // NOI18N
        lblEye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEyeMouseClicked(evt);
            }
        });
        JPblackband.add(lblEye, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, -1, -1));

        JPbackground.add(JPblackband, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 731, 420));

        JPblueband.setBackground(new java.awt.Color(0, 0, 102));
        JPblueband.setForeground(new java.awt.Color(0, 0, 102));

        WhiteBand.setBackground(new java.awt.Color(255, 255, 255));
        WhiteBand.setForeground(new java.awt.Color(255, 255, 255));

        lblSchoolName.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblSchoolName.setForeground(new java.awt.Color(0, 0, 102));
        lblSchoolName.setText("ENJABULWENI SCHOOL");

        lblSchoolMotto.setFont(new java.awt.Font("Edwardian Script ITC", 0, 30)); // NOI18N
        lblSchoolMotto.setForeground(new java.awt.Color(0, 0, 0));
        lblSchoolMotto.setText("\"Sifundza Kanyekanye\" - Together we Learn");

        lblBadge.setIcon(new javax.swing.ImageIcon("C:\\Users\\eliznab74\\Pictures\\Badge.jpg")); // NOI18N

        lblHelp.setForeground(new java.awt.Color(0, 0, 0));
        lblHelp.setText("Help");
        lblHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHelpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout WhiteBandLayout = new javax.swing.GroupLayout(WhiteBand);
        WhiteBand.setLayout(WhiteBandLayout);
        WhiteBandLayout.setHorizontalGroup(
            WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WhiteBandLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBadge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSchoolMotto)
                            .addComponent(lblSchoolName)))
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblHelp)))
                .addContainerGap())
        );
        WhiteBandLayout.setVerticalGroup(
            WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WhiteBandLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addComponent(lblBadge, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblSchoolName)
                        .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WhiteBandLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHelp)
                                .addGap(26, 26, 26))
                            .addGroup(WhiteBandLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSchoolMotto)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout JPbluebandLayout = new javax.swing.GroupLayout(JPblueband);
        JPblueband.setLayout(JPbluebandLayout);
        JPbluebandLayout.setHorizontalGroup(
            JPbluebandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(WhiteBand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JPbluebandLayout.setVerticalGroup(
            JPbluebandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPbluebandLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(WhiteBand, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPbackground.add(JPblueband, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 240));

        JPyellowband.setBackground(new java.awt.Color(255, 204, 0));
        JPyellowband.setForeground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout JPyellowbandLayout = new javax.swing.GroupLayout(JPyellowband);
        JPyellowband.setLayout(JPyellowbandLayout);
        JPyellowbandLayout.setHorizontalGroup(
            JPyellowbandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        JPyellowbandLayout.setVerticalGroup(
            JPyellowbandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        JPbackground.add(JPyellowband, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 731, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPbackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPbackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        // TODO add your handling code here:
        
        Color r = new Color(156, 0, 0);
        Color g = new Color(15, 166, 7);
        
        Border a = BorderFactory.createMatteBorder(1, 1, 3, 1, g);
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, r);
        
        int opt = JOptionPane.showConfirmDialog(null, "You are about to Change elements of your profile. Do you wish to continue?", "Edit", JOptionPane.YES_NO_OPTION);
        if(opt == 0)
        {
            //First Name Validation Checks
            //Presence Check
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

            //Position Validation Checks
            //Presence Check
            //if position has not been selected
            if((cmbPos.getSelectedItem().toString()).equals("Position"))
            {
                //error message to be displayed if position has not been selecetd
                lblPOSerr.setText("Position must be selected*");
                //set label red to indicate error
                lblPOSerr.setForeground(r);
            }
            //if position has been selected
            else
            {
                //display green stars to indicate correct input
                lblPOSerr.setText("****");
                //set label to green to indicate correct input
                lblPOSerr.setForeground(g);
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
                    //if age less than 17 years or greater than 70 years
                    if(age.getYears() < 17 || age.getYears() > 70)
                    {
                        //message to be displayed if age less than 17 years
                        lblDOBerr.setText("Date of Birth out of range*");
                        //set label to red to indicate error
                        lblDOBerr.setForeground(r);
                    }
                    //if no errors are present
                    else
                    {
                        //display stars to indicate correct input
                        lblDOBerr.setText("****");
                        //set label to green to indicate correct input
                        lblDOBerr.setForeground(g);
                    }
                }
            }
            
            //Username Checks
            //Presence Check
            //if username field is empty
            if(txtUN.getText().isEmpty())
            {
                //message to be dispalyed if username field is empty
                lblUNerr.setText("Username must be entered*");
                //set label and field to red to indicate error
                lblUNerr.setForeground(r);
                txtUN.setBorder(b);
                txtUN.setForeground(r);
            }
            //if field is not empty
            else
            {      
                //Length check
                //if input is greater than 50 characters
                if(txtUN.getText().length() > 50)
                {
                    //message to be displayed if inout is greater than 50 characters
                    lblUNerr.setText("Ensure Username has less than 50 characters*");
                    //set label and field to red to indicate error
                    lblUNerr.setForeground(r);
                    txtUN.setBorder(b);
                    txtUN.setForeground(r);
                }
                //if input is less than 5 characters
                else if(txtUN.getText().length() < 5)
                {
                    //message to be displayed if input is less than 5 characters
                    lblUNerr.setText("Ensure Username has more than 5 characters*");
                    //set label and field to red to indicate error
                    lblUNerr.setForeground(r);
                    txtUN.setBorder(b);
                    txtUN.setForeground(r);
                }
                //if not errors are present
                else
                {
                    //display starts to indicate appropriate input
                    lblUNerr.setText("****");
                    //set label and field to green to indicate appropriate input
                    lblUNerr.setForeground(g);
                    txtUN.setBorder(a);
                    txtUN.setForeground(g);
                }
            }
            
            //Password Checks
            //Presence Check
            //if password field is empty
            if(pfPass.getText().isEmpty())
            {
                //message to be displayed if password field is empty
                lblPWDerr.setText("Password must be entered*");
                //set label and field to red to indicate error
                lblPWDerr.setForeground(r);
                pfPass.setBorder(b);
                pfPass.setForeground(r);
            }
            //if password field is not empty
            else
            {       
                //Length check
                //if input is greater than 50 characters
                if(pfPass.getText().length() > 50)
                {
                    //message to be displayed if input is greater than 50 characters
                    lblPWDerr.setText("Ensure Password has less than 50 characters*");
                    //set label and field to red to indicate error
                    lblLNerr.setForeground(r);
                    pfPass.setBorder(b);
                    pfPass.setForeground(r);
                }
                //if input is less than 7 characters
                else if(pfPass.getText().length() < 7)
                {
                    //message to be displayed if input is less than 7 characters
                    lblPWDerr.setText("Ensure Password has more than 7 characters*");
                    //set label and field to red to indicate error
                    lblPWDerr.setForeground(r);
                    pfPass.setBorder(b);
                    pfPass.setForeground(r);
                }
                //if no errors are present
                else
                {
                    //display stars to indicate appropriate input
                    lblPWDerr.setText("****");
                    //set label and field to green to indicate appropriate input
                    lblPWDerr.setForeground(g);
                    pfPass.setBorder(a);
                    pfPass.setForeground(g);
                }
            }

            //If all data is valid
            if(lblUNerr.getText().equals("****") && lblFNerr.getText().equals("****") && lblLNerr.getText().equals("****") && lblPWDerr.getText().equals("****") && lblGENerr.getText().equals("****") && lblDOBerr.getText().equals("****") && lblPOSerr.getText().equals("****"))
            {
                //Update databases
                try
                {
                    //get connection to database
                    con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                   
                    //read temp.txt file
                    try(Scanner sc = new Scanner(new FileReader("temp.txt")))
                    {
                        String line = sc.nextLine();
                        try(Scanner ac = new Scanner(line).useDelimiter("#"))
                        {
                            String un = ac.next();
                            String p = ac.next();
                            String pos = ac.next();
                            //create login object
                            Login log = new Login(un, p, pos);
                    
                            //message asking user if they wish to update their security question and answer
                            int i = JOptionPane.showConfirmDialog(null, "Do you wish to update your security question?", "Security Question", JOptionPane.YES_NO_OPTION);
                            //if user clicks yes
                            if(i == 0)
                            {
                                //ask for new question and answer
                                String s = JOptionPane.showInputDialog(null, "Enter new Security Question");
                                String ss = JOptionPane.showInputDialog(null, "Enter new Answer");

                                String query2 = "UPDATE security SET `Username` = ?, `Question` = ?, `Answer` = ? WHERE Username = '" + log.getUsername() + "'";
                                ps = con.prepareStatement(query2);

                                //update table 'security'
                                ps.setString(1, txtUN.getText());
                                ps.setString(2, s);
                                ps.setString(3, ss);

                                ps.executeUpdate();
                            }
                            
                            String query = "UPDATE accountdetails SET `Username`= ?, `Password`= ?, `Firstname`= ?, `Lastname`= ?, `Gender`= ?, `DateOfBirth`= ?, `Position`= ? WHERE `Username` = '" + log.getUsername() + "'";
                            pst = con.prepareStatement(query);
                            
                            //update data in textfile
                            log.setUsername(txtUN.getText());
                            log.setPassword(pfPass.getText());
                            log.setPosition(cmbPos.getSelectedItem().toString());
                            
                            try (PrintWriter px = new PrintWriter(new FileWriter(new File("temp.txt"))))
                            {
                                String q = log.getUsername() + "#" + log.getPassword() + "#" + log.getPosition();
                                px.print(q);
                            }
                            
                            //update data in database 'accountdetails'
                            pst.setString(1, txtUN.getText());
                            pst.setString(2, pfPass.getText());
                            pst.setString(3, txtFName.getText());
                            pst.setString(4, txtLName.getText());
                    
                            if(cbMale.isSelected())
                            {
                                gender = "Male";
                            }
                            else
                            {
                                gender = "Female";
                            }
                    
                            pst.setString(5, gender);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            pst.setString(6, sdf.format(jdcDOB.getDate()));
                            pst.setString(7, cmbPos.getSelectedItem().toString());
                            pst.executeUpdate();

                            //display after successful update
                            JOptionPane.showMessageDialog(null, "Profile Updated Successfully");
                            //set menu page visible
                            MainMenu menu =  new MainMenu();
                            menu.setVisible(true);
                            //remove current page from view
                            setVisible(false);
                        }
                    }
                }
                catch(HeadlessException | SQLException  | IOException ex)
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
    }//GEN-LAST:event_btnChangeActionPerformed

    //method to change colour of all fields and labels
    private void changeColor(String s, Color g, Border bb)
    {
        lblFNerr.setText(s);
        lblFNerr.setForeground(g);
        lblLNerr.setText(s);
        lblLNerr.setForeground(g);
        lblGENerr.setText(s);
        lblGENerr.setForeground(g);
        lblDOBerr.setText(s);
        lblDOBerr.setForeground(g);
        lblPOSerr.setText(s);
        lblPOSerr.setForeground(g);
        lblUNerr.setText(s);
        lblUNerr.setForeground(g);
        lblPWDerr.setText(s);
        lblPWDerr.setForeground(g);
        
        txtUN.setForeground(g);
        txtUN.setBorder(bb);
        txtFName.setForeground(g);
        txtFName.setBorder(bb);
        txtLName.setForeground(g);
        txtLName.setBorder(bb);
        pfPass.setForeground(g);
        pfPass.setBorder(bb);
        cbMale.setForeground(g);
        cbFemale.setForeground(g);
        btnDelete.setForeground(g);
        btnChange.setForeground(g);
        btnReturn.setForeground(g);
    }
    
    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        MainMenu menu =  new MainMenu();
        //set main menu screen to be visible
        menu.setVisible(true);
        //remove current screen from view
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void lblHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseClicked
        // TODO add your handling code here:
        //display help screen
        Help h = new Help();
        h.setVisible(true);
    }//GEN-LAST:event_lblHelpMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        //colours used to indicate delete action or to change colours back to normal if delete action is aborted
        Color red = new Color(156, 0, 0);
        Color yellow = new Color(255, 204, 0);
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, red);
        Border a = BorderFactory.createMatteBorder(1, 1, 3, 1, yellow);
        
        //make all fields and labels turn red
        changeColor("****", red, b);
        
        //message asking user if they want to delete their profile
        int opt = JOptionPane.showConfirmDialog(null, "You are about to Delete your profile. Do you wish to continue?", "Delete", JOptionPane.YES_NO_OPTION);
        //if user clicks yes
        if(opt == 0)
        {
            //begin try-catch
            try
            {
                //get connection to database
                con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                String query = "DELETE FROM accountdetails WHERE Username = '" + txtUN.getText() + "'";
                String query2 = "DELETE FROM security WHERE Username = '" + txtUN.getText() + "'";
                //delete data for user in both tables 'security' and 'accountdetails'
                pst = con.prepareStatement(query);
                ps = con.prepareStatement(query2);
                pst.executeUpdate();
                ps.executeUpdate();
                clear("", yellow, a);
                JOptionPane.showMessageDialog(null, "Deleted Successfuly");
                
                //remove data from text file
                //begin try-catch
                try
                {
                    try (PrintWriter px = new PrintWriter(new FileWriter(new File("temp.txt"))))
                    {
                        px.print("username#password#position");
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
                //end of try-catch
                LoginPage log = new LoginPage();
                //set login page visible
                log.setVisible(true);
                //remove current screen from view
                setVisible(false);
            }
            catch(HeadlessException | SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //if user clicks no
        else
        {
            changeColor("", yellow, a);
            JOptionPane.showMessageDialog(null, "Delete Action Aborted");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void pfPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfPassKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pfPassKeyTyped

    private void lblEyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeMouseClicked
        // TODO add your handling code here:
        //images for password eye
        ImageIcon open = new ImageIcon("open.png");
        ImageIcon closed = new ImageIcon("closed.png");
        
        if(lblEye.getIcon().toString().endsWith("closed.png"))
        {
            lblEye.setIcon(open);
            pfPass.setEchoChar((char)0);
        }
        else
        {
            lblEye.setIcon(closed);
            pfPass.setEchoChar('*');
        }
    }//GEN-LAST:event_lblEyeMouseClicked

    //clear method
    private void clear(String s, Color g, Border bb)
    {
        txtFName.setText(s);
        txtLName.setText(s);
        txtUN.setText(s);
        pfPass.setText(s);
        cbMale.setSelected(false);
        cbFemale.setSelected(false);
        cmbPos.setSelectedIndex(0);
        jdcDOB.setDate(null);
        
        changeColor(s, g, bb);
    }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EditProfile().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPbackground;
    private javax.swing.JPanel JPblackband;
    private javax.swing.JPanel JPblueband;
    private javax.swing.JPanel JPyellowband;
    private javax.swing.JPanel WhiteBand;
    private javax.swing.JToggleButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReturn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbFemale;
    private javax.swing.JCheckBox cbMale;
    private javax.swing.JComboBox<String> cmbPos;
    private com.toedter.calendar.JDateChooser jdcDOB;
    private javax.swing.JLabel lblBadge;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDOBerr;
    private javax.swing.JLabel lblEye;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblFNerr;
    private javax.swing.JLabel lblGENerr;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblLName;
    private javax.swing.JLabel lblLNerr;
    private javax.swing.JLabel lblPOSerr;
    private javax.swing.JLabel lblPWDerr;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblSchoolMotto;
    private javax.swing.JLabel lblSchoolName;
    private javax.swing.JLabel lblUNerr;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField pfPass;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextField txtUN;
    // End of variables declaration//GEN-END:variables
}
