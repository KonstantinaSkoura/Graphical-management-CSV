package server;

import java.util.ArrayList;

public class AllData {
	private ArrayList<String[]> DATA = new ArrayList<String[]>();  //gia ta original data
	private String o_name;
	
	private ArrayList<ArrayList<String[]>> F = new ArrayList<ArrayList<String[]>>();  //ta filtrarismena data
	private String[] f_name;
	
	public AllData(){
		;
	}
	
	public void addNewData(ArrayList<String[]> d) {
		DATA = d;
		System.out.println(DATA.size());
	}

	public ArrayList<String[]> getDATA(){
		return DATA;
	}
	
	public String[] getDataOne(int i) {
		return DATA.get(i);
	}
	
	public void addNewFData(ArrayList<String[]> d) {
		F.add(d);
	}
	
	public void setOName(String name) {
		o_name = name;
	}
	
	public String getOName() {
		return(o_name);
	}
	
	public void setFName(String name, int n) {
		f_name[n] = name;
	}
	
	public int lengthFname() {
		return (f_name.length);
	}
	
	public String getFName(int n) {
		return(f_name[n]);
	}
	
	public ArrayList<String[]> getFData(int n){
		return(F.get(n));
	}
	
	public String[] getFDataOne(int n, int i){
		return(F.get(n).get(i));
	}
	
}
