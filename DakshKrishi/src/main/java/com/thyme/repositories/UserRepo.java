package com.thyme.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.thyme.entities.User;


@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name=:name")
    public User getUserByName(@Param("name") String name);
}

