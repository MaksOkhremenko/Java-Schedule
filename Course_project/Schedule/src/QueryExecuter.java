import java.sql.*;

public class QueryExecuter {
	private Connection connection;
	private PreparedStatement prStatement;
	private String sqlCommand;

	public QueryExecuter(Connection connection) {
		this.connection = connection;
	}
	
	public ResultSet executeQuery(String table, String variable) throws SQLException {
		if(connection!=null) {
			ResultSet resultSet;
			sqlCommand = "SELECT " + variable + " FROM " + table + " GROUP BY " + variable + " ORDER BY " + variable + " ASC";
			prStatement = connection.prepareStatement(sqlCommand);
			resultSet = prStatement.executeQuery();
			return resultSet;
		} else return null;
	}

	public ResultSet executeNameQuery(String table) throws SQLException {
		if (connection != null) {
			switch (table) {
			case "teachers":
				sqlCommand = "SELECT teacher_name FROM teachers ORDER BY teacher_id ASC";
				break;
			case "subjects":
				sqlCommand = "SELECT subject_name FROM subjects ORDER BY subject_id ASC";
				break;
			default:
				return null;
			}
			prStatement = connection.prepareStatement(sqlCommand);
			ResultSet resultSet = prStatement.executeQuery();
			return resultSet;
		} else
			return null;
	}

	public ResultSet executeDayScheduleQuery(String day) throws SQLException {
		if (connection != null) {
			sqlCommand = "SELECT sequence_number,\r\n" + "subjects.subject_name,\r\n" + "subjects.auditorium,\r\n"
					+ "teachers.teacher_name,\r\n" + "students_amount\r\n" + "FROM the_schedule\r\n"
					+ "JOIN teachers ON teachers.teacher_id=the_schedule.teacher_id\r\n"
					+ "JOIN subjects ON subjects.subject_id=the_schedule.subject_id\r\n" + "WHERE day_of_week=?"
					+ "ORDER BY sequence_number ASC;";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setString(1, day);
			ResultSet resultSet = prStatement.executeQuery();
			return resultSet;
		} else
			return null;
	}
	
	public ResultSet executeTaskOne(String day_of_week, String auditorium) throws SQLException{
		if(connection!=null) {
			sqlCommand = "SELECT teachers.teacher_name,\r\n"
					+ "subjects.subject_name,\r\n"
					+ "subjects.auditorium,\r\n"
					+ "day_of_week\r\n"
					+ "FROM the_schedule\r\n"
					+ "JOIN teachers ON teachers.teacher_id=the_schedule.teacher_id\r\n"
					+ "JOIN subjects ON subjects.subject_id=the_schedule.subject_id\r\n"
					+ "WHERE subjects.auditorium=? AND day_of_week=? GROUP BY teacher_name";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setString(1, auditorium);
			prStatement.setString(2, day_of_week);
			ResultSet resultSet = prStatement.executeQuery();
			return resultSet;
		} else return null;
	}
	
	public ResultSet executeTaskTwo(String day_of_week) throws SQLException{
		if(connection!=null) {
			sqlCommand = "SELECT teachers.teacher_id\r\n"
					+ "FROM the_schedule\r\n"
					+ "JOIN teachers ON teachers.teacher_id=the_schedule.teacher_id\r\n"
					+ "WHERE day_of_week=?\r\n"
					+ "GROUP BY teachers.teacher_name;";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setString(1, day_of_week);
			ResultSet resultSet1 = prStatement.executeQuery();
			
			sqlCommand = "SELECT teachers.teacher_name\r\n"
					+ "FROM the_schedule\r\n"
					+ "JOIN teachers ON teachers.teacher_id=the_schedule.teacher_id\r\n"
					+ "WHERE the_schedule.teacher_id!=";
			if(resultSet1.next()) {
				sqlCommand = sqlCommand.concat(resultSet1.getString("teacher_id"));
				while(resultSet1.next()) {
					sqlCommand = sqlCommand.concat(" AND the_schedule.teacher_id!=");
					sqlCommand = sqlCommand.concat(resultSet1.getString("teacher_id"));
				}
				sqlCommand = sqlCommand.concat(" GROUP BY teacher_name");
			}
			prStatement = connection.prepareStatement(sqlCommand);
			ResultSet resultSet2 = prStatement.executeQuery();
			return resultSet2;
		} else return null;
	}
	
	public ResultSet executeTaskThree(String classes_amount) throws SQLException {
		if(connection!=null) {
			sqlCommand = "SELECT day_of_week\r\n"
					+ "FROM the_schedule\r\n"
					+ "GROUP BY day_of_week\r\n"
					+ "HAVING COUNT(day_of_week)=?";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setString(1, classes_amount);
			ResultSet resultSet = prStatement.executeQuery();
			return resultSet;
		} else return null;
	}
	
	public ResultSet executeTaskFour(String auditorium_amount) throws SQLException {
		if(connection!=null) {
			sqlCommand = "SELECT day_of_week\r\n"
					+ "FROM the_schedule\r\n"
					+ "JOIN subjects ON subjects.subject_id=the_schedule.subject_id\r\n"
					+ "GROUP BY day_of_week\r\n"
					+ "HAVING COUNT(subjects.auditorium)=?";
			prStatement = connection.prepareStatement(sqlCommand);
			prStatement.setString(1, auditorium_amount);
			ResultSet resultSet = prStatement.executeQuery();
			return resultSet;
		} else return null;
	}
}
