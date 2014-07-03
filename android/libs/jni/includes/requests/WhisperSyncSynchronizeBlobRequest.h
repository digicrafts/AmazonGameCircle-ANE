/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * WhisperSyncSynchronizeBlobRequest.h
 *
 * Request class used as a parameter for more complicated synchronize blob-data requests
 */

#ifndef __WHISPERSYNC_SYNCHRONIZED_BLOB_REQUEST_H_INCLUDED__
#define __WHISPERSYNC_SYNCHRONIZED_BLOB_REQUEST_H_INCLUDED__

#include "../WhisperSyncClientInterface.h"

namespace AmazonGames {

    class WhisperSyncSynchronizeBlobRequest {
    public:
        WhisperSyncSynchronizeBlobRequest(IWhisperSyncSynchronizeBlobCb* const cb);
        /**
         * Setters
         */
        void setConflictStrategy(ConflictStrategy strategy);
        /**
         * Getters
         */
        AmazonGames::ConflictStrategy getConflictStrategy();
        IWhisperSyncSynchronizeBlobCb* const getCallback();

    private:
        static const AmazonGames::ConflictStrategy DEFAULT_STRATEGY = PLAYER_SELECT;
        IWhisperSyncSynchronizeBlobCb* callback;
        AmazonGames::ConflictStrategy mConflictStrategy;
    };
}

#endif
