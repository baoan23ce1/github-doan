package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_KhachHang;
import model.Model_NhanVien;

public class DBKhachHang {
	private final Connection con;
	private static DBKhachHang instance;
	private final String INSERT_THANHVIEN = "INSERT INTO khachhang (ten, sdt, tongChi, diemTichLuy, hangThanhVien) VALUES (?,?,?,?,?)";
	private final String SELECT_THANHVIEN = "SELECT maKhachHang, ten, sdt, tongChi, diemTichLuy, hangThanhVien FROM khachhang";
	private final String UPDATE_THONGTIN = "UPDATE khachhang SET ten=?, sdt=?, tongChi=?, diemTichLuy=?, hangThanhVien=? WHERE maKhachHang=?";
	private final String DELETE_THANHVIEN = "DELETE FROM khachhang WHERE maKhachHang=?";
	private final String SELECT_TIMKIEM_NHANVIEN = "SELECT maNhanVien, ten, cccd, gioitinh, ngaysinh, sdt, chucvu, luong FROM nhanvien WHERE ten LIKE ?";
	private final String SELECT_TIMKIEM_MAKH = "SELECT maKhachHang, ten, sdt, tongChi, diemTichLuy, hangThanhVien FROM khachhang WHERE makhachhang=?";
	private final String SELECT_TIMKIEM_SDT = "SELECT maKhachHang, ten, sdt, tongChi, diemTichLuy, hangThanhVien FROM khachhang  WHERE sdt LIKE ?";
	private final String SELECT_TRACUU_SDT = "SELECT maKhachHang, ten, sdt, tongChi, diemTichLuy, hangThanhVien FROM khachhang  WHERE sdt=?";
	
	public static DBKhachHang getInstance() {
		if(instance == null) {
			instance = new DBKhachHang();
		}
		return instance;
	}
	
	public DBKhachHang() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_KhachHang> loadThanhVien() {
		ArrayList<Model_KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THANHVIEN);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int tongChi = r.getInt(4);
            	int diem = r.getInt(5);
            	String hang = r.getString(6);
            	
				Model_KhachHang tv = new Model_KhachHang(maKhachHang, ten, sdt, tongChi, diem, hang);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_KhachHang themThanhVien(Model_KhachHang thanhVien) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_THANHVIEN, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, thanhVien.getTen());
            p.setString(2, thanhVien.getSdt());
            p.setInt(3, thanhVien.getTongChi());
            p.setInt(4, thanhVien.getDiemTichLuy());
            p.setString(5, thanhVien.getHang());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maKhanhHang = r.getInt(1);
            thanhVien.setMaKhachHang(maKhanhHang);
            p.close();
            r.close();
            
            JOptionPane.showMessageDialog(null, "Đã thêm thành viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm thành viên thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return thanhVien;
	}
	
	public Model_KhachHang themThanhVien2(Model_KhachHang thanhVien) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_THANHVIEN, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, thanhVien.getTen());
            p.setString(2, thanhVien.getSdt());
            p.setInt(3, thanhVien.getTongChi());
            p.setInt(4, thanhVien.getDiemTichLuy());
            p.setString(5, thanhVien.getHang());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maKhanhHang = r.getInt(1);
            thanhVien.setMaKhachHang(maKhanhHang);
            p.close();
            r.close();
            
          } catch (SQLException e) {
          	e.printStackTrace();
          }
        return thanhVien;
	}
	
	public Model_KhachHang suaThongTin(Model_KhachHang khachHang) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, khachHang.getTen());
            p.setString(2, khachHang.getSdt());
            p.setInt(3, khachHang.getTongChi());
            p.setInt(4, khachHang.getDiemTichLuy());
            p.setString(5, khachHang.getHang());
            p.setInt(6, khachHang.getMaKhachHang());
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return khachHang;
	}
	
	public Model_KhachHang suaThongTin2(Model_KhachHang khachHang) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, khachHang.getTen());
            p.setString(2, khachHang.getSdt());
            p.setInt(3, khachHang.getTongChi());
            p.setInt(4, khachHang.getDiemTichLuy());
            p.setString(5, khachHang.getHang());
            p.setInt(6, khachHang.getMaKhachHang());
                        
            p.execute();
            p.close();
            
          } catch (SQLException e) {
          	e.printStackTrace();
          }
        return khachHang;
	}
	
	public void xoaThanhVien(int maKhachHang) {
        try {
            PreparedStatement p = con.prepareStatement(DELETE_THANHVIEN);
            p.setInt(1, maKhachHang);
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã xóa thành viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa thành viên thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
	}
	
	public ArrayList<Model_KhachHang> locThanhVien(String dieukien) {
		ArrayList<Model_KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_THANHVIEN + " WHERE " + dieukien);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int tongChi = r.getInt(4);
            	int diem = r.getInt(5);
            	String hang = r.getString(6);
            	
				Model_KhachHang tv = new Model_KhachHang(maKhachHang, ten, sdt, tongChi, diem, hang);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_KhachHang> timkiemMaKH(int ma) {
		ArrayList<Model_KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_MAKH);
            p.setInt(1, ma);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int tongChi = r.getInt(4);
            	int diem = r.getInt(5);
            	String hang = r.getString(6);
            	
				Model_KhachHang tv = new Model_KhachHang(maKhachHang, ten, sdt, tongChi, diem, hang);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_KhachHang> timkiemSdt(String sodienthoai) {
		ArrayList<Model_KhachHang> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_SDT);
            p.setString(1, sodienthoai);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int tongChi = r.getInt(4);
            	int diem = r.getInt(5);
            	String hang = r.getString(6);
            	
				Model_KhachHang tv = new Model_KhachHang(maKhachHang, ten, sdt, tongChi, diem, hang);
            	list.add(tv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_KhachHang tracuu(String sodienthoai) {
		Model_KhachHang khachhang = null;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TRACUU_SDT);
            p.setString(1, sodienthoai);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maKhachHang = r.getInt(1);
            	String ten = r.getString(2);
            	String sdt = r.getString(3);
            	int tongChi = r.getInt(4);
            	int diem = r.getInt(5);
            	String hang = r.getString(6);
            	
				khachhang = new Model_KhachHang(maKhachHang, ten, sdt, tongChi, diem, hang);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return khachhang;
	}
}
