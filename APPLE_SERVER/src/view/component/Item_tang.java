package view.component;

import javax.swing.*;

import model.Model_NhanVien;

import java.awt.Font;
import java.awt.Color;

public class Item_tang extends JPanel{
	
	private JPanel panel;
	private JLabel lb_nhanVienTruc;
	private JLabel lb_trangThai;
	private Model_NhanVien nhanvien;
	private int quay;

	public Item_tang(int quay) {
		this.quay = quay;
		setBackground(Color.WHITE);
		setSize(280, 179);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 280, 177);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhân viên trực");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 91, 199, 40);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Trạng thái");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 10, 139, 40);
		panel.add(lblNewLabel_1);
		
		lb_trangThai = new JLabel("Không hoạt động");
		lb_trangThai.setIcon(new ImageIcon(Item_tang.class.getResource("/images/offline.png")));
		lb_trangThai.setForeground(Color.red);
		lb_trangThai.setFont(new Font("Tahoma", Font.BOLD, 25));
		lb_trangThai.setBounds(10, 47, 260, 34);
		panel.add(lb_trangThai);
		
		lb_nhanVienTruc = new JLabel("");
		lb_nhanVienTruc.setIcon(new ImageIcon(Item_tang.class.getResource("/images/staff_offline.png")));
		lb_nhanVienTruc.setForeground(new Color(35, 210, 52));
		lb_nhanVienTruc.setFont(new Font("Tahoma", Font.BOLD, 25));
		lb_nhanVienTruc.setBounds(10, 133, 260, 34);
		panel.add(lb_nhanVienTruc);
	}
	
	public void online(Model_NhanVien nhanvien) {
		this.nhanvien = nhanvien;
		lb_nhanVienTruc.setText(nhanvien.getTen());
		lb_trangThai.setText("Đang hoạt động");
		lb_trangThai.setForeground(new Color(35, 210, 52));
		lb_trangThai.setIcon(new ImageIcon(Item_tang.class.getResource("/images/online.png")));
		lb_nhanVienTruc.setIcon(new ImageIcon(Item_tang.class.getResource("/images/staff_online.png")));
	}
	
	public void offline() {
		this.nhanvien = null;
		lb_nhanVienTruc.setText("");
		lb_trangThai.setText("Không hoạt động");
		lb_trangThai.setForeground(Color.RED);
		lb_trangThai.setIcon(new ImageIcon(Item_tang.class.getResource("/images/offline.png")));
		lb_nhanVienTruc.setIcon(new ImageIcon(Item_tang.class.getResource("/images/staff_offline.png")));
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLb_nhanVienTruc() {
		return lb_nhanVienTruc;
	}

	public void setLb_nhanVienTruc(JLabel lb_nhanVienTruc) {
		this.lb_nhanVienTruc = lb_nhanVienTruc;
	}

	public JLabel getLb_trangThai() {
		return lb_trangThai;
	}

	public void setLb_trangThai(JLabel lb_trangThai) {
		this.lb_trangThai = lb_trangThai;
	}


	public Model_NhanVien getNhanvien() {
		return nhanvien;
	}

	public int getQuay() {
		return quay;
	}
	
	

}
