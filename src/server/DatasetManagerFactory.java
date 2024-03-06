package server;

public class DatasetManagerFactory {

	public DatasetManagerFactory() {
		;
	}

	/*
	 * TODO FIX THIS!!! Cannot return null!
	 */
	public IDatasetManager create(String className) {
		DatasetManager a = new DatasetManager();
		switch(className) {
			case "DatasetManager": return a;
			default: return null;
		}

	}
}
