package model;

import org.json.JSONObject;

public class Model_KhachHang {
	private int maKhachHang;
	private String ten;
	private String sdt;
	private int tongChi;
	private int diemTichLuy;
	private String hang;
	
	public Model_KhachHang(int maKhachHang, String ten, String sdt, int tongChi, int diemTichLuy, String hang) {
		this.maKhachHang = maKhachHang;
		this.ten = ten;
		this.sdt = sdt;
		this.tongChi = tongChi;
		this.diemTichLuy = diemTichLuy;
		this.hang = hang;
	}
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("maKhachHang", maKhachHang);
			json.put("ten", ten);
			json.put("sdt", sdt);
			json.put("tongChi", tongChi);
			json.put("diemTichLuy", diemTichLuy);
			json.put("hang", hang);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
	public Model_KhachHang(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	maKhachHang = obj.getInt("maKhachHang");
        	ten = obj.getString("ten");
        	sdt = obj.getString("sdt");
        	tongChi = obj.getInt("tongChi");
        	diemTichLuy = obj.getInt("diemTichLuy");
        	hang = obj.getString("hang");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public int getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getTongChi() {
		return tongChi;
	}
	public void setTongChi(int tongChi) {
		this.tongChi = tongChi;
	}
	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}


	public String getHang() {
		return hang;
	}


	public void setHang(String hang) {
		this.hang = hang;
	}
	
	
}
