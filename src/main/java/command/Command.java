package command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command extends Serializable {

    public abstract void execute(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException;

}