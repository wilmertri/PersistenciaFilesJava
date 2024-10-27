package repositories;

import entities.Department;
import entities.Employee;
import persistence.FileManage;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class EmployeeRepository {

    private final FileManage fileManage;
    private String delimiterToken;
    protected DepartmentRepository departmentRepository;

    public EmployeeRepository(){
        this.fileManage = new FileManage("employees.txt");
        this.delimiterToken = "|";
        this.departmentRepository = new DepartmentRepository();
    }

    public ArrayList<Employee> getDataList(){
        ArrayList<Employee> employees = null;
        ArrayList<String> lines = this.fileManage.getDataOfFile();
        if (lines != null){
            employees = new ArrayList<>();
            for(String line : lines){
                StringTokenizer tokens = new StringTokenizer(line, "|");
                int id = Integer.parseInt(tokens.nextToken());
                String firstName = tokens.nextToken();
                String lastName = tokens.nextToken();
                double salary = Double.parseDouble(tokens.nextToken());
                String phoneNumber = tokens.nextToken();
                int idDepartment = Integer.parseInt(tokens.nextToken());
                Department department = this.departmentRepository.findEntityById(idDepartment);
                Employee employee = new Employee(id, firstName, lastName, salary, phoneNumber);
                if(department != null){
                    employee.setDepartment(department);
                }
                employees.add(employee);
            }
        }

        return employees;
    }

    public void insertDataEntity(Employee employee){
        this.fileManage.writeFile(this.getLineData(employee));
    }

    public void updateDataEntity(Employee oldEmployee, Employee newEmployee){
        this.fileManage.updateFile(this.getLineData(oldEmployee), this.getLineData(newEmployee));
    }

    public void deleteDataEntity(Employee employee){
        this.fileManage.deleteLineInFile(this.getLineData(employee));
    }

    private String getLineData(Employee employee){
        return employee.getId() + this.delimiterToken + employee.getFirstName() + this.delimiterToken +
                employee.getLastName() + this.delimiterToken + Math.round(employee.getSalary()) + this.delimiterToken +
                employee.getPhoneNumber();
    }
}
