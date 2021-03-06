package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Account;
import model.Address;
import model.Order;
import model.OrderRow;

public class OrderPostgreSQLDAOImpl extends PostgreSQLBaseDao implements OrderDAO{

	@Override
	public Order insert(Order order) {
		
		// Momenteel Account en afleveradres nog statisch
		
		try (Connection con = getConnection()) {
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO bestelling (afleveradres, account) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, order.getAddress().getId());
			stmt.setInt(2, order.getAccount().getId());
			
			stmt.executeUpdate();
			
			ResultSet tableKeys = stmt.getGeneratedKeys();
			tableKeys.next();
			
			int autoGeneratedID = tableKeys.getInt(1);
			
			order.setId(autoGeneratedID);
			
			return order;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
			}
	}

	public Order delete(Order order) {
		try {
			Connection conn = super.getConnection();

			String query = "delete from bestelling where id = ?;";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, order.getId());
			
			conn.close();
			return order;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Order getById(int id) {
		
		try {
			Connection conn = super.getConnection();

			String query = "select * from bestelling where id = ?;";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			
			ResultSet dbResultset = stmt.executeQuery();
			dbResultset.next();
			
			int addressId = dbResultset.getInt("afleveradres");
			int accountId = dbResultset.getInt("account");
			
			// Nog testdata
			Address address = new Address("daltonlaan", "200");
			Account account = new Account("test@test.nl", "test");
			
			Order order = new Order(id, account, address);
			
			return order;
			
		} catch (SQLException e) {
			return null;
		}
	}

}
