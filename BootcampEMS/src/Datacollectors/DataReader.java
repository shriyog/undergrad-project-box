package Datacollectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Entities.Employee;
import Entities.EmployeeCollection;
import Filters.ResultFilter;

/*
 * Reader class to receive user input from STDIN
 * TODO: use csv file as data source
 */
public class DataReader {

	private EmployeeCollection collection;
	private BufferedReader br;

	public DataReader() {
	
			collection = new EmployeeCollection();
			br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	/* creates employee collection by reading data in csv format from console stdin
     * Provide input from stdin
     * Input format : 
     * Line 1: Number of records N
     * Next N Lines : All N records
     * Next line : Search term
     * Next line : Filter term
     
     *  TODO: read from csv file
    */
	public EmployeeCollection readCSV() throws IOException {
		
		String line = br.readLine();
        int N = Integer.parseInt(line);
        
        String record = "";
        for (int i = 0; i < N; i++) {
            record = br.readLine();
            String [] tmp = record.split(",");
            collection.addEmployee(new Employee(tmp[0], tmp[1], tmp[2]));
        }
		
		return collection;
	}
	
	/*
	 * Reads filter input terms for name search with project filter
	 * 
	 */
	public ResultFilter readProjectFilter() throws IOException {
		
        String nameFilter = br.readLine();
        String projectFilter = br.readLine();
        
		return new ResultFilter(nameFilter, projectFilter);
	}

	
	/*
	 * Reads filter input terms for name search with project & practice filters
	 * 
	 */
	public ResultFilter readProjectAndPracticeFilter() throws IOException {
		
        String nameFilter = br.readLine();
        String practiceFilter = br.readLine();
        String projectFilter = br.readLine();
        
		return new ResultFilter(nameFilter, projectFilter, practiceFilter);
	}
}
