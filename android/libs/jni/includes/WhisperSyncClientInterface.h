/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * WhisperSyncClientInterface.h
 *
 * Client interface class for interacting with the Amazon Games WhisperSync service.
 */

#ifndef __WHISPER_SYNC_CLIENT_INTERFACE_H_INCLUDED__
#define __WHISPER_SYNC_CLIENT_INTERFACE_H_INCLUDED__

#include "AGSClientCommonInterface.h"

namespace AmazonGames {

    class WhisperSyncSynchronizeBlobRequest;
    class WhisperSyncSynchronizeMultiFileRequest;
    class WhisperSyncSynchronizeBlobProgressRequest;
    class WhisperSyncSynchronizeMultiFileProgressRequest;

    //************************************
    // Constants
    //************************************
    enum ConflictStrategy {
       /**
        * Asks the player to:
        *     - Upload the current progress to the cloud
        *     - Download and continue with the latest version in the cloud
        *     - Defer resolving the conflict and continuing locally with the current version
        *
        * This is considered the default behavior
        */
        PLAYER_SELECT=0,
       /**
        * Automatically resolves to download the latest version of game data from the cloud.  This
        * is typically used when the game has been either recently installed or re-installed and there
        * has been no game progress.
        */
        AUTO_RESOLVE_TO_CLOUD,
       /**
        * Automatically defers conflict resolution.  This is typically used during onStop() life-cycle
        * events to allow the game to save to the cloud only if there is no conflict.  If a conflict
        * is encountered, this strategy will automatically defer without asking the user.
        */
        AUTO_RESOLVE_TO_IGNORE,
    };

    //************************************
    // Callback classes
    //************************************

    class IWhisperSyncSynchronizeBlobCb : public ICallback {
    public:
        virtual void onAlreadySynchronized(int developerTag) = 0;
        virtual void onConflictDeferral(int developerTag) = 0;
        virtual void onGameUploadSuccess(int developerTag) = 0;
        virtual void onSynchronizeFailure(ErrorCode errorCode, int developerTag) = 0;
        virtual bool onNewGameData(char* data, int developerTag) = 0;
    };
    
    class IWhisperSyncSynchronizeMultiFileCb : public ICallback {
    public:
        virtual void onAlreadySynchronized(int developerTag) = 0;
        virtual void onConflictDeferral(int developerTag) = 0;
        virtual void onGameUploadSuccess(int developerTag) = 0;
        virtual void onSynchronizeFailure(ErrorCode errorCode, int developerTag) = 0;
        virtual void onUnpackComplete(int developerTag) = 0;
        virtual void onUnpackFailure(int developerTag) = 0;
        virtual bool onNewGameData(int developerTag) = 0;
    };
    
    class IWhisperSyncRevertBlobCb : public ICallback {
    public:
        virtual bool onRevertedGameData(char* data, int developerTag) = 0;
        virtual void onPlayerCancelled(int developerTag) = 0;
        virtual void onRevertFailure(ErrorCode errorCode, int developerTag) = 0;
    };
    
    class IWhisperSyncRevertMultiFileCb : public ICallback {
    public:
        virtual void onRevertedGameData(int developerTag) = 0;
        virtual void onPlayerCancelled(int developerTag) = 0;
        virtual void onRevertFailure(ErrorCode errorCode, int developerTag) = 0;
    };
    
    //************************************
    // WhisperSync Client Interface
    //************************************
    class WhisperSyncClientInterface {
    public:
        /**
         * Initiates an asynchronous request to synchronize the game with the latest version in the cloud using the default
         * conflict strategy.  If a previous synchronizeProgress() call has failed, this method will also attempt to
         * synchronize that progress. This is typically called during onResume() events.
         * @param callback SynchronizeMultiFileCallback to handle the response from the service.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void synchronize(IWhisperSyncSynchronizeBlobCb* const callback, int developerTag = 0);
        /**
         * Initiates an asynchronous request to synchronize the game with the latest version of the game state
         * in the cloud.  The response will be handled by the callback specified by the request.  If a previous
         * synchronizeProgress() call had failed, this method will attempt to synchronize the progress.  This is typically
         * called during onResume() events.
         * @param request SynchronizeMultiFileRequest object used to specify the various options for synchronizing.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void synchronize(WhisperSyncSynchronizeBlobRequest* const request, int developerTag = 0);
        /**
         * Initiates an asynchronous request to synchronize the game with the latest version in the cloud using the default
         * conflict strategy.  If a previous synchronizeProgress() call has failed, this method will attempt to
         * synchronize that progress. This is typically called during onResume() events.
         * @param callback SynchronizeMultiFileCallback to handle the response from the service.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void synchronize(IWhisperSyncSynchronizeMultiFileCb* const callback, int developerTag = 0);
        /**
         * Initiates an asynchronous request to synchronize the game with the latest version of the game state
         * in the cloud.  The response will be handled by the callback specified by the request.  If a previous
         * synchronizeProgress() call had failed, this method will attempt to synchronize the progress.  This is typically
         * called during onResume() events.
         * @param request SynchronizeMultiFileRequest object used to specify the various options for synchronizing.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void synchronize(WhisperSyncSynchronizeMultiFileRequest* const request, int developerTag = 0);
        /**
         * Initiates an asynchronous request to synchronize the current game state (represented as a byte[])
         * with the latest version of the game state in the cloud.  The response will be handled by the callback
         * specified by the request.  This is typically called when the game reaches a checkpoint or after progress
         * has been made.
         * @param request SynchronizeBlobRequest used to specify the various options for synchronizing.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void synchronizeProgress (WhisperSyncSynchronizeBlobProgressRequest* const request, int developerTag = 0);
        /**
         * Initiates an asynchronous request to synchronize the current game state with the latest
         * version of the game state in the cloud.  The response will be handled by the callback
         * specified by the request. This is typically called when the game reaches a checkpoint or after progress
         * has been made.
         * @param request SynchronizeMultiFileRequest object used to specify the various options for synchronizing.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void synchronizeProgress (WhisperSyncSynchronizeMultiFileProgressRequest* const request, int developerTag = 0);
        /**
         * Initiates an asynchronous request to revert the game state to a previously saved state.  The
         * player will be presented with a list of previous checkpoints to select to revert to.
         * @param callback to handle various response scenarios.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void requestRevert(IWhisperSyncRevertBlobCb* const callback, int developerTag = 0);
        /**
         * Initiates an asynchronous request to revert the game state to a previously saved state.  The
         * player will be presented with a list of previous checkpoints to select to revert to.  This
         * should only be called by games that synchronize
         * @param callback to handle various response scenarios.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void requestRevert(IWhisperSyncRevertMultiFileCb* const callback, int developerTag = 0);
        /**
         * Specifies the FilenameFilter that selects files to be included in synchronize() operations.  The
         * filter is recursively applied to each file/directory under the game's package directory.
         * (i.e. /data/com.example.game/)
         *
         * If not specified, a default FilenameFilter will be used which includes all files not
         * found under a 'cache' or 'lib' directory.  The filter can be overridden for an individual
         * save() operation.
         * @param filter Specifies which files are to be included or excluded for save() operations.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void setFilter(const char* filterRegex, int developerTag = 0);
        /**
         * Returns true when there is multi-file game data that was previously downloaded
         * from the cloud during a synchronize call, but was never unpacked over the file system.
         * @param developerTag Value used to tag your request with a unique value
         */
        static bool hasNewMultiFileGameData(int developerTag = 0);
        /**
         * This will unpack and overlay multi-file game data that was previously downloaded from the cloud
         * during a synchronize call, but never unpacked.
         * @param developerTag Value used to tag your request with a unique value
         */
        static void unpackNewMultiFileGameData(int developerTag = 0);
    };
};

#endif
