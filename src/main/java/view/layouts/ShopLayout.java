package view.layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import map.Kategoria;
import map.Produkt;
import dao.KategoriaDao;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.Image;
import view.MainFrame;
import static view.layouts.ListPanel.scale;

public class ShopLayout extends JPanel implements ActionListener {

    public static final int borderPx = Toolkit.getDefaultToolkit().getScreenSize().width / 500;

    private JScrollPane scroll;
    private ListPanel mainPanel;

    private JPanel upPanel;
    private JButton logOut;
    private JButton cart;
    private JButton user;

    private JPanel categoryPanel;
    private JButton addCategory;
    private JList<String> list;
    private JComboBox cardinality;
    private JComboBox category;

    private JTextField nameField;
    private JTextArea descField;

    private boolean admin = false;

    public ShopLayout(boolean admin) {
        this.admin = admin;
        this.setLayout(null);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.WHITE);
        this.upPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(Image.LOGO.icon.getImage(),
                        Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 7 * borderPx,
                        -borderPx,
                        null
                );
            }
        };
        this.categoryPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        categoryPanel.setLayout(layout);
        this.makeMainPanel();

        this.upPanel.setLayout(null);

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

    private void makeCategoryPanel() {
        if (admin) {
            this.addCategory = new JButton("<html>" + "  Dodaj" + "<br>" + "kategorię" + "</html>");
            addCategory.setPreferredSize(new Dimension(60, 60));
            this.addCategory.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40)));
            this.categoryPanel.add(addCategory, BorderLayout.NORTH);
            this.addCategory.addActionListener(this);
            // dodaj przycisk umożliwiający dodawanie kategorii
            // do tego jakiś popup
            // wymyśl usuwanie kategorii przez admina
            // pov: obsługa kategorii też nie działa więc xd
            // do tego lista producentów
            // wymyśl coś
        } else {
            this.addCategory = new JButton("<html>" + "  Zastosuj" + "<br>" + "filtry" + "</html>");
            addCategory.setPreferredSize(new Dimension(60, 60));
            this.addCategory.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40)));
            this.categoryPanel.add(addCategory, BorderLayout.NORTH);
            this.addCategory.addActionListener(this);
        }
        this.categoryPanel.setBackground(Color.black);
        KategoriaDao dao = new KategoriaDao();
        List<Kategoria> kategorie = dao.getAll();
        DefaultListModel<String> model = new DefaultListModel<>();
        this.list = new JList<>(model);
        list.setForeground(Color.white);
        list.setBackground(Color.black);
        list.setSelectionBackground(Color.gray);
        list.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40)));
        for (Kategoria kategoria : kategorie) {
            model.addElement(kategoria.getNazwaKategorii());
            System.out.println(kategoria.getNazwaKategorii());
        }
        this.categoryPanel.add(list, BorderLayout.CENTER);
        this.list.setSelectionBackground(new Color(188, 69, 69));
//        this.list.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
//                ArrayList<Kategoria> katList = null;
//                if (!lsm.isSelectionEmpty()) {
//                    int f = lsm.getMinSelectionIndex();
//                    int l = lsm.getMaxSelectionIndex();
//                    katList = new ArrayList<Kategoria>();
//                    for (int i = f; i < l; i++) {
//                        katList.add(kategorie.get(i));
//                    }
//                }
//
//                mainPanel.setList(katList);
//            }
//
//        });
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

        this.user = new JButton(Image.USER.icon);
        this.user.setBackground(Color.black);
        this.user.setBounds(this.upPanel.getPreferredSize().width - this.upPanel.getPreferredSize().height + 3 * this.borderPx - Image.USER.icon.getImage().getWidth(user), borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
//                .setBounds(borderPx, borderPx, 
//                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx/*-Image.USER.icon.getImage().getWidth(user)*/, 
//                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
        this.user.addActionListener(this);

        this.cart = new JButton();
        if (admin) {
            this.cart.setIcon(Image.WAREHOUSE.icon);
        } else {
            this.cart.setIcon(Image.CART.icon);
        }
        this.cart.setBounds(this.upPanel.getPreferredSize().width - this.upPanel.getPreferredSize().height + this.borderPx, borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx,
                this.upPanel.getPreferredSize().height - 2 * ShopLayout.borderPx);
        this.cart.setBackground(Color.black);
        this.cart.addActionListener(this);

        this.upPanel.setBackground(Color.BLACK);
        this.upPanel.add(this.logOut);
        this.upPanel.add(this.cart);
        this.upPanel.add(this.user);
    }

    public void refreshProduct(Produkt produkt) {
        this.mainPanel.refreshProduct(produkt);
    }

    public void updateCategoryList() {
        DefaultListModel<String> model = new DefaultListModel<>();
//        this.list = new JList<>(model);
        this.list.setModel(model);
//        list.setForeground(Color.white);
//        list.setBackground(Color.black);
//        list.setSelectionBackground(Color.gray);
//        list.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40)));
//        for (Kategoria kategoria : kategorie) {
//            model.addElement(categoryName);
//            System.out.println(kategoria.getNazwaKategorii());
        KategoriaDao dao = new KategoriaDao();
        List<Kategoria> kategorie = dao.getAll();
//        DefaultListModel<String> model = new DefaultListModel<>();
        this.list = new JList<>(model);
//        list.setForeground(Color.white);
//        list.setBackground(Color.black);
//        list.setSelectionBackground(Color.gray);
//        list.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40)));
        for (Kategoria kategoria : kategorie) {
            model.addElement(kategoria.getNazwaKategorii());
            System.out.println(kategoria.getNazwaKategorii());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.logOut) {
            this.logOutPopUp();
        }
        if (e.getSource() == this.cart) {
            if (!admin) {
                System.out.println("Przechodzisz do koszyka");
                MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
                mf.showCart();
            } else {
                System.out.println("Przechodzisz do magazynów");
                MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
                mf.showWarehouse();
            }
        }

        if (e.getSource() == this.user) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showUserSettings();
        }
        if (e.getSource() == this.addCategory && admin) {

            addedCategoryPopUp();
        }
        if (e.getSource() == this.addCategory && admin == false){
//             int index = list.getSelectedIndex();
//            System.out.println("Index Selected: " + index);
//            String s = (String) list.getSelectedValue();
//            System.out.println("Value Selected: " + s);


            mainPanel.clearProductsPanel();
            int[] selectedIndices = list.getSelectedIndices();
//            String[] myArray = new String[50];
            for (int i = 0; i < selectedIndices.length; i++) {
//                myArray[i] = String.valueOf(jList1.getModel().getElementAt(selectedIndices[i]));
                System.out.println(list.getModel().getElementAt(selectedIndices[i]));
//                    if(   list.getModel().getElementAt(selectedIndices[i]))
                mainPanel.filterProducts(list.getModel().getElementAt(selectedIndices[i]));
            }
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

    private void addedCategoryPopUp() {
//        JOptionPane.showMessageDialog(null, "Dodano kategorię", "", JOptionPane.INFORMATION_MESSAGE);
        this.nameField = new JTextField(15);
        this.descField = new JTextArea("Wpisz opis kategorii", 5, 5);
        this.descField.setWrapStyleWord(true);
        this.descField.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(descField);
        scrollPane.setPreferredSize(new Dimension(300, 125));
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Nazwa:"));
        myPanel.add(nameField);
        myPanel.add(Box.createVerticalStrut(15)); // a spacer
        myPanel.add(new JLabel("Opis:"));
        myPanel.add(scrollPane);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Podaj nazwę i opis kategorii", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            KategoriaDao dao = new KategoriaDao();
            dao.addKategoria(nameField.getText(), descField.getText());
            updateCategoryList();
//            this.makeCategoryPanel();
//            this.list.addElement(nameField.getText());
//            this.revalidate();
            this.list.repaint();
        }

    }
}
