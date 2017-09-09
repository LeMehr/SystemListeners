import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import SystemHookController;
import KeyCombination;

public abstract class SystemKeyCombinationListener extends SystemHookController implements NativeKeyListener {

    public SystemKeyCombinationListener() {
	super();
	this.addKeyCombinations();
	GlobalScreen.addNativeKeyListener(this);
    }

    private final List<Integer> keysPressed = new ArrayList<>();
    private final List<KeyCombination> combos = new ArrayList<>();

    protected abstract void addKeyCombinations();

    protected void addKeyCombination(KeyCombination kc) {
	this.combos.add(kc);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent arg0) {
	if (!this.keysPressed.contains(arg0.getKeyCode())) {
	    this.keysPressed.add(arg0.getKeyCode());
	    this.checkForKeys();
	}
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent arg0) {
	if (this.keysPressed.contains(arg0.getKeyCode()))
	    this.keysPressed.remove(this.keysPressed.indexOf(arg0.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent arg0) {}

    private void checkForKeys() {
	for (KeyCombination kc : this.combos) 
	    if (kc.isOrderImportant()) {
		if (this.isSubsetOrdered(this.keysPressed, kc.getKeys()))
		    kc.getAction().actionPerformed(new ActionEvent(this, 0, null));
	    } else {
		if (this.isSubset(this.keysPressed, kc.getKeys()))
		    kc.getAction().actionPerformed(new ActionEvent(this, 0, null));
	    }
    }

    private boolean isSubset(List<Integer> list, int[] subset) {
	for (int i : subset)
	    if (!list.contains(i))
		return false;
	return true;
    }

    private boolean isSubsetOrdered(List<Integer> list, int[] subset) {
	int pointer = -1;
	for (int i = 0; i < subset.length; i++) {
	    int index = list.indexOf(subset[i]);
	    if (index <= pointer)
		return false;
	    pointer = index;
	}
	return true;
    }

}