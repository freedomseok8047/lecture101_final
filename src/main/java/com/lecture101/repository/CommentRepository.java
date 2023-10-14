package com.lecture101.repository;



import com.lecture101.entity.CommentEntity;
import com.lecture101.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<CommentEntity> findAllByItemOrderByIdDesc(Item item);

    List<CommentEntity> findByItemIdOrderByCreationDateDesc(Long itemId);

    Page<CommentEntity> findPageByItemIdOrderByCreationDateDesc(Long itemId, Pageable pageable);
}
