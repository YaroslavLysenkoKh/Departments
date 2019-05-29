package comm.controller;

import comm.service.departments.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    public String delete(@RequestParam(name = "departmentId") String id) {
        Long lId = Long.parseLong(id);
        departmentService.deleteById(lId);
        return "redirect:/";
    }
}
