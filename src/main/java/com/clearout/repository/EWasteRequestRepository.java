package com.clearout.repository;

import com.clearout.entity.EWasteRequest;
import com.clearout.entity.RequestStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EWasteRequestRepository extends JpaRepository<EWasteRequest,Long> {

    @Query("select e from EWasteRequest e where e.city = :city and e.requestStatus <> 'COMPLETED'")
    List<EWasteRequest> findInCompleteByCity(String city);


    @Modifying
    @Transactional
    @Query("update EWasteRequest e set e.requestStatus = :status where e.id = :requestId")
    void updateRequestStatus(Long requestId, RequestStatus status);

}
