package company.guud.library_management.returned;

import company.guud.library_management.returned.web.ReturnCreationDto;
import company.guud.library_management.returned.web.ReturnDtoDetail;
import company.guud.library_management.returned.web.ReturnEditionDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReturnMapper {

    Return toEntity(ReturnDtoDetail returnDtoDetail);
    ReturnDtoDetail toDtoDetail(Return returnEntity);
    Return toCreateEntity(ReturnCreationDto returnCreationDto);
    ReturnEditionDto toDto(Return returnEntity);
    List<ReturnCreationDto> toDtoList(List<Return> returnList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
   void partialUpdate(@MappingTarget Return entity,ReturnCreationDto returnCreationDto);
    
}
