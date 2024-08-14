package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import swing.ImageRenderer;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import EnCode.ImageUtil;
import dao.DBDoanhThu;
import model.Model_DoanhThu;
import model.Model_KhachHang;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QL_DoanhThu extends JPanel{
	private JTextField tf_soluong;
	private JTextField tf_tongthu;
	private JTable table;
	private DefaultTableModel table_model;
	private JDateChooser date_from;
	private JDateChooser date_to;
	
	public QL_DoanhThu() {
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
					"M\u00E3", "", "T\u00EAn s\u00E1ch", "T\u00EAn kh\u00E1ch h\u00E0ng", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y mua"
				}
			);
		table = new JTable();
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(399);
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		Font headerFont = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(53, 167, 1129, 565);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("SỐ LƯỢNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(53, 742, 197, 33);
		add(lblNewLabel);
		
		tf_soluong = new JTextField();
		tf_soluong.setFont(new Font("Tahoma", Font.BOLD, 25));
		tf_soluong.setBounds(218, 742, 139, 33);
		add(tf_soluong);
		tf_soluong.setColumns(10);
		
		JLabel lblTngThu = new JLabel("TỔNG THU");
		lblTngThu.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTngThu.setBounds(780, 742, 155, 33);
		add(lblTngThu);
		
		tf_tongthu = new JTextField();
		tf_tongthu.setFont(new Font("Tahoma", Font.BOLD, 25));
		tf_tongthu.setColumns(10);
		tf_tongthu.setBounds(931, 742, 251, 33);
		add(tf_tongthu);
		
		JLabel bt_homnay = new JLabel("Hôm nay");
		bt_homnay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Calendar today = Calendar.getInstance();
				date_from.setDate(today.getTime());
				date_to.setDate(today.getTime());
				
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlFromDate);
			}
		});
		bt_homnay.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_homnay.setHorizontalAlignment(SwingConstants.CENTER);
		bt_homnay.setBounds(53, 120, 124, 48);
		bt_homnay.setOpaque(true);
		bt_homnay.setBackground(new Color(183, 225, 255));
		add(bt_homnay);
		
		JLabel bt_thangnay = new JLabel("Tháng nay");
		bt_thangnay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMonthToCurrent();
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlToDate);
			}
		});
		bt_thangnay.setOpaque(true);
		bt_thangnay.setHorizontalAlignment(SwingConstants.CENTER);
		bt_thangnay.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_thangnay.setBackground(new Color(125, 201, 255));
		bt_thangnay.setBounds(176, 120, 124, 48);
		add(bt_thangnay);
		
		JLabel bt_namnay = new JLabel("Năm nay");
		bt_namnay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setYearToCurrent();
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlToDate);
			}
		});
		bt_namnay.setOpaque(true);
		bt_namnay.setHorizontalAlignment(SwingConstants.CENTER);
		bt_namnay.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_namnay.setBackground(new Color(49, 166, 250));
		bt_namnay.setBounds(299, 120, 124, 48);
		add(bt_namnay);
		
		date_from = new JDateChooser();
		date_from.setBackground(new Color(135, 206, 235));
		date_from.setSize(200, 37);
		date_from.setFont(new Font("Tahoma", Font.BOLD, 20));
		date_from.setLocation(628, 120);
		add(date_from);
		
		date_to = new JDateChooser();
		date_to.setBackground(new Color(135, 206, 235));
		date_to.setBounds(880, 120, 200, 37);
		date_to.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(date_to);
		
		JButton bt_timkiem = new JButton("");
		bt_timkiem.setIcon(new ImageIcon(QL_DoanhThu.class.getResource("/images/search.png")));
		bt_timkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Date fromDate = date_from.getDate();
		        Date toDate = date_to.getDate();
	            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
	            java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
				timkiem(sqlFromDate, sqlToDate);
			}
		});
		bt_timkiem.setBounds(1090, 120, 92, 37);
		add(bt_timkiem);
		
		JLabel lblNewLabel_1 = new JLabel("đến");
		lblNewLabel_1.setForeground(new Color(169, 169, 169));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(836, 120, 45, 37);
		add(lblNewLabel_1);
		
		JButton bt_huy = new JButton("");
		bt_huy.setIcon(new ImageIcon(QL_DoanhThu.class.getResource("/images/refresh.png")));
		bt_huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDonMua();
			    date_from.setDate(null);
			    date_to.setDate(null);
			}
		});
		bt_huy.setBounds(546, 120, 72, 37);
		add(bt_huy);
	}
	
	public void loadDonMua() {
		ArrayList<Model_DoanhThu> list = DBDoanhThu.getInstance().loadDonMua();
		table_model.setRowCount(0);
		for(Model_DoanhThu donmua : list) {
			ImageIcon image = ImageUtil.bytesToImageIcon(donmua.getHinhAnh(), 80, 80);
			
	        Object[] newRow = {donmua.getMaDonMua(), image, donmua.getTenSach(), donmua.getTenKhachHang(), donmua.getSoluong(), donmua.getNgayMua()};
	        table_model.addRow(newRow);
		}
		
		tongThu();
	}
	
	public void timkiem( java.sql.Date from ,  java.sql.Date to) {
		ArrayList<Model_DoanhThu> list = DBDoanhThu.getInstance().timkiem(from, to);
		table_model.setRowCount(0);
		for(Model_DoanhThu donmua : list) {
			ImageIcon image = ImageUtil.bytesToImageIcon(donmua.getHinhAnh(), 80, 80);
			
	        Object[] newRow = { donmua.getMaDonMua(),image, donmua.getTenSach(), donmua.getTenKhachHang(), donmua.getSoluong(), donmua.getNgayMua()};
	        table_model.addRow(newRow);
		}
		
		tongThu();
	}
	
	public void tongThu() {
        int soluong = 0;
        long doanhThu = 0;
        for (int i = 0; i < table_model.getRowCount(); i++) {
        	soluong += Integer.parseInt(table_model.getValueAt(i, 4).toString()); 
        	doanhThu += 1L * Integer.parseInt(table_model.getValueAt(i, 4).toString()) * DBDoanhThu.getInstance().giaSach(Integer.parseInt(table_model.getValueAt(i, 0).toString()));
        }
        tf_soluong.setText(soluong+"");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String tongthu = currencyFormatter.format(doanhThu);
        tf_tongthu.setText(tongthu+"");
	}
	
    private void setMonthToCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfMonth = calendar.getTime();

        date_from.setDate(firstDayOfMonth);
        date_to.setDate(lastDayOfMonth);
    }
    
    private void setYearToCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfYear = calendar.getTime();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date lastDayOfYear = calendar.getTime();

        date_from.setDate(firstDayOfYear);
        date_to.setDate(lastDayOfYear);
    }
}
