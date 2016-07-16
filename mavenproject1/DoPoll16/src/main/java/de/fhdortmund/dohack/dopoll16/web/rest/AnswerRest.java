/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.web.rest;

import de.fhdortmund.dohack.dopoll16.service.AnswerService;
import de.fhdortmund.dohack.dopoll16.web.dto.AnswerCreateDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.AnswerDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.PollDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sebastian
 */
public class AnswerRest {
     @Autowired
    private AnswerService answerService;
    
    @RequestMapping(value = "/createAnswer", method = RequestMethod.POST)
    public AnswerDTO create(@RequestBody AnswerCreateDTO answerCreateDTO){
        return answerService.create(answerCreateDTO);
    }
    
    @RequestMapping(value = "/getAllAnswer", method = {RequestMethod.GET, RequestMethod.DELETE})
    public List<AnswerDTO> getAll(){
        return answerService.getAll();
    }
    
    @RequestMapping(value = "/getByIdAnswer/{id}", method = RequestMethod.GET)
    public AnswerDTO getById(@PathVariable(value =  "id") int id){
        return answerService.getById(id);
    }
}
