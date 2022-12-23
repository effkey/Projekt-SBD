package view.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import map.Produkt;

import view.Image;
import view.MainFrame;

public class ShopLayout extends JPanel implements ActionListener {

    public static final int borderPx = Toolkit.getDefaultToolkit().getScreenSize().width / 500;

    private JScrollPane scroll;
    private ListPanel mainPanel;

    private JPanel upPanel;
    private JButton logOut;
    private JButton cart;

    private JPanel categoryPanel = new JPanel();
    private JComboBox cardinality;
    private JComboBox category;

    private boolean admin = false;

    public ShopLayout(boolean admin) {
        this.admin = admin;
        this.setLayout(null);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.WHITE);
        this.upPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(Image.LOGO.icon.getImage(), Toolkit.getDefaultToolkit().getScreenSize().width/2-7*borderPx, -borderPx, null);
            }
        };
        this.categoryPanel = new JPanel();

        this.makeMainPanel();

        this.upPanel.setLayout(null);
        this.categoryPanel.setLayout(null);

        this.scroll = new JScrollPane(mainPanel);
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
        this.mainPanel.addProdukt(produkt);
    }

    public void removeProduct(Produkt produkt) {
        this.mainPanel.removeProdukt(produkt);
    }

    private void makeCategoryPanel() {
        this.categoryPanel.setBackground(Color.black);
    }

    private void makeMainPanel() {
        this.mainPanel = new ListPanel(new Dimension(getPreferredSize().width - 3 * borderPx - this.getPreferredSize().width / 10,
                this.getPreferredSize().height - this.getPreferredSize().height / 8 - 8 * borderPx), 20, this.admin);
        this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(new Color(188, 69, 69));
    }

    private void makeUpPanel() {
        this.logOut = new JButton(Image.LOG_OUT.icon);
        this.logOut.setBackground(Color.black);
        this.logOut.setBounds(borderPx, borderPx, this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx, this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
        this.logOut.addActionListener(this);

        this.cart = new JButton(Image.CART.icon);
        this.cart.setBounds(this.upPanel.getPreferredSize().width - this.upPanel.getPreferredSize().height + this.borderPx, borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx, this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
        this.cart.setBackground(Color.black);
        this.cart.addActionListener(this);

        this.upPanel.setBackground(Color.BLACK);
        this.upPanel.add(this.logOut);
        this.upPanel.add(this.cart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.logOut) {
            this.logOutPopUp();
        }
        if (e.getSource() == this.cart) {
            System.out.println("Przechodzisz do koszyka");
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showCart();
        }
    }

    private void logOutPopUp() {
        Object[] options = {"Tak",
            "Nie, wróć do sklepu"};
        int n = JOptionPane.showOptionDialog(null,
                "Czy na pewno chcesz się wylogować?",
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if (n == 0) {
            logOutMessagePopUp();
            System.out.println("Wylogowujesz sie");
            MainFrame mf = (MainFrame) (JFrame) SwingUtilities.getWindowAncestor(this);
            this.setVisible(false);
            mf.showLogInPanel();
        }
        if (n == 1) {
        }
    }

    private void logOutMessagePopUp() {
        JOptionPane.showMessageDialog(null, "Nastąpiło wylogowanie", "", JOptionPane.INFORMATION_MESSAGE);
    }
}
