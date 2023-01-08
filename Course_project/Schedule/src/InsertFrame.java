import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InsertFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String dayOfWeek;
	private DBConnection dbConnection;

	public InsertFrame(String dayOfWeek, DBConnection dbConnection) {
		this.dayOfWeek = dayOfWeek;
		this.dbConnection = dbConnection;
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(165, 28, 246, 29);
		contentPane.add(comboBox1);	
		if(dbConnection!=null) {
			try {
					QueryExecuter queryExecuter = new QueryExecuter(dbConnection.getConnection());
					ResultSet resultSet = queryExecuter.executeNameQuery("teachers");
					while(resultSet.next()) {
						comboBox1.addItem(resultSet.getString("teacher_name"));
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.setBounds(165, 73, 190, 29);
		contentPane.add(comboBox2);
		if(dbConnection!=null) {
			try {
					QueryExecuter queryExecuter = new QueryExecuter(dbConnection.getConnection());
					ResultSet resultSet = queryExecuter.executeNameQuery("subjects");
					while(resultSet.next()) {
						comboBox2.addItem(resultSet.getString("subject_name"));
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String[] comboboxVariants = { "1", "2", "3", "4", "5", "6"};
		JComboBox<String> comboBox3 = new JComboBox<String>(comboboxVariants);
		comboBox3.setBounds(165, 122, 47, 29);
		contentPane.add(comboBox3);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		textField.setText("0");
		textField.setBounds(165, 171, 53, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0438\u043A\u043B\u0430\u0434\u0430\u0447");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 36, 136, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u041F\u0440\u0435\u0434\u043C\u0435\u0442");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 81, 136, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u041F\u043E\u0440\u044F\u0434\u043A\u043E\u0432\u0438\u0439 \u043D\u043E\u043C\u0435\u0440");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(20, 126, 136, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u041A-\u0441\u0442\u044C \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0456\u0432");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(20, 179, 136, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		
		JLabel resultLabel = new JLabel("");
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		resultLabel.setBounds(365, 160, 136, 36);
		contentPane.add(resultLabel);
		setVisible(true);
		
		JButton btnNewButton = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbConnection != null) {
					UpdateExecuter dbUpdater = new UpdateExecuter(dbConnection.getConnection());	
					try {
						boolean isSuccessful = dbUpdater.addToSchedule(comboBox1.getSelectedIndex()+1, comboBox2.getSelectedIndex()+1, 
								dayOfWeek, comboBox3.getSelectedItem().toString(), textField.getText());
						if(isSuccessful==false) {
							resultLabel.setText("Помилка...");
						} else {
							resultLabel.setText("Успішно додано.");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(365, 194, 154, 36);
		contentPane.add(btnNewButton);
	}
}
