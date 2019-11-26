package empiricist.http;

public class CreatePlayListRequest {
	 String playlistName;
	 
	 
	 public CreatePlayListRequest(String playlistName) {
		 this.playlistName = playlistName;
	 }
	 public String getPlaylistName() {return playlistName;}
	 
}
