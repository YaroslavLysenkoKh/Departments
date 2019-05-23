package comm.command;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("dispatcher")
public class Controller extends HttpRequestHandlerServlet implements HttpRequestHandler {

    private ApplicationContext applicationContext;

    public Controller(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Command command = (Command) applicationContext.getBean(request.getRequestURI());
        if (command == null) {
            command = (Command) applicationContext.getBean("/");
        }

        command.execute(request, response);
    }
}
