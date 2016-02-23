package com.twilio.sdk.resources.api.v2010.account.call;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.deleters.api.v2010.account.call.RecordingDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.call.RecordingFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.call.RecordingReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recording extends SidResource {
    private static final long serialVersionUID = 232068402359085L;

    /**
     * Create a RecordingFetcher to execute fetch.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     * @param sid The sid
     * @return RecordingFetcher capable of executing the fetch
     */
    public static RecordingFetcher fetch(final String accountSid, 
                                         final String callSid, 
                                         final String sid) {
        return new RecordingFetcher(accountSid, callSid, sid);
    }

    /**
     * Create a RecordingDeleter to execute delete.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     * @param sid The sid
     * @return RecordingDeleter capable of executing the delete
     */
    public static RecordingDeleter delete(final String accountSid, 
                                          final String callSid, 
                                          final String sid) {
        return new RecordingDeleter(accountSid, callSid, sid);
    }

    /**
     * Create a RecordingReader to execute read.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     * @return RecordingReader capable of executing the read
     */
    public static RecordingReader read(final String accountSid, 
                                       final String callSid) {
        return new RecordingReader(accountSid, callSid);
    }

    /**
     * Converts a JSON String into a Recording object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Recording object represented by the provided JSON
     */
    public static Recording fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Recording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Recording object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Recording object represented by the provided JSON
     */
    public static Recording fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Recording.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String apiVersion;
    private final String callSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String duration;
    private final String sid;
    private final String uri;

    @JsonCreator
    private Recording(@JsonProperty("account_sid")
                      final String accountSid, 
                      @JsonProperty("api_version")
                      final String apiVersion, 
                      @JsonProperty("call_sid")
                      final String callSid, 
                      @JsonProperty("date_created")
                      final String dateCreated, 
                      @JsonProperty("date_updated")
                      final String dateUpdated, 
                      @JsonProperty("duration")
                      final String duration, 
                      @JsonProperty("sid")
                      final String sid, 
                      @JsonProperty("uri")
                      final String uri) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.callSid = callSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.duration = duration;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The api_version.
     * 
     * @return The api_version
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Returns The The call_sid.
     * 
     * @return The call_sid
     */
    public final String getCallSid() {
        return this.callSid;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date_updated.
     * 
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The duration.
     * 
     * @return The duration
     */
    public final String getDuration() {
        return this.duration;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The uri.
     * 
     * @return The uri
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Recording other = (Recording) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(callSid, other.callSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(duration, other.duration) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            apiVersion,
                            callSid,
                            dateCreated,
                            dateUpdated,
                            duration,
                            sid,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("apiVersion", apiVersion)
                          .add("callSid", callSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("duration", duration)
                          .add("sid", sid)
                          .add("uri", uri)
                          .toString();
    }
}