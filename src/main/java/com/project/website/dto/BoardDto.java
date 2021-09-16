package com.project.website.dto;

import com.project.website.domain.entity.BoardEntity;
import com.project.website.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private int viewCnt;
    private Long fileId;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
    private UserEntity userEntity;
    private String writer;

    public BoardEntity toEntity(){
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .viewCnt(viewCnt)
                .fileId(fileId)
                .userEntity(userEntity)
                .build();
        return boardEntity;
    }

    @Builder
    public BoardDto(UserEntity userEntity,Long id, String title, String content, int viewCnt, Long fileId, LocalDateTime insertTime, LocalDateTime updateTime, LocalDateTime deleteTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.fileId = fileId;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.userEntity = userEntity;
    }
}

