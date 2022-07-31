package com.alisimsek.javabootcamp.finalproject.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.alisimsek.javabootcamp.finalproject.helper.Helper;
import com.alisimsek.javabootcamp.finalproject.model.Users;
import com.alisimsek.javabootcamp.finalproject.service.UsersService;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel wrapper;
	private JTextField fld_email;
	private JPasswordField fld_pass;
	private UsersService usersService = new UsersService();

	public LoginGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle("Java Bootcamp Bitirme Projesi");
		setResizable(false);
		wrapper = new JPanel();
		wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wrapper);
		wrapper.setLayout(null);


		JLabel lblNewLabel = new JLabel("Email :");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(75, 75, 150, 25);
		wrapper.add(lblNewLabel);
		
		fld_email = new JTextField();
		fld_email.setBounds(75, 100, 200, 25);
		wrapper.add(fld_email);
		fld_email.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Şifre :");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_1.setBounds(75, 140, 150, 25);
		wrapper.add(lblNewLabel_1);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(75, 165, 200, 25);
		wrapper.add(fld_pass);
		
		JButton btn_login = new JButton("Giriş Yap");
		btn_login.setFont(new Font("Arial Black", Font.BOLD, 12));
		btn_login.setBounds(100, 240, 150, 25);
		wrapper.add(btn_login);

		//email ve şifreye göre eşleştirme kontrolü yapılır ve sisteme girişe izin verilir
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_pass)) {
					Helper.showMsg("Tüm alanları doldurunuz.");
				}
				else {
					Users user = usersService.getByEmailPass(fld_email.getText(), fld_pass.getText());
					if (user==null){
						Helper.showMsg("Kullanıcı bulunamadı. Kullanıcı adı veya şifre hatalı.");
					}
					else{
						UserGUI userGUI = new UserGUI(user);
						dispose();
					}
				}
			}
		});
	}
}
