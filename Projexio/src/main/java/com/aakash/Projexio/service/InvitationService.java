package com.aakash.Projexio.service;

import com.aakash.Projexio.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    public void sendInvitation(String email, Long projectId) throws MessagingException;
    public Invitation acceptInvitation(String token, Long projectId) throws Exception;
    public String getTokenByUserMail(String userMail);
    void deleteToken(String token);
}
