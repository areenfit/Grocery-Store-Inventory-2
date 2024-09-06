// Areen Fetyani 1212673

// Class Brand that inherits Class Item
public class Brand extends Item {

	// Data Fields
	private String brand;

	// Counstructors
	public Brand() {
	}

	public Brand(String brand, String type) {
		super(type);
		this.brand = brand;
	}
	
	// Getter
	public String getBrand() {
		return brand;	
	}
}