package midbase.domain;

import net.sourceforge.floggy.persistence.Persistable;

public class Title implements Persistable {
	
	private String value;
	
	public Title(){
		
	}

	public Title(String value){
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	
	
	
}
