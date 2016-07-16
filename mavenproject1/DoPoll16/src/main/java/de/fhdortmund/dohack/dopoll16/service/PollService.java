/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.service;


import de.fhdortmund.dohack.dopoll16.db.entity.Answer;
import de.fhdortmund.dohack.dopoll16.db.entity.Poll;
import de.fhdortmund.dohack.dopoll16.db.repository.AnswerRepository;
import de.fhdortmund.dohack.dopoll16.db.repository.PollRepository;
import de.fhdortmund.dohack.dopoll16.web.dto.AnswerDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.PollCreateDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.PollDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.ResultDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastian
 */
@Service
public class PollService {
    
    @Autowired
    private PollRepository pollRepository;
    
    @Autowired
    private AnswerRepository answerRepository;
    
    public PollDTO create(PollCreateDTO pollCreateDTO){
        Poll poll = new Poll();
        BeanUtils.copyProperties(pollCreateDTO, poll);
        
        pollRepository.save(poll);
        
        PollDTO pollDTO = new PollDTO();
        BeanUtils.copyProperties(poll, pollDTO);
        
        return pollDTO;
    }
    
    public PollDTO getById(int id) {
        Poll poll = pollRepository.getOne(id);
        PollDTO pollDTO = new PollDTO();
        BeanUtils.copyProperties(poll, pollDTO);
        return pollDTO;
    }
    
    public List<PollDTO> getAll(){
        List<Poll> liste;
   //     List<PollCreateDTO> listeCreateDTO = new ArrayList<PollCreateDTO>();
        List<PollDTO> listeDTO = new ArrayList<PollDTO>();
        PollDTO pollDTO = null;
    //    PollCreateDTO pollCreateDTO = null;
       liste = pollRepository.findAll();
        for(Poll i : liste){
            pollDTO = new PollDTO();
            BeanUtils.copyProperties(i, pollDTO);
            listeDTO.add(pollDTO);
        }
        return listeDTO;
    }
    
    public AnswerDTO addAnswer(int id, String answer){
        Answer a = new Answer();
        
        a.setPoll(id);
        a.setAnswer(answer);
        a.setAnsweredAt(new Date());
        answerRepository.save(a);
        AnswerDTO answerDTO = new AnswerDTO();
        BeanUtils.copyProperties(a, answerDTO);
        return answerDTO;
    }
    public ResultDTO getResult(int pollId){
        Poll poll = pollRepository.findOne(pollId);
        PollDTO pollDTO = new PollDTO();
        BeanUtils.copyProperties(poll, pollDTO);
        List<Answer> listeAnswer = answerRepository.findAll();
        List<AnswerDTO> listeAnswerDTO = new ArrayList<AnswerDTO>();
        AnswerDTO answerDTO; 
        for(Answer a : listeAnswer){
            answerDTO = new AnswerDTO();
            BeanUtils.copyProperties(a, answerDTO);
            listeAnswerDTO.add(answerDTO);
        }
        return ResultDTO.createResultDTO(pollDTO, listeAnswerDTO);
    }
    public ResultDTO getProcent(int pollId){
        Poll poll = pollRepository.findOne(pollId);
        PollDTO pollDTO = new PollDTO();
        BeanUtils.copyProperties(poll, pollDTO);
        List<Answer> listeAnswer = answerRepository.findAll();
        List<AnswerDTO> listeAnswerDTO = new ArrayList<AnswerDTO>();
        AnswerDTO answerDTO;
        for(Answer a : listeAnswer){
            answerDTO = new AnswerDTO();
            BeanUtils.copyProperties(a, answerDTO);
            listeAnswerDTO.add(answerDTO);
        }
        return ResultDTO.createResultDTOProcent(pollDTO, listeAnswerDTO);
    }
}
