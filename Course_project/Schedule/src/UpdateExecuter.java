import java.sql.*;

public class UpdateExecuter {
	private Connection connection;
	private PreparedStatement prStatement;
	private String sqlCommand;

	public UpdateExecuter(Connection connection) {
		this.connection = connection;
	}

	public boolean addToSchedule(int teacher_id, int subject_id, String day_of_week, String sequence_number,
			String students_amount) throws SQLException {
		if (connection != null) {
			ResultSet resultSet;
			sqlCommand = "SELECT COUNT(*) FROM the_schedule WHERE day_of_week=? AND sequence_number=?";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setString(1, day_of_week);
			prStatement.setString(2, sequence_number);
			resultSet = prStatement.executeQuery();
			resultSet.next();
			if (resultSet.getInt("COUNT(*)") == 1) {
				return false;
			}

			sqlCommand = "SELECT COUNT(subjects.subject_id) FROM the_schedule\r\n"
					+ "JOIN subjects ON subjects.subject_id=the_schedule.subject_id\r\n"
					+ "WHERE subjects.subject_id=?";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setInt(1, subject_id);
			resultSet = prStatement.executeQuery();
			resultSet.next();
			int classesPerWeek = resultSet.getInt("COUNT(subjects.subject_id)") + 1;

			sqlCommand = "INSERT the_schedule(teacher_id, subject_id, day_of_week, sequence_number, classes_per_week, students_amount) \r\n"
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setInt(1, teacher_id);
			prStatement.setInt(2, subject_id);
			prStatement.setString(3, day_of_week);
			prStatement.setString(4, sequence_number);
			prStatement.setInt(5, classesPerWeek);
			prStatement.setString(6, students_amount);
			prStatement.executeUpdate();

			sqlCommand = "UPDATE the_schedule SET classes_per_week=? WHERE subject_id=?";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setInt(1, classesPerWeek);
			prStatement.setInt(2, subject_id);
			prStatement.executeUpdate();

			return true;
		} else
			return false;
	}

	public boolean deleteAt(String day_of_week, String sequence_number) throws SQLException {
		sqlCommand = "SELECT subject_id FROM the_schedule WHERE day_of_week=? AND sequence_number=?";
		prStatement = connection.prepareStatement(sqlCommand);
		prStatement.setString(1, day_of_week);
		prStatement.setString(2, sequence_number);
		ResultSet resultSet;
		int subject_id, classes_per_week;
		resultSet = prStatement.executeQuery();
		if (resultSet.next()) {
			subject_id = resultSet.getInt("subject_id");
		} else 
			return false;
		
		sqlCommand = "SELECT COUNT(subject_id) FROM the_schedule WHERE subject_id=?";
		prStatement = connection.prepareStatement(sqlCommand);
		prStatement.setInt(1, subject_id);
		resultSet = prStatement.executeQuery();
		if (resultSet.next()) {
			classes_per_week = resultSet.getInt("COUNT(subject_id)") - 1;
		} else
			return false;
		
		sqlCommand = "UPDATE the_schedule SET classes_per_week=? WHERE subject_id=?";
		prStatement = connection.prepareStatement(sqlCommand);
		prStatement.setInt(1, classes_per_week);
		prStatement.setInt(2, subject_id);
		prStatement.executeUpdate();
		
		sqlCommand = "DELETE FROM the_schedule WHERE day_of_week=? AND sequence_number=?";
		prStatement = connection.prepareStatement(sqlCommand);
		prStatement.setString(1, day_of_week);
		prStatement.setString(2, sequence_number);
		prStatement.executeUpdate();
		
		return true;
	}
}