package config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import starter.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songjiyang
 */
@ConfigurationProperties(prefix = "school")
@Data
public class SchoolProperties {

    private List<Student> students = new ArrayList<>();

    private boolean enabled = false;
}
