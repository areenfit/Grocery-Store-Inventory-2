// Areen Fetyani 1212673

// Class Item that implements the interface Comparable
public class Item implements Comparable<Item> {

	// Data Fields
	private String type;
	private int quantity;
	private double price;
	private String expDate;

	// Counstructors
	public Item() {
	}

	public Item(String type) {
		this.type = type;
	}

	// Setters & Getters
	public Item setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}
	

	public Item setPrice(double price) {
		this.price = price;
		return this;
	}
	
	public Item setExpDate(String expDate) {
		this.expDate = expDate;
		return this;
	}

	public String getType() {
		return type;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
	
	public String getExpDate() {
		return expDate;
	}

	// Methods to update the items
	public Item update(int qtyIncrease) {
		quantity= quantity+qtyIncrease; //update the quantity
		return this;
	}

	public Item update(double adjustmentFactor) {
		price = price + (price*adjustmentFactor); //update the price
		return this;
	}
	
	public Item update(String expDate) {
		this.expDate = expDate; //update the price
		return this;
	}

	// Compares between prices and returns the difference between the two
	@Override
	public int compareTo(Item o) {
		return (int) (this.getPrice() - o.getPrice());
	}

}