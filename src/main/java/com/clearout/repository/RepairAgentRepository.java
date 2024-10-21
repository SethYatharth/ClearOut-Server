package com.clearout.repository;

import com.clearout.entity.RepairAgent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RepairAgentRepository extends JpaRepository<RepairAgent, Long> {
    Optional<RepairAgent> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update RepairAgent b set b.firstname = :firstname where b.id = :id")
    void updateFirstname(String firstname,long id);

    @Modifying
    @Transactional
    @Query("update RepairAgent b set b.lastname = :lastname where b.id = :id")
    void updateLastname(String lastname,long id);


    @Modifying
    @Transactional
    @Query("update RepairAgent b set b.phoneNo = :phoneNo where b.id = :id")
    void updatePhoneNo(String phoneNo, long id);


}
