package com.twilio.sdk.resources.taskrouter.v1.workspace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.converters.MarshalConverter;
import com.twilio.sdk.creators.taskrouter.v1.workspace.TaskQueueCreator;
import com.twilio.sdk.deleters.taskrouter.v1.workspace.TaskQueueDeleter;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.taskrouter.v1.workspace.TaskQueueFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.taskrouter.v1.workspace.TaskQueueReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.sdk.updaters.taskrouter.v1.workspace.TaskQueueUpdater;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskQueue extends SidResource {
    private static final long serialVersionUID = 237451693529850L;

    /**
     * Create a TaskQueueFetcher to execute fetch.
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return TaskQueueFetcher capable of executing the fetch
     */
    public static TaskQueueFetcher fetch(final String workspaceSid, 
                                         final String sid) {
        return new TaskQueueFetcher(workspaceSid, sid);
    }

    /**
     * Create a TaskQueueUpdater to execute update.
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return TaskQueueUpdater capable of executing the update
     */
    public static TaskQueueUpdater update(final String workspaceSid, 
                                          final String sid) {
        return new TaskQueueUpdater(workspaceSid, sid);
    }

    /**
     * Create a TaskQueueReader to execute read.
     * 
     * @param workspaceSid The workspace_sid
     * @return TaskQueueReader capable of executing the read
     */
    public static TaskQueueReader read(final String workspaceSid) {
        return new TaskQueueReader(workspaceSid);
    }

    /**
     * Create a TaskQueueCreator to execute create.
     * 
     * @param workspaceSid The workspace_sid
     * @param friendlyName The friendly_name
     * @param reservationActivitySid The reservation_activity_sid
     * @param assignmentActivitySid The assignment_activity_sid
     * @return TaskQueueCreator capable of executing the create
     */
    public static TaskQueueCreator create(final String workspaceSid, 
                                          final String friendlyName, 
                                          final String reservationActivitySid, 
                                          final String assignmentActivitySid) {
        return new TaskQueueCreator(workspaceSid, friendlyName, reservationActivitySid, assignmentActivitySid);
    }

    /**
     * Create a TaskQueueDeleter to execute delete.
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return TaskQueueDeleter capable of executing the delete
     */
    public static TaskQueueDeleter delete(final String workspaceSid, 
                                          final String sid) {
        return new TaskQueueDeleter(workspaceSid, sid);
    }

    /**
     * Converts a JSON String into a TaskQueue object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return TaskQueue object represented by the provided JSON
     */
    public static TaskQueue fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a TaskQueue object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return TaskQueue object represented by the provided JSON
     */
    public static TaskQueue fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskQueue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String assignmentActivitySid;
    private final String assignmentActivityName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final Integer maxReservedWorkers;
    private final String reservationActivitySid;
    private final String reservationActivityName;
    private final String sid;
    private final String targetWorkers;
    private final URI url;
    private final String workspaceSid;

    @JsonCreator
    private TaskQueue(@JsonProperty("account_sid")
                      final String accountSid, 
                      @JsonProperty("assignment_activity_sid")
                      final String assignmentActivitySid, 
                      @JsonProperty("assignment_activity_name")
                      final String assignmentActivityName, 
                      @JsonProperty("date_created")
                      final String dateCreated, 
                      @JsonProperty("date_updated")
                      final String dateUpdated, 
                      @JsonProperty("friendly_name")
                      final String friendlyName, 
                      @JsonProperty("max_reserved_workers")
                      final Integer maxReservedWorkers, 
                      @JsonProperty("reservation_activity_sid")
                      final String reservationActivitySid, 
                      @JsonProperty("reservation_activity_name")
                      final String reservationActivityName, 
                      @JsonProperty("sid")
                      final String sid, 
                      @JsonProperty("target_workers")
                      final String targetWorkers, 
                      @JsonProperty("url")
                      final URI url, 
                      @JsonProperty("workspace_sid")
                      final String workspaceSid) {
        this.accountSid = accountSid;
        this.assignmentActivitySid = assignmentActivitySid;
        this.assignmentActivityName = assignmentActivityName;
        this.dateCreated = MarshalConverter.dateTimeFromString(dateCreated);
        this.dateUpdated = MarshalConverter.dateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.maxReservedWorkers = maxReservedWorkers;
        this.reservationActivitySid = reservationActivitySid;
        this.reservationActivityName = reservationActivityName;
        this.sid = sid;
        this.targetWorkers = targetWorkers;
        this.url = url;
        this.workspaceSid = workspaceSid;
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
     * Returns The The assignment_activity_sid.
     * 
     * @return The assignment_activity_sid
     */
    public final String getAssignmentActivitySid() {
        return this.assignmentActivitySid;
    }

    /**
     * Returns The The assignment_activity_name.
     * 
     * @return The assignment_activity_name
     */
    public final String getAssignmentActivityName() {
        return this.assignmentActivityName;
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
     * Returns The The friendly_name.
     * 
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The The max_reserved_workers.
     * 
     * @return The max_reserved_workers
     */
    public final Integer getMaxReservedWorkers() {
        return this.maxReservedWorkers;
    }

    /**
     * Returns The The reservation_activity_sid.
     * 
     * @return The reservation_activity_sid
     */
    public final String getReservationActivitySid() {
        return this.reservationActivitySid;
    }

    /**
     * Returns The The reservation_activity_name.
     * 
     * @return The reservation_activity_name
     */
    public final String getReservationActivityName() {
        return this.reservationActivityName;
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
     * Returns The The target_workers.
     * 
     * @return The target_workers
     */
    public final String getTargetWorkers() {
        return this.targetWorkers;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The workspace_sid.
     * 
     * @return The workspace_sid
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        TaskQueue other = (TaskQueue) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(assignmentActivitySid, other.assignmentActivitySid) && 
               Objects.equals(assignmentActivityName, other.assignmentActivityName) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(maxReservedWorkers, other.maxReservedWorkers) && 
               Objects.equals(reservationActivitySid, other.reservationActivitySid) && 
               Objects.equals(reservationActivityName, other.reservationActivityName) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(targetWorkers, other.targetWorkers) && 
               Objects.equals(url, other.url) && 
               Objects.equals(workspaceSid, other.workspaceSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            assignmentActivitySid,
                            assignmentActivityName,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            maxReservedWorkers,
                            reservationActivitySid,
                            reservationActivityName,
                            sid,
                            targetWorkers,
                            url,
                            workspaceSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("assignmentActivitySid", assignmentActivitySid)
                          .add("assignmentActivityName", assignmentActivityName)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("maxReservedWorkers", maxReservedWorkers)
                          .add("reservationActivitySid", reservationActivitySid)
                          .add("reservationActivityName", reservationActivityName)
                          .add("sid", sid)
                          .add("targetWorkers", targetWorkers)
                          .add("url", url)
                          .add("workspaceSid", workspaceSid)
                          .toString();
    }
}