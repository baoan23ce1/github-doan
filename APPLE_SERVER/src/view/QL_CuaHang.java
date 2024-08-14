package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Model_NhanVien;
import view.component.Item_tang;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Font;

public class QL_CuaHang extends JPanel{
	
	private JPanel panel;

	public QL_CuaHang() {
		setBackground(Color.WHITE);
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(159, 303, 940, 186);
		add(panel);
		panel.setLayout(new GridLayout(1, 3, 50, 50));
		
		Item_tang item1 = new Item_tang(1);
		item1.getPanel().setBackground(new Color(239, 163, 163));
		item1.getPanel().setOpaque(true);
		panel.add(item1);
		
		Item_tang item2 = new Item_tang(2);
		item2.getPanel().setBackground(new Color(255, 237, 172));
		item2.getPanel().setOpaque(true);
		panel.add(item2);
		
		Item_tang item3 = new Item_tang(3);
		item3.getPanel().setBackground(new Color(255, 183, 239));
		item3.getPanel().setOpaque(true);
		panel.add(item3);
		
		JLabel lblNewLabel = new JLabel("TẦNG 1");
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(159, 494, 278, 41);
		add(lblNewLabel);
		
		JLabel lblTng = new JLabel("TẦNG 2");
		lblTng.setForeground(new Color(70, 130, 180));
		lblTng.setHorizontalAlignment(SwingConstants.CENTER);
		lblTng.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTng.setBounds(486, 494, 278, 41);
		add(lblTng);
		
		JLabel lblTng_1 = new JLabel("TẦNG 3");
		lblTng_1.setForeground(new Color(70, 130, 180));
		lblTng_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTng_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTng_1.setBounds(820, 494, 278, 41);
		add(lblTng_1);
			
	}
	
	public void online(Model_NhanVien nhanvien, int quay) {
		Component[] components = panel.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_tang) {
		        Item_tang item = (Item_tang) component;
		        if(item.getQuay() == quay) {
		        	item.online(nhanvien);
		        	panel.repaint();
		        	panel.revalidate();
		        	break;
		        }
		    }
		}
	}
	
	public void offline(int quay) {
		Component[] components = panel.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_tang) {
		        Item_tang item = (Item_tang) component;
		        if(item.getQuay() == quay) {
		        	item.offline();
		        	panel.repaint();
		        	panel.revalidate();
		        	break;
		        }
		    }
		}
	}
}
