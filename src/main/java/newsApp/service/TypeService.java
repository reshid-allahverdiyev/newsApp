package newsApp.service;

import newsApp.entity.TypeEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.TypeRepository;
import newsApp.request.CreateTypeRequest;
import newsApp.response.GetTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<GetTypeResponse> getAllType() {
        return typeRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public GetTypeResponse getTypeById(Long id) {
        return objectMapper.entityToDto(typeRepository.getById(id));
    }


    public GetTypeResponse createType(CreateTypeRequest request) {
        TypeEntity entity = objectMapper.dtoToEntity(request);
        return objectMapper.entityToDto(typeRepository.save(entity));
    }


    public GetTypeResponse updateType(CreateTypeRequest request, Long id) {
        TypeEntity entity =
                typeRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setName(request.getName());
        return objectMapper.entityToDto(typeRepository.save(entity));
    }
}
