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
    private Long inferenceId;
    private String inferenceTime;
    private String inferenceType;

    public RecordEntity toEntity(){
        return RecordEntity.builder()
                .inferenceId(inferenceId)
                .inferenceTime(inferenceTime)
                .inferenceType(inferenceType)
                .build();
    }

    @Builder
    public RecordDto(Long inferenceId, String inferenceTime, String inferenceType) {
        this.inferenceId=inferenceId;
        this.inferenceTime=inferenceTime;
        this.inferenceType=inferenceType;
            }

}
