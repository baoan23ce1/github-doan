package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EnCode.ImageUtil;
import model.Model_DonMua;
import model.Model_Phone;
import service.Service;
import java.awt.SystemColor;

public class ChiTiet extends JPanel{
	private JDialog dialog;
	private JTextField tf_tenSach;
	private JTextField tf_slTonKho;
	private JTextField tf_donGia;
	private JLabel lb_anh;
	private JButton bt_them;
	private byte[] hinhAnh;
	private JTextField tf_soluong;
	private Model_Phone sach;
	private JTextField tf_theloai;
	
	public ChiTiet(JDialog dialog, Model_Phone sach) {
		this.sach = sach;
		this.dialog = dialog;
		setBackground(new Color(255, 255, 255));
		setSize(950, 450);
		setLayout(null);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTnSch.setBounds(366, 53, 145, 30);
		add(lblTnSch);
		
		tf_tenSach = new JTextField(sach.getTen());
		tf_tenSach.setBackground(new Color(255, 255, 255));
		tf_tenSach.setEditable(false);
		tf_tenSach.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_tenSach.setColumns(10);
		tf_tenSach.setBounds(521, 53, 359, 40);
		add(tf_tenSach);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblThLoi.setBounds(366, 128, 145, 30);
		add(lblThLoi);
		
		String[] itemTheLoai = { "IPhone", "IPad", "MacBook", "Apple Watch" };
		
		JLabel lblSlTnKho = new JLabel("SL tồn kho");
		lblSlTnKho.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSlTnKho.setBounds(366, 199, 145, 30);
		add(lblSlTnKho);
		
		tf_slTonKho = new JTextField(sach.getSlTonKho() + "");
		tf_slTonKho.setBackground(new Color(255, 255, 255));
		tf_slTonKho.setEditable(false);
		tf_slTonKho.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_slTonKho.setColumns(10);
		tf_slTonKho.setBounds(521, 197, 359, 40);
		add(tf_slTonKho);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblnGi.setBounds(366, 273, 145, 30);
		add(lblnGi);
		
		tf_donGia = new JTextField(sach.getDonGia()+ "");
		tf_donGia.setBackground(new Color(255, 255, 255));
		tf_donGia.setEditable(false);
		tf_donGia.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_donGia.setColumns(10);
		tf_donGia.setBounds(521, 271, 359, 40);
		add(tf_donGia);
		
		lb_anh = new JLabel("");
        ImageUtil.setImageLabelFromBytes(sach.getHinhAnh(), lb_anh, 250, 250);
		lb_anh.setBackground(SystemColor.activeCaption);
		lb_anh.setOpaque(true);
		lb_anh.setBounds(70, 53, 250, 250);
		add(lb_anh);
		
		bt_them = new JButton("THÊM");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(tf_soluong.getText()) > 0) {
			        long millis = System.currentTimeMillis();
			        Date currentDate = new Date(millis);
					Model_DonMua donmua = new Model_DonMua(sach.getId(), 0, sach.getId(), sach.getTen(), sach.getDonGia(),Integer.parseInt(tf_soluong.getText()), currentDate);
					Service.getInstance().getMain().getBody().themDonMua(donmua, sach.getHinhAnh());
					Service.getInstance().getMain().getMenuLeft().themDonMua(donmua);
					Service.getInstance().getMain().getMenuLeft().getDonmuaList().add(donmua);
					dialog.dispose();
				}
			}
		});
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_them.setBounds(384, 370, 235, 56);
		add(bt_them);
		
		JButton bt_tru = new JButton("");
		bt_tru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(tf_soluong.getText()) > 0) {
					tf_soluong.setText((Integer.parseInt(tf_soluong.getText())-1) + "" );
				}
			}
		});
		bt_tru.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_tru.png")));
		bt_tru.setFont(new Font("Tahoma", Font.BOLD, 10));
		bt_tru.setBounds(70, 313, 40, 40);
		add(bt_tru);
		
		JButton bt_cong = new JButton("");
		bt_cong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(tf_soluong.getText()) < Integer.parseInt(tf_slTonKho.getText())) {
					tf_soluong.setText((Integer.parseInt(tf_soluong.getText())+1) + "" );
				}
			}
		});
		bt_cong.setIcon(new ImageIcon(ChiTiet.class.getResource("/images/icon_cong.png")));
		bt_cong.setFont(new Font("Tahoma", Font.BOLD, 10));
		bt_cong.setBounds(280, 313, 40, 40);
		add(bt_cong);
		
		tf_soluong = new JTextField();
		tf_soluong.setHorizontalAlignment(SwingConstants.CENTER);
		tf_soluong.setText("1");
		tf_soluong.setBackground(new Color(255, 255, 255));
		tf_soluong.setEditable(false);
		tf_soluong.setFont(new Font("Tahoma", Font.BOLD, 33));
		tf_soluong.setBounds(123, 313, 145, 40);
		add(tf_soluong);
		tf_soluong.setColumns(10);
		
		tf_theloai = new JTextField(sach.getTheLoai());
		tf_theloai.setBackground(new Color(255, 255, 255));
		tf_theloai.setEditable(false);
		tf_theloai.setFont(new Font("Tahoma", Font.BOLD, 24));
		tf_theloai.setColumns(10);
		tf_theloai.setBounds(521, 124, 359, 40);
		add(tf_theloai);
		
		setBackground(new Color(150, 220, 248));
	}

	public Model_Phone getSach() {
		return sach;
	}
	
	
}
