package knowledge;

/**
 * Many will form One.
 * @author X3N0-Life-Form
 *
 */
public class Many extends AbstractKnowledge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9038983679741564956L;
	private String url;

	public Many(String url) {
		this.setUrl(url);
	}
	
	/**
	 * Many doesn't have a file extension. It is empty.
	 */
	@Override
	public String getFileExtension() {
		return "";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
