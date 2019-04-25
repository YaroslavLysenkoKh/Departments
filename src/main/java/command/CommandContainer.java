package command;

import command.impl.departments.DeleteDepartmentCommand;
import command.impl.employees.*;
import command.impl.departments.DepartmentsListCommand;

import java.util.HashMap;
import java.util.Map;


public class CommandContainer {
    private static Map<String, Command> commands = new HashMap();

    static {
        put("departments", new DepartmentsListCommand());
        put("departmentEmployees", new EmployeesListCommand());
        put("deleteEmployee", new DeleteEmployeeCommand());
        put("getToEditEmployee", new GetEmployeeCommand());
        put("addEmployee", new EmployeeAddOrEditCommand());
        put("deleteDepartment", new DeleteDepartmentCommand());
//        put("toEdit", new TransferToAddMenu());
    }

    Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            System.out.println("Command not found, name --> " + commandName);
            return null;
        }

        return commands.get(commandName);
    }

    private static void put(String commandName, Command command) {
        commands.put(commandName, command);
    }

}