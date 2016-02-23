package com.twilio.sdk.creators.api.v2010.account.sms;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.Promoter;
import com.twilio.sdk.creators.Creator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.sms.SmsMessage;

import java.net.URI;
import java.util.List;

public class SmsMessageCreator extends Creator<SmsMessage> {
    private final String accountSid;
    private final com.twilio.types.PhoneNumber to;
    private final com.twilio.types.PhoneNumber from;
    private String body;
    private List<URI> mediaUrl;
    private URI statusCallback;
    private String applicationSid;

    /**
     * Construct a new SmsMessageCreator.
     * 
     * @param accountSid The account_sid
     * @param to The to
     * @param from The from
     * @param body The body
     */
    public SmsMessageCreator(final String accountSid, final com.twilio.types.PhoneNumber to, final com.twilio.types.PhoneNumber from, final String body) {
        this.accountSid = accountSid;
        this.to = to;
        this.from = from;
        this.body = body;
    }

    /**
     * Construct a new SmsMessageCreator.
     * 
     * @param accountSid The account_sid
     * @param to The to
     * @param from The from
     * @param mediaUrl The media_url
     */
    public SmsMessageCreator(final String accountSid, final com.twilio.types.PhoneNumber to, final com.twilio.types.PhoneNumber from, final List<URI> mediaUrl) {
        this.accountSid = accountSid;
        this.to = to;
        this.from = from;
        this.mediaUrl = mediaUrl;
    }

    /**
     * The status_callback.
     * 
     * @param statusCallback The status_callback
     * @return this
     */
    public SmsMessageCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The status_callback.
     * 
     * @param statusCallback The status_callback
     * @return this
     */
    public SmsMessageCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The application_sid.
     * 
     * @param applicationSid The application_sid
     * @return this
     */
    public SmsMessageCreator setApplicationSid(final String applicationSid) {
        this.applicationSid = applicationSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created SmsMessage
     */
    @Override
    public SmsMessage execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/SMS/Messages.json",
            client.getAccountSid()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("SmsMessage creation failed: Unable to connect to server");
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
        
        return SmsMessage.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to.toString());
        }
        
        if (from != null) {
            request.addPostParam("From", from.toString());
        }
        
        if (body != null) {
            request.addPostParam("Body", body);
        }
        
        if (mediaUrl != null) {
            request.addPostParam("MediaUrl", mediaUrl.toString());
        }
        
        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }
        
        if (applicationSid != null) {
            request.addPostParam("ApplicationSid", applicationSid);
        }
    }
}