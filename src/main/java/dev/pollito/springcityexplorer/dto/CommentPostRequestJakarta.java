package dev.pollito.springcityexplorer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentPostRequestJakarta {
  @Size(min = 1, max = 200)
  private String content;
}
