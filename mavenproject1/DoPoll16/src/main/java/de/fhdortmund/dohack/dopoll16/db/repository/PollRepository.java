/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.db.repository;

import de.fhdortmund.dohack.dopoll16.db.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 *
 * @author Sebastian
 */
public interface PollRepository extends JpaRepository<Poll, Integer>{
    
}
