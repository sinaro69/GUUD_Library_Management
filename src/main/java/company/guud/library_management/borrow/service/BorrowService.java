package company.guud.library_management.borrow.service;

import company.guud.library_management.borrow.dto.BorrowDto;
import company.guud.library_management.borrow.dto.CreateBorrowDto;
import company.guud.library_management.borrow.dto.UpdateBorrowDto;
import company.guud.library_management.borrow.model.Borrow;

import java.util.List;

public interface BorrowService {

    BorrowDto createBorrow(CreateBorrowDto createBorrowDto);
    BorrowDto findById(Long id);

    List<BorrowDto> findAll();

    BorrowDto updateById(Long id, UpdateBorrowDto updateBorrowDto);

    Borrow deleteById(Long id);
}
