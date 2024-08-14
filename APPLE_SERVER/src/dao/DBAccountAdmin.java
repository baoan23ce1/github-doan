package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Model_DoanhThu;
import model.Model_Login;
import model.Model_Register;

public class DBAccountAdmin {
	private final Connection con;
	private static DBAccountAdmin instance;
	
	private final String CHECK_REGISTER = "SELECT username FROM account_admin WHERE username=?";
	private final String INSERT_USER = "INSERT INTO account_admin (username, `password`) VALUES (?, ?)";
	private final String CHECK_lOGIN = "SELECT username FROM account_admin WHERE username=? AND `password`=?";
	
	public static DBAccountAdmin getInstance() {
		if(instance == null) {
			instance = new DBAccountAdmin();
		}
		return instance;
	}
	
	public DBAccountAdmin() {
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
}
