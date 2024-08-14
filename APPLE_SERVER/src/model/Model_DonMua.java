package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class Model_DonMua {
	private int maDonMua;
	private int maKhachHang;
	private int maSach;
	private String tenSach;
	private int soluong;
	private int gia;
	private Date ngayMua;
	
	public Model_DonMua(int maDonMua, int maKhachHang, int maSach, String tenSach, int gia, int soluong, Date ngayMua) {
		this.maDonMua = maDonMua;
		this.maKhachHang = maKhachHang;
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.gia = gia;
		this.soluong = soluong;
		this.ngayMua = ngayMua;
	}
	
	public Model_DonMua(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	maDonMua = obj.getInt("maDonMua");
        	maKhachHang = obj.getInt("maKhachHang");
        	maSach = obj.getInt("maSach");
        	tenSach = obj.getString("tenSach");
        	gia = obj.getInt("gia");
        	soluong = obj.getInt("soluong");
        	ngayMua = convertToSqlDate(obj.getString("ngayMua"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("maDonMua", maDonMua);
			json.put("maKhachHang", maKhachHang);
			json.put("maSach", maSach);
			json.put("tenSach", tenSach);
			json.put("gia", gia);
			json.put("soluong", soluong);
			json.put("ngayMua", formatDate(ngayMua));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	
	private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    
    private Date convertToSqlDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(dateString);
            return new Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

	public int getMaDonMua() {
		return maDonMua;
	}

	public void setMaDonMua(int maDonMua) {
		this.maDonMua = maDonMua;
	}

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public int getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
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

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}
	
	
	
	
}