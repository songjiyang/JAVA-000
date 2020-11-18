package starter;


import config.SchoolProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author songjiyang
 */
@Configuration
@ConditionalOnProperty(prefix = "school", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(SchoolProperties.class)
public class SchoolAutoConfiguration implements ApplicationContextAware {

    @Autowired
    SchoolProperties schoolProperties;
    ApplicationContext applicationContext;


    @Bean("student100")
    @ConditionalOnMissingBean
    public Student student() {
        return new Student(100, "student100");
    }

    @Bean()
    @ConditionalOnMissingBean
    public Klass klass() {
        Klass klass = new Klass();
        klass.setStudents(schoolProperties.getStudents());
        return klass;
    }

    @Bean()
    @ConditionalOnMissingBean
    public School school() {
        School school = new School();
        Student student100 = (Student) applicationContext.getBean("student100");
        Klass klass = (Klass) applicationContext.getBean("klass");

        school.setStudent100(student100);
        school.setClass1(klass);
        return school;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
