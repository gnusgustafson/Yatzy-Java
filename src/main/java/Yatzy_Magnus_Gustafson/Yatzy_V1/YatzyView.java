package Yatzy_Magnus_Gustafson.Yatzy_V1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class YatzyView extends JFrame {
	
	final JButton throw_button = new JButton("Spela");
	final JLabel game_guide = new JLabel("Välkommen till Yatzy - Välj antal spelare");
	final JLabel player_name = new JLabel("[PLAYERNAME]");
	final JLabel showThrows = new JLabel();
	final JButton startGame = new JButton("Starta spelet"); 

    JPanel playerAmount = new JPanel();
    JPanel setPlayers = new JPanel();
	
	JButton[] dices = new JButton[5];
	JButton[] yatzy = new JButton[5];
    JButton[] rulesButton = new JButton[19];
   
    JButton[] amountOfPlayers = new JButton[4];
    JLabel[] player = new JLabel[4];
    JTextField[] playerName = new JTextField[4];

	JPanel rulesPanel = new JPanel();

	JPanel mainPanel = new JPanel();
	JPanel dicePanel = new JPanel();
	JPanel d1 = new JPanel();
	JPanel d2 = new JPanel();
	JPanel throwPanel = new JPanel();
	
	JPanel scorePanel = new JPanel();
	
	JPanel gameOverPanel = new JPanel();
	
	
	JPanel[] playerScore = new JPanel[4]; //Fyra paneler f�r fyra spelare. 
	JButton[][] scoreChoice = new JButton[4][19]; //4 avser fyra spelare, 19 avser samtliga val som en spelare kan g�ra. 
												//Om det �r fyra spelare s� blir det fyra kolumner med 19 knappar. 

	/*
	 * View-klassen �r en smula r�rig. Jag m�ste villigt erk�nna att jag fortfarande inte helt har greppat konceptet med olika layouts. 
	 * Du f�r urs�kta att den ser ut som den g�r. 
	 */
	
	YatzyView(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(680, 900);
	    

		
	    dices[0] = new JButton(new ImageIcon("images/5.png")); dices[0].setBorder(BorderFactory.createEmptyBorder());
	    dices[1] = new JButton(new ImageIcon("images/5.png")); dices[1].setBorder(BorderFactory.createEmptyBorder());
	    dices[2] = new JButton(new ImageIcon("images/5.png")); dices[2].setBorder(BorderFactory.createEmptyBorder());
	    dices[3] = new JButton(new ImageIcon("images/5.png")); dices[3].setBorder(BorderFactory.createEmptyBorder());
	    dices[4] = new JButton(new ImageIcon("images/5.png")); dices[4].setBorder(BorderFactory.createEmptyBorder());
	    
	    yatzy[0] = new JButton(new ImageIcon("images/y.png")); yatzy[0].setBorder(BorderFactory.createEmptyBorder());
	    yatzy[1] = new JButton(new ImageIcon("images/a.png")); yatzy[1].setBorder(BorderFactory.createEmptyBorder());
	    yatzy[2] = new JButton(new ImageIcon("images/t.png")); yatzy[2].setBorder(BorderFactory.createEmptyBorder());
	    yatzy[3] = new JButton(new ImageIcon("images/z.png")); yatzy[3].setBorder(BorderFactory.createEmptyBorder());
	    yatzy[4] = new JButton(new ImageIcon("images/y.png")); yatzy[4].setBorder(BorderFactory.createEmptyBorder());
	    
	    dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.X_AXIS)); //För t�rningarna anv�nds BoxLayout, horisontell.  
	    
	    
	    d1.add(dices[0]);
	    d1.add(Box.createRigidArea(new Dimension(10,0))); //L�gger till ett litet mellanrum f�r t�rningarna s� att dem blir l�ttare att s�rskilja. 
	    d1.add(dices[1]);
	    d1.add(Box.createRigidArea(new Dimension(10,0)));
	    d1.add(dices[2]);
	    d1.add(Box.createRigidArea(new Dimension(10,0)));
	    d1.add(dices[3]);
	    d1.add(Box.createRigidArea(new Dimension(10,0)));
	    d1.add(dices[4]);
	
	    
	    
	    d2.add(yatzy[0]);
	    d2.add(Box.createRigidArea(new Dimension(10,0)));
	    d2.add(yatzy[1]);
	    d2.add(Box.createRigidArea(new Dimension(10,0)));
	    d2.add(yatzy[2]);
	    d2.add(Box.createRigidArea(new Dimension(10,0)));
	    d2.add(yatzy[3]);
	    d2.add(Box.createRigidArea(new Dimension(10,0)));
	    d2.add(yatzy[4]);
	    
	    this.dicePanel.add(d1);
	    this.dicePanel.add(d2);
	    
	    // Namnen f�r samtliga po�ngalternativ. 
	    rulesButton[0] = new JButton("Namn:"); 
	    rulesButton[1] = new JButton("Ettor"); 
	    rulesButton[2] = new JButton("Tvåor"); 
	    rulesButton[3] = new JButton("Treor"); 
	    rulesButton[4] = new JButton("Fyror"); 
	    rulesButton[5] = new JButton("Femmor"); 
	    rulesButton[6] = new JButton("Sexor"); 
	    rulesButton[7] = new JButton("Summa:"); 
	    rulesButton[8] = new JButton("Bonus");
	    rulesButton[9] = new JButton("Par");
	    rulesButton[10] = new JButton("Två par");
	     rulesButton[11] = new JButton("Tretal");
	    rulesButton[12] = new JButton("Fyrtal");
	    rulesButton[13] = new JButton("Kåk");
	    rulesButton[14] = new JButton("Liten stege");
	    rulesButton[15] = new JButton("Stor stege");
	    rulesButton[16] = new JButton("Chans");
	    rulesButton[17] = new JButton("Yatzy");
	    rulesButton[18] = new JButton("Tot:");
	    
	    
	rulesPanel.setAlignmentX(CENTER_ALIGNMENT);
	    
   	rulesButton[0].setOpaque(false);  //Spelares namn [0]
   	rulesButton[0].setContentAreaFilled(false);
   	rulesButton[0].setBorderPainted(false);

   	rulesButton[0].setAlignmentX(CENTER_ALIGNMENT);
   	rulesPanel.add(rulesButton[0]);
   	rulesPanel.add(Box.createRigidArea(new Dimension(0,4)));
	   
	   for(int i = 1; i < 7; i++){ //Po�ngalternativen 0-6

	    	rulesButton[i].setContentAreaFilled(false);
	    	rulesButton[i].setAlignmentX(CENTER_ALIGNMENT);
	    	rulesPanel.add(rulesButton[i]);
	    	rulesPanel.add(Box.createRigidArea(new Dimension(0,4)));	
	    }

		rulesButton[7].setOpaque(false); //[7] = Summa
		rulesButton[7].setContentAreaFilled(false);
		rulesButton[7].setBorderPainted(false);
		rulesButton[7].setAlignmentX(CENTER_ALIGNMENT);

		rulesPanel.add(rulesButton[7]);
	    
	  	rulesButton[8].setOpaque(false); //[8] = bonus
	   	rulesButton[8].setContentAreaFilled(false);
	   	rulesButton[8].setBorderPainted(false);
	   	rulesButton[8].setAlignmentX(CENTER_ALIGNMENT);

	   	rulesPanel.add(rulesButton[8]);
	   	rulesPanel.add(Box.createRigidArea(new Dimension(0,9)));
	   	
	   	
		   for(int i = 9; i < rulesButton.length; i++){ // Par till Yatzy

				    	rulesButton[i].setContentAreaFilled(false);
				    	rulesButton[i].setAlignmentX(CENTER_ALIGNMENT);
				    	rulesPanel.add(rulesButton[i]);
				    	rulesPanel.add(Box.createRigidArea(new Dimension(0,4)));
				    }
		   
		rulesButton[18].setOpaque(false); // Tot: , dvs. den totala po�ngen.
		rulesButton[18].setContentAreaFilled(false);
		rulesButton[18].setBorderPainted(false);
		rulesButton[18].setAlignmentX(CENTER_ALIGNMENT);

		rulesPanel.add(rulesButton[18]);
		rulesPanel.add(Box.createRigidArea(new Dimension(0, 4)));

	    throwPanel.add(showThrows);
	    throwPanel.add(Box.createRigidArea(new Dimension(0,5)));
	    showThrows.setText("Tjabba");
	    throwPanel.add(player_name);
	    throwPanel.add(Box.createRigidArea(new Dimension(0,10)));
	    throwPanel.add(Box.createRigidArea(new Dimension(0,10)));
	    throwPanel.add(throw_button);

	    game_guide.setFont(new Font("Plain", Font.BOLD, 25));
		player_name.setFont(new Font("Plain", Font.BOLD, 18));
		showThrows.setFont(new Font("Plain", Font.BOLD, 18));

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		mainPanel.add(dicePanel);

		throwPanel.setLayout(new BoxLayout(throwPanel, BoxLayout.PAGE_AXIS));

	    throw_button.setAlignmentX(CENTER_ALIGNMENT);
	    player_name.setAlignmentX(CENTER_ALIGNMENT);
	    game_guide.setAlignmentX(CENTER_ALIGNMENT);
	    showThrows.setAlignmentX(CENTER_ALIGNMENT);
	    
	    throw_button.setMinimumSize(new Dimension(100,100)); 
	    throw_button.setMaximumSize(new Dimension(100,100)); 
	    
	    mainPanel.add(throwPanel);
	    
	    scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
	    scorePanel.setAlignmentX(CENTER_ALIGNMENT);

	    mainPanel.add(Box.createRigidArea(new Dimension(0,10)));

	    scorePanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	   
	    amountOfPlayers[0] = new JButton("En spelare"); amountOfPlayers[0].setPreferredSize(new Dimension(150,100));
	    amountOfPlayers[1] = new JButton("Två spelare");amountOfPlayers[1].setPreferredSize(new Dimension(150,100));
	    amountOfPlayers[2] = new JButton("Tre spelare");amountOfPlayers[2].setPreferredSize(new Dimension(150,100));
	    amountOfPlayers[3] = new JButton("Fyra spelare");amountOfPlayers[3].setPreferredSize(new Dimension(150,100));
	
	    playerAmount.setLayout(new BoxLayout( playerAmount, BoxLayout.PAGE_AXIS));
	    
	    amountOfPlayers[0].setAlignmentX(CENTER_ALIGNMENT);
	    amountOfPlayers[1].setAlignmentX(CENTER_ALIGNMENT);
	    amountOfPlayers[2].setAlignmentX(CENTER_ALIGNMENT);
	    amountOfPlayers[3].setAlignmentX(CENTER_ALIGNMENT); 
	    
	    playerAmount.add(game_guide);
	    
	    playerAmount.add(Box.createRigidArea(new Dimension(0,20)));
	    
	    playerAmount.add(amountOfPlayers[0]);
	    
	    playerAmount.add(Box.createRigidArea(new Dimension(0,20)));
	    playerAmount.add(amountOfPlayers[1]);
	    
	    playerAmount.add(Box.createRigidArea(new Dimension(0,20)));
	    playerAmount.add(amountOfPlayers[2]);
	    
	    playerAmount.add(Box.createRigidArea(new Dimension(0,20)));
	    playerAmount.add(amountOfPlayers[3]);
	    

	    for(int i = 0; i <= 3; i++){ //H�r instansieras samtliga po�ngalternativ f�r samtliga spelare, dvs. 19 knappar f�r varje spelare
	    							//Detsamam g�ller f�r varje kolumn, dvs. en JPanel f�r varje spelare.   	
	    	JPanel p = new JPanel();	
	    	playerScore[i] = p;
	    	
	    	for(int y = 0; y < this.scoreChoice[i].length; y++){
	    		
	    		JButton b = new JButton();
	    		scoreChoice[i][y] = b;		
	    	}
	    }

		this.add(playerAmount);
		this.setVisible(true);

	
	}
	
	///Slut konstruktor

	
	
	
	///Funktioner - View. 
	
	public void setRules(int amount, String names[]) {  //Po�ngalternativen m�las upp och adderas till f�nstret

		rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
		rulesPanel.setAlignmentX(CENTER_ALIGNMENT);
		scorePanel.add(rulesPanel);						//Po�ngalternativens namn m�las upp i kolumnen l�ngst till v�nster. 
		for (int i = 0; i < amount + 1; i++) {
			playerScore[i].setLayout(new BoxLayout(playerScore[i], BoxLayout.Y_AXIS)); 
			playerScore[i].setAlignmentX(CENTER_ALIGNMENT);
			for (int x = 0; x <= 8; x++) {
				if (x <= 0) {

					scoreChoice[i][x].setText(names[i]); //Namnet p� spelarens h�mtas och s�tts. 
					scoreChoice[i][x].setOpaque(false);
					scoreChoice[i][x].setContentAreaFilled(false);
					scoreChoice[i][x].setBorderPainted(false);
					scoreChoice[i][x].setAlignmentX(CENTER_ALIGNMENT);
					playerScore[i].add(scoreChoice[i][x]);
					playerScore[i].add(Box.createRigidArea(new Dimension(0, 4)));

				}else if(x >= 7){  //Avser alternativen "Summa" & "Bonus"
					
					scoreChoice[i][x].setText("0");
					scoreChoice[i][x].setOpaque(false);
					scoreChoice[i][x].setContentAreaFilled(false);
					scoreChoice[i][x].setBorderPainted(false);
					playerScore[i].add(scoreChoice[i][x]);
					playerScore[i].add(Box.createRigidArea(new Dimension(0,4)));
					
				}
				
				else { // Alternativen 1-6

					scoreChoice[i][x].setText("0");
					scoreChoice[i][x].setEnabled(false);
					scoreChoice[i][x].setAlignmentX(CENTER_ALIGNMENT);
					playerScore[i].add(scoreChoice[i][x]);
					playerScore[i].add(Box.createRigidArea(new Dimension(0,4)));
				}

			}

			scorePanel.add(playerScore[i]);
			playerScore[i].setBorder(BorderFactory.createLineBorder(Color.RED)); 
		}
		
		rulesPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		for (int i = 0; i < amount + 1; i++) { //Avser "Par" - "Yatzy"

			for (int x = 9; x < scoreChoice[i].length; x++) {
				
				scoreChoice[i][x].setText("0");
				scoreChoice[i][x].setEnabled(false);
				scoreChoice[i][x].setAlignmentX(CENTER_ALIGNMENT);
				playerScore[i].add(scoreChoice[i][x]);
				playerScore[i].add(Box.createRigidArea(new Dimension(0,4)));
				
				if(x >= 18){ //Avser "Tot:"
					
					scoreChoice[i][x].setText("0");
					scoreChoice[i][x].setOpaque(false);
					scoreChoice[i][x].setContentAreaFilled(false);
					scoreChoice[i][x].setBorderPainted(false);
					playerScore[i].add(scoreChoice[i][x]);
					playerScore[i].add(Box.createRigidArea(new Dimension(0,4)));
					
				}
			}		
		}

		mainPanel.add(scorePanel);
	}
	
	public void addSetPlayers(int amount){ //Efter att antalet spelare har valts p� den f�rsta sidan
											//m�las d�refter f�lten upp p� n�stkommande sida f�r att namnge spelarna. 
		
		setPlayers.setLayout(new BoxLayout(setPlayers, BoxLayout.Y_AXIS));
		setPlayers.add(game_guide);
		game_guide.setText("Namnge samtliga spelare");
		setPlayers.add(Box.createRigidArea(new Dimension(0,20)));
		
		for(int i = 0; i <= amount; i++){
			 
			JLabel l = new JLabel("Spelare: " + (i+1));
			JTextField tf = new JTextField("Spelare: " + (i+1));		
			
			l.setAlignmentX(CENTER_ALIGNMENT);
		    tf.setMinimumSize(new Dimension(100,30)); 
		    tf.setMaximumSize(new Dimension(100,30));	    
			
			player[i] = l;
			playerName[i] = tf;		
			
			setPlayers.add(l);
			setPlayers.add(tf);
			
			setPlayers.add(Box.createRigidArea(new Dimension(0,20)));
		}
		startGame.setAlignmentX(CENTER_ALIGNMENT);		
		setPlayers.add(Box.createRigidArea(new Dimension(0,20)));		
		setPlayers.add(startGame);
		
		this.add(setPlayers);
	} 
	
	public void addGameField(){			//Efter att namnen har angetts, startas spelet. 

			this.add(mainPanel);	
	}
	
	public void gameOver(boolean b, String[] scoreWinner){ 
		//Efter att den sista spelaren i f�ljden har valt sitt sista po�ngalternativ avslutas spelet. 
		//D� m�las vyn upp f�r Game Over vilket presenterar samtliga spelares resultat och plats. 
		
		if(b == true){
			mainPanel.setVisible(false);
			
			gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
			
			JLabel[] gameOverLabel = new JLabel[scoreWinner.length+1];
	 		
			JLabel gameStats = new JLabel("GAME OVER");
			
			gameStats.setAlignmentX(CENTER_ALIGNMENT);
			
			gameStats.setFont(new Font("Plain", Font.BOLD, 45));
			
			gameOverPanel.add(Box.createRigidArea(new Dimension(0,50)));
			
			gameOverPanel.add(gameStats);
			
			gameOverPanel.add(Box.createRigidArea(new Dimension(0,50)));
			
			for(int i = scoreWinner.length -1 ; i >= 0; i--){
				gameOverLabel[i] = new JLabel();
				gameOverLabel[i].setText(scoreWinner[i]);
				gameOverLabel[i].setAlignmentX(CENTER_ALIGNMENT);
				gameOverLabel[i].setFont(new Font("Plain", Font.BOLD, 25));
				gameOverPanel.add(gameOverLabel[i]);
				gameOverPanel.add(Box.createRigidArea(new Dimension(0,20)));

			}

			this.add(gameOverPanel);		
			
		}else{
			return;
		}
		
	}
	
	public void hideDices(){ //T�rningarna g�ms och ers�tts med "Y A T Z Y"
		
		for(int i = 0; i < dices.length; i++){
			
			d1.setVisible(false);
			d2.setVisible(true);	
		}
		
	}
	
	public void showDices(){ //"Y A T Z Y" d�lja och ers�tts med t�rningarna. 
			
			d1.setVisible(true);
			d2.setVisible(false);	
	}
	
	
	public void activatePlayer(int currentPlayer){	//Po�ngalternativen f�r den spelare som st�r n�st p� tur aktiveras. 
		
			for(int x = 0; x < scoreChoice[1].length; x++){
				scoreChoice[currentPlayer][x].setEnabled(true);
			}	
	}
	
	public void deactivatePlayer(int amount){ //Po�ngalternativen f�r de som v�ntar p� sin tur avaktiveras. 
		
		for(int i = 0; i <= amount; i++){
			for(int x = 0; x < scoreChoice[1].length; x++){
				scoreChoice[i][x].setEnabled(false);
			}
		}
		
	}
	
	public void checkSelectedLabel(int[][] x, int amount){ //Visar po�ngen f�r de valde po�ngalternativen f�r spelaren n�st p� tur. 

		for (int i = 1; i < scoreChoice[1].length; i++) {

			if (i >= 7) {
				scoreChoice[amount][i].setText(Integer.toString(x[i][0]));

			} else {

				scoreChoice[amount][i].setText(Integer.toString(x[i][0]) + " (" + Integer.toString(x[i][1]) + ")");

			}
		}

	}
	
	public void checkPossibleScore(int[][] x, boolean[] b, int amount){ //Visar potentiell po�ngsk�rd f�r en spelare efter varje kast. 
		
		for (int i = 1; i < scoreChoice[1].length; i++) {

			if (b[i] == false) {
				if (i >= 7) {
					scoreChoice[amount][i].setText(Integer.toString(x[i][0]));

				} else {

					scoreChoice[amount][i].setText(Integer.toString(x[i][0]) + " (" + Integer.toString(x[i][1]) + ")");

				}
			}

		}

	}	
	
	public void checkSelectedRule(boolean[] b, int current){ //Kontrollerar vilka po�ngalternativ som redan valts f�r spelaren n�st p� tur. 

		for(int i = 0; i < scoreChoice[1].length; i++){
			
			if(b[i] == true){
				scoreChoice[current][i].setEnabled(false);
			}else{
				scoreChoice[current][i].setEnabled(true);
			}
			
		}
		
	}
	
	void addThrowListener(ActionListener listenForThrowButton){ //Lyssnare. 
		
		throw_button.addActionListener(listenForThrowButton);

		dices[0].addActionListener(listenForThrowButton);
		dices[1].addActionListener(listenForThrowButton);
		dices[2].addActionListener(listenForThrowButton);
		dices[3].addActionListener(listenForThrowButton);
		dices[4].addActionListener(listenForThrowButton);

		rulesButton[0].addActionListener(listenForThrowButton);
		rulesButton[1].addActionListener(listenForThrowButton);
		rulesButton[2].addActionListener(listenForThrowButton);
		rulesButton[3].addActionListener(listenForThrowButton);
		rulesButton[4].addActionListener(listenForThrowButton);
		rulesButton[5].addActionListener(listenForThrowButton);

		amountOfPlayers[0].addActionListener(listenForThrowButton);
		amountOfPlayers[1].addActionListener(listenForThrowButton);
		amountOfPlayers[2].addActionListener(listenForThrowButton);
		amountOfPlayers[3].addActionListener(listenForThrowButton);

		startGame.addActionListener(listenForThrowButton);

		for (int i = 0; i < scoreChoice.length; i++) {

			for (int x = 0; x < scoreChoice[i].length; x++) {

				scoreChoice[i][x].addActionListener(listenForThrowButton);

			}

		}

	}
}
