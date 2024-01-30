package company.guud.library_management.borrow.map;

import company.guud.library_management.borrow.dto.BorrowDto;
import company.guud.library_management.borrow.dto.CreateBorrowDto;
import company.guud.library_management.borrow.dto.UpdateBorrowDto;
import company.guud.library_management.borrow.model.Borrow;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowMapStruct {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Borrow createBorrow(CreateBorrowDto createBorrowDto);

    BorrowDto toDto(Borrow borrow);

    List<BorrowDto> toDtoList(List<Borrow> model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Borrow borrow, UpdateBorrowDto updateBorrowDto);

//    Borrow Borrow(CreateBorrowDto createBorrowDto)
}
