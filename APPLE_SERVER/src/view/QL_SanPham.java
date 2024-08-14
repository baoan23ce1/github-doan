package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.ImageUtil;
import EnCode.XMLExporter;
import dao.DBNhanVien;
import dao.DBPhone;
import model.Model_NhanVien;
import model.Model_Phone;
import swing.PlaceholderTextField;
import view.component.ThemNhanVien;
import view.component.ThemPhone;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_SanPham extends JPanel{
	private JTextField tf_loc_dongia_from;
	private JTextField tf_loc_dongia_to;
	private JTextField tf_loc_daban_from;
	private JTextField tf_loc_daban_to;
	private JTextField tf_loc_tonkho_from;
	private JTextField tf_loc_tonkho_to;
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JTextField tf_slTonKho;
	private JTextField tf_slDaBan;
	private JTextField tf_donGia;
	private JTable table;
	private DefaultTableModel table_model;
	private JComboBox cb_theloai;
	private JLabel lb_hinhAnh;
	private JButton bt_luu;
	private JComboBox tf_loc_theloai;
	private PlaceholderTextField tf_loc_ten;
	
	public QL_SanPham() {
		setBackground(Color.WHITE);
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);	
		
		table_model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"M\u00E3", "T\u00EAn", "Th\u1EC3 lo\u1EA1i", "T\u1ED3n kho", "\u0110\u00E3 b\u00E1n", "\u0110\u01A1n gi\u00E1"
				}
			);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(520);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(40);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 489, 1200, 308);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(15, 83, 392, 376);
		add(panel);
		
		JLabel lblTn = new JLabel("Tên");
		lblTn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTn.setBounds(10, 28, 92, 30);
		panel.add(lblTn);
		
		JLabel lblSt_1 = new JLabel("Thể loại");
		lblSt_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1.setBounds(10, 74, 92, 30);
		panel.add(lblSt_1);
		
		tf_loc_ten = new PlaceholderTextField("Nhập tên sản phẩm...");
		tf_loc_ten.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiem();
            }
            public void removeUpdate(DocumentEvent e) {
                timkiem();
            }
            public void changedUpdate(DocumentEvent e) {
                timkiem();
            }
        });
		tf_loc_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_ten.setColumns(10);
		tf_loc_ten.setBounds(112, 28, 268, 30);
		panel.add(tf_loc_ten);
		
		JButton bt_loc = new JButton("LỌC");
		bt_loc.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/filter.png")));
		bt_loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loc();
			}
		});
		bt_loc.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_loc.setBounds(124, 333, 150, 33);
		panel.add(bt_loc);
		
		String[] itemTheLoaiLoc = {"Tất cả", "IPhone", "IPad", "MacBook", "Apple Watch" };
		tf_loc_theloai = new JComboBox<>(itemTheLoaiLoc);
		tf_loc_theloai.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_theloai.setBounds(112, 74, 268, 30);
		panel.add(tf_loc_theloai);
		
		JLabel lblSt_1_1 = new JLabel("Đơn giá");
		lblSt_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1.setBounds(10, 114, 92, 30);
		panel.add(lblSt_1_1);
		
		tf_loc_dongia_from = new JTextField();
		tf_loc_dongia_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_dongia_from.setColumns(10);
		tf_loc_dongia_from.setBounds(10, 143, 150, 30);
		panel.add(tf_loc_dongia_from);
		
		tf_loc_dongia_to = new JTextField();
		tf_loc_dongia_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_dongia_to.setColumns(10);
		tf_loc_dongia_to.setBounds(230, 143, 150, 30);
		panel.add(tf_loc_dongia_to);
		
		JLabel lblSt_1_1_1 = new JLabel("đến");
		lblSt_1_1_1.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1.setBounds(173, 143, 47, 30);
		panel.add(lblSt_1_1_1);
		
		JLabel lblSt_1_1_2 = new JLabel("Số lượng đã bán");
		lblSt_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_2.setBounds(10, 252, 198, 30);
		panel.add(lblSt_1_1_2);
		
		tf_loc_daban_from = new JTextField();
		tf_loc_daban_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_daban_from.setColumns(10);
		tf_loc_daban_from.setBounds(10, 281, 150, 30);
		panel.add(tf_loc_daban_from);
		
		JLabel lblSt_1_1_1_1 = new JLabel("đến");
		lblSt_1_1_1_1.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1_1.setBounds(173, 281, 47, 30);
		panel.add(lblSt_1_1_1_1);
		
		tf_loc_daban_to = new JTextField();
		tf_loc_daban_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_daban_to.setColumns(10);
		tf_loc_daban_to.setBounds(230, 281, 150, 30);
		panel.add(tf_loc_daban_to);
		
		JLabel lblSt_1_1_3 = new JLabel("Số lượng tồn kho");
		lblSt_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_3.setBounds(10, 183, 222, 30);
		panel.add(lblSt_1_1_3);
		
		tf_loc_tonkho_from = new JTextField();
		tf_loc_tonkho_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_tonkho_from.setColumns(10);
		tf_loc_tonkho_from.setBounds(10, 212, 150, 30);
		panel.add(tf_loc_tonkho_from);
		
		JLabel lblSt_1_1_1_2 = new JLabel("đến");
		lblSt_1_1_1_2.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1_2.setBounds(173, 212, 47, 30);
		panel.add(lblSt_1_1_1_2);
		
		tf_loc_tonkho_to = new JTextField();
		tf_loc_tonkho_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_loc_tonkho_to.setColumns(10);
		tf_loc_tonkho_to.setBounds(230, 212, 150, 30);
		panel.add(tf_loc_tonkho_to);
		
		JLabel lblMSch = new JLabel("Mã SP");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMSch.setBounds(462, 83, 145, 30);
		add(lblMSch);
		
		JLabel lblTnSch = new JLabel("Tên SP");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnSch.setBounds(462, 137, 145, 30);
		add(lblTnSch);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThLoi.setBounds(462, 192, 145, 30);
		add(lblThLoi);
		
		JLabel lblSlTnKho = new JLabel("SL tồn kho");
		lblSlTnKho.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlTnKho.setBounds(462, 246, 145, 30);
		add(lblSlTnKho);
		
		JLabel lblSlBn = new JLabel("SL đã bán");
		lblSlBn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSlBn.setBounds(462, 301, 145, 30);
		add(lblSlBn);
		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblnGi.setBounds(462, 354, 145, 30);
		add(lblnGi);
		
		tf_ma = new JTextField();
		tf_ma.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ma.setColumns(10);
		tf_ma.setBounds(589, 83, 318, 30);
		add(tf_ma);
		
		tf_ten = new JTextField();
		tf_ten.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ten.setColumns(10);
		tf_ten.setBounds(589, 137, 318, 30);
		add(tf_ten);
		
		tf_slTonKho = new JTextField();
		tf_slTonKho.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slTonKho.setColumns(10);
		tf_slTonKho.setBounds(589, 246, 318, 30);
		add(tf_slTonKho);
		
		tf_slDaBan = new JTextField();
		tf_slDaBan.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_slDaBan.setColumns(10);
		tf_slDaBan.setBounds(589, 301, 318, 30);
		add(tf_slDaBan);
		
		tf_donGia = new JTextField();
		tf_donGia.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_donGia.setColumns(10);
		tf_donGia.setBounds(589, 354, 318, 30);
		add(tf_donGia);
		
		String[] itemTheLoai = { "IPhone", "IPad", "MacBook", "Apple Watch" };
		cb_theloai = new JComboBox<>(itemTheLoai);
		cb_theloai.setFont(new Font("Tahoma", Font.BOLD, 20));
		cb_theloai.setBounds(589, 192, 318, 30);
		add(cb_theloai);
		
		lb_hinhAnh = new JLabel("");
		lb_hinhAnh.setBackground(new Color(255, 255, 255));
		lb_hinhAnh.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/icon_image2.png")));
		lb_hinhAnh.setOpaque(true);
		lb_hinhAnh.setBounds(945, 83, 270, 270);
		add(lb_hinhAnh);
		
		JLabel bt_them = new JLabel("THÊM");
		bt_them.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/icon_add.png")));
		bt_them.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
	        	JDialog dialog = new JDialog();
	        	ThemPhone them = new ThemPhone(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(1000, 550);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_them.setOpaque(true);
		bt_them.setHorizontalAlignment(SwingConstants.CENTER);
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_them.setBackground(new Color(2, 119, 185));
		bt_them.setBounds(945, 363, 132, 45);
		add(bt_them);
		
		JLabel bt_sua = new JLabel("SỬA");
		bt_sua.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/edit.png")));
		bt_sua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_ma.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					unreset();
					bt_luu.setVisible(true);
				}
			}
		});
		bt_sua.setOpaque(true);
		bt_sua.setHorizontalAlignment(SwingConstants.CENTER);
		bt_sua.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_sua.setBackground(new Color(188, 219, 0));
		bt_sua.setBounds(1083, 363, 132, 45);
		add(bt_sua);
		
		JLabel bt_xoa = new JLabel("XÓA");
		bt_xoa.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/icon_delete (2).png")));
		bt_xoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_ma.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					xoaPhone(Integer.parseInt(tf_ma.getText()));
					reset();
					loadPhone();
				}

			}
		});
		bt_xoa.setOpaque(true);
		bt_xoa.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_xoa.setBackground(new Color(210, 25, 13));
		bt_xoa.setBounds(945, 414, 132, 45);
		add(bt_xoa);
		
		JLabel bt_excel = new JLabel("XML");
		bt_excel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XMLExporter.exportPhoneListToXML(DBPhone.getInstance().loadPhone());
			}
		});
		bt_excel.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/icon_export.png")));
		bt_excel.setOpaque(true);
		bt_excel.setHorizontalAlignment(SwingConstants.CENTER);
		bt_excel.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_excel.setBackground(new Color(0, 185, 7));
		bt_excel.setBounds(1083, 414, 132, 45);
		add(bt_excel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(462, 405, 442, 2);
		add(separator);
		
		bt_luu = new JButton("LƯU");
		bt_luu.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/save (2).png")));
		bt_luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		suaThongTin();
        		reset();
        		bt_luu.setVisible(false);
        		loadPhone();
			}
		});
		bt_luu.setVisible(false);
		bt_luu.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_luu.setBounds(640, 413, 111, 37);
		add(bt_luu);
		
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { 
                        tf_ma.setText(table.getValueAt(selectedRow, 0).toString());
                        tf_ten.setText(table.getValueAt(selectedRow, 1).toString());
                        cb_theloai.setSelectedItem(table.getValueAt(selectedRow, 2).toString());
                        tf_slTonKho.setText(table.getValueAt(selectedRow, 3).toString());
                        tf_slDaBan.setText(table.getValueAt(selectedRow, 4).toString());
                        tf_donGia.setText(table.getValueAt(selectedRow, 5).toString());
                    
                        ImageUtil.setImageLabelFromBytes(DBPhone.getInstance().getImage(Integer.parseInt(table.getValueAt(selectedRow, 0).toString())), lb_hinhAnh);
                    }
                }
            }
        });
        reset();
		
	}
	
	public void loadPhone() {
		ArrayList<Model_Phone> list = DBPhone.getInstance().loadPhone();
		table_model.setRowCount(0);
		for(Model_Phone phone : list) {
	        Object[] newRow = {phone.getId(), phone.getTen(), phone.getTheLoai(), phone.getSlTonKho(), phone.getSlDaBan(), phone.getDonGia()};
	        table_model.addRow(newRow);
		}
	}
	
	public void themPhone(Model_Phone sp) {
		Model_Phone phone = DBPhone.getInstance().themPhone(sp);
		Object[] newRow = {phone.getId(), phone.getTen(), phone.getTheLoai(), phone.getSlTonKho(), phone.getSlDaBan(), phone.getDonGia()};        table_model.addRow(newRow);
	}
	
	public void suaThongTin() {
		int ma = Integer.parseInt(tf_ma.getText());
		String ten = tf_ten.getText();
		String theLoai = cb_theloai.getSelectedItem().toString();
		int tonkho = Integer.parseInt(tf_slTonKho.getText());
		int daban = Integer.parseInt(tf_slDaBan.getText());
		int dongia = Integer.parseInt(tf_donGia.getText());

		Model_Phone phone = new Model_Phone(ma, ten, theLoai, tonkho, daban, dongia, null);
		DBPhone.getInstance().suaThongTin(phone);
	}
	
	public void xoaPhone(int maPhone) {
		DBPhone.getInstance().xoaPhone(maPhone);
	}
	
	public void reset() {
		tf_ma.setText("");
		tf_ten.setText("");
		cb_theloai.setSelectedIndex(0);
		tf_slTonKho.setText("");
		tf_slDaBan.setText("");
		tf_donGia.setText("");
		
		tf_ma.setEditable(false);
		tf_ten.setEditable(false);
		cb_theloai.setEnabled(false);
		tf_slTonKho.setEditable(false);
		tf_slDaBan.setEditable(false);
		tf_donGia.setEditable(false);
		
		lb_hinhAnh.setIcon(new ImageIcon(QL_SanPham.class.getResource("/images/icon_image2.png")));
		
	}
	
	public void unreset() {
		tf_ten.setEditable(true);
		cb_theloai.setEnabled(true);
		tf_slTonKho.setEditable(true);
		tf_slDaBan.setEditable(true);
		tf_donGia.setEditable(true);
	}
	
	public void loc() {
		String dieukien = "1=1";
		if(!tf_loc_theloai.getSelectedItem().toString().equals("Tất cả")) {
			dieukien += " AND loai='" + tf_loc_theloai.getSelectedItem().toString() + "'";
		}
		if(!tf_loc_dongia_from.getText().isEmpty()) {
			dieukien += " AND dongia>=" + tf_loc_dongia_from.getText();
		}
		if(!tf_loc_dongia_to.getText().isEmpty()) {
			dieukien += " AND dongia<=" + tf_loc_dongia_to.getText();
		}
		if(!tf_loc_tonkho_from.getText().isEmpty()) {
			dieukien += " AND sltonkho>=" + tf_loc_tonkho_from.getText();
		}
		if(!tf_loc_tonkho_to.getText().isEmpty()) {
			dieukien += " AND sltonkho<=" + tf_loc_tonkho_to.getText();
		}
		if(!tf_loc_daban_from.getText().isEmpty()) {
			dieukien += " AND sldaban>=" + tf_loc_daban_from.getText();
		}
		if(!tf_loc_daban_to.getText().isEmpty()) {
			dieukien += " AND sldaban<=" + tf_loc_daban_to.getText();
		}
		
		ArrayList<Model_Phone> list = DBPhone.getInstance().locPhone(dieukien);
		table_model.setRowCount(0);
		for(Model_Phone phone : list) {
			Object[] newRow = {phone.getId(), phone.getTen(), phone.getTheLoai(), phone.getSlTonKho(), phone.getSlDaBan(), phone.getDonGia()};        table_model.addRow(newRow);
	        table_model.addRow(newRow);
		}
		
		reset();
	}
	
	public void timkiem() {
		String tenPhone = tf_loc_ten.getText();
		if(tenPhone.isEmpty() || tenPhone.equals("Nhập tên sản phẩm...")) {
			loadPhone();
		}
		else {
			ArrayList<Model_Phone> list = DBPhone.getInstance().timkiem("%" + tenPhone + "%");
			table_model.setRowCount(0);
			for(Model_Phone phone : list) {
				Object[] newRow = {phone.getId(), phone.getTen(), phone.getTheLoai(), phone.getSlTonKho(), phone.getSlDaBan(), phone.getDonGia()};        table_model.addRow(newRow);
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}
}
