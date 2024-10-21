package com.clearout.repository;

import com.clearout.entity.RepairRequest;
import com.clearout.entity.RequestStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepairRequestRepository extends JpaRepository<RepairRequest, Long> {

    @Query("select r from RepairRequest r where r.city= :city and r.requestStatus <> 'COMPLETED'")
    List<RepairRequest> findInCompleteByCity(String city);

    @Modifying
    @Transactional
    @Query("update RepairRequest r set r.requestStatus = :requestStatus where r.id= :id")
    void updateRequestStatus(Long id, RequestStatus requestStatus);

}
