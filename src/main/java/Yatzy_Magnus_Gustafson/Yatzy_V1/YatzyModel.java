package Yatzy_Magnus_Gustafson.Yatzy_V1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class YatzyModel {

	private int[] holdDice = new int[5];
	private int[] diceValue = new int[5];
	private int count = 3;
	private int currentPlayer = 0;
	private int[][] refValue = new int[19][2];

	private int amountOfPlayers;
	private String[] playerNames;

	YatzyModelPlayer[] player; //Objekt f�r varje spelare
	
	
	// Getters and setters f�r spelar-objekten
	
	public void initPlayers(int x) {   //H�r instansieras objekt-arrayen, dvs. hur m�nga som spelar

		player = new YatzyModelPlayer[x + 1];

		for (int i = 0; i <= x; i++) {

			YatzyModelPlayer p = new YatzyModelPlayer();

			this.player[i] = p;

		}
	}

	public String[] getWinner() {  //H�r h�mtas vinnaren efter att spelet avslutats. 

		
		Map<String, String> map = new HashMap<>();  //F�r att kunna sortera spelare i f�rh�llande till po�ng

		String[] scoreName = new String[this.player.length];  //Skapas f�r att kunna skicka vidare till Controller -> view f�r presentation. 

		for (int i = 0; i < this.player.length; i++) {

			map.put(Integer.toString(this.player[i].getTotScore()), this.playerNames[i]);

		}

		Map<String, String> treeMap = new TreeMap<>(map);

		int i = 0;

		for (Map.Entry<String, String> entry : treeMap.entrySet()) {  //H�r sorteras och sparas po�ngst�llningen i ScoreName 
			scoreName[i] = entry.getValue();
			scoreName[i] = scoreName[i] + " : " + entry.getKey() + " PO�NG";
			i++;
		}


		return scoreName; // Obs!, omv�nd ordning, dvs. f�rloraren f�rst. 
							// Detta eftersom TreeMap sorterar minst f�rst.

	}

	public boolean gameOver() { //Unders�ker om den sista spelaren har valt alla valbara 
								//alternativ. D� returneras false till controller->view och spelet avslutas
		return this.player[this.amountOfPlayers].gameOver();
	}

	public int[][] checkSelectedScore() { //Anv�nds f�r att kontrollera vilka alternativ som den spelare n�st i 
											//turordningen har valt. 
		return this.player[this.currentPlayer].getRuleValue();
	}

	public int[][] checkAllPlayerScore(int x) {
												//G�r precis samma som ovanst�ende, fast p� ett annat s�tt.
												//Denna skrevs l�ngt innan ovanst�ende, d�rav tv� olika som g�r samma sak. 
		return this.player[x].getRuleValue();

	}

	public void setSelectedScore(int result, int remainder, int position) {
												// Anv�nds f�r att lagra ett givet resultat i spelarobjektet, ex. sexor, triss. 
												//Result �r resultatet, remainder �r det som saknas f�r att uppn� bonus, position �r f�r ange 
												//Den givna regeln, ex. sexor eller triss. 
		this.player[this.currentPlayer].setRuleValue(result, remainder, position);
	}

	public boolean[] checkSelectedRule() {
										//H�mtar hem spelarobjektet boolean-array som anger vilka alternativ som har valts och inte.
										//Om ex. ettor har valts (dvs. index[1]) ska denna vara true i arrayen
		return this.player[this.currentPlayer].getRuleSelected(); 

	}

	public void setSelectedRule(boolean b, int x) {
											//H�r s�tts en vald regel till true vilket inneb�r att den inte kan v�ljas igen
											//f�r en given spelare. 
		this.player[this.currentPlayer].setRuleSelected(x, true);

	}

	/// Getters och setters f�r instansvariablerna i YatzyModel-klassen

	public int[][] getRefValue() {
		//Arrayen anv�nds f�r att efter varje slag ge po�ngf�rslag p� samtliga icke-valda alternativ. 
		return this.refValue;
	}

	public int getCurrentPlayer() {
		//Ger den spelare som st�r n�st p� turordningen-
		return this.currentPlayer;
	}

	/**
	 * 
	 * @return
	 */
	public int nextPlayer() {
		//H�r s�tts n�sta spelare i turordningen genom att �ka med 1. Notera att f�rsta spelaren alltid �r noll. 
		//
		this.currentPlayer = this.currentPlayer + 1;

		if (this.currentPlayer > this.playerNames.length - 1) { //Den f�r inte returnera ett index f�r mycket. 
			this.currentPlayer = 0; //Om den g�r �ver s� s�tts den till 0 vilket inneb�r att det �r f�rsta spelarens tur igen. 
		}

		return this.currentPlayer;

	}

	public void setPlayerIndex(int amount) {

		this.playerNames = new String[amount + 1]; //Antal index f�r spelarnamn s�tts. 

	}

	public String[] getPlayerArray() {

		return this.playerNames; //Namn-arrayen med spelarnas namn returneras. 
	}

	public String getPlayerName(int index) {

		return this.playerNames[index]; //Samma som ovan fast enskild spelare vilket avg�rs genom index. 
	}

	public void setPlayerName(String name, int index) {
		//H�r s�tts namnen som skrivs in i b�rjan av spelet via f�lten. 
		//
		this.playerNames[index] = name;
		this.player[this.currentPlayer].setPlayerName(name);

	}

	public void setAmountOfPlayers(int x) {
		//Beroende p� hur m�nga spelare som v�ljs p� f�rsta sidan, f�r denna sitt v�rde d�refter. 
		//
		this.amountOfPlayers = x;
		return;

	}

	public int getAmountOfPlayers() {
		//
		//H�r h�mtas ovanst�ende v�rde. 
		return this.amountOfPlayers;
	}

	 public int getHoldDice(int x) {
			//
			//Anv�nds i syfte att kontrollera om en t�rning �r markerad eller inte. 
		 return holdDice[x];
	 }

	public void setHoldDice(int x) {
		//Anv�nds f�r att markera och avmarkera t�rningar. 
		//
		if (this.holdDice[x] == 0) {
			this.holdDice[x] = 1;
		} else {
			this.holdDice[x] = 0;
		}
	}

	public void resetHoldDice() {
		//
		//Anv�nds f�r att �terst�lla samtliga t�rningar f�r att inte h�llas. 
		for (int i = 0; i < this.holdDice.length; i++) {
			this.holdDice[i] = 0;
		}

	}

	public void resetDiceValue() {
		//
		//V�rdet som t�rningarna haft (1-6) �terst�lls efter varje omg�ng
		for (int i = 0; i < this.diceValue.length; i++) {
			this.diceValue[i] = 0;
		}
	}

	public void decreaseCount() {
		this.count--; //Antal kast minskas
	}

	public int getCount() {
		return this.count; //Hur m�nga kast som �terst�r
	}

	public void setCount() {
		this.count = 3; //�terst�ller antal kast vid ny omg�ng. 
	}

	// Generera slumpm�ssiga tal.

	public int getRandNum(int dice) { //T�rningarna f�r sitt slump-v�rde. 

		int randNum = (int) (Math.random() * 7);

		if (randNum == 0)
			randNum = 1;

		saveDiceValue(randNum, dice);

		return randNum;

	}


	public void saveDiceValue(int randNum, int dice) {

		if (this.holdDice[dice] == 0) { // Om t�rningen �r markerad s� ska den
										// inte f� ett nytt v�rde. Markerad inneb�r i sammanhanget v�rdet "1"
			this.diceValue[dice] = randNum;
		}
	}

	// H�r k�r vi kontroll av samtliga valbara alternativ 

	public void standardValues(int value, int choice) { 
		//Kontroll av 1-6. Notera att samtliga funktioner tar emot ett int-v�rde. Detta eftersom vi antingen kan v�lja att lagra v�rdet
		//eller bara kontrollera hur mycket po�ng n�got ger. 

		int remainder = 0;  //Anv�nds f�r at ber�kna �terst�ende po�ng till bonus i 1-6
		int score = 0;

		for (int i = 0; i < this.diceValue.length; i++) {
			if (this.diceValue[i] == (value - 1)) {
				score = score + diceValue[i];
			}
		}

		remainder = score - ((value - 1) * 3);

		if (choice < 1) { //H�r skickas den till refValue som anv�nds f�r att visa hur mycket po�ng en spelare kan f�
			this.refValue[value - 1][0] = score;
			this.refValue[value - 1][1] = remainder;
		} else { //H�r LAGRAS po�ngen till ett givet alternativ. Spelaren kan d� inte v�lja samma alternativ igen. 
			player[this.currentPlayer].setRuleValue(score, remainder, value - 1);
			player[this.currentPlayer].bonus(); //Kontrollerar om spelaren uppn�tt bonus
			player[this.currentPlayer].sum();  //Summan f�r v�rdena 1-6 sparas f�r vidare presentation. 
		}
	}

	public void checkPair(int choice) { //Kontroll av par

		int temp = 0;
		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		for (int i = 0; i < this.diceValue.length - 1; i++) {

			if (temp2[i] == temp2[i + 1]) {

				if ((temp2[i] + temp2[i + 1]) > temp) {
					temp = (temp2[i] + temp2[i + 1]);
				}

			}

		}

		if (choice < 1) {
			this.refValue[9][0] = temp;
			this.refValue[9][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 9);
		}

		// 9

	}

	public void checkTwoPair(int choice) { //Kontroll av tv�par

		int temp = 0;
		int counter = 0;
		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		for (int i = 0; i < this.diceValue.length - 1; i++) {

			if (temp2[i] == temp2[i + 1]) {

				if ((temp2[i] + temp2[i + 1]) > temp) {
					temp = temp + (temp2[i] + temp2[i + 1]);
					counter++;
				}

			}

		}

		if (counter == 1) {
			temp = 0;
		}

		if (choice < 1) {
			this.refValue[10][0] = temp;
			this.refValue[10][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 10);
		}

		// 10
	}

	public void checkTretal(int choice) { //Kontroll av tretal

		int temp = 0;
		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		for (int i = 0; i <= 2; i++) {
			if (temp2[i] == temp2[i + 1] && temp2[i] == temp2[i + 2]) {
				if ((temp2[i] + temp2[i + 1] + temp2[i + 2]) > temp) {
					temp = (temp2[i] + temp2[i + 1] + temp2[i + 2]);
				}
			}
		}

		if (choice < 1) {
			this.refValue[11][0] = temp;
			this.refValue[11][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 11);
		}

		// 11
	}

	public void checkFyrtal(int choice) { //Kontroll av fyrtal

		int temp = 0;
		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		if (temp2[0] == temp2[1] && temp2[0] == temp2[2] && temp2[0] == temp2[3]) {
			temp = temp + (temp2[0] + temp2[1] + temp2[2] + temp2[3]);

		} else if (temp2[1] == temp2[2] && temp2[1] == temp2[3] && temp2[1] == temp2[4]) {
			temp = temp + (temp2[1] + temp2[2] + temp2[3] + temp2[4]);
		}

		if (choice < 1) {
			this.refValue[12][0] = temp;
			this.refValue[12][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 12);
		}

		// 12

	}

	public void checkFullHouse(int choice) { // //Kontroll av k�k

		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);
		int temp = 0;

		if (temp2[0] == temp2[1] && temp2[0] == temp2[2] && temp2[3] == temp2[4]) {
			temp = temp + (temp2[0] + temp2[1] + temp2[2] + temp2[3] + temp2[4]);

		} else if (temp2[2] == temp2[3] && temp2[2] == temp2[4] && temp2[0] == temp2[1]) {
			temp = temp + (temp2[0] + temp2[1] + temp2[2] + temp2[3] + temp2[4]);
		}

		if (choice < 1) {
			this.refValue[13][0] = temp;
			this.refValue[13][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 13);
		}

		// 13

	}

	public void checkLitenStege(int choice) { //Kontroll av lilla stege

		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		int temp = 0;

		if (temp2[0] == 1 && temp2[1] == 2 && temp2[2] == 3 && temp2[3] == 4 && temp2[4] == 5) {
			temp = 15;
		} else {
			temp = 0;
		}

		if (choice < 1) {
			this.refValue[14][0] = temp;
			this.refValue[14][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 14);
		}

		// 14

	}

	public void checkStorStege(int choice) {//Kontroll av st�r stege

		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);
		int temp = 0;

		if (temp2[0] == 2 && temp2[1] == 3 && temp2[2] == 4 && temp2[3] == 5 && temp2[4] == 6) {
			temp = 20;
		} else {
			temp = 0;
		}

		if (choice < 1) {
			this.refValue[15][0] = temp;
			this.refValue[15][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 15);
		}

	}

	public void checkChans(int choice) { //Kontroll av chans

		int temp = 0;
		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		for (int i = 0; i < this.diceValue.length; i++) {
			temp = temp + temp2[i];
		}

		if (choice < 1) {
			this.refValue[16][0] = temp;
			this.refValue[16][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 16);
		}

	}

	public void checkYatzy(int choice) { //Kontroll av yatzy

		int temp = 0;
		int[] temp2 = this.diceValue.clone();
		Arrays.sort(temp2);

		if (temp2[0] == temp2[1] && temp2[0] == temp2[2] && temp2[0] == temp2[3] && temp2[0] == temp2[4]) {

			temp = 50;

		} else {
			temp = 0;
		}

		if (choice < 1) {
			this.refValue[17][0] = temp;
			this.refValue[17][1] = 0;
		} else {
			player[this.currentPlayer].setRuleValue(temp, 0, 17);
		}

	}

}
