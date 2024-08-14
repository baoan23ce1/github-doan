package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_Phone;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Item_Phone extends JPanel{
	private Model_Phone sach;
	
	public Item_Phone(Model_Phone sach) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	        	JDialog dialog = new JDialog();
	        	ChiTiet them = new ChiTiet(dialog, sach);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(950, 500);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.sach = sach;
		setBackground(new Color(255, 255, 255));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setSize(150, 200);
		setLayout(null);
		
		JLabel lb_anh = new JLabel("");
		lb_anh.setBorder(new LineBorder(new Color(128, 128, 128)));
        ImageUtil.setImageLabelFromBytes(sach.getHinhAnh(), lb_anh, 150, 150);
//		lb_anh.setIcon(new ImageIcon(new ImageIcon(Item_Sach.class.getResource("/images/dacnhantam.jpg")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		lb_anh.setBackground(new Color(255, 255, 255));
		lb_anh.setOpaque(true);
		lb_anh.setBounds(0, 0, 150, 150);
		add(lb_anh);
		
		JTextArea lb_ten = new JTextArea(sach.getTen());
		lb_ten.setWrapStyleWord(true);
		lb_ten.setLineWrap(true);
		lb_ten.setEditable(false);
		lb_ten.setBackground(new Color(255, 255, 255));
		lb_ten.setFont(new Font("Tahoma", Font.BOLD, 14));
		JScrollPane scroll = new JScrollPane(lb_ten);
		scroll.setBorder(new LineBorder(SystemColor.controlHighlight));
		scroll.setBounds(0, 150, 150, 50);
		add(scroll);
	}
}
