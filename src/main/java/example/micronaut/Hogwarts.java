package example.micronaut;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.util.List;

@ConfigurationProperties("hogwarts")
public class Hogwarts {

    @NonNull
    @NotBlank
    private String headmaster;

    @Nullable
    private List<Student> students;

    @Nullable
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(@Nullable List<Student> students) {
        this.students = students;
    }

    @NonNull
    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(@NonNull String headmaster) {
        this.headmaster = headmaster;
    }

    @EachProperty("students")
    public static class Student {
        @NonNull
        @NotBlank
        private String firstName;

        @NonNull
        @NotBlank
        private String lastName;

        @NonNull
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(@NonNull String firstName) {
            this.firstName = firstName;
        }

        @NonNull
        public String getLastName() {
            return lastName;
        }

        public void setLastName(@NonNull String lastName) {
            this.lastName = lastName;
        }
    }

}
