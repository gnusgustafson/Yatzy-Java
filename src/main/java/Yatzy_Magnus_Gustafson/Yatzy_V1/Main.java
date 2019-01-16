package Yatzy_Magnus_Gustafson.Yatzy_V1;

public class Main {
	
	public static void main(String args[]){
		

		
		YatzyModel theModel = new YatzyModel();
		
		YatzyView theView = new YatzyView();
		
		YatzyController c = new YatzyController(theModel, theView);
		
	
		

		
	}

}
