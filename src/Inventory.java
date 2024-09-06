// Areen Fetyani 1212673

import java.util.ArrayList;

// Class Inventory 
public class Inventory {

	// Data Fields
	private ArrayList<Item> inventory = new ArrayList<>();
	private String category;
	private String output;

	// Counstructor
	public Inventory(String category) {
		this.category = category;
	}

	// Methods to add new items to the list
	public void newItem(String type, int quantity, double price,String expDate) {
		if (findItem(type, true) == -1) {
			inventory.add(new Item(type).setQuantity(quantity).setPrice(price).setExpDate(expDate));
			this.setOutput(type + " successfully added");
		} else
			this.setOutput(type + " already exists");
	}

	public void newItem(String brand, String type, int quantity, double price,String expDate) {
		if (findItem(type, true, brand) == -1) {
			inventory.add(new Brand(brand, type).setQuantity(quantity).setPrice(price).setExpDate(expDate));
			this.setOutput(brand + " " + type + " successfully added");
		} else
			this.setOutput(brand + " " + type + " already exists");
	}

	public void removeItem(String type) {
		if (findItem(type, true) != -1) {
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType())) {
					inventory.remove(item);
					this.setOutput(type + " is removed succesfully.");
				}
			}
		}
	}

	public void removeItem(String type, String brand) {
		if (findItem(type, true, brand) != -1) {
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand())) {
					inventory.remove(item);
					this.setOutput(brand + " " + type + " is removed successfully.");
				}
			}
		}
	}

	// Setters and Getters for the Quantity and Price for listed items if found
	public void setQuantity(String type, int quantity) {
		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setQuantity(quantity);
		}
	}

	public void setQuantity(String brand, String type, int quantity) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setQuantity(quantity);
		}
	}

	public void setPrice(String type, double price) {
		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setPrice(price);
		}
	}

	public void setPrice(String brand, String type, double price) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setPrice(price);
		}
	}
	public void setExpDate(String type, String expDate) {
		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setExpDate(expDate);
		}
	}

	public void setExpDate(String brand, String type, String expDate) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setExpDate(expDate);
		}
	}

	public int getQuantity(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			return inventory.get(index).getQuantity();
		}
		return -1;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return this.output;
	}

	public int getQuantity(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			return inventory.get(index).getQuantity();
		}
		return 0;
	}

	public double getPrice(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			return inventory.get(index).getPrice();
		}
		return Double.NaN;
	}

	public double getPrice(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			return inventory.get(index).getPrice();

		}
		return Double.NaN;
	}
	public String getExpDate(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			return inventory.get(index).getExpDate();
		}
		return null;
	}

	public String getexpDate(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			return inventory.get(index).getExpDate();

		}
		return null;
	}
	// Updating methods to change the Quantity and Price of listed items if found
	public void update(String type, int qtyIncrease) {
		if (findItem(type, false) != -1) {
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType())) {
					item.update(qtyIncrease);
					this.setOutput(type + " is successfully updated.");
				}
			}
		}
	}

	public void update(String brand, String type, int qtyIncrease) {
		if (findItem(type, false, brand) != -1) {
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand())) {
					item.update(qtyIncrease);
					this.setOutput(brand + " " + type + " is successfully updated.");
				}
			}
		}

	}

	public void update(String type, double adjustmentFactor) {
		if (findItem(type, false) != -1) {

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType())) {
					item.update(adjustmentFactor);
					this.setOutput(type + " is successfully updated.");
				}
			}

		}
	}

	public void update(String brand, String type, double adjustmentFactor) {
		if (findItem(type, false, brand) != -1) {

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand())) {
					item.update(adjustmentFactor);
					this.setOutput(brand + " " + type + " is successfully updated.");
				}
			}

		}
	}
	
	public void update(String type, String expDate) {
		if (findItem(type, false) != -1) {

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType())) {
					item.update(expDate);
					this.setOutput(type + " is successfully updated.");
				}
			}

		}
	}

	public void update(String brand, String type, String expDate) {
		if (findItem(type, false, brand) != -1) {

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand())) {
					item.update(expDate);
					this.setOutput(brand + " " + type + " is successfully updated.");
				}
			}

		}
	}

	// Methods to find the wanted Item in the list
	int findItem(String type, boolean warningIfFound) {
		int index = -1;
		int itemsFound = 0;
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (type.equals(item.getType())) {
				itemsFound++;
				index = i;
			}
		}
		if (itemsFound == 1) {
			this.setOutput(type + "'s quantity : " + inventory.get(index).getQuantity() + " price : "
			        + inventory.get(index).getPrice());
			return index;
		} else if (itemsFound == 0 && warningIfFound == false) {
			this.setOutput("cannot find " + type);
		} else if (itemsFound != 0 && warningIfFound == true) {
			this.setOutput("found more than one brand of " + type);
		} else if (itemsFound > 1) {
			this.setOutput("found more than one brand of " + type);
		}

		return -1;
	}

	protected int findItem(String type, boolean warningIfFound, String brand) {
		int index = -1;
		int itemsFound = 0;

		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand()) && item instanceof Brand) {
				index = i;
				itemsFound++;
			}
		}
		if (itemsFound == 0 && warningIfFound == false) {
			this.setOutput("cannot find " + brand + " " + type);
		}
		if (itemsFound != 0 && warningIfFound == true) {
			this.setOutput("found more than one brand of " + type);
		}

		if (itemsFound == 1) {
			this.setOutput(brand + " " + type + "'s quantity : " + inventory.get(index).getQuantity() + " price : "
			        + inventory.get(index).getPrice());

			return index;

		}
		return -1;

	}

	// Method to print complete information about the Items in the list and their
	// total value
	public String stockReport() {
		double total = 0.0;
		String sentence = "";
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (item instanceof Brand) {
				sentence += (((Brand) item).getBrand() + " " + ((Brand) item).getType() + " - in stock: "
				        + ((Brand) item).getQuantity() + ", price: $" + ((Brand) item).getPrice() +", Expiration date: " + ((Brand) item).getExpDate() + "\n");
				total += ((Brand) item).getQuantity() * ((Brand) item).getPrice();
			} else {
				sentence += (item.getType() + " - in stock: " + item.getQuantity() + ", price: $" + item.getPrice()+ ", Expiration date: " + item.getExpDate()
				        + "\n");
				total += (item.getQuantity() * item.getPrice());
			}
		}
		sentence += ("Total value: $" + total);
		return sentence;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
}