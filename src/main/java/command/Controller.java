package command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/"}, loadOnStartup = 1)
public class Controller extends HttpServlet {
    private CommandContainer commandContainer = new CommandContainer();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Command command = commandContainer.getCommand(req.getRequestURI());
        if (command == null) {
            command = commandContainer.getDefaultCommand();
        }

        command.execute(req, resp);
    }

}
