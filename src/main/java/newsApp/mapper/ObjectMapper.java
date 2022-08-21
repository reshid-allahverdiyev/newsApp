package newsApp.mapper;

import newsApp.entity.AuthorEntity;
import newsApp.entity.NewsEntity;
import newsApp.entity.TypeEntity;
import newsApp.request.CreateAuthorRequest;
import newsApp.request.CreateNewsRequest;
import newsApp.request.CreateTypeRequest;
import newsApp.response.GetAuthorResponse;
import newsApp.response.GetNewsResponse;
import newsApp.response.GetTypeResponse;
import org.aspectj.weaver.ConcreteTypeMunger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ObjectMapper {
    public abstract GetNewsResponse entityToDto(NewsEntity  entity);
    public abstract NewsEntity dtoToEntity(CreateNewsRequest request);

    public abstract GetTypeResponse entityToDto(TypeEntity  entity);
    public abstract TypeEntity dtoToEntity(CreateTypeRequest request);

    public abstract GetAuthorResponse entityToDto(AuthorEntity  entity);

    public abstract AuthorEntity dtoToEntity(CreateAuthorRequest request);
}
