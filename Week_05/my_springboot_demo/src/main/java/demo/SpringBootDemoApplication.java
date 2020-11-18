package demo;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import starter.School;

import java.util.Arrays;

/**
 * @author songjiyang
 */
@SpringBootApplication
@Getter
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemoApplication.class);

        System.out.println(Strings.join(Arrays.asList(Arrays.stream(
                run.getBeanFactory().getBeanDefinitionNames()).
                filter((s) -> s.toLowerCase().contains("school")).toArray()), '\n'));
        School school = (School) run.getBean("school");
        System.out.println(school);
        school.ding();
    }
}
