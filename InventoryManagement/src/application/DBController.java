package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBController 
{
	private static Connection connection;
	private static final String url = "jdbc:h2:.\\database\\management";
	private static final String user = "sa";
	private static final String pass = "";

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.h2.Driver");
			} catch (Exception e) {
				System.out.println(e.getMessage() + "\n");
			}

			try {
				connection = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				printSQLException(e);
			}
		}
		return connection;
	}

	public static boolean addInventory(String itemName, String unit, String orderunit, String vendor, int IDNumber, int stock) {
		getConnection();
		String query = "Insert into inventory (productID,Item,Unit,OrderUnit,distributor,quantity) values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setInt(1, IDNumber);
			addStatement.setString(2, itemName);
			addStatement.setString(3, unit);
			addStatement.setString(4, orderunit);
			addStatement.setString(5, vendor);
			addStatement.setInt(6, stock);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return false;
	}

	public static boolean updateStock(int productID, int newStock) {
		getConnection();
		String query = "UPDATE inventory SET quantity = ? where productID = ?";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setInt(1, newStock);
			addStatement.setInt(2, productID);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return false;
	}
	public static boolean deleteItem(int productID) {
		getConnection();
		String query = "DELETE FROM INVENTORY WHERE productID = ?;";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setInt(1, productID);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return false;
	}
	public static ObservableList<inventoryItem> getInventory() {
		ObservableList<inventoryItem> data = null;
		getConnection();
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM inventory";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}

		try {
			data = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return data;
	}

	private static ArrayList<inventoryItem> dataBaseArrayList(ResultSet resultSet) throws SQLException {
		ArrayList<inventoryItem> data = new ArrayList<>();
		while (resultSet.next()) 
		{
			inventoryItem item = new inventoryItem(resultSet.getString("item"), resultSet.getInt("productID"),
					resultSet.getInt("quantity"), resultSet.getString("distributor"), 
					resultSet.getString("unit"), resultSet.getString("orderunit"));
			data.add(item);
		}
		return data;
	}
	private static void printSQLException(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
}