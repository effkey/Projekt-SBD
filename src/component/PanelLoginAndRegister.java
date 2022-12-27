package component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
// Brane z maven
import net.miginfocom.swing.MigLayout;
import packageSac.Button;
import packageSac.MyPasswordField;
import packageSac.MyTextField;

/**
 *
 * @author gs
 * 
 * Odpowiada za panel z boku z gradientem
 */
public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    
    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));  // push[center] aby wszystko do środka kolumny w layoucie, push[] do środka rzędu
        JLabel label = new JLabel("Utwórz konto");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(196, 53, 53));    // 196, 53, 53 - czerwony do tekstu
        register.add(label);
        
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon("user.png"));
        txtUser.setHint("Nazwa użytkownika");
        register.add(txtUser, "w 60%");
        
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("email.png"));
        txtEmail.setHint("E-mail");
        register.add(txtEmail, "w 60%");
        
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("password.png"));
        txtPass.setHint("Hasło");
        register.add(txtPass, "w 60%");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(196, 53, 53));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Stwórz konto");
        register.add(cmd, "w 40%, h 40");
    }
    
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Zaloguj się");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(196, 53, 53));    // 196, 53, 53 - czerwony do tekstu
        login.add(label);
        
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("email.png"));
        txtEmail.setHint("E-mail");
        login.add(txtEmail, "w 60%");
        
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("password.png"));
        txtPass.setHint("Hasło");
        login.add(txtPass, "w 60%");
        
        JButton cmdForget = new JButton("Nie pamiętasz hasła?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        
        Button cmd = new Button();
        cmd.setBackground(new Color(196, 53, 53));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Zaloguj się");
        login.add(cmd, "w 40%, h 40");
    }
    
    public void showRegister(boolean show) {
        if(show) {
            register.setVisible(true);
            login.setVisible(false);
        }
        else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
