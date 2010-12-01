package midbase.domain;

import java.util.Date;


import net.sourceforge.floggy.persistence.Persistable;

public class UserSettings implements Persistable{

	private Date runDate;
	private String applicationLanguage;
	private byte[] validationData;
	
	public UserSettings(){
		
	}
	
	public UserSettings(Date runDate, String applicationLanguage){
		this.runDate = runDate;
		this.applicationLanguage = applicationLanguage;
	}
	
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public Date getRunDate() {
		return runDate;
	}
	
	public void setApplicationLanguage(String applicationLanguage) {
		this.applicationLanguage = applicationLanguage;
	}
	public String getApplicationLanguage() {
		return applicationLanguage;
	}

	public void setValidationData(byte[] validationData) {
		this.validationData = validationData;
	}

	public byte[] getValidationData() {
		return validationData;
	}
	
	
}
