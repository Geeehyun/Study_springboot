package org.fullstack4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {
    @CreatedDate
    @Column(name="reg_date", updatable = false)
    private LocalDateTime reg_date;

    @LastModifiedDate
    @Column(name = "modify_date", updatable = true, nullable = true, insertable = false)
    private LocalDateTime modify_date;

    public void setModify_date(LocalDateTime modify_date) {
        this.modify_date = LocalDateTime.now();
    }
}
