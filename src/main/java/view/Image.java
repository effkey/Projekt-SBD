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
  RETURN(new ImageIcon("src/main/images/return.png")),
  REMOVE(new ImageIcon("src/main/images/REMOVE.png")),
  SAVE(new ImageIcon("src/main/images/save.png")),
  EDIT_SAVE(new ImageIcon("src/main/images/edit_save.png")),
  EMPTY(new ImageIcon("src/main/images/empty_image.png"));
	
	public ImageIcon icon;
	private Image(ImageIcon icon) {
		this.icon = icon;
	}