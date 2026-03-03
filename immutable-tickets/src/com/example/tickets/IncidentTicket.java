package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final TicketPriorityEnum priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // mutable leak
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority != null ? TicketPriorityEnum.valueOf(b.priority) : null;
        this.tags = b.tags != null ? new ArrayList<>(b.tags) : new ArrayList<>();
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority != null ? priority.name() : null; }
    public List<String> getTags() { return new ArrayList<>(tags); }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    static class Builder {
        String id;
        String reporterEmail;
        String title;
        String description;
        String priority;
        List<String> tags;
        String assigneeEmail;
        boolean customerVisible;
        Integer slaMinutes;
        String source;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }


        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = new ArrayList<>(tags);
            return this;
        }

        public Builder assigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        }

        public Builder slaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }


        public IncidentTicket build() {

            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireTitle(title);
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL", null);
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            return new IncidentTicket(this);
        }

        public Builder from(IncidentTicket t) {
            return new Builder()
                    .id(t.getId())
                    .reporterEmail(t.getReporterEmail())
                    .title(t.getTitle())
                    .description(t.getDescription())
                    .priority(t.getPriority())
                    .tags(t.getTags())
                    .assigneeEmail(t.getAssigneeEmail())
                    .customerVisible(t.isCustomerVisible())
                    .slaMinutes(t.getSlaMinutes())
                    .source(t.getSource());
        }

    }
}













//1. IncidentTicket Class (The Fortress)
//You transformed this from a simple POJO into a truly immutable record.
//
//        Immutability: You marked all fields as final. Once a ticket is built, it can never be changed.
//
//Enforced Encapsulation: You removed all public constructors and all set methods. The only way to get a ticket is through the Builder.
//
//Defensive Copying: In getTags(), you now return new ArrayList<>(tags). In the older version, you leaked the internal list, allowing anyone to call .clear() or .add() on a "read-only" object.
//
//Type Safety: You switched the internal priority field from a String to the TicketPriorityEnum.
//
//        2. The Builder Pattern (The Gatekeeper)
//You added a static nested Builder class to handle the complex construction logic.
//
//Fluent API: Methods like .id().reporterEmail().build() allow for readable, flexible object creation.
//
//Centralized Validation: This is the biggest win. You moved validation logic out of the Service and into the build() method. Now, it is impossible to create an IncidentTicket that violates your business rules.
//
//The from() Method: Since the ticket is immutable, you can't "edit" it. You added a from(IncidentTicket t) method that clones an existing ticket into a new builder, allowing for "mutations" by creating a fresh copy.
//
//        3. TicketService (The Coordinator)
//You refactored the service to respect immutability.
//
//No Side Effects: In the older version, escalateToCritical and assign were void—they changed the object behind the scenes. Now, they return a new instance of IncidentTicket.
//
//Clean Logic: The service no longer needs to perform manual null checks or regex validation; it trusts the Builder to throw an exception if the data is bad.





/**
 * IncidentTicket (The Data Model)
 * Previously: It was a "POJO" (Plain Old Java Object) with a massive leak. It had multiple overlapping constructors and public setters. You could change the id or title at any time after the ticket was created, which is dangerous for audit logs or database consistency.
 *
 * Now: It is an Immutable Record. By making fields final and removing setters, the object is now thread-safe and "read-only." It also gained a Builder—a dedicated "factory" that handles the complexity of creating a ticket with many optional fields without needing 10 different constructors.
 *
 * TicketService (The Business Logic)
 * Previously: It acted like a "mechanic" constantly modifying a car while it was driving. It would create a blank ticket and then call .setPriority() or .setSource() multiple times. If an error happened halfway through, you’d be left with a "half-baked," invalid ticket object.
 *
 * Now: It acts like a Functional Coordinator. Instead of changing an existing ticket, it uses the Builder to produce a brand-new version. Methods like escalateToCritical now return a new ticket instance. This ensures that the original state is preserved (great for "undo" features or history tracking).
 *
 * Validation (The Guard)
 * Previously: It existed but was barely used. The TicketService was doing its own manual, "scattered" validation (like checking for @ symbols), which meant you had to remember to write validation code every time you created a ticket.
 *
 * Now: It is the Single Source of Truth. The Builder calls these validation methods inside the .build() step. This means the rest of your app can "trust" an IncidentTicket object; if the object exists, you know for a fact it passed all the regex and range checks.*/