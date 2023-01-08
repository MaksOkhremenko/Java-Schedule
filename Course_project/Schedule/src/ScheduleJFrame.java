import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ScheduleJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JFrame frame;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private InsertFrame insertFrame;
	private DeleteFrame	deleteFrame;
	private TaskFrame taskFrame;
	private DBConnection dbConnection;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleJFrame frame = new ScheduleJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ScheduleJFrame() {
		connectToDataBase();
		initialize();
	}

	private void initialize() {
		// Frame Initialization
		frame = new JFrame();
		frame.setTitle("Розклад");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ComboBox Initialization
		String[] comboboxString = { "Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця" };
		comboBox = new JComboBox<String>(comboboxString);
		comboBox.setBounds(250, 30, 195, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(comboBox.getSelectedItem().toString());
			}
		});
		contentPane.add(comboBox);

		// Table Initialization
		String data[][] = { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" } };
		String column[] = { "№", "Предмет", "Ауд.", "Вчитель", "Студентів" };
		table = new JTable(data, column);
		table.setModel(new DefaultTableModel(
				new Object[][] { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
						{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, },
				new String[] { "\u2116", "\u041F\u0440\u0435\u0434\u043C\u0435\u0442", "\u0410\u0443\u0434.",
						"\u0412\u0447\u0438\u0442\u0435\u043B\u044C",
						"\u0421\u0442\u0443\u0434\u0435\u043D\u0442\u0456\u0432" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(25);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(40);
		table.getColumnModel().getColumn(3).setMaxWidth(270);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setMinWidth(65);
		table.getColumnModel().getColumn(4).setMaxWidth(80);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setBackground(Color.WHITE);
		table.setBounds(60, 90, 565, 295);
		table.setRowHeight(47);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 90, 565, 305);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (insertFrame != null) {
					insertFrame.dispose();
				}
				if(dbConnection.getConnection()!=null) {
					insertFrame = new InsertFrame(comboBox.getSelectedItem().toString(), dbConnection);
				}
			}
		});
		btnNewButton.setBounds(60, 405, 131, 33);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deleteFrame !=null ) {
					deleteFrame.dispose();
				}
				if(dbConnection.getConnection()!=null) {
					deleteFrame = new DeleteFrame(dbConnection.getConnection(), comboBox.getSelectedItem().toString());
					deleteFrame.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(228, 405, 131, 33);
		contentPane.add(btnNewButton_1);
		
		JButton tasksButton = new JButton("\u0417\u0430\u0432\u0434\u0430\u043D\u043D\u044F");
		tasksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskFrame = new TaskFrame(dbConnection.getConnection());
				taskFrame.setVisible(true);
			}
		});
		tasksButton.setBounds(480, 405, 145, 33);
		contentPane.add(tasksButton);
		updateTable("Понеділок");
	}

	private void updateTable(String day) {
		if (dbConnection != null) {
			try {
				QueryExecuter queryExecuter = new QueryExecuter(dbConnection.getConnection());
				ResultSet resultSet = queryExecuter.executeDayScheduleQuery(day);
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 5; j++) {
						table.setValueAt("", i, j);
					}
				}
				int i = 0;
				while (resultSet.next()) {
					table.setValueAt(resultSet.getString("sequence_number"), i, 0);
					table.setValueAt(resultSet.getString("subject_name"), i, 1);
					table.setValueAt(resultSet.getString("auditorium"), i, 2);
					table.setValueAt(resultSet.getString("teacher_name"), i, 3);
					table.setValueAt(resultSet.getString("students_amount"), i, 4);
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void connectToDataBase() {
		String url = "jdbc:mysql://localhost/schedule";
		String username = "root";
		String password = "HabblBabbl32_03";
		try {
			DBConnection dbConnectionTemp = new DBConnection(url, username, password);
			this.dbConnection = dbConnectionTemp;
		} catch (Exception e) {
			System.out.println("Не вдалось з'єднатись...");
			e.printStackTrace();
		}

	}
}