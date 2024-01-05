package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;

public interface CommentService {
  Comments getComments(Integer limit, Integer offset, String sortOrder);

  CommentPostResponse postComment(CommentPostRequest request);
}
