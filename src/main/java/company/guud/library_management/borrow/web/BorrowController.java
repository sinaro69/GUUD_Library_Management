package company.guud.library_management.borrow.web;

import company.guud.library_management.base.BaseApi;
import company.guud.library_management.borrow.Borrow;
import company.guud.library_management.borrow.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/borrow")
public class BorrowController {

    private final BorrowService borrowService;
    @PostMapping("/create")
    public BaseApi<?> createBorrow(@RequestBody CreateBorrowDto createBorrowDto){
        BorrowDto borrowDto = borrowService.createBorrow(createBorrowDto);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Borrow created successfully")
                .data(borrowDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @GetMapping("/{id}")
    public BaseApi<?> findById(@PathVariable Long id){
        BorrowDto borrowDto = borrowService.findById(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Borrow Find By ID successfully")
                .data(borrowDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }
    @GetMapping
    public BaseApi<?> findAll(){
        List<BorrowDto> borrowDto = borrowService.findAll();
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Borrow Find All successfully")
                .data(borrowDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @PutMapping("/{id}")
    public BaseApi<?> updateById(@PathVariable Long id, @RequestBody UpdateBorrowDto updateBorrowDto){
        BorrowDto borrowDto = borrowService.updateById(id,updateBorrowDto);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Borrow Update successfully")
                .data(borrowDto)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseApi<?> deleteById(@PathVariable Long id){
        Borrow borrow = borrowService.deleteById(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Borrow Deleted successfully")
                .data(borrow)
                .timestamp(LocalDateTime.now())
                .status(true)
                .build();
    }
}
