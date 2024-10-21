package com.clearout.repository;

import com.clearout.entity.EWasteAgent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EWasteAgentRepository extends JpaRepository<EWasteAgent, Long> {
    Optional<EWasteAgent> findByRepresentativeEmail(String representativeEmail);

    @Modifying
    @Transactional
    @Query("update EWasteAgent b set b.representativeFirstname = :representativeFirstname where b.id = :id")
    void updateRepresentativeFirstname(String representativeFirstname,long id);

    @Modifying
    @Transactional
    @Query("update EWasteAgent b set b.representativeLastname = :representativeLastname where b.id = :id")
    void updateRepresentativeLastname(String representativeLastname,long id);


    @Modifying
    @Transactional
    @Query("update EWasteAgent b set b.phoneNo = :phoneNo where b.id = :id")
    void updatePhoneNo(String phoneNo, long id);
}
