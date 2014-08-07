package digicrafts.extensions;

import android.app.Activity;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

import java.util.*;

import com.amazon.ags.api.*;

import com.amazon.ags.api.achievements.*;
import com.amazon.ags.api.leaderboards.*;
import com.amazon.ags.api.overlay.PopUpLocation;
import com.amazon.ags.api.player.Player;
import com.amazon.ags.api.player.PlayerClient;
import com.amazon.ags.api.player.RequestFriendIdsResponse;
import com.amazon.ags.api.player.RequestPlayerResponse;
import com.amazon.ags.constants.LeaderboardFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AmazonGameCircleContext extends FREContext {

	private static final String TAG = AmazonGameCircleContext.class.getName();

    private AmazonGamesClient _agsClient;
    private List<AmazonGameCircleFunction<?>> methods;

	@Override
	public void dispose() {
        if (_agsClient != null) {
            _agsClient.release();
        }
	}

    /**
     *
     */
    public AmazonGameCircleContext() {

        super();

        // create list to hold the functions
        methods = new ArrayList<AmazonGameCircleFunction<?>>();

        try {

            //
            methods.add(new AmazonGameCircleFunction<Void>("isSupported") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {

                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("initialize") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    initialize(context.getActivity());
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("showGameCircle") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    showGameCircle();
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("showSignInPage") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    showSignInPage();
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("pause") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    pause();
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("resume") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    resume(context.getActivity());
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("shutdown") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    shutdown();
                    return null;
                }
            });

            // player

            methods.add(new AmazonGameCircleFunction<Boolean>("isSignedIn") {
                public Boolean onCall(AmazonGameCircleContext context, Object[] args) {
                    return isSignedIn();
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getLocalPlayer") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    getLocalPlayer();
                    return null;
                }
            });


            // leaderboard

            //
            methods.add(new AmazonGameCircleFunction<Void>("getLeaderboards") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    getLeaderboards();
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getPercentileRanks",String.class,String.class,String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    String filterType=(String) args[1];
                    getPercentileRanks((String) args[0], mapFilterType(filterType));
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getPercentileRanksForPlayer",String.class,String.class,String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    String filterType=(String) args[2];
                    getPercentileRanksForPlayer((String) args[0],(String) args[1],mapFilterType(filterType));
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getLocalPlayerScore",String.class,String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    String filterType=(String) args[1];
                    getLocalPlayerScore((String) args[0], mapFilterType(filterType));
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getScoreForPlayer",String.class,String.class,String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    String filterType=(String) args[2];
                    getScoreForPlayer((String) args[0],(String) args[1],mapFilterType(filterType));
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getScores",String.class,String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    String filterType=(String) args[1];
                    getScores((String) args[0],mapFilterType(filterType));
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("submitScore",String.class, Integer.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    submitScore((String) args[0],(Integer) args[1]);
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("showLeaderboard",String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    showLeaderboard((String) args[0]);
                    return null;
                }
            });

            // Achievement

            methods.add(new AmazonGameCircleFunction<Void>("updateAchievement",String.class,Double.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    updateAchievement((String) args[0],((Double) args[1]).floatValue());
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getAchievement",String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    getAchievement((String) args[0]);
                    return null;
                }
            });

            //

            //
            methods.add(new AmazonGameCircleFunction<Void>("getAchievements") {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    getAchievements();
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getAchievementForPlayer",String.class,String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    getAchievementForPlayer((String) args[0],(String) args[1]);
                    return null;
                }
            });

            //
            methods.add(new AmazonGameCircleFunction<Void>("getAchievementsForPlayer",String.class) {
                public Void onCall(AmazonGameCircleContext context, Object[] args) {
                    getAchievementsForPlayer((String) args[0]);
                    return null;
                }
            });


        } catch (Exception e) {

            e.printStackTrace();

        }
    }

	@Override
	public Map<String, FREFunction> getFunctions() {
		
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

        // add functions to map
        for (AmazonGameCircleFunction<?> func : methods)
            functionMap.put(func.getName(), func);
	    
		return functionMap;
	}

// Callback Methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    AmazonGamesCallback createCallback = new AmazonGamesCallback() {
        @Override
        public void onServiceNotReady(AmazonGamesStatus status) {
            //unable to use service
            dispatchStatusEventAsync("onServiceNotReady", "event");
        }
        @Override
        public void onServiceReady(AmazonGamesClient amazonGamesClient) {
            _agsClient = amazonGamesClient;
            //ready to use GameCircle
            dispatchStatusEventAsync("onServiceReady","ok");
        }
    };

// Helper Methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     *
     * @param name
     * @return
     */
    private LeaderboardFilter mapFilterType(String name) {
        LeaderboardFilter type;

        if(name.equals("FRIENDS_ALL_TIME"))
            return LeaderboardFilter.FRIENDS_ALL_TIME;
        else if(name.equals("GLOBAL_DAY"))
            return LeaderboardFilter.GLOBAL_DAY;
        else if(name.equals("GLOBAL_WEEK"))
            return LeaderboardFilter.GLOBAL_WEEK;
        else
            return LeaderboardFilter.GLOBAL_ALL_TIME;
    }
    /**
     *
     * @param leaderboard
     * @return
     */
    private JSONObject leaderboardToJSON(Leaderboard leaderboard) {

        // create JSON object
        JSONObject jsonObject= new JSONObject();
        try {
            // fill in the JSON object
            jsonObject.put("id", leaderboard.getId());
            jsonObject.put("name", leaderboard.getName());
            jsonObject.put("displayText", leaderboard.getDisplayText());
            jsonObject.put("imageURL", leaderboard.getImageURL());
            jsonObject.put("scoreFormat", leaderboard.getScoreFormat());

            // return json string
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    /**
     *
     * @param leaderboards
     * @return
     */
    private JSONArray leaderboardToJSONArray(List<Leaderboard> leaderboards) {

        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < leaderboards.size(); i++) {
            jsonArray.put(leaderboardToJSON(leaderboards.get(i)));
        }
        return jsonArray;
    }

    /**
     *
     * @param achievement
     * @return
     */
    private JSONObject achievementToJSON(Achievement achievement) {

        // create JSON object
        JSONObject jsonObject= new JSONObject();
        try {
            // fill in the JSON object
            jsonObject.put("id", achievement.getId());
            jsonObject.put("pointValue", achievement.getPointValue());
            jsonObject.put("description", achievement.getDescription());
            jsonObject.put("imageURL", achievement.getImageURL());
            jsonObject.put("position", achievement.getPosition());
            jsonObject.put("progress", achievement.getProgress());
            jsonObject.put("dateUnlocked", achievement.getDateUnlocked());
            jsonObject.put("title", achievement.getTitle());

            // return json string
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    /**
     *
     * @param achievements
     * @return
     */
    private JSONArray achievementsToJSONArray(List<Achievement> achievements) {

        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < achievements.size(); i++) {
            jsonArray.put(achievementToJSON(achievements.get(i)));
        }
        return jsonArray;
    }

    /**
     *
     * @param player
     * @return
     */
    private JSONObject playerToJSON(Player player) {

        // create JSON object
        JSONObject jsonObject= new JSONObject();
        try {
            // fill in the JSON object
            jsonObject.put("alias", player.getAlias());
            jsonObject.put("avatarUrl", player.getAvatarUrl());
            jsonObject.put("playerId", player.getPlayerId());

            // return json string
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    /**
     *
     * @param score
     * @return
     */
    private JSONObject scoreToJSON(Score score) {

        // create JSON object
        JSONObject jsonObject= new JSONObject();
        try {
            // fill in the JSON object
            jsonObject.put("scoreValue", score.getScoreValue());
            jsonObject.put("leaderboard", score.getLeaderboard());
            jsonObject.put("player", playerToJSON(score.getPlayer()));
            jsonObject.put("rank", score.getRank());
            jsonObject.put("scoreString", score.getScoreString());

            // return json string
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    /**
     *
     * @param scores
     * @return
     */
    private JSONArray scoresToJSONArray(List<Score> scores) {

        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < scores.size(); i++) {
            jsonArray.put(scoreToJSON(scores.get(i)));
        }
        return jsonArray;
    }

    /**
     *
     * @param item
     * @return
     */
    private JSONObject percentileToJSON(LeaderboardPercentileItem item) {

        // create JSON object
        JSONObject jsonObject= new JSONObject();
        try {
            // fill in the JSON object
            jsonObject.put("percentile", item.getPercentile());
            jsonObject.put("playerScore", item.getPlayerScore());
            jsonObject.put("player", playerToJSON(item.getPlayer()));

            // return json string
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    private JSONArray percentilesToJSONArray(List<LeaderboardPercentileItem> items) {

        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < items.size(); i++) {
            jsonArray.put(percentileToJSON(items.get(i)));
        }
        return jsonArray;
    }


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

//        List<AmazonGamesFeature> features = null;

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
    public void showGameCircle() {

        if(_agsClient==null) {

        } else {

            AGResponseHandle<RequestResponse> handle = _agsClient.showGameCircle();


            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<RequestResponse>() {
                @Override
                public void onComplete(RequestResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onShowGameCircleError",result.toString());
                    } else {

                        // Continue game flow.
                        dispatchStatusEventAsync("onShowGameCircleComplete", result.toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
    public void showSignInPage() {

        if(_agsClient==null) {

        } else {

            AGResponseHandle<RequestResponse> handle = _agsClient.showSignInPage();


            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<RequestResponse>() {
                @Override
                public void onComplete(RequestResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onShowGameCircleError",result.toString());
                    } else {

                        // Continue game flow.
                        dispatchStatusEventAsync("onShowGameCircleComplete", result.toString());
                    }
                }
            });
        }


    }


    /**
     *
     */
    public void pause() {

        if (_agsClient != null) {
            _agsClient.release();
            _agsClient=null;
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
     */
    public void shutdown() {

        if(_agsClient==null){
            _agsClient.shutdown();
        }
    }

// Public Methods (Player)
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    public Boolean isSignedIn() {

        if(_agsClient==null) {
            return false;
        } else {

            return _agsClient.getPlayerClient().isSignedIn();
        }
    }

    /**
     *
     */
    public void getLocalPlayer() {

        if(_agsClient==null) {

        } else {

            PlayerClient lbClient = _agsClient.getPlayerClient();
            AGResponseHandle<RequestPlayerResponse> handle = lbClient.getLocalPlayer();

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<RequestPlayerResponse>() {
                @Override
                public void onComplete(RequestPlayerResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetPlayerError",result.toString());
                    } else {

                        // Continue game flow.
                        dispatchStatusEventAsync("onGetPlayerComplete", playerToJSON(result.getPlayer()).toString());
                    }
                }
            });
        }
    }


    /**
     *
     */
    public void getFriendIds() {

        if(_agsClient==null) {

        } else {

            PlayerClient lbClient = _agsClient.getPlayerClient();
            AGResponseHandle<RequestFriendIdsResponse> handle = lbClient.getFriendIds();

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<RequestFriendIdsResponse>() {
                @Override
                public void onComplete(RequestFriendIdsResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetFriendIdsError",result.toString());
                    } else {

                        // Continue game flow.
//                        dispatchStatusEventAsync("onGetFriendIdsComplete", playerToJSON(result.getPlayer()).toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
//    public void getFriendIds() {
//
//        if(_agsClient==null) {
//
//        } else {
//
//            PlayerClient lbClient = _agsClient.getPlayerClient();
//            AGResponseHandle<RequestFriendIdsResponse> handle = lbClient.getBatchFriends();
//
//            // Optional callback to receive notification of success/failure.
//            handle.setCallback(new AGResponseCallback<RequestFriendIdsResponse>() {
//                @Override
//                public void onComplete(RequestFriendIdsResponse result) {
//                    if (result.isError()) {
//                        // Add optional error handling here.  Not strictly required
//                        // since retries and on-device request caching are automatic.
//                        dispatchStatusEventAsync("onGetFriendIdsError",result.toString());
//                    } else {
//
//                        // Continue game flow.
////                        dispatchStatusEventAsync("onGetFriendIdsComplete", playerToJSON(result.getPlayer()).toString());
//                    }
//                }
//            });
//        }
//    }

// Public Methods (Leaderboard)
/////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     *
     */
    public void getPercentileRanks(String leaderboardId, LeaderboardFilter filter) {

        if(_agsClient==null) {

        } else {

            LeaderboardsClient acClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<GetLeaderboardPercentilesResponse> handle = acClient.getPercentileRanks(leaderboardId, filter);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetLeaderboardPercentilesResponse>() {

                @Override
                public void onComplete(GetLeaderboardPercentilesResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetLeaderboardPercentilesError",result.toString());
                    } else {
                        // Create json to hold the score value
                        JSONObject jsonScore=new JSONObject();
                        try {
                            // fill in the JSON object
                            jsonScore.put("leaderboard", leaderboardToJSON(result.getLeaderboard()));
                            jsonScore.put("userIndex", result.getUserIndex());
                            jsonScore.put("percentiles", percentilesToJSONArray(result.getPercentileList()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Continue game flow.
                        dispatchStatusEventAsync("onGetLeaderboardPercentilesComplete", jsonScore.toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
    public void getPercentileRanksForPlayer(String leaderboardId, String playerId, LeaderboardFilter filter) {

        if(_agsClient==null) {

        } else {

            LeaderboardsClient acClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<GetLeaderboardPercentilesResponse> handle = acClient.getPercentileRanksForPlayer(leaderboardId, playerId, filter);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetLeaderboardPercentilesResponse>() {

                @Override
                public void onComplete(GetLeaderboardPercentilesResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetLeaderboardPercentilesError",result.toString());
                    } else {
                        // Create json to hold the score value
                        JSONObject jsonScore=new JSONObject();
                        try {
                            // fill in the JSON object
                            jsonScore.put("leaderboard", leaderboardToJSON(result.getLeaderboard()));
                            jsonScore.put("userIndex", result.getUserIndex());
                            jsonScore.put("percentiles", percentilesToJSONArray(result.getPercentileList()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Continue game flow.
                        dispatchStatusEventAsync("onGetLeaderboardPercentilesComplete", jsonScore.toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
    public void getLocalPlayerScore(String leaderboardId, LeaderboardFilter filter) {

        if(_agsClient==null) {

        } else {

            LeaderboardsClient acClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<GetPlayerScoreResponse> handle = acClient.getLocalPlayerScore(leaderboardId, filter);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetPlayerScoreResponse>() {

                @Override
                public void onComplete(GetPlayerScoreResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetPlayerScoreError",result.toString());
                    } else {

                        // Create json to hold the score value
                        JSONObject jsonScore=new JSONObject();
                        try {
                            // fill in the JSON object
                            jsonScore.put("rank", result.getRank());
                            jsonScore.put("scoreValue", result.getScoreValue());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Continue game flow.
                        dispatchStatusEventAsync("onGetPlayerScoreComplete", jsonScore.toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
    public void getScoreForPlayer(String leaderboardId, String playerId, LeaderboardFilter filter) {

        if(_agsClient==null) {

        } else {

            LeaderboardsClient acClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<GetPlayerScoreResponse> handle = acClient.getScoreForPlayer(leaderboardId, playerId, filter);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetPlayerScoreResponse>() {

                @Override
                public void onComplete(GetPlayerScoreResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetPlayerScoreError",result.toString());
                    } else {
                        // Create json to hold the score value
                        JSONObject jsonScore=new JSONObject();
                        try {
                            // fill in the JSON object
                            jsonScore.put("rank", result.getRank());
                            jsonScore.put("scoreValue", result.getScoreValue());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Continue game flow.
                        dispatchStatusEventAsync("onGetPlayerScoreComplete", jsonScore.toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
    public void getScores(String leaderboardId, LeaderboardFilter filter) {

        if(_agsClient==null) {

        } else {

            LeaderboardsClient acClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<GetScoresResponse> handle = acClient.getScores(leaderboardId, filter);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetScoresResponse>() {

                @Override
                public void onComplete(GetScoresResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetScoresError",result.toString());
                    } else {
                        // Create json to hold the score value
                        JSONObject jsonScore=new JSONObject();
                        try {
                            // fill in the JSON object
                            jsonScore.put("leaderboard", leaderboardToJSON(result.getLeaderboard()));
                            jsonScore.put("scores", scoresToJSONArray(result.getScores()));
                            jsonScore.put("numScores", result.getNumScores());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dispatchStatusEventAsync("onGetScoresComplete", jsonScore.toString());
                    }
                }
            });
        }
    }

    /**
     *
     */
    public void getLeaderboards() {

        if(_agsClient==null) {

        } else {

            LeaderboardsClient acClient = _agsClient.getLeaderboardsClient();
            AGResponseHandle<GetLeaderboardsResponse> handle = acClient.getLeaderboards();

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetLeaderboardsResponse>() {

                @Override
                public void onComplete(GetLeaderboardsResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetLeaderboardsError",result.toString());
                    } else {
                        // Continue game flow.
                        dispatchStatusEventAsync("onGetLeaderboardsComplete", leaderboardToJSONArray(result.getLeaderboards()).toString());
                    }
                }
            });

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
                        // Create json to hold the score value
                        JSONObject jsonScore=new JSONObject();
                        try {
                            JSONObject json=new JSONObject();
                            Map map=result.getNewRank();
                            for (Object key : map.keySet()) {
                                json.put(key.toString(), map.get(key));
                            }
                            jsonScore.put("newRank", json);
                            json=new JSONObject();
                            map=result.getRankImproved();
                            for (Object key : map.keySet()) {
                                json.put(key.toString(), map.get(key));
                            }
                            jsonScore.put("rankImproved", json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Continue game flow.
                        dispatchStatusEventAsync("onSubmitScoreComplete", jsonScore.toString());
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

// Public Methods (Achievements)
/////////////////////////////////////////////////////////////////////////////////////////////////////////

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
     * @param achievementId
     */
    public void getAchievement(String achievementId) {

        if(_agsClient==null) {

        } else {

            AchievementsClient acClient = _agsClient.getAchievementsClient();
            AGResponseHandle<GetAchievementResponse> handle = acClient.getAchievement(achievementId);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetAchievementResponse>() {

                @Override
                public void onComplete(GetAchievementResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetAchievementError", result.toString());
                    } else {
                        // Continue game flow.
                        dispatchStatusEventAsync("onGetAchievementComplete",achievementToJSON(result.getAchievement()).toString());
                    }
                }
            });

        }
    }

    /**
     *
     */
    public void getAchievements() {

        if(_agsClient==null) {

        } else {

            AchievementsClient acClient = _agsClient.getAchievementsClient();
            AGResponseHandle<GetAchievementsResponse> handle = acClient.getAchievements();

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetAchievementsResponse>() {

                @Override
                public void onComplete(GetAchievementsResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetAchievementsError",result.toString());
                    } else {
                        // Continue game flow.
                        dispatchStatusEventAsync("onGetAchievementsComplete", achievementsToJSONArray(result.getAchievementsList()).toString());
                    }
                }
            });

        }
    }

    /**
     *
     * @param achievementId
     */
    public void getAchievementForPlayer(String achievementId, String playerId) {

        if(_agsClient==null) {

        } else {

            AchievementsClient acClient = _agsClient.getAchievementsClient();
            AGResponseHandle<GetAchievementResponse> handle = acClient.getAchievementForPlayer(achievementId, playerId);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetAchievementResponse>() {

                @Override
                public void onComplete(GetAchievementResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetAchievementError", result.toString());
                    } else {
                        // Continue game flow.
                        dispatchStatusEventAsync("onGetAchievementComplete",achievementToJSON(result.getAchievement()).toString());
                    }
                }
            });

        }
    }

    /**
     *
     */
    public void getAchievementsForPlayer(String playerId) {

        if(_agsClient==null) {

        } else {

            AchievementsClient acClient = _agsClient.getAchievementsClient();
            AGResponseHandle<GetAchievementsResponse> handle = acClient.getAchievementsForPlayer(playerId);

            // Optional callback to receive notification of success/failure.
            handle.setCallback(new AGResponseCallback<GetAchievementsResponse>() {

                @Override
                public void onComplete(GetAchievementsResponse result) {
                    if (result.isError()) {
                        // Add optional error handling here.  Not strictly required
                        // since retries and on-device request caching are automatic.
                        dispatchStatusEventAsync("onGetAchievementsError",result.toString());
                    } else {
                        // Continue game flow.
                        dispatchStatusEventAsync("onGetAchievementsComplete", achievementsToJSONArray(result.getAchievementsList()).toString());
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
