package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.exception.FileNotSavedException;
import com.springboot.star_wars_character_popularity.app.model.Vote;
import com.springboot.star_wars_character_popularity.app.model.VoteFiles;
import com.springboot.star_wars_character_popularity.app.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    public VoteController(VoteService voteService){
        this.voteService = voteService;
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

    @RequestMapping("/voteView")
    public ModelAndView uploadPage(Model model){
        List<Vote> votes = new ArrayList<>();
        Vote vote = new Vote();

        model.addAttribute("votes", votes);
        model.addAttribute("vote", vote);
        model.addAttribute("isAdd", true);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("votes/vote-view");

        return modelAndView;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView saveVote(@ModelAttribute Vote vote, RedirectAttributes redirectAttributes, Model model, @RequestPart(value="image", required = false) MultipartFile multipartFile) throws IOException, FileNotSavedException {
       Vote dbVote = voteService.saveVote(vote, multipartFile);

       ModelAndView modelAndView = new ModelAndView();

       if(dbVote != null){
           redirectAttributes.addFlashAttribute("successmessage", "Vote is saved successfully !");

           modelAndView.setViewName("redirect:/api/vote/getAllVotes");

           return modelAndView;
       }
       else{
           model.addAttribute("errormessage","Vote is not saved. Please try again.");
           model.addAttribute("vote", vote);

           modelAndView.setViewName("redirect:/api/vote/voteView");

           return modelAndView;
       }
    }

    @GetMapping("/getAllVotes")
    public ModelAndView getAllVotes(Model model){
        List<Vote> votes = voteService.getAllVotes();

        model.addAttribute("votes", votes);
        model.addAttribute("vote", new Vote());
        model.addAttribute("votefiles", new ArrayList<VoteFiles>());
        model.addAttribute("isAdd", true);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("votes/vote-view");

        return modelAndView;
    }
}