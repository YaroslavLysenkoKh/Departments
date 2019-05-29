package comm.controller;

import comm.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;

@Controller
public class EmployeeController extends HttpServlet {

    @Autowired
    private EmployeeService employeeService;

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


}
