package com.project.website.domain.entity;



import lombok.*;
import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inferenceId;

    @Column(length=100, nullable = false )
    private String inferenceTime;

    @Column(length=100, nullable = false)
    private String inferenceType;


    @Builder
    public RecordEntity( Long inferenceId, String inferenceTime, String inferenceType) {
        this.inferenceId=inferenceId;
        this.inferenceTime=inferenceTime;
        this.inferenceType=inferenceType;
    }

}