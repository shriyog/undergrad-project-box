package Filters;

import Entities.Employee;
import Entities.EmployeeCollection;

/*
 * Filter class that houses filter information
 * Contains various methods for filtering depending upon input filter terms
 * 
 * TODO: Build hashes around filter terms to optimize searching.
 */

public class ResultFilter {

	private String nameFilter;
	private String projectFilter;
	private String practiceFilter;
	
	
	public ResultFilter(String nameFilter){
		this.nameFilter = nameFilter;
	}
		
	public ResultFilter(String nameFilter, String projectFilter){

		this.nameFilter = nameFilter;
		this.projectFilter = projectFilter;

	}
	
	public ResultFilter(String nameFilter, String projectFilter, String practiceFilter){
		
		this.nameFilter = nameFilter;
		this.projectFilter = projectFilter;		
		this.practiceFilter = practiceFilter;
	}
	
	
	public EmployeeCollection nameFilter(EmployeeCollection list){

		EmployeeCollection results = new EmployeeCollection();

		for(Employee employee: list.getEmployeeList()){
			
			if(employee.getEmployeeName().toLowerCase().startsWith(nameFilter.toLowerCase())){
				results.addEmployee(employee);
			}
		}


		return results;	
	}

	public EmployeeCollection projectFilter(EmployeeCollection list){

		boolean excluded = false, included = false;

		if(projectFilter.startsWith("!")){
			excluded = true;
			projectFilter = projectFilter.substring(1);
		}
		else
			included = true;

		EmployeeCollection results = new EmployeeCollection();

		for(Employee employee: list.getEmployeeList()){

			if(excluded && (!employee.getEmployeeProject().equalsIgnoreCase(projectFilter)))
				results.addEmployee(employee);

			if(included && employee.getEmployeeProject().equalsIgnoreCase(projectFilter))
				results.addEmployee(employee);

		}


		return results;	
	}

	public EmployeeCollection practiceFilter(EmployeeCollection list){

		boolean excluded = false, included = false;

		if(practiceFilter.startsWith("!")){
			excluded = true;
			practiceFilter = practiceFilter.substring(1);
		}
		else
			included = true;

		EmployeeCollection results = new EmployeeCollection();

		for(Employee employee: list.getEmployeeList()){

			if(excluded && (!employee.getEmployeePractice().equalsIgnoreCase(practiceFilter)))
				results.addEmployee(employee);

			if(included && employee.getEmployeePractice().equalsIgnoreCase(practiceFilter))
				results.addEmployee(employee);

		}

		return results;	
	}

}
