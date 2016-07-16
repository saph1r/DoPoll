/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.service;


import de.fhdortmund.dohack.dopoll16.db.entity.Poll;
import de.fhdortmund.dohack.dopoll16.db.repository.PollRepository;
import de.fhdortmund.dohack.dopoll16.web.dto.PollCreateDTO;
import de.fhdortmund.dohack.dopoll16.web.dto.PollDTO;
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
    
    public PollDTO create(PollCreateDTO pollCreateDTO){
        Poll poll = new Poll();
        BeanUtils.copyProperties(pollCreateDTO, poll);
        pollRepository.save(poll);
    }
}
