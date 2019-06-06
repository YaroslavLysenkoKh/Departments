package comm.controller;

import comm.entity.Employee;
import comm.exception.IdException;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.service.employee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EmployeeController extends HttpServlet {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @InitBinder
    private void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
    }

    @RequestMapping(value = "/employee/{departmentId}", method = RequestMethod.GET)
    public String getEmployees(@PathVariable Long departmentId, Model model) {
        model.addAttribute("employees", employeeService.getAllByDepartmentId(departmentId));
        model.addAttribute("departmentId", departmentId);
        return "EmployeesList";
    }

    @RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam Long employeeId, @RequestParam Long departmentId, Model model) {
        try {
            employeeService.deleteById(employeeId, departmentId);
        } catch (IdException e) {
            model.addAttribute("message", e.getMessage());
            return "ErrorPage";
        }
        return "redirect:/employee/" + departmentId;
    }

    @RequestMapping(value = "/employee/form/{departmentId}", method = RequestMethod.GET)
    public String showForm(@PathVariable Long departmentId, Model model) {
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("employee", new Employee());
        return "EditEmployee";
    }

    @RequestMapping(value = "/employee/edit/{employeeId}", method = RequestMethod.GET)
    public String getToEdit(@PathVariable Long employeeId, Model model) {
        model.addAttribute("department", departmentService.getById(employeeId));
        return "EditEmployee";
    }

    @RequestMapping(value = "/employee/add", method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute Employee employee, @RequestParam Long departmentId, Model model) {
        try {
            employeeService.addOrUpdate(employee, departmentId);
        } catch (ValidationException e) {
            model.addAttribute("validationErrors", e);
            return "EditEmployee";
        } catch (IdException e) {
            model.addAttribute("message", e.getMessage());
            return "ErrorPage";
        }
        return "redirect:/employee/" + departmentId;
    }


}
