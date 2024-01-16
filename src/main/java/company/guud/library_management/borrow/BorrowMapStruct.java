package company.guud.library_management.borrow;

import company.guud.library_management.borrow.web.BorrowDto;
import company.guud.library_management.borrow.web.CreateBorrowDto;
import company.guud.library_management.borrow.web.UpdateBorrowDto;
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
