package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.Vote;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VoteService {
   Vote saveVote(Vote vote, MultipartFile multipartFile) throws IOException;
   List<Vote> getAllVotes();
   Vote getVoteById(long id);
   Vote updateVote(Vote vote, long id);
   void deleteVote(long id);
}
