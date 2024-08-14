package EnCode;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodePaymentGenerator {
    public static void generatePaymentQRCode(JLabel label, String accountNumber, int amount) {
//        Map<String, String> paymentData = new HashMap<>();
//        paymentData.put("accountNumber", accountNumber); // Số tài khoản người nhận
//        paymentData.put("amount", String.valueOf(amount)); // Số tiền thanh toán
//        
//        StringBuilder qrDataBuilder = new StringBuilder();
//        for (Map.Entry<String, String> entry : paymentData.entrySet()) {
//            qrDataBuilder.append(entry.getKey()).append(":").append(entry.getValue()).append("|");
//        }
//        String qrData = qrDataBuilder.toString();
    	
    	String qrData = "https://www.facebook.com/profile.php?id=100045865801699";

        try {
            // Tạo mã QR code từ dữ liệu
            BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);

            // Đặt ảnh mã QR code vào JLabel
            ImageIcon icon = new ImageIcon(qrImage);
            label.setIcon(icon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
