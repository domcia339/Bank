import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.Button;
import java.awt.ScrollPane;
import java.awt.Choice;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.SystemColor;

public class Administrator extends JFrame {

	
	public void refreshTable(){
		try {
			
			String query="select p.id_pracownika,p.imie, p.nazwisko,p.login, p.nr_konta_bankowego,p.id_oddzialu ,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska  where p.id_pracownika='"+Loguj.ID+"' ";
		
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	Connection connection=null;
	private JTable table;
	public JFrame logframe;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator frame = new Administrator();
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
	public Administrator() {
		connection=SqlConnection.dbConnector();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 505);
		setResizable(false);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setForeground(SystemColor.controlHighlight);
		panelMenu.setBackground(new Color(0, 0, 153));
		panelMenu.setBounds(10, 11, 182, 444);
		panel.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setForeground(SystemColor.controlHighlight);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Verdana", Font.BOLD, 22));
		lblMenu.setBounds(10, 11, 162, 25);
		panelMenu.add(lblMenu);
		
		JPanel panelGlowny = new JPanel();
		panelGlowny.setBackground(new Color(0, 102, 255));
		panelGlowny.setBounds(202, 11, 527, 444);
		panel.add(panelGlowny);
		panelGlowny.setLayout(new CardLayout(0, 0));
		
		JPanel panelTwojeDane = new JPanel();
		panelTwojeDane.setBackground(new Color(102, 204, 255));
		panelGlowny.add(panelTwojeDane, "name_304468575111006");
		panelTwojeDane.setLayout(null);
		
		
		
	
		
				
		JButton btnTwojeDane = new JButton("Twoje Dane");
		btnTwojeDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				panelGlowny.add(panelTwojeDane);
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				JLabel lblNewLabel = new JLabel("Dane personalne:");
				lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
				lblNewLabel.setBounds(30, 11, 197, 26);
				panelTwojeDane.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("ID pracownika:");
				lblNewLabel_1.setBounds(30, 73, 142, 14);
				panelTwojeDane.add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("Imi\u0119:");
				lblNewLabel_2.setBounds(30, 98, 142, 14);
				panelTwojeDane.add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("Nazwisko:");
				lblNewLabel_3.setBounds(30, 123, 142, 14);
				panelTwojeDane.add(lblNewLabel_3);
				
				JLabel lblNewLabelID = new JLabel("");
				lblNewLabelID.setBounds(167, 73, 220, 14);
				panelTwojeDane.add(lblNewLabelID);
				
				JLabel lblNewLabelImie = new JLabel("");
				lblNewLabelImie.setBounds(167, 98, 220, 14);
				panelTwojeDane.add(lblNewLabelImie);
				
				JLabel lblNewLabelNazwisko = new JLabel("");
				lblNewLabelNazwisko.setBounds(167, 123, 220, 14);
				panelTwojeDane.add(lblNewLabelNazwisko);
				
				JLabel lblNrKontaBankowego = new JLabel("Nr konta bankowego:");
				lblNrKontaBankowego.setBounds(30, 173, 142, 14);
				panelTwojeDane.add(lblNrKontaBankowego);
				
				JLabel lblNewLabelNrKonta = new JLabel("");
				lblNewLabelNrKonta.setBounds(167, 173, 220, 14);
				panelTwojeDane.add(lblNewLabelNrKonta);
				
				JLabel lblStanowisko = new JLabel("Stanowisko:");
				lblStanowisko.setBounds(30, 198, 142, 14);
				panelTwojeDane.add(lblStanowisko);
				
				JLabel labelStanowisko = new JLabel("");
				labelStanowisko.setBounds(167, 198, 220, 14);
				panelTwojeDane.add(labelStanowisko);
				
				JLabel lblGrafikZmian = new JLabel("Grafik zmian:");
				lblGrafikZmian.setFont(new Font("Verdana", Font.PLAIN, 22));
				lblGrafikZmian.setBounds(30, 258, 197, 26);
				panelTwojeDane.add(lblGrafikZmian);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(30, 306, 462, 98);
				panelTwojeDane.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				JLabel lblLogin = new JLabel("Login");
				lblLogin.setBounds(29, 148, 128, 14);
				panelTwojeDane.add(lblLogin);
				
				JLabel labelLogin = new JLabel("");
				labelLogin.setBounds(167, 148, 220, 14);
				panelTwojeDane.add(labelLogin);
				
				JLabel lblIdOddziau = new JLabel("Id oddzia\u0142u:");
				lblIdOddziau.setBounds(30, 223, 127, 14);
				panelTwojeDane.add(lblIdOddziau);
				
				JLabel label = new JLabel("");
				label.setBounds(167, 223, 220, 14);
				panelTwojeDane.add(label);
				
				try{
				
				String query="select  dzien_tygodnia, godzina_rozpoczecia, godzina_zakonczenia from grafiki_zmian where id_pracownika='"+Loguj.ID+"' ";
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				rs.close();
				pst.close();
				
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
					try{
				
				String query="select p.id_pracownika,p.imie, p.nazwisko,p.login, p.nr_konta_bankowego,p.id_oddzialu ,s.NAZWA_STANOWISKA from pracownicy p join stanowiska s on  s.id_stanowiska=p.id_stanowiska  where p.id_pracownika='"+Loguj.ID+"' ";
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
			
				while(rs.next()){
				lblNewLabelID.setText(rs.getString("id_pracownika"));
				lblNewLabelImie.setText(rs.getString("imie"));
				lblNewLabelNazwisko.setText(rs.getString("nazwisko"));
				labelLogin.setText(rs.getString("login"));
				lblNewLabelNrKonta.setText(rs.getString("nr_konta_bankowego"));
				labelStanowisko.setText(rs.getString("nazwa_stanowiska"));
				label.setText(rs.getString("id_oddzialu"));
				}
				
				rs.close();
				pst.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
					
					//refreshTable();
			}
		});
		btnTwojeDane.setBounds(20, 47, 152, 23);
		panelMenu.add(btnTwojeDane);
		
		JButton btnWyloguj = new JButton("WYLOGUJ");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
			Loguj log=	new Loguj();
			log.setVisible(true);
			
				  setVisible(false);
				   dispose();
				
				
			}
		});
		btnWyloguj.setBounds(20, 410, 152, 23);
		panelMenu.add(btnWyloguj);
		
		JButton btnDanePracownikw = new JButton("Dane pracownik\u00F3w");
		btnDanePracownikw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				DanePrac danePrac= new DanePrac();
				
				panelGlowny.add(danePrac);
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
			}
		});
		btnDanePracownikw.setBounds(21, 92, 151, 23);
		panelMenu.add(btnDanePracownikw);
		
		JButton btnDodajPracownika = new JButton("Dodaj pracownika");
		btnDodajPracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				DodajPrac dodajPrac= new DodajPrac();
				
				panelGlowny.add(dodajPrac);
				panelGlowny.repaint();
				panelGlowny.revalidate();
			}
		});
		btnDodajPracownika.setBounds(20, 126, 152, 23);
		panelMenu.add(btnDodajPracownika);
		
		JButton btnUsuPracownika = new JButton("Usu\u0144 pracownika");
		btnUsuPracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				UsunPrac usunPrac= new UsunPrac();
				
				panelGlowny.add(usunPrac);
				panelGlowny.repaint();
				panelGlowny.revalidate();
			}
		});
		btnUsuPracownika.setBounds(20, 160, 152, 23);
		panelMenu.add(btnUsuPracownika);
		
		JButton btnModyfikujDanePracownika = new JButton("Modyfikuj dane ");
		btnModyfikujDanePracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				ModifyPrac modifyPrac= new ModifyPrac();
				
				panelGlowny.add(modifyPrac);
				panelGlowny.repaint();
				panelGlowny.revalidate();
			}
		});
		btnModyfikujDanePracownika.setBounds(20, 194, 152, 25);
		panelMenu.add(btnModyfikujDanePracownika);
		
		JButton btnDodajGrafik = new JButton("<html>Dodaj/modyfikuj<br /> grafik</html>");
		btnDodajGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				DodajGrafik dodajGrafik= new DodajGrafik();
				
				panelGlowny.add(dodajGrafik);
				panelGlowny.repaint();
				panelGlowny.revalidate();
			}
		});
		btnDodajGrafik.setBounds(20, 230, 152, 37);
		panelMenu.add(btnDodajGrafik);
		
		JButton btnOddziay = new JButton("Oddzia\u0142y");
		btnOddziay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelGlowny.removeAll();
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
				Oddzialy oddzialy= new Oddzialy();
				
				panelGlowny.add(oddzialy);
				panelGlowny.repaint();
				panelGlowny.revalidate();
				
			}
		});
		btnOddziay.setBounds(20, 279, 152, 23);
		panelMenu.add(btnOddziay);
	}
}
