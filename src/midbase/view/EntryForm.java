package midbase.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.TextField;

import midbase.component.CustomChoiceGroup;
import midbase.component.CustomTextField;
import midbase.domain.Entry;
import midbase.domain.Field;
import midbase.domain.Title;
import midbase.localization.L10nConstants;
import midbase.localization.L10nResources;
import midbase.security.ChriptoHelper;

public class EntryForm extends Form {
	
	private L10nResources resources;
	
	private Entry entry;
	private Title[] fieldTitles;
	
	public int CategoryId;
	public TextField AccountNameTextField = null;	
	public TextField NoteTextField = null;
           
	private ChriptoHelper chriptoHelper;
	private byte[] cipher;
	
    public EntryForm(
    		int categoryId, Title[] fieldTitles, 
    		ChriptoHelper chriptoHelper, byte[] chipher,
    		L10nResources resources){
    	super("Account"); 
    	this.setTitle(resources.getString(L10nConstants.keys.ACCOUNT_NAME));
    	
    	this.fieldTitles = fieldTitles;
    	this.chriptoHelper = chriptoHelper;
    	this.cipher = chipher;
    	this.resources = resources;
    	
    	AccountNameTextField = new TextField(resources.getString(L10nConstants.keys.ACCOUNT_NAME), null, 32, TextField.ANY);
    	NoteTextField = new TextField(resources.getString(L10nConstants.keys.NOTE), null, 64, TextField.ANY);
    	
    	prepareForm(categoryId);
    }
    
    public EntryForm(
    		int categoryId, Entry entry, Title[] fieldTitles,
    		ChriptoHelper chriptoHelper, byte[] chipher,
    		L10nResources resources){
    	super("Account");   
    	this.setTitle(resources.getString(L10nConstants.keys.ACCOUNT_NAME));
    	
    	this.entry = entry;
    	this.fieldTitles = fieldTitles;    	
    	this.chriptoHelper = chriptoHelper;
    	this.cipher = chipher;
    	this.resources = resources;
    	
    	AccountNameTextField = new TextField(resources.getString(L10nConstants.keys.ACCOUNT_NAME), null, 32, TextField.ANY);
    	NoteTextField = new TextField(resources.getString(L10nConstants.keys.NOTE), null, 64, TextField.ANY);
    	   	
    	prepareForm(categoryId); 
    	bindData(entry);
    }
    
    
    private void prepareForm(int categoryId){
    	this.CategoryId = categoryId;
    	
    	this.append(AccountNameTextField);    	
    	   	  	
    	NoteTextField.setLayout(ImageItem.LAYOUT_DEFAULT);
	    NoteTextField.setPreferredSize(20, -1);	  
	    this.append(NoteTextField); 
    }
    
    private void bindData(Entry form){
		AccountNameTextField.setString(form.getName());
		this.CategoryId = form.getCategory().GetId();
		NoteTextField.setString(form.getNote());
		this.append(new Spacer(16, 1));
		Field[] fields = form.getFields();
		
		for (int i=0; i < fields.length; i++){
			if (fields[i] == null)
				return;
				
			String titleValue = fields[i].getTitle().getValue();
			int selectedIndex = -1;
			CustomChoiceGroup fieldChoice = new CustomChoiceGroup(this.resources.getString(L10nConstants.keys.FIELD_NAME), Choice.POPUP);
			for (int n=0; n < this.fieldTitles.length; n++){
				int choiceIndex = fieldChoice.append(this.fieldTitles[n].getValue(), null);
				if (titleValue.equals(this.fieldTitles[n].getValue()))
					selectedIndex = choiceIndex;
			}
			if (selectedIndex != -1){
				fieldChoice.setSelectedIndex(selectedIndex, true);
				selectedIndex = -1;
			}
			this.append(fieldChoice);
			
			//CustomTextField textField = new CustomTextField("Alan De�eri", fields[i].getValue(), 32, TextField.ANY);			
			//this.append(textField);
			
			//enc test
			byte[] encryptedValue = fields[i].getValue();
			String decryptedValue = this.chriptoHelper.Dec(encryptedValue, this.cipher);
			CustomTextField encTextField = new CustomTextField(this.resources.getString(L10nConstants.keys.FIELD_VALUE), decryptedValue, 32, TextField.ANY);			
			this.append(encTextField);
			this.append(new Spacer(16, 1));
			//
		}		
    }

    
    public Entry getOriginalEntry(){
    	return this.entry;
    }
    
    public void addField(){    
    	this.append(new Spacer(16, 1));
    	CustomChoiceGroup fieldChoice = new CustomChoiceGroup("Field Options", Choice.POPUP);
    	for (int n=0; n < this.fieldTitles.length; n++){
			fieldChoice.append(this.fieldTitles[n].getValue(), null);			
		}
    	this.append(fieldChoice);
    	
    	CustomTextField textField = new CustomTextField("Field Value", null, 32, TextField.ANY);
    	this.append(textField);
    }
	
}
