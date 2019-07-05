package comm.controller;

import comm.entity.Department;
import comm.exception.IdException;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@RestController
public class DepartmentController extends HttpServlet {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Department> main() {
        return departmentService.getAll();
    }

    @RequestMapping(value = "/department/delete", method = RequestMethod.DELETE)
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
            return "EditDepartment";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/department/edit/{departmentId}", method = RequestMethod.GET)
    public Department getToEdit(@PathVariable Long departmentId) {
        return departmentService.getById(departmentId);
    }
}
