package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.FileNotSavedException;
import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.Vote;
import com.springboot.star_wars_character_popularity.app.model.VoteFiles;
import com.springboot.star_wars_character_popularity.app.repository.VoteFilesRepository;
import com.springboot.star_wars_character_popularity.app.repository.VoteRepository;
import com.springboot.star_wars_character_popularity.app.service.VoteService;
import com.springboot.star_wars_character_popularity.app.util.FileUploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private VoteFilesRepository voteFilesRepository;

    public VoteServiceImpl(VoteRepository voteRepository, VoteFilesRepository voteFilesRepository){
        this.voteRepository = voteRepository;
        this.voteFilesRepository = voteFilesRepository;
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

    @Override
    public Vote saveVote(Vote vote, MultipartFile multipartFile) throws IOException, FileNotSavedException {
       Vote dbVote = voteRepository.save(vote);

       if(dbVote!=null && multipartFile!=null) {

           MultipartFile file = multipartFile;

           String fileName = file.getOriginalFilename();
           String modifiedFileName = FilenameUtils.getBaseName(fileName) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);

           VoteFiles files = new VoteFiles();
           files.setFileExtension(FilenameUtils.getExtension(fileName));
           files.setFileName(fileName);
           files.setModifiedFileName(modifiedFileName);
           files.setVote(dbVote);
           files.setIconPath(System.getProperty("user.dir") + "\\user-photos\\" + modifiedFileName);

           String uploadDir = "user-photos/";
           FileUploadUtil.saveFile(uploadDir, modifiedFileName, file);

           voteFilesRepository.save(files);
       }

       return dbVote;
    }
}