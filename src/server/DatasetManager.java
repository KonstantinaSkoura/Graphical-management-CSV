package server;

import java.util.ArrayList;

import utils.SimpleCSVReader;


public class DatasetManager implements IDatasetManager {
	private String datasetName;
	private String canonicalPath;
	private String originalDatasetName;
	private String newDatasetName;
	private String filterColumnName;
	private String filterValue;
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private ArrayList<String> attributeNames  = new ArrayList<String>();
	private ArrayList<AllData> AllDataset = new ArrayList<AllData>();   //exw ola ta dataset
	
	public DatasetManager() {
		;
	}

	@Override
	public int registerDataset(String datasetName, String canonicalPath) {
		
		if(datasetName==null) {
			System.out.println("The file does not exist");
			return -1;
		}
		
		AllData newData = new AllData();
		SimpleCSVReader reader = new SimpleCSVReader();
		
		data = reader.load(canonicalPath);  //exoume to prwto dataset
		
		for(int i=0;i<AllDataset.size();i++) {			
			if(AllDataset.get(i).getOName().equals(datasetName)) {
				System.out.println("The file already exists");
				return -10;
			}
		}
		
		newData.setOName(datasetName);
		newData.addNewData(data);
		AllDataset.add(newData);		
			
		
		
		return 0;
	}

	@Override
	public String[] retrieveDataset(String datasetName, ArrayList<String[]> data) {
		
		String FLine;   //prwth grammh
		
		for(int i=0;i<AllDataset.size();i++) {   //an einai original
			if(AllDataset.get(i).getOName().equals(datasetName)) {
				for(int k=0;k<AllDataset.get(i).getDATA().size();k++)
					data.add(AllDataset.get(i).getDataOne(k));					
				return (data.get(0));
			}
		}
		
		for(int i=0;i<AllDataset.size();i++) {  //an einai filtrarismeno
			int n = AllDataset.get(i).lengthFname();
			for(int j=0;j<n;j++) {
				if(AllDataset.get(i).getFName(j).equals(datasetName)) {
					for(int k=0;k<AllDataset.get(i).getFData(j).size();k++)
						data.add(AllDataset.get(i).getFDataOne(j, k));
					return (data.get(0));
				}
			}
		
		}
		System.out.println("Dataset does not exist with "+ datasetName);
		return null;
		
	}

	@Override
	public int filterDataset(String originalDatasetName, String newDatasetName, String filterColumnName,
			String filterValue) {
		ArrayList<String[]> temp = new ArrayList<String[]>();  //apothhkeuw ton new pinaka dataset
		ArrayList<String[]> returnedOData = new ArrayList<String[]>();
		int trueCol=0;
		
		 
				for(int i=0;i<AllDataset.size();i++) {			
					if(AllDataset.get(i).getOName().equals(datasetName)) {  //an uparxei to original dataset
				
				for(int j=0;j<AllDataset.get(i).getDATA().get(0).length;j++) {  //euresh ths swsths sthlhs
					if(AllDataset.get(i).getDATA().get(0)[j].equals(filterColumnName)) {
						trueCol = j;
						break;
					}
				}
					
				returnedOData = AllDataset.get(i).getDATA();
				
				for(int j=0;j<returnedOData.size();j++) {
					if(returnedOData.get(j)[trueCol] == filterValue) {
						temp.add(returnedOData.get(j));  //exoume oloklhro ton filtrarismeno pinaka
					}
				}
				
				if(temp==null) {
					System.out.println("in this column with name " + filterColumnName +" does not exist value "+filterValue);
				}
				int numOfFDataset;
				numOfFDataset = AllDataset.get(i).lengthFname();
				AllDataset.get(i).addNewFData(temp);
				
				
				
				return 0;
			}	
					
		}	
			
		return -1;
	}

	@Override
	public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames) {
		
		ArrayList<String[]> temp = new ArrayList<String[]>();  //apothhkeuw ton new pinaka dataset
		ArrayList<String[]> retdata = new ArrayList<String[]>();
		
		String[] firstLine;
		int col1=0,col2=0;
		
		firstLine = retrieveDataset(datasetName,temp);
		
		for(int i=0;i<firstLine.length;i++) {
			if(firstLine[i].equals(attributeNames.get(0))) {
				col1 = i;
			}
			else if(!firstLine[i].equals(attributeNames.get(0)) && i==firstLine.length-1) {
				System.out.println("The first attribute does not exist");
			}
			else if(firstLine[i].equals(attributeNames.get(1))) {
				col2 = i;
			}
			else if(!firstLine[i].equals(attributeNames.get(1)) && i==firstLine.length-1) {
				System.out.println("The second attribute does not exist");
			}
		}
		
		
		for(int i=0;i<temp.size();i++) {	
			String[] twoValues = null;
			twoValues[0] = temp.get(i)[col1];
			twoValues[1] = temp.get(i)[col2];
			retdata.add(twoValues);
		}
		
		
		return retdata;
	}

}
