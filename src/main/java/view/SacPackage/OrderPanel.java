package view.SacPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


/**
 *
 * @author gs
 */
public class OrderPanel extends javax.swing.JPanel {

//    private PopUps popUps = new PopUps();
    
    public OrderPanel() {
        initComponents();
        init();
    }

    private void init() {
        this.setBackground(Color.BLACK);
        
        JLabel adLabel = new JLabel("Dodaj adres lub wybierz z listy:");
        adLabel.setFont(new Font("sansserif", 1, 30));
        adLabel.setForeground(Color.WHITE);
        Dimension size = adLabel.getPreferredSize();
        adLabel.setBounds(150, 100, size.width, size.height);
        this.add(adLabel);
        
        MyTextField txtCity = new MyTextField();
        txtCity.setHint("Miasto");
        txtCity.setBackground(Color.WHITE);
        size = txtCity.getPreferredSize();
        txtCity.setBounds(150, 150, 400, size.height);
        this.add(txtCity);
        
        MyTextField txtStreet = new MyTextField();
        txtStreet.setHint("Ulica");
        txtStreet.setBackground(Color.WHITE);
        size = txtStreet.getPreferredSize();
        txtStreet.setBounds(150, 200, 400, size.height);
        this.add(txtStreet);
        
        MyTextField txtCode = new MyTextField();
        txtCode.setHint("Kod pocztowy");
        txtCode.setBackground(Color.WHITE);
        size = txtCode.getPreferredSize();
        txtCode.setBounds(150, 250, 400, size.height);
        this.add(txtCode);
        
        MyTextField txtNumber = new MyTextField();
        txtNumber.setHint("Numer budynku");
        txtNumber.setBackground(Color.WHITE);
        size = txtNumber.getPreferredSize();
        txtNumber.setBounds(150, 300, 400, size.height);
        this.add(txtNumber);
        
        MyTextField txtNrlocal = new MyTextField();
        txtNrlocal.setHint("Numer lokalu (jeśli brak, pozostaw puste)");
        txtNrlocal.setBackground(Color.WHITE);
        size = txtNrlocal.getPreferredSize();
        txtNrlocal.setBounds(150, 350, 400, size.height);
        this.add(txtNrlocal);
        
        Button addAdress = new Button();
        addAdress.setBackground(new Color(196, 53, 53));
        addAdress.setForeground(new Color(250, 250, 250));
        addAdress.setText("Dodaj adres do listy");
        size = addAdress.getPreferredSize();
        addAdress.setBounds(150, 400, 140, size.height);
        this.add(addAdress);
        
        addAdress.addActionListener(new ActionListener() {  // tutaj dodać wpisany adres do bazy
            public void actionPerformed(ActionEvent e) {
//                popUps.addAdressPopUp();
            }
        });
        
        JComboBox<String> list = new JComboBox<String>();
        list.addItem("Adres 1");    // tutaj trzeba będzie zmienić aby brało adresy z bazy
        list.addItem("Adres 2");
        size = list.getPreferredSize();
        list.setBounds(150, 440, 300, size.height);
        this.add(list);
        
        list.addItemListener(new ItemListener() {   // wybrany adres przypisz do zamówienia
            public void itemStateChanged(ItemEvent e) {
                System.out.println("wybrane");
            }
        });
        
        JLabel delivLabel = new JLabel("Wybierz sposób dostawy:");
        delivLabel.setFont(new Font("sansserif", 1, 30));
        delivLabel.setForeground(Color.WHITE);
        size = delivLabel.getPreferredSize();
        delivLabel.setBounds(150, 490, size.width, size.height);
        this.add(delivLabel);
        
        JRadioButton kurier = new JRadioButton("Kurier");
        JRadioButton odbior = new JRadioButton("Odbiór osobisty");
        kurier.setForeground(Color.WHITE);
        odbior.setForeground(Color.WHITE);
        ButtonGroup dostawa = new ButtonGroup();    // pilnuje aby tylko jedna opcja mogła być zaznaczona
        dostawa.add(kurier);
        dostawa.add(odbior);
        size = kurier.getPreferredSize();
        kurier.setBounds(150, 540, size.width, size.height);
        size = odbior.getPreferredSize();
        odbior.setBounds(150, 560, size.width, size.height);
        this.add(kurier);
        this.add(odbior);
        
        kurier.addActionListener(new ActionListener() {     // kurier jako wybrany sposób dostawy dodaj do zamówienia
            public void actionPerformed(ActionEvent e) {
               System.out.println("wybrane"); 
            }
        });
        
        odbior.addActionListener(new ActionListener() {     // odbiór jako wybrany sposób dostawy dodaj do zamówienia
            public void actionPerformed(ActionEvent e) {
                System.out.println("wybrane");
            }
        });
        
        Button cmd = new Button();
        cmd.setBackground(new Color(196, 53, 53));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Złóż zamówienie");
        size = cmd.getPreferredSize();
        cmd.setBounds(150, 590, 180, size.height);
        this.add(cmd, "w 40%, h 40");
        
        cmd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    // tutaj będzie trzeba dodać funkcjonalność związaną z dodaniem do listy zamówień danego zamówienia, powrót do widoku sklepu
//                popUps.addOrderPopUp();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
