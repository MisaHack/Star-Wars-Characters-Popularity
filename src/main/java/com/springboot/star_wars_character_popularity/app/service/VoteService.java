package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.VoteModel;

import java.util.List;

public interface VoteService {
   VoteModel saveVote(VoteModel vote);
   List<VoteModel> getAllVotes();
   VoteModel getVoteById(long id);
   VoteModel updateVote(VoteModel vote, long id);
   void deleteVote(long id);
}
