package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.annotation.ValidCommentSortOrder;
import dev.pollito.springcityexplorer.dto.CommentPostRequestJakarta;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CommentService {
  Comments getComments(
      @Max(100) @Min(1) Integer limit,
      @Min(0) Integer offset,
      @ValidCommentSortOrder String sortOrder);

  CommentPostResponse postComment(@Valid CommentPostRequestJakarta request);
}
