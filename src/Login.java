import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import net.proteanit.sql.DbUtils;

import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField textFieldLogin;
	private JLabel lblNewLabelLogin;
	private JLabel lblNewLabelHaslo;
	private JComboBox comboBox;
	private JPasswordField passwordField;
	Connection connection=null;
	String box;
	static String ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=SqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
		frame.setBackground(new Color(0, 0, 139));
		frame.setBounds(100, 100, 607, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("Witamy w aplikacji Banku PKO!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(211, 211, 211));
		lblNewLabel.setBounds(55, 11, 484, 49);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(206, 147, 150, 30);
		frame.getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		lblNewLabelLogin = new JLabel("Login:");
		lblNewLabelLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabelLogin.setBounds(150, 149, 46, 22);
		frame.getContentPane().add(lblNewLabelLogin);
		
		lblNewLabelHaslo = new JLabel("Has\u0142o:");
		lblNewLabelHaslo.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabelHaslo.setBounds(150, 213, 46, 14);
		frame.getContentPane().add(lblNewLabelHaslo);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					
					if(box=="Administrator"){
						
						String query="select id_pracownika from pracownicy p where login=? and haslo=? and id_stanowiska=2";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.setString(1, textFieldLogin.getText());
						pst.setString(2, passwordField.getText());
						
						ResultSet rs=pst.executeQuery();
						
						int count=0;
						
						while(rs.next()){
							count++;
							ID=rs.getString("id_pracownika");
						}
						
						if(count==1){
							JOptionPane.showMessageDialog(null, "Poprawnie zalogowano Administratora");
							//JOptionPane.showMessageDialog(null,ID);
							
							frame.dispose();
							
							Administrator adminInfo=new Administrator();
							adminInfo.setVisible(true);
							
						
						}else if(count>1){
							JOptionPane.showMessageDialog(null, "S¹ dwa logini o takiej nazwie!");
						}else {
							JOptionPane.showMessageDialog(null, "Login lub has³o niepoprawne");
						}
						
						rs.close();
						pst.close();
						
					}else if(box=="Doradca Klienta"){
						
						String query="select id_pracownika from pracownicy p where login=? and haslo=? and id_stanowiska=3";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.setString(1, textFieldLogin.getText());
						pst.setString(2, passwordField.getText());
						
						ResultSet rs=pst.executeQuery();
						int count=0;
						
						while(rs.next()){
							count++;
						}
						if(count==1){
							JOptionPane.showMessageDialog(null, "Poprawnie zalogowano Doradcê Klienta");
							
							frame.dispose();
							DoradcaKlienta doradcaInfo=new DoradcaKlienta();
							doradcaInfo.setVisible(true);
						
						}else if(count>1){
							JOptionPane.showMessageDialog(null, "S¹ dwa logini o takiej nazwie!");
						}else {
							JOptionPane.showMessageDialog(null, "Login lub has³o niepoprawne");
						}
						
						rs.close();
						pst.close();
						
					}else JOptionPane.showMessageDialog(null, "Wybierz jako kto chcesz byæ zalogowany!");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		
		
		btnOK.setBackground(new Color(169, 169, 169));
		btnOK.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnOK.setBounds(235, 270, 95, 30);
		frame.getContentPane().add(btnOK);
		
		String [] wyborPerspektywy= { "","Doradca Klienta", "Administrator"};
		JComboBox comboBox = new JComboBox(wyborPerspektywy);
		comboBox.setBounds(206, 87, 150, 30);
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox comboBox = (JComboBox) event.getSource();

                String selected = (String) comboBox.getSelectedItem();
                switch(selected){
                case "Doradca Klienta": box=comboBox.getSelectedItem().toString();
                case "Administrator": box=comboBox.getSelectedItem().toString();
                }
                
			}
		}
				);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(206, 207, 150, 30);
		frame.getContentPane().add(passwordField);
	}
}
