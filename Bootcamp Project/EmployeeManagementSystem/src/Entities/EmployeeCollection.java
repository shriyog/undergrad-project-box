package Entities;

import java.util.ArrayList;
import java.util.List;

/*
 * Collection of employee records with provided methods for manipulation.
 */
public class EmployeeCollection {

	private ArrayList<Employee> employeeList;
	
	public EmployeeCollection() {
		employeeList = new ArrayList<>();
	}
	
	public void addEmployee(Employee employee){
		employeeList.add(employee);
	}
	
	public void removeEmployee(Employee employee){
		employeeList.remove(employee);
	}
	
	public Employee getEmployee(int index){
		return employeeList.get(index);
	}
	
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public void displayEmployeeData(){
		
		if(employeeList.size() == 0){
			System.out.println("No Records Found!");
			return;
		}

		for(Employee employee : employeeList)
		System.out.println(employee.getEmployeeName()+" / "+employee.getEmployeeProject()
		+" / "+employee.getEmployeePractice());
		
	}
}
