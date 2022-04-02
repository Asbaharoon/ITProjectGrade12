package PAT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 * @author Banzile Nhlebela
 */
public class LoginPage extends javax.swing.JFrame {

    private Connection con = null;
    private PreparedStatement pst = null;
    
    /**
     * Creates new form LoginPage
     */
    
    public LoginPage() 
    {
        initComponents();
        //code to make screen appear in center of device screen
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2, size.height/2-getHeight()/2);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        blueBand = new javax.swing.JPanel();
        WhiteBand = new javax.swing.JPanel();
        lblSchoolName = new javax.swing.JLabel();
        lblMotto = new javax.swing.JLabel();
        lblBadge = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        YellowBand = new javax.swing.JPanel();
        lblFgtPwd = new javax.swing.JLabel();
        lblUN = new javax.swing.JLabel();
        lblPwd = new javax.swing.JLabel();
        txtUN = new javax.swing.JTextField();
        btnCNA = new javax.swing.JButton();
        btnLogIn = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        pfPwd = new javax.swing.JPasswordField();
        lblUNerr = new javax.swing.JLabel();
        lblPwdErr = new javax.swing.JLabel();
        cbSP = new javax.swing.JCheckBox();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setName("Login Page"); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        blueBand.setBackground(new java.awt.Color(0, 0, 102));
        blueBand.setForeground(new java.awt.Color(0, 0, 102));
        blueBand.setPreferredSize(new java.awt.Dimension(731, 243));

        WhiteBand.setBackground(new java.awt.Color(255, 255, 255));
        WhiteBand.setForeground(new java.awt.Color(255, 255, 255));
        WhiteBand.setPreferredSize(new java.awt.Dimension(731, 216));

        lblSchoolName.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblSchoolName.setForeground(new java.awt.Color(0, 0, 102));
        lblSchoolName.setText("ENJABULWENI SCHOOL");

        lblMotto.setFont(new java.awt.Font("Edwardian Script ITC", 0, 30)); // NOI18N
        lblMotto.setForeground(new java.awt.Color(0, 0, 0));
        lblMotto.setText("\"Sifundza Kanyekanye\" - Together we Learn");

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
                .addComponent(lblBadge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMotto)
                            .addComponent(lblSchoolName)))
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblHelp)))
                .addGap(501, 501, 501))
        );
        WhiteBandLayout.setVerticalGroup(
            WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WhiteBandLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBadge, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(WhiteBandLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblSchoolName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(WhiteBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(WhiteBandLayout.createSequentialGroup()
                                .addComponent(lblMotto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WhiteBandLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblHelp)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout blueBandLayout = new javax.swing.GroupLayout(blueBand);
        blueBand.setLayout(blueBandLayout);
        blueBandLayout.setHorizontalGroup(
            blueBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(WhiteBand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1227, Short.MAX_VALUE)
        );
        blueBandLayout.setVerticalGroup(
            blueBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blueBandLayout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(WhiteBand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(blueBand, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1205, -1));

        YellowBand.setBackground(new java.awt.Color(255, 204, 0));

        lblFgtPwd.setForeground(new java.awt.Color(0, 0, 0));
        lblFgtPwd.setText("Forgot Password");
        lblFgtPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFgtPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFgtPwdMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout YellowBandLayout = new javax.swing.GroupLayout(YellowBand);
        YellowBand.setLayout(YellowBandLayout);
        YellowBandLayout.setHorizontalGroup(
            YellowBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, YellowBandLayout.createSequentialGroup()
                .addContainerGap(626, Short.MAX_VALUE)
                .addComponent(lblFgtPwd)
                .addGap(110, 110, 110))
        );
        YellowBandLayout.setVerticalGroup(
            YellowBandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(YellowBandLayout.createSequentialGroup()
                .addComponent(lblFgtPwd)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel1.add(YellowBand, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 830, 20));

        lblUN.setForeground(new java.awt.Color(0, 0, 0));
        lblUN.setText("Username");
        jPanel1.add(lblUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        lblPwd.setForeground(new java.awt.Color(0, 0, 0));
        lblPwd.setText("Password");
        jPanel1.add(lblPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, -1));

        txtUN.setBackground(new java.awt.Color(0, 0, 0));
        txtUN.setForeground(new java.awt.Color(255, 204, 0));
        txtUN.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(255, 204, 0)));
        txtUN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUNKeyTyped(evt);
            }
        });
        jPanel1.add(txtUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 220, 20));

        btnCNA.setBackground(new java.awt.Color(0, 0, 0));
        btnCNA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCNA.setForeground(new java.awt.Color(255, 204, 0));
        btnCNA.setText("Create New Account");
        btnCNA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNAActionPerformed(evt);
            }
        });
        jPanel1.add(btnCNA, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 590, -1, -1));

        btnLogIn.setBackground(new java.awt.Color(0, 0, 0));
        btnLogIn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLogIn.setForeground(new java.awt.Color(255, 204, 0));
        btnLogIn.setText("LogIn");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, -1, -1));

        btnExit.setBackground(new java.awt.Color(0, 0, 0));
        btnExit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 204, 0));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));

        pfPwd.setBackground(new java.awt.Color(0, 0, 0));
        pfPwd.setForeground(new java.awt.Color(255, 204, 0));
        pfPwd.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 1, new java.awt.Color(255, 204, 0)));
        jPanel1.add(pfPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 220, -1));
        jPanel1.add(lblUNerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, -1, -1));
        jPanel1.add(lblPwdErr, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        cbSP.setBackground(new java.awt.Color(255, 255, 255));
        cbSP.setForeground(new java.awt.Color(0, 0, 0));
        cbSP.setText("Show Password");
        cbSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSPActionPerformed(evt);
            }
        });
        jPanel1.add(cbSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 120, -1));

        lblBackground.setIcon(new javax.swing.ImageIcon("C:\\Users\\eliznab74\\Pictures\\R2.jpeg")); // NOI18N
        jPanel1.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 730, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //clear method
    private void clear()
    {
        txtUN.setText("");
        pfPwd.setText("");
        lblUNerr.setText("");
        lblPwdErr.setText("");
    }
    
    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        // TODO add your handling code here:
        
        //Colours and borders to be used for indication of correct and incorrect information entry
        Color r = new Color(156, 0, 0);
        Color g = new Color(15, 166, 7);
        
        Border a = BorderFactory.createMatteBorder(1, 1, 3, 1, g);
        Border b = BorderFactory.createMatteBorder(1, 1, 3, 1, r);
        
        //Username Presence Check
        if(txtUN.getText().isEmpty())
        {
            //Error message if field is empty
            lblUNerr.setText("Enter User Name*");
            //Change colour of field and label to red
            lblUNerr.setForeground(r);
            txtUN.setBorder(b);
            txtUN.setForeground(r);
            txtUN.requestFocus();
        }
        else
        {
            //Message displayed if field is not empty
            lblUNerr.setText("****");
            //Set colour of label to green
            lblUNerr.setForeground(g);
        }
        
        //Password Presence Check
        if(pfPwd.getText().isEmpty())
        {
            //Error message if field is empty
            lblPwdErr.setText("Enter Password*");
            //Change colour of field and label to red
            lblPwdErr.setForeground(r);
            pfPwd.setBorder(b);
            pfPwd.setForeground(r);
        }
        else
        {
            //Message displayed if field is not empty
            lblPwdErr.setText("****");
            //Set colour of label to green
            lblPwdErr.setForeground(g);
        }
        
        //If both fields are not empty
        if(lblPwdErr.getText().equals("****") && lblUNerr.getText().equals("****"))
        {
            String query = "SELECT * FROM `accountdetails` WHERE `Username` = '" + txtUN.getText() + "' AND `Password` = '" + pfPwd.getText() + "'";
            //Begin try-catch
            try
            {
                //get connection to database
                con = DriverManager.getConnection("jdbc:mysql://localhost/consultations", "root", "");
                pst = con.prepareStatement(query);
                //get all data from database
                ResultSet rs = pst.executeQuery(query);
                MainMenu menu = new MainMenu();
                
                //If data is found in database/matches data in database
                if(rs.next())
                {
                    //Begin try-catch
                    try
                    {
                        //Write Username, Password, Position to new text file
                        try (PrintWriter px = new PrintWriter(new FileWriter(new File("temp.txt"))))
                        {
                            String s = txtUN.getText() + "#" + pfPwd.getText() + "#" + rs.getString("Position");
                            px.print(s);
                        }
                    }
                    catch(SQLException | IOException ex)
                    {
                        //Display if Exception appears
                        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //end of try-catch
                    
                    //Set fields to green colour to show successful login
                    lblUNerr.setForeground(g);
                    lblPwdErr.setForeground(g);
                    pfPwd.setBorder(a);
                    txtUN.setBorder(a);
                    pfPwd.setForeground(g);
                    txtUN.setForeground(g);
                    
                    //if user is male
                    if(rs.getString("Gender").equals("Male"))
                    { 
                       //Welcome message to be displayed when login is successful
                       JOptionPane.showMessageDialog(null, "Login Successful"+ "\nWelcome Mr. " + rs.getString("Lastname"), rs.getString("Position"), JOptionPane.INFORMATION_MESSAGE);
                    }
                    //if user is female
                    else
                    {
                        //Welcome message to be displayed when login is successful
                        JOptionPane.showMessageDialog(null, "Login Successful"+ "\nWelcome Ms. " + rs.getString("Lastname"), rs.getString("Position"), JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    //Show Main Menu screen
                    menu.setVisible(true);
                    //Remove current screen from view
                    this.dispose();
                }
                //If data is not found/ does not match data in database
                else
                {
                    //Set firlds and labels to red to indicate unsuccessful login
                    pfPwd.setBorder(b);
                    txtUN.setBorder(b);
                    pfPwd.setForeground(r);
                    txtUN.setForeground(r);
                    txtUN.requestFocus();
                    lblUNerr.setForeground(r);
                    lblPwdErr.setForeground(r);
                    //Message indicating unsuccessful login
                    JOptionPane.showMessageDialog(null, "Username or Password Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //close connection to database
                con.close();
            }
            catch(HeadlessException | SQLException ex)
            {
                //Message displaying exception
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                //set text of fields and lables to ""
                clear();
            }
            //end of try-catch
        }
        //if data is not valid
        else
        {
            //message indicating an error in input of information in fields
            JOptionPane.showMessageDialog(null, "Check if all has been entered correctly", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLogInActionPerformed

    private void lblFgtPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFgtPwdMouseClicked
        // TODO add your handling code here:
        //Set forgot password screen to be visible
        ForgotPassword fg = new ForgotPassword();
        fg.setVisible(true);
        //Close current screen
        this.dispose();
    }//GEN-LAST:event_lblFgtPwdMouseClicked

    private void btnCNAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNAActionPerformed
        // TODO add your handling code here:
        //Set New Account screen to be vivible
        NewAccount newAcc = new NewAccount();
        newAcc.setVisible(true);
        //Close current screen
        this.dispose();
    }//GEN-LAST:event_btnCNAActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        //Display confirmation message asking user if they want to exit
        int opt = JOptionPane.showConfirmDialog(null, "You are about to close this page. Do you wish to continue", "Exit", JOptionPane.YES_NO_OPTION);
        //if user clicks yes
        if(opt == 0)
        {
            //Close current screen
            this.dispose();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void lblHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseClicked
        // TODO add your handling code here:
        //Display help screen
        Help h = new Help();
        h.setVisible(true);
    }//GEN-LAST:event_lblHelpMouseClicked

    private void txtUNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUNKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtUNKeyTyped

    private void cbSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSPActionPerformed
        // TODO add your handling code here:
        //if 'show password' check box is selected
        if(cbSP.isSelected())
        {
            //reveal password
            pfPwd.setEchoChar((char)0);
        }
        //if it is not selected
        else
        {
            //conceal password using asterisks
            pfPwd.setEchoChar('*');
        }
    }//GEN-LAST:event_cbSPActionPerformed

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>..0

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel WhiteBand;
    private javax.swing.JPanel YellowBand;
    private javax.swing.JPanel blueBand;
    private javax.swing.JButton btnCNA;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JCheckBox cbSP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBadge;
    private javax.swing.JLabel lblFgtPwd;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblMotto;
    private javax.swing.JLabel lblPwd;
    private javax.swing.JLabel lblPwdErr;
    private javax.swing.JLabel lblSchoolName;
    private javax.swing.JLabel lblUN;
    private javax.swing.JLabel lblUNerr;
    private javax.swing.JPasswordField pfPwd;
    private javax.swing.JTextField txtUN;
    // End of variables declaration//GEN-END:variables
}
