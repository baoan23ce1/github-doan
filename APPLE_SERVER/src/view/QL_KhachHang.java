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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import EnCode.XMLExporter;
import dao.DBKhachHang;
import dao.DBNhanVien;
import model.Model_KhachHang;
import model.Model_NhanVien;
import swing.PlaceholderTextField;
import view.component.ThemNhanVien;
import view.component.ThemThanhVien;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_KhachHang extends JPanel{
	private JTextField tf_maKhanhHang;
	private JTextField tf_tenKhachHang;
	private JTextField tf_sdt;
	private JTextField tf_tongChi;
	private JTextField tf_diemTichLuy;
	private JTextField tf_loc_tongchi_from;
	private JTextField tf_loc_tongchi_to;
	private JTable table;
	private JComboBox cb_hangThanhVien;
	private DefaultTableModel table_model;
	private JButton bt_luu;
	private JComboBox cb_loc_hangThanhVien;
	private PlaceholderTextField tf_loc_makhachhang;
	private PlaceholderTextField tf_loc_sdt;
	
	public QL_KhachHang() {
		setBackground(Color.WHITE);
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);
		
		table_model = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"M\u00E3 KH", "T\u00EAn KH", "S\u0110T", "T\u1ED5ng chi", "\u0110i\u1EC3m TL", "H\u1EA1ng TV"
				}
			);
		table = new JTable();
		table.setModel(table_model);
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		Font headerFont = new Font("Arial", Font.BOLD, 20);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 445, 1200, 354);
		add(scrollPane);
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMKhchHng.setBounds(10, 105, 154, 30);
		add(lblMKhchHng);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnKhchHng.setBounds(10, 156, 177, 30);
		add(lblTnKhchHng);
		
		JLabel lblSt = new JLabel("SĐT");
		lblSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt.setBounds(10, 208, 154, 30);
		add(lblSt);
		
		JLabel lblTngChi = new JLabel("Tổng chi");
		lblTngChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTngChi.setBounds(10, 259, 154, 30);
		add(lblTngChi);
		
		JLabel lblHngThnhVin = new JLabel("Hạng thành viên");
		lblHngThnhVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHngThnhVin.setBounds(10, 362, 177, 30);
		add(lblHngThnhVin);
		
		JLabel lblimTchLy = new JLabel("Điểm tích lũy");
		lblimTchLy.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblimTchLy.setBounds(10, 313, 154, 30);
		add(lblimTchLy);
		
		tf_maKhanhHang = new JTextField();
		tf_maKhanhHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_maKhanhHang.setBounds(197, 104, 283, 30);
		add(tf_maKhanhHang);
		tf_maKhanhHang.setColumns(10);
		
		tf_tenKhachHang = new JTextField();
		tf_tenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_tenKhachHang.setColumns(10);
		tf_tenKhachHang.setBounds(197, 156, 283, 30);
		add(tf_tenKhachHang);
		
		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(197, 208, 283, 30);
		add(tf_sdt);
		
		tf_tongChi = new JTextField();
		tf_tongChi.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_tongChi.setColumns(10);
		tf_tongChi.setBounds(197, 259, 283, 30);
		add(tf_tongChi);
		
		tf_diemTichLuy = new JTextField();
		tf_diemTichLuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_diemTichLuy.setColumns(10);
		tf_diemTichLuy.setBounds(197, 313, 283, 30);
		add(tf_diemTichLuy);
		
		String[] itemHang = { "Đồng", "Bạc", "Vàng", "Kim cương" };
		cb_hangThanhVien = new JComboBox<>(itemHang);
		cb_hangThanhVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb_hangThanhVien.setBounds(197, 362, 283, 30);
		add(cb_hangThanhVien);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(521, 104, 468, 288);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMKh = new JLabel("Mã KH");
		lblMKh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMKh.setBounds(10, 10, 92, 30);
		panel.add(lblMKh);
		
		JLabel lblSt_1 = new JLabel("SĐT");
		lblSt_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1.setBounds(10, 68, 75, 30);
		panel.add(lblSt_1);
		
		JLabel lblSt_1_1 = new JLabel("Tổng chi");
		lblSt_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1.setBounds(10, 134, 92, 30);
		panel.add(lblSt_1_1);
		
		JLabel lblSt_1_2 = new JLabel("Hạng TV");
		lblSt_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_2.setBounds(10, 195, 92, 30);
		panel.add(lblSt_1_2);
		
		tf_loc_makhachhang = new PlaceholderTextField("Nhập mã khách hàng...");
		tf_loc_makhachhang.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiemMaKH();
            }
            public void removeUpdate(DocumentEvent e) {
            	timkiemMaKH();
            }
            public void changedUpdate(DocumentEvent e) {
            	timkiemMaKH();
            }
        });
		tf_loc_makhachhang.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_makhachhang.setBounds(125, 10, 306, 30);
		panel.add(tf_loc_makhachhang);
		tf_loc_makhachhang.setColumns(10);
		
		tf_loc_sdt = new PlaceholderTextField("Nhập SĐT...");
		tf_loc_sdt.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                timkiemSdt();
            }
            public void removeUpdate(DocumentEvent e) {
            	timkiemSdt();
            }
            public void changedUpdate(DocumentEvent e) {
            	timkiemSdt();
            }
        });
		tf_loc_sdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_sdt.setColumns(10);
		tf_loc_sdt.setBounds(125, 68, 306, 30);
		panel.add(tf_loc_sdt);
		
		tf_loc_tongchi_from = new JTextField();
		tf_loc_tongchi_from.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_tongchi_from.setColumns(10);
		tf_loc_tongchi_from.setBounds(125, 134, 126, 30);
		panel.add(tf_loc_tongchi_from);
		
		tf_loc_tongchi_to = new JTextField();
		tf_loc_tongchi_to.setFont(new Font("Tahoma", Font.BOLD, 18));
		tf_loc_tongchi_to.setColumns(10);
		tf_loc_tongchi_to.setBounds(305, 134, 126, 30);
		panel.add(tf_loc_tongchi_to);
		
		JLabel lblSt_1_1_1 = new JLabel("đến");
		lblSt_1_1_1.setForeground(new Color(128, 128, 128));
		lblSt_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt_1_1_1.setBounds(261, 134, 38, 30);
		panel.add(lblSt_1_1_1);
		
		String[] itemHangLoc = {"Tất cả" ,"Đồng", "Bạc", "Vàng", "Kim cương" };
		cb_loc_hangThanhVien = new JComboBox<>(itemHangLoc);
		cb_loc_hangThanhVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		cb_loc_hangThanhVien.setBounds(125, 195, 306, 30);
		panel.add(cb_loc_hangThanhVien);
		
		JButton bt_loc = new JButton("LỌC");
		bt_loc.setIcon(new ImageIcon(QL_KhachHang.class.getResource("/images/filter.png")));
		bt_loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loc();
			}
		});
		bt_loc.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_loc.setBounds(160, 245, 150, 33);
		panel.add(bt_loc);
		
		JLabel bt_xuatDanhSach = new JLabel("XUẤT DANH SÁCH");
		bt_xuatDanhSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XMLExporter.exportKhachHangListToXML(DBKhachHang.getInstance().loadThanhVien());
			}
		});
		bt_xuatDanhSach.setIcon(new ImageIcon(QL_KhachHang.class.getResource("/images/icon_export.png")));
		bt_xuatDanhSach.setOpaque(true);
		bt_xuatDanhSach.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xuatDanhSach.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_xuatDanhSach.setBackground(new Color(0, 185, 7));
		bt_xuatDanhSach.setBounds(1013, 105, 197, 56);
		add(bt_xuatDanhSach);
		
		JLabel bt_themThanhVien = new JLabel("THÊM THÀNH VIÊN");
		bt_themThanhVien.setIcon(new ImageIcon(QL_KhachHang.class.getResource("/images/icon_add.png")));
		bt_themThanhVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
	        	JDialog dialog = new JDialog();
	        	ThemThanhVien them = new ThemThanhVien(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(650, 450);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_themThanhVien.setOpaque(true);
		bt_themThanhVien.setHorizontalAlignment(SwingConstants.CENTER);
		bt_themThanhVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_themThanhVien.setBackground(new Color(2, 119, 185));
		bt_themThanhVien.setBounds(1013, 182, 197, 56);
		add(bt_themThanhVien);
		
		JLabel bt_suaThongTin = new JLabel("SỬA THÔNG TIN");
		bt_suaThongTin.setIcon(new ImageIcon(QL_KhachHang.class.getResource("/images/edit.png")));
		bt_suaThongTin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maKhanhHang.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					unreset();
					bt_luu.setVisible(true);
				}
			}
		});
		bt_suaThongTin.setOpaque(true);
		bt_suaThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		bt_suaThongTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_suaThongTin.setBackground(new Color(188, 219, 0));
		bt_suaThongTin.setBounds(1013, 259, 197, 56);
		add(bt_suaThongTin);
		
		JLabel bt_xoaThanhVien = new JLabel("XÓA THÀNH VIÊN");
		bt_xoaThanhVien.setIcon(new ImageIcon(QL_KhachHang.class.getResource("/images/icon_delete (2).png")));
		bt_xoaThanhVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maKhanhHang.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					xoaThanhVien(Integer.parseInt(tf_maKhanhHang.getText()));
					reset();
					loadThanhVien();
				}

			}
		});
		bt_xoaThanhVien.setOpaque(true);
		bt_xoaThanhVien.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xoaThanhVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_xoaThanhVien.setBackground(new Color(210, 25, 13));
		bt_xoaThanhVien.setBounds(1013, 336, 197, 56);
		add(bt_xoaThanhVien);
		
		bt_luu = new JButton("LƯU");
		bt_luu.setIcon(new ImageIcon(QL_KhachHang.class.getResource("/images/save (2).png")));
		bt_luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
        		suaThongTin();
        		reset();
        		bt_luu.setVisible(false);
        		loadThanhVien();
			}
		});
		bt_luu.setVisible(false);
		bt_luu.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_luu.setBounds(179, 402, 118, 33);
		add(bt_luu);
		
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tf_maKhanhHang.setText(table.getValueAt(selectedRow, 0).toString());
                        tf_tenKhachHang.setText(table.getValueAt(selectedRow, 1).toString());
                        tf_sdt.setText(table.getValueAt(selectedRow, 2).toString());
                        tf_tongChi.setText(table.getValueAt(selectedRow, 3).toString());
                        tf_diemTichLuy.setText(table.getValueAt(selectedRow, 4).toString());
                        cb_hangThanhVien.setSelectedItem(table.getValueAt(selectedRow, 5).toString());
                    }
                }
            }
        });
		reset();
	}
	
	public void loadThanhVien() {
		ArrayList<Model_KhachHang> list = DBKhachHang.getInstance().loadThanhVien();
		table_model.setRowCount(0);
		for(Model_KhachHang khachHangMoi : list) {
	        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getTongChi(), khachHangMoi.getDiemTichLuy(), khachHangMoi.getHang()};
	        table_model.addRow(newRow);
		}
	}
	
	public void themThanhVien(Model_KhachHang khachhang) {
		Model_KhachHang khachHangMoi = DBKhachHang.getInstance().themThanhVien(khachhang);
        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getTongChi(), khachHangMoi.getDiemTichLuy(), khachHangMoi.getHang()};
        table_model.addRow(newRow);
	}
	
	
	public void suaThongTin() {
		int maKhachHang = Integer.parseInt(tf_maKhanhHang.getText());
		String ten = tf_tenKhachHang.getText();
		String sdt = tf_sdt.getText();
		int diem = Integer.parseInt(tf_diemTichLuy.getText());
		int tongChi = Integer.parseInt(tf_tongChi.getText());
		String hang = cb_hangThanhVien.getSelectedItem().toString();
		
		Model_KhachHang khachHang = new Model_KhachHang(maKhachHang, ten, sdt, tongChi, diem, hang);		
		
		DBKhachHang.getInstance().suaThongTin(khachHang);
	}
	
	public void xoaThanhVien(int maKhachHang) {
		DBKhachHang.getInstance().xoaThanhVien(maKhachHang);
	}
	
	public void reset() {
		tf_diemTichLuy.setText("");
		tf_maKhanhHang.setText("");
		tf_sdt.setText("");
		tf_tenKhachHang.setText("");
		tf_tongChi.setText("");
		cb_hangThanhVien.setSelectedIndex(0);
		
		tf_diemTichLuy.setEditable(false);
		tf_maKhanhHang.setEditable(false);
		tf_sdt.setEditable(false);
		tf_tenKhachHang.setEditable(false);
		tf_tongChi.setEditable(false);
		cb_hangThanhVien.setEnabled(false);
	}
	
	public void unreset() {
		tf_diemTichLuy.setEditable(true);
		tf_maKhanhHang.setEditable(true);
		tf_sdt.setEditable(true);
		tf_tenKhachHang.setEditable(true);
		tf_tongChi.setEditable(true);
		cb_hangThanhVien.setEnabled(true);
	}
	
	public void loc() {
		String dieukien = "1=1";
		if(!tf_loc_tongchi_from.getText().isEmpty()) {
			dieukien += " AND tongchi>=" + tf_loc_tongchi_from.getText();
		}
		if(!tf_loc_tongchi_to.getText().isEmpty()) {
			dieukien += " AND tongchi<=" + tf_loc_tongchi_to.getText();
		}
		if(!cb_loc_hangThanhVien.getSelectedItem().toString().equals("Tất cả")) {
			dieukien += " AND hangThanhVien='" + cb_loc_hangThanhVien.getSelectedItem().toString()  + "'";
		}
		
		ArrayList<Model_KhachHang> list = DBKhachHang.getInstance().locThanhVien(dieukien);
		table_model.setRowCount(0);
		for(Model_KhachHang khachHangMoi : list) {
	        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getTongChi(), khachHangMoi.getDiemTichLuy(), khachHangMoi.getHang()};
	        table_model.addRow(newRow);
		}
		
		reset();
	}
	
	public void timkiemMaKH() {
		if(tf_loc_makhachhang.getText().isEmpty()|| tf_loc_makhachhang.getText().equals("Nhập mã khách hàng...")) {
			loadThanhVien();
		}
		else {
			int ma = Integer.parseInt(tf_loc_makhachhang.getText());
			ArrayList<Model_KhachHang> list = DBKhachHang.getInstance().timkiemMaKH(ma);
			table_model.setRowCount(0);
			for(Model_KhachHang khachHangMoi : list) {
		        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getTongChi(), khachHangMoi.getDiemTichLuy(), khachHangMoi.getHang()};
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}
	
	public void timkiemSdt() {
		String sdt = tf_loc_sdt.getText();
		if(sdt.isEmpty()|| sdt.equals("Nhập SĐT...")) {
			loadThanhVien();
		}
		else {
			ArrayList<Model_KhachHang> list = DBKhachHang.getInstance().timkiemSdt(sdt + "%");
			table_model.setRowCount(0);
			for(Model_KhachHang khachHangMoi : list) {
		        Object[] newRow = {khachHangMoi.getMaKhachHang(), khachHangMoi.getTen(), khachHangMoi.getSdt(), khachHangMoi.getTongChi(), khachHangMoi.getDiemTichLuy(), khachHangMoi.getHang()};
		        table_model.addRow(newRow);
			}
		}
		
		reset();
	}

}
