import Listeners.SystemKeyCombinationListener;
import IFTTT;
import KeyCombination;
import Keyboard;

public class Main {

    public static void main(String[] args) {
	
	//put your key here
	IFTTT.IFTTT_KEY = "YOUR KEY HERE";
	
	new SystemKeyCombinationListener() {
	    @Override
	    protected void addKeyCombinations() {
		
		//triggers IFTTT event when keys [ ctrl, alt, b ] are pressed in that order
		this.addKeyCombination(new KeyCombination(a -> {
		    IFTTT.triggerEvent("event_name", "value1", "value2", "value3");
		}, true, Keyboard.CTRL, Keyboard.ALT, Keyboard.B));
		
		/*
		
		//triggers IFTTT event when keys [ ctrl, shift, t ] are pressed in any order
		this.addKeyCombination(new KeyCombination(a -> {
		    IFTTT.triggerEvent("event_name_2", "value1", "value2", "value3");
		}, false, Keyboard.CTRL, Keyboard.SHIFT, Keyboard.T));
		
		*/
		
		/*
		 * you can use as many or as few keys as you'd like
		 */
		
		/*
		
		//1 key
		this.addKeyCombination(new KeyCombination(a -> {
		    IFTTT.triggerEvent("event_name_2", "value1", "value2", "value3");
		}, false, Keyboard.L));
		
		//5 keys
		this.addKeyCombination(new KeyCombination(a -> {
		    IFTTT.triggerEvent("event_name_2", "value1", "value2", "value3");
		}, false, Keyboard.CTRL, Keyboard.ALT, Keyboard.SHIFT, Keyboard.SPACE, Keyboard.O));
		
		*/
		
	    }
	};
    }

}