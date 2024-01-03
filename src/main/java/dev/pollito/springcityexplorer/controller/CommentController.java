package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.CommentApi;
import dev.pollito.springcityexplorer.models.CommentPostBody;
import dev.pollito.springcityexplorer.models.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController implements CommentApi {
  @Override
  public ResponseEntity<Comments> getComments(Integer limit, Integer offset) {
    return null;
  }

  @Override
  public ResponseEntity<Void> postComment(CommentPostBody body) {
    return null;
  }
}
