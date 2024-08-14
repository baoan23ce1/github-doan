package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_NhanVien;

public class DBNhanVien {
	private final Connection con;
	private static DBNhanVien instance;
	private final String INSERT_NHANVIEN = "INSERT INTO nhanvien (ten, cccd, gioitinh, ngaysinh, sdt, chucvu, luong) VALUES (?,?,?,?,?,?,?)";
	private final String SELECT_NHANVIEN = "SELECT maNhanVien, ten, cccd, gioitinh, ngaysinh, sdt, chucvu, luong FROM nhanvien";
	private final String UPDATE_THONGTIN = "UPDATE nhanvien SET ten=?, cccd=?, gioitinh=?, ngaysinh=?, sdt=?, chucvu=?, luong=? WHERE maNhanVien=?";
	private final String DELETE_NHANVIEN = "DELETE FROM nhanvien WHERE maNhanVien=?";
	private final String SELECT_TIMKIEM_NHANVIEN = "SELECT maNhanVien, ten, cccd, gioitinh, ngaysinh, sdt, chucvu, luong FROM nhanvien WHERE ten LIKE ?";
	
	public static DBNhanVien getInstance() {
		if(instance == null) {
			instance = new DBNhanVien();
		}
		return instance;
	}
	
	public DBNhanVien() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_NhanVien> loadNhanVien() {
		ArrayList<Model_NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_NHANVIEN);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maNhanVien = r.getInt(1);
				String ten = r.getString(2);
				String cccd = r.getString(3);
				String gioiTinh = r.getString(4);
				java.sql.Date ngaySinh = r.getDate(5);		
				String sdt = r.getString(6);
				String chucVu = r.getString(7);
				int luong = r.getInt(8);
            	
            	Model_NhanVien nv = new Model_NhanVien(maNhanVien, ten, cccd, gioiTinh, ngaySinh, sdt, chucVu, luong);
            	list.add(nv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_NhanVien themNhanVien(Model_NhanVien nhanVien) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_NHANVIEN, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, nhanVien.getTen());
            p.setString(2, nhanVien.getCccd());
            p.setString(3, nhanVien.getGioiTinh());
            p.setDate(4, nhanVien.getNgaySinh());
            p.setString(5, nhanVien.getSdt());
            p.setString(6, nhanVien.getChucVu());
            p.setInt(7, nhanVien.getLuong());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maNhanVien = r.getInt(1);
            nhanVien.setMaNhanVien(maNhanVien);
            p.close();
            r.close();
            
            JOptionPane.showMessageDialog(null, "Đã thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return nhanVien;
	}
	
	public Model_NhanVien themNhanVien2(Model_NhanVien nhanVien) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_NHANVIEN, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, nhanVien.getTen());
            p.setString(2, nhanVien.getCccd());
            p.setString(3, nhanVien.getGioiTinh());
            p.setDate(4, nhanVien.getNgaySinh());
            p.setString(5, nhanVien.getSdt());
            p.setString(6, nhanVien.getChucVu());
            p.setInt(7, nhanVien.getLuong());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int maNhanVien = r.getInt(1);
            nhanVien.setMaNhanVien(maNhanVien);
            DBAccount.getInstance().updateMaNhanVien(maNhanVien);
            p.close();
            r.close();
            
          } catch (SQLException e) {
          	e.printStackTrace();
          }
        return nhanVien;
	}
	
	public Model_NhanVien suaThongTin(Model_NhanVien nhanVien) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, nhanVien.getTen());
            p.setString(2, nhanVien.getCccd());
            p.setString(3, nhanVien.getGioiTinh());
            p.setDate(4, nhanVien.getNgaySinh());
            p.setString(5, nhanVien.getSdt());
            p.setString(6, nhanVien.getChucVu());
            p.setInt(7, nhanVien.getLuong());
            p.setInt(8, nhanVien.getMaNhanVien());
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return nhanVien;
	}
	
	public void xoaNhanVien(int maNhanVien) {
        try {
            PreparedStatement p = con.prepareStatement(DELETE_NHANVIEN);
            p.setInt(1, maNhanVien);
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
	}
	
	public ArrayList<Model_NhanVien> timkiem(String name) {
		ArrayList<Model_NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_NHANVIEN);
            p.setString(1, name);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int maNhanVien = r.getInt(1);
				String ten = r.getString(2);
				String cccd = r.getString(3);
				String gioiTinh = r.getString(4);
				java.sql.Date ngaySinh = r.getDate(5);		
				String sdt = r.getString(6);
				String chucVu = r.getString(7);
				int luong = r.getInt(8);
            	
            	Model_NhanVien nv = new Model_NhanVien(maNhanVien, ten, cccd, gioiTinh, ngaySinh, sdt, chucVu, luong);
            	list.add(nv);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
}
