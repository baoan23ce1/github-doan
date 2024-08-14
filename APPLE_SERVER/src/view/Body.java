package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.CardLayout;

public class Body extends JPanel{
	
	private CardLayout cardLayout;
	private QL_DoanhThu doanhthu;
	private QL_SanPham khosach;
	private QL_KhachHang khachhang;
	private QL_NhanVien nhanvien;
	private QL_CuaHang cuahang;
	private QL_Thong_Ke thongke;

	public Body() {
		setSize(1240, 840);
		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		
		cuahang = new QL_CuaHang();
		add(cuahang, "cuahang");	
		
		doanhthu = new QL_DoanhThu();
		add(doanhthu, "doanhthu");
		
		khosach = new QL_SanPham();
		add(khosach, "khosach");
		
		khachhang = new QL_KhachHang();
		add(khachhang, "khachhang");
		
		nhanvien = new QL_NhanVien();
		add(nhanvien, "nhanvien");	
		
		thongke = new QL_Thong_Ke();
		add(thongke, "thongke");
		
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public QL_DoanhThu getDoanhthu() {
		return doanhthu;
	}

	public QL_SanPham getKhosach() {
		return khosach;
	}

	public QL_KhachHang getKhachhang() {
		return khachhang;
	}

	public QL_NhanVien getNhanvien() {
		return nhanvien;
	}

	public QL_CuaHang getCuahang() {
		return cuahang;
	}

	public QL_Thong_Ke getThongke() {
		return thongke;
	}
	
	
	
	
	
}
