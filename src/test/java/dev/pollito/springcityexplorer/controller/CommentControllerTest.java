package dev.pollito.springcityexplorer.controller;

import static dev.pollito.springcityexplorer.MockData.mockCommentPostRequest;
import static dev.pollito.springcityexplorer.MockData.mockCommentPostResponse;
import static dev.pollito.springcityexplorer.MockData.mockComments;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.models.SortOrderEnum;
import dev.pollito.springcityexplorer.service.CommentService;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {
  @InjectMocks private CommentController commentController;
  @Mock private CommentService commentService;

  private final Faker faker = new Faker();

  @Test
  void getComments() {
    ResponseEntity<Comments> expectedResponse = ResponseEntity.ok(mockComments());
    when(commentService.getComments(anyInt(), anyInt(), any(SortOrderEnum.class)))
        .thenReturn(expectedResponse.getBody());

    ResponseEntity<Comments> actualResponse =
        commentController.getComments(
            faker.number().numberBetween(1, 10),
            faker.number().numberBetween(0, 10000),
            SortOrderEnum.ASC);
    assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  void postComment() {
    ResponseEntity<CommentPostResponse> expectedResponse =
        ResponseEntity.status(HttpStatus.CREATED).body(mockCommentPostResponse());
    when(commentService.postComment(any(CommentPostRequest.class)))
        .thenReturn(expectedResponse.getBody());

    ResponseEntity<CommentPostResponse> actualResponse =
        commentController.postComment(mockCommentPostRequest());
    assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    assertEquals(expectedResponse, actualResponse);
  }
}
