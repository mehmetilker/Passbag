package midbase.domain;

import net.sourceforge.floggy.persistence.Persistable;

public class Entry implements Persistable {

	private int Id;
	private String Name;
	private Category Category;	
	private Field[] fields;	
	private String note;
	
	
	public Entry()
	{
		
	}
	
	public int getId(){
		return this.Id;
	}	
	
	public void setId(int id){
		this.Id = id;
	}
		
	public String getName() {
		return this.Name;
	}
	
	public void setName(String name){
		this.Name = name;
	}
	
	public Category getCategory(){
		return this.Category;
	}
	
	public void setCategory(Category category){
		this.Category = category;
	}
	

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}
		
	

	
}
