package com.saravjot.journalBackend.controller;

import com.saravjot.journalBackend.dto.PaginatedResult;
import com.saravjot.journalBackend.entity.Journal;
import com.saravjot.journalBackend.entity.User;
import com.saravjot.journalBackend.service.JournalService;
import com.saravjot.journalBackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> saveJournal(@RequestBody Journal myjournal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        myjournal.setUser(user.getId());
        Journal journal = journalService.save(myjournal);
        return new ResponseEntity<>(journal, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<?> getJournals(@RequestParam(required = false, name = "page") Integer page, @RequestParam(required = false, name = "pageSize") Integer pageSize ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        int safePageSize = (pageSize == null || pageSize <= 0) ? 10 : pageSize;
        int safePage = (page == null || page <= 0) ? 1 : page;
        PaginatedResult journals = journalService.findAll(user.getId(),safePageSize, safePage);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable("id") ObjectId id, @RequestBody Journal myJournal){
        Journal journal = journalService.update(id, myJournal);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable("id") ObjectId id){
       journalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
