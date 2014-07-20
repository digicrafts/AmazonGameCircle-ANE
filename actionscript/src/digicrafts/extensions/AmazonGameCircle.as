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
 * Time: 2:55 PM
 */
package digicrafts.extensions {
import digicrafts.extensions.data.Achievement;
import digicrafts.extensions.data.Leaderboard;
import digicrafts.extensions.data.LeaderboardPercentileItem;
import digicrafts.extensions.data.Player;
import digicrafts.extensions.data.Score;
import digicrafts.extensions.events.AmazonGameCircleEvent;

import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;
import flash.system.Capabilities;

/**
 *
 */
public class AmazonGameCircle extends EventDispatcher {

    // static
    public static var instance:AmazonGameCircle;
    public static var defaultFeatures:Array=['achievements','leaderboards'];

    // private
    private static var allowInstance:Boolean=false;
    private static var extensionContext:ExtensionContext = null;
    private static var mServiceReady:Boolean=false;


    /**
     *  Constructor
     */
    public function AmazonGameCircle() {

        if(!allowInstance){
            throw new Error("Error: Instantiation failed: Use AmazonGameCircle.getInstance() instead of new.");
        }
    }

// Private Static Function
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @private
     * @return
     *
     */
    public static function getInstance():AmazonGameCircle
    {
        if(instance==null){
            allowInstance=true;
            instance=new AmazonGameCircle();
            if ( !extensionContext && Capabilities.os.indexOf("x86_64")==-1)
            {
                trace("[AmazonGameCircle] Get AmazonGameCircle Extension Instance...");
                extensionContext = ExtensionContext.createExtensionContext("digicrafts.extensions.AmazonGameCircle","AmazonGameCircle");
                extensionContext.addEventListener(StatusEvent.STATUS,instance._handleStatusEvents);
            }
            allowInstance=false;
        }
        return instance;
    }

// Public Static Function
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *  Check if the extension is supported
     *
     */
    public static function isSupported():Boolean
    {
        if(extensionContext)
            return extensionContext.call("isSupported");
        return false;
    }

    /**
     * Check if the service already
     *
     */
    public static function get serviceReady():Boolean
    {
        return mServiceReady;
    }

    /**
     * Initializes the AmazonGamesClient
     *
     */
    public static function initialize(features:Array=null):void
    {
        if(!mServiceReady)
            getInstance()._initialize();
        else
            _error(2);
    }


    /**
     * Show the GameCircle overlay.
     *
     */
    public static function showGameCircle():void
    {
        if(extensionContext)
            extensionContext.call("showGameCircle");
    }

    /**
     * Show the GameCircle sign in page.
     *
     */
    public static function showSignInPage():void
    {
        if(extensionContext)
            extensionContext.call("showSignInPage");
    }

    /**
     * Updates the pop up location for toast notifications with the given location.
     */
    public static function setPopUpLocation(location:String=PopUpLocation.BOTTOM_CENTER):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("setPopUpLocation",location);
        else
            _error(1);
    }

    /**
     *  Call this when application deactivate
     *
     */
    public static function pause():void
    {
        mServiceReady=false;
        if(extensionContext)
            extensionContext.call("pause");
    }

    /**
     *  Call this when application activate
     *
     */
    public static function resume():void
    {
        if(extensionContext)
            extensionContext.call("resume");
    }

// Public Static Function (Leaderboard)
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Request all leaderboards for this game.
     */
    public static function getLeaderboards():void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getLeaderboards");
        else
            _error(1);
    }

    /**
     * Requests the percentile ranks for the passed leaderboard.
     * @param leaderboardId Leaderboard ID to get scores from.
     * @param filter Filter to apply to the request.
     */
    public static function getPercentileRanks(leaderboardId:String,filter:String=LeaderboardFilter.GLOBAL_ALL_TIME):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getPercentileRanks",leaderboardId,filter);
        else
            _error(1);
    }

    /**
     * Requests the percentile ranks for the passed leaderboard for specified player.
     * @param leaderboardId  leaderboardId Leaderboard ID to get scores from.
     * @param playerId playerId of the player whose score we want to retrieve
     * @param filter filter Filter to apply to the request.
     */
    public static function getPercentileRanksForPlayer(leaderboardId:String,playerId:String,filter:String=LeaderboardFilter.GLOBAL_ALL_TIME):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getPercentileRanksForPlayer",leaderboardId,playerId,filter);
        else
            _error(1);
    }

    /**
     * Requests the current users top RankedScore for the leaderboard.
     * @param leaderboardId Leaderboard ID to get scores from
     * @param filter Filter to apply to the request.
     */
    public static function getLocalPlayerScore(leaderboardId:String,filter:String=LeaderboardFilter.GLOBAL_ALL_TIME):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getLocalPlayerScore",leaderboardId,filter);
        else
            _error(1);
    }

    /**
     * Requests the specified player's top RankedScore for the leaderboard.
     * @param leaderboardId  Leaderboard ID to get scores from.
     * @param playerId playerId of the player whose score we want to retrieve
     * @param filter Filter to apply to the request.
     */
    public static function getScoreForPlayer(leaderboardId:String,playerId:String,filter:String=LeaderboardFilter.GLOBAL_ALL_TIME):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getScoreForPlayer",leaderboardId,playerId,filter);
        else
            _error(1);
    }

    /**
     * Request the top 100 scores from a leaderboard based on the filter selected.
     * @param leaderboardId Leaderboard ID to get scores from.
     * @param filter Filter to apply to the request.
     */
    public static function getScores(leaderboardId:String,filter:String=LeaderboardFilter.GLOBAL_ALL_TIME):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getScores",leaderboardId,filter);
        else
            _error(1);
    }


    /**
     * Request that a score be submitted to the leaderboard.
     * @param leaderboardId Leaderboard ID to submit the score to.
     * @param score Score to submit.
     */
    public static function submitScore(leaderboardId:String,score:int):void
    {
        if(mServiceReady&&extensionContext)
                extensionContext.call("submitScore",leaderboardId,score);
        else
            _error(1);
    }
    /**
     *  Show the leaderboard overlay for a particular leaderboard.
     * @param leaderboardId  Leaderboard ID to show.
     */
    public static function showLeaderboard(leaderboardId:String):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("showLeaderboard",leaderboardId);
        else
            _error(1);
    }


// Public Static Function (Achievement)
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Achievement

    /**
     * Returns the specified achievement object.
     * @param achievementId Achievement ID to get.
     */
    public static function getAchievement(achievementId:String):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getAchievement",achievementId);
        else
            _error(1);
    }

    /**
     * Returns the specified achievement object.
     * @param achievementId  Achievement ID to get for specified player.
     * @param playerId The playerId for the player we want to retrieve achievement data about.
     */
    public static function getAchievementForPlayer(achievementId:String, playerId:String):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getAchievementForPlayer",achievementId, playerId);
        else
            _error(1);
    }

    /**
     *  Returns a list of Achievement objects to the returned handle. The list returned in the response includes all visible achievements for the game. Visible achievements include those that were never hidden, and those that were once hidden but since made visible. Each Achievement object in the list includes the current players progress toward the Achievement.
     */
    public static function getAchievements():void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getAchievements");
        else
            _error(1);
    }

    /**
     * Returns a list of Achievement objects to the returned handle. The list returned in the response includes all visible achievements for the game. Visible achievements include those that were never hidden, and those that were once hidden but since made visible. Each Achievement object in the list includes the specified player's progress toward the Achievement.
     * @param playerId The playerId for the player we want to retrieve achievements data about.
     */
    public static function getAchievementsForPlayer(playerId:String):void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("getAchievementsForPlayer",playerId);
        else
            _error(1);
    }

    /**
     * Updates progress toward the specified achievement by the specified amount. Result is returned through the returned handle. The response is a generic RequestResponse If a value outside of range is submitted, it is capped at 100 or 0. If submitted value is less than the stored value, the update is ignored.
     * @param achievementId string that contains the identifier of the achievement.
     * @param progress a float between 0.0f and 100.0f.
     */
    public static function updateAchievement(achievementId:String,progress:Number):void
    {
        if(mServiceReady&&extensionContext)
                extensionContext.call("updateAchievement",achievementId,progress);
        else
            _error(1);
    }
    /**
     * Show the AmazonGames achievements overlay.
     */
    public static  function showAchievements():void
    {
        if(mServiceReady&&extensionContext)
            extensionContext.call("showAchievements");
        else
            _error(1);

    }

// Private Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @private
     * @param code
     */
    protected static function _error(code:int):void
    {
        var msg:String="error";
        switch (code){
            case 1:
                msg="Service not ready!";
                break;
            case 2:
                msg="Service already initialize!";
                break;

        }
        trace("[AmazonGameCircle] error code:", code, "msg:", msg);
    }
    /**
     * @private
     *
     */
    protected function _initialize():void
    {
        if(extensionContext)
            extensionContext.call("initialize");
    }


// Private Handler Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @private
     * @param e
     *
     */
    private function _handleStatusEvents(e:StatusEvent):void
    {
        var event:AmazonGameCircleEvent;

        trace('[AmazonGameCircle] events:',e.code,e.level);

        switch (e.code) {
            case AmazonGameCircleEvent.GET_PLAYER_COMPLETE:
                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {
                    // convert the json to array
                    var player:Object = JSON.parse(e.level);
                    if(player){
                        // assign the achievements
                        event.data=new Player(player);
                    }
                }
                break;
            case AmazonGameCircleEvent.GET_LEADERBOARD_PERCENTILES_COMPLETE:

                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {
                    // create achievements list
                    var percentiles:Vector.<LeaderboardPercentileItem> = new <LeaderboardPercentileItem>[];

                    // convert the json to array
                    var percentiles_results:Array = JSON.parse(e.level) as Array;
                    if(percentiles_results){

                        // convert the objects to percentiles_results
                        for each(var obj:Object in percentiles_results.percentiles){
                            percentiles.push(new LeaderboardPercentileItem(obj));
                        }
                        // assign the achievements
                        event.data={
                            leaderboard:percentiles_results.leaderboard,
                            userIndex:percentiles_results.userIndex,
                            percentiles:percentiles
                        };
                    }
                }

                break;
            case AmazonGameCircleEvent.GET_PLAYERSCORE_COMPLETE:
                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {
                    // convert the json to array
                    var score:Object = JSON.parse(e.level);
                    if(score){
                        // assign the achievements
                        event.data=new Score(score);
                    }
                }
                break;
            case AmazonGameCircleEvent.GET_SCORES_COMPLETE:

                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {
                    // create achievements list
                    var scores:Vector.<Score> = new <Score>[];

                    // convert the json to array
                    var scores_results:Array = JSON.parse(e.level) as Array;
                    if(scores_results){

                        // convert the objects to leaderboards
                        for each(var obj:Object in scores_results.scores){
                            scores.push(new Score(obj));
                        }
                        // assign the achievements
                        event.data={
                            leaderboard:scores_results.leaderboard,
                            scores:scores
                        };
                    }
                }

                break;
            case AmazonGameCircleEvent.GET_LEADERBOARDS_COMPLETE:

                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {
                    // create achievements list
                    var leaderboards:Vector.<Leaderboard> = new <Leaderboard>[];

                    // convert the json to array
                    var leaderboards_results:Array = JSON.parse(e.level) as Array;
                    if(leaderboards_results){

                        // convert the objects to leaderboards
                        for each(var obj:Object in leaderboards_results){
                            leaderboards.push(new Leaderboard(obj));
                        }
                        // assign the achievements
                        event.data=leaderboards;
                    }
                }

                break;
            case AmazonGameCircleEvent.GET_ACHIEVEMENT_COMPLETE:

                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {

                    // convert the json to array
                    var achievement:Object = JSON.parse(e.level);
                    if(achievement){

                        // assign the achievements
                        event.data=new Achievement(achievement);
                    }
                }
                break;
            case AmazonGameCircleEvent.GET_ACHIEVEMENTS_COMPLETE:

                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);

                if(e.level) {
                    // create achievements list
                    var achievements:Vector.<Achievement> = new <Achievement>[];

                    // convert the json to array
                    var achievements_results:Array = JSON.parse(e.level) as Array;
                    if(achievements_results){

                        // convert the objects to achievements
                        for each(var obj:Object in achievements_results){
                            achievements.push(new Achievement(obj));
                        }
                        // assign the achievements
                        event.data=achievements;
                    }
                }

                break;
            case AmazonGameCircleEvent.SUBMIT_SCORE_COMPLETE:
                // create event
                event = new AmazonGameCircleEvent(e.code, e.level);
                event.data=JSON.parse(e.level);
                break;
            case "LOGGING":
                trace('[AmazonGameCircle] ' + e.level);
            case AmazonGameCircleEvent.SERVICE_READY:
                mServiceReady=true;
            default:
                event = new AmazonGameCircleEvent(e.code, e.level);
                break;
        }

        if (event) dispatchEvent(event);
    }
}
}
