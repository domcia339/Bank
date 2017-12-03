import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Loguj extends JFrame {

	private JPanel contentPane;

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
					Loguj frame = new Loguj();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Loguj() {
		
		connection=SqlConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().setBackground(new Color(30, 144, 255));
		setBackground(new Color(0, 0, 139));
		setBounds(100, 100, 607, 409);
		setResizable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Witamy w aplikacji Banku PKO!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(211, 211, 211));
		lblNewLabel.setBounds(55, 11, 484, 49);
		getContentPane().add(lblNewLabel);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(206, 147, 150, 30);
		getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		lblNewLabelLogin = new JLabel("Login:");
		lblNewLabelLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabelLogin.setBounds(150, 149, 46, 22);
		getContentPane().add(lblNewLabelLogin);
		
		lblNewLabelHaslo = new JLabel("Has\u0142o:");
		lblNewLabelHaslo.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabelHaslo.setBounds(150, 213, 46, 14);
		getContentPane().add(lblNewLabelHaslo);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String value=null;
					String query="select p.id_pracownika, s.nazwa_stanowiska from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska where login=? and haslo=? ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldLogin.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs=pst.executeQuery();
					
					int count=0;
					
					while(rs.next()){
						count++;
						ID=rs.getString("id_pracownika");
						value=rs.getString("nazwa_stanowiska");
					}
					
				
					
					if(count==1){
						JOptionPane.showMessageDialog(null, "Poprawnie zalogowano.");
						//JOptionPane.showMessageDialog(null, value);
						
						dispose();
						
						if(value.equals("Administrator")){
						
						Administrator adminInfo=new Administrator();
						adminInfo.setVisible(true);
						
						} else if(value.equals("Doradca klienta")){
							DoradcaKlienta doradcaInfo=new DoradcaKlienta();
							doradcaInfo.setVisible(true);
						}
						
					
					}else if(count>1){
						JOptionPane.showMessageDialog(null, "S¹ dwa logini o takiej nazwie!");
					}else {
						JOptionPane.showMessageDialog(null, "Login lub has³o niepoprawne");
					}
					
					rs.close();
					pst.close();
					
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}

				/*try {
					
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
							
							dispose();
							
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
							
							dispose();
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
				}*/
				
			}
		});
		
		
		btnOK.setBackground(new Color(169, 169, 169));
		btnOK.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnOK.setBounds(235, 270, 95, 30);
		getContentPane().add(btnOK);
		
	/*	String [] wyborPerspektywy= { "","Doradca Klienta", "Administrator"};
		JComboBox comboBox = new JComboBox(wyborPerspektywy);
		comboBox.setBounds(206, 87, 150, 30);
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		getContentPane().add(comboBox);
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
				);*/
		
		passwordField = new JPasswordField();
		passwordField.setBounds(206, 207, 150, 30);
		getContentPane().add(passwordField);
	}
	}


