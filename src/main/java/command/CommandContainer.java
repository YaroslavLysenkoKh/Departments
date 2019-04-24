package command;

import command.impl.employees.DeleteEmployeeCommand;
import command.impl.employees.DepartmentEmployeesCommand;
import command.impl.departments.DepartmentsListCommand;
import command.impl.employees.GetEmployeeCommand;

import java.util.HashMap;
import java.util.Map;


public class CommandContainer {
    private static Map<String, Command> commands = new HashMap();

    {
        put("departments", new DepartmentsListCommand());
        put("departmentEmployees", new DepartmentEmployeesCommand());
        put("deleteEmployee", new DeleteEmployeeCommand());
        put("getToEditEmployee", new GetEmployeeCommand());
    }

    Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            System.out.println("Command not found, name --> " + commandName);
            return null;
        }

        return commands.get(commandName);
    }

    private void put(String commandName, Command command) {
        commands.put(commandName, command);
    }

}