import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Ranker {
	
	public class College {
		private String name;
		private String location;
		private int CSrank;
		private int timesRank;
		private double totalCost;
		private double fees;
		private double tuition;
		private double additional;
		
		public College(String name, String location, int CSrank, int timesRank, int totalCost, int fees, int tuition, int additional) {
			this.name = name;
			this.location = location;
			this.CSrank = CSrank;
			this.timesRank = timesRank;
			this.totalCost = totalCost;
			this.fees = fees;
			this.tuition = tuition;
			this.additional = additional;
		}
		
		
		
		public College (String [] record) {
			name = record[0];
			location = record[1];
			CSrank = Integer.parseInt(record[2]);
			timesRank = Integer.parseInt(record[3]);
			totalCost = Double.parseDouble(record[4]);
			fees = Double.parseDouble(record [5]);
			tuition = Double.parseDouble(record[6]);
			additional = Double.parseDouble(record[7]);
		}
	}
	
	private List<College> colleges = new ArrayList<College>();
	
	public void loadFile (String filePath) throws FileNotFoundException {
		try {
			File file = new File(filePath);
			Scanner scnr = new Scanner (file);
			while(scnr.hasNextLine()) {
				String record [] = new String [8];
				record = scnr.nextLine().split(",");
				if (record != null && record.length > 0) {
					College college = new College(record);
					colleges.add(college);
				}
			}
			scnr.close();
		}
		catch(FileNotFoundException except) {
			throw except;
		}
	}
	public List<String> getCollegeNames () {
		List<String> list = new ArrayList<String>();
		for(College college : colleges) {
			list.add(college.name);
		}
		return list;
	}
		
	}

