package repositories;

import entities.Department;
import persistence.FileManage;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private final FileManage fileManage;
    private String delimiterToken;

    public DepartmentRepository(){
        this.fileManage = new FileManage("departments.txt");
        this.delimiterToken = "|";
    }

    public List<Department> getDataList()
    {
        List<Department> departments = new ArrayList<>();
        List<String> lines = this.fileManage.getDataOfFile();
        if(lines != null){
            for (String line : lines){
                String[] tokens = line.split("\\|");
                try {
                    int id = Integer.parseInt(tokens[0].trim());
                    String name = tokens[1].trim();

                    Department department = new Department(id, name);
                    departments.add(department);
                }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        }

        return departments;
    }

    public Department findEntityById(int id)
    {
        List<Department> departments = this.getDataList();
        for (Department d : departments){
            if(d.getId() == id){
                return d;
            }
        }
        return null;
    }
}
