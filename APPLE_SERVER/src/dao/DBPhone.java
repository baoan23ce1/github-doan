package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Model_NhanVien;
import model.Model_Phone;

public class DBPhone {
	private final Connection con;
	private static DBPhone instance;
	private final String INSERT_PHONE= "INSERT INTO phone (ten, Loai, slTonKho, slDaBan, DonGia, HinhAnh) VALUES (?,?,?,?,?,?)";
	private final String SELECT_PHONE = "SELECT id, ten, Loai, slTonKho, slDaBan, DonGia, hinhAnh FROM phone";
	private final String UPDATE_THONGTIN = "UPDATE phone SET ten=?, loai=?,sltonkho=?, sldaban=?, dongia=? WHERE id=?";
	private final String DELETE_PHONE = "DELETE FROM phone WHERE id=?";
	private final String SELECT_IMAGE = "SELECT hinhanh FROM phone WHERE id=?";
	private final String SELECT_TIMKIEM_PHONE = "SELECT id, ten, Loai, slTonKho, slDaBan, DonGia FROM phone WHERE ten LIKE ?";
	private final String UPDATE_SOLUONG = "UPDATE phone SET sltonkho=?, sldaban=? WHERE id=?";
	private final String SELECT_TIMKIEM_MAPHONE = "SELECT id, ten, Loai, slTonKho, slDaBan, DonGia FROM phone WHERE id=?";
	
	public static DBPhone getInstance() {
		if(instance == null) {
			instance = new DBPhone();
		}
		return instance;
	}
	
	public DBPhone() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public ArrayList<Model_Phone> loadPhone() {
		ArrayList<Model_Phone> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_PHONE);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int tonkho = r.getInt(4);
            	int daban = r.getInt(5);
            	int dongia = r.getInt(6);
                Blob blob = r.getBlob(7);
                byte[] hinhAnh = blob.getBytes(1, (int) blob.length());
            	
				Model_Phone phone = new Model_Phone(ma, ten, theloai, tonkho, daban, dongia, hinhAnh);
            	list.add(phone);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_Phone themPhone(Model_Phone phone) {
        try {
            PreparedStatement p = con.prepareStatement(INSERT_PHONE, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, phone.getTen());
            p.setString(2, phone.getTheLoai());
            p.setInt(3, phone.getSlTonKho());
            p.setInt(4, phone.getSlDaBan());
            p.setInt(5, phone.getDonGia());
            p.setBytes(6, phone.getHinhAnh());
                        
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int id = r.getInt(1);
            phone.setId(id);
            p.close();
            r.close();
            
            JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return phone;
	}
	
	public Model_Phone suaThongTin(Model_Phone phone) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_THONGTIN);
            p.setString(1, phone.getTen());
            p.setString(2, phone.getTheLoai());
            p.setInt(3, phone.getSlTonKho());
            p.setInt(4, phone.getSlDaBan());
            p.setInt(5, phone.getDonGia());
            p.setInt(6, phone.getId());
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
        return phone;
	}
	
	public void xoaPhone(int id) {
        try {
            PreparedStatement p = con.prepareStatement(DELETE_PHONE);
            p.setInt(1, id);
                        
            p.execute();
            p.close();
            
            JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
          	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại XXX", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
	}
	
	public byte[] getImage(int id) {
        try {
            PreparedStatement p = con.prepareStatement(SELECT_IMAGE);
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Blob blob = r.getBlob(1);
                return blob.getBytes(1, (int) blob.length());
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public ArrayList<Model_Phone> locPhone(String dieukien) {
		ArrayList<Model_Phone> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_PHONE + " WHERE " + dieukien);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int tonkho = r.getInt(4);
            	int daban = r.getInt(5);
            	int dongia = r.getInt(6);
            	
				Model_Phone phone = new Model_Phone(ma, ten, theloai, tonkho, daban, dongia, null);
            	list.add(phone);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public ArrayList<Model_Phone> timkiem(String ten) {
		ArrayList<Model_Phone> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_PHONE);
            p.setString(1, ten);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String tenSP = r.getString(2);
            	String theloai = r.getString(3);
            	int tonkho = r.getInt(4);
            	int daban = r.getInt(5);
            	int dongia = r.getInt(6);
            	
				Model_Phone phone = new Model_Phone(ma, tenSP, theloai, tonkho, daban, dongia, null);
            	list.add(phone);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
	}
	
	public Model_Phone timkiemMaSP(int id) {
        try {
            PreparedStatement p = con.prepareStatement(SELECT_TIMKIEM_MAPHONE);
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int ma = r.getInt(1);
            	String ten = r.getString(2);
            	String theloai = r.getString(3);
            	int tonkho = r.getInt(4);
            	int daban = r.getInt(5);
            	int dongia = r.getInt(6);
            	
				Model_Phone phone = new Model_Phone(ma, ten, theloai, tonkho, daban, dongia, null);
				return phone;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public Model_Phone updateSoLuong(int ma, int soluong) {
		Model_Phone phone = timkiemMaSP(ma);
		int tonkho = phone.getSlTonKho();
		int daban = phone.getSlDaBan();
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_SOLUONG);
            p.setInt(1, tonkho-soluong);
            p.setInt(2, daban+soluong);
            p.setInt(3, ma);
                        
            p.execute();
            p.close();
         } catch (SQLException e) {
          	e.printStackTrace();
          }
        return phone;
	}
}
