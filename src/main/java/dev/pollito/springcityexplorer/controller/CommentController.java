package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.CommentApi;
import dev.pollito.springcityexplorer.mapper.CommentPostRequestJakartaMapper;
import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {
  private final CommentService commentService;
  private final CommentPostRequestJakartaMapper commentPostRequestJakartaMapper;

  @Override
  public ResponseEntity<Comments> getComments(Integer limit, Integer offset, String sortOrder) {
    return ResponseEntity.ok(commentService.getComments(limit, offset, sortOrder));
  }

  @Override
  public ResponseEntity<CommentPostResponse> postComment(CommentPostRequest body) {
    return ResponseEntity.ok(commentService.postComment(commentPostRequestJakartaMapper.map(body)));
  }
}
