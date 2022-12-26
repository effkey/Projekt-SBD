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
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import view.SacPackage.Button;


/**
 *
 * @author gs
 */
public class OrderPanel extends javax.swing.JPanel {
    
    public OrderPanel() {
        initComponents();
        init();
    }

    private void init() {
        this.setBackground(Color.BLACK);
        
        JLabel adLabel = new JLabel("Dodaj adres lub wybierz z listy:");
        adLabel.setFont(new Font("sansserif", 1, 36));
        adLabel.setForeground(Color.WHITE);
        Dimension size = adLabel.getPreferredSize();
        adLabel.setBounds(150, 100, size.width, size.height);
        this.add(adLabel);
        
        MyTextField txtCity = new MyTextField();
        txtCity.setHint("Miasto");
        txtCity.setBackground(Color.WHITE);
        txtCity.setBounds(150, 170, 400, 45);
        this.add(txtCity);
        
        MyTextField txtStreet = new MyTextField();
        txtStreet.setHint("Ulica");
        txtStreet.setBackground(Color.WHITE);
        txtStreet.setBounds(150, 230, 400, 45);
        this.add(txtStreet);
        
        MyTextField txtCode = new MyTextField();
        txtCode.setHint("Kod pocztowy");
        txtCode.setBackground(Color.WHITE);
        txtCode.setBounds(150, 290, 400, 45);
        this.add(txtCode);
        
        MyTextField txtNumber = new MyTextField();
        txtNumber.setHint("Numer budynku");
        txtNumber.setBackground(Color.WHITE);
        txtNumber.setBounds(150, 350, 400, 45);
        this.add(txtNumber);
        
        MyTextField txtNrlocal = new MyTextField();
        txtNrlocal.setHint("Numer lokalu (jeśli brak, pozostaw puste)");
        txtNrlocal.setBackground(Color.WHITE);
        txtNrlocal.setBounds(150, 410, 400, 45);
        this.add(txtNrlocal);
        
        Button addAdress = new Button();
        addAdress.setBackground(new Color(196, 53, 53));
        addAdress.setForeground(new Color(250, 250, 250));
        addAdress.setText("Dodaj adres do listy");
        addAdress.setBounds(150, 475, 140, 35);
        this.add(addAdress);
        
        addAdress.addActionListener(new ActionListener() {  // tutaj dodać wpisany adres do bazy
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dodano adres do listy adresów.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        JComboBox<String> list = new JComboBox<String>();
        list.addItem("Adres 1");    // tutaj trzeba będzie zmienić aby brało adresy z bazy
        list.addItem("Adres 2");
        size = list.getPreferredSize();
        list.setBounds(150, 540, 300, size.height);
        this.add(list);
        
        list.addItemListener(new ItemListener() {   // wybrany adres przypisz do zamówienia
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("wybrane");
            }
        });
        
        JLabel delivLabel = new JLabel("Wybierz sposób dostawy:");
        delivLabel.setFont(new Font("sansserif", 1, 36));
        delivLabel.setForeground(Color.WHITE);
        size = delivLabel.getPreferredSize();
        delivLabel.setBounds(150, 610, size.width, size.height);
        this.add(delivLabel);
        
        JRadioButton kurier = new JRadioButton("Kurier");
        JRadioButton odbior = new JRadioButton("Odbiór osobisty");
        kurier.setForeground(Color.WHITE);
        odbior.setForeground(Color.WHITE);
        ButtonGroup dostawa = new ButtonGroup();    // pilnuje aby tylko jedna opcja mogła być zaznaczona
        dostawa.add(kurier);
        dostawa.add(odbior);
        size = kurier.getPreferredSize();
        kurier.setBounds(150, 690, size.width, size.height);
        size = odbior.getPreferredSize();
        odbior.setBounds(150, 730, size.width, size.height);
        this.add(kurier);
        this.add(odbior);
        
        kurier.addActionListener(new ActionListener() {     // kurier jako wybrany sposób dostawy dodaj do zamówienia
            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("wybrane"); 
            }
        });
        
        odbior.addActionListener(new ActionListener() {     // odbiór jako wybrany sposób dostawy dodaj do zamówienia
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("wybrane");
            }
        });
        
        Button orderButton = new Button();
        orderButton.setBackground(new Color(196, 53, 53));
        orderButton.setForeground(new Color(250, 250, 250));
        orderButton.setText("Złóż zamówienie");
        orderButton.setBounds(150, 780, 180, 35);
        this.add(orderButton, "w 40%, h 40");
        
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // tutaj będzie trzeba dodać funkcjonalność związaną z dodaniem do listy zamówień danego zamówienia, powrót do widoku sklepu
                JOptionPane.showMessageDialog(null, "Dodano zamówienie do listy zamówień.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        Button backButton = new Button();
        backButton.setBackground(new Color(196, 53, 53));
        backButton.setForeground(new Color(250, 250, 250));
        backButton.setText("Anuluj składanie zamówienia");
        backButton.setBounds(350, 780, 200, 35);
        this.add(backButton);
        
        backButton.addActionListener(new ActionListener() {     // po naciśnięciu powrót do głównej strony sklepu
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("wybrane");
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
