package dev.pollito.springcityexplorer.controller;

import static dev.pollito.springcityexplorer.MockData.MOCK_STRING;
import static dev.pollito.springcityexplorer.MockData.mockCommentPostRequest;
import static dev.pollito.springcityexplorer.MockData.mockCommentPostResponse;
import static dev.pollito.springcityexplorer.MockData.mockComments;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import dev.pollito.springcityexplorer.dto.CommentPostRequestJakarta;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {
  @InjectMocks private CommentController commentController;
  @Mock private CommentService commentService;

  @Test
  void whenGetCommentsThenReturnComments() {
    ResponseEntity<Comments> expectedResponse = ResponseEntity.ok(mockComments());
    when(commentService.getComments(anyInt(), anyInt(), anyString()))
        .thenReturn(expectedResponse.getBody());

    ResponseEntity<Comments> actualResponse = commentController.getComments(0, 0, MOCK_STRING);
    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  void whenPostCommentThenReturnCommentResponse() {
    ResponseEntity<CommentPostResponse> expectedResponse =
        ResponseEntity.ok(mockCommentPostResponse());
    when(commentService.postComment(any(CommentPostRequestJakarta.class)))
        .thenReturn(expectedResponse.getBody());

    ResponseEntity<CommentPostResponse> actualResponse =
        commentController.postComment(mockCommentPostRequest());
    assertEquals(expectedResponse, actualResponse);
  }
}
