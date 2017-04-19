/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.entity;

import java.sql.Date;

/**
 *
 * @author Zeppelin
 */
public class Event {
    private int id;
    private String title, description, venue;
    private Date startDate, endDate;
    private boolean inviteStatus;
    private User organizer;

    public Event() {
    }

    public Event(int id, String title, String description, User organizer, String venue, Date startDate, Date endDate, boolean inviteStatus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.organizer = organizer;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inviteStatus = inviteStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(boolean inviteStatus) {
        this.inviteStatus = inviteStatus;
    }
    
}
