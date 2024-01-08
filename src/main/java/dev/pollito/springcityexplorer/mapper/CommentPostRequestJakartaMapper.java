package dev.pollito.springcityexplorer.mapper;

import dev.pollito.springcityexplorer.dto.CommentPostRequestJakarta;
import dev.pollito.springcityexplorer.models.CommentPostRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentPostRequestJakartaMapper {
  CommentPostRequestJakarta map(CommentPostRequest commentPostRequest);
}
