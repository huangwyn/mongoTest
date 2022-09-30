package com.quintrix.mongoTest;

import com.quintrix.mongoTest.model.GroceryItem;
import com.quintrix.mongoTest.utils.ShowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.quintrix.mongoTest.repository.ItemRepository;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class MongoTestApplication implements CommandLineRunner {

	@Autowired
	ItemRepository groceryItemRepo;

	@Autowired
	ShowTime showTime;

	public static void main(String[] args) {
		SpringApplication.run(MongoTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");

		createGroceryItems();

		System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");

		showAllGroceryItems();

		System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");

		getGroceryItemByName("Whole Wheat Biscuit");

		System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");

		getItemsByCategory("millets");

		System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");

		updateCategoryName("snacks", "munchies");

		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");

		deleteGroceryItem("Kodo Millet");

		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");

		findCountOfGroceryItems();

		System.out.println("\n-------------------THANK YOU---------------------------");

	}

	// CRUD ops
	//CREATE
	void createGroceryItems() {
		System.out.println("Data creation started...");
		groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
		groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
		groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
		groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
		groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
		System.out.println("Data creation complete...");
	}

	//READ ops
	// 1. Show all the data
	public void showAllGroceryItems() {
		groceryItemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
	}

	// 2. Get item by name
	public void getGroceryItemByName(String name) {
		System.out.println("Getting item by name: " + name);
		GroceryItem item = groceryItemRepo.findItemByName(name);
		System.out.println(getItemDetails(item));
	}

	// 3. Get name and quantity of a all items of a particular category
	public void getItemsByCategory(String category) {
		System.out.println("Getting items for the category " + category);
		List<GroceryItem> list = groceryItemRepo.findAll(category);

		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
	}

	// 4. Get count of documents in the collection
	public void findCountOfGroceryItems() {
		long count = groceryItemRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}

	// Print details in readable form
	public String getItemDetails(GroceryItem item) {

		System.out.println("Item Name: " + item.getName() +
						", \nQuantity: " + item.getQuantity() +
						", \nItem Category: " + item.getCategory()
		);

		return "";
	}

	//UPDATE
	public void updateCategoryName(String category, String newCategory) {
		List<GroceryItem> list = groceryItemRepo.findAll(category);

		list.forEach(item -> {
			item.setCategory(newCategory);
		});

		//save it all
		List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);

		//out an updated statement
		if(itemsUpdated != null){
			System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
		}

	}

	//DELETE
	public void deleteGroceryItem(String id) {
		groceryItemRepo.deleteById(id);
		System.out.println("Item with ID " + id + " has been yeeted...");
	}
}
