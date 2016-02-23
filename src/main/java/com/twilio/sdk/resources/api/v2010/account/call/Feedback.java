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
import com.twilio.sdk.creators.api.v2010.account.call.FeedbackCreator;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.call.FeedbackFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.updaters.api.v2010.account.call.FeedbackUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback extends Resource {
    private static final long serialVersionUID = 234556362553174L;

    public enum Issues {
        AUDIO_LATENCY("audio-latency"),
        DIGITS_NOT_CAPTURED("digits-not-captured"),
        DROPPED_CALL("dropped-call"),
        IMPERFECT_AUDIO("imperfect-audio"),
        INCORRECT_CALLER_ID("incorrect-caller-id"),
        ONE_WAY_AUDIO("one-way-audio"),
        POST_DIAL_DELAY("post-dial-delay"),
        UNSOLICITED_CALL("unsolicited-call");
    
        private final String value;
        
        private Issues(final String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
        
        @JsonCreator
        public static Issues forValue(final String value) {
            String normalized = value.replace("-", "_").toUpperCase();
            return Issues.valueOf(normalized);
        }
    }

    /**
     * Create a FeedbackCreator to execute create.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     * @param qualityScore The quality_score
     * @return FeedbackCreator capable of executing the create
     */
    public static FeedbackCreator create(final String accountSid, 
                                         final String callSid, 
                                         final Integer qualityScore) {
        return new FeedbackCreator(accountSid, callSid, qualityScore);
    }

    /**
     * Create a FeedbackFetcher to execute fetch.
     * 
     * @param accountSid The account_sid
     * @param callSid The call sid that uniquely identifies the call
     * @return FeedbackFetcher capable of executing the fetch
     */
    public static FeedbackFetcher fetch(final String accountSid, 
                                        final String callSid) {
        return new FeedbackFetcher(accountSid, callSid);
    }

    /**
     * Create a FeedbackUpdater to execute update.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     * @param qualityScore An integer from 1 to 5
     * @return FeedbackUpdater capable of executing the update
     */
    public static FeedbackUpdater update(final String accountSid, 
                                         final String callSid, 
                                         final Integer qualityScore) {
        return new FeedbackUpdater(accountSid, callSid, qualityScore);
    }

    /**
     * Converts a JSON String into a Feedback object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Feedback object represented by the provided JSON
     */
    public static Feedback fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Feedback.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Feedback object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Feedback object represented by the provided JSON
     */
    public static Feedback fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Feedback.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final List<Feedback.Issues> issues;
    private final Integer qualityScore;
    private final String sid;

    @JsonCreator
    private Feedback(@JsonProperty("account_sid")
                     final String accountSid, 
                     @JsonProperty("date_created")
                     final String dateCreated, 
                     @JsonProperty("date_updated")
                     final String dateUpdated, 
                     @JsonProperty("issues")
                     final List<Feedback.Issues> issues, 
                     @JsonProperty("quality_score")
                     final Integer qualityScore, 
                     @JsonProperty("sid")
                     final String sid) {
        this.accountSid = accountSid;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.issues = issues;
        this.qualityScore = qualityScore;
        this.sid = sid;
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
     * Returns The The issues.
     * 
     * @return The issues
     */
    public final List<Feedback.Issues> getIssues() {
        return this.issues;
    }

    /**
     * Returns The 1 to 5 quality score.
     * 
     * @return 1 to 5 quality score
     */
    public final Integer getQualityScore() {
        return this.qualityScore;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Feedback other = (Feedback) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(issues, other.issues) && 
               Objects.equals(qualityScore, other.qualityScore) && 
               Objects.equals(sid, other.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            issues,
                            qualityScore,
                            sid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("issues", issues)
                          .add("qualityScore", qualityScore)
                          .add("sid", sid)
                          .toString();
    }
}