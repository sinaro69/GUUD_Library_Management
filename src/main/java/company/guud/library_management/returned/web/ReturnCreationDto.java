package company.guud.library_management.returned.web;



public record ReturnCreationDto(
    Long customerId,
    Long borrowId,
    Long amount

) {
}
