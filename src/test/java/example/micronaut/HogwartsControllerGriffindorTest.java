package example.micronaut;

import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(environments = "gryffindor")
public class HogwartsControllerGriffindorTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @DisabledIfEnvironmentVariable(named = "MICRONAUT_ENVIRONMENTS", matches = "^((?!gryffindor).)*$")
    @Test
    void studentsChangePerEnvironment() {
        BlockingHttpClient client = httpClient.toBlocking();
        Hogwarts hogwarts = client.retrieve("/hogwarts", Hogwarts.class);
        assertNotNull(hogwarts.getStudents());
        assertEquals(3, hogwarts.getStudents().size());
        assertTrue(hogwarts.getStudents()
                .stream()
                .map(Hogwarts.Student::getFirstName)
                .anyMatch(name -> name.equals("Harry")));
        assertTrue(hogwarts.getStudents()
                .stream()
                .map(Hogwarts.Student::getFirstName)
                .noneMatch(name -> name.equals("Draco")));

    }
}
