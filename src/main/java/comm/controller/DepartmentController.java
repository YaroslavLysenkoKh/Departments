package comm.controller;

import comm.dto.DepartmentDto;
import comm.entity.Department;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.util.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

@Controller
public class DepartmentController extends HttpServlet {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DtoConverter<DepartmentDto, Department> dtoConverter;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("DepartmentsList", "departments", departmentService.getAll());
    }

    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    public String delete(@RequestParam(name = "departmentId") String id) {
        Long lId = Long.parseLong(id);
        departmentService.deleteById(lId);
        return "redirect:/";
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("department") DepartmentDto departmentDto, Model model) {
        Department department = dtoConverter.convert(departmentDto);
        try {
            departmentService.addOrUpdate(department);
        } catch (ValidationException e) {
            model.addAttribute("validationErrors", e);
            model.addAttribute("department", new Department());
            return "EditDepartment";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/getToEditDepartment/{departmentId}", method = RequestMethod.GET)
    public String getToEdit(@PathVariable String departmentId, Model model) {
        if (departmentId != null && !departmentId.isEmpty()) {
            model.addAttribute("department", departmentService.getById(Long.parseLong(departmentId)));
        } else {
            return "ErrorPage";
        }
        return "EditDepartment";
    }

    @RequestMapping("/depForm")
    public ModelAndView showForm() {
        return new ModelAndView("EditDepartment", "department", new Department());
    }
}
