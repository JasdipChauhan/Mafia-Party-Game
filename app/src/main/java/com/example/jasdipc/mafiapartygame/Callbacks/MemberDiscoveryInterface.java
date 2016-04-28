package com.example.jasdipc.mafiapartygame.Callbacks;

public interface MemberDiscoveryInterface {

    void foundNewMember(String name, String endpointID);

    void lostMember(String endpointID);

}
