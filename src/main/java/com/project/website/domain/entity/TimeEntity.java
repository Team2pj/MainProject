package com.project.website.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {
    /** 등록일 */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime insertTime;

    /** 수정일 */
    @UpdateTimestamp
    private LocalDateTime updateTime;

    /** 삭제일 */
    private LocalDateTime deleteTime;
}
