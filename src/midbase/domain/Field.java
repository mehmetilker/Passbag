package midbase.domain;

import net.sourceforge.floggy.persistence.Persistable;

public class Field implements Persistable {

	private Title title;
	//private String value;
	private byte[] value;
	
	public Field(){
		
	}
	
	/*public Field(Title title, String value){
		this.title = title;
		this.value = value;
	}*/
	
	public Field(Title title, byte[] value){
		this.title = title;
		this.value = value;
	}
	
	public void setTitle(Title title) {
		this.title = title;
	}

	public Title getTitle() {
		return title;
	}
		
/*	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}*/

	public void setValue(byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}
	
	
}
