package command;

import command.impl.departments.DeleteDepartmentCommand;
import command.impl.departments.DepartmentAddOrEditCommand;
import command.impl.departments.GetDepartmentCommand;
import command.impl.employees.*;
import command.impl.departments.DepartmentsListCommand;

import java.util.HashMap;
import java.util.Map;


public class CommandContainer {
    private Map<String, Command> commands = new HashMap<>();

    private Command defaultCommand = new DepartmentsListCommand();

    {
        put("/", defaultCommand);
        put("/departmentEmployees", new EmployeesListCommand());
        put("/deleteEmployee", new DeleteEmployeeCommand());
        put("/getToEditEmployee", new GetEmployeeCommand());
        put("/addEmployee", new EmployeeAddOrEditCommand());
        put("/deleteDepartment", new DeleteDepartmentCommand());
        put("/getToEditDepartment", new GetDepartmentCommand());
        put("/addDepartment", new DepartmentAddOrEditCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public Command getDefaultCommand() {
        return defaultCommand;
    }

    private void put(String commandName, Command command) {
        commands.put(commandName, command);
    }

}