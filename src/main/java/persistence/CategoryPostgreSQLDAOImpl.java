package persistence;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoryPostgreSQLDAOImpl extends PostgreSQLBaseDao implements CategoryDAO {
	
	
	@Override
	public Category findByName(String nm) {
		
        try (Connection con = super.getConnection()) {

            String query = "select naam, omschrijving, afbeelding from categorie where naam = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nm);
            ResultSet dbResultSet = stmt.executeQuery();
            dbResultSet.next();

            String name = dbResultSet.getString("naam");
            String description = dbResultSet.getString("omschrijving");
            String image = dbResultSet.getString("afbeelding");
            
            return new Category(name, description, image);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
