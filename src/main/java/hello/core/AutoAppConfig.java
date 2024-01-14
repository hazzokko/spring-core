package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component 어노테이션이 붙은 클래스를 모두 찾아서 자동으로 스프링 빈으로 등록
        // 제외할 것 지정 (이전 예제 유지를 위해 기존의 AppConfig 클래스 제외)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
