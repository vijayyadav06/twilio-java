package com.twilio.sdk.readers.api.v2010.account;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.Reader;
import com.twilio.sdk.resources.Page;
import com.twilio.sdk.resources.ResourceSet;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.api.v2010.account.Conference;

public class ConferenceReader extends Reader<Conference> {
    private final String accountSid;
    private String dateCreated;
    private String dateUpdated;
    private String friendlyName;
    private Conference.Status status;

    /**
     * Construct a new ConferenceReader.
     * 
     * @param accountSid The account_sid
     */
    public ConferenceReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show conferences that started on this date, given as YYYY-MM-DD. You can
     * also specify inequality such as DateCreated<=YYYY-MM-DD.
     * 
     * @param dateCreated Filter by date created
     * @return this
     */
    public ConferenceReader byDateCreated(final String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * Only show conferences that were last updated on this date, given as
     * YYYY-MM-DD. You can also specify inequality such as DateUpdated>=YYYY-MM-DD.
     * 
     * @param dateUpdated Filter by date updated
     * @return this
     */
    public ConferenceReader byDateUpdated(final String dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * Only show results who's friendly name exactly matches the string.
     * 
     * @param friendlyName Filter by friendly name
     * @return this
     */
    public ConferenceReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * A string representing the status of the conference. May be `init`,
     * `in-progress`, or `completed`..
     * 
     * @param status The status of the conference
     * @return this
     */
    public ConferenceReader byStatus(final Conference.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Conference ResourceSet
     */
    @Override
    public ResourceSet<Conference> execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API,
            "/2010-04-01/Accounts/" + this.accountSid + "/Conferences.json",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        
        Page<Conference> page = pageForRequest(client, request);
        
        return new ResourceSet<>(this, client, page);
    }

    /**
     * Retrieve the next page from the Twilio API.
     * 
     * @param nextPageUri URI from which to retrieve the next page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Conference> nextPage(final String nextPageUri, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            nextPageUri,
            client.getAccountSid()
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Conference Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    protected Page<Conference> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Conference read failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
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
        
        Page<Conference> result = new Page<>();
        result.deserialize(
            "conferences",
            response.getContent(),
            Conference.class,
            client.getObjectMapper()
        );
        
        return result;
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (dateCreated != null) {
            request.addQueryParam("DateCreated", dateCreated);
        }
        
        if (dateUpdated != null) {
            request.addQueryParam("DateUpdated", dateUpdated);
        }
        
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}