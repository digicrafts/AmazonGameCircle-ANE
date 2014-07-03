/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * WhisperSyncSynchronizeMultiFileRequest.h
 *
 * Request class used as a parameter for more complicated synchronize multi-file requests
 */

#ifndef __WHISPERSYNC_SYNCHRONIZED_MULTI_FILE_REQUEST_H_INCLUDED__
#define __WHISPERSYNC_SYNCHRONIZED_MULTI_FILE_REQUEST_H_INCLUDED__

#include "../WhisperSyncClientInterface.h"

namespace AmazonGames {

    class WhisperSyncSynchronizeMultiFileRequest {
    public:
        WhisperSyncSynchronizeMultiFileRequest(IWhisperSyncSynchronizeMultiFileCb* const cb);
        /**
         * Setters
         */
        void setConflictStrategy(ConflictStrategy strategy);
        /**
         * Getters
         */
        AmazonGames::ConflictStrategy getConflictStrategy();
        IWhisperSyncSynchronizeMultiFileCb* const getCallback();
    private:
        static const AmazonGames::ConflictStrategy DEFAULT_STRATEGY = PLAYER_SELECT;
        IWhisperSyncSynchronizeMultiFileCb* callback;
        AmazonGames::ConflictStrategy mConflictStrategy;
    };
}

#endif
