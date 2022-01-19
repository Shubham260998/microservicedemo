package com.restful.webservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositary extends JpaRepository<User, Integer>{

}
