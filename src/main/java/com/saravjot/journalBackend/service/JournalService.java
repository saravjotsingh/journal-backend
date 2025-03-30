package com.saravjot.journalBackend.service;

import com.saravjot.journalBackend.entity.Journal;
import com.saravjot.journalBackend.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public Journal save(Journal myJournal){
        return journalRepository.save(myJournal);
    }

    public List<Journal> getAll(){
        return journalRepository.findAll();
    }

    public Journal update(ObjectId id, Journal myJournal){
        Optional<Journal> journalInDb = journalRepository.findById(id);
        System.out.println(journalInDb);
        if(journalInDb.isPresent()){
            Journal journal = journalInDb.get();
            journal.setTitle(myJournal.getTitle());
            journal.setDescription(myJournal.getDescription());
            return journalRepository.save(journal);
        }
        throw new RuntimeException();
    }

    public void delete(ObjectId id){
        journalRepository.deleteById(id);
    }
}
