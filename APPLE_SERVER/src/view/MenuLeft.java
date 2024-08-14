package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import service.Service;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuLeft extends JPanel{
	
	private JLabel lb_QL_ThongKe;
	private JLabel lb_QL_DoanhThu;
	private JLabel lb_QL_KhachHang;
	private JLabel lb_QL_KhoSach;
	private JLabel lb_QL_NhanVien;
	private JLabel lb_QL_CuaHang;

	public MenuLeft() {
		setSize(300, 840);
		setLayout(null);
		setBackground(new Color(74, 170, 239));
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(new ImageIcon(MenuLeft.class.getResource("/images/logo.png")).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
		lb_logo.setBounds(57, 32, 180, 180);
		add(lb_logo);
		
		lb_QL_CuaHang = new JLabel("QUẢN LÝ CỬA HÀNG");
		lb_QL_CuaHang.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/cuahang.png")));
		lb_QL_CuaHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_CuaHang.setBackground(new Color(34, 133, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_CuaHang.setBackground(new Color(135, 200, 246));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "cuahang");
				reset();
				lb_QL_CuaHang.setBackground(new Color(34, 133, 205));
			}
		});
		lb_QL_CuaHang.setForeground(new Color(255, 255, 255));
		lb_QL_CuaHang.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_CuaHang.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_CuaHang.setBounds(0, 283, 300, 58);
		lb_QL_CuaHang.setBackground(new Color(135, 200, 246));
		lb_QL_CuaHang.setOpaque(true);
		add(lb_QL_CuaHang);
		
		lb_QL_NhanVien = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lb_QL_NhanVien.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/nhanvien.png")));
		lb_QL_NhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_NhanVien.setBackground(new Color(34, 133, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_NhanVien.setBackground(new Color(135, 200, 246));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "nhanvien");
				reset();
				lb_QL_NhanVien.setBackground(new Color(34, 133, 205));
			}
		});
		lb_QL_NhanVien.setOpaque(true);
		lb_QL_NhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_NhanVien.setForeground(Color.WHITE);
		lb_QL_NhanVien.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_NhanVien.setBackground(new Color(135, 200, 246));
		lb_QL_NhanVien.setBounds(0, 345, 300, 58);
		add(lb_QL_NhanVien);
		
		lb_QL_KhoSach = new JLabel("QUẢN LÝ SẢN PHẨM");
		lb_QL_KhoSach.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/sach.png")));
		lb_QL_KhoSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_KhoSach.setBackground(new Color(34, 133, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_KhoSach.setBackground(new Color(135, 200, 246));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "khosach");
				reset();
				lb_QL_KhoSach.setBackground(new Color(34, 133, 205));
			}
		});
		lb_QL_KhoSach.setOpaque(true);
		lb_QL_KhoSach.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_KhoSach.setForeground(Color.WHITE);
		lb_QL_KhoSach.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_KhoSach.setBackground(new Color(135, 200, 246));
		lb_QL_KhoSach.setBounds(0, 407, 300, 58);
		add(lb_QL_KhoSach);
		
		lb_QL_KhachHang = new JLabel("KHÁCH HÀNG");
		lb_QL_KhachHang.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/kahchhang.png")));
		lb_QL_KhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_KhachHang.setBackground(new Color(34, 133, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_KhachHang.setBackground(new Color(135, 200, 246));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "khachhang");
				reset();
				lb_QL_KhachHang.setBackground(new Color(34, 133, 205));
			}
		});
		lb_QL_KhachHang.setOpaque(true);
		lb_QL_KhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_KhachHang.setForeground(Color.WHITE);
		lb_QL_KhachHang.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_KhachHang.setBackground(new Color(135, 200, 246));
		lb_QL_KhachHang.setBounds(0, 470, 300, 58);
		add(lb_QL_KhachHang);
		
		lb_QL_DoanhThu = new JLabel("DOANH THU");
		lb_QL_DoanhThu.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/doanhthu.png")));
		lb_QL_DoanhThu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_DoanhThu.setBackground(new Color(34, 133, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_DoanhThu.setBackground(new Color(135, 200, 246));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "doanhthu");
				reset();
				lb_QL_DoanhThu.setBackground(new Color(34, 133, 205));
			}
		});
		lb_QL_DoanhThu.setOpaque(true);
		lb_QL_DoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_DoanhThu.setForeground(Color.WHITE);
		lb_QL_DoanhThu.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_DoanhThu.setBackground(new Color(135, 200, 246));
		lb_QL_DoanhThu.setBounds(0, 533, 300, 58);
		add(lb_QL_DoanhThu);
		
		lb_QL_ThongKe = new JLabel("THỐNG KÊ");
		lb_QL_ThongKe.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/thongke.png")));
		lb_QL_ThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_QL_ThongKe.setBackground(new Color(34, 133, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_QL_ThongKe.setBackground(new Color(135, 200, 246));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "thongke");
				reset();
				lb_QL_ThongKe.setBackground(new Color(34, 133, 205));
			}
		});
		lb_QL_ThongKe.setOpaque(true);
		lb_QL_ThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QL_ThongKe.setForeground(Color.WHITE);
		lb_QL_ThongKe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_QL_ThongKe.setBackground(new Color(135, 200, 246));
		lb_QL_ThongKe.setBounds(0, 596, 300, 58);
		add(lb_QL_ThongKe);
	}
	
	public void reset() {
		lb_QL_CuaHang.setBackground(new Color(135, 200, 246));
		lb_QL_NhanVien.setBackground(new Color(135, 200, 246));
		lb_QL_KhoSach.setBackground(new Color(135, 200, 246));
		lb_QL_KhachHang.setBackground(new Color(135, 200, 246));
		lb_QL_DoanhThu.setBackground(new Color(135, 200, 246));
		lb_QL_ThongKe.setBackground(new Color(135, 200, 246));
	}
}
