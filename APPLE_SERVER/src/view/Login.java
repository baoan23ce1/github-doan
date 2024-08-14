package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import EnCode.MD5;
import dao.DBAccountAdmin;
import dao.DBDoanhThu;
import model.Model_Login;
import model.Model_Register;
import service.Service;

public class Login extends JPanel{
	
	private JPanel panel;
	private CardLayout cardLayout;
	private JTextField tf_userName;
	private JPasswordField tf_password;
	private JLabel lb_login_message;
	private JTextField tf_userName_register;
	private JPasswordField tf_password_register;
	private JPasswordField tf_confirm_register;
	private JLabel lb_register_message;

	public Login() {
		setSize(1554, 840);
		setLayout(null);
		setBackground(new Color(74, 170, 239));
		
		panel = new JPanel();
		panel.setBounds(989, 174, 361, 434);
		panel.setBackground(new Color(254, 197, 205));
		add(panel);
		cardLayout = new CardLayout(0, 0);
		panel.setLayout(cardLayout);
		
		JPanel panel_login = new JPanel();
		panel_login.setBackground(Color.WHITE);
		panel.add(panel_login, "panel_login");
		panel_login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(25, 10, 315, 39);
		panel_login.add(lblNewLabel);
		
		tf_userName = new JTextField();
		tf_userName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_userName.setBounds(25, 104, 315, 39);
		panel_login.add(tf_userName);
		tf_userName.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_password.setBounds(25, 211, 315, 39);
		panel_login.add(tf_password);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(25, 78, 163, 26);
		panel_login.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(25, 186, 163, 26);
		panel_login.add(lblNewLabel_1_1);
		
		JButton bt_login = new JButton("LOGIN");
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_userName.getText().isEmpty()) {
					tf_userName.grabFocus();
				}
				else if(tf_password.getText().isEmpty()) {
					tf_password.grabFocus();
				}
				else {
					Model_Login login = new Model_Login(tf_userName.getText(), MD5.getMd5(tf_password.getText()));
					boolean check = DBAccountAdmin.getInstance().login(login);
					if(check) {
						Service.getInstance().getMain().display();
					}
					else {
						lb_login_message.setText("TÀI KHOẢN HOẶC MẬT KHẨU SAI");
						lb_login_message.setForeground(Color.red);
					}
				}
			}
		});
		bt_login.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_login.setBounds(25, 294, 315, 39);
		panel_login.add(bt_login);
		
		lb_login_message = new JLabel("");
		lb_login_message.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_login_message.setHorizontalAlignment(SwingConstants.CENTER);
		lb_login_message.setBounds(25, 374, 315, 39);
		panel_login.add(lb_login_message);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "panel_register");
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(23, 338, 317, 19);
		panel_login.add(btnNewButton);
		
		JPanel panel_register = new JPanel();
		panel_register.setBackground(Color.WHITE);
		panel.add(panel_register, "panel_register");
		panel_register.setLayout(null);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblRegister.setBounds(22, 10, 315, 39);
		panel_register.add(lblRegister);
		
		tf_userName_register = new JTextField();
		tf_userName_register.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_userName_register.setColumns(10);
		tf_userName_register.setBounds(22, 94, 315, 39);
		panel_register.add(tf_userName_register);
		
		tf_password_register = new JPasswordField();
		tf_password_register.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_password_register.setBounds(22, 168, 315, 39);
		panel_register.add(tf_password_register);
		
		tf_confirm_register = new JPasswordField();
		tf_confirm_register.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_confirm_register.setBounds(22, 240, 315, 39);
		panel_register.add(tf_confirm_register);
		
		JLabel lblNewLabel_1_2 = new JLabel("UserName");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(22, 69, 163, 26);
		panel_register.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(22, 143, 163, 26);
		panel_register.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Confirm");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_1.setBounds(22, 217, 163, 26);
		panel_register.add(lblNewLabel_1_3_1);
		
		JButton bt_register = new JButton("REGISTER");
		bt_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_userName_register.getText().isEmpty()) {
					tf_userName.grabFocus();
				}
				else if(tf_password_register.getText().isEmpty()) {
					tf_password.grabFocus();
				}
				else if(!tf_password_register.getText().equals(tf_confirm_register.getText())) {
					lb_register_message.setText("Xác nhận mật khẩu chưa chính xác!");
					lb_register_message.setForeground(Color.red);
				}
				else {
					Model_Register register = new Model_Register(tf_userName_register.getText(), MD5.getMd5(tf_password_register.getText()));
					boolean check = DBAccountAdmin.getInstance().register(register);
					
					if(check) {
						lb_register_message.setText("ĐĂNG KÝ THÀNH CÔNG");
						lb_register_message.setForeground(Color.GREEN);
					}
					else {
						lb_register_message.setText("TÀI KHOẢN ĐÃ TỒN TẠI");
						lb_register_message.setForeground(Color.red);
					}
				}
			}
		});
		bt_register.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_register.setBounds(22, 303, 315, 39);
		panel_register.add(bt_register);
		
		lb_register_message = new JLabel("");
		lb_register_message.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_register_message.setHorizontalAlignment(SwingConstants.CENTER);
		lb_register_message.setBounds(22, 374, 315, 39);
		panel_register.add(lb_register_message);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "panel_login");
			}
		});
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBounds(20, 345, 317, 19);
		panel_register.add(btnLogin);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(280, 174, 300, 300);
		lblNewLabel_3.setIcon(new ImageIcon(new ImageIcon(MenuLeft.class.getResource("/images/logo.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 50));
		lblNewLabel_2.setBounds(280, 477, 300, 51);
		add(lblNewLabel_2);
	}
}
