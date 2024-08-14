package EnCode;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Model_KhachHang;
import model.Model_NhanVien;
import model.Model_Phone;

public class XMLExporter {

    public static void exportNhanVienListToXML(List<Model_NhanVien> nhanVienList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu trữ");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                filePath += ".xml"; // Đảm bảo rằng tệp có đuôi .xml
            }
            exportNhanVienListToXML(nhanVienList, filePath);
        }
    }

    private static void exportNhanVienListToXML(List<Model_NhanVien> nhanVienList, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("nhanvienlist");
            doc.appendChild(rootElement);

            for (Model_NhanVien nhanVien : nhanVienList) {
                Element nhanVienElement = doc.createElement("nhanvien");
                rootElement.appendChild(nhanVienElement);

                createElementWithValue(doc, nhanVienElement, "maNhanVien", String.valueOf(nhanVien.getMaNhanVien()));
                createElementWithValue(doc, nhanVienElement, "ten", nhanVien.getTen());
                createElementWithValue(doc, nhanVienElement, "cccd", nhanVien.getCccd());
                createElementWithValue(doc, nhanVienElement, "gioiTinh", nhanVien.getGioiTinh());
                createElementWithValue(doc, nhanVienElement, "ngaySinh", formatDate(nhanVien.getNgaySinh()));
                createElementWithValue(doc, nhanVienElement, "sdt", nhanVien.getSdt());
                createElementWithValue(doc, nhanVienElement, "chucVu", nhanVien.getChucVu());
                createElementWithValue(doc, nhanVienElement, "luong", String.valueOf(nhanVien.getLuong()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "File đã được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file.");
        }
    }
    
    public static void exportPhoneListToXML(List<Model_Phone> phoneList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu trữ");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                filePath += ".xml"; 
            }
            exportPhoneListToXML(phoneList, filePath);
        }
    }

    private static void exportPhoneListToXML(List<Model_Phone> phoneList, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("phonelist");
            doc.appendChild(rootElement);

            for (Model_Phone phone : phoneList) {
                Element phoneElement = doc.createElement("phone");
                rootElement.appendChild(phoneElement);

                createElementWithValue(doc, phoneElement, "id", String.valueOf(phone.getId()));
                createElementWithValue(doc, phoneElement, "ten", phone.getTen());
                createElementWithValue(doc, phoneElement, "theLoai", phone.getTheLoai());
                createElementWithValue(doc, phoneElement, "slTonKho", String.valueOf(phone.getSlTonKho()));
                createElementWithValue(doc, phoneElement, "slDaBan", String.valueOf(phone.getSlDaBan()));
                createElementWithValue(doc, phoneElement, "donGia", String.valueOf(phone.getDonGia()));
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "File đã được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file.");
        }
    }
    
    public static void exportKhachHangListToXML(List<Model_KhachHang> khachHangList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu trữ");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                filePath += ".xml"; 
            }
            exportKhachHangListToXML(khachHangList, filePath);
        }
    }

    private static void exportKhachHangListToXML(List<Model_KhachHang> khachHangList, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("khachhanglist");
            doc.appendChild(rootElement);

            for (Model_KhachHang khachHang : khachHangList) {
                Element khachHangElement = doc.createElement("khachhang");
                rootElement.appendChild(khachHangElement);

                createElementWithValue(doc, khachHangElement, "maKhachHang", String.valueOf(khachHang.getMaKhachHang()));
                createElementWithValue(doc, khachHangElement, "ten", khachHang.getTen());
                createElementWithValue(doc, khachHangElement, "sdt", khachHang.getSdt());
                createElementWithValue(doc, khachHangElement, "tongChi", String.valueOf(khachHang.getTongChi()));
                createElementWithValue(doc, khachHangElement, "diemTichLuy", String.valueOf(khachHang.getDiemTichLuy()));
                createElementWithValue(doc, khachHangElement, "hang", khachHang.getHang());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "File đã được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu file.");
        }
    }


    private static void createElementWithValue(Document doc, Element parentElement, String elementName, String value) {
        Element element = doc.createElement(elementName);
        element.appendChild(doc.createTextNode(value));
        parentElement.appendChild(element);
    }

    private static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
