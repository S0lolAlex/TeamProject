package com.example.DevTeamProject_Notes.note;

import com.example.DevTeamProject_Notes.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @Size(min = 5, max = 100, message = "Size must be between 5 and 100 characters")
    private String title;

    @Column
    @Size(min = 5, max = 10000, message = "Size must be between 5 and 10000 characters")
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getAuthor() {
        return user == null ? "User not found" : user.getLogin();
    }
}
