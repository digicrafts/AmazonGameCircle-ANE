package digicrafts.extensions;

import android.app.Activity;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.amazon.ags.api.*;

import com.amazon.ags.api.achievements.AchievementsClient;
import com.amazon.ags.api.achievements.UpdateProgressResponse;
import com.amazon.ags.api.leaderboards.LeaderboardsClient;
import com.amazon.ags.api.leaderboards.SubmitScoreResponse;
import com.amazon.ags.api.overlay.PopUpLocation;
import digicrafts.extensions.function.*;

public class AmazonGameCircleContext extends FREContext {

	private static final String TAG = AmazonGameCircleContext.class.getName();

    private AmazonGamesClient _agsClient;
	
	@Override
	public void dispose() {

        if (_agsClient != null) {
            _agsClient.release();
        }
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

        functionMap.put("isSupported", new AmazonGameCircleSupportedFunction());
		functionMap.put("initialize", new AmazonGameCircleInitializeFunction());
        functionMap.put("pause", new AmazonGameCirclePauseFunction());
        functionMap.put("resume", new AmazonGameCircleResumeFunction());
	    functionMap.put("submitScore", new AmazonGameCircleSubmitScoreFunction());
        functionMap.put("showLeaderboard", new AmazonGameCircleShowLeaderboardFunction());

        functionMap.put("updateAchievement", new AmazonGameCircleUpdateAchievementFunction());
        functionMap.put("showAchievements", new AmazonGameCircleShowAchievementsFunction());


	    
		return functionMap;
	}

// Callback Methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////


    AmazonGamesCallback createCallback = new AmazonGamesCallback() {
        @Override
        public void onServiceNotReady(AmazonGamesStatus status) {
            //unable to use service
//            log("onServiceNotReady");
            dispatchStatusEventAsync("onServiceNotReady", "event");
        }
        @Override
        public void onServiceReady(AmazonGamesClient amazonGamesClient) {
            _agsClient = amazonGamesClient;
            //ready to use GameCircle
//            log("onServiceReady");
            dispatchStatusEventAsync("onServiceReady","ok");
        }
    };

// Public Methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param message
     */
    public void log(String message)
    {
        Log.d(TAG, message);
        dispatchStatusEventAsync("LOGGING", message);
    }

    /**
     *
     * @param act
     */
	public void initialize(Activity act) {

        //list of features your game uses (in this example, achievements and leaderboards)
        EnumSet<AmazonGamesFeature> myGameFeatures = EnumSet.of(
                AmazonGamesFeature.Achievements, AmazonGamesFeature.Leaderboards);

        try{
            AmazonGamesClient.initialize(act, createCallback, myGameFeatures);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


    }

    /**
     *
     */
    public void pause() {

        if (_agsClient != null) {
            _agsClient.release();
        }

    }

    /**
     *
     * @param act
     */
    public void resume(Activity act) {

        if(_agsClient==null){
            initialize(act);
        }
    }


    /**
     *
     * @param leaderboardId
     * @param score
     */
    public void submitScore(String leaderboardId, int score) {

        if(_agsClient==null) {

        } else {

            long s = 0+score;
            String id = ""+leaderboardId.trim();

            LeaderboardsClient lbClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<SubmitScoreResponse> handle = lbClient.submitScore(id,s);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<SubmitScoreResponse>() {
                @Override
                public void onComplete(SubmitScoreResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onSubmitScoreError",result.toString());
                    } else {

                        // Continue game flow.
                        dispatchStatusEventAsync("onSubmitScoreComplete", result.toString());
                    }
                }
            });
        }
    }

    /**
     * Show the leaderboard overlay
     * @param leaderboardId Leaderboard ID
     */
    public void showLeaderboard(String leaderboardId) {

        if(_agsClient==null) {

        } else {
            LeaderboardsClient lbClient = _agsClient.getLeaderboardsClient();

            if(leaderboardId==null)
                lbClient.showLeaderboardsOverlay();
            else
                lbClient.showLeaderboardOverlay(leaderboardId);
        }
    }

    /**
     *
     * @param achievementId
     * @param progress
     */
    public void updateAchievement(String achievementId, float progress) {

        if(_agsClient==null) {

        } else {

            AchievementsClient acClient = _agsClient.getAchievementsClient();
            AGResponseHandle<UpdateProgressResponse> handle = acClient.updateProgress(achievementId, progress);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<UpdateProgressResponse>() {

                @Override
                public void onComplete(UpdateProgressResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onUpdateAchievementError",result.toString());
                    } else {
                        // Continue game flow.
                        dispatchStatusEventAsync("onUpdateAchievementComplete",result.toString());
                    }
                }
            });

        }
    }


    /**
     *
     */
    public void showAchievements() {

        if(_agsClient==null) {

        } else {
            AchievementsClient acClient = _agsClient.getAchievementsClient();
            acClient.showAchievementsOverlay();
        }
    }

    /**
     *
     * @param location
     */
    public void setPopUpLocation(String location) {


        if(_agsClient==null) {

        } else {
            if (location.equals("top_center"))
                _agsClient.setPopUpLocation(PopUpLocation.TOP_CENTER);
            else
                _agsClient.setPopUpLocation(PopUpLocation.BOTTOM_CENTER);
        }
    }

}
