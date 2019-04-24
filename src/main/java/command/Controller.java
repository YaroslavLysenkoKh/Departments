package command;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/"})
public class Controller extends HttpServlet {
    private CommandContainer commandContainer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        commandContainer = new CommandContainer();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter("command");

        Command command = commandContainer.get(commandName);

        command.execute(req, resp);
    }

}
