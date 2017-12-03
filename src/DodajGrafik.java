import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodajGrafik extends JPanel {

	Connection connection=null;
	private JTextField textField;
	private JComboBox  comboBox_1, comboBox_2, comboBox_3;
	String box, box1, box2, box3;
	/**
	 * Create the panel.
	 */
	public DodajGrafik() {
		
		setBackground(new Color(0, 102, 255));
		setBounds(202, 11, 527, 444);
		
		connection=SqlConnection.dbConnector();
		setBackground(new Color(102, 204, 255));
		setLayout(null);
		setLayout(null);
		
		JLabel lblDodajGrafikPracownika = new JLabel("Dodaj/modyfikuj grafik pracownika");
		lblDodajGrafikPracownika.setFont(new Font("Verdana", Font.BOLD, 22));
		lblDodajGrafikPracownika.setBounds(10, 24, 492, 31);
		add(lblDodajGrafikPracownika);
		
		JLabel lblIdPracownikal = new JLabel("ID pracownika:");
		lblIdPracownikal.setBounds(36, 111, 111, 14);
		add(lblIdPracownikal);
		
		textField = new JTextField();
		textField.setBounds(181, 108, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDzieTygodnia = new JLabel("Dzie\u0144 tygodnia:");
		lblDzieTygodnia.setBounds(35, 155, 91, 14);
		add(lblDzieTygodnia);
		
		JLabel lblGodzinaRozpoczcia = new JLabel("Godzina rozpocz\u0119cia:");
		lblGodzinaRozpoczcia.setBounds(36, 197, 122, 14);
		add(lblGodzinaRozpoczcia);
		
		JLabel lblGodzinaZakoczenia = new JLabel("Godzina zako\u0144czenia:");
		lblGodzinaZakoczenia.setBounds(36, 238, 111, 14);
		add(lblGodzinaZakoczenia);
		
		
		String [] dniTygodnia= {"","poniedzialek", "wtorek", "sroda", "czwartek", "piatek"};
		JComboBox comboBox_1 = new JComboBox(dniTygodnia);
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setToolTipText("");
		//comboBox_1.setBounds(180, 239, 130, 20);
		add(comboBox_1);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox comboBox_1 = (JComboBox) e.getSource();
                String selected = (String) comboBox_1.getSelectedItem();
                switch(selected){
                case "poniedzialek": box1=comboBox_1.getSelectedItem().toString();
                case "wtorek": box1=comboBox_1.getSelectedItem().toString();
                case "sroda": box1=comboBox_1.getSelectedItem().toString();
                case "czwartek": box1=comboBox_1.getSelectedItem().toString();
                case "piatek": box1=comboBox_1.getSelectedItem().toString();
                
                }
				
			}
		});
		comboBox_1.setBounds(181, 152, 86, 20);
		add(comboBox_1);
		
		
		
		String [] godzRoz= {"","7:00", "8:00", "9:00", "10:00", "11:00","12:00", "13:00", "14:00","15:00"};
		JComboBox comboBox_2 = new JComboBox(godzRoz);
		comboBox_2.setSelectedIndex(0);
		comboBox_2.setToolTipText("");
		//comboBox_2.setBounds(180, 239, 130, 20);
		add(comboBox_2);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox_2 = (JComboBox) e.getSource();
                String selected = (String) comboBox_2.getSelectedItem();
                switch(selected){
                case "7:00": box2=comboBox_2.getSelectedItem().toString();
                case "8:00": box2=comboBox_2.getSelectedItem().toString();
                case "9:00": box2=comboBox_2.getSelectedItem().toString();
                case "10:00": box2=comboBox_2.getSelectedItem().toString();
                case "11:00": box2=comboBox_2.getSelectedItem().toString();
                case "12:00": box2=comboBox_2.getSelectedItem().toString();
                case "13:00": box2=comboBox_2.getSelectedItem().toString();
                case "14:00": box2=comboBox_2.getSelectedItem().toString();
                case "15:00": box2=comboBox_2.getSelectedItem().toString();
                }
			}
		});
		comboBox_2.setBounds(181, 194, 86, 20);
		add(comboBox_2);
		
		
		String [] godzZak= {"","12:00", "13:00", "14:00","15:00", "16:00", "17:00", "18:00","19:00","20:00"};
		JComboBox comboBox_3 = new JComboBox(godzZak);
		comboBox_3.setSelectedIndex(0);
		comboBox_3.setToolTipText("");
		comboBox_3.setBounds(180, 239, 130, 20);
		add(comboBox_3);
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox_3 = (JComboBox) e.getSource();
                String selected = (String) comboBox_3.getSelectedItem();
                switch(selected){
                case "12:00": box3=comboBox_3.getSelectedItem().toString();
                case "13:00": box3=comboBox_3.getSelectedItem().toString();
                case "14:00": box3=comboBox_3.getSelectedItem().toString();
                case "15:00": box3=comboBox_3.getSelectedItem().toString();
                case "16:00": box3=comboBox_3.getSelectedItem().toString();
                case "17:00": box3=comboBox_3.getSelectedItem().toString();
                case "18:00": box3=comboBox_3.getSelectedItem().toString();
                case "19:00": box3=comboBox_3.getSelectedItem().toString();
                case "20:00": box3=comboBox_3.getSelectedItem().toString();
                }
			}
		});
		comboBox_3.setBounds(181, 235, 86, 20);
		add(comboBox_3);
		
		JButton btnDodajGrafik = new JButton("Dodaj");
		btnDodajGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int value = 0;
					String query2="SELECT count(*) FROM grafiki_zmian WHERE id_pracownika ='"+textField.getText()+"' AND dzien_tygodnia = '"+box1+"' ";
					PreparedStatement pst2=connection.prepareStatement(query2);
					ResultSet rs=pst2.executeQuery();
					while (rs.next()){
		               // value = rs.getString("count(*)");
		               value= rs.getInt("count(*)");
					}
					pst2.close();
					//JOptionPane.showMessageDialog(null,value);
					//System.out.println(value);
					
					if(value==0){
					String query="insert into grafiki_zmian (id_grafiku,id_pracownika, dzien_tygodnia, godzina_rozpoczecia, godzina_zakonczenia)"+"values((select (max(id_grafiku)+1) from grafiki_zmian),?,'"+box1+"','"+box2+"','"+box3+"')";

					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.execute();
					pst.close();
					//JOptionPane.showMessageDialog(null,"if");
					}else{
						String query3="update grafiki_zmian set  godzina_rozpoczecia='"+box2+"', godzina_zakonczenia='"+box3+"'where id_pracownika='"+textField.getText()+"'and dzien_tygodnia='"+box1+"'  ";
						PreparedStatement pst3=connection.prepareStatement(query3);
						//pst3.setString(1, textField.getText());
						pst3.execute();
						pst3.close();
						//JOptionPane.showMessageDialog(null,"else");
					}
					
					
					JOptionPane.showMessageDialog(null,"Dane zapisane");
					
					
					//rs.close();
					removeAll();
					repaint();
					revalidate();
					
					DodajPrac dodajPrac= new DodajPrac();
					
					
				} catch (Exception e3) {
					e3.printStackTrace();
					JOptionPane.showMessageDialog(null, "Uzupe³nij dane!");
				}
				
				
			}
		});
		btnDodajGrafik.setBounds(178, 304, 89, 20);
		add(btnDodajGrafik);
		
		
		

	}
}
