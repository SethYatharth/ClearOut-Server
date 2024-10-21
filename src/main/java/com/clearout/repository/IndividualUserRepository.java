package com.clearout.repository;

import com.clearout.entity.IndividualUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IndividualUserRepository extends JpaRepository<IndividualUser, Long> {
    Optional<IndividualUser> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update IndividualUser i set i.firstname = :firstname where i.id = :id")
    void updateIndividualFirstname(String firstname,long id);

    @Modifying
    @Transactional
    @Query("update IndividualUser i set i.lastname = :lastname where i.id = :id")
    void updateIndividualLastname(String lastname,long id);

    @Modifying
    @Transactional
    @Query("update IndividualUser i set i.phoneNo=:phoneNo where i.id = :id")
    void updateIndividualPhoneNo(String phoneNo, long id);
}
