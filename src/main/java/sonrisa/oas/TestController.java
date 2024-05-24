package sonrisa.oas;

// REST APIに必要なパッケージ
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
// アノテーションに必要なパッケージ
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/greeting")
@Tag(name = "Hello", description = "Hello World API")
public class TestController {
    @Operation(summary = "お昼の挨拶をする", description = "お昼の挨拶をする")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AllOfPet.class))),
            @ApiResponse(responseCode = "400", description = "エラー")
    })

    @GetMapping(path = "/hello")
    @Schema(description = "Base Pet schema")
    public String hello() {
        return "Hello World";
    }
}
