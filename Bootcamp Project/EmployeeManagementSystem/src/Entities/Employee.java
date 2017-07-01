package Entities;

/*
 * Employee model which represents an entity for single record
 */
public class Employee {

		private String employeeName;
		private String employeeProject;
		private String employeePractice;
		
		public Employee(String name, String project, String practice) {
		
			employeeName = name;
			employeeProject= project;
			employeePractice= practice;
		}
		
		
		public void createEmployee(String name, String project, String practice){
			
			employeeName = name;
			employeeProject= project;
			employeePractice= practice;
		}
		
		public String getEmployeeName(){
			return employeeName;
		}
		
		public String getEmployeeProject(){
			return employeeProject;
		}
		
		public String getEmployeePractice(){
			return employeePractice;
		}
		
}
