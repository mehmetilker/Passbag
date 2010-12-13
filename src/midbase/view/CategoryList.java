/**
 * 
 */
package midbase.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

import midbase.domain.Category;
import midbase.localization.L10nConstants;
import midbase.localization.L10nResources;

/**
 * @author milk
 *
 */
public class CategoryList extends List {
	
	//private L10nResources resources;
	
	/**
	 * @param Category List
	 */
	public CategoryList(Category[] list, L10nResources resources) {		
		super(null, Choice.IMPLICIT);
		//this.resources = resources;
		String title = resources.getString(L10nConstants.keys.APP_NAME) + " " + resources.getString(L10nConstants.keys.CATEGORIES);
		this.setTitle(title);
	
		for(int i=0; i < list.length; i++)
			this.append(list[i].GetName(), getImageWarning(list[i].GetId()));
	}
	
	
	public Image getImageWarning(int Id) {       
        try {
            return  Image.createImage("/cat-" + Id + ".png");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	

}
