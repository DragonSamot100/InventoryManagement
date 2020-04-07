package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class inventoryItem 
{
	private IntegerProperty productID  = new SimpleIntegerProperty();
    private StringProperty item = new SimpleStringProperty();
    private StringProperty distributor = new SimpleStringProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();
    private StringProperty unit = new SimpleStringProperty();
    private StringProperty orderunit = new SimpleStringProperty();
    
	public inventoryItem(String name, int id, int stock, String vendor, String aUnit, String aOrderUnit)
	{
		item.set(name);
		productID .set(id);
		quantity.set(stock);
		distributor.set(vendor);
		unit.set(aUnit);
		orderunit.set(aOrderUnit);
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
    public StringProperty distributorProperty() 
    {
        return distributor;
    }
    public StringProperty unitProperty() 
    {
        return unit;
    }
    public StringProperty orderunitProperty() 
    {
        return orderunit;
    }
    
    inventoryItem()
    {
    	
    }
	
}
