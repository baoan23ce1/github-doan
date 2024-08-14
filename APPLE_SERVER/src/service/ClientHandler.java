package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

public class ClientHandler extends Thread{
    private BufferedReader in;
    private DataOutputStream out;
    private ArrayList<ClientHandler> clients;
    private Service service;
    private int userId;
    private Socket socket;
    private SecretKeySpec aesKey;

    public ClientHandler(int userId, Service service, BufferedReader in, DataOutputStream out, ArrayList<ClientHandler> clients, Socket socket, SecretKey aesKey) {
        this.userId = userId;
    	this.service = service;
    	this.in = in;
        this.out = out;
        this.clients = clients;
        clients.add(this);
        this.socket = socket;
        this.aesKey = new SecretKeySpec(aesKey.getEncoded(), "AES");
        start();
    }
    
    public void sendMessage(JSONObject jsonData) {
        try {
            String encryptedData = encrypt(jsonData.toString());
            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            writer.write(encryptedData + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String encrypt(String data) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new IOException("Failed to encrypt data", e);
        }
    }
    
    private String decrypt(String encryptedData) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IOException("Failed to decrypt data", e);
        }
    }
    
	@Override
	public void run() {
		new Thread(() -> {
	        while (true) {
	            try {
	                String message;
	                synchronized (in) {
	                    message = in.readLine();
	                }
	                if (message != null) {
	                    // Giải mã dữ liệu nhận được trước khi xử lý
	                    String decryptedMessage = decrypt(message);
	                    service.listen(this, decryptedMessage);
	                    System.out.println("Decrypted message: " + decryptedMessage);
	                }
	            } catch (Exception e) {
	                service.getInstance().getMain().getBody().getCuahang().offline(userId);
    	            for (ClientHandler client : clients) {
    	                if (client.getUserId() == userId) {
    	                	clients.remove(client);
    	                	break;
    	                }
    	            }
	                System.out.println("đóng clientHandler");
	                break;
	            }
	        }
		}).start();			
	}
    
	public Socket getSocket() {
		return socket;
	}

	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	public void setClients(ArrayList<ClientHandler> clients) {
		this.clients = clients;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
