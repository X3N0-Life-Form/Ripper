package work;

/**
 * Thrown by {@link Worker}s who can't do stuff for some reason.
 * @author X3N0-Life-Form
 *
 */
public class WorkerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2361611921240639865L;
	
	public WorkerException(String message) {
		super(message);
	}

}
