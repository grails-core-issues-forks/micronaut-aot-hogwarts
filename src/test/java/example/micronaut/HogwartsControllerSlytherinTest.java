package example.micronaut;

import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(environments = "slytherin")
public class HogwartsControllerSlytherinTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @DisabledIfEnvironmentVariable(named = "MICRONAUT_ENVIRONMENTS", matches = "^((?!slytherin).)*$")
    @Test
    void studentsChangePerEnvironment() {
        BlockingHttpClient client = httpClient.toBlocking();
        Hogwarts hogwarts = client.retrieve("/hogwarts", Hogwarts.class);
        assertNotNull(hogwarts.getStudents());
        assertEquals(3, hogwarts.getStudents().size());
        assertTrue(hogwarts.getStudents()
                .stream()
                .map(Hogwarts.Student::getFirstName)
                .anyMatch(name -> name.equals("Draco")));
        assertTrue(hogwarts.getStudents()
                .stream()
                .map(Hogwarts.Student::getFirstName)
                .noneMatch(name -> name.equals("Harry")));

    }
}
