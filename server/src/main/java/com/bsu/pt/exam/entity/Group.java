package com.bsu.pt.exam.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sudent_group")
@Getter
@Setter
//Группа - название, список студентов, староста
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String groupName;
    // @NotEmpty
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "group",
            fetch = FetchType.EAGER
    )
    private List<Student> students = new ArrayList<>();
    @OneToOne
    private Student groupLeader;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "group",
            fetch = FetchType.EAGER
    )
    private List<Event> events=new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
