package model;

import org.json.JSONObject;

public class Model_Phone {
	private int id;
	private String ten;
	private String theLoai;
	private int slTonKho;
	private int slDaBan;
	private int donGia;
	private byte[] hinhAnh;
	
	public Model_Phone(int id, String ten, String theLoai, int slTonKho, int slDaBan, int donGia,
			byte[] hinhAnh) {
		this.id = id;
		this.ten = ten;
		this.theLoai = theLoai;
		this.slTonKho = slTonKho;
		this.slDaBan = slDaBan;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
	}
	
	public Model_Phone(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	id = obj.getInt("id");
        	ten = obj.getString("ten");
        	theLoai = obj.getString("theLoai");
        	slTonKho = obj.getInt("slTonKho");
        	slDaBan = obj.getInt("slDaBan");
        	donGia = obj.getInt("donGia");
        	hinhAnh = convertHexStringToByteArray(obj.getString("hinhAnh"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("id", id);
			json.put("ten", ten);
			json.put("theLoai", theLoai);
			json.put("slTonKho", slTonKho);
			json.put("slDaBan", slDaBan);
			json.put("donGia", donGia);
			json.put("hinhAnh", convertByteArrayToHexString(hinhAnh));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    private String convertByteArrayToHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private byte[] convertHexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }


	public int getId() {
		return id;
	}

	public void setId(int maSach) {
		this.id = maSach;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public int getSlTonKho() {
		return slTonKho;
	}

	public void setSlTonKho(int slTonKho) {
		this.slTonKho = slTonKho;
	}

	public int getSlDaBan() {
		return slDaBan;
	}

	public void setSlDaBan(int slDaBan) {
		this.slDaBan = slDaBan;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}


	
	
	
}