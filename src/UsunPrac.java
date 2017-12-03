import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsunPrac extends JPanel {

	Connection connection=null;
	private JTable table;
	private JTextField textField;
	
	
	public void refreshTable(){
		try {
			
			String query="select p.id_pracownika,p.imie, p.nazwisko, p.nr_konta_bankowego,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska order by p.id_pracownika";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the panel.
	 */
	public UsunPrac() {
		setBackground(new Color(0, 102, 255));
		setBounds(202, 11, 527, 444);
		
		connection=SqlConnection.dbConnector();
		setBackground(new Color(102, 204, 255));
		setLayout(null);
		setLayout(null);
		
		JLabel lblUsuwaniePracownikw = new JLabel("Usuwanie pracownik\u00F3w");
		lblUsuwaniePracownikw.setFont(new Font("Verdana", Font.BOLD, 22));
		lblUsuwaniePracownikw.setBounds(10, 11, 479, 37);
		add(lblUsuwaniePracownikw);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 272, 479, 150);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			String query="select p.id_pracownika,p.imie, p.nazwisko, p.nr_konta_bankowego,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska order by p.id_pracownika";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		JLabel lblWpiszIdPracownika = new JLabel("Wpisz ID pracownika, kt\u00F3rego chcesz usun\u0105\u0107:");
		lblWpiszIdPracownika.setBounds(10, 74, 348, 19);
		add(lblWpiszIdPracownika);
		
		textField = new JTextField();
		textField.setBounds(20, 106, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnUsu = new JButton("USU\u0143");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
						
					int dialogButton = JOptionPane.YES_NO_OPTION;
					dialogButton = JOptionPane.showConfirmDialog (null, "Na pewno chcesz usun¹æ pracownika?","WARNING", dialogButton);
					
					if(dialogButton == JOptionPane.YES_OPTION) {
					String query1="delete from grafiki_zmian where id_pracownika='"+textField.getText()+" ' ";
					PreparedStatement pst1=connection.prepareStatement(query1);				
					
					String query="delete from pracownicy where id_pracownika='"+textField.getText()+" ' ";
					PreparedStatement pst=connection.prepareStatement(query);
					
					pst1.execute();
					pst.execute();
				
					JOptionPane.showMessageDialog(null, "Pracownik usuniêty");
					
					pst.close();
					pst1.close();}
					
				}catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Uzupe³nij dane do usuniêcia!");
				}
				
			}
		});
		btnUsu.setBounds(145, 105, 89, 23);
		add(btnUsu);
		
		JButton btnOdwie = new JButton("Od\u015Bwie\u017C");
		btnOdwie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		btnOdwie.setBounds(145, 151, 89, 23);
		add(btnOdwie);
	}

}
