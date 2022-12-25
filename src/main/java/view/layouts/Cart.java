/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import map.Produkt;
import view.Image;
import view.MainFrame;

/**
 *
 * @author grzec
 */
public class Cart extends JPanel implements ActionListener {
    private int imageWidth = 400;
    private int imageHeight = 300;
    public final Dimension defaultResolution = new Dimension(2560, 1440);
    private Dimension curResolution;
    public float scale = 1;

    private Font font;

    private List<JButton> removeButtons = new ArrayList<JButton>();
    private List<JButton> toDetailsButton = new ArrayList<JButton>();
    private List<JTextArea> shortTextLabel = new ArrayList<JTextArea>();
    private List<Produkt> list = new ArrayList<Produkt>();

    public Cart(Dimension dim, int cardinality) {
//		this.setSize(new Dimension(dim.width, dim.height*10));
        System.out.println(dim.width + "  " + dim.height);
        this.curResolution = Toolkit.getDefaultToolkit().getScreenSize();
        if (this.curResolution.width != this.defaultResolution.width || this.curResolution.height != this.defaultResolution.height) {
            float tmp = this.curResolution.height / (float) (this.defaultResolution.height);
            System.out.println(tmp);
            this.scale = this.curResolution.width / (float) (this.defaultResolution.width);
            if (scale > 1 && tmp > 1) {
                if (tmp > scale) {
                    scale = tmp;
                }
            } else if (scale > 1 && tmp < 1) {
                if (scale + tmp < 0) {
                    scale = tmp;
                }
            } else if (scale < 1 && tmp < 1) {
                if (tmp < scale) {
                    scale = tmp;
                }
            } else if (scale < 1 && tmp > 1) {
                if (scale + tmp > 0) {
                    scale = tmp;
                }
            }
        }
        System.out.println(scale);
        this.imageWidth = (int) (this.imageWidth * scale);
        this.imageHeight = (int) (this.imageHeight * scale);
        this.setPreferredSize(new Dimension(dim.width - CartLayout.borderPx * 10, cardinality * imageHeight));
        font = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, (int) (scale * 40));
    }

    public void addProdukt(Produkt produkt) {
        System.out.println(curResolution.width + " " + curResolution.height);
        JButton removeButton;
        JButton toDetails;
        JTextArea shortText;

        removeButton = new JButton(Image.CART_ADD.icon);
        toDetails = new JButton(Image.DETAILS.icon);

        shortText = new JTextArea(produkt.getNazwaProduktu());

        shortText.setFont(font);
        shortText.setLineWrap(true);
        shortText.setWrapStyleWord(true);
        shortText.setEditable(false);
//		shortText.setIgnoreRepaint(true);

        shortText.setBounds(imageWidth / 4 + imageWidth + ShopLayout.borderPx, (this.list.size()) * imageHeight * 6 / 5 + imageHeight / 6,
                this.getPreferredSize().width - 2 * imageWidth, imageHeight);
        toDetails.setBounds(this.getPreferredSize().width - 3 * imageWidth / 4 + 2 * ShopLayout.borderPx, 3 / 2 * (this.list.size()) * imageHeight * 6 / 5 + imageHeight / 6 + imageHeight / 2 + ShopLayout.borderPx,
                imageHeight / 2 - ShopLayout.borderPx, imageHeight / 2 - ShopLayout.borderPx);
        removeButton.setBounds(this.getPreferredSize().width - 3 * imageWidth / 4 + 2 * ShopLayout.borderPx,
                3 / 2 * (this.list.size()) * imageHeight * 6 / 5 + imageHeight / 6,
                imageHeight / 2 - ShopLayout.borderPx,
                imageHeight / 2 - ShopLayout.borderPx);

        removeButton.addActionListener(this);
        toDetails.addActionListener(this);

        shortText.setForeground(Color.WHITE);
        shortText.setBackground(Color.BLACK);
        toDetails.setBackground(Color.black);
        removeButton.setBackground(Color.black);

        this.removeButtons.add(removeButton);
        this.shortTextLabel.add(shortText);
        this.toDetailsButton.add(toDetails);

        removeButton.setVisible(true);
        shortText.setVisible(true);
        toDetails.setVisible(true);

        this.add(shortText);
        this.add(removeButton);
        this.add(toDetails);

        list.add(produkt);
        this.repaint();
//		System.out.println("DODAWANE JEST, ileIchJest: " + this.list.size());
    }

    public void removeProdukt(Produkt produkt, int index) {
//        int index = list.indexOf(produkt);
        this.remove(index);
        this.remove(index);
        this.remove(index);
        list.remove(produkt);
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale/3, scale/3);
        this.paintProducts(g2d);
        g2d.scale(1 / scale, 1 / scale);
    }

    private void paintProducts(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        int i = 0;
        for (Produkt produkt : list) {
            int posX = (int) (1 / scale * imageWidth / 4), posY = (int) (i * 1 / scale * imageHeight * 6 / 5 + 1 / scale * imageHeight / 6);
            g2d.drawImage(new ImageIcon("src/products/" + this.list.get(this.list.size() - 1).getNazwaObrazka()).getImage(), posX, posY, null);
            i++;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = 0;
        for (JButton but : this.removeButtons) {
            if (e.getSource() == but) {
                removeProdukt(this.list.get(i), i);
                System.out.println("Usunięto z koszyka: " + this.list.get(i).getNazwaProduktu() + "indeks" + i);
            }
            i++;
        }
        i = 0;
        for (JButton but : this.toDetailsButton) {
            if (e.getSource() == but) {
                System.out.println("Przejscie do detali: " + this.list.get(i).getNazwaProduktu());
                MainFrame mf = (MainFrame) (JFrame) SwingUtilities.getWindowAncestor(this);
                mf.showProductPanel(this.list.get(i));
            }
            i++;
        }
    }

    private void addToCartPopUp() {
        JOptionPane.showMessageDialog(null, "Dodano produkt do koszyka", "", JOptionPane.INFORMATION_MESSAGE);
    }
}
