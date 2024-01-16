package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.models.SortOrderEnum;

public interface CommentService {
  Comments getComments(Integer limit, Integer offset, SortOrderEnum sortOrder);

  CommentPostResponse postComment(CommentPostRequest commentPostRequest);
}
