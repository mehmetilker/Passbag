package midbase.data.filter;

import midbase.domain.Entry;
import net.sourceforge.floggy.persistence.Persistable;

public class CategoryFilter implements net.sourceforge.floggy.persistence.Filter {

	int categoryId;
	
	public CategoryFilter(int categoryId){
		this.categoryId = categoryId;
	}
	
    public boolean matches(Persistable persistable) {
            Entry p = (Entry) persistable;
            
            return p.getCategory().GetId() == this.categoryId;
    }
    
}