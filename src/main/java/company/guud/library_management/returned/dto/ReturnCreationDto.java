package company.guud.library_management.returned.dto;



public record ReturnCreationDto(
    Long customerId,
    Long borrowId,
    Long amount

) {
}
