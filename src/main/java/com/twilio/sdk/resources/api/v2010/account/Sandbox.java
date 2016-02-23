package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.SandboxFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Resource;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.updaters.api.v2010.account.SandboxUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sandbox extends Resource {
    private static final long serialVersionUID = 227479628535986L;

    /**
     * Create a SandboxFetcher to execute fetch.
     * 
     * @param accountSid The account_sid
     * @return SandboxFetcher capable of executing the fetch
     */
    public static SandboxFetcher fetch(final String accountSid) {
        return new SandboxFetcher(accountSid);
    }

    /**
     * Create a SandboxUpdater to execute update.
     * 
     * @param accountSid The account_sid
     * @return SandboxUpdater capable of executing the update
     */
    public static SandboxUpdater update(final String accountSid) {
        return new SandboxUpdater(accountSid);
    }

    /**
     * Converts a JSON String into a Sandbox object using the provided ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Sandbox object represented by the provided JSON
     */
    public static Sandbox fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Sandbox.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Sandbox object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Sandbox object represented by the provided JSON
     */
    public static Sandbox fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Sandbox.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final Integer pin;
    private final String accountSid;
    private final com.twilio.types.PhoneNumber phoneNumber;
    private final String applicationSid;
    private final String apiVersion;
    private final URI voiceUrl;
    private final HttpMethod voiceMethod;
    private final URI smsUrl;
    private final HttpMethod smsMethod;
    private final URI statusCallback;
    private final HttpMethod statusCallbackMethod;
    private final URI uri;

    @JsonCreator
    private Sandbox(@JsonProperty("date_created")
                    final String dateCreated, 
                    @JsonProperty("date_updated")
                    final String dateUpdated, 
                    @JsonProperty("pin")
                    final Integer pin, 
                    @JsonProperty("account_sid")
                    final String accountSid, 
                    @JsonProperty("phone_number")
                    final com.twilio.types.PhoneNumber phoneNumber, 
                    @JsonProperty("application_sid")
                    final String applicationSid, 
                    @JsonProperty("api_version")
                    final String apiVersion, 
                    @JsonProperty("voice_url")
                    final URI voiceUrl, 
                    @JsonProperty("voice_method")
                    final HttpMethod voiceMethod, 
                    @JsonProperty("sms_url")
                    final URI smsUrl, 
                    @JsonProperty("sms_method")
                    final HttpMethod smsMethod, 
                    @JsonProperty("status_callback")
                    final URI statusCallback, 
                    @JsonProperty("status_callback_method")
                    final HttpMethod statusCallbackMethod, 
                    @JsonProperty("uri")
                    final URI uri) {
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.pin = pin;
        this.accountSid = accountSid;
        this.phoneNumber = phoneNumber;
        this.applicationSid = applicationSid;
        this.apiVersion = apiVersion;
        this.voiceUrl = voiceUrl;
        this.voiceMethod = voiceMethod;
        this.smsUrl = smsUrl;
        this.smsMethod = smsMethod;
        this.statusCallback = statusCallback;
        this.statusCallbackMethod = statusCallbackMethod;
        this.uri = uri;
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
     * Returns The The pin.
     * 
     * @return The pin
     */
    public final Integer getPin() {
        return this.pin;
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
     * Returns The The phone_number.
     * 
     * @return The phone_number
     */
    public final com.twilio.types.PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns The The application_sid.
     * 
     * @return The application_sid
     */
    public final String getApplicationSid() {
        return this.applicationSid;
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
     * Returns The The voice_url.
     * 
     * @return The voice_url
     */
    public final URI getVoiceUrl() {
        return this.voiceUrl;
    }

    /**
     * Returns The The voice_method.
     * 
     * @return The voice_method
     */
    public final HttpMethod getVoiceMethod() {
        return this.voiceMethod;
    }

    /**
     * Returns The The sms_url.
     * 
     * @return The sms_url
     */
    public final URI getSmsUrl() {
        return this.smsUrl;
    }

    /**
     * Returns The The sms_method.
     * 
     * @return The sms_method
     */
    public final HttpMethod getSmsMethod() {
        return this.smsMethod;
    }

    /**
     * Returns The The status_callback.
     * 
     * @return The status_callback
     */
    public final URI getStatusCallback() {
        return this.statusCallback;
    }

    /**
     * Returns The The status_callback_method.
     * 
     * @return The status_callback_method
     */
    public final HttpMethod getStatusCallbackMethod() {
        return this.statusCallbackMethod;
    }

    /**
     * Returns The The uri.
     * 
     * @return The uri
     */
    public final URI getUri() {
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
        
        Sandbox other = (Sandbox) o;
        
        return Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(pin, other.pin) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(phoneNumber, other.phoneNumber) && 
               Objects.equals(applicationSid, other.applicationSid) && 
               Objects.equals(apiVersion, other.apiVersion) && 
               Objects.equals(voiceUrl, other.voiceUrl) && 
               Objects.equals(voiceMethod, other.voiceMethod) && 
               Objects.equals(smsUrl, other.smsUrl) && 
               Objects.equals(smsMethod, other.smsMethod) && 
               Objects.equals(statusCallback, other.statusCallback) && 
               Objects.equals(statusCallbackMethod, other.statusCallbackMethod) && 
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCreated,
                            dateUpdated,
                            pin,
                            accountSid,
                            phoneNumber,
                            applicationSid,
                            apiVersion,
                            voiceUrl,
                            voiceMethod,
                            smsUrl,
                            smsMethod,
                            statusCallback,
                            statusCallbackMethod,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("pin", pin)
                          .add("accountSid", accountSid)
                          .add("phoneNumber", phoneNumber)
                          .add("applicationSid", applicationSid)
                          .add("apiVersion", apiVersion)
                          .add("voiceUrl", voiceUrl)
                          .add("voiceMethod", voiceMethod)
                          .add("smsUrl", smsUrl)
                          .add("smsMethod", smsMethod)
                          .add("statusCallback", statusCallback)
                          .add("statusCallbackMethod", statusCallbackMethod)
                          .add("uri", uri)
                          .toString();
    }
}