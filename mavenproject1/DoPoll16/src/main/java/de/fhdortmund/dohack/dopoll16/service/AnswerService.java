/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.service;

import de.fhdortmund.dohack.dopoll16.db.entity.Answer;
import de.fhdortmund.dohack.dopoll16.db.repository.AnswerRepository;
import de.fhdortmund.dohack.dopoll16.web.dto.AnswerCreateDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.AnswerDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastian
 */
@Service
public class AnswerService {
    
    @Autowired
    private AnswerRepository answerRepository;
    
    public AnswerDTO create(AnswerCreateDTO answerCreateDTO){
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerCreateDTO, answer);
        
        answerRepository.save(answer);
        
        AnswerDTO answerDTO = new AnswerDTO();
        BeanUtils.copyProperties(answer, answerDTO);
        
        return answerDTO;
    }
    
    public AnswerDTO getById(int id){
        Answer answer = answerRepository.getOne(id);
        AnswerDTO answerDTO = new AnswerDTO();
        BeanUtils.copyProperties(answer, answerDTO);
        return answerDTO;
    }
    
    public List<AnswerDTO> getAll(){
        List<Answer> liste;
        
        List<AnswerDTO> listeDTO = new ArrayList<AnswerDTO>();
        AnswerDTO answerDTO = null;
        
        liste = answerRepository.findAll();
        for(Answer answer : liste){
            answerDTO = new AnswerDTO();
            BeanUtils.copyProperties(answer, answerDTO);
            listeDTO.add(answerDTO);
        }
        return listeDTO;
    }
}
