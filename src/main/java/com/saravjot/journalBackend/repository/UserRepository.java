package com.saravjot.journalBackend.repository;

import com.saravjot.journalBackend.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User getUserByEmail(String email);
    User getUserById(ObjectId id);
}
