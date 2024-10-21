import entities.Employee;
import repositories.EmployeeRepository;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        ArrayList<Employee> AllEmployees = employeeRepository.getDataList();
        Employee deleteEmployee = AllEmployees.get(14);
        employeeRepository.deleteDataEntity(deleteEmployee);
        /*
        Employee newEmployee = new Employee(17, "Monica", "Belluci", 120, "3652148796");
        employeeRepository.insertDataEntity(newEmployee);
        ArrayList<Employee> AllEmployees = employeeRepository.getDataList();
        Employee oldEmployee = AllEmployees.get(7);
        System.out.println("Update employee: " + oldEmployee.getFullName());
        Employee updateEmployee = new Employee(
                oldEmployee.getId(),
                oldEmployee.getFirstName(),
                "Ramos",
                1200,
                oldEmployee.getPhoneNumber()
        );
        employeeRepository.updateDataEntity(oldEmployee, updateEmployee);
        */

        ArrayList<Employee> employees = employeeRepository.getDataList();
        System.out.println("Info Employees");
        for (Employee e: employees){
            System.out.println("-----------------------------");
            System.out.println("Name: " + e.getFullName());
            System.out.println("cellPhone: " + e.getPhoneNumber());
            System.out.println("Salary: " + e.getSalary());
        }
    }
}