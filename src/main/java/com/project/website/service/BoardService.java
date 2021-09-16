package com.project.website.service;

import com.project.website.domain.entity.BoardEntity;
import com.project.website.domain.entity.UserEntity;
import com.project.website.domain.repository.BoardRepository;
import com.project.website.domain.repository.UserRepository;
import com.project.website.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;
    
    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 5;

    @Transactional
    public long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardList(Integer pageNum) {

        Page<BoardEntity> page = boardRepository
                .findAll(PageRequest
                        .of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "insertTime")));
        List<BoardEntity> boardList = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(BoardEntity board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .insertTime(board.getInsertTime())
                    .viewCnt(board.getViewCnt())
                    .fileId(board.getFileId())
                    .userEntity(board.getUserEntity())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                :totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        //페이지 번호 할당
        for(int val=curPageNum, i=0; val<=blockLastPageNum; val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    @Transactional
    public BoardDto getPost(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .insertTime(boardEntity.getInsertTime())
                .viewCnt(boardEntity.getViewCnt())
                .build();
        return boardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boardEntities.isEmpty()) return boardDtoList;

        for(BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .insertTime(boardEntity.getInsertTime())
                .viewCnt(boardEntity.getViewCnt())
                .build();
    }

    public void updateCount(Long id) {
        boardRepository.updateCount(id);
    }
}
