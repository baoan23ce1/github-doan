package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Model_Login;
import model.Model_NhanVien;
import model.Model_Register;

public class DBAccount {
	private final Connection con;
	private static DBAccount instance;
	
	private final String CHECK_REGISTER = "SELECT username FROM account WHERE username=?";
	private final String INSERT_USER = "INSERT INTO account (username, `password`, maNhanVien) VALUES (?, ?, 100000000)";
	private final String CHECK_lOGIN = "SELECT username FROM account WHERE username=? AND `password`=?";
	private final String UPDATE_MANHANVIEN = "UPDATE account SET maNhanVien=? WHERE username=?";
	private final String SELECT_USER = "SELECT username FROM account ORDER BY maNhanVien ASC";
	private final String SELECT_NHANVIEN = "SELECT nhanvien.maNhanVien, ten, cccd, gioitinh, ngaysinh, sdt, chucvu, luong "
			+ "FROM nhanvien JOIN account ON nhanvien.maNhanVien = account.maNhanVien WHERE username=?";
	
	public static DBAccount getInstance() {
		if(instance == null) {
			instance = new DBAccount();
		}
		return instance;
	}
	
	public DBAccount() {
        this.con = DatabaseConnection.getInstance().getConnection();
	}
	
	public boolean register(Model_Register register) {
		boolean check = false;
        try {
            PreparedStatement p = con.prepareStatement(CHECK_REGISTER , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            p.setString(1, register.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                check = false;
            } else {
                r.close();
                p.close();
                
                p = con.prepareStatement(INSERT_USER);
                p.setString(1, register.getUserName());
                p.setString(2, register.getPassword());
                p.execute();
                check = true;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return check;
	}
	
	public boolean login(Model_Login login) {
		boolean check = false;
        try {
            PreparedStatement p = con.prepareStatement(CHECK_lOGIN , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            p.setString(1, login.getUserName());
            p.setString(2, login.getPassword());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                check = true;
            } else {
                check = false;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return check;
	}
	
	public void updateMaNhanVien(int maNV) {
        try {
            PreparedStatement p = con.prepareStatement(UPDATE_MANHANVIEN);
            p.setInt(1, maNV);
            p.setString(2, dongcuoi());
            p.execute();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String dongcuoi() {
		String username = ""; 
        try {
            PreparedStatement p = con.prepareStatement(SELECT_USER);
            ResultSet r = p.executeQuery();
            while(r.next() ) {
            	username = r.getString(1);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return username;
	}
	
	public Model_NhanVien getNhanVien(String username) {
		Model_NhanVien nhanvien = null;
        try {
            PreparedStatement p = con.prepareStatement(SELECT_NHANVIEN);
            p.setString(1, username);
            ResultSet r = p.executeQuery();
            while(r.next() ) {
            	int maNhanVien = r.getInt(1);
				String ten = r.getString(2);
				String cccd = r.getString(3);
				String gioiTinh = r.getString(4);
				java.sql.Date ngaySinh = r.getDate(5);		
				String sdt = r.getString(6);
				String chucVu = r.getString(7);
				int luong = r.getInt(8);
            	
            	nhanvien = new Model_NhanVien(maNhanVien, ten, cccd, gioiTinh, ngaySinh, sdt, chucVu, luong);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return nhanvien;
	}
}
