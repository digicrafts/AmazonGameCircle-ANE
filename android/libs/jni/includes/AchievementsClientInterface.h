/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * AchievementsClientInterface.h
 *
 * Client interface class for requesting and submitting information to the
 * Amazon Games Achievements service.
 */

#ifndef __ACHIEVEMENTS_CLIENT_INTERFACE_H_INCLUDED__
#define __ACHIEVEMENTS_CLIENT_INTERFACE_H_INCLUDED__

#include "AGSClientCommonInterface.h"

namespace AmazonGames {

    //************************************
    // Data access structures
    //************************************

    struct AchievementData {
        const char* id;
        const char* title;
        const char* description;
        int pointValue;
        bool isHidden;
        bool isUnlocked;
        float progress;
        int position;

        AchievementData()
            : id(0), title(0), description(0)
        {}
    };

    struct AchievementsData {
        int numAchievements;
        const AchievementData* achievements;
    };

    struct UpdateProgressResponse {
        const char* achievementId;
        bool isNewlyUnlocked;
    };

    //***********************************
    // Callback classes
    //***********************************

    class IGetAchievementCb : public ICallback {
    public:
        virtual void onGetAchievementCb(
                ErrorCode errorCode,
                const AchievementData* responseStruct,
                int developerTag) = 0;
    };

    class IGetAchievementsCb : public ICallback {
    public:
        virtual void onGetAchievementsCb(
                ErrorCode errorCode,
                const AchievementsData* responseStruct,
                int developerTag) = 0;
    };

    class IResetAchievementCb : public ICallback {
    public:
        virtual void onResetAchievementCb(
                ErrorCode errorCode,
                int developerTag) = 0;
    };

    class IResetAchievementsCb : public ICallback {
    public:
        virtual void onResetAchievementsCb(
                ErrorCode errorCode,
                int developerTag) = 0;
    };

    class IUpdateProgressCb : public ICallback {
    public:
        virtual void onUpdateProgressCb(
                ErrorCode errorCode,
                const UpdateProgressResponse* responseStruct,
                int developerTag) = 0;
    };

    //***********************************
    // Handle classes
    //***********************************

    // All Handle classes have these functions:
    //    HandleStatus getHandleStatus();
    //    ErrorCode getErrorCode();
    //    int getDeveloperTag();

    class IGetAchievementHandle : public IHandle {
    public:
        virtual const AchievementData* getResponseData() = 0;

        virtual IGetAchievementHandle* clone() const = 0;
    };

    class IGetAchievementsHandle : public IHandle {
    public:
        virtual const AchievementsData* getResponseData() = 0;

        virtual IGetAchievementsHandle* clone() const = 0;
    };

    class IUpdateProgressHandle : public IHandle {
    public:
        virtual const UpdateProgressResponse* getResponseData() = 0;

        virtual IUpdateProgressHandle* clone() const = 0;
    };

    class IResetAchievementHandle : public IHandle {
    public:
        //
        virtual IResetAchievementHandle* clone() const = 0;
    };

    class IResetAchievementsHandle : public IHandle {
    public:
        //
        virtual IResetAchievementsHandle* clone() const = 0;
    };

    //************************************
    // Achievements Client Interface
    //************************************

    class AchievementsClientInterface {

    public:

        /**
         * Brings up the Amazon Games Achievements overlay for the player.
         */
        static void showAchievementsOverlay();

        //************************************
        // Callbacks
        //************************************

        static void getAchievements(
                IGetAchievementsCb* const callback,
                int developerTag = 0);

        static void getAchievement(
                const char* achievementId,
                IGetAchievementCb* const callback,
                int developerTag = 0);

        static void updateProgress(
                const char* achievementId,
                float percentComplete,
                IUpdateProgressCb* const callback,
                int developerTag = 0);

        static void resetAchievements(
                IResetAchievementsCb* const callback,
                int developerTag = 0);

        static void resetAchievement(
                const char* achievementId,
                IResetAchievementCb* const callback,
                int developerTag = 0);

        //************************************
        // Handles
        //     HandleWrapper class is defined in AGSClientCommonInterface.h
        //************************************

        static HandleWrapper<IGetAchievementsHandle> getAchievements(
                int developerTag = 0);

        static HandleWrapper<IGetAchievementHandle> getAchievement(
                const char* achievementId,
                int developerTag = 0);

        static HandleWrapper<IUpdateProgressHandle> updateProgress(
                const char* achievementId,
                float percentComplete,
                int developerTag = 0);

        static HandleWrapper<IResetAchievementsHandle> resetAchievements(
                int developerTag = 0);

        static HandleWrapper<IResetAchievementHandle> resetAchievement(
                const char* achievementId,
                int developerTag = 0);
    };
}

#endif
