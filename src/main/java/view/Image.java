package view;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public enum Image {
	CART(new ImageIcon("src/main/images/cart.png")),
	CART_ADD(new ImageIcon("src/main/images/add_to_cart.png")),
	USER(new ImageIcon("src/main/images/user.png")),
	DETAILS(new ImageIcon("src/main/images/details.png")),
	LOG_OUT(new ImageIcon("src/main/images/log_out.png")),
	LOGO(new ImageIcon("src/main/images/LOGO.png")),
        RETURN(new ImageIcon("src/main/images/return.png"));
	
	public ImageIcon icon;
	private Image(ImageIcon icon) {
		this.icon = icon;
	}
}