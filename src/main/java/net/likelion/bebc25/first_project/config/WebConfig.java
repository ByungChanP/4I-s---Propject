package net.likelion.bebc25.first_project.config;
import net.likelion.bebc25.first_project.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()).order(1)
                .addPathPatterns("/member/profile", "/board/edit", "/board/write", "/board/detail")
                .excludePathPatterns("/member/login", "/member/register","/board/list","/board/main","/css/**", "/js/**");
    }
}