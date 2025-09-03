package com.wipro.sindhu.hms.dto;

public class EventMessage {
    private String entity;
    private String action;
    private String details;

    public EventMessage() {} // no-arg

    public EventMessage(String entity, String action, String details) { // all-args
        this.entity = entity;
        this.action = action;
        this.details = details;
    }

    // getters & setters
    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
