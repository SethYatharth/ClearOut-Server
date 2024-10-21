package com.clearout.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EWasteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private int quantity;
    private String createdBy;
    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "individual_user_id")
    @JsonIgnoreProperties("eWasteRequests")
    private IndividualUser individualUser;

    @ManyToOne
    @JoinColumn(name = "business_id")
    @JsonIgnoreProperties("eWasteRequests")
    private Business business;

    @ManyToMany
    @JoinTable(
            name = "ewaste_request_agent",
            joinColumns = @JoinColumn(name = "ewaste_request_id"),
            inverseJoinColumns = @JoinColumn(name = "ewaste_agent_id")
    )
    @JsonIgnoreProperties("eWasteRequests")
    List<EWasteAgent> eWasteAgents;



}
