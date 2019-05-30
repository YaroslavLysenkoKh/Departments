package comm.controller;

import comm.dto.EmployeeDto;
import comm.entity.Employee;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.service.employee.EmployeeService;
import comm.util.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@Controller
public class EmployeeController extends HttpServlet {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DtoConverter<EmployeeDto, Employee> employeeDtoConverter;

    @RequestMapping(value = "/departmentEmployees/{departmentId}", method = RequestMethod.GET)
    public String getEmployees(@PathVariable String departmentId, Model model) {
        Long tmpId = Long.parseLong(departmentId);
        model.addAttribute("employees", employeeService.getAllByDepartmentId(tmpId));
        model.addAttribute("departmentId", tmpId);
        return "EmployeesList";
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam(name = "employeeId") String employeeId, @RequestParam(name = "departmentId") String departmentId) {
        Long tmpEmployeeId = Long.parseLong(employeeId);
        Long tmpDepartmentId = Long.parseLong(departmentId);
        employeeService.deleteById(tmpEmployeeId);
        return "redirect:/departmentEmployees/" + tmpDepartmentId;
    }

    @RequestMapping(value = "/departmentEmployees/empForm/{departmentId}", method = RequestMethod.GET)
    public String showForm(@PathVariable String departmentId, Model model) {
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("employee", new Employee());
        return "EditEmployee";
    }

    @RequestMapping(value = "/departmentEmployees/getToEditEmployee/{employeeId}", method = RequestMethod.GET)
    public String getToEdit(@PathVariable String employeeId, Model model) {
        if (employeeId != null && !employeeId.isEmpty()) {
            model.addAttribute("department", departmentService.getById(Long.parseLong(employeeId)));
        } else {
            return "ErrorPage";
        }
        return "EditEmployee";
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute EmployeeDto employeeDto, Model model) {
        Employee employee = employeeDtoConverter.convert(employeeDto);
        try {
            employeeService.addOrUpdate(employee);
        } catch (ValidationException e) {
            model.addAttribute("validationErrors", e);
            return "EditEmployee";
        }
        return "redirect:/departmentEmployees/" + employeeDto.getDepartmentId();
    }


}
