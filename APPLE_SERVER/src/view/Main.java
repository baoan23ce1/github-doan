package view;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import service.Service;

public class Main extends JFrame {

	private JPanel contentPane;
	private MenuLeft menuLeft;
	private Body body;
	private Login login;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1554, 850);
		setTitle("APPLE STORE");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0 ,0, 1554, 840);
//		setContentPane(contentPane);
		
		contentPane.setLayout(new MigLayout("fillx, filly", "0[300!]0[fill, 100%]0", "0[fill]0"));
		
		menuLeft = new MenuLeft();
		contentPane.add(menuLeft, "width 300:300:300");
		body = new Body();
		contentPane.add(body, "width 1240:1240:1240");
		
		login = new Login();
		add(login);
		
		Service.getInstance(this).startServer();;
	}
	
	public void display() {
		login.setVisible(false);
		setContentPane(contentPane);
	}

	public MenuLeft getMenuLeft() {
		return menuLeft;
	}

	public Body getBody() {
		return body;
	}
	
	
}
