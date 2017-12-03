import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZmienHaslo extends JPanel {

	
	Connection connection=null;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	/**
	 * Create the panel.
	 */
	public ZmienHaslo() {

		setBackground(new Color(0, 102, 255));
		setBounds(202, 11, 527, 444);
		
		connection=SqlConnection.dbConnector();
		setBackground(new Color(102, 204, 255));
		setLayout(null);
		setLayout(null);
		
		JLabel lblZmianaHasa = new JLabel("Zmiana has\u0142a");
		lblZmianaHasa.setFont(new Font("Verdana", Font.BOLD, 22));
		lblZmianaHasa.setBounds(44, 22, 349, 39);
		add(lblZmianaHasa);
		
		JLabel lblStareHaso = new JLabel("Stare has\u0142o:");
		lblStareHaso.setBounds(44, 107, 90, 14);
		add(lblStareHaso);
		
		JLabel lblNoweHaso = new JLabel("Nowe has\u0142o:");
		lblNoweHaso.setBounds(44, 140, 101, 14);
		add(lblNoweHaso);
		
		JLabel lblPowtrzNoweHaso = new JLabel("Powt\u00F3rz nowe has\u0142o:");
		lblPowtrzNoweHaso.setBounds(44, 176, 117, 14);
		add(lblPowtrzNoweHaso);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(212, 104, 90, 20);
		add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(212, 137, 90, 20);
		add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(212, 173, 90, 20);
		add(passwordField_2);
		
		JButton btnZmieHaso = new JButton("Zmie\u0144");
		btnZmieHaso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String value=null;
					String query="select p.haslo from pracownicy p  where p.id_pracownika='"+Loguj.ID+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()){
					value=rs.getString("haslo");
					//JOptionPane.showMessageDialog(null,passwordField.getText());
					}
				
					if(value.equals(passwordField.getText()) && passwordField_1.getText().equals(passwordField_2.getText())){
					
						String query1="Update pracownicy set haslo='" +passwordField_1.getText()+"' where id_pracownika='" +Loguj.ID+ "' ";
						PreparedStatement pst1=connection.prepareStatement(query1);
						JOptionPane.showMessageDialog(null,"Haslo zmienione");

						pst1.execute();
						pst1.close();
					}else{
						JOptionPane.showMessageDialog(null,"Nieprawid³owe dane!!!!!");
					}
					//while(rs.next()){
					//lblNewLabelID.setText(rs.getString("id_pracownika"));
				
					
					rs.close();
					pst.close();
					
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Nieprawid³owe dane!");
					
				}
				
				
			}
		});
		btnZmieHaso.setBounds(213, 215, 89, 23);
		add(btnZmieHaso);
	}
}
