package com.twilio.sdk.resources.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.api.v2010.account.AvailablePhoneNumberCountryFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.api.v2010.account.AvailablePhoneNumberCountryReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailablePhoneNumberCountry extends SidResource {
    private static final long serialVersionUID = 118317602820324L;

    /**
     * Create a AvailablePhoneNumberCountryReader to execute read.
     * 
     * @param accountSid The account_sid
     * @return AvailablePhoneNumberCountryReader capable of executing the read
     */
    public static AvailablePhoneNumberCountryReader read(final String accountSid) {
        return new AvailablePhoneNumberCountryReader(accountSid);
    }

    /**
     * Create a AvailablePhoneNumberCountryFetcher to execute fetch.
     * 
     * @param accountSid The account_sid
     * @param countryCode The country_code
     * @return AvailablePhoneNumberCountryFetcher capable of executing the fetch
     */
    public static AvailablePhoneNumberCountryFetcher fetch(final String accountSid, 
                                                           final String countryCode) {
        return new AvailablePhoneNumberCountryFetcher(accountSid, countryCode);
    }

    /**
     * Converts a JSON String into a AvailablePhoneNumberCountry object using the
     * provided ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return AvailablePhoneNumberCountry object represented by the provided JSON
     */
    public static AvailablePhoneNumberCountry fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AvailablePhoneNumberCountry.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a AvailablePhoneNumberCountry object using
     * the provided ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return AvailablePhoneNumberCountry object represented by the provided JSON
     */
    public static AvailablePhoneNumberCountry fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, AvailablePhoneNumberCountry.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String countryCode;
    private final String country;
    private final URI uri;
    private final Boolean beta;
    private final Map<String, String> subresourceUris;

    @JsonCreator
    private AvailablePhoneNumberCountry(@JsonProperty("country_code")
                                        final String countryCode, 
                                        @JsonProperty("country")
                                        final String country, 
                                        @JsonProperty("uri")
                                        final URI uri, 
                                        @JsonProperty("beta")
                                        final Boolean beta, 
                                        @JsonProperty("subresource_uris")
                                        final Map<String, String> subresourceUris) {
        this.countryCode = countryCode;
        this.country = country;
        this.uri = uri;
        this.beta = beta;
        this.subresourceUris = subresourceUris;
    }

    /**
     * Returns The The country_code.
     * 
     * @return The country_code
     */
    public final String getSid() {
        return this.getCountryCode();
    }

    /**
     * Returns The The country_code.
     * 
     * @return The country_code
     */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Returns The The country.
     * 
     * @return The country
     */
    public final String getCountry() {
        return this.country;
    }

    /**
     * Returns The The uri.
     * 
     * @return The uri
     */
    public final URI getUri() {
        return this.uri;
    }

    /**
     * Returns The The beta.
     * 
     * @return The beta
     */
    public final Boolean getBeta() {
        return this.beta;
    }

    /**
     * Returns The The subresource_uris.
     * 
     * @return The subresource_uris
     */
    public final Map<String, String> getSubresourceUris() {
        return this.subresourceUris;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        AvailablePhoneNumberCountry other = (AvailablePhoneNumberCountry) o;
        
        return Objects.equals(countryCode, other.countryCode) && 
               Objects.equals(country, other.country) && 
               Objects.equals(uri, other.uri) && 
               Objects.equals(beta, other.beta) && 
               Objects.equals(subresourceUris, other.subresourceUris);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode,
                            country,
                            uri,
                            beta,
                            subresourceUris);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("countryCode", countryCode)
                          .add("country", country)
                          .add("uri", uri)
                          .add("beta", beta)
                          .add("subresourceUris", subresourceUris)
                          .toString();
    }
}