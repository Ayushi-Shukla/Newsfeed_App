package com.news.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.news.entity.*;

public interface CusRepository extends CrudRepository<AnalystDetails, Long>  {
    List<AnalystDetails> findByEmail(String email);
    List<AnalystDetails> findByemailAndPassword(String email, String password);
    boolean existsByemail(String email);
    //boolean existsByid(long id);
    boolean existsByusername(String username);
    AnalystDetails findByusername(String username);
    AnalystDetails findById(long id);
    AnalystDetails findByemail(String email);
}
