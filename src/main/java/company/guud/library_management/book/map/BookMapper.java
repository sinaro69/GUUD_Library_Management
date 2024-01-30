package company.guud.library_management.book.map;

import company.guud.library_management.book.dto.BookCreationDto;
import company.guud.library_management.book.dto.BookDto;
import company.guud.library_management.book.dto.BookEditionDto;
import company.guud.library_management.book.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Book book, BookEditionDto bookEditionDto);
    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
    Book toCreateEntity(BookCreationDto bookCreationDto);
    List<BookDto> toDtoList(List<Book> books);
}
