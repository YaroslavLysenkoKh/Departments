package comm.util.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"comm", "net.sf"})
public class ApplicationConfiguration {
}
