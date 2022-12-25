package view.layouts;

import dao.KategoriaDao;
import dao.ProducentDao;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import map.Kategoria;
import map.Producent;
import map.Produkt;
import view.MainFrame;

public class Details extends JPanel implements ActionListener {

    public static final int borderPx = Toolkit.getDefaultToolkit().getScreenSize().width / 500;
    private JButton returnButton;
    private JButton addToCartButton;
    private boolean isAdmin;
    private int imageWidth = 144;
    private int imageHeight = 144;
    private boolean fromCart;

    private Font font = new Font("Sans Serif", Font.BOLD, 40);

    public Details() {

    }

    public Details(Produkt produkt, boolean admin, boolean fromCart) {
        this.isAdmin = admin;
        this.fromCart = fromCart;
        setForeground(Color.WHITE);
        this.setBackground(new Color(188, 69, 69));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{500, 500, 0};
        gbl_contentPane.rowHeights = new int[]{119, 292, 295, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.5, 1.0, 1.0, Double.MIN_VALUE};
        this.setLayout(gbl_contentPane);

        JPanel panel = new JPanel();
        panel.setForeground(Color.WHITE);
        panel.setBackground(Color.BLACK);
        panel.setBorder(new EmptyBorder(4, 4, 4, 4));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridwidth = 2;
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(panel, gbc_panel);
        panel.setLayout(new GridLayout(1, 3, 0, 0));

        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
        returnPanel.setBackground(Color.BLACK);
        panel.add(returnPanel);

        returnButton = new JButton(view.Image.RETURN.icon);
        returnButton.setPreferredSize(new Dimension(imageWidth, imageHeight));
        returnButton.addActionListener(this);
        returnButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        returnButton.setBackground(Color.BLACK);
        returnPanel.add(returnButton);

        JLabel logo = new JLabel(view.Image.LOGO.icon);
        panel.add(logo);

        JLabel nameLabel = new JLabel(produkt.getNazwaProduktu());
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(nameLabel);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(128, 128, 128));
        GridBagConstraints gbc_imagePanel = new GridBagConstraints();
        gbc_imagePanel.insets = new Insets(0, 0, 5, 5);
        gbc_imagePanel.gridx = 0;
        gbc_imagePanel.gridy = 1;
        this.add(imagePanel, gbc_imagePanel);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("src/products/" + produkt.getNazwaObrazka()));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(188, 69, 69));
        GridBagConstraints gbc_textPanel = new GridBagConstraints();
        gbc_textPanel.insets = new Insets(0, 0, 5, 0);
        gbc_textPanel.gridx = 1;
        gbc_textPanel.gridy = 1;
        this.add(textPanel, gbc_textPanel);
        textPanel.setLayout(new FlowLayout());

        JPanel textInputsPanel = new JPanel();
        textInputsPanel.setBackground(new Color(188, 69, 69));
        textInputsPanel.setLayout(new GridLayout(4, 3, 0, 0));  //new GridLayout(4, 2, 0, 0)
        textPanel.add(textInputsPanel);

        addToCartButton = new JButton(view.Image.CART_ADD.icon);
        addToCartButton.addActionListener(this);
        addToCartButton.setPreferredSize(new Dimension(imageWidth, imageHeight));
        addToCartButton.setBackground(Color.BLACK);
        textPanel.add(addToCartButton);

        JLabel pieces = new JLabel("Liczba sztuk: ");
        pieces.setFont(font);
        pieces.setForeground(Color.WHITE);
        textInputsPanel.add(pieces);

        SpinnerModel model = new SpinnerNumberModel(1, 1, 10000, 1);
        JSpinner spinner = new JSpinner(model);
        spinner.setFont(font);
        textInputsPanel.add(spinner);

        JLabel priceLabel = new JLabel("Cena: ");
        priceLabel.setFont(font);
        priceLabel.setForeground(Color.WHITE);
        textInputsPanel.add(priceLabel);

        JTextField price = new JTextField(String.valueOf(produkt.getCena()));
        price.setFont(font);
        price.setEditable(isAdmin);
        textInputsPanel.add(price);

        JLabel producentLabel = new JLabel("Producent: ");
        producentLabel.setFont(font);
        producentLabel.setForeground(Color.WHITE);
        textInputsPanel.add(producentLabel);

        
        ProducentDao pDao = new ProducentDao();
        ArrayList<Producent> prod = (ArrayList<Producent>) pDao.getAll();
        JComboBox producent = new JComboBox();
        for (Producent p : prod) {
            producent.addItem(p);
        }
        producent.setFont(font);
        producent.setSelectedItem(produkt.getProducent());
        producent.setEditable(isAdmin);
        textInputsPanel.add(producent);

        JLabel categoryLabel = new JLabel("Kategoria: ");
        categoryLabel.setFont(font);
        categoryLabel.setForeground(Color.WHITE);
        textInputsPanel.add(categoryLabel);

        KategoriaDao kDao = new KategoriaDao();
        ArrayList<Kategoria> kat = (ArrayList<Kategoria>) kDao.getAll();
        JComboBox category = new JComboBox();
        for (Kategoria k : kat) {
            category.addItem(k);
        }
        category.setSelectedItem(produkt.getKategoria());
        category.setFont(font);
        category.setEditable(admin);
        textInputsPanel.add(category);

        JPanel descPanel = new JPanel();
        descPanel.setForeground(Color.WHITE);
        descPanel.setBackground(new Color(188, 69, 69));
        GridBagConstraints gbc_descPanel = new GridBagConstraints();
        gbc_descPanel.anchor = GridBagConstraints.PAGE_START;
        gbc_descPanel.gridwidth = 2;
        gbc_descPanel.fill = GridBagConstraints.BOTH;
        gbc_descPanel.gridx = 0;
        gbc_descPanel.gridy = 2;
        this.add(descPanel, gbc_descPanel);
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));

        JLabel descLabel = new JLabel("Opis");
        descLabel.setForeground(Color.WHITE);
        descLabel.setBackground(new Color(188, 69, 69));
        descLabel.setFont(font);
        descLabel.setHorizontalAlignment(SwingConstants.LEFT);
        descPanel.add(descLabel);

        JTextArea descTextArea = new JTextArea(produkt.getOpis());
        descTextArea.setForeground(Color.WHITE);
        descTextArea.setFont(font);
        descTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        descTextArea.setLineWrap(true);
        descTextArea.setWrapStyleWord(true);
        descTextArea.setBackground(Color.BLACK);
        descTextArea.setEditable(isAdmin);
        descPanel.add(descTextArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) (JFrame) SwingUtilities.getWindowAncestor(this);
            if (!fromCart) {
                mf.returnToShop();
            } else {
                mf.returnToCart();
            }
        }
        if (e.getSource() == this.addToCartButton) {
            this.addToCartPopUp();
        }

    }

    private void addToCartPopUp() {
        JOptionPane.showMessageDialog(null, "Dodano produkt do koszyka", "", JOptionPane.INFORMATION_MESSAGE);
    }

}
