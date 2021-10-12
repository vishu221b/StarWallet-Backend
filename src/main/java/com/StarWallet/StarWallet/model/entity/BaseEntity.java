package com.StarWallet.StarWallet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    public void prePersist(){
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setIsActive(Boolean.TRUE);
    }

    @PreUpdate
    public void preUpdate(){
        this.setLastUpdatedAt(new Date());
    }
}
