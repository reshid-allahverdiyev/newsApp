package newsApp.service;

import newsApp.entity.AuthorEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.AuthorRepository;
import newsApp.request.CreateAuthorRequest;
import newsApp.response.GetAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<GetAuthorResponse> getAllAuthor (){
        return authorRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }
    public GetAuthorResponse getAuthorById(Long id) {
        return objectMapper.entityToDto(authorRepository.getById(id));
    }


    public GetAuthorResponse createAuthor(CreateAuthorRequest request) {
        AuthorEntity entity = objectMapper.dtoToEntity(request);
        return objectMapper.entityToDto(authorRepository.save(entity));
    }


    public GetAuthorResponse updateAuthor(CreateAuthorRequest request, Long id) {
        AuthorEntity entity =
                authorRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        return objectMapper.entityToDto(authorRepository.save(entity));
    }
}
