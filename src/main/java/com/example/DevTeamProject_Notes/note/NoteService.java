package com.example.DevTeamProject_Notes.note;

import com.example.DevTeamProject_Notes.user.User;
import com.example.DevTeamProject_Notes.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public Page<Note> findPaginated(int pageNumber, int pageSize, long userId) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        return noteRepository.findByUserId(String.valueOf(userId), pageable);
    }

    public List<Note> noteList() {
        return noteRepository.findAll();
    }

    public void save(String login, Note note) {
        User loggedUser = userRepository.findByLogin(login);
        note.setUser(loggedUser);
        noteRepository.save(note);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public Note getById(UUID id) {
        return noteRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        noteRepository.deleteById(id);
    }

    public void copyLink(String url) {
        StringSelection stringSelection = new StringSelection(url);
        System.setProperty("java.awt.headless", "false");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

}

