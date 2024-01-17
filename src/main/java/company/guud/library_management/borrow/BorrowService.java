package company.guud.library_management.borrow;

import company.guud.library_management.borrow.web.BorrowDto;
import company.guud.library_management.borrow.web.CreateBorrowDto;
import company.guud.library_management.borrow.web.UpdateBorrowDto;

import java.util.List;

public interface BorrowService {

    BorrowDto createBorrow(CreateBorrowDto createBorrowDto);

    BorrowDto findById(Long id);

    List<BorrowDto> findAll();

    BorrowDto updateById(Long id, UpdateBorrowDto updateBorrowDto);

    Borrow deleteById(Long id);
    BorrowDto create(CreateBorrowDto createBorrowDto);
}
