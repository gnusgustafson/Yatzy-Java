package Yatzy_Magnus_Gustafson.Yatzy_V1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class YatzyController{
	
	private YatzyModel theModel;
	private YatzyView theView;

	public YatzyController(YatzyModel theModel, YatzyView theView){
		
		this.theModel = theModel;
		this.theView = theView;

		this.theView.addThrowListener(new YatzyListener());
		
	}
	
	class YatzyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == theView.startGame){  //Spelet startas efter att namn f�r samtliga spelare angivits. 
				
				theView.player_name.setText("Du har: " + theModel.getCount() + " kast kvar"); //Visar hur m�nga kast en spelare har kvar
				theView.setPlayers.setVisible(false); //Panelen sl�cks ner och panelen d�r sj�lva spelet finns presenteras p� raden nedanf�r. 
				theView.addGameField();

				theView.hideDices(); //T�rningarnas default l�ge visas, vilket �r Y A T Z Y
				
				for(int i = 0; i < theModel.getAmountOfPlayers()+1; i++){ // H�r lagras samtliga namn p� spelarna -> Model
					theModel.setPlayerName(theView.playerName[i].getText(), i); //Namnen h�mtas fr�n inmatningsf�lten 
				}
			
				theView.setRules(theModel.getAmountOfPlayers(), theModel.getPlayerArray()); //H�r skickas namn och antal spelare till regelf�ltet i view som m�las upp. 
				theView.showThrows.setText(theModel.getPlayerName(0)); //Visar namnet p� den f�rsta spelaren. 
				
				deactivePlayer(); //St�nger av m�jligheten f�r SAMTLIGA spelare att kunna v�lja ett alternativ, ex. sexor, par. 
				activePlayer(); //Nu aktiveras den spelaren som st�r p� tur. Den spelaren kan med andra ord baraa v�lja alternativen fr�n sitt egna f�lt. 

			}
			
			if (e.getSource() == theView.throw_button) {  //Om spela-knappen anv�nds. 
				
				theModel.decreaseCount();  //Antal kast minskas
				theView.showDices(); //Nu visas de riktiga t�rningarna, dvs. inte Y A T Z Y
				
				for (int i = 0; i < 5; i++) {

					if (theModel.getHoldDice(i) == 0) { //om en t�rning inte �r vald s� ska den bytas

						theView.dices[i].setIcon(new ImageIcon("images/" + theModel.getRandNum(i) + ".png"));

					}

				}
				
				theView.showThrows.setText(theModel.getPlayerName(theModel.getCurrentPlayer())); // Visar den aktuella spelarens namn
				theView.player_name.setText("Du har: " + theModel.getCount() + " kast kvar"); //Samt hur m�nga kast denne har kvar. 

				if(theModel.getCount() == 0){ //Om en spelare har kastat tre g�nger s� sl�cks knappen f�r att kasta igen.  
					theView.throw_button.setEnabled(false);
				}
				
				//Nedanst�ende kod anv�nds f�r att kolla m�jliga po�ng efter varje kast. 
				//Argumentet "0" medf�r att inga po�ng sparas utan visas enbart p� knapparna f�r varje valbart alternativ. 
				
				for(int i = 1; i <= 7; i++){  
					
					theModel.standardValues(i, 0);
					
				}
				
				theModel.checkPair(0);
				theModel.checkTwoPair(0);
				theModel.checkTretal(0);
				theModel.checkFyrtal(0);
				theModel.checkFullHouse(0);
				theModel.checkLitenStege(0);
				theModel.checkStorStege(0);
				theModel.checkChans(0);
				theModel.checkYatzy(0);

			theView.checkPossibleScore(theModel.getRefValue(), theModel.checkSelectedRule(), theModel.getCurrentPlayer()); 
			//Anrop till view f�r att rita upp potentiella po�ng efter varje kast. 	
			}

			//////////// H�r kontrolleras vilka knappar som har trycks. 
			
			/*
			 * F�r att slippa l�gga in en lyssnare f�r varje t�rning, po�ngalternativ och alternativen f�r antal spelare 
			 * s� gjorde jag nedanst�ende l�sning. De tre lyssnarna kontrollerar vid varje knapptryck om en av "deras" tillh�rande 
			 * knappar har anv�nts. Detta g�rs genom att skicka objektet "e" (dvs. den faktiska knappen som anv�ndes) som argument till en funktion som loopar igenom objektet.
			 * Loopen kontrollerar d�refter om objektet "e" matchar en given knapp som befinner sig i en array i View, ex. po�ngalternativen.  
			 *  D�refter kan den specifika knappen anv�ndas, ex. om jag trycker p� en t�rning s� ska den markeras/avmarkeras. 
			 *  Genom denna l�sning kunde jag ta bort MYCKET kod - vilket f�rvisso har ersatts av spaltmeter med kommentarer =)
			 *  
			 */
			
			if(diceClicked(e.getSource()) == true){  //Om en t�rningsknapp har trycks p�, returneras true 
				findDice(e.getSource()); // och sedan s�ks den korrekta knappen upp. 		
			}
			
			if(playerButtonClicked(e.getSource()) == true){ // Samma som ovan fast f�r antal spelare. 
				findPlayerButton(e.getSource());	
			}
			
			if(rulesButtonClicked(e.getSource()) == true){ // Samma som ovan fast f�r regelknapparna. 
			findRulesButton(e.getSource());
			}

		}
	}
	
	public boolean playerButtonClicked(Object c){ //Object c �r den knapp som har anv�nts. 
	
		 for (int x = 0; x < theView.amountOfPlayers.length; x++) {
	           
	           if (c.equals(theView.amountOfPlayers[x])) { //Kontrolleras sedan mot arrayen som h�ller samtliga knappar f�r antal spelare.  
	        	   theModel.setPlayerIndex(x);			//Om den hittas s� s�tts antal spelare och arrayen f�r spelarobjekten f�r sitt indexv�rde vilket g�r 
	        	   theModel.initPlayers(x);				//att de kan initieras. 
	        	   theModel.setAmountOfPlayers(x);
	        	   
	           	return true;
	           }
	       
	   }
			 
			 return false;  //Om den inte hittas, dvs. om det �r en annan knapp som anv�nts, returneras FALSE 
	}						//Vilket inneb�r att ingen kod kopplat till knapparna f�r antal spelare k�rs. 
	
	public void findPlayerButton(Object c){
		
		theView.playerAmount.setVisible(false);					//Om en knapp f�r antal spelare har anv�nts s� kommer detta block
		theView.addSetPlayers(theModel.getAmountOfPlayers());	//att k�ras. 
	}
	
	public boolean rulesButtonClicked(Object c) {

		for (int i = 0; i < theView.scoreChoice.length; i++) {

			for (int x = 1; x < theView.scoreChoice[i].length; x++) { //Samma princip som ovan fast med po�ngalternativen

				if (c.equals(theView.scoreChoice[i][x])) {

					return true;
				}

			}
		}
		return false;
	}
	
	
	public void findRulesButton(Object c) { // Om en knapp f�r po�nalternativ har anv�nts. 
											

		for (int i = 0; i < theModel.getAmountOfPlayers() + 1; i++) { 

			for (int x = 0; x < theView.rulesButton.length; x++) {

				if (c.equals(theView.scoreChoice[i][x])) {

					if (theModel.getCount() == 3) { //Ett po�ngalternativ kan inte v�ljas om en spelare inte har kastat minst en g�ng. 
						JOptionPane.showMessageDialog(null, "Du m�ste kasta en g�ng f�rst"); 
					} else {

						theView.throw_button.setEnabled(true); //Om ett po�ngalternativ har valts kan kasta-knappen anv�ndas igen
																
						theModel.setCount(); //Antal kast �terst�lls till 3
						theView.hideDices(); //Y A T Z Y visas ist�llet f�r t�rningarna. 

						for (int z = 0; z < 5; z++) { //De t�rningar som har markerats blir avmarkerade. 

							theView.dices[z].setBorder(BorderFactory.createEmptyBorder());

						}

						/*
						 * Nu blir det lite klurigt, men jag ska f�rs�ka g�ra mitt b�sta att f�rklara. Eftersom "Object c" �r en kopia av
						 * det anv�nda po�ngalternativet som sedan loopas igenom och j�mf�ras med arrayen scoreChoice i view, s� vet vi exakt vilken ber�kning
						 * som beh�vs genomf�ras. Detta eftersom arrayen scoreChoice i view representerar de knappar som spelaren trycker p� f�r att lagra po�ng. 
						 * 
						 *  "x" �r s�ledes index f�r ett givet po�ngalternativ vilket har exakt samma index i spelarobjektet d�r po�ngen lagras. Om spelaren ex. 
						 *  trycker p� "ettor", k�rs "case 1" vilket anropar Model som i sin tur genomf�r ber�kningen av antalet ettor, sparar po�ngen hos det givna spelarobjektet
						 *  samt lagrar "true" i det givna spelarobjektet s� att po�ngalternativet inte kan v�ljas igen. 
						 *  
						 *  Notera argumentet "10" vilket signalerar f�r utr�kningsfunktionerna i Model att resultatet �ven ska lagras i spelarobjekten. 
						 *  
						 */
						
						switch (x) { 

						// Index [0] �r reserverad f�r spelarnas namn.

						case 1:
							theModel.setSelectedRule(true, x);
							theModel.standardValues(x + 1, 10); //Eftersom vi utg�r ifr�n index-v�rdet hos scoreChoice, m�ste "1" l�ggas. Annars blir det fel v�rde som ber�knas. 
							break;
						case 2:
							theModel.setSelectedRule(true, x);
							theModel.standardValues(x + 1, 10);
							break;
						case 3:
							theModel.setSelectedRule(true, x);
							theModel.standardValues(x + 1, 10);
							break;
						case 4:
							theModel.setSelectedRule(true, x);
							theModel.standardValues(x + 1, 10);
							break;
						case 5:
							theModel.setSelectedRule(true, x);
							theModel.standardValues(x + 1, 10);
							break;
						case 6:
							theModel.setSelectedRule(true, x);
							theModel.standardValues(x + 1, 10);
							break;
						case 9: // 9 eftersom vi har summa och bonus p� index 7
								// & 8
							theModel.setSelectedRule(true, x);
							theModel.checkPair(10);
							break;
						case 10:
							theModel.setSelectedRule(true, x);
							theModel.checkTwoPair(10);
							break;
						case 11:
							theModel.setSelectedRule(true, x);
							theModel.checkTretal(10);
							break;
						case 12:
							theModel.setSelectedRule(true, x);
							theModel.checkFyrtal(10);
							break;
						case 13:
							theModel.setSelectedRule(true, x);
							theModel.checkFullHouse(10);
							break;
						case 14:
							theModel.setSelectedRule(true, x);
							theModel.checkLitenStege(10);
							break;
						case 15:
							theModel.setSelectedRule(true, x);
							theModel.checkStorStege(10);
							break;
						case 16:
							theModel.setSelectedRule(true, x);
							theModel.checkChans(10);
							break;
						case 17:
							theModel.setSelectedRule(true, x);
							theModel.checkYatzy(10);
							break;

						}

						deactivePlayer();  //Samtliga spelares po�ngalternativ avaktiveras. 
						nextPlayer(theModel.nextPlayer()); //N�sta spelare i turordningen. 
						activePlayer(); //Nu aktiveras ENBART den spelare som st�r n�st p� tur. 
						selectedRule(); //Kontrollerar de po�ngalternativ som valt sedan tidigare samt icke valts. 
						selectedScore(); //Kontrollerar po�ngen f�r samtliga po�ngalternativ. 

						theView.showThrows.setText(theModel.getPlayerName(theModel.getCurrentPlayer())); //H�mtar n�sta spelares namn. 
						theView.player_name.setText("Du har: " + theModel.getCount() + " kast kvar"); // Visar spelarens kvarst�ende kast, vilket �r 3
						theModel.resetHoldDice(); // T�rningarnas "h�ll"-v�rde s�tts till 0 vilket inneb�r att de inte h�lls kvar till n�sta spelare. 
						theModel.resetDiceValue(); //Samtliga t�rningars v�rde nollst�lls. 
						theView.gameOver(theModel.gameOver(), theModel.getWinner()); //Om den sista spelaren har valt samtliga po�ngalternativ, ska spelet avslutas. 
																					//Mer om detta i View under funktionen med samma namn. 

					}

				}

			}
		}

	}
	
	public void findDice(Object c){ //Unders�ker vilken t�rning som tryckts p� - f�r att kunna markera/avmarkera 
        for (int x = 0; x < theView.dices.length; x++) {
           
                if (c.equals(theView.dices[x])) {
                   
    				if (theModel.getHoldDice(x) == 1) {
    					theModel.setHoldDice(x);
    					theView.dices[x].setBorder(BorderFactory.createEmptyBorder());
    					
    				} else {
    					theModel.setHoldDice(x);
    					theView.dices[x].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    					
    					
    				}
                    
                }
            
        }
	}
	
	public boolean diceClicked(Object c){
		
		 for (int x = 0; x < theView.dices.length; x++) {
	           
             if (c.equals(theView.dices[x])) {
                
             	return true;
             }
         
     }
		 
		 return false;
	}
	
	
	
	/// Funktioner inom Controller
	
	public void nextPlayer(int player){ //Anv�nds enbart som ett medel att f� n�sta spelare genom metodanrop i regelverket
		
		
	}
	
	public void activePlayer(){
		theView.activatePlayer(theModel.getCurrentPlayer()); //F�r att aktivera po�ngalternativen f�r spelaren n�st p� tur. 
	}
	
	public void deactivePlayer(){ //Avaktivera de andra spelarna s� att man inte kan v�lja varandras alternativ. 
		theView.deactivatePlayer(theModel.getAmountOfPlayers());
	}

	public void selectedRule(){ //Kontroll av de po�ngalternativ som valts/ej valts. H�mtas fr�n spelarobjekten. 
		theView.checkSelectedRule(theModel.checkSelectedRule(), theModel.getCurrentPlayer());
	}
	
	public void selectedScore(){ //Kontroll po�ng f�r spelaren n�st p� tur. H�mtas fr�n spelarobjekten. 
		
		for(int i = 0; i <= theModel.getAmountOfPlayers(); i++){
			theView.checkSelectedLabel(theModel.checkAllPlayerScore(i), i);
		}

	}
	
}
