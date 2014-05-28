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
import digicrafts.extensions.events.AmazonGameCircleEvent;

import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;
import flash.system.Capabilities;

public class AmazonGameCircle extends EventDispatcher {

    // static
    public static var instance:AmazonGameCircle;
    public static var defaultFeatures:Array=['achievements','leaderboards'];

    // private
    private static var allowInstance:Boolean=false;
    private static var extensionContext:ExtensionContext = null;

    public function AmazonGameCircle() {

        if(!allowInstance){
            throw new Error("Error: Instantiation failed: Use AmazonGameCircle.getInstance() instead of new.");
        }
    }

// Private Static Function
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
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
     *
     *
     */
    public static function isSupported():Boolean
    {
        return getInstance()._isSupported();
    }

    /**
     *
     *
     */
    public static function initialize(features:Array=null):void
    {
        getInstance()._initialize();
    }

    /**
     *
     *
     */
    public static function pause():void
    {
        getInstance()._pause();
    }

    /**
     *
     *
     */
    public static function resume():void
    {
        getInstance()._resume();
    }

    /**
     *
     * @param leaderboardId
     * @param score
     */
    public static function submitScore(leaderboardId:String,score:int):void
    {
        getInstance()._submitScore(leaderboardId,score);
    }
    /**
     *
     *
     */
    public static function showLeaderboard(leaderboardId:String):void
    {
        getInstance()._showLeaderboard(leaderboardId);
    }

    /**
     *
     * @param achievementId
     * @param progress
     */
    public static function updateAchievement(achievementId:String,progress:Number):void
    {
        getInstance()._updateAchievement(achievementId,progress);
    }
    /**
     *
     */
    public static  function showAchievements():void
    {
        getInstance()._showAchievements();
    }
    /**
     *
     */
    public static  function setPopUpLocation(location:String):void
    {
        getInstance()._setPopUpLocation(location);
    }

// Private Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     *
     */
    protected function _isSupported():Boolean
    {
        if(extensionContext)
            return extensionContext.call("isSupported");
        return false;
    }
    /**
     *
     *
     */
    protected function _initialize():void
    {
        if(extensionContext)
            extensionContext.call("initialize");
    }

    /**
     *
     *
     */
    protected function _pause():void
    {
        if(extensionContext)
            extensionContext.call("pause");
    }

    /**
     *
     *
     */
    protected function _resume():void
    {
        if(extensionContext)
            extensionContext.call("resume");
    }

    /**
     *
     * @param leaderboardId
     * @param score
     */
    protected function _submitScore(leaderboardId:String,score:int):void
    {
        if(extensionContext)
            extensionContext.call("submitScore",leaderboardId,score);
    }

    /**
     *
     * @param leaderboardId
     */
    protected function _showLeaderboard(leaderboardId:String):void
    {
        if(extensionContext)
            extensionContext.call("showLeaderboard",leaderboardId);
    }

    /**
     *
     * @param achievementId
     * @param progress
     */
    protected function _updateAchievement(achievementId:String,progress:Number):void
    {
        if(extensionContext)
            extensionContext.call("updateAchievement",achievementId,progress);
    }

    /**
     *
     */
    protected function _showAchievements():void
    {
        if(extensionContext)
            extensionContext.call("showAchievements");
    }

    protected function _setPopUpLocation(location:String):void
    {
        if(extensionContext)
            extensionContext.call("setPopUpLocation",location);
    }




// Private Handler Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param e
     *
     */
    protected function _handleStatusEvents(e:StatusEvent):void
    {
        var event:AmazonGameCircleEvent;

        trace('[AmazonGameCircle] events:',e.code,e.level);

        switch (e.code) {
            case AmazonGameCircleEvent.SERVICE_READY:
            case AmazonGameCircleEvent.SERVICE_NOT_READY:
            case AmazonGameCircleEvent.SUBMIT_SCORE_COMPLETE:
            case AmazonGameCircleEvent.SUBMIT_SCORE_ERROR:
                event = new AmazonGameCircleEvent(e.code, e.level);
                break;
            case "LOGGING":
                trace('[AmazonGameCircle] ' + e.level);
        }

        if (event) dispatchEvent(event);
    }
}
}
