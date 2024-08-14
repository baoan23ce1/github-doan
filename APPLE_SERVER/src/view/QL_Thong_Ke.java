package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DBDoanhThu;
import swing.chart.Chart;
import swing.chart.ModelChart;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class QL_Thong_Ke extends JPanel{
	private JPanel panel_soluong;
	private JPanel panel_doanhthu;
	private Chart chart_soluong;
	private Chart chart_doanhthu;
	private JButton bt_refresh;
	private int[] soluongList;
	private int[] doanhthuList;
	private String[] month = {"", "January", "February","March","April","May","June","July","August","September","October","November","December"};
 	
	public QL_Thong_Ke() {	
		soluongList = new int[12];
		doanhthuList = new int[12];
		
		setBackground(Color.WHITE);
		setSize(1240, 830);
		setLayout(null);
		
		JLabel lb_logo = new JLabel("");
		lb_logo.setIcon(new ImageIcon(QL_CuaHang.class.getResource("/images/logo_title.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(370, 10, 442, 63);
		add(lb_logo);	
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(65, 101, 1107, 664);
		add(tabbedPane);

		panel_soluong = new JPanel();
		tabbedPane.addTab("Số lượng", panel_soluong);
		
		panel_doanhthu = new JPanel();
		tabbedPane.addTab("Doanh thu", panel_doanhthu);
		panel_soluong.setLayout(null);
		panel_doanhthu.setLayout(null);
			
		chart_doanhthu = new Chart();
		chart_doanhthu.setLocation(0, 0);
		chart_doanhthu.setSize(1102, 637);
		panel_doanhthu.add(chart_doanhthu);
		
		chart_doanhthu.addLegend("Doanh thu", new Color(38, 190, 51));
			
		chart_soluong = new Chart();
		chart_soluong.setLocation(0, 0);
		chart_soluong.setSize(1102, 627);
		panel_soluong.add(chart_soluong);
		
		bt_refresh = new JButton("");
		bt_refresh.setIcon(new ImageIcon(QL_Thong_Ke.class.getResource("/images/refresh.png")));
		bt_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		bt_refresh.setBounds(586, 768, 85, 30);
		add(bt_refresh);
		
		chart_soluong.addLegend("Số lượng", new Color(255, 132, 132));		
		
	}
	
	public void refresh() {
		chart_soluong.clear();
		chart_doanhthu.clear();
		
		thongke();
	}
	
	public void thongke() {
        for(int i = 1; i <= 12; i++) {
        	Date[] dates = getDateRangeOfMonth(i);
        	int soluong = DBDoanhThu.getInstance().thongkeSoLuong(dates[0], dates[1]);
        	int doanhthu = DBDoanhThu.getInstance().thongkeDoanhThu(dates[0], dates[1]);
        	chart_soluong.addData(new ModelChart(month[i], new double[]{soluong}));
        	chart_doanhthu.addData(new ModelChart(month[i], new double[]{doanhthu}));
        }
        chart_soluong.start();
        chart_doanhthu.start();
	}
	
    public static Date[] getDateRangeOfMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Tháng không hợp lệ");
        }

        YearMonth yearMonth = YearMonth.of(YearMonth.now().getYear(), month);
        Date firstDate = Date.valueOf(yearMonth.atDay(1));
        Date lastDate = Date.valueOf(yearMonth.atEndOfMonth());

        return new Date[]{firstDate, lastDate};
    }
}
