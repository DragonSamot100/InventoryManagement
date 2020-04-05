package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class inventoryItem 
{
	IntegerProperty productID  = new SimpleIntegerProperty();
    StringProperty item = new SimpleStringProperty();
    StringProperty distributor = new SimpleStringProperty();
    IntegerProperty quantity = new SimpleIntegerProperty();
    
	public inventoryItem(String name, int id, int stock, String vendor)
	{
		item.set(name);
		productID .set(id);
		quantity.set(stock);
		distributor.set(vendor);
	}
	
	public IntegerProperty productIDProperty() 
	{
        return productID;
    }
	public IntegerProperty quantityProperty() 
	{
        return quantity;
    }
    public StringProperty itemProperty() 
    {
        return item;
    }
    public StringProperty distributor() 
    {
        return item;
    }
    
    inventoryItem()
    {
    	
    }
	
}
