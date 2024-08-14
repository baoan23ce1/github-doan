package view.component;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_KhachHang;
import model.Model_Phone;
import service.Service;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemPhone extends JPanel{
	private JDialog dialog;
	private JTextField tf_ten;
	private JTextField tf_slTonKho;
	private JTextField tf_slDaBan;
	private JTextField tf_donGia;
	private JComboBox cb_theloai;
	private JLabel lb_anh;
	private JButton bt_them;
	private JLabel lblNewLabel;
	private byte[] hinhAnh;
	
	public ThemPhone(JDialog dialog) {
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(1000, 500);
		setLayout(null);
		
		JLabel Teen = new JLabel("Tên sản phẩm");
		Teen.setFont(new Font("Tahoma", Font.BOLD, 20));
		Teen.setBounds(412, 124, 145, 30);
		add(Teen);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setText("");
		tf_ten.setColumns(10);
		tf_ten.setBounds(560, 124, 318, 30);
		add(tf_ten);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThLoi.setBounds(412, 179, 145, 30);
		add(lblThLoi);
		
		String[] itemTheLoai = { "IPhone", "IPad", "MacBook", "Apple Watch" };
		cb_theloai = new JComboBox<>(itemTheLoai);
		cb_theloai.setFont(new Font("Tahoma", Font.BOLD, 20));
		cb_theloai.setSelectedIndex(0);
		cb_theloai.setBounds(560, 179, 318, 30);
		add(cb_theloai);
		
		JLabel lblSlTnKho = new JLabel("SL tồn kho");
		lblSlTnKho.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlTnKho.setBounds(412, 233, 145, 30);
		add(lblSlTnKho);
		
		tf_slTonKho = new JTextField();
		tf_slTonKho.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slTonKho.setText("");
		tf_slTonKho.setColumns(10);
		tf_slTonKho.setBounds(560, 233, 318, 30);
		add(tf_slTonKho);
		
		JLabel lblSlBn = new JLabel("SL đã bán");
		lblSlBn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlBn.setBounds(412, 286, 145, 30);
		add(lblSlBn);
		
		tf_slDaBan = new JTextField();
		tf_slDaBan.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slDaBan.setText("");
		tf_slDaBan.setColumns(10);
		tf_slDaBan.setBounds(560, 286, 318, 30);
		add(tf_slDaBan);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblnGi.setBounds(412, 340, 145, 30);
		add(lblnGi);
		
		tf_donGia = new JTextField();
		tf_donGia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_donGia.setText("");
		tf_donGia.setColumns(10);
		tf_donGia.setBounds(560, 340, 318, 30);
		add(tf_donGia);
		
		lb_anh = new JLabel("");
		lb_anh.setIcon(new ImageIcon(ThemPhone.class.getResource("/images/icon_image.png")));
		lb_anh.setBounds(71, 124, 250, 250);
		add(lb_anh);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinhAnh = ImageUtil.imageToBytes(dialog, lb_anh);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ThemPhone.class.getResource("/images/icon_camera.png")));
		btnNewButton.setBounds(71, 374, 250, 30);
		add(btnNewButton);
		
		bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = tf_ten.getText();
				int slTonKho = Integer.parseInt(tf_slTonKho.getText());
				int slDaBan = Integer.parseInt(tf_slDaBan.getText());
				String theLoai = cb_theloai.getSelectedItem().toString();
				int donGia = Integer.parseInt(tf_donGia.getText());
				
				
				Model_Phone phone = new Model_Phone(0, ten, theLoai, slTonKho, slDaBan, donGia, hinhAnh);
				Service.getInstance().getMain().getBody().getKhosach().themPhone(phone);
				dialog.dispose();
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_them.setBounds(386, 435, 235, 40);
		add(bt_them);
		
		lblNewLabel = new JLabel("THÊM SẢN PHẨM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(263, 31, 449, 46);
		add(lblNewLabel);
	}
}
