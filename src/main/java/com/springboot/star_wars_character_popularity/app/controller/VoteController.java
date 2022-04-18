package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.Vote;
import com.springboot.star_wars_character_popularity.app.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Vote> saveVote(@RequestBody Vote vote){
        return new ResponseEntity<Vote>(voteService.saveVote(vote), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Vote> getAllVotes(){
        return voteService.getAllVotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable("id") long vote_id){
       return new ResponseEntity<Vote>(voteService.getVoteById(vote_id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable("id") long id, @RequestBody Vote vote){
        return new ResponseEntity<Vote>(voteService.updateVote(vote,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVote(@PathVariable("id") long id){

        voteService.deleteVote(id);

        return new ResponseEntity<String>("Vote deleted successfully !", HttpStatus.OK);
    }
}
