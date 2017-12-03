import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifyPrac extends JPanel {

	
	Connection connection=null;
	private JComboBox comboBox, comboBox_1;
	String box=null, box2=null;
	private JTable table;
	private JTextField textFieldID;
	private JTextField textFieldImie;
	private JTextField textFieldNazwisko;
	private JTextField textFieldLogin;
	private JTextField textFieldHaslo;
	private JTextField textFieldNrKonta;

	public static boolean isInt(String str) {
	    try {
	    	Float.parseFloat(str);
	     
	    } catch (NumberFormatException e) {
	        return false;
	    }

	    return true;
	}
	

	public void refreshTable(){
		try {
			
			String query="select p.id_pracownika,p.imie, p.nazwisko,p.login,p.haslo, p.nr_konta_bankowego,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska order by p.id_pracownika";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create the panel.
	 */
	public ModifyPrac() {
		setBackground(new Color(0, 102, 255));
		setBounds(202, 11, 527, 444);
		
		connection=SqlConnection.dbConnector();
		setBackground(new Color(102, 204, 255));
		setLayout(null);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 507, 122);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			String query="select p.id_pracownika,p.imie, p.nazwisko,p.login,p.haslo, p.nr_konta_bankowego,p.id_oddzialu, s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska order by p.id_pracownika";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		JLabel lblModyfikowanieDanychPracownikw = new JLabel("Modyfikowanie danych pracownik\u00F3w");
		lblModyfikowanieDanychPracownikw.setHorizontalAlignment(SwingConstants.CENTER);
		lblModyfikowanieDanychPracownikw.setFont(new Font("Verdana", Font.BOLD, 22));
		lblModyfikowanieDanychPracownikw.setBounds(10, 11, 507, 39);
		add(lblModyfikowanieDanychPracownikw);
		
		JLabel lblWybierzIdPracownika = new JLabel("Wybierz ID pracownika, kt\u00F3rego dane chcesz zmodyfikowa\u0107:");
		lblWybierzIdPracownika.setBounds(26, 73, 347, 14);
		add(lblWybierzIdPracownika);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(400, 70, 86, 20);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblImi = new JLabel("Imi\u0119:");
		lblImi.setBounds(28, 124, 46, 14);
		add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(26, 149, 74, 14);
		add(lblNazwisko);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(28, 174, 46, 14);
		add(lblLogin);
		
		JLabel lblHaso = new JLabel("Has\u0142o:");
		lblHaso.setBounds(28, 199, 46, 14);
		add(lblHaso);
		
		JLabel lblNrKontaBankowego = new JLabel("Nr konta bankowego:");
		lblNrKontaBankowego.setBounds(28, 224, 138, 14);
		add(lblNrKontaBankowego);
		
		JLabel lblNazwaStanowiska = new JLabel("Nazwa stanowiska:");
		lblNazwaStanowiska.setBounds(28, 248, 138, 14);
		add(lblNazwaStanowiska);
		
		textFieldImie = new JTextField();
		textFieldImie.setBounds(195, 121, 178, 20);
		add(textFieldImie);
		textFieldImie.setColumns(10);
		
		textFieldNazwisko = new JTextField();
		textFieldNazwisko.setText("");
		textFieldNazwisko.setBounds(195, 146, 178, 20);
		add(textFieldNazwisko);
		textFieldNazwisko.setColumns(10);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setText("");
		textFieldLogin.setBounds(195, 171, 178, 20);
		add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		textFieldHaslo = new JTextField();
		textFieldHaslo.setText("");
		textFieldHaslo.setBounds(195, 196, 178, 20);
		add(textFieldHaslo);
		textFieldHaslo.setColumns(10);
		
		textFieldNrKonta = new JTextField();
		textFieldNrKonta.setBounds(195, 221, 178, 20);
		add(textFieldNrKonta);
		textFieldNrKonta.setColumns(10);
		
		
		
		JButton btnModyfikuj = new JButton("Modyfikuj");
		btnModyfikuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String gthxt=Integer.toString(textFieldNrKonta.getText().length());
				String ggg="24";
				
				
				
				try{
					try{
					if(textFieldID.getText().isEmpty()==true){
						JOptionPane.showMessageDialog(null, "Uzupe³nij numer pracownika");
					}}catch(Exception e1 ){
						e1.printStackTrace();
					}
					
					if(	textFieldImie.getText().isEmpty()==false ){
						String query="Update pracownicy set imie='" +textFieldImie.getText()+"' where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();
					}


					
					if(textFieldNazwisko.getText().isEmpty()==false){
						String query="Update pracownicy set nazwisko='" +textFieldNazwisko.getText()+"' where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();
					}
					
					if(textFieldLogin.getText().isEmpty()==false){
						String query="Update pracownicy set login='" +textFieldLogin.getText()+"' where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();
					}
					
					if(textFieldHaslo.getText().isEmpty()==false){
						String query="Update pracownicy set haslo='" +textFieldHaslo.getText()+"' where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();
					}
					
					if(textFieldNrKonta.getText().isEmpty()==false){
						if( (isInt(textFieldNrKonta.getText()) && gthxt.equals(ggg)) )
						{
						String query="Update pracownicy set nr_konta_bankowego='" +textFieldNrKonta.getText()+"' where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();}else{
							JOptionPane.showMessageDialog(null, "Nieprawid³owy nr konta!");
						}
					}
					if(box!=null){
						String query="Update pracownicy set id_stanowiska=(select id_stanowiska from stanowiska where nazwa_stanowiska='"+box+"') where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();
					
					}
					if(box2!=null){
						String query="Update pracownicy set id_oddzialu='"+box2+"' where id_pracownika='" +textFieldID.getText()+ "' ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.execute();
						pst.close();
					
					}
					JOptionPane.showMessageDialog(null, "Dane zaktualizowane");
					
			
					
				}catch (Exception ex){
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Uzupe³nij dane do aktualizacji!");
				}
				
				refreshTable();
			
	
			}});
		btnModyfikuj.setBounds(397, 149, 89, 64);
		add(btnModyfikuj);
		
		String [] stanowiskaString= {"","Ksiêgowoœæ", "Doradca klienta", "Administrator"};
		JComboBox comboBox = new JComboBox(stanowiskaString);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				JComboBox comboBox = (JComboBox) event.getSource();

                String selected = (String) comboBox.getSelectedItem();
                switch(selected){
                case "Ksiêgowoœæ": box=comboBox.getSelectedItem().toString();
                case "Doradca klienta": box=comboBox.getSelectedItem().toString();
                case "Administrator": box=comboBox.getSelectedItem().toString();

			}}
		});
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		comboBox.setBounds(195, 245, 178, 20);
		add(comboBox);
		
		JLabel lblIdOddziau = new JLabel("ID oddzia\u0142u:");
		lblIdOddziau.setBounds(26, 273, 107, 14);
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
		comboBox_1.setBounds(195, 270, 178, 20);
		add(comboBox_1);
	
		
		}
}

