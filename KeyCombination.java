import java.awt.event.ActionListener;

public class KeyCombination {

    private final ActionListener action;
    private final int[] keys;
    private final boolean orderImportant;
    
    public KeyCombination(ActionListener action, boolean orderImportant, int... keys) {
	this.action = action;
	this.keys = keys; 
	this.orderImportant = orderImportant;
    }

    public boolean isOrderImportant() {
        return orderImportant;
    }

    public ActionListener getAction() {
        return action;
    }

    public int[] getKeys() {
        return keys;
    }

}
