package com.saravjot.journalBackend.repository;

import com.saravjot.journalBackend.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {
}
