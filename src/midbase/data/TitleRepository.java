package midbase.data;

import midbase.domain.Title;

public class TitleRepository {

	Title[] titles;
	
	// TODO PersistableManager alacak sekilde (EntryRepo daki gibi) degistirlecek ve alttakitanimlar dinamik olacak
	
	public TitleRepository(){
		this.titles = new Title[4];
		
		titles[0] = new Title("User Name");
		titles[1] = new Title("Password");
		titles[2] = new Title("Parole");
		titles[3] = new Title("Customer No");
	}
	
	public Title[] GetFieldTitles(){
						
		return this.titles;
	}
	
	public Title getFieldTitle(int id){
		return this.titles[id];
	}
	
}
