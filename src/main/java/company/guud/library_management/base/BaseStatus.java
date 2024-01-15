package company.guud.library_management.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

public record BaseStatus(@NotNull
                         @JsonInclude(JsonInclude.Include.NON_NULL)
                         Boolean status) {
}
