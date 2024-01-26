package com.lecture101.controller;



import com.lecture101.dto.CommentDTO;
import com.lecture101.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")

public class CommentController {
    private final CommentService commentService;

    // 리뷰 작성
    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        Long saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            CommentDTO savedComment = commentService.findById(saveResult);
            return new ResponseEntity<>(savedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 리뷰 화면
    @GetMapping("/edit/{commentId}")
    public String editComment(@PathVariable Long commentId, Model model) {
        CommentDTO commentDTO = commentService.findById(commentId);
        model.addAttribute("comment", commentDTO);

        Long itemId = commentDTO.getItemId();  // assuming CommentDTO has getItemId() method
        List<CommentDTO> comments = commentService.findByItemId(itemId);
        model.addAttribute("comments", comments);

        return "editComment";
    }


    // 리뷰 수정
    @PostMapping("/update/{id}")
    public String updateComment(@PathVariable Long id, @ModelAttribute CommentDTO commentDTO, RedirectAttributes redirectAttributes) {
        try {
            CommentDTO updatedComment = commentService.update(id, commentDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Comment updated successfully");
            return "redirect:/item/" + updatedComment.getItemId();  // assuming CommentDTO has getItemId() method
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Comment could not be updated");
            return "redirect:/comment/edit/" + id;
        }
    }


    // 리뷰 삭제
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        // 댓글을 삭제하고 해당 상품 페이지로 리다이렉트합니다.
        Long itemId = commentService.deleteComment(id);
        return "redirect:/item/" + itemId;
    }






}
