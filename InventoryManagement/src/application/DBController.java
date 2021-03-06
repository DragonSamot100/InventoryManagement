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
	private static String user = "sa";
	private static String pass = "";

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.h2.Driver");
			} catch (Exception e) {
				
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
		String query = "Insert into inventory (productID,item,unit,orderUnit,distributor,quantity) values (?, ?, ?, ?, ?, ?)";
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
			return false;
		}
	}
	
	public static boolean modifyInventory(String itemName, String unit, String orderunit, String vendor, int IDNumber, int stock) {
		getConnection();
		String query = "UPDATE inventory SET (item,unit,orderunit,distributor,quantity) = (?,?,?,?,?) WHERE productID = ?";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setString(1, itemName);
			addStatement.setString(2, unit);
			addStatement.setString(3, orderunit);
			addStatement.setString(4, vendor);
			addStatement.setInt(5, stock);
			addStatement.setInt(6, IDNumber);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean addMenuItem(int menuNum, menuItem food) {
		getConnection();
		String query = "INSERT INTO menuItems (MENU,OBJECT) values (?, ?)";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setInt(1, menuNum);
			addStatement.setObject(2, food);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
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
			return false;
		}
		
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
			return false;
		}
		
	}
	
	public static ObservableList<inventoryItem> getInventory() {
		ObservableList<inventoryItem> data = null;
		getConnection();
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM inventory";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(query);
			data = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return data;
	}
	
	public static ObservableList<menuItem> getMenu() {
		ObservableList<menuItem> dataMenu = null;
		getConnection();
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM menuItems";
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
            dataMenu = FXCollections.observableArrayList(getMenuItems(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return dataMenu;
	}
	
	public static ArrayList<menuItem> getMenuItems(ResultSet resultSet) throws SQLException {
		ArrayList<menuItem> dataMenu = new ArrayList<>();
        while (resultSet.next()) 
    	{
    		menuItem item = (menuItem) resultSet.getObject(2);
    		dataMenu.add(item);
    	}
    	return dataMenu;
	}
	
	public static int getMenuItemsSize(){
		int length = 0;
		getConnection();    
		ResultSet resultSet = null;
		try {
			String query = "SELECT COUNT(*) FROM menuItems";
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
            	length = resultSet.getInt(1);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return length;
	}
	
	public static boolean updateMenuItem(int ID, menuItem updated) {
		getConnection();
		String query = "UPDATE menuItems SET object = ? where menu = ?";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setObject(1, updated);
			addStatement.setInt(2, ID);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean deleteMenuItem(int ID) {
		getConnection();
		String query = "DELETE from menuItems where menu = ?";
		try {
			PreparedStatement addStatement = connection.prepareStatement(query);
			addStatement.setInt(1, ID);
			addStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
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
	
	public static ArrayList<inventoryItem> getInventoryAsArray() {
		getConnection();
		ResultSet resultSet = null;
		ArrayList<inventoryItem> data = new ArrayList();
		try {
			String query = "SELECT * FROM inventory";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(query);
			data = dataBaseArrayList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return data;
	}
	
	public static ArrayList<menuItem> getMenuAsArray() {
		getConnection();
		ResultSet resultSet = null;
		ArrayList<menuItem> data = new ArrayList();
		try {
			String query = "SELECT * FROM menuItems";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(query);
			data = getMenuItems(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return data;
	}
	
	public static inventoryItem getItem(String name) {
		getConnection();
		inventoryItem item = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM inventory where item = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, name);
			resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				item = new inventoryItem(resultSet.getString("item"), resultSet.getInt("productID"),
						resultSet.getInt("quantity"), resultSet.getString("distributor"), 
						resultSet.getString("unit"), resultSet.getString("orderunit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return item;
	}
}