package com.project.website.dto;

import com.project.website.domain.entity.RecordEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordDto {
    private Long videoId;
    private String videoName;
    private String videoPath;
    private String videoType;

    public RecordEntity toEntity(){
        return RecordEntity.builder()
                .videoId(videoId)
                .videoName(videoName)
                .videoPath(videoPath)
                .videoType(videoType)
                .build();
    }

    @Builder
    public RecordDto(Long videoId, String videoName, String videoPath, String videoType) {
        this.videoId=videoId;
        this.videoName=videoName;
        this.videoPath=videoPath;
        this.videoType=videoType;
    }

}
