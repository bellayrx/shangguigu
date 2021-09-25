package com.wk.mapper;

import com.wk.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMapper extends MongoRepository<User, String> {

}

