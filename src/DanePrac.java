import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DanePrac extends JPanel {
	
	Connection connection=null;
	private JComboBox comboBox;
	String box;
	private JTextField textField;
	private JTable table;
	private JTextField textFieldGrafik;
	/**
	 * Create the panel.
	 */
	public DanePrac() {
		setBackground(new Color(0, 102, 255));
		setBounds(202, 11, 527, 444);
		
		connection=SqlConnection.dbConnector();
		setBackground(new Color(102, 204, 255));
		setLayout(null);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Za\u0142aduj dane");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
					
					String query="select p.id_pracownika,p.imie, p.nazwisko,p.login, p.haslo, p.nr_konta_bankowego,p.id_oddzialu,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska order by p.id_pracownika";
				
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(30, 62, 149, 23);
		add(btnNewButton);

		
		String [] wyszukiwanie= { "","id_pracownika", "nazwisko","stanowisko"};
		JComboBox comboBox = new JComboBox(wyszukiwanie);
		comboBox.setBounds(30, 130, 149, 23);
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox comboBox = (JComboBox) event.getSource();

                String selected = (String) comboBox.getSelectedItem();
                switch(selected){
                case "id_pracownika": box=comboBox.getSelectedItem().toString();
                case "nazwisko": box=comboBox.getSelectedItem().toString();
                case "stanowisko": box=comboBox.getSelectedItem().toString();
                }
                
			}
		});
		
		
		textField = new JTextField();
		textField.setBounds(30, 166, 149, 23);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Wyszukaj dane po:");
		lblNewLabel.setBounds(30, 96, 149, 23);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 262, 507, 160);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					if(box=="id_pracownika"){
						
						String query="select p.id_pracownika,p.imie, p.nazwisko, p.nr_konta_bankowego,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska  where p.id_pracownika='"+textField.getText()+"' ";
				
						PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}else if(box=="nazwisko"){
						String query="select p.id_pracownika,p.imie, p.nazwisko, p.nr_konta_bankowego,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska  where p.nazwisko='"+textField.getText()+"' ";
						
						PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}else if(box=="stanowisko"){
						String query="select p.id_pracownika,p.imie, p.nazwisko, p.nr_konta_bankowego,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska  where s.nazwa_stanowiska='"+textField.getText()+"' ";
						
						PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}else {JOptionPane.showMessageDialog(null, "Nie ma takich danych!");}
					
					
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, "Uzupe³nij dane!");
					
				}
				
			}
		});
		btnOk.setBounds(30, 197, 149, 23);
		add(btnOk);
		
		JLabel lblWybierzNrPracownika = new JLabel("Wybierz nr pracownika:");
		lblWybierzNrPracownika.setBounds(329, 100, 168, 14);
		add(lblWybierzNrPracownika);
		
		textFieldGrafik = new JTextField();
		textFieldGrafik.setBounds(329, 130, 150, 23);
		add(textFieldGrafik);
		textFieldGrafik.setColumns(10);
		
		JButton btnWywietlGrafik = new JButton("Wy\u015Bwietl Grafik");
		btnWywietlGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
						
						String query="select  dzien_tygodnia, godzina_rozpoczecia, godzina_zakonczenia from grafiki_zmian where id_pracownika='"+textFieldGrafik.getText()+"' ";
				
						PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, "Uzupe³nij dane!");
					
				}
			}
		});
		btnWywietlGrafik.setBounds(329, 166, 150, 23);
		add(btnWywietlGrafik);
		
		JLabel lblDanePracownikw = new JLabel("Dane pracownik\u00F3w");
		lblDanePracownikw.setFont(new Font("Verdana", Font.BOLD, 22));
		lblDanePracownikw.setBounds(30, 11, 431, 40);
		add(lblDanePracownikw);
		
		JLabel lblGrafik = new JLabel("Grafik");
		lblGrafik.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrafik.setFont(new Font("Verdana", Font.BOLD, 11));
		lblGrafik.setBounds(330, 66, 149, 14);
		add(lblGrafik);

	}
}
