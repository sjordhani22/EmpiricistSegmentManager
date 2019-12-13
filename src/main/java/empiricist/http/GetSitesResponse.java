package empiricist.http;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Playlist;
import empiricist.model.RemoteSite;

public class GetSitesResponse {
	
	public final List<RemoteSite> list;
	public final int stats;
	public String error;
	
	public GetSitesResponse (List<RemoteSite> list, int code) {
		this.list = list;
		this.stats = code;
		this.error = "";
	}
	
	public GetSitesResponse (int code, String errorMessage) {
		this.list = new ArrayList<RemoteSite>();
		this.stats = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyPlaylists"; }
		return "AllPlaylists(" + list.size() + ")";
	}
}


