import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

public class Oddzialy extends JPanel {

		
		Connection connection=null;
		
		private JComboBox comboBox;
		String box;
		private JTable table;
		/**
		 * Create the panel.
		 */
		public Oddzialy() {
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
						
						String query="select * from oddzialy_bankowe order by id_oddzialu";
					
						PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(162, 105, 173, 54);
			add(btnNewButton);

			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 215, 507, 207);
			add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
			JLabel lblDanePracownikw = new JLabel("Dane Oddzia\u0142\u00F3w Banku PKO");
			lblDanePracownikw.setFont(new Font("Verdana", Font.BOLD, 22));
			lblDanePracownikw.setBounds(10, 11, 451, 40);
			add(lblDanePracownikw);

		

	}

}
