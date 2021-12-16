package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hogwarts")
class HogwartsController {

    private final Hogwarts hogwarts;

    HogwartsController(Hogwarts hogwarts) {
        this.hogwarts = hogwarts;
    }

    @Get
    Hogwarts index() {
        return hogwarts;
    }
}
