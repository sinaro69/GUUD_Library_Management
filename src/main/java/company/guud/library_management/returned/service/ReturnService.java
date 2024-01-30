package company.guud.library_management.returned.service;

import company.guud.library_management.returned.dto.ReturnCreationDto;
import company.guud.library_management.returned.dto.ReturnDtoDetail;

import java.util.List;

public interface ReturnService {
    ReturnDtoDetail create(ReturnCreationDto returnCreationDto);
    ReturnDtoDetail update(Long id, ReturnCreationDto returnCreationDto);
    void delete(Long id);
    ReturnDtoDetail getById(Long id);
    List<ReturnDtoDetail> getAll();
}
