package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import EnCode.QRCodePaymentGenerator;
import model.Model_DonMua;
import model.Model_KhachHang;
import service.Service;
import swing.PlaceholderTextField;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MenuLeft extends JPanel{
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JTextField tf_diem;
	private JTextField tf_dongia;
	private JTextField tf_giamgia;
	private JTextField tf_thanhtoan;
	private JLabel lb_username;
	private JLabel lb_quay;
	private PlaceholderTextField tf_sdt;
	private Model_KhachHang khachhang;
	private ArrayList<Model_DonMua> donmuaList;
	
	public MenuLeft() {
		setSize(300, 840);
		setLayout(null);
		setBackground(new Color(74, 170, 239));
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(new ImageIcon(MenuLeft.class.getResource("/images/user_account.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		lb_logo.setBounds(72, 31, 150, 150);
		add(lb_logo);
		
		donmuaList = new ArrayList<>();
		
		lb_username = new JLabel("ĐÍNH DƯƠNG");
		lb_username.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_username.setHorizontalAlignment(SwingConstants.CENTER);
		lb_username.setBounds(10, 185, 280, 28);
		add(lb_username);
		
		JLabel lblNewLabel = new JLabel("Mã KH");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 261, 82, 28);
		add(lblNewLabel);
		
		JLabel lblTnKh = new JLabel("Tên KH");
		lblTnKh.setForeground(Color.WHITE);
		lblTnKh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnKh.setBounds(10, 296, 82, 28);
		add(lblTnKh);
		
		JLabel lblStKh = new JLabel("SĐT KH");
		lblStKh.setForeground(Color.WHITE);
		lblStKh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStKh.setBounds(10, 334, 82, 28);
		add(lblStKh);
		
		JLabel lblim = new JLabel("Điểm TL");
		lblim.setForeground(Color.WHITE);
		lblim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblim.setBounds(10, 372, 82, 28);
		add(lblim);
		
		tf_ma = new JTextField();
		tf_ma.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ma.setBounds(107, 261, 183, 28);
		add(tf_ma);
		tf_ma.setColumns(10);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setColumns(10);
		tf_ten.setBounds(107, 296, 183, 28);
		add(tf_ten);
		
		tf_sdt = new PlaceholderTextField("Tra cứu...");
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(107, 334, 183, 28);
		add(tf_sdt);
		
		tf_diem = new JTextField();
		tf_diem.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_diem.setColumns(10);
		tf_diem.setBounds(107, 372, 183, 28);
		add(tf_diem);
		
		JButton bt_tracuu = new JButton("TRA CỨU");
		bt_tracuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tf_sdt.equals("Tra cứu...")) {
					Service.getInstance().tracuu(tf_sdt.getText());
				}
			}
		});
		bt_tracuu.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_tracuu.setBounds(10, 423, 110, 36);
		add(bt_tracuu);
		
		JButton bt_doidiem = new JButton("ĐỔI ĐIỂM");
		bt_doidiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(tf_diem.getText()) > 0) {
					int diem = Integer.parseInt(tf_diem.getText());
					int gia = Integer.parseInt(tf_dongia.getText());
					if(diem*1000 <= gia) {
						tf_diem.setText("0");
						tf_giamgia.setText(diem*1000 + "");
		                tf_thanhtoan.setText(Integer.parseInt(tf_dongia.getText()) - Integer.parseInt(tf_giamgia.getText()) + "");
					}
					else {
						diem = diem - gia/1000;
						tf_diem.setText(diem + "");
						tf_giamgia.setText(gia + "");
		                tf_thanhtoan.setText(Integer.parseInt(tf_dongia.getText()) - Integer.parseInt(tf_giamgia.getText()) + "");
					}
				}
			}
		});
		bt_doidiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_doidiem.setBounds(180, 423, 110, 36);
		add(bt_doidiem);
		
		JButton bt_them = new JButton("");
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	JDialog dialog = new JDialog();
	        	ThemThanhVien them = new ThemThanhVien(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(650, 450);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_them.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/icon_cong.png")));
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_them.setBounds(130, 423, 40, 40);
		add(bt_them);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setForeground(Color.WHITE);
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblnGi.setBounds(10, 477, 110, 28);
		add(lblnGi);
		
		JLabel lblGimGi = new JLabel("Giảm giá");
		lblGimGi.setForeground(Color.WHITE);
		lblGimGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGimGi.setBounds(10, 515, 110, 28);
		add(lblGimGi);
		
		JLabel lblThanhTon = new JLabel("Thanh toán");
		lblThanhTon.setForeground(Color.WHITE);
		lblThanhTon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThanhTon.setBounds(10, 553, 125, 28);
		add(lblThanhTon);
		
		tf_dongia = new JTextField("0");
		tf_dongia.setBackground(new Color(255, 255, 255));
		tf_dongia.setEditable(false);
		tf_dongia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_dongia.setColumns(10);
		tf_dongia.setBounds(140, 477, 150, 28);
		add(tf_dongia);
		
		tf_giamgia = new JTextField("0");
		tf_giamgia.setBackground(new Color(255, 255, 255));
		tf_giamgia.setEditable(false);
		tf_giamgia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_giamgia.setColumns(10);
		tf_giamgia.setBounds(140, 515, 150, 28);
		add(tf_giamgia);
//		tf_giamgia.getDocument().addDocumentListener(new DocumentListener() {
//            public void insertUpdate(DocumentEvent e) {
//                tf_thanhtoan.setText(Integer.parseInt(tf_dongia.getText()) - Integer.parseInt(tf_giamgia.getText()) + "");
//            }
//            public void removeUpdate(DocumentEvent e) {
//                tf_thanhtoan.setText(Integer.parseInt(tf_dongia.getText()) - Integer.parseInt(tf_giamgia.getText()) + "");
//            }
//            public void changedUpdate(DocumentEvent e) {
//                tf_thanhtoan.setText(Integer.parseInt(tf_dongia.getText()) - Integer.parseInt(tf_giamgia.getText()) + "");
//            }
//        });
		
		tf_thanhtoan = new JTextField("0");
		tf_thanhtoan.setBackground(new Color(255, 255, 255));
		tf_thanhtoan.setEditable(false);
		tf_thanhtoan.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_thanhtoan.setColumns(10);
		tf_thanhtoan.setBounds(140, 553, 150, 28);
		add(tf_thanhtoan);
		
		JButton bt_xuathoadon = new JButton("XUẤT HÓA ĐƠN");
		bt_xuathoadon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatHoaDon();
			}
		});
		bt_xuathoadon.setFont(new Font("Tahoma", Font.BOLD, 14));
		bt_xuathoadon.setBounds(7, 604, 283, 36);
		add(bt_xuathoadon);
		
		lb_quay = new JLabel("QUẦY SỐ 1");
		lb_quay.setForeground(new Color(0, 0, 0));
		lb_quay.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_quay.setHorizontalAlignment(SwingConstants.CENTER);
		lb_quay.setBounds(0, 763, 300, 41);
		lb_quay.setBackground(new Color(183, 225, 255));
		lb_quay.setOpaque(true);
		add(lb_quay);
				
	}
	
	public void update(String name, int quay) {
		lb_username.setText(name);
		lb_quay.setText("TẦNG " + quay);
	}
	
	public void tracuu(Model_KhachHang khachhang) {
		this.khachhang = khachhang;
		tf_ten.setText(khachhang.getTen());
		tf_sdt.setText(khachhang.getSdt());
		tf_diem.setText(khachhang.getDiemTichLuy() + "");
		tf_ma.setText(khachhang.getMaKhachHang()+"");
	}
	
	public void themDonMua(Model_DonMua donmua) {
		int tien = donmua.getGia() * donmua.getSoluong();
		int dongia = Integer.parseInt(tf_dongia.getText());
		int thanhtoan = Integer.parseInt(tf_thanhtoan.getText());
		dongia += tien;
		thanhtoan += tien;
		tf_dongia.setText(dongia + "");
		tf_thanhtoan.setText(thanhtoan + "");
	}
	
	public void xuatHoaDon() {
		int diem = Integer.parseInt(tf_diem.getText());
		int gia = Integer.parseInt(tf_dongia.getText());
		khachhang.setDiemTichLuy(diem + gia/10000);
		khachhang.setTongChi(khachhang.getTongChi() + gia);
		if(khachhang.getTongChi() < 1000000) {
			khachhang.setHang("Đồng");
		}
		else if(khachhang.getTongChi() < 3000000) {
			khachhang.setHang("Bạc");
		}
		else if(khachhang.getTongChi() < 5000000) {
			khachhang.setHang("Vàng");
		}
		else {
			khachhang.setHang("Kim cương");
		}
		
		Service.getInstance().xuatHoaDonKhachHang(khachhang.toJsonObject("xuatHoaDonKhachHang"));
        
		for(Model_DonMua donmua : donmuaList) { 
        	donmua.setMaKhachHang(khachhang.getMaKhachHang());
        }
		Service.getInstance().xuatHoaDonSach(donmuaList);
		
		printHoaDon();
		reset();

	}
	
	public void printHoaDon() {
    	
    	HoaDon hoadon = new HoaDon();
    	int stt = 1;
    	hoadon.getTextArea().append(String.format("%-7s%-50s%-15s%-6s%-15s\n", "STT", "Tên Sản phẩm", "Đơn giá", "SL", "Thành tiền"));
		for(Model_DonMua donmua : donmuaList) { 
        	hoadon.getTextArea().append(String.format("%-7s%-50s%-15s%-6s%-15s\n", stt++, donmua.getTenSach(), donmua.getGia(), donmua.getSoluong(), donmua.getGia()*donmua.getSoluong()));
        }
    	
		QRCodePaymentGenerator.generatePaymentQRCode(hoadon.getLb_qr(), "0979727604", Integer.parseInt(tf_thanhtoan.getText()));
    	
    	JDialog dialog = new JDialog();
		dialog.getContentPane().setLayout(new GridLayout(1,1));
		dialog.setSize(600, 700);
		dialog.setLocationRelativeTo(null);
    	dialog.getContentPane().add(hoadon);
    	dialog.setVisible(true);
	}
	
	public void reset() {
		tf_ten.setText("");
		tf_sdt.setText("");
		tf_diem.setText("");
		tf_ma.setText("");
		
		tf_dongia.setText("0");
		tf_giamgia.setText("0");
		tf_thanhtoan.setText("0");
		
		donmuaList = new ArrayList();
		
		Service.getInstance().getMain().getBody().getTable_model().setRowCount(0);
	}

	public ArrayList<Model_DonMua> getDonmuaList() {
		return donmuaList;
	}

	public void setDonmuaList(ArrayList<Model_DonMua> donmuaList) {
		this.donmuaList = donmuaList;
	}
	
	
}
