package midbase.domain;

import net.sourceforge.floggy.persistence.Persistable;

public class Category implements Persistable {

	private int Id; 
	private String Name;
	
	public Category(){
		
	}
	
	public Category(int id){
		this.Id = id;
	}
	
	public Category(int id, String name){
		this.Id = id;
		this.Name = name;
	}
	
	public int GetId(){
		return this.Id;
	}	
	
	public void SetId(int id)
	{
		this.Id = id;
	}
	
	public String GetName()
	{
		return this.Name;
	}
	
	public void SetName(String name)
	{
		this.Name = name;
	}
	
}
