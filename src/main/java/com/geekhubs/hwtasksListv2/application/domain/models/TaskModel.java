package com.geekhubs.hwtasksListv2.application.domain.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TaskModel {
    private Long id;
    private String title;
    private String description;
    private String createdAt;
    private UserModel user;
    private StatusModel status;
    private boolean enabled;

    public TaskModel() {}

    public TaskModel(Long id, String title, String description, String createdAt, UserModel user, StatusModel status, boolean enabled) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.user = user;
        this.status = status;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
