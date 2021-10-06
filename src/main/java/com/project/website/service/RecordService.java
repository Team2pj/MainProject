package com.project.website.service;


import com.project.website.domain.entity.BoardEntity;
import com.project.website.domain.entity.RecordEntity;
import com.project.website.domain.repository.RecordRepository;
import com.project.website.dto.BoardDto;
import com.project.website.dto.RecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Transactional
    public Long saveRecord(RecordDto recordDto) {
        return recordRepository.save(recordDto.toEntity()).getVideoId();
    }

    @Transactional
    public List<RecordDto> getRecordList() {
        List<RecordEntity> recordEntityList = recordRepository.findAll();
        List<RecordDto> recordDtoList = new ArrayList<>();

        for(RecordEntity record : recordEntityList){
            RecordDto recordDto = RecordDto.builder()
                    .videoId(record.getVideoId())
                    .videoType(record.getVideoType())
                    .videoPath(record.getVideoPath())
                    .videoName(record.getVideoName())
                    .build();
            recordDtoList.add(recordDto);
        }
        return recordDtoList;
    }




}