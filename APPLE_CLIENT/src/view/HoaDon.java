package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.Color;

public class HoaDon extends JPanel{
	private JLabel lb_qr;
	private JTextArea textArea;

	public HoaDon() {
		setBackground(new Color(255, 255, 255));
		setSize(600, 650);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HÓA ĐƠN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(200, 0, 230, 54);
		add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setBounds(24, 70, 555, 323);
		add(textArea);
		
		lb_qr = new JLabel("");
		lb_qr.setBounds(200, 421, 200, 200);
		add(lb_qr);
	}



	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getLb_qr() {
		return lb_qr;
	}

	public void setLb_qr(JLabel lb_qr) {
		this.lb_qr = lb_qr;
	}
	
	
	
}
