package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.CommentApi;
import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.models.SortOrderEnum;
import dev.pollito.springcityexplorer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {
  private final CommentService commentService;

  @Override
  public ResponseEntity<Comments> getComments(
      Integer limit, Integer offset, SortOrderEnum sortOrder) {
    return ResponseEntity.ok(commentService.getComments(limit, offset, sortOrder));
  }

  @Override
  public ResponseEntity<CommentPostResponse> postComment(CommentPostRequest commentPostRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(commentService.postComment(commentPostRequest));
  }
}
