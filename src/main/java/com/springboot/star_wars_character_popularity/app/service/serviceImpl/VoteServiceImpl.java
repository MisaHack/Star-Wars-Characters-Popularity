package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.VoteModel;
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
    public VoteModel saveVote(VoteModel vote) {
        return voteRepository.save(vote);
    }

    @Override
    public List<VoteModel> getAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public VoteModel getVoteById(long id) {

        Optional<VoteModel> vote = voteRepository.findById(id);

        if(vote.isPresent()){
           return vote.get();
        }
        else{
            throw new ResourceNotFoundException("Vote","id",id);
        }
    }

    @Override
    public VoteModel updateVote(VoteModel vote, long id) {

        VoteModel existingVote = voteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote","id",id));

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
