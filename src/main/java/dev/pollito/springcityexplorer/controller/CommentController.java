package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.CommentApi;
import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.models.SortOrderEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController implements CommentApi {

  @Override
  public ResponseEntity<Comments> getComments(
      Integer limit, Integer offset, SortOrderEnum sortOrder) {
    return CommentApi.super.getComments(limit, offset, sortOrder);
  }

  @Override
  public ResponseEntity<CommentPostResponse> postComment(CommentPostRequest commentPostRequest) {
    return CommentApi.super.postComment(commentPostRequest);
  }
}
