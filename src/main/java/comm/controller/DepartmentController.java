package comm.controller;

import comm.service.departments.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;

@Controller
public class DepartmentController extends HttpServlet {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "DepartmentsList";
    }

}