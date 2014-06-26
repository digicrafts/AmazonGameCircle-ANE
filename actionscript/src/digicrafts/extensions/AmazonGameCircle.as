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
    public static function get serviceReady():Boolean
    {
        return mServiceReady;
    }

    /**
     *
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
        if(mServiceReady)
            getInstance()._submitScore(leaderboardId,score);
        else
            _error(1);
    }
    /**
     *
     *
     */
    public static function showLeaderboard(leaderboardId:String):void
    {
        if(mServiceReady)
            getInstance()._showLeaderboard(leaderboardId);
        else
            _error(1);
    }

    /**
     *
     * @param achievementId
     * @param progress
     */
    public static function updateAchievement(achievementId:String,progress:Number):void
    {
        if(mServiceReady)
            getInstance()._updateAchievement(achievementId,progress);
        else
            _error(1);
    }
    /**
     *
     */
    public static  function showAchievements():void
    {
        if(mServiceReady)
            getInstance()._showAchievements();
        else
            _error(1);

    }
    /**
     *
     */
    public static function setPopUpLocation(location:String):void
    {
        if(mServiceReady)
            getInstance()._setPopUpLocation(location);
        else
            _error(1);
    }

// Private Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
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
        mServiceReady=false;
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
                mServiceReady=true;
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
