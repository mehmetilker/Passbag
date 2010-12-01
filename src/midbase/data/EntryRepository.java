package midbase.data;

import java.util.Vector;

import midbase.data.filter.CategoryFilter;
import midbase.domain.Category;
import midbase.domain.Entry;
import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.ObjectSet;
import net.sourceforge.floggy.persistence.Persistable;
import net.sourceforge.floggy.persistence.PersistableManager;

public class EntryRepository {

	PersistableManager persistableManager = null;
	
	public EntryRepository(PersistableManager persistableManager){
		this.persistableManager = persistableManager;
	} 
		
	public int save(Persistable domain){
		try {			
			int id = this.persistableManager.save(domain);
			
			return id;
		} catch (FloggyException e) {
			e.printStackTrace();
			return -1;
		}     
	}
	
	public Persistable get(Persistable domain, int id){
		try {				
			this.persistableManager.load(domain, id);
			
			return domain;
		} catch (FloggyException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(Persistable domain){
		try {				
			this.persistableManager.delete(domain);			
		} catch (FloggyException e) {
			e.printStackTrace();			
		}
	}
	
	public int getDomainId(Persistable domain){
		
		return this.persistableManager.getId(domain);	
	}
	
	public Vector getEntriesByCategory(Class domain, int categoryId){		
		Persistable entry = new Entry();
		Vector dataList = new Vector();

		Entry[] entries = null;///
		
		ObjectSet persons;
		try {
			persons = persistableManager.find(domain, new CategoryFilter(categoryId), null);
			entries = new Entry[persons.size()];/////
			for (int i = 0, size=persons.size(); i < size; i++) {				
				persons.get(i, entry);	
				entries[i] = (Entry)entry; ////
				Entry e = new Entry();
				e.setId(this.persistableManager.getId(entry));
				e.setName(((Entry)entry).getName());
				e.setNote(((Entry)entry).getNote());
				e.setFields(((Entry)entry).getFields());
				e.setCategory(new Category(((Entry)entry).getCategory().GetId()));
				dataList.addElement(e);
				e = null;
				//dataList.addElement(entry);				
			}
		} catch (FloggyException e) {
			e.printStackTrace();
		}

		//return entries;
		return dataList;
	}
	
	public Vector getAll(Class domain){		
		Persistable entry = new Entry();
		Vector dataList = new Vector();

		ObjectSet persons;
		try {
			persons = persistableManager.find(domain, null, null);
			for (int i = 0; i < persons.size(); i++) {
				/*
				 * The same instance is used to load the data. This avoid the
				 * creation of persons.size()-1 objects.
				 */
				persons.get(i, entry);
								
				// Don't use the person reference outside the loop, only its
				// data.
				// BAD
				dataList.addElement(entry);
				// GOOD
				// list.add(person.getName());
			}
		} catch (FloggyException e) {			
			e.printStackTrace();
		}

		return dataList;
	}
	
}
