package midbase.data;

import midbase.domain.UserSettings;
import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.PersistableManager;

public class UserSettingsRepository {

	PersistableManager persistableManager = null;
	
	public UserSettingsRepository(PersistableManager persistableManager) {
		this.persistableManager = persistableManager;
	}
	
	public int save(UserSettings domain){
		try {			
			int id = this.persistableManager.save(domain);
			
			return id;
		} catch (FloggyException e) {
			e.printStackTrace();
			return -1;
		}     
	}
	
	public UserSettings get(){
		try {				
			UserSettings domain = new UserSettings();
			this.persistableManager.load(domain, 1);
			
			return domain;
		} catch (FloggyException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
