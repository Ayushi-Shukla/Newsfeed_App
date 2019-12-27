package com.news.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.news.entity.*;

public interface CusRepository extends CrudRepository<AnalystDetails, Long>  {
    List<AnalystDetails> findByEmail(String email);
    List<AnalystDetails> findByemailAndPassword(String email, String password);
    boolean existsByemail(String email);
    //boolean existsByid(long id);
    boolean existsByusername(String username);
    AnalystDetails findByusername(String username);
    AnalystDetails findById(long id);
    
    @Query("SELECT u from AnalystDetails u where u.email=:email")
    AnalystDetails findByemail(@Param("email") String email);
    
    
    List<AnalystDetails> findByUsername(String username);
}
