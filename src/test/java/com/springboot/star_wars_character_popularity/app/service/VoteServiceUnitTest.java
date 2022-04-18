package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.Vote;
import com.springboot.star_wars_character_popularity.app.repository.VoteRepository;
import com.springboot.star_wars_character_popularity.app.service.serviceImpl.VoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VoteServiceUnitTest {

    private VoteServiceImpl voteService;

    private VoteRepository voteRepositoryMock;

    @BeforeEach
    void setup(){
        this.voteRepositoryMock = mock(VoteRepository.class);

        this.voteService = new VoteServiceImpl(voteRepositoryMock);
    }

    @Test
    void shouldReturnSavedVote(){
        //given
        Vote voteModel = new Vote(2, "Comment #1");

        when(this.voteRepositoryMock.save(voteModel)).thenReturn(new Vote(2, "Comment #1"));

        Vote expectedVote = new Vote(2, "Comment #1");

        //when
        Vote actualModel = voteService.saveVote(voteModel);

        //then
        assertNotNull(actualModel);
        assertEquals(2, actualModel.getCount());
        assertEquals("Comment #1", actualModel.getComment());
        assertEquals(actualModel, expectedVote);
        verify(voteRepositoryMock).save(voteModel);
    }

    @Test
    void shouldReturnAllSavedVotes(){
        //given
        Vote voteModel1 = new Vote(1, "Comment #1");
        Vote voteModel2 = new Vote(2, "Comment #2");
        Vote voteModel3 = new Vote(3, "Comment #3");

        List<Vote> votes = new ArrayList<>();
        votes.add(voteModel1);
        votes.add(voteModel2);
        votes.add(voteModel3);

        when(this.voteRepositoryMock.findAll()).thenReturn(votes);

        //when
        List<Vote> actual = voteService.getAllVotes();

        //then
        assertEquals(actual, votes);
        assertEquals(actual.get(0), voteModel1);
        assertEquals(actual.get(1), voteModel2);
        assertEquals(actual.get(2), voteModel3);
        verify(voteRepositoryMock).findAll();
    }

    @Test
    void shouldReturnSavedVoteById(){
        //given
        Vote voteModel = new Vote(1, "Comment #1");

        long id = 1;

        Optional<Vote> voteOptional = Optional.of(new Vote(1, "Comment #1"));

        when(this.voteRepositoryMock.findById(id)).thenReturn(voteOptional);

        //when
        Vote actualModel = voteService.getVoteById(1);

        //then
        assertEquals(actualModel, voteModel);
        verify(voteRepositoryMock).findById(id);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenNoVoteAvailable(){
        //given
        long id = 1;

        when(this.voteRepositoryMock.findById(id)).thenThrow(ResourceNotFoundException.class);

        //when
        Executable executable = () -> voteService.getVoteById(1);

        //then
        assertThrows(ResourceNotFoundException.class, executable);
    }


}
