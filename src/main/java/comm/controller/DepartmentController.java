package comm.controller;

import comm.entity.Department;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public String addOrEdit(@ModelAttribute("department") Department department, Model model) {
        try {
            departmentService.addOrUpdate(department);
        } catch (ValidationException e) {
            model.addAttribute("validationErrors", e);
            return "EditDepartment";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/getToEditDepartment/{departmentId}", method = RequestMethod.GET)
    @ResponseBody
    public String getToEdit(@PathVariable String departmentId, Model model) {
        if (departmentId != null && !departmentId.isEmpty()) {
            model.addAttribute("department", departmentService.getById(Long.parseLong(departmentId)));
        }
        return "EditDepartment";
    }

    @RequestMapping("/depForm")
    public String showForm(Model model) {
        model.addAttribute("department", new Department());
        return "EditDepartment";
    }
}
