/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * WhisperSyncSynchronizeMultiFileProgressRequest.h
 *
 * Request class used as a parameter for more complicated synchronize multi-file progress requests
 */

#ifndef __WHISPERSYNC_SYNCHRONIZED_MULTI_FILE_PROGRESS_REQUEST_H_INCLUDED__
#define __WHISPERSYNC_SYNCHRONIZED_MULTI_FILE_PROGRESS_REQUEST_H_INCLUDED__

#include "../WhisperSyncClientInterface.h"

namespace AmazonGames {

    class WhisperSyncSynchronizeMultiFileProgressRequest {
    public:
        WhisperSyncSynchronizeMultiFileProgressRequest(IWhisperSyncSynchronizeMultiFileCb* const cb);
        /**
         * Setters
         */
        void setDescription(const char* desc);
        void setFilter(const char* excludeRegex);
        void setConflictStrategy(AmazonGames::ConflictStrategy strategy);
        /**
         * Getters
         */
        const char* getDescription();
        const char* getFilter();
        AmazonGames::ConflictStrategy getConflictStrategy();
        IWhisperSyncSynchronizeMultiFileCb* const getCallback();

    private:
        static const AmazonGames::ConflictStrategy DEFAULT_STRATEGY = PLAYER_SELECT;
        const char* description;
        const char* filter;
        IWhisperSyncSynchronizeMultiFileCb* callback;
        AmazonGames::ConflictStrategy mConflictStrategy;
    };
}

#endif
