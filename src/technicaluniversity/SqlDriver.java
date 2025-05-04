package technicaluniversity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDriver {
	private static Connection conn;

	public static boolean connect(String dbFile) {
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public static void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static boolean createTables() {
		if (conn == null)
			return false;
		String sql1 = "CREATE TABLE IF NOT EXISTS students ("
				+ "id integer PRIMARY KEY AUTOINCREMENT,"
				+ "name varchar(50) NOT NULL,"
				+ "surname varchar(50) NOT NULL,"
				+ "yearOfBirth integer NOT NULL,"
				+ "type varchar(20) NOT NULL);";

		String sql2 = "CREATE TABLE IF NOT EXISTS grades ("
				+ "id integer,"
				+ "grade float NOT NULL,"
				+ "FOREIGN KEY (id) REFERENCES students(id));";

		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql1);
			stmt.execute(sql2);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static boolean truncate() {
		if (conn == null)
			return false;
		String sql1 = "DELETE FROM grades;";
		String sql2 = "DELETE FROM students;";

		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql1);
			stmt.execute(sql2);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static boolean insertStudent(int id, String name, String surname, int yearOfBirth, String group) {
		if (conn == null)
			return false;
		String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, surname);
			pstmt.setInt(4, yearOfBirth);
			pstmt.setString(5, group);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean insertGrade(int id, float grade) {
		if (conn == null)
			return false;
		String sql = "INSERT INTO grades VALUES (?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setFloat(2, grade);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean selectStudentsAndGrades(Database db) {
		if (conn == null)
			return false;
		String sql1 = "SELECT * FROM students";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				db.addStudent(InputSanitizer.toType(rs1.getString("type")),
						rs1.getString("name"),
						rs1.getString("surname"),
						rs1.getInt("yearOfBirth"));

				String sql2 = "SELECT * FROM grades WHERE id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, rs1.getInt("id"));
				ResultSet rs2 = pstmt.executeQuery();
				while (rs2.next()) {
					db.getStudent(rs1.getInt("id")).addGrade(rs2.getFloat("grade"));
				}
			}
		} catch (IllegalArgumentException | SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
