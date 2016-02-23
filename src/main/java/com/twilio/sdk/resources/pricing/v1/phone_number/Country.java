package com.twilio.sdk.resources.pricing.v1.phone_number;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.pricing.v1.phone_number.CountryFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.pricing.v1.phone_number.CountryReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.types.PhoneNumberPrice;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country extends SidResource {
    private static final long serialVersionUID = 157533851169375L;

    /**
     * Create a CountryReader to execute read.
     * 
     * @return CountryReader capable of executing the read
     */
    public static CountryReader read() {
        return new CountryReader();
    }

    /**
     * Create a CountryFetcher to execute fetch.
     * 
     * @param isoCountry The iso_country
     * @return CountryFetcher capable of executing the fetch
     */
    public static CountryFetcher fetch(final String isoCountry) {
        return new CountryFetcher(isoCountry);
    }

    /**
     * Converts a JSON String into a Country object using the provided ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Country object represented by the provided JSON
     */
    public static Country fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Country.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Country object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Country object represented by the provided JSON
     */
    public static Country fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Country.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String country;
    private final String isoCountry;
    private final List<PhoneNumberPrice> phoneNumberPrices;
    private final Currency priceUnit;
    private final URI uri;
    private final URI url;

    @JsonCreator
    private Country(@JsonProperty("country")
                    final String country, 
                    @JsonProperty("iso_country")
                    final String isoCountry, 
                    @JsonProperty("phone_number_prices")
                    final List<PhoneNumberPrice> phoneNumberPrices, 
                    @JsonProperty("price_unit")
                    @JsonDeserialize(using = com.twilio.sdk.converters.CurrencyDeserializer.class)
                    final Currency priceUnit, 
                    @JsonProperty("uri")
                    final URI uri, 
                    @JsonProperty("url")
                    final URI url) {
        this.country = country;
        this.isoCountry = isoCountry;
        this.phoneNumberPrices = phoneNumberPrices;
        this.priceUnit = priceUnit;
        this.uri = uri;
        this.url = url;
    }

    /**
     * Returns The The iso_country.
     * 
     * @return The iso_country
     */
    public final String getSid() {
        return this.getIsoCountry();
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
     * Returns The The iso_country.
     * 
     * @return The iso_country
     */
    public final String getIsoCountry() {
        return this.isoCountry;
    }

    /**
     * Returns The The phone_number_prices.
     * 
     * @return The phone_number_prices
     */
    public final List<PhoneNumberPrice> getPhoneNumberPrices() {
        return this.phoneNumberPrices;
    }

    /**
     * Returns The The price_unit.
     * 
     * @return The price_unit
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
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
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Country other = (Country) o;
        
        return Objects.equals(country, other.country) && 
               Objects.equals(isoCountry, other.isoCountry) && 
               Objects.equals(phoneNumberPrices, other.phoneNumberPrices) && 
               Objects.equals(priceUnit, other.priceUnit) && 
               Objects.equals(uri, other.uri) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country,
                            isoCountry,
                            phoneNumberPrices,
                            priceUnit,
                            uri,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("country", country)
                          .add("isoCountry", isoCountry)
                          .add("phoneNumberPrices", phoneNumberPrices)
                          .add("priceUnit", priceUnit)
                          .add("uri", uri)
                          .add("url", url)
                          .toString();
    }
}