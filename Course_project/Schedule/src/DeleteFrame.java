import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection connection;
	String day_of_week;

	public DeleteFrame(Connection connection, String day_of_week) {
		this.connection = connection;
		this.day_of_week = day_of_week;
		initialization();
	}
	
	private void initialization() {
		setBounds(100, 100, 320, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] comboboxVariants = { "1", "2", "3", "4", "5", "6"};
		JComboBox<String> comboBox = new JComboBox<String>(comboboxVariants);
		comboBox.setBounds(208, 73, 47, 29);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u041F\u043E\u0440\u044F\u0434\u043A\u043E\u0432\u0438\u0439 \u043D\u043E\u043C\u0435\u0440");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(30, 75, 136, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel resultLabel = new JLabel("");
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		resultLabel.setBounds(95, 112, 160, 40);
		contentPane.add(resultLabel);
		
		JButton btnNewButton = new JButton("\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(connection!=null) {
					UpdateExecuter dbUpdater = new UpdateExecuter(connection);	
					try {
						if(dbUpdater.deleteAt(day_of_week, comboBox.getSelectedItem().toString())) {
							resultLabel.setText("Успішно видалено.");
						} else resultLabel.setText("Помилка...");
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(95, 156, 117, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel(day_of_week);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(110, 10, 127, 29);
		contentPane.add(lblNewLabel);
	}
}
