package test_project;



import java.util.Date;






public class MySQL extends MySQL_Base {
	
	
	MySQL_Base base = new MySQL_Base();
	
	public MySQL() {
		super();
	}
	
	

	
	public void addUnfall(String Ort, Date datum, String Tag, int Anzahl) {
		update("INSERT INTO `unfall_test`(`Ort`,`Datum`,`Tag`,`Anzahl`) VALUES ('"+Ort+"','"+datum+"','"+Tag+"','"+Anzahl+"')");
	}	
	
	
	public void getUnfall(){
		
	}
	}