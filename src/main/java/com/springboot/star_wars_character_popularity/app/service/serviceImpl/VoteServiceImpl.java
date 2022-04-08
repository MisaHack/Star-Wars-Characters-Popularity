package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.Vote;
import com.springboot.star_wars_character_popularity.app.repository.VoteRepository;
import com.springboot.star_wars_character_popularity.app.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public Vote getVoteById(long id) {

        Optional<Vote> vote = voteRepository.findById(id);

        if(vote.isPresent()){
           return vote.get();
        }
        else{
            throw new ResourceNotFoundException("Vote","id",id);
        }
    }

    @Override
    public Vote updateVote(Vote vote, long id) {

        Vote existingVote = voteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote","id",id));

        existingVote.setComment(vote.getComment());

        voteRepository.save(existingVote);

        return existingVote;
    }

    @Override
    public void deleteVote(long id) {

        voteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote","id",id));

        voteRepository.deleteById(id);
    }
}
