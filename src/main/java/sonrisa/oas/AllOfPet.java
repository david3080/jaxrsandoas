package sonrisa.oas;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Combined Pet schema",
    allOf = {Pet.class, NewPet.class}
)
public record AllOfPet(
    Long id,
    String name,
    String tag
) {}