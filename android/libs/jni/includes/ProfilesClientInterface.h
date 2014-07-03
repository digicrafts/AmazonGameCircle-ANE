/*
 * Copyright 2012, Amazon.com, Inc. or its affiliates.
 */

/**
 * ProfilesClientInterface.h
 *
 * Client interface class for requesting information from the Amazon Games Profiles service.
 */

#ifndef __PROFILES_CLIENT_INTERFACE_H_INCLUDED__
#define __PROFILES_CLIENT_INTERFACE_H_INCLUDED__

#include "AGSClientCommonInterface.h"

namespace AmazonGames {

    //************************************
    // Data access structures
    //************************************
    struct PlayerInfo {
        const char* alias;

        PlayerInfo()
            : alias(0)
        {}
    };

    //************************************
    // Callback classes
    //************************************
    class IProfileGetLocalPlayerProfileCb : public ICallback {
    public:
        virtual void onGetLocalPlayerProfileCb(
                    ErrorCode errorCode,
                    const PlayerInfo* responseStruct,
                    int developerTag) = 0;
    };

    //************************************
    // Handle classes
    //************************************

    // All Handle classes have these functions:
    //    HandleStatus getHandleStatus();
    //    ErrorCode getErrorCode();
    //    int getDeveloperTag();

    class ILocalPlayerProfileHandle : public IHandle {
    public:
        virtual const AmazonGames::PlayerInfo* getResponseData() = 0;

        virtual ILocalPlayerProfileHandle* clone() const = 0;
    };

    //************************************
    // Profiles Client Interface
    //************************************

    class ProfilesClientInterface {

    public:
        //************************************
        // Callbacks
        //************************************
        static void getLocalPlayerProfile(
                IProfileGetLocalPlayerProfileCb* const callback,
                int developerTag = 0);

        //************************************
        // Handles
        //************************************
        static HandleWrapper<ILocalPlayerProfileHandle> getLocalPlayerProfile(
                int developerTag = 0);
    };
}

#endif
