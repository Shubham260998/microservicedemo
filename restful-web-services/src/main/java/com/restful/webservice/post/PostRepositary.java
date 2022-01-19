package com.restful.webservice.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositary  extends JpaRepository<Post, Integer>{

}
