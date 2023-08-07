package com.example.DevTeamProject_Notes.user;

import com.example.DevTeamProject_Notes.note.Note;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Size(min = 5, max = 50, message = "Size must be between 5 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login can contain only numbers and latin symbols")
    private String login;

    @Column
    @Size(min = 8, max = 100, message = "Size must be between 8 and 100 characters")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;
}