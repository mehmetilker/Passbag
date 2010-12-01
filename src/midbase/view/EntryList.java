/**
 * 
 */
package midbase.view;

import java.util.Vector;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.List;

import midbase.domain.Entry;

/**
 * @author milk
 *
 */
public class EntryList extends List {

	private Entry[] entries;
	private Vector entryListVector;
	
	/**
	 * @param Entry List to create list
	 */
	public EntryList(Entry[] list) {
		super("Kayýtlar", Choice.IMPLICIT);
		this.entries = list;
		
		for(int i=0, length = list.length; i < length; i++)
			this.append(list[i].getName(), null);		
	}

	/**
	 * @param Entry List to create list
	 */
	public EntryList(Vector list) {
		super("Kayýtlar", Choice.IMPLICIT);

		this.entryListVector = list;
		
		for(int i=0, size = list.size(); i < size; i++)
			this.append(((Entry)list.elementAt(i)).getName(), null);		
	}
	
	public Entry getSelectedEntry(int listIndex){
		return ((Entry)this.entryListVector.elementAt(listIndex));
	}
	
	public Entry getSelectedEntryii(int listIndex){
		return ((Entry)this.entries[listIndex]);
	}
	
	public void ChangeList(Vector list){
		this.deleteAll();
		
		this.entryListVector = list;
		
		for(int i=0, size = list.size(); i < size; i++)
			this.append(((Entry)list.elementAt(i)).getName(), null);
	}
	
	public void ChangeList(Entry[] list){
		this.deleteAll();
		
		this.entries = list;
		
		for(int i=0, length = list.length; i < length; i++)
			this.append(list[i].getName(), null);		
	}
	

}
