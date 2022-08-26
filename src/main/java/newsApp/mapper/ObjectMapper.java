package newsApp.mapper;

import newsApp.entity.*;
import newsApp.repository.view.NewsView;
import newsApp.request.CreateAuthorRequest;
import newsApp.request.CreateNewsRequest;
import newsApp.request.CreateTypeRequest;
import newsApp.response.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ObjectMapper {
    public abstract GetNewsResponse entityToDto(NewsEntity  entity);
    public abstract NewsEntity dtoToEntity(CreateNewsRequest request);

    public abstract GetTypeResponse entityToDto(TypeEntity  entity);
    public abstract TypeEntity dtoToEntity(CreateTypeRequest request);

    public abstract GetAuthorResponse entityToDto(AuthorEntity  entity);

    public abstract AuthorEntity dtoToEntity(CreateAuthorRequest request);


    public abstract GetOneNewsResponse entityToDto(NewsView entity);

    public abstract TokenEntity authResponseToCache(AuthResponse authResponse);
    public abstract TokenEntityRedis authResponseToCache2(AuthResponse authResponse);

}
