package com.saravjot.journalBackend.service;

import com.saravjot.journalBackend.dto.PaginatedResult;
import com.saravjot.journalBackend.dto.UserInfo;
import com.saravjot.journalBackend.entity.Journal;
import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private JournalRepository journalRepository;

    @CacheEvict(value = "journalCache",  allEntries = true)
    public Journal save(Journal myJournal){
        return journalRepository.save(myJournal);
    }

    public List<Journal> getAll(){
        return journalRepository.findAll();
    }

    @CacheEvict(value = "journalCache",  allEntries = true)
    public Journal update(ObjectId id, Journal myJournal){
        Optional<Journal> journalInDb = journalRepository.findById(id);
        System.out.println(journalInDb);
        if(journalInDb.isPresent()){
            Journal journal = journalInDb.get();
            journal.setTitle(myJournal.getTitle());
            journal.setDescription(myJournal.getDescription());
            return journalRepository.save(journal);
        }
        throw new RuntimeException("ID not exists");
    }

    public void delete(ObjectId id){
        journalRepository.deleteById(id);
    }

    @Cacheable(value = "journalCache")
    public PaginatedResult findAll(String userId, int pageSize, int page){
        Query query = new Query().skip((long) (page - 1) * pageSize)
                .limit(pageSize);;
        Query countQuery = new Query();

        if(userId != null){
            query.addCriteria(Criteria.where("user").is(userId));
            countQuery.addCriteria(Criteria.where("user").is(userId));
        }
        List<Journal> journals =  mongoTemplate.find(query, Journal.class);
        for (Journal journal : journals) {
            Optional<User> user = this.userService.findById(new ObjectId(journal.getUser()));
            user.ifPresent(value -> journal.setUserInfo(new UserInfo(value.getId(), value.getEmail(), value.getMobile())));
        }
        long totalCount = mongoTemplate.count(countQuery, Journal.class);
        
        return new PaginatedResult(journals, totalCount, pageSize,page);
    }
}
