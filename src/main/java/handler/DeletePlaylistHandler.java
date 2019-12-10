package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import empiricist.database.PlaylistsDAO;
import empiricist.http.CreatePlayListRequest;
import empiricist.http.CreatePlayListResponse;
import empiricist.http.DeletePlaylistRequest;
import empiricist.http.DeletePlaylistResponse;
import empiricist.model.Playlist;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest,DeletePlaylistResponse> {
	public LambdaLogger logger = null;

	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeletePlaylistResponse response = null;
		logger.log(req.toString());

		PlaylistsDAO dao = new PlaylistsDAO();

		// See how awkward it is to call delete with an object, when you only
		// have one part of its information?
		Playlist pl = new Playlist(req.name);
		try {
			if (dao.deletePlaylist(pl)) {
				response = new DeletePlaylistResponse(req.name, 200);
			} else {
				response = new DeletePlaylistResponse(req.name, 422, "Unable to delete Playlist.");
			}
		} catch (Exception e) {
			response = new DeletePlaylistResponse(req.name, 403, "Unable to delete Playlist: " + req.name + "(" + e.getMessage() + ")");
		}

		return response;
	}
}


