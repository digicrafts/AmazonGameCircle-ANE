/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * WhisperSyncSynchronizeBlobProgressRequest.h
 *
 * Request class used as a parameter for more complicated synchronize blob-data progress requests
 */

#ifndef __WHISPERSYNC_SYNCHRONIZED_BLOB_PROGRESS_REQUEST_H_INCLUDED__
#define __WHISPERSYNC_SYNCHRONIZED_BLOB_PROGRESS_REQUEST_H_INCLUDED__

#include "../WhisperSyncClientInterface.h"

namespace AmazonGames {

    class WhisperSyncSynchronizeBlobProgressRequest {
    public:
        WhisperSyncSynchronizeBlobProgressRequest(IWhisperSyncSynchronizeBlobCb* const cb);
        /**
         * Setters
         */
        void setDescription(const char* desc);
        void setData(const unsigned char* dat);
        void setDataLength(int length);
        void setConflictStrategy(AmazonGames::ConflictStrategy strategy);
        /**
         * Getters
         */
        const char* getDescription();
        const unsigned char* getData();
        int getDataLength();
        AmazonGames::ConflictStrategy getConflictStrategy();
        IWhisperSyncSynchronizeBlobCb* const getCallback();

    private:
        static const AmazonGames::ConflictStrategy DEFAULT_STRATEGY = PLAYER_SELECT;
        const char* description;
        const unsigned char* data;
        IWhisperSyncSynchronizeBlobCb* callback;
        AmazonGames::ConflictStrategy mConflictStrategy;
        int mDataLength;
    };
}

#endif
