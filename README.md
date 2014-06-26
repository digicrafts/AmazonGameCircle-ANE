AmazonGameCircle Native Extension for Adobe Air
=========

This AmazonGameCircle ANE add supprt to using Amazon Game Circle with Adobe Air. Supports Android only.

##About Amazon Game Circle?

https://developer.amazon.com/public/apis/engage/gamecircle

##Changelog

1.0.1

- add serviceReady to indicate the service is ready.

##Install the library

Add the AmazonGameCircle-ANE library to your project.

In Flash Professional CS6:

  1. Create a new mobile project
  2. Choose File > PublishSettings... 
  3. Select the wrench icon next to 'Script' for 'ActionScriptSettings' 
  4. Select the Library Path tab. 
  5. Click 'Browse for Native Extension(ANE) File' and select the Mopub.ane file. 

In Flash Builder 4.6:

  1. Goto Project Properties
  2. Select Native Extensions under Actionscript Build Path
  3. Choose Add ANE... and navigate to the Mopub.ane file 
  4. Select Actionscript Build Packaging > Google Android or Apple IOS
  5. Select the Native Extensions tab, and click the 'Package' check box next to the extension

In Flash Professional CS5.5 or Lower:

  1. Select File>PublishSettings>Flash>ActionScript 3.0 Settings 
  2. Select External Library Path
  3. Click Browseto SWC File
  4. Select the Mopub.swc

In Flash Builder 4.5:

  1. Goto Project Properties
  2. Select Action Script Build Path
  3. Select Add Swc
  4. Navigate to Mopub.swc and choose External Library type

In FlashDevelop:

  1. Copy the Mopub.swc file to your project folder.
  2. In the explorer panel, right click the .swc and select Add to Library.
  3. Right-click the swc file in the explorer, choose Options, and select External Library

##Add the Actionscript

Import the library

```javascript
  import digicrafts.extensions.AmazonGameCircle;
  import digicrafts.extensions.events.AmazonGameCircleEvent;
```

Initialize.

```javascript
  AmazonGameCircle.getInstance().addEventListener(AmazonGameCircleEvent.SERVICE_READY, handleAmazonGameCircleEvent);
  AmazonGameCircle.getInstance().addEventListener(AmazonGameCircleEvent.SERVICE_NOT_READY, handleAmazonGameCircleEvent);
  AmazonGameCircle.initialize();
  
  private function handleAmazonGameCircleEvent(e:AmazonGameCircleEvent):void
  {
      switch (e.type){
          case AmazonGameCircleEvent.SERVICE_READY:
              // Game circle ready           
              break;
          case AmazonGameCircleEvent.SERVICE_NOT_READY:
              // Game circle not ready
              break;
      }
  }
```

Submit Score. 

```javascript
  AmazonGameCircle.submitScore(leaderboard_id, value);
    
  private function handleAmazonGameCircleLeaderboardEvent(e:AmazonGameCircleEvent):void
  {
      switch (e.type){
          case AmazonGameCircleEvent.SUBMIT_SCORE_COMPLETE:
  
              break;
          case AmazonGameCircleEvent.SUBMIT_SCORE_ERROR:
              break;
      }
  }  
```

Show leaderboard.

```javascript
  AmazonGameCircle.showLeaderboard(leaderboard_id);
```

Update Achievements.

```javascript
  AmazonGameCircle.updateAchievement(achievementId,progress);
```

Show Achievements.

```javascript
  AmazonGameCircle.showAchievements();
```

##Setup for Android

Update Your Application Descriptor

You'll need to be using the AIR 4.0 SDK or higher, include the extension in your Application Descriptor XML, and update the Android Manifest Additions with some settings.

Add the following settings in <application> tag. Remember to replace your "YOUR.APP.PACKAGE.ID".

```xml
  <activity android:name="com.amazon.ags.html5.overlay.GameCircleUserInterface"
      android:theme="@style/GCOverlay" android:hardwareAccelerated="false"></activity>
  <activity
    android:name="com.amazon.identity.auth.device.authorization.AuthorizationActivity"
    android:theme="@android:style/Theme.NoDisplay"
    android:allowTaskReparenting="true"
    android:launchMode="singleTask">
    <intent-filter>
       <action android:name="android.intent.action.VIEW" />
       <category android:name="android.intent.category.DEFAULT" />
       <category android:name="android.intent.category.BROWSABLE" />
       <data android:host="YOUR.APP.PACKAGE.ID" android:scheme="amzn" />
    </intent-filter>
  </activity>
  <activity android:name="com.amazon.ags.html5.overlay.GameCircleAlertUserInterface"
  android:theme="@style/GCAlert" android:hardwareAccelerated="false"></activity>
  <receiver
    android:name="com.amazon.identity.auth.device.authorization.PackageIntentReceiver"
    android:enabled="true">
    <intent-filter>
       <action android:name="android.intent.action.PACKAGE_INSTALL" />
       <action android:name="android.intent.action.PACKAGE_ADDED" />
       <data android:scheme="package" />
    </intent-filter>
  </receiver>
```

##TODO

-Allow initializing with different Games Feature
-Supporting Whispersync

##Game we made using this ANE

[Flippy Day](http://www.amazon.com/Digicrafts-Flippy-Day/dp/B00KL3TYJE/ref=sr_1_1?ie=UTF8&qid=1401244788&sr=8-1&keywords=Flippy+Day)

[Recycle Rangers](http://www.amazon.com/Recycle-Rangers-Free-Kindle-Tablet/dp/B00B4MTUEU/ref=sr_1_1?ie=UTF8&qid=1401244943&sr=8-1&keywords=Recycle+rangers)

[Diamond Speedy](http://www.amazon.com/Diamond-Speedy-Kindle-Tablet-Edition/dp/B0091FQNHO/ref=sr_1_1?ie=UTF8&qid=1401244971&sr=8-1&keywords=Diamond+Speedy)


##Developer

The software is developed by Digicrafts.

http://www.facebook.com/DigicraftsComponents

http://www.digicrafts.com.hk/components

http://www.html5components.com

##License

This project is licensed under the BSD license
