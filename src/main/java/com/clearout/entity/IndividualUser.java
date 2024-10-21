package com.clearout.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class IndividualUser extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private Role role;


    @OneToMany(mappedBy = "individualUser")
    @JsonIgnoreProperties("individualUser")
    private List<RepairRequest> repairRequests;

    @OneToMany(mappedBy = "individualUser")
    @JsonIgnoreProperties("individualUser")
    List<EWasteRequest> eWasteRequests;

    @OneToMany(mappedBy = "individualSeller")
    @JsonIgnoreProperties("individualSeller")
    List<Product> sellingProducts;

    @ManyToMany(mappedBy = "individualBuyers")
    @JsonIgnoreProperties("individualBuyers")
    List<Product> purchasedProducts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
