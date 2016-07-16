/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.web.rest;

import de.fhdortmund.dohack.dopoll16.service.PollService;
import de.fhdortmund.dohack.dopoll16.web.dto.AnswerDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.PollCreateDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.PollDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastian
 */
@RestController
@RequestMapping(value = "/rest")
public class PollRest {
    
    @Autowired
    private PollService pollService;
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public PollDTO create(@RequestBody PollCreateDTO pollCreateDTO){
        return pollService.create(pollCreateDTO);
    }
    
    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.DELETE})
    public List<PollDTO> getAll(){
        return pollService.getAll();
    }
    
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public PollDTO getById(@PathVariable(value =  "id") int id){
        return pollService.getById(id);
    }
    @RequestMapping(value = "/addAnswer/{id}/{answer}",method = RequestMethod.PUT)
    public AnswerDTO addAnswer(@PathVariable(value = "id")int pollId,@PathVariable(value = "answer") String answer){
        
    }
}
