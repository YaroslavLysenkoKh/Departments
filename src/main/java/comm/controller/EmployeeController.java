package comm.controller;

import comm.dto.EmployeeDto;
import comm.entity.Employee;
import comm.exception.IdException;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.service.employee.EmployeeService;
import comm.util.converter.DtoConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@Controller
public class EmployeeController extends HttpServlet {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final DtoConverter<EmployeeDto, Employee> employeeDtoConverter;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, DtoConverter<EmployeeDto, Employee> employeeDtoConverter) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    @RequestMapping(value = "/employee/{departmentId}", method = RequestMethod.GET)
    public String getEmployees(@PathVariable Long departmentId, Model model) {
        try {
            model.addAttribute("employees", employeeService.getAllByDepartmentId(departmentId));
            model.addAttribute("departmentId", departmentId);
        } catch (IdException e) {
            model.addAttribute("message", e.getMessage());
            return "ErrorPage";
        }

        return "EmployeesList";
    }

    @RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam Long employeeId, @RequestParam String departmentId, Model model) {
        try {
            employeeService.deleteById(employeeId);
        } catch (IdException e) {
            model.addAttribute("message", e.getMessage());
            return "ErrorPage";
        }
        return "redirect:/departmentEmployees/" + departmentId;
    }

    @RequestMapping(value = "/employee/form/{departmentId}", method = RequestMethod.GET)
    public String showForm(@PathVariable String departmentId, Model model) {
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("employee", new Employee());
        return "EditEmployee";
    }

    @RequestMapping(value = "/employee/edit/{employeeId}", method = RequestMethod.GET)
    public String getToEdit(@PathVariable Long employeeId, Model model) {
        try {
            model.addAttribute("department", departmentService.getById(employeeId));
        } catch (IdException e) {
            model.addAttribute("message", e.getMessage());
            return "ErrorPage";
        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/departmentEmployees/" + departmentId;
    }


}
