package com.example.DevTeamProject_Notes.note;

import com.example.DevTeamProject_Notes.user.User;
import com.example.DevTeamProject_Notes.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NoteServiceTest {

    @Autowired
    private NoteService noteService;
    @MockBean
    private NoteRepository noteRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    void findPaginated() {
        int pageNumber = 1;
        int pageSize = 10;
        long userId = 1L;

        Note note = getNote(userId);
        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        when(noteRepository.findByUserId(String.valueOf(userId), pageable))
                .thenReturn(new PageImpl<>(List.of(note)));

        var notes = noteService.findPaginated(pageNumber, pageSize, userId);

        assertNotNull(notes);
        assertEquals(notes.get().toList(), List.of(note));
    }

    @Test
    void noteList() {
        List<Note> noteList = List.of(getNote(1L));
        when(noteRepository.findAll())
                .thenReturn(noteList);
        List<Note> notes = noteService.noteList();

        assertEquals(noteList, notes);
    }

    @Test
    void save() {
        Note note = getNote(1L);
        String login = "user";
        when(userRepository.findByLogin(login))
                .thenReturn(note.getUser());
        when(noteRepository.save(note))
                .thenReturn(note);
        noteService.save(login, note);

        verify(userRepository, times(1)).findByLogin(login);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void getById() {
        UUID id = UUID.randomUUID();
        when(noteRepository.findById(id))
                .thenReturn(Optional.of(getNote(1L)));
        Note noteById = noteService.getById(id);

        assertNotNull(noteById);
        assertEquals(noteById, getNote(1L));
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        Note note = getNote(1L);
        doNothing().when(noteRepository).deleteById(id);

        noteService.deleteById(id);
        verify(noteRepository, times(1)).deleteById(id);
    }

    private static Note getNote(long userId) {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Note Content");
        User user = new User();
        user.setId(userId);
        note.setUser(user);
        return note;
    }
}