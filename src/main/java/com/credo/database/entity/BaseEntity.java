package com.credo.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

@Slf4j
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseEntity {
//    @Column(name = "created_dt", nullable = false, updatable = false)
    @Column(name = "created_dt", updatable = false)
    @CreationTimestamp
    OffsetDateTime createdDate;

//    @Column(nullable = false, updatable = false)
    @Column(updatable = false)
    String createdBy;

//    @Column(name = "updated_dt", nullable = false)
    @Column(name = "updated_dt")
    @UpdateTimestamp
    OffsetDateTime updatedDate;

//    @Column(nullable = false, updatable = false)
    @Column(updatable = false)
    String updatedBy;

    @PrePersist
    public void prePersist() {
        createdDate = OffsetDateTime.now();
        updatedDate = OffsetDateTime.now();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            createdBy = ((UserDetails) principal).getUsername();
            updatedBy = ((UserDetails) principal).getUsername();
        } else {
            createdBy = "Admin";
            updatedBy = "Admin";
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = OffsetDateTime.now();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            updatedBy = ((UserDetails) principal).getUsername();
        } else {
            updatedBy = "Admin";
        }
    }
}
