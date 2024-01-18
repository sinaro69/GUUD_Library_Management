package company.guud.library_management.returned.web;

import company.guud.library_management.base.BaseApi;
import company.guud.library_management.returned.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService returnService;

    @PostMapping
    public BaseApi<?> create(@RequestBody ReturnCreationDto returnCreationDto
                             ){
        var returned = this.returnService.create(returnCreationDto);
        return BaseApi.builder()
                .code(HttpStatus.CREATED.value())
                .message("Created successfully")
                .data(returned)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @GetMapping("/{id}")
    public BaseApi<?> getById(@PathVariable Long id){
        var returned = this.returnService.getById(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Get by id successfully")
                .data(returned)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @GetMapping
    public BaseApi<?> getAll(){
        var returned = this.returnService.getAll();
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Get all successfully")
                .data(returned)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @PutMapping("/{id}")
    public BaseApi<?> update(@PathVariable Long id,@RequestBody ReturnCreationDto returnCreationDto){
        var returned = this.returnService.update(id,returnCreationDto);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Update successfully")
                .data(returned)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseApi<?> delete(@PathVariable Long id){
        this.returnService.delete(id);
        return BaseApi.builder()
                .code(HttpStatus.OK.value())
                .message("Delete successfully")
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }



}
