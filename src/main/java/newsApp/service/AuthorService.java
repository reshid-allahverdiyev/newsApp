package newsApp.service;

import newsApp.entity.AuthorEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.AuthorRepository;
import newsApp.request.CreateAuthorRequest;
import newsApp.response.GeneralResponse;
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

    public GeneralResponse getAllAuthor (){
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(authorRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList()));
        return generalResponse;

    }
    public GeneralResponse getAuthorById(Long id) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(authorRepository.getById(id)));
        return generalResponse;
    }


    public GeneralResponse createAuthor(CreateAuthorRequest request) {
        AuthorEntity entity = objectMapper.dtoToEntity(request);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(authorRepository.save(entity)));
        return generalResponse;
    }


    public GeneralResponse updateAuthor(CreateAuthorRequest request, Long id) {
        AuthorEntity entity =
                authorRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setName(request.getName());
        entity.setSurname(request.getSurname());

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(authorRepository.save(entity)));
        return generalResponse;
    }
}
