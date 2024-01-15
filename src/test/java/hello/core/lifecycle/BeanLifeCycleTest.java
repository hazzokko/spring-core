package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // 관계 : ApplicationContext -> ConfigurableApplicationContext -> AnnotationConfigApplicationContext
        // close 하기 위해서 ConfigurableApplicationContext를 사용 (기본 ApplicationContext에서 제공 X)
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //스프링 컨테이너를 종료
    }

    @Configuration
    static class LifeCycleConfig {

        // 스프링 빈이 생성되고 호출된 결과물이 스프링 빈으로 등록
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
