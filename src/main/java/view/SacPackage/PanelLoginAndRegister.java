package view.SacPackage;

import dao.UzytkownikDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import map.Uzytkownik;
// Brane z maven
import net.miginfocom.swing.MigLayout;
// Klasy rozszerzające
import view.SacPackage.Button;
import view.MainFrame;
import view.SacPackage.MyPasswordField;
import view.SacPackage.MyTextField;
/**
 *
 * @author gs
 * 
 * Odpowiada za panel z boku z gradientem
 */
public class PanelLoginAndRegister extends JPanel {
    
    private MyPasswordField txtPassLog;
    private MyTextField txtUserLog;
    
    private MyPasswordField txtPassReg;
    
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
        txtUser.setPrefixIcon(new ImageIcon("user_login.png"));
        txtUser.setHint("Nazwa użytkownika");
        register.add(txtUser, "w 60%");
        
        MyTextField txtname = new MyTextField();
        txtname.setPrefixIcon(new ImageIcon("user_login.png"));
        txtname.setHint("Imie");
        register.add(txtname, "w 60%");
        
        MyTextField txtsurname = new MyTextField();
        txtsurname.setPrefixIcon(new ImageIcon("user_login.png"));
        txtsurname.setHint("Nazwisko");
        register.add(txtsurname, "w 60%");
        
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("email.png"));
        txtEmail.setHint("E-mail");
        register.add(txtEmail, "w 60%");
        
        txtPassReg = new MyPasswordField();
        txtPassReg.setPrefixIcon(new ImageIcon("password.png"));
        txtPassReg.setHint("Hasło");
        register.add(txtPassReg, "w 60%");
        
        view.MyPasswordField txtPass2 = new view.MyPasswordField();
        txtPass2.setPrefixIcon(new ImageIcon("password.png"));
        txtPass2.setHint("Powtorz haslo");
        register.add(txtPass2, "w 60%");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(196, 53, 53));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Stwórz konto");
        register.add(cmd, "w 40%, h 40");
        
                cmd.addActionListener(new ActionListener() {    // narazie po wciśnięciu przycisku "Zaloguj się" przechodzi do sklepu!! //juz nie
            public void actionPerformed(ActionEvent e) {
                
                UzytkownikDao dao = new UzytkownikDao();
                Uzytkownik user = new Uzytkownik();
                String login = txtUser.getText(), pass = txtPassReg.getText(), name = txtname.getText(), surname = txtsurname.getText(), email = txtEmail.getText(), pass2 = txtPass2.getText();
                if(!"".equals(login) && !"".equals(pass) && !"".equals(name) && !"".equals(surname) && !"".equals(email) && pass.equals(pass2) )
                {
                    user = dao.addUser(name,  surname,  login,   pass, new Date(),  email );

                   //user = dao.getUser(pass, login);
                   
                }
            }
        });
    }
    
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Zaloguj się");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(196, 53, 53));    // 196, 53, 53 - czerwony do tekstu
        login.add(label);
        
        txtUserLog = new MyTextField();
//        txtUserLog.setPrefixIcon(new ImageIcon("email.png"));
        txtUserLog.setHint("Nazwa Użytkownika");
        login.add(txtUserLog, "w 60%");
        
        txtPassLog = new MyPasswordField();
        txtPassLog.setPrefixIcon(new ImageIcon("password.png"));
        txtPassLog.setHint("Hasło");
        login.add(txtPassLog, "w 60%");
        
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
        
        cmd.addActionListener(new ActionListener() {    // narazie po wciśnięciu przycisku "Zaloguj się" przechodzi do sklepu!! //juz nie
            public void actionPerformed(ActionEvent e) {
                
                UzytkownikDao dao = new UzytkownikDao();
                Uzytkownik user;
                String login = txtUserLog.getText(), pass = txtPassLog.getText();
                if(!"".equals(login) && !"".equals(pass))
                {
                    user = dao.getUser(pass, login);
                    if(user!=null){
                        MainFrame frame = (MainFrame) (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, (JComponent)e.getSource());  // zdobądź rodzica (czyli JFrame)
                        frame.getContentPane().removeAll();     // by usunąć wszystko co było na ekranie logowania
                        frame.setLayout(new BorderLayout());    // funkcjonalność starego konstruktora ShopFrame
                        frame.loadPanels(user);
                        frame.pack();
                        frame.invalidate();     // funkcje do odświeżenia frame
                        frame.validate();
                        frame.repaint();
                        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);   // Fullscreen
                    }
                }
            }
        });
    }
    
    // Z ShopFrame
//    
    //
    
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
