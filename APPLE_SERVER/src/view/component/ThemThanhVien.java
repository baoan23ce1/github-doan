package view.component;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import model.Model_KhachHang;
import service.Service;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemThanhVien extends JPanel{
	private JDialog dialog;
	private JTextField tf_ten;
	private JTextField tf_sdt;
	private JTextField tf_tongChi;
	private JTextField tf_diem;
	private JComboBox cb_hangThanhVien;
	
	public ThemThanhVien(JDialog dialog) {
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(650, 400);
		setLayout(null);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnKhchHng.setBounds(86, 83, 177, 30);
		add(lblTnKhchHng);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setColumns(10);
		tf_ten.setBounds(273, 83, 283, 30);
		add(tf_ten);
		
		JLabel lblSt = new JLabel("SĐT");
		lblSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt.setBounds(86, 135, 154, 30);
		add(lblSt);
		
		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(273, 135, 283, 30);
		add(tf_sdt);
		
		JLabel lblTngChi = new JLabel("Tổng chi");
		lblTngChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTngChi.setBounds(86, 186, 154, 30);
		add(lblTngChi);
		
		tf_tongChi = new JTextField();
		tf_tongChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tongChi.setColumns(10);
		tf_tongChi.setBounds(273, 186, 283, 30);
		add(tf_tongChi);
		
		JLabel lblimTchLy = new JLabel("Điểm tích lũy");
		lblimTchLy.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblimTchLy.setBounds(86, 240, 154, 30);
		add(lblimTchLy);
		
		tf_diem = new JTextField();
		tf_diem.setText("0");
		tf_diem.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_diem.setColumns(10);
		tf_diem.setBounds(273, 240, 283, 30);
		add(tf_diem);
		
		JLabel lblHngThnhVin = new JLabel("Hạng thành viên");
		lblHngThnhVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHngThnhVin.setBounds(86, 289, 177, 30);
		add(lblHngThnhVin);
		
		String[] itemHang = { "Đồng", "Bạc", "Vàng", "Kim cương" };
		cb_hangThanhVien = new JComboBox<>(itemHang);
		cb_hangThanhVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		cb_hangThanhVien.setBounds(273, 289, 283, 30);
		add(cb_hangThanhVien);
		
		JLabel lblNewLabel_1 = new JLabel("THÊM THÀNH VIÊN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setBounds(89, 10, 449, 46);
		add(lblNewLabel_1);
		
		JButton bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tf_ten.getText();
				String sdt = tf_sdt.getText();
				int diem = Integer.parseInt(tf_diem.getText());
				int tongChi = Integer.parseInt(tf_tongChi.getText());
				String hang = cb_hangThanhVien.getSelectedItem().toString();
				
				Model_KhachHang khachHang = new Model_KhachHang(0, ten, sdt, tongChi, diem, hang);
				Service.getInstance().getMain().getBody().getKhachhang().themThanhVien(khachHang);
				dialog.dispose();
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_them.setBounds(247, 340, 168, 36);
		add(bt_them);
	}
}
