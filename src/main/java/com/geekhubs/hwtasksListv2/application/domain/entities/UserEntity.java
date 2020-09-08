package com.geekhubs.hwtasksListv2.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private Set<TaskEntity> tasks;

    protected UserEntity() {}

    public UserEntity(Long id, String name, String lastname, String email, boolean enabled) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.enabled = enabled;
        this.tasks = new HashSet<TaskEntity>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TaskEntity> getTasks() {
        return new HashSet<TaskEntity>(this.tasks);
    }

    public TaskEntity getTask(Long taskId) {
        return this.tasks.stream().filter(task -> task.getId() == taskId && task.isEnabled()).findFirst().orElseThrow(RuntimeException::new);
    }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
