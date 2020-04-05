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

public class DBController {
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
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
        return connection;
    }

    public static boolean addInventory(String itemName, String unit, String orderunit, String vendor, int IDNumber, int stock) 
    {
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
        //    System.out.println("SQLException: " + e.getMessage());
          //  System.out.println("SQLState: " + e.getSQLState());
            //System.out.println("VendorError: " + e.getErrorCode());
        }
        return false;
    }
    public static ObservableList<inventoryItem> getInventory()
	{
    	ObservableList<inventoryItem> data = null;
		getConnection();
		ResultSet resultSet = null;
	       try {
	    	   String query = "SELECT * FROM inventory";
	    	   Statement stmt = connection.createStatement();  
	           resultSet = stmt.executeQuery(query);

	        }catch (SQLException e) {
	           e.printStackTrace();
	        }

	       
		try {
			data = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
    private static ArrayList<inventoryItem> dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<inventoryItem> data =  new ArrayList<>();
        while (resultSet.next()) {
            inventoryItem item = new inventoryItem();
            item.productID.set(resultSet.getInt("productID"));
            item.item.set(resultSet.getString("item"));
            item.quantity.set(resultSet.getInt("quantity"));
            item.distributor.set(resultSet.getString("distributor"));
            data.add(item);
        }
        return data;
    }
}