import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodajPrac extends JPanel {

	Connection connection=null;
	private JComboBox comboBox, comboBox_1;
	String box, box1, box2, box3;
	private JTextField textFieldNrKonta;
	private JTextField textFieldHaslo;
	private JTextField textFieldNazwisko;
	private JTextField textFieldImie;
	
	public static boolean isInt(String str) {
	    try {
	    	Float.parseFloat(str);
	     
	    } catch (NumberFormatException e) {
	        return false;
	    }

	    return true;
	}
	/**
	 * Create the panel.
	 */
	public DodajPrac() {
		setBackground(new Color(0, 102, 255));
		setBounds(202, 11, 527, 444);
		
		connection=SqlConnection.dbConnector();
		setBackground(new Color(102, 204, 255));
		setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblDodawanieNowegoPracownika = new JLabel("Dodawanie nowego pracownika");
		lblDodawanieNowegoPracownika.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDodawanieNowegoPracownika.setBounds(26, 25, 431, 31);
		add(lblDodawanieNowegoPracownika);
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(26, 113, 46, 14);
		add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(26, 138, 88, 14);
		add(lblNazwisko);
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setBounds(26, 163, 46, 14);
		add(lblHaso);
		
		JLabel lblNrKonta = new JLabel("Nr konta bankowego");
		lblNrKonta.setBounds(26, 192, 119, 14);
		add(lblNrKonta);
		
		JLabel lblStanowisko = new JLabel("Stanowisko");
		lblStanowisko.setBounds(26, 218, 101, 14);
		add(lblStanowisko);
		
		JLabel lblIdPracownika = new JLabel("ID pracownika");
		lblIdPracownika.setBounds(26, 88, 88, 14);
		add(lblIdPracownika);
		
		textFieldNrKonta = new JTextField();
		textFieldNrKonta.setBounds(180, 189, 214, 20);
		add(textFieldNrKonta);
		textFieldNrKonta.setColumns(10);
		
		textFieldHaslo = new JTextField();
		textFieldHaslo.setBounds(180, 162, 214, 20);
		add(textFieldHaslo);
		textFieldHaslo.setColumns(10);
		
		textFieldNazwisko = new JTextField();
		textFieldNazwisko.setBounds(180, 135, 214, 20);
		add(textFieldNazwisko);
		textFieldNazwisko.setColumns(10);
		
		textFieldImie = new JTextField();
		textFieldImie.setBounds(180, 110, 214, 20);
		add(textFieldImie);
		textFieldImie.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(180, 88, 65, 14);
		add(lblNewLabel);
		//lblNewLabel.setText(lblNewLabel.getText()+textFieldNazwisko.getText());
	
		String [] stanowiskaString= {"","Ksiêgowoœæ", "Doradca klienta", "Administrator"};
		JComboBox comboBox = new JComboBox(stanowiskaString);
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		comboBox.setBounds(180, 215, 214, 20);
		add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox comboBox = (JComboBox) event.getSource();

                String selected = (String) comboBox.getSelectedItem();
                switch(selected){
                case "Ksiêgowoœæ": box=comboBox.getSelectedItem().toString();
                case "Doradca klienta": box=comboBox.getSelectedItem().toString();
                case "Administrator": box=comboBox.getSelectedItem().toString();

                  }
                
			}
		}
				);
		
		
		try{
			
			String query="select max(id_pracownika)+1 from pracownicy";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
		
			while(rs.next()){
			lblNewLabel.setText(rs.getString("max(id_pracownika)+1"));
				//lblNewLabel.setText(rs.toString());
			}
			
			rs.close();
			pst.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String gthxt=Integer.toString(textFieldNrKonta.getText().length());
				String ggg="24";
				
				//JOptionPane.showMessageDialog(null,isInt(textFieldNrKonta.getText()));
				
				if(isInt(textFieldNrKonta.getText()) && gthxt.equals(ggg))
				{
				try {
					String query="insert into pracownicy (id_pracownika, imie, nazwisko, login, haslo, nr_konta_bankowego,id_oddzialu,id_stanowiska)"+
"values(?,?,?,?,?,?,'"+box2+"', (select id_stanowiska from stanowiska where nazwa_stanowiska='"+box+"'))";

					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, lblNewLabel.getText());
					pst.setString(2, textFieldImie.getText());
					pst.setString(3, textFieldNazwisko.getText());
					pst.setString(4,lblNewLabel.getText()+textFieldNazwisko.getText() );//textFieldLogin.getText());
					pst.setString(5, textFieldHaslo.getText());
					pst.setString(6, textFieldNrKonta.getText());
				
					
					//pst.setString(7, box);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Dane zapisane");
					
					pst.close();
					//rs.close();
					removeAll();
					repaint();
					revalidate();
					
					DodajPrac dodajPrac= new DodajPrac();
					
					
				} catch (Exception e3) {
					e3.printStackTrace();
					JOptionPane.showMessageDialog(null, "Uzupe³nij dane!");
				}
				}else{
					JOptionPane.showMessageDialog(null, "Nieprawid³owy nr konta!");
				}
				
			}
		});
		btnDodaj.setBounds(208, 288, 174, 42);
		add(btnDodaj);
		
		JLabel lblIdOddziau = new JLabel("Id oddzia\u0142u:");
		lblIdOddziau.setBounds(26, 243, 101, 14);
		add(lblIdOddziau);
		
		String [] oddzialy= {"","1","2","3","4","5","10"};
		
		JComboBox comboBox_1 = new JComboBox(oddzialy);
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setToolTipText("");
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox comboBox_1 = (JComboBox) e.getSource();

                String selected = (String) comboBox_1.getSelectedItem();
                switch(selected){
                case "1": box2=comboBox_1.getSelectedItem().toString();
                case "2": box2=comboBox_1.getSelectedItem().toString();
                case "3": box2=comboBox_1.getSelectedItem().toString();
                case "4": box2=comboBox_1.getSelectedItem().toString();
                case "5": box2=comboBox_1.getSelectedItem().toString();
                case "10": box2=comboBox_1.getSelectedItem().toString();

                  }
                
			}
		});
		comboBox_1.setBounds(180, 240, 214, 20);
		add(comboBox_1);
		
		
		//String [] dniTygodnia= {"","poniedzialek", "wtorek", "sroda", "czwartek", "piatek"};
		
		
		//String [] godzRoz= {"","7:00", "8:00", "9:00", "10:00", "11:00","12:00", "13:00", "14:00","15:00"};
		
	
		//String [] godzZak= {"","12:00", "13:00", "14:00","15:00", "16:00", "17:00", "18:00","19:00","20:00"};
	}
}
