package midbase;
import java.util.Stack;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;


public class DisplayManager {

	private Displayable current;
	private Display display;
	private Stack	stack;
	
	/**
	 * Creates a display manager associated to
	 * the specified display.
	 * 
	 * @param display target display.
	 */
	public DisplayManager(Display display) {
		if (display == null) {
			throw new IllegalArgumentException("Display can not be null.");
		}
		this.setDisplay(display);
		this.stack	 = new Stack();
	}
	
	/**
	 * Sets the specified displayable as the current
	 * screen.
	 * 
	 * @param next screen to show.
	 */
	public void next(Displayable next) {
		if (this.current == next) {
			return;
		}
		
		if (this.current != null) {
			this.stack.push(this.current);
		}
		this.current = next;
		this.getDisplay().setCurrent(this.current);
	}
	
	/**
	 * Goes back to the last screen.
	 */
	public void back() {
		if (this.stack.size() > 0x00) {
			this.current = (Displayable) this.stack.pop();
			this.getDisplay().setCurrent(this.current);
		}
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Display getDisplay() {
		return display;
	}
	
}
