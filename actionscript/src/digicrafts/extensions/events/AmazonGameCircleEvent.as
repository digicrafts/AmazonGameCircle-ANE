/**
 * (R)2002-2013 Digicrafts
 * All Rights Reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 *
 * Created with IntelliJ IDEA.
 * User: tsangwailam
 * Date: 26/5/14
 * Time: 1:34 PM
 */
package digicrafts.extensions.events {
import flash.events.Event;

public class AmazonGameCircleEvent extends Event {

    // General
    public static const SERVICE_READY:String='onServiceReady';
    public static const SERVICE_NOT_READY:String='onServiceNotReady';
    // Player Events
    public static const GET_PLAYER_ERROR:String='onGetPlayerError';
    public static const GET_PLAYER_COMPLETE:String='onGetPlayerComplete';
    // Leaderboard Events
    public static const GET_LEADERBOARDS_ERROR:String='onGetLeaderboardsError';
    public static const GET_LEADERBOARDS_COMPLETE:String='onGetLeaderboardsComplete';
    public static const GET_PLAYERSCORE_ERROR:String='onGetGetPlayerScoreError';
    public static const GET_PLAYERSCORE_COMPLETE:String='onGetPlayerScoreComplete';
    public static const GET_LEADERBOARD_PERCENTILES_ERROR:String='onGetLeaderboardPercentilesError';
    public static const GET_LEADERBOARD_PERCENTILES_COMPLETE:String='onGetLeaderboardPercentilesComplete';
    public static const GET_SCORES_ERROR:String='onGetScoresError';
    public static const GET_SCORES_COMPLETE:String='onGetScoresComplete';
    public static const SUBMIT_SCORE_ERROR:String='onSubmitScoreError';
    public static const SUBMIT_SCORE_COMPLETE:String='onSubmitScoreComplete';
    // Achievement Events
    public static const UPDATE_ACHIEVEMENT_ERROR:String='onUpdateAchievementError';
    public static const UPDATE_ACHIEVEMENT_COMPLETE:String='onUpdateAchievementComplete';
    public static const GET_ACHIEVEMENT_ERROR:String='onGetAchievementError';
    public static const GET_ACHIEVEMENT_COMPLETE:String='onGetAchievementComplete';
    public static const GET_ACHIEVEMENTS_ERROR:String='onGetAchievementsError';
    public static const GET_ACHIEVEMENTS_COMPLETE:String='onGetAchievementsComplete';

    /** Name of the location related to this event. */
    public var location:String;

    /** Data of the events return*/
    public var data:*;

    public function AmazonGameCircleEvent(type:String, location:String=null, bubbles:Boolean=false, cancelable:Boolean=false)
    {
        super(type, bubbles, cancelable);
        this.location = location;
    }
}
}
