package company.guud.library_management.returned;

import company.guud.library_management.returned.web.ReturnCreationDto;
import company.guud.library_management.returned.web.ReturnDtoDetail;

import java.util.List;

public interface ReturnService {
    ReturnDtoDetail create(ReturnCreationDto returnCreationDto,Long borrowId,Long customerId);
    ReturnDtoDetail update(Long id, ReturnCreationDto returnCreationDto);
    void delete(Long id);
    ReturnDtoDetail getById(Long id);
    List<ReturnCreationDto> getAll();
}
