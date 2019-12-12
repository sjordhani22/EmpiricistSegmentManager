package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import empiricist.database.PlaylistsDAO;
import empiricist.database.SegmentsDAO;
import empiricist.http.AppendSegmentRequest;
import empiricist.http.AppendSegmentResponse;
import empiricist.model.Playlist;
import empiricist.model.Segment;

public class AppendSegmentHandler /*implements RequestHandler<AppendSegmentRequest, AppendSegmentResponse>*/ {
/*
	LambdaLogger logger;

	// To access S3 storage
	private AmazonS3 s3 = null;

	boolean appendSegment(String id, String playName) throws Exception {
		if (logger != null) {
			logger.log("in createPlaylist");
		}
		PlaylistsDAO dao = new PlaylistsDAO();
		SegmentsDAO segDAO = new SegmentsDAO();
		// check if present
		Playlist exist = dao.getPlaylist(playName); // gets from database
		Playlist play = new Playlist(playName);
		Segment seg = segDAO.getSegment(id);

		dao.updatePlaylist(playName, seg);

		if (exist == null) {
			return dao.addPlaylist(play);
		} else {
			return false;
		}
	}

	@Override
	public AppendSegmentResponse handleRequest(AppendSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("do you even CreatePlayListResponse bro");

		AppendSegmentResponse response;
		try {
			// byte[] encoded =
			// java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			logger.log("no encoding necessary thank you very much");
			if (req.system) {
				if (appendSegment(req.id, req.playName)) {
					response = new AppendSegmentResponse(req.playName);
					logger.log("sys=True, if");
				} else {
					response = new AppendSegmentResponse(req.playName, 422);
					logger.log("sys=True, else");
				}
			} else {
				// String contents = new String();
				// double value = Double.valueOf(contents);
				if (appendSegment(req.id, req.playName)) {
					response = new AppendSegmentResponse(req.playName);
					logger.log("sys=False, if");
					req.system = true;
				} else {
					response = new AppendSegmentResponse(req.playName, 422);
					logger.log("sys=False, else");
					req.system = true;
				}
			}
		} catch (Exception e) {
			response = new AppendSegmentResponse(
					"Unable to create playlist: " + req.playName + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
	*/
}
