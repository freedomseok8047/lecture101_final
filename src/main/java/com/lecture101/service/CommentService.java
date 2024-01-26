package com.lecture101.service;


import com.lecture101.dto.CommentDTO;
import com.lecture101.entity.CommentEntity;
import com.lecture101.entity.Item;
import com.lecture101.repository.CommentRepository;
import com.lecture101.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<Item> optionalItem = itemRepository.findById(commentDTO.getItemId());
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, item);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByItemOrderByIdDesc(item);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, itemId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public CommentDTO findById(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        return CommentDTO.toCommentDTO(commentEntity, commentEntity.getItem().getId());
    }

    public List<CommentDTO> findByItemId(Long itemId) {
        List<CommentEntity> commentEntities = commentRepository.findByItemIdOrderByCreationDateDesc(itemId);

        return commentEntities.stream()
                .map(commentEntity -> CommentDTO.toCommentDTO(commentEntity, itemId))
                .collect(Collectors.toList());
    }


    // 수정 메서드
    public CommentDTO update(Long id, CommentDTO commentDTO) throws Exception {
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("Comment not found"));
        comment.update(commentDTO);
        commentRepository.save(comment);
        return CommentDTO.fromEntity(comment);
    }

    // 삭제 메서드
    public Long deleteComment(Long id) {
        CommentEntity commentToDelete = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));

        Long itemId = commentToDelete.getItem().getId(); // 해당 댓글의 상품 ID를 가져옵니다.
        commentRepository.delete(commentToDelete);

        return itemId; // 댓글이 삭제된 상품의 ID를 반환합니다.
    }

    // 페이징 메서드
    public Page<CommentDTO> findCommentsByItemId(Long itemId, Pageable pageable) {
        Page<CommentEntity> commentEntitiesPage = commentRepository.findPageByItemIdOrderByCreationDateDesc(itemId, pageable);
        return commentEntitiesPage.map(commentEntity -> CommentDTO.toCommentDTO(commentEntity, itemId));
    }




}
