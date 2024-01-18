package company.guud.library_management.returned;

import company.guud.library_management.returned.web.ReturnCreationDto;
import company.guud.library_management.returned.web.ReturnDtoDetail;
import company.guud.library_management.returned.web.ReturnEditionDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReturnMapper {

    Return toEntity(ReturnDtoDetail returnDtoDetail);
    ReturnDtoDetail toDtoDetail(Return returnEntity);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "borrow",ignore = true)
    @Mapping(target = "customer",ignore = true)
    Return toCreateEntity(ReturnCreationDto returnCreationDto);
    ReturnEditionDto toDto(Return returnEntity);

    List<ReturnDtoDetail> toDtoList(List<Return> returnList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
   void partialUpdate(@MappingTarget Return entity,ReturnCreationDto returnCreationDto);
    
}
