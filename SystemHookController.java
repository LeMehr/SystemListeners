import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public abstract class SystemHookController {

    private static boolean error = false;
    private static boolean init = false;

    public SystemHookController() {
	SystemHookController.init();
    }

    protected final static boolean init() {
	if (SystemHookController.error) {
	    System.out.println("SystemHookController init: ERROR");
	    return false;
	} else if (SystemHookController.init) {
	    System.out.println("SystemHookController init: GOOD");
	    return true;
	} else
	    try {
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);
		GlobalScreen.registerNativeHook();
		SystemHookController.init = true;
		return SystemHookController.init();
	    } catch (NativeHookException e) {
		e.printStackTrace();
		SystemHookController.error = true;
		return SystemHookController.init();
	    }
    }

}
 