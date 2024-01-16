package company.guud.library_management.book;

import company.guud.library_management.book.web.BookCreationDto;
import company.guud.library_management.book.web.BookDto;
import company.guud.library_management.book.web.BookEditionDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.lang.annotation.Target;
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
