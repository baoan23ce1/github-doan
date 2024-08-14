package view.component;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import model.Model_NhanVien;
import service.Service;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ThemNhanVien extends JPanel{
	private JTextField tf_tenNhanVien;
	private JTextField tf_cccd;
	private JTextField tf_luong;
	private JTextField tf_sdt;
	private JTextField tf_ngaySinh;
	private JDialog dialog;
	private JDateChooser date_ngaySinh;
	
	public ThemNhanVien(JDialog dialog) {
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(1000, 400);
		setLayout(null);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnNhnVin.setBounds(53, 84, 154, 30);
		add(lblTnNhnVin);
		
		tf_tenNhanVien = new JTextField();
		tf_tenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tenNhanVien.setColumns(10);
		tf_tenNhanVien.setBounds(217, 84, 268, 29);
		add(tf_tenNhanVien);
		
		JLabel lblCccd = new JLabel("CCCD");
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCccd.setBounds(53, 171, 154, 30);
		add(lblCccd);
		
		tf_cccd = new JTextField();
		tf_cccd.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_cccd.setColumns(10);
		tf_cccd.setBounds(217, 171, 268, 29);
		add(tf_cccd);
		
		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGiiTnh.setBounds(53, 258, 154, 30);
		add(lblGiiTnh);
		
		String[] itemGioiTinh = { "Nam", "Nữ" };
		JComboBox<String> cb_gioiTinh = new JComboBox<String>(itemGioiTinh);
		cb_gioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cb_gioiTinh.setBounds(217, 258, 268, 30);
		add(cb_gioiTinh);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLng.setBounds(563, 258, 154, 30);
		add(lblLng);
		
		tf_luong = new JTextField();
		tf_luong.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_luong.setColumns(10);
		tf_luong.setBounds(682, 258, 268, 29);
		add(tf_luong);
		
		String[] itemChucVu = { "Quản lý", "Nhân viên", "Lao công", "Bảo vệ" };
		JComboBox<String> cb_chucVu = new JComboBox<String>(itemChucVu);
		cb_chucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cb_chucVu.setBounds(682, 198, 268, 30);
		add(cb_chucVu);
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChcV.setBounds(563, 198, 154, 30);
		add(lblChcV);
		
		JLabel lblSt = new JLabel("SĐT");
		lblSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt.setBounds(563, 143, 154, 30);
		add(lblSt);
		
		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(682, 143, 268, 29);
		add(tf_sdt);
				
		date_ngaySinh = new JDateChooser();
		date_ngaySinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		date_ngaySinh.setBounds(682, 84, 268, 29);
		add(date_ngaySinh);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNgySinh.setBounds(563, 84, 154, 30);
		add(lblNgySinh);
		
		JLabel lblNewLabel_1 = new JLabel("THÊM NHÂN VIÊN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(268, 10, 449, 46);
		add(lblNewLabel_1);
		
		JButton bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tf_tenNhanVien.getText();
				String cccd = tf_cccd.getText();
				String gioiTinh = cb_gioiTinh.getSelectedItem().toString();
				
				java.util.Date selectedDate = date_ngaySinh.getDate();
				java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
				
				String sdt = tf_sdt.getText();
				String chucVu = cb_chucVu.getSelectedItem().toString();
				int luong = Integer.parseInt(tf_luong.getText());
				
				Model_NhanVien nhanVien = new Model_NhanVien(0, ten,cccd, gioiTinh, sqlDate, sdt, chucVu, luong);
				
				Service.getInstance().getMain().getBody().getNhanvien().themNhanVien(nhanVien);
				dialog.dispose();			
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 30));
		bt_them.setBounds(363, 332, 268, 46);
		add(bt_them);
	}

}
