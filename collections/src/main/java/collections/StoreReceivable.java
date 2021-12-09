package collections;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreReceivable {

	private static final String SQL_INSERT = "INSERT INTO RECEIVABLES (POLICY_NUMBER, TOTAL_PREMIUM, PAID) VALUES (?, ?, ?)";
	private String policyNumber;
	private BigDecimal totalPremium;

	public StoreReceivable(String policyNumber, BigDecimal totalPremium) {
		this.policyNumber = policyNumber;
		this.totalPremium = totalPremium;
	}

	public void doStore() {
		String url = "jdbc:sqlite:src/test/resources/test.db";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url);
			ps = connection.prepareStatement(SQL_INSERT);
			ps.setString(1, policyNumber);
			ps.setBigDecimal(2, totalPremium);
			ps.setString(3, "0");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
	}

}
