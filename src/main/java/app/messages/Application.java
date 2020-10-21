package app.messages;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@SpringBootApplication
public class Application {
  public static void main(String[] args) throws Exception{
    SpringApplication.run(Application.class, args);
  }
}
