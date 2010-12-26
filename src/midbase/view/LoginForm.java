/**
 * 
 */
package midbase.view;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import midbase.localization.L10nConstants;
import midbase.localization.L10nResources;

/**
 * @author milk
 *
 */
public class LoginForm extends Form {

	private L10nResources resources;
	
	public StringItem explanationStringItem;
	public TextField passwordTextField;
	
	public StringItem explanation2StringItem;
	public TextField password2TextField;
	
	public boolean firstTime;
	
	
	/**
	 * @param arg0
	 */
	public LoginForm(L10nResources resources, boolean firstTimeForm) {
		super(resources.getString(L10nConstants.keys.LOGIN_FORM_TITLE));
		
		this.resources = resources;
		this.firstTime = firstTimeForm;
		
		if (firstTimeForm)				
			initializeFirstTimeForm();
		else
			initializeForm();
	}

	private void initializeFirstTimeForm() {
		this.explanationStringItem = new StringItem(resources.getString(L10nConstants.keys.FIRST_TIME_PASSWORD), null);
		this.passwordTextField = new TextField(resources.getString(L10nConstants.keys.PASSWORD), null, 16, TextField.PASSWORD);
		
		this.explanation2StringItem = new StringItem(resources.getString(L10nConstants.keys.REENTER_YOUR_PASSWORD), null);
		this.password2TextField = new TextField(resources.getString(L10nConstants.keys.REPEAT_PASSWORD), null, 16, TextField.PASSWORD);
				
		this.append(new Spacer(16, 1));
		this.append(this.explanationStringItem);
		this.append(this.passwordTextField);
		this.append(new Spacer(16, 1));
		//this.append(this.explanation2StringItem);
		this.append(this.password2TextField);
	}

	private void initializeForm() {
		this.explanationStringItem = new StringItem(resources.getString(L10nConstants.keys.LOGIN_PASSWORD), resources.getString(L10nConstants.keys.ENTER_YOUR_PASSWORD));
		this.passwordTextField = new TextField(resources.getString(L10nConstants.keys.PASSWORD), null, 16, TextField.PASSWORD);
		
		this.append(this.explanationStringItem);
		this.append(this.passwordTextField);		
	}
	
}
