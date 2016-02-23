package com.twilio.sdk.creators.api.v2010.account.sip.domain;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.sip.domain.CredentialListMapping;

public class CredentialListMappingCreator extends Creator<CredentialListMapping> {
    private final String accountSid;
    private final String domainSid;
    private final String credentialListSid;

    /**
     * Construct a new CredentialListMappingCreator.
     * 
     * @param accountSid The account_sid
     * @param domainSid The domain_sid
     * @param credentialListSid The credential_list_sid
     */
    public CredentialListMappingCreator(final String accountSid, final String domainSid, final String credentialListSid) {
        this.accountSid = accountSid;
        this.domainSid = domainSid;
        this.credentialListSid = credentialListSid;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created CredentialListMapping
     */
    @Override
    public CredentialListMapping execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/SIP/Domains/" + this.domainSid + "/CredentialListMappings.json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("CredentialListMapping creation failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_CREATED) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return CredentialListMapping.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (credentialListSid != null) {
            request.addPostParam("CredentialListSid", credentialListSid);
        }
    }
}