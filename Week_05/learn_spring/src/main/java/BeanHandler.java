import mybean.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author songjiyang
 */
public class BeanHandler {


    public static void xml() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("student");

        student.say();
    }

    public static void annotation() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("mybean");

        Student student = (Student) annotationConfigApplicationContext.getBean("student");

        student.say();
    }

    public static void manuallyBeanDefinition() {

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        BeanDefinition beanDefinition = new GenericBeanDefinition();

        beanDefinition.setBeanClassName("mybean.Student");
        beanDefinition.setLazyInit(false);

        genericApplicationContext.registerBeanDefinition("student", beanDefinition);
        genericApplicationContext.refresh();


        Student student = (Student) genericApplicationContext.getBean("student");

        student.say();
    }

    public static void manuallyBeanDefinitionBuilder() {

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        genericApplicationContext.refresh();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Student.class);

        genericApplicationContext.registerBeanDefinition("student", beanDefinitionBuilder.getBeanDefinition());

        Student student = (Student) genericApplicationContext.getBean("student");

        student.say();
    }

    public static void manuallyBeanDefinitionReaderUtils() throws ClassNotFoundException {

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        genericApplicationContext.refresh();

        AbstractBeanDefinition beanDefinition = BeanDefinitionReaderUtils.createBeanDefinition(null, "mybean.Student", BeanHandler.class.getClassLoader());

        genericApplicationContext.registerBeanDefinition("student", beanDefinition);

        Student student = (Student) genericApplicationContext.getBean("student");

        student.say();
    }

    public static void manuallyBeanDefinitionRegistryPostProcessor() throws ClassNotFoundException {

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        AbstractBeanDefinition beanDefinition = BeanDefinitionReaderUtils.
                createBeanDefinition(null, "mybean.MyBeanBeanDefinitionRegistryPostProcessor", BeanHandler.class.getClassLoader());

        genericApplicationContext.registerBeanDefinition("myBeanBeanDefinitionRegistryPostProcessor", beanDefinition);
        genericApplicationContext.refresh();
        Student student = (Student) genericApplicationContext.getBean("student");


        student.say();
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        xml();
//        annotation();
//        manuallyBeanDefinition();
//        manuallyBeanDefinitionBuilder();
//        manuallyBeanDefinitionReaderUtils();
        manuallyBeanDefinitionRegistryPostProcessor();
    }

}
