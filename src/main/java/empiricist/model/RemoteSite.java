package empiricist.model;

public class RemoteSite {
	
	private static String Url;

	
	public RemoteSite(String URL) {
		this.Url = URL;
	}
	
	public static  String getUrl() {
		return Url;
	}
	
	
}
