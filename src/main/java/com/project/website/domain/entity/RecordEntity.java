package com.project.website.domain.entity;



import lombok.*;
import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(nullable = false)
    private String videoName;

    @Column(nullable = false)
    private String videoPath;

    @Column(nullable = false)
    private String videoType;


    @Builder
    public RecordEntity( Long videoId, String videoName, String videoPath, String videoType) {
        this.videoId=videoId;
        this.videoName=videoName;
        this.videoPath=videoPath;
        this.videoType=videoType;
    }

}