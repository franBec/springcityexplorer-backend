package dev.pollito.springcityexplorer.service.impl;

import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  @Override
  public Comments getComments(Integer limit, Integer offset, String sortOrder) {
    return null;
  }

  @Override
  public CommentPostResponse postComment(CommentPostRequest request) {
    return null;
  }
}
