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
import javax.swing.JTable;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import EnCode.XMLExporter;
import dao.DBNhanVien;
import model.Model_NhanVien;
import swing.PlaceholderTextField;
import view.component.ThemNhanVien;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_NhanVien extends JPanel{
	private JTable table;
	private JTextField tf_maNhanVien;
	private JTextField tf_tenNhanVien;
	private JTextField tf_cccd;
	private JTextField tf_ngaySinh;
	private JTextField tf_sdt;
	private JTextField tf_luong;
	private JTextField tf_timKiem;
	private DefaultTableModel table_model;
	private JComboBox cb_chucVu;
	private JComboBox cb_gioiTinh;
	private JButton bt_luu;
	
	public QL_NhanVien() {
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
					"M\u00E3 NV", "T\u00EAn NV", "CCCD", "Gi\u1EDBi", "Ng\u00E0y sinh", "S\u0110T", "Ch\u1EE9c v\u1EE5", "L\u01B0\u01A1ng"
				});
		table = new JTable();
		table.setModel(table_model);
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(230);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(133);
		table.getColumnModel().getColumn(5).setPreferredWidth(125);
		table.getColumnModel().getColumn(6).setPreferredWidth(170);
		table.getColumnModel().getColumn(7).setPreferredWidth(171);
		
		Font headerFont = new Font("Arial", Font.BOLD, 20);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(40);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 421, 1194, 375);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(20, 103, 154, 30);
		add(lblNewLabel);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnNhnVin.setBounds(20, 162, 154, 30);
		add(lblTnNhnVin);
		
		JLabel lblCccd = new JLabel("CCCD");
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCccd.setBounds(20, 217, 154, 30);
		add(lblCccd);
		
		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGiiTnh.setBounds(20, 277, 154, 30);
		add(lblGiiTnh);
		
		tf_maNhanVien = new JTextField();
		tf_maNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_maNhanVien.setBounds(184, 103, 268, 29);
		add(tf_maNhanVien);
		tf_maNhanVien.setColumns(10);
		
		tf_tenNhanVien = new JTextField();
		tf_tenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tenNhanVien.setColumns(10);
		tf_tenNhanVien.setBounds(184, 162, 268, 29);
		add(tf_tenNhanVien);
		
		tf_cccd = new JTextField();
		tf_cccd.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_cccd.setColumns(10);
		tf_cccd.setBounds(184, 217, 268, 29);
		add(tf_cccd);
		
		String[] itemGioiTinh = { "Nam", "Nữ" };
		cb_gioiTinh = new JComboBox<>(itemGioiTinh);
		cb_gioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cb_gioiTinh.setBounds(184, 277, 268, 30);
		add(cb_gioiTinh);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNgySinh.setBounds(532, 103, 154, 30);
		add(lblNgySinh);
		
		JLabel lblSt = new JLabel("SĐT");
		lblSt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSt.setBounds(532, 162, 154, 30);
		add(lblSt);
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChcV.setBounds(532, 217, 154, 30);
		add(lblChcV);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLng.setBounds(532, 277, 154, 30);
		add(lblLng);
		
		tf_ngaySinh = new JTextField();
		tf_ngaySinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_ngaySinh.setColumns(10);
		tf_ngaySinh.setBounds(651, 103, 268, 29);
		add(tf_ngaySinh);
		
		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(651, 162, 268, 29);
		add(tf_sdt);
		
		tf_luong = new JTextField();
		tf_luong.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_luong.setColumns(10);
		tf_luong.setBounds(651, 277, 268, 29);
		add(tf_luong);
		
		String[] itemChucVu = { "Quản lý", "Nhân viên", "Lao công", "Bảo vệ" };
		cb_chucVu = new JComboBox<>(itemChucVu);
		cb_chucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cb_chucVu.setBounds(651, 217, 268, 30);
		add(cb_chucVu);
		
		JLabel bt_xuatDanhSach = new JLabel("XUẤT DANH SÁCH");
		bt_xuatDanhSach.setIcon(new ImageIcon(QL_NhanVien.class.getResource("/images/icon_export.png")));
		bt_xuatDanhSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XMLExporter.exportNhanVienListToXML(DBNhanVien.getInstance().loadNhanVien());				
			}
		});
		bt_xuatDanhSach.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_xuatDanhSach.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xuatDanhSach.setBounds(1017, 103, 197, 56);
		bt_xuatDanhSach.setBackground(new Color(0, 185, 7));
		bt_xuatDanhSach.setOpaque(true);
		add(bt_xuatDanhSach);
		
		JLabel bt_themNhanVien = new JLabel("THÊM NHÂN VIÊN");
		bt_themNhanVien.setIcon(new ImageIcon(QL_NhanVien.class.getResource("/images/icon_add.png")));
		bt_themNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
	        	JDialog dialog = new JDialog();
	        	ThemNhanVien them = new ThemNhanVien(dialog);
	    		dialog.getContentPane().setLayout(new GridLayout(1,1));
	    		dialog.setSize(1000, 500);
	    		dialog.setLocationRelativeTo(null);
	        	dialog.getContentPane().add(them);
	        	dialog.setVisible(true);
			}
		});
		bt_themNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		bt_themNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_themNhanVien.setBounds(1017, 175, 197, 56);
		bt_themNhanVien.setBackground(new Color(2, 119, 185));
		bt_themNhanVien.setOpaque(true);
		add(bt_themNhanVien);
		
		JLabel bt_suaThongTin = new JLabel("SỬA THÔNG TIN");
		bt_suaThongTin.setIcon(new ImageIcon(QL_NhanVien.class.getResource("/images/edit.png")));
		bt_suaThongTin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maNhanVien.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					unreset();
					bt_luu.setVisible(true);
				}
			}
		});
		bt_suaThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		bt_suaThongTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_suaThongTin.setBounds(1017, 251, 197, 56);
		bt_suaThongTin.setBackground(new Color(188, 219, 0));
		bt_suaThongTin.setOpaque(true);
		add(bt_suaThongTin);
		
		JLabel bt_xoaNhanVien = new JLabel("XÓA NHÂN VIÊN");
		bt_xoaNhanVien.setIcon(new ImageIcon(QL_NhanVien.class.getResource("/images/icon_delete (2).png")));
		bt_xoaNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tf_maNhanVien.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					xoaNhanVien(Integer.parseInt(tf_maNhanVien.getText()));
					reset();
					loadNhanVien();
				}
			}
		});
		bt_xoaNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		bt_xoaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_xoaNhanVien.setBounds(1017, 322, 197, 56);
		bt_xoaNhanVien.setBackground(new Color(210, 25, 13));
		bt_xoaNhanVien.setOpaque(true);
		add(bt_xoaNhanVien);
		
		tf_timKiem = new PlaceholderTextField("Nhập tên nhân viên...");
		tf_timKiem.getDocument().addDocumentListener(new DocumentListener() {
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
		tf_timKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_timKiem.setBounds(20, 374, 341, 37);
		add(tf_timKiem);
		tf_timKiem.setColumns(10);
		
		// KHI KICK CHỌN 1 DÒNG
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        bt_luu = new JButton("LƯU");
        bt_luu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		suaThongTin();
        		reset();
        		bt_luu.setVisible(false);
        		loadNhanVien();
        	}
        });
        bt_luu.setFont(new Font("Tahoma", Font.BOLD, 20));
        bt_luu.setBounds(814, 332, 105, 37);
        bt_luu.setVisible(false);
        add(bt_luu);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tf_maNhanVien.setText(table.getValueAt(selectedRow, 0).toString());
                        tf_tenNhanVien.setText(table.getValueAt(selectedRow, 1).toString());
                        tf_cccd.setText(table.getValueAt(selectedRow, 2).toString());
                        cb_gioiTinh.setSelectedItem(table.getValueAt(selectedRow, 3).toString());
                        tf_ngaySinh.setText(table.getValueAt(selectedRow, 4).toString());
                        tf_sdt.setText(table.getValueAt(selectedRow, 5).toString());
                        cb_chucVu.setSelectedItem(table.getValueAt(selectedRow, 6).toString());
                        tf_luong.setText(table.getValueAt(selectedRow, 7).toString());
                    }
                }
            }
        });
        reset();
	}
	
	public void loadNhanVien() {
		ArrayList<Model_NhanVien> list = DBNhanVien.getInstance().loadNhanVien();
		table_model.setRowCount(0);
		for(Model_NhanVien nhanVienMoi : list) {
	        Object[] newRow = {nhanVienMoi.getMaNhanVien(), nhanVienMoi.getTen(), nhanVienMoi.getCccd(), nhanVienMoi.getGioiTinh(), nhanVienMoi.getNgaySinh(), nhanVienMoi.getSdt(), nhanVienMoi.getChucVu(), nhanVienMoi.getLuong()};
	        table_model.addRow(newRow);
		}
	}
	
	public void themNhanVien(Model_NhanVien nhanVien) {
		Model_NhanVien nhanVienMoi = DBNhanVien.getInstance().themNhanVien(nhanVien);
        Object[] newRow = {nhanVienMoi.getMaNhanVien(), nhanVienMoi.getTen(), nhanVienMoi.getCccd(), nhanVienMoi.getGioiTinh(), nhanVienMoi.getNgaySinh(), nhanVienMoi.getSdt(), nhanVienMoi.getChucVu(), nhanVienMoi.getLuong()};
        table_model.addRow(newRow);
	}
	
	public void themNhanVien2(Model_NhanVien nhanVien) {
		Model_NhanVien nhanVienMoi = DBNhanVien.getInstance().themNhanVien2(nhanVien);
        Object[] newRow = {nhanVienMoi.getMaNhanVien(), nhanVienMoi.getTen(), nhanVienMoi.getCccd(), nhanVienMoi.getGioiTinh(), nhanVienMoi.getNgaySinh(), nhanVienMoi.getSdt(), nhanVienMoi.getChucVu(), nhanVienMoi.getLuong()};
        table_model.addRow(newRow);
	}
	
	public void suaThongTin() {
		int maNhanVien = Integer.parseInt(tf_maNhanVien.getText());
		String ten = tf_tenNhanVien.getText();
		String cccd = tf_cccd.getText();
		String gioiTinh = cb_gioiTinh.getSelectedItem().toString();
		java.sql.Date dateSql = null;	
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateUtil = sdf.parse(tf_ngaySinh.getText());
            dateSql = new java.sql.Date(dateUtil.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sdt = tf_sdt.getText();
		String chucVu = cb_chucVu.getSelectedItem().toString();
		int luong = Integer.parseInt(tf_luong.getText());
		
		Model_NhanVien nhanVien = new Model_NhanVien(maNhanVien, ten, cccd, gioiTinh, dateSql, sdt, chucVu, luong);
		DBNhanVien.getInstance().suaThongTin(nhanVien);
	}
	
	public void xoaNhanVien(int maNhanVien) {
		DBNhanVien.getInstance().xoaNhanVien(maNhanVien);
	}
	
	public void timkiem() {
		String name = tf_timKiem.getText();
		if(name.isEmpty() || name.equals("Nhập tên nhân viên...")) {
			loadNhanVien();
		}
		else {
			ArrayList<Model_NhanVien> list = DBNhanVien.getInstance().timkiem("%" + name + "%");
			table_model.setRowCount(0);
			for(Model_NhanVien nhanVienMoi : list) {
		        Object[] newRow = {nhanVienMoi.getMaNhanVien(), nhanVienMoi.getTen(), nhanVienMoi.getCccd(), nhanVienMoi.getGioiTinh(), nhanVienMoi.getNgaySinh(), nhanVienMoi.getSdt(), nhanVienMoi.getChucVu(), nhanVienMoi.getLuong()};
		        table_model.addRow(newRow);
			}
		}	
		
		reset();
	}
	
	public void reset() {
		tf_maNhanVien.setText("");
		tf_tenNhanVien.setText("");
		tf_ngaySinh.setText("");
		tf_cccd.setText("");
		tf_sdt.setText("");
		tf_luong.setText("");
		cb_gioiTinh.setSelectedIndex(0);
		cb_chucVu.setSelectedIndex(0);
		
		tf_maNhanVien.setEditable(false);
		tf_tenNhanVien.setEditable(false);
		tf_ngaySinh.setEditable(false);
		tf_cccd.setEditable(false);
		tf_sdt.setEditable(false);
		tf_luong.setEditable(false);
		cb_gioiTinh.setEnabled(false);
		cb_chucVu.setEnabled(false);
	}
	
	public void unreset() {		
//		tf_maNhanVien.setEditable(true);
		tf_tenNhanVien.setEditable(true);
		tf_ngaySinh.setEditable(true);
		tf_cccd.setEditable(true);
		tf_sdt.setEditable(true);
		tf_luong.setEditable(true);
		cb_gioiTinh.setEnabled(true);
		cb_chucVu.setEnabled(true);
	}
}
