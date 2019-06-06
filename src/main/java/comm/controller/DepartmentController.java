package comm.controller;

import comm.entity.Department;
import comm.exception.IdException;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

@Controller
public class DepartmentController extends HttpServlet {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("DepartmentsList", "departments", departmentService.getAll());
    }

    @RequestMapping(value = "/department/delete", method = RequestMethod.POST)
    public String delete(@RequestParam Long departmentId, Model model) {
        try {
            departmentService.deleteById(departmentId);
        } catch (IdException e) {
            model.addAttribute("message", e.getMessage());
            return "ErrorPage";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("department") Department department, Model model) {
        try {
            departmentService.addOrUpdate(department);
        } catch (ValidationException e) {
            model.addAttribute("validationErrors", e);
            model.addAttribute("department", new Department());
            return "EditDepartment";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/department/{departmentId}", method = RequestMethod.GET)
    public String getToEdit(@PathVariable Long departmentId, Model model) {
        model.addAttribute("department", departmentService.getById(departmentId));
        return "EditDepartment";
    }

    @RequestMapping("/department")
    public ModelAndView showForm() {
        return new ModelAndView("EditDepartment", "department", new Department());
    }
}
