package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_DoanhThu;
import model.Model_DonMua;
import model.Model_NhanVien;
import model.Model_Phone;

public class DBDoanhThu {
	private final Connection con;
	private static DBDoanhThu instance;
	
	private final String SELECT_DONMUA = "SELECT donmua.MaDonMua, phone.HinhAnh, phone.Ten, khachhang.Ten, donmua.SoLuong, donmua.NgayMua\r\n"
			+ "FROM donmua JOIN khachhang ON donmua.MaKhachHang = khachhang.MaKhachHang\r\n"
			+ "JOIN phone ON donmua.maSP = phone.id ORDER BY donmua.MaDonMua ASC";
	private final String SELECT_TIMKIEM_DOMMUA = "SELECT donmua.MaDonMua, phone.HinhAnh, phone.Ten, khachhang.Ten, donmua.SoLuong, donmua.NgayMua\r\n"
			+ "FROM donmua JOIN khachhang ON donmua.MaKhachHang = khachhang.MaKhachHang\r\n"
			+ "JOIN phone ON donmua.MaSP = phone.id "
			+ "WHERE donmua.ngaymua BETWEEN ? AND ?";
	private final String SELECT_GIASACH = "SELECT dongia FROM phone JOIN donmua ON phone.id = donmua.maSP WHERE maDonMua=?";
	private final String SELECT_THONGKE_DOMMUA = "SELECT phone.dongia, donmua.SoLuong\r\n"
			+ "FROM donmua JOIN phone ON donmua.MaSP = phone.id "
			+ "WHERE donmua.ngaymua BETWEEN ? AND ?";
	private final String INSERT_DONMUA = "INSERT INTO donmua (makhachhang, maSP, soluong, ngaymua) VALUES (?,?,?,?)";
	
	public static DBDoanhThu getInstance() {
		if(instance == null) {
			instance = new DBDoanhThu();
		}
		return instance;
	}
	
	public DBDoanhThu() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_DoanhThu> loadDonMua() {
		ArrayList<Model_DoanhThu> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_DONMUA);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maDonMua = r.getInt(1);
            	Blob blob = r.getBlob(2);
                byte[] image =  blob.getBytes(1, (int) blob.length());
                String tenSach = r.getString(3);
                String tenKhachHang = r.getString(4);
                int soluong = r.getInt(5);
                Date ngaymua = r.getDate(6);
            	
            	Model_DoanhThu sach = new Model_DoanhThu(maDonMua, image, tenSach, tenKhachHang, soluong, ngaymua);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_DoanhThu> timkiem(Date from, Date to) {
		ArrayList<Model_DoanhThu> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_DOMMUA);
            p.setDate(1, from);
            p.setDate(2, to);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maDonMua = r.getInt(1);
            	Blob blob = r.getBlob(2);
                byte[] image =  blob.getBytes(1, (int) blob.length());
                String tenSach = r.getString(3);
                String tenKhachHang = r.getString(4);
                int soluong = r.getInt(5);
                Date ngaymua = r.getDate(6);
            	
            	Model_DoanhThu sach = new Model_DoanhThu(maDonMua, image, tenSach, tenKhachHang, soluong, ngaymua);
            	list.add(sach);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public int thongkeDoanhThu(Date from, Date to) {
		int doanhthu = 0;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THONGKE_DOMMUA);
            p.setDate(1, from);
            p.setDate(2, to);
            
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int dongia = r.getInt(1);
            	int soluong = r.getInt(2);
            	
            	doanhthu += dongia * soluong;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return doanhthu;
	}
	
	public int thongkeSoLuong(Date from, Date to) {
		int soluong = 0;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THONGKE_DOMMUA);
            p.setDate(1, from);
            p.setDate(2, to);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int sl = r.getInt(2);
            	
            	soluong += sl;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return soluong;
	}
	
	public int giaSach(int maDonMua) {
		int gia = 0;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_GIASACH);
            p.setInt(1, maDonMua);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	gia = r.getInt(1);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gia;
	}
	
	public void themDonMua(Model_DonMua donmua) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_DONMUA);
            p.setInt(1, donmua.getMaKhachHang());
            p.setInt(2, donmua.getMaSach());
            p.setInt(3, donmua.getSoluong());
            p.setDate(4, donmua.getNgayMua());
            p.execute();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
