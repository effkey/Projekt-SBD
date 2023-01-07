package view.layouts;

import dao.AdresDao;
import dao.KategoriaDao;
import dao.MagazynDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.text.View;
import map.Adres;
import map.Magazyn;
import map.Produkt;
import view.Image;
import view.MainFrame;
import static view.layouts.ListPanel.scale;

public class WarehouseLayout extends JPanel implements ActionListener {

    public static final int borderPx = Toolkit.getDefaultToolkit().getScreenSize().width / 500;
    private Font font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (40 * scale));

    private JScrollPane scroll;
    private Cart mainPanel;

    private JPanel upPanel;
    private JButton returnButton;
    private JButton addMagazin;
    private JLabel logo;
    private JTextField cityField;
    private JTextField zipCodeField;
    private JTextField streetField;
    private JTextField buildNumField;
    private JTextField apartNumField;

    private JPanel categoryPanel = new JPanel();
    private JLabel quantity;
    private JLabel sum;
    private JLabel mass;
    private ArrayList<JButton> warehousesBut;
    private ArrayList<Magazyn> warehouses = new ArrayList<>();
    private JSpinner volumeSpinner;
    private int idxWarehouse = 0;

    public WarehouseLayout() {
        this.setLayout(null);

        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.WHITE);

        this.upPanel = new JPanel();
//        {
//            @Override
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.drawImage(Image.LOGO.icon.getImage(), Toolkit.getDefaultToolkit().getScreenSize().width / 2, -borderPx, null);
//            }
//        };
        this.categoryPanel = new JPanel();

//		this.categoryPanel.repaint();
        this.makeMainPanel();

        this.upPanel.setLayout(null);
        this.categoryPanel.setLayout(new FlowLayout(View.Y_AXIS));

        this.scroll = new JScrollPane(mainPanel);
//		this.scroll.add(mainPanel);
        this.scroll.setVisible(true);
        this.scroll.getVerticalScrollBar().setUnitIncrement(16);

        this.upPanel.setBounds(borderPx, borderPx, this.getPreferredSize().width - 2 * borderPx, this.getPreferredSize().height / 8);
        this.categoryPanel.setBounds(borderPx, this.getPreferredSize().height / 8 + 2 * ShopLayout.borderPx,
                this.getPreferredSize().width / 10, this.getPreferredSize().height - this.getPreferredSize().height / 8 - 8 * borderPx);
        this.scroll.setBounds(this.getPreferredSize().width / 10 + 2 * borderPx, this.getPreferredSize().height / 8 + 2 * borderPx,
                getPreferredSize().width - 3 * borderPx - this.getPreferredSize().width / 10, this.getPreferredSize().height - this.getPreferredSize().height / 8 - 8 * borderPx);

        makeCategoryPanel();
        makeUpPanel();

        this.add(upPanel);
        this.add(categoryPanel);
        this.add(scroll);
    }

    public void addProduct(Produkt produkt) {
        this.mainPanel.addProdukt(produkt, false);
    }

    public void refreshCategoryPanel() {
        float sumFloat = 0;
        int numOf = 0;
        float massFloat = 0;
        ArrayList<Produkt> products = (ArrayList<Produkt>) this.mainPanel.getProducts();
        ArrayList<Integer> numOfProducts = (ArrayList<Integer>) this.mainPanel.getNumOfProducts();
        int i = 0;
        for (Produkt p : products) {
            sumFloat += p.getCena() * (int) (numOfProducts.get(i));
            numOf += (int) (numOfProducts.get(i));
            massFloat += p.getMasa() * (int) (numOfProducts.get(i));
            i++;
        }
        quantity.setText(String.valueOf(numOf));
        sum.setText(String.valueOf(sumFloat));
        mass.setText(String.valueOf(massFloat));
    }

    public void makeCategoryPanel() {
        
        this.categoryPanel.setBackground(Color.black);
        
        addMagazin = new JButton("<html>" + "  Dodaj" + "<br>" + "magazyn" + "</html>");
        addMagazin.setPreferredSize(new Dimension(120, 60));
        addMagazin.addActionListener(this);
        addMagazin.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40)));
        sum = new JLabel();
        sum.setFont(font);
        sum.setForeground(Color.white);
        quantity = new JLabel();
        quantity.setFont(font);
        quantity.setForeground(Color.white);
        mass = new JLabel();
        mass.setFont(font);
        mass.setForeground(Color.white);

        JLabel tmp1 = new JLabel("Wartość:");
        tmp1.setFont(font);
        tmp1.setForeground(Color.white);

        JLabel tmp2 = new JLabel("Liczność:");
        tmp2.setFont(font);
        tmp2.setForeground(Color.white);

        JLabel tmp3 = new JLabel("Masa:");
        tmp3.setFont(font);
        tmp3.setForeground(Color.white);

        this.categoryPanel.add(addMagazin, BorderLayout.NORTH);
        this.categoryPanel.add(tmp1, BorderLayout.NORTH);
        this.categoryPanel.add(sum, BorderLayout.NORTH);
        this.categoryPanel.add(new JLabel());
        this.categoryPanel.add(tmp2, BorderLayout.NORTH);
        this.categoryPanel.add(quantity, BorderLayout.NORTH);
        this.categoryPanel.add(new JLabel());
        this.categoryPanel.add(tmp3, BorderLayout.NORTH);
        this.categoryPanel.add(mass, BorderLayout.NORTH);
        this.categoryPanel.add(new JLabel());

        MagazynDao dao = new MagazynDao();
        List<Magazyn> magazyny = dao.getAll();
        this.warehousesBut = new ArrayList<JButton>();
        for (Magazyn m : magazyny) {
            JButton temp = new JButton(m.toString());
            this.warehousesBut.add(temp);
            this.warehouses.add(m);
            temp.setBackground(Color.BLACK);
            temp.setForeground(Color.WHITE);
            temp.addActionListener((ActionEvent e) -> {
                if (e.getSource() == temp) {
                    for (JButton b : this.warehousesBut) {
                        b.setEnabled(true);
                    }
                    temp.setEnabled(false);
                    idxWarehouse = Integer.parseInt(temp.getText().substring(3))-1;
                    this.refreshUpPanel();
                }
            });
//            temp.setPreferredSize(new Dimension(categoryPanel.getSize().width, (int)(2*40*scale)));
            temp.setFont(font);
            this.categoryPanel.add(temp, BorderLayout.CENTER);
        }
        this.warehousesBut.get(this.idxWarehouse).setEnabled(false);
//        this.refreshUpPanel();
        this.refreshCategoryPanel();
    }

    private void makeMainPanel() {
        this.mainPanel = new Cart(new Dimension(getPreferredSize().width - 3 * borderPx - this.getPreferredSize().width / 10,
                this.getPreferredSize().height - this.getPreferredSize().height / 8 - 8 * borderPx), 20);
        this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(new Color(188, 69, 69));

        this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(new Color(188, 69, 69));
    }

    private void makeUpPanel() {
        this.returnButton = new JButton(Image.RETURN.icon);
        this.returnButton.setBackground(Color.black);
        this.returnButton.setBounds(borderPx,
                borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
        this.returnButton.addActionListener(this);

        JPanel address = new JPanel(new GridLayout(2, 5));
        address.setBackground(Color.BLACK);
        address.setBounds(2 * this.upPanel.getPreferredSize().height, 0,
                this.upPanel.getPreferredSize().width - 3 * this.upPanel.getPreferredSize().height, this.upPanel.getPreferredSize().height);

        JLabel city = new JLabel("Miasto:");
        city.setFont(font);
        city.setForeground(Color.WHITE);
        cityField = new JTextField();
        cityField.setFont(font);
        cityField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getMiasto());

        JLabel zipCode = new JLabel("Kod pocztowy:");
        zipCode.setFont(font);
        zipCode.setForeground(Color.WHITE);
        zipCodeField = new JTextField();
        zipCodeField.setFont(font);
        zipCodeField.setText(String.valueOf(this.warehouses.get(this.idxWarehouse).getAdres().getKodPocztowy()));

        JLabel street = new JLabel("Ulica:");
        street.setFont(font);
        street.setForeground(Color.WHITE);
        streetField = new JTextField();
        streetField.setFont(font);
        streetField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getUlica());

        JLabel buildNum = new JLabel("Nr budynku:");
        buildNum.setFont(font);
        buildNum.setForeground(Color.WHITE);
        buildNumField = new JTextField();
        buildNumField.setFont(font);
        buildNumField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getNrBudynku());

        JLabel apartNum = new JLabel("Nr lokalu:");
        apartNum.setFont(font);
        apartNum.setForeground(Color.WHITE);
        address.add(apartNum);
        apartNumField = new JTextField();
        apartNumField.setFont(font);

        address.add(city);
        address.add(zipCode);
        address.add(street);
        address.add(buildNum);
        address.add(apartNum);

        if (this.warehouses.get(this.idxWarehouse).getAdres().getNrLokalu() != null) {
            apartNumField.setText(String.valueOf(this.warehouses.get(this.idxWarehouse).getAdres().getNrLokalu()));
        }

        address.add(cityField);
        address.add(zipCodeField);
        address.add(streetField);
        address.add(buildNumField);
        address.add(apartNumField);
        
        this.upPanel.add(address);

        this.upPanel.setBackground(Color.BLACK);
        this.upPanel.add(this.returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) (JFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnToShopFromCart();
        }
        if (e.getSource() == this.addMagazin) {
            addedCategoryPopUp();
        }
    }
    
    private void addMagazineButton(int volume) {
          JButton temp = new JButton("new button");
            this.warehousesBut.add(temp);
            Magazyn magazyn = new Magazyn(volume,new Adres("brak", 0, "brak", "brak", 0));
            this.warehouses.add(magazyn);
             temp.setBackground(Color.BLACK);
            temp.setForeground(Color.WHITE);
            temp.addActionListener((ActionEvent e) -> {
                if (e.getSource() == temp) {
                    for (JButton b : this.warehousesBut) {
                        b.setEnabled(true);
                    }
                    temp.setEnabled(false);
                    idxWarehouse = Integer.parseInt(temp.getText().substring(3))-1;
                    this.refreshUpPanel();
                }
            });
            temp.setFont(font);
            this.categoryPanel.add(temp, BorderLayout.CENTER);
        
    }

    private void refreshUpPanel() {
        cityField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getMiasto());
        zipCodeField.setText(String.valueOf(this.warehouses.get(this.idxWarehouse).getAdres().getKodPocztowy()));
        streetField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getUlica());
        buildNumField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getNrBudynku());
        if (this.warehouses.get(this.idxWarehouse).getAdres().getNrLokalu() != null) {
            apartNumField.setText(String.valueOf(this.warehouses.get(this.idxWarehouse).getAdres().getNrLokalu()));
        }else{
            apartNumField.setText("");
        }
    }
    
        private void addedCategoryPopUp() {
        
        JPanel address = new JPanel(new GridLayout(2, 6));
        JLabel city = new JLabel("Miasto:");
        city.setFont(font);
        city.setForeground(Color.WHITE);
        cityField = new JTextField();
        cityField.setFont(font);
        cityField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getMiasto());

        JLabel zipCode = new JLabel("Kod pocztowy:");
        zipCode.setFont(font);
        zipCode.setForeground(Color.WHITE);
        zipCodeField = new JTextField();
        zipCodeField.setFont(font);
        zipCodeField.setText(String.valueOf(this.warehouses.get(this.idxWarehouse).getAdres().getKodPocztowy()));

        JLabel street = new JLabel("Ulica:");
        street.setFont(font);
        street.setForeground(Color.WHITE);
        streetField = new JTextField();
        streetField.setFont(font);
        streetField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getUlica());

        JLabel buildNum = new JLabel("Nr budynku:");
        buildNum.setFont(font);
        buildNum.setForeground(Color.WHITE);
        buildNumField = new JTextField();
        buildNumField.setFont(font);
        buildNumField.setText(this.warehouses.get(this.idxWarehouse).getAdres().getNrBudynku());

        JLabel apartNum = new JLabel("Nr lokalu:");
        apartNum.setFont(font);
        apartNum.setForeground(Color.WHITE);
        address.add(apartNum);
        apartNumField = new JTextField();
        apartNumField.setFont(font);
        
        JLabel volume = new JLabel("Pojemność");
        volume.setFont(font);
        volume.setForeground(Color.WHITE);
       
        
        SpinnerModel model = new SpinnerNumberModel(1, 1, 1000000, 1);
        volumeSpinner = new JSpinner(model);
        volumeSpinner.setFont(font);

        address.add(city);
        address.add(zipCode);
        address.add(street);
        address.add(buildNum);
        address.add(apartNum);
         address.add(volume);

        if (this.warehouses.get(this.idxWarehouse).getAdres().getNrLokalu() != null) {
            apartNumField.setText(String.valueOf(this.warehouses.get(this.idxWarehouse).getAdres().getNrLokalu()));
        }

        address.add(cityField);
        address.add(zipCodeField);
        address.add(streetField);
        address.add(buildNumField);
        address.add(apartNumField);
        address.add(volumeSpinner);
//        address.setLocation(ShopLayout.borderPx, this.upPanel.getPreferredSize().height - ShopLayout.borderPx);
        this.upPanel.add(address);
         int result = JOptionPane.showConfirmDialog(null, address,
                "Dodaj magazyn", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {


            MagazynDao dao = new MagazynDao();
            AdresDao adresDao = new AdresDao();
            Adres adres = adresDao.getAdres(cityField.getText(),Integer.valueOf(zipCodeField.getText()), streetField.getText(),
                        buildNumField.getText(), Integer.valueOf(apartNumField.getText()));
            if (adres == null) {
                adresDao.addAdres(cityField.getText(),Integer.valueOf(zipCodeField.getText()), streetField.getText(),
                        buildNumField.getText(), Integer.valueOf(apartNumField.getText()));
                adres = adresDao.getAdres(cityField.getText(),Integer.valueOf(zipCodeField.getText()), streetField.getText(),
                        buildNumField.getText(), Integer.valueOf(apartNumField.getText()));
                
                
            }
           
            dao.addMagazyn((Integer)volumeSpinner.getValue(),adres);
            Magazyn maagazyn = dao.getMagazyn((Integer)volumeSpinner.getValue(), adres);
            
            
               JButton temp = new JButton(maagazyn.toString());
            this.warehousesBut.add(temp);
            this.warehouses.add(maagazyn);
            temp.setBackground(Color.BLACK);
            temp.setForeground(Color.WHITE);
            temp.addActionListener((ActionEvent e) -> {
                if (e.getSource() == temp) {
                    for (JButton b : this.warehousesBut) {
                        b.setEnabled(true);
                    }
                    temp.setEnabled(false);
                    idxWarehouse = Integer.parseInt(temp.getText().substring(3))-1;
                    this.refreshUpPanel();
                }
            });
//            temp.setPreferredSize(new Dimension(categoryPanel.getSize().width, (int)(2*40*scale)));
            temp.setFont(font);
            this.categoryPanel.add(temp, BorderLayout.CENTER);
            this.categoryPanel.validate();
        }
    }
}
