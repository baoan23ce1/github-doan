package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import EnCode.AESUtil;
import model.Model_DonMua;
import model.Model_KhachHang;
import model.Model_Register;
import model.Model_Phone;
import view.Main;

public class Service {
    private static Service instance;
    private Socket client;
    private final int PORT_NUMBER = 1016;
    private final String IP = "localhost";
    private BufferedReader in;
    private DataOutputStream out;
    private Main main;
    private AESUtil aesUtil;

    public static Service getInstance(Main main) {
        if (instance == null) {
            instance = new Service(main);
        }
        return instance;
    }

    public static Service getInstance() {
        return instance;
    }

    private Service(Main main) {
        this.main = main;
    }

    public void startClient() {
        try {
            client = new Socket(IP, PORT_NUMBER);
            in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            out = new DataOutputStream(client.getOutputStream());

            // Nhận và lưu trữ khóa AES từ server
            String base64Key = in.readLine();
            System.out.println("Received AES Key: " + base64Key);
            aesUtil = new AESUtil(base64Key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                try {
                    String encryptedMessage;
                    synchronized (in) {
                        encryptedMessage = in.readLine();
                    }
                    if (encryptedMessage != null) {
                        System.out.println("Received encrypted message: " + encryptedMessage);
                        // Giải mã thông điệp
                        String message = aesUtil.decrypt(encryptedMessage);
                        System.out.println("Decrypted message: " + message);
                        listen(message);
                    } else {
                        System.out.println("Client disconnected");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void listen(String newdata) {
        JSONObject jsonData;
        String data = new String(newdata);
        try {
            jsonData = new JSONObject(data);

            if (jsonData.getString("type").equals("register")) {
                main.getLogin().checkRegister(jsonData.getBoolean("check"));
            } else if (jsonData.getString("type").equals("login")) {
                main.getLogin().checkLogin(jsonData.getBoolean("check"));
            } else if (jsonData.getString("type").equals("listSach")) {
                main.getBody().getListPhone().removeAll();
                JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    Model_Phone phone = new Model_Phone(json);
                    main.getBody().addPhone(phone);
                }
            } else if (jsonData.getString("type").equals("update")) {
                String nhanVien = jsonData.getString("nhanVien");
                int quay = jsonData.getInt("quay");
                main.getMenuLeft().update(nhanVien, quay);
            } else if (jsonData.getString("type").equals("tracuu_true")) {
                Model_KhachHang khachhang = new Model_KhachHang(jsonData);
                main.getMenuLeft().tracuu(khachhang);
            } else if (jsonData.getString("type").equals("tracuu_false")) {
                JOptionPane.showMessageDialog(null, "Khách hàng chưa là thành viên của shop!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else if (jsonData.getString("type").equals("themThanhVien")) {
                Model_KhachHang khachhang = new Model_KhachHang(jsonData);
                main.getMenuLeft().tracuu(khachhang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEncryptedMessage(JSONObject jsonData) {
        new Thread(() -> {
            try {
                // Mã hóa thông điệp
                String encryptedData = aesUtil.encrypt(jsonData.toString());
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(encryptedData + "\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void register(JSONObject jsonData) {
        sendEncryptedMessage(jsonData);
    }

    public void login(JSONObject jsonData, int quay) {
        try {
            jsonData.put("quay", quay);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendEncryptedMessage(jsonData);
    }

    public void themNhanVien(JSONObject jsonData) {
        sendEncryptedMessage(jsonData);
    }

    public void tracuu(String sdt) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "tracuu");
            json.put("sdt", sdt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendEncryptedMessage(json);
    }

    public void themThanhVien(JSONObject jsonData) {
        sendEncryptedMessage(jsonData);
    }

    public void xuatHoaDonKhachHang(JSONObject jsonData) {
        sendEncryptedMessage(jsonData);
    }

    public void xuatHoaDonSach(ArrayList<Model_DonMua> donmuaList) {
        JSONArray jsonArray = new JSONArray();
        for (Model_DonMua donmua : donmuaList) {
            jsonArray.put(donmua.toJsonObject("xuatHoaDonSach"));
        }
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("type", "xuatHoaDonSach");
            jsonData.put("jsonArray", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendEncryptedMessage(jsonData);
    }

    public Socket getClient() {
        return client;
    }

    public Main getMain() {
        return main;
    }
}
