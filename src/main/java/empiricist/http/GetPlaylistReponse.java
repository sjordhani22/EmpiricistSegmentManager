package empiricist.http;
import java.util.ArrayList;
import java.util.List;

import empiricist.model.Playlist;

public class GetPlaylistReponse {
	
	public final List<Playlist> list;
	public final int stats;
	public String error;
	
	public GetPlaylistReponse (List<Playlist> list, int code) {
		this.list = list;
		this.stats = code;
		this.error = "";
	}
	
	public GetPlaylistReponse (int code, String errorMessage) {
		this.list = new ArrayList<Playlist>();
		this.stats = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyPlaylists"; }
		return "AllPlaylists(" + list.size() + ")";
		
	}
}


