package sonrisa.oas;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "New Pet schema")
public record NewPet(
    @Schema(description = "The tag of the pet", example = "dog")
    String tag
) {}