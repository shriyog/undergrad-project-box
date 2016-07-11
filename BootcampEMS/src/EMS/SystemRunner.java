package EMS;

import java.io.IOException;

import Datacollectors.DataReader;
import Entities.EmployeeCollection;
import Filters.ResultFilter;

/*
 * Main driver program for Employee management system
 * 
 * Creates employee collection
 * Filters them by specified terms
 * Displays results
 * 
 * @Author : Shriyog_Ingale
 */
public class SystemRunner {

	public static void main(String[] args) {
	
		//Data collection
		DataReader reader = new DataReader();
		EmployeeCollection collection = null;
		ResultFilter filter = null;	
		try {
			collection = reader.readCSV();
		} catch (IOException e) {
			System.out.println("Invalid input format");
			e.printStackTrace();
		}
		

		//Filtering
		try {
			//read filter terms for both project and practice
			filter = reader.readProjectAndPracticeFilter();

			//read filter term for project
			//filter = reader.readProjectFilter();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Filters applied: both practice and project
		collection = filter.practiceFilter(filter.projectFilter(filter.nameFilter(collection)));
		
		//Filter applied: project
		//collection = filter.projectFilter(filter.nameFilter(collection));
		collection.displayEmployeeData();
	}

}
