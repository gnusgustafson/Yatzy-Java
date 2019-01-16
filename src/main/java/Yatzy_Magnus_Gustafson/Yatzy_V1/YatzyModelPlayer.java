package Yatzy_Magnus_Gustafson.Yatzy_V1;

public class YatzyModelPlayer {

	private boolean[] ruleSelected = new boolean[19];  //Sparar v�rdet f�r om en regel har valts.
	private int[][] ruleValue = new int[19][2];   // Po�ng f�r varje regel. F�rsta indexet p� andra dimensionen avg�r po�ng och den andra rest
	private String playerName;
	private int count = 0; //F�r senare boknuskontroll 

	public YatzyModelPlayer(){

	}
	
	public void setPlayerName(String name){
		
		this.playerName = name; //En spelares namn
		
	}
	
	public void setRuleSelected(int x, boolean tf){
		
		//H�r avg�rs om en regel ska v�ljas. Detta g�rs genom "x" som avser indexv�rdet f�r var en regel ligger, ex. ettor
		//ligger p� index [1]. Defaultv�rdet "false" �ndras till true och regeln kan d� inte v�ljas igen. 
		
		this.ruleSelected[x] = tf;
		//Nedanst�ende �r ej valdbara alternativ, ex. "Bonus", "Sum", "Score". Ska inte kunna v�ljas 
		this.ruleSelected[0] = true; 
		this.ruleSelected[7] = true;
		this.ruleSelected[8] = true;
		this.ruleSelected[18] = true;
	}

	 public boolean[] getRuleSelected(){
		 //Returnerar hela regel-arrayen som visar vilka alternativ som �r valda och inte. 		 
	 return this.ruleSelected;
	
	 }
	
	public boolean gameOver(){
		//Kontrollerar om samtliga regler �r "True", dvs. om alla i ruleSelected har valts. 
		//Om s� �r fallet returneras true vilket inneb�r att spelet ska avslutas. 
		
		for(boolean b : this.ruleSelected) if(!b) return false;
		    return true;

	}
	
	
	public void setRuleValue(int result, int remainder, int position){
		//H�r lagras po�ngen p� en given plats i po�ngarrayen ruleValue. 
		//Position f�r att avg�ra vilket, ex. ettor, par. Resultat �r resultatet, remainder �r det som �r kvar f�r bonus. 
		this.ruleValue[position][0] = result;
		this.ruleValue[position][1] = remainder;
		
		this.ruleValue[18][0] = this.ruleValue[18][0] + result; //Den total po�ngst�llningen som visas l�ngst
																//ner f�r varje spelare. 
		
	}
	
	public int[][] getRuleValue(){
		//Po�ng-arrayen returneras. 
		return this.ruleValue;
		
	}
	
	public void bonus(){
		//Kontroll av bonus. 
		
		int temp = 0;
		
		for(int i = 0; i < 7; i++){ //Po�ngen av 1-6 r�knas samman. 
			temp = temp + this.ruleValue[i][0];
		}
		
		if(temp >= 63 && count <= 0){ //Om den �verstiger eller �r lika med 63 blir det bonus. 
			this.ruleValue[8][0] = 50;
			count++;  //Anv�nder count som ett s�tt f�r att bonus inte ska forts�tta att lagras bland po�ngen. 
			this.ruleValue[18][0] = this.ruleValue[18][0] + 50;
		}else{
			return;
		}
		
	}
	
	public void sum(){
		//F�r ber�kning av summa vilket ligger p� index 7 i spelet. 
		int sum = 0;
		
		for(int i = 0; i < 7; i++){
			sum = sum + this.ruleValue[i][0];
		}
			this.ruleValue[7][0] = sum;
		}
		
	public int getTotScore(){
		//F�r ber�kning av Totala po�ngantalet vilket finns p� index 18 i spelet. 
		int temp = 0;
		
		for(int i = 1; i < 7; i++){
			temp = temp + this.ruleValue[i][0];
		}
		
		for(int i = 8; i < 18; i++){
			temp = temp + this.ruleValue[i][0];
		}
		
		
		return temp;
	}
	

	
}

