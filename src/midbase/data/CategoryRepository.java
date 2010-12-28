package midbase.data;

import midbase.domain.Category;

public class CategoryRepository {

	Category[] categories;
	
	// PersistableManager alacak sekilde (EntryRepo daki gibi) degistirlecek ve alttakitanimlar dinamik olacak
	// Category domain icin yeni bir tip tanimlanip ilgili icon lar onun uzerinden yuklenecek.
	
	public CategoryRepository(){
		this.categories = new Category[4];
		
		categories[0] = new Category(1, "Operating System");
		categories[1] = new Category(2, "E-Mail");
		categories[2] = new Category(3, "Bank");
		categories[3] = new Category(4, "Other");		
	}
	
	public Category[] GetCategories(){
		
		return this.categories;
	}
	
}
