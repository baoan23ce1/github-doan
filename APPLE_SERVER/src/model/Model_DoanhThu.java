package model;

import java.sql.Date;

public class Model_DoanhThu {
	private int maDonMua;
	private byte[] hinhAnh;
	private String tenSach;
	private String tenKhachHang;
	private int soluong;
	private Date ngayMua;
	
	public Model_DoanhThu(int maDonMua, byte[] hinhAnh, String tenSach, String tenKhachHang, int soluong, Date ngayMua) {
		this.maDonMua = maDonMua;
		this.hinhAnh = hinhAnh;
		this.tenSach = tenSach;
		this.tenKhachHang = tenKhachHang;
		this.soluong = soluong;
		this.ngayMua = ngayMua;
	}

	public int getMaDonMua() {
		return maDonMua;
	}

	public void setMaDonMua(int maDonMua) {
		this.maDonMua = maDonMua;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public Date getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	
	
	
	
	
}
