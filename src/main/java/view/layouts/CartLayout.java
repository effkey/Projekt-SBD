package view.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.View;
import map.Produkt;
import view.Image;
import view.MainFrame;


public class CartLayout extends JPanel implements ActionListener {

    public static final int borderPx = Toolkit.getDefaultToolkit().getScreenSize().width / 500;

    private JScrollPane scroll;
    private Cart mainPanel;

    private JPanel upPanel;
    private JButton returnButton;
    private JLabel logo;

    private JPanel categoryPanel = new JPanel();
    private JComboBox cardinality;
    private JComboBox category;

    public CartLayout() {
        this.setLayout(null);

        this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.998), (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.998)));
        this.setBackground(Color.WHITE);

        this.upPanel = new JPanel();
        this.categoryPanel = new JPanel() {
//            @Override
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.drawImage(Image.LOGO.icon.getImage(), borderPx, this.getPreferredSize().height - borderPx, null);
//            }
        };

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
        this.mainPanel.addProdukt(produkt);
    }

//    public void removeProduct(Produkt produkt) {
//        this.mainPanel.removeProdukt(produkt);
//    }

    private void makeCategoryPanel() {
        JLabel sum = new JLabel("tu suma");
        sum.setForeground(Color.white);
        JLabel quantity = new JLabel("ilosc");
        quantity.setForeground(Color.white);
        this.categoryPanel.add(sum);
        this.categoryPanel.add(quantity);
        this.categoryPanel.setBackground(Color.black);
    }

    private void makeMainPanel() {
        this.mainPanel = new Cart(new Dimension(getPreferredSize().width - 3 * borderPx - this.getPreferredSize().width / 10,
                this.getPreferredSize().height - this.getPreferredSize().height / 8 - 8 * borderPx), 20);
        this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(new Color(255, 69, 69));
        
         this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(new Color(255, 69, 69));
    }

    private void makeUpPanel() {
        this.returnButton = new JButton(Image.RETURN.icon);
        this.returnButton.setBackground(Color.black);
        this.returnButton.setBounds(borderPx, borderPx, this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx, this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
        this.returnButton.addActionListener(this);

     
        this.upPanel.setBackground(Color.BLACK);
        this.upPanel.add(this.returnButton);
        
        this.logo = new JLabel("sdgfdfgsd");  //view.Image.LOGO.icon
         this.logo.setForeground(Color.WHITE);
        this.upPanel.add(this.logo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) (JFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnToShopFromCart();
        }
    }
}
