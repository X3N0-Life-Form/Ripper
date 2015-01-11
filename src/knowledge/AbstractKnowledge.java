package knowledge;

/**
 * Though Knowledge can differ in its form, all must bear certain attributes, such as a name.
 * @author X3N0-Life-Form
 *
 */
public abstract class AbstractKnowledge implements Knowledge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1863633886140207916L;
	protected String name = "nameless";

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getSimpleName() {
		return name.substring(name.lastIndexOf('/') + 1);
	}
}
