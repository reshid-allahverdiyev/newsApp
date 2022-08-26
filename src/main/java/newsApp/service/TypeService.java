package newsApp.service;

import newsApp.entity.TypeEntity;
import newsApp.mapper.ObjectMapper;
import newsApp.repository.mysql.TypeRepository;
import newsApp.request.CreateTypeRequest;
import newsApp.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public GeneralResponse getAllType() {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(typeRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList()));
        return generalResponse;
    }

    public GeneralResponse getTypeById(Long id) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(typeRepository.getById(id)));
        return generalResponse;
    }


    public GeneralResponse createType(CreateTypeRequest request) {
        TypeEntity entity = objectMapper.dtoToEntity(request);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(typeRepository.save(entity)));
        return generalResponse;
    }


    public GeneralResponse updateType(CreateTypeRequest request, Long id) {
        TypeEntity entity =
                typeRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setName(request.getName());
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(objectMapper.entityToDto(typeRepository.save(entity)));
        return generalResponse;
    }
}
