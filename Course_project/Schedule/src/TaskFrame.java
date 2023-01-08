import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class TaskFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	private JComboBox<String> comboBox_day;
	private JComboBox<String> comboBox_auditorium;
	private JComboBox<String> comboBox_number;
	private JLabel lblDayOfWeek;
	private JLabel lblAuditorium;

	public TaskFrame(Connection connection) {
		this.connection = connection;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] tasksString = { "�������� �1", "�������� �2", "�������� �3", "�������� �4" };
		JComboBox<String> comboBox = new JComboBox<String>(tasksString);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisibilities(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(208, 28, 192, 36);
		contentPane.add(comboBox);

		String[] dayStrings = { "��������", "³������", "������", "������", "�'������" };
		comboBox_day = new JComboBox<String>(dayStrings);
		comboBox_day.setBounds(208, 76, 168, 30);
		contentPane.add(comboBox_day);

		comboBox_auditorium = new JComboBox<String>();
		comboBox_auditorium.setBounds(208, 127, 168, 30);
		contentPane.add(comboBox_auditorium);

		String[] numberStrings = { "1", "2", "3", "4", "5", "6" };
		comboBox_number = new JComboBox<String>(numberStrings);
		comboBox_number.setBounds(208, 76, 168, 30);
		contentPane.add(comboBox_number);

		lblDayOfWeek = new JLabel("\u0414\u0435\u043D\u044C \u0442\u0438\u0436\u043D\u044F");
		lblDayOfWeek.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDayOfWeek.setBounds(104, 74, 272, 32);
		contentPane.add(lblDayOfWeek);

		lblAuditorium = new JLabel("\u0410\u0432\u0434\u0438\u0442\u043E\u0440\u0456\u044F");
		lblAuditorium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuditorium.setBounds(104, 125, 270, 30);
		contentPane.add(lblAuditorium);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPane.setEditable(false);
		textPane.setBounds(93, 165, 410, 142);
		contentPane.add(textPane);

		JButton btnNewButton = new JButton("\u0412\u0438\u043A\u043E\u043D\u0430\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (connection != null) {
					QueryExecuter queryExecuter = new QueryExecuter(connection);
					String taskString = comboBox.getSelectedItem().toString();
					switch (taskString) {
					case "�������� �1":
						try {
							ResultSet resultSet = queryExecuter.executeTaskOne(
									comboBox_day.getSelectedItem().toString(),
									comboBox_auditorium.getSelectedItem().toString());
							if (resultSet.next()) {
								String resultText = "� " + resultSet.getString("day_of_week") + " � ������� �"
										+ resultSet.getString("auditorium") + " �������� ���������: ";
								resultText = resultText.concat(resultSet.getString("teacher_name"));
								while (resultSet.next()) {
									resultText = resultText.concat(", " + resultSet.getString("teacher_name"));
								}
								textPane.setText(resultText);
							} else {
								String resultText = "� ��� ���� �� �������� �����.";
								textPane.setText(resultText);
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						break;
					case "�������� �2":
						try {
							ResultSet resultSet = queryExecuter
									.executeTaskTwo(comboBox_day.getSelectedItem().toString());
							if (resultSet.next()) {
								String resultText = "� " + comboBox_day.getSelectedItem().toString()
										+ " �� ������ ������� ��� ���������:";
								resultText = resultText.concat(resultSet.getString("teacher_name"));
								while (resultSet.next()) {
									resultText = resultText.concat(", " + resultSet.getString("teacher_name"));
								}
								textPane.setText(resultText);
							} else {
								String resultText = "";
								textPane.setText(resultText);
							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						break;
					case "�������� �3":
						try {
							ResultSet resultSet = queryExecuter.executeTaskThree(comboBox_number.getSelectedItem().toString());
							if (resultSet.next()) {
								String resultText = comboBox_number.getSelectedItem().toString() + " ������ ���������� � ��� ��: ";
								resultText = resultText.concat(resultSet.getString("day_of_week"));
								while (resultSet.next()) {
									resultText = resultText.concat(", " + resultSet.getString("day_of_week"));
								}
								textPane.setText(resultText);
							} else {
								String resultText = "���� ������� ������ ���� ������� ���";
								textPane.setText(resultText);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						break;
					case "�������� �4":
						try {
							ResultSet resultSet = queryExecuter.executeTaskFour(comboBox_number.getSelectedItem().toString());
							if (resultSet.next()) {
								String resultText = comboBox_number.getSelectedItem().toString() + " �������� ������� � ��� ��: ";
								resultText = resultText.concat(resultSet.getString("day_of_week"));
								while (resultSet.next()) {
									resultText = resultText.concat(", " + resultSet.getString("day_of_week"));
								}
								textPane.setText(resultText);
							} else {
								String resultText = "���� ������� �������� �������� ���� ������� ���";
								textPane.setText(resultText);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						break;
					}

				}
			}
		});
		btnNewButton.setBounds(238, 317, 114, 36);
		contentPane.add(btnNewButton);
		if (connection != null) {
			QueryExecuter queryExecuter = new QueryExecuter(connection);
			try {
				ResultSet resultSet = queryExecuter.executeQuery("subjects", "auditorium");
				while (resultSet.next()) {
					comboBox_auditorium.addItem(resultSet.getString("auditorium"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
	}

	private void setVisibilities(String taskName) {
		switch (taskName) {
		case "�������� �1":
			comboBox_number.setVisible(false);
			comboBox_day.setVisible(true);
			comboBox_auditorium.setVisible(true);
			lblDayOfWeek.setText("���� �����");
			lblDayOfWeek.setVisible(true);
			lblAuditorium.setVisible(true);
			break;
		case "�������� �2":
			comboBox_number.setVisible(false);
			comboBox_day.setVisible(true);
			comboBox_auditorium.setVisible(false);
			lblDayOfWeek.setText("���� �����");
			lblDayOfWeek.setVisible(true);
			lblAuditorium.setVisible(false);
			break;
		case "�������� �3":
			comboBox_number.setVisible(true);
			comboBox_day.setVisible(false);
			comboBox_auditorium.setVisible(false);
			lblAuditorium.setVisible(false);
			lblDayOfWeek.setText("�-��� ������");
			lblDayOfWeek.setVisible(true);
			break;
		case "�������� �4":
			comboBox_number.setVisible(true);
			comboBox_day.setVisible(false);
			comboBox_auditorium.setVisible(false);
			lblAuditorium.setVisible(false);
			lblDayOfWeek.setText("���. �������");
			lblDayOfWeek.setVisible(true);
			break;
		}
	}
}
