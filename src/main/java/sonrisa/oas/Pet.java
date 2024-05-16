package sonrisa.oas;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Base Pet schema")
public record Pet(
    @Schema(description = "The ID of the pet", example = "1")
    Long id,
    @Schema(description = "The name of the pet", example = "Buddy")
    String name
) {}