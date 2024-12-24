package com.trello.trello.domain.lists.service;

import com.trello.trello.domain.lists.entity.Lists;
import com.trello.trello.domain.lists.repository.ListsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListsService {

    private final ListsRepository listsRepository;

    // 특정 보드에 속한 모든 리스트 조회
    public List<Lists> getListsByBoard(Long boardId) {
        return listsRepository.findByBoardId(boardId);
    }

    // 리스트 작성
    public Lists addList(Lists list) {
        return listsRepository.save(list);
    }

    // 리스트 수정
    public Lists updateList(Long id, Lists list) {
        Optional<Lists> existingList = listsRepository.findById(id);
        if (existingList.isPresent()) {
            Lists updatedList = existingList.get();
            // 필요한 필드 업데이트
            // 예: updatedList.setTitle(list.getTitle());
            return listsRepository.save(updatedList);
        }
        return null;
    }

    // 리스트 삭제
    public boolean deleteList(Long id) {
        if (listsRepository.existsById(id)) {
            listsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
