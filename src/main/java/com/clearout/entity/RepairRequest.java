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
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String description;
    private String imageOfDevice;
    private LocalDateTime generatedAt;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "individual_user_id")
    @JsonIgnoreProperties("repairRequests")
    private IndividualUser individualUser;

    @ManyToMany
    @JoinTable(
            name = "repair_request_agent",
            joinColumns = @JoinColumn(name = "repair_request_id"),
            inverseJoinColumns = @JoinColumn(name = "repair_agent_id")
    )
    private List<RepairAgent> repairAgents;

}
