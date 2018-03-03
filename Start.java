
/*  Java Basketball
JavaBall.java
Dean Stirrat
dvstirra
setion 4 */
import java.util.*;
import java.io.*;

public class Start{
  public static void main(String [] args) throws IOException{
    
    //VARIABLES:
    
    int[] teamChoices = new int[2];
    char menuChoice = 'a';
    String firstChoice = "A";
    
    //temporary team number save:
    int tempTeam = 0;
    //team ussage array
    int[] teamUsage = new int[9];
    teamUsage = teamUsageGet();
    //quit boolean
    boolean quit = false;
    
    //value that will tell main when to start the game
    boolean startGame = false;
    
    //array of players that represent teams:
    //(AR)
    Player[] team1 = new Player[5];
    Player[] team2 = new Player[5];
    
    
    //file names for each team
    String team1FileName = "";
    String team2FileName = "";
    //File scanner of team file names:
    //(I/O)
    File text = new File("teamFileNames.txt");
    Scanner scan = new Scanner(text);
    //Array of strings holding each team file name:
    String[] teamFiles = new String[9];
    int flag = 0;
    while(scan.hasNextLine()){
      teamFiles[flag] = scan.nextLine();
      flag++;
    }
    scan.close();
    //scanner setup:
    Scanner userScan = new Scanner(System.in);
    
    
    
    
    //GAMEPLAY
    
    //menus & game-prep:
    while(!quit){
    while(!startGame){
      Scanner menuScan =  new Scanner(System.in);
    System.out.println("Welcome to Java Basketball!\n---------"+
                         "---------------------\nA) Player vs."+ 
                         "Computer\nB) Player vs. Player\nC) View Stats\nD) Quit");
    firstChoice = menuScan.next();
    //INTERACTIVE INPUT/OUTPUT:
    while(firstChoice.charAt(0)<'A' || firstChoice.charAt(0)>'D'){
      System.out.println("Please only input upercase letters A, B, C or D");
      firstChoice = menuScan.next();}
    menuChoice = Character.toUpperCase(firstChoice.charAt(0));
    if(menuChoice=='A'){
      //teamChoices = playerVsComputer();
      System.out.println("*THIS HAS NOT BEEN CLOMPLETED YET*");
    }
    if(menuChoice=='B'){
      //selects teams and places number values inputted
      //to the teamChoices array of ints
      teamChoices = playerVsPlayer();
      //saving those for stats
      teamUsage[teamChoices[0]]++;
      teamUsage[teamChoices[1]]++;
      tempTeam = teamChoices[0];
      teamUsageAdd(tempTeam, teamUsage);
      tempTeam = teamChoices[1];
      teamUsageAdd(tempTeam, teamUsage);
      
      //Sets team selections to their corrisponding 
      //team file names so they can be accessed
      for(int i = 0; i<2; i++){
        team1FileName = teamFiles[teamChoices[0]];
        team2FileName = teamFiles[teamChoices[1]];
      }
      //for each team and array of players is built from
      //the respective team files
      //setting up the variables that will be used
      //for the construction process after reading from the file
      String firstName;
      String lastName;
      Double thrpp;
      Double twopp;
      
      File team1File = new File(team1FileName+".txt");
      Scanner fileScan1 = new Scanner(team1File);
      for(int i = 0; i<5; i++){
        firstName = fileScan1.next();
        lastName = fileScan1.next();
        thrpp = fileScan1.nextDouble();
        twopp = fileScan1.nextDouble();
        //fileScan1.nextLine();
        team1[i] = new Player(firstName, lastName, thrpp, twopp);
      }
      //same process for the second team
      fileScan1.close();
      File team2File = new File(team2FileName+".txt");
      Scanner fileScan2 = new Scanner(team2File);
      for(int i = 0; i<5; i++){
        firstName = fileScan2.next();
        lastName = fileScan2.next();
        thrpp = fileScan2.nextDouble();
        twopp = fileScan2.nextDouble();
        team2[i] = new Player(firstName, lastName, thrpp, twopp);
      }
      fileScan2.close();
      
      //the game is now set up with both teams selected and
      //the teams loaded into the player arrays
      
      //This value will allow main to continue into playing the game:
      startGame = true;
    }
    if(menuChoice=='C'){
      Scanner scan4 = new Scanner(System.in);
      System.out.println("1. Search for player\n2. See top teams");
      int selection = scan4.nextInt();
      if(selection == 1){
      Player[] statTeam = new Player[5];
      System.out.println("Select a team");
      System.out.println("1. Boston Celtics\n2. Cleveland Cavaliers\n3. Golden State "+
                       "Warriors\n4. Houston Rockets\n5. LA Lakers\n6. Milwaukee Bucks\n"+
                       "7. Oklahoma City Thunder\n8."+
                       "San Antonio Spurs\n9. Toronto Raptors");
      Scanner statScan = new Scanner(System.in);
      int team = statScan.nextInt();
      File statFile = new File(teamFiles[team-1]+".txt");
      Scanner statScanner = new Scanner(statFile);
      for(int i = 0; i<5; i++){
        String firstName = statScanner.next();
        String lastName = statScanner.next();
        double thrpp = statScanner.nextDouble();
        double twopp = statScanner.nextDouble();
        statTeam[i] = new Player(firstName, lastName, thrpp, twopp);
      }
      System.out.println("Select a player:");
      for(int i = 0; i<5; i++){
        System.out.println((i+1) + ". " +statTeam[i]);
      }
      int player = statScan.nextInt();
      player--;
      System.out.println(statTeam[player].printStats());
    }
      if(selection == 2){
        String[] bestTeams = new String[9];
        bestTeams = bubbleSort(getRecord(), teamFiles);
        for(int i = 0; i<9; i++)
          System.out.println(i+". "+bestTeams[i]);
      }
    }
    if(menuChoice == 'D')
      quit = true;
    }
  
    
    //GAME STARTS:
    if(startGame == true){
      //score:
      int[] score = new int[2];
      score[0] = 0;
      score[1] = 0;
      //game tamer:
      int gameTime = 20;
      //which team has the ball
      int teamWithBall = 1;
      //which player has the ball
      int playerWithBall = 0;
      //Creates a 2-dimensional array of both teams of players
      Player[][] game = new Player[2][5];
      for(int i = 0; i<2; i++){
        for(int n = 0; n<5; n++){
          if(i==0)
            game[i][n] = team1[n];
          if(i==1)
            game[i][n] = team2[n];
        }
      }
      
      int winner = intro(team1, team2);
      if(winner==0){
        printCourt(team1);
        teamWithBall = 0;
      }
      else{
        printCourt(team2);
        teamWithBall = 1;
      }
      //Gamplay loop
      while(gameTime>=0){
        Scanner continueScanner = new Scanner(System.in);
        System.out.println("==============================\n*Press enter to continue*");
        if((continueScanner.nextLine()).length()>=0){
        int option = -1;
        if(teamWithBall==0)
          printCourt(team1);
        else
          printCourt(team2);
        option = playerOption(teamWithBall, playerWithBall, game);
        //Shoot
        if(option == 1){
          if(game[teamWithBall][playerWithBall].threePoint())
          {
            if(playerWithBall==4||playerWithBall==0){
              score[teamWithBall] += 3;
              System.out.println("+=============+\n|THREE POINTS|\n+=============+");
              teamWithBall = scoreBoard(teamWithBall,score, gameTime, teamFiles, teamChoices);
          }
            else{
              score[teamWithBall] += 2;
              System.out.println("+=============+\n|TWO   POINTS|\n+=============+");
              teamWithBall = scoreBoard(teamWithBall,score, gameTime, teamFiles, teamChoices);
            }
          }
          else{
            System.out.println("**The shot has missed!**");
            teamWithBall = rebound();
          }
        }
        
        //pass
        if(option == 2){
          Scanner passScan = new Scanner(System.in);
          //(BOOL)
          boolean key1 = false;
          while(!key1){
          System.out.println("Who would you like to pass too?\n(Enter the number next to the player)");
          option = passScan.nextInt();
          if(option==1||option==2||option==3||option==4||option==5)
            key1 = true;
          }
          option -= 1;
          //(RANDOM)
          Random pass = new Random();
          if(pass.nextInt(100)<8){
            System.out.println("**The pass was broken up**");
            if(teamWithBall==0){
              System.out.println("Team 2 now has possetion");
              teamWithBall = 1;
            }
             if(teamWithBall==1){
              System.out.println("Team 1 now has possetion");
              teamWithBall = 0;                  
             }
          }
          else{
            playerWithBall = option;
            System.out.println(game[teamWithBall][playerWithBall] + " was passed the ball");
          }
        }
        
        //Drive
        if(option == 3){
          if(game[teamWithBall][playerWithBall].drive()){
            score[teamWithBall] += 2;
            System.out.println("+=============+\n|TWO   POINTS|\n+=============+\n");
            teamWithBall = scoreBoard(teamWithBall,score, gameTime, teamFiles, teamChoices);
            }
          else{
            System.out.println("**The layup is off iron**");
            teamWithBall = rebound();
          }
        }
        gameTime--;
      }
      }//end of gametime loop
      System.out.println("===========================\n"+
                         "== == == GAME OVER == == ==\n"+
                         "===========================\n\nHERE IS THE FINAL SCOREBOARD:\n");
      scoreBoard(teamWithBall,score, gameTime, teamFiles, teamChoices);
      startGame = false;
      
      if(score[0]>score[1])
        winner(0);
      else
        winner(1);
    }//end of game
    }
  }//end main
  
  
  
  
  
  
  
  
  
  
  //(MYMETH)
  //Methods:
  public static int[] playerVsPlayer(){
    Scanner scan = new Scanner(System.in);
    int[] teams = new int[2];
    System.out.println("================================\n"+
                       "=====Player =  VS  = Player=====\n"+
                       "================================");
    
    System.out.println("Player 1 choice a team: ");
    System.out.println("1. Boston Celtics\n2. Cleveland Cavaliers\n3. Golden State "+
                       "Warriors\n4. Houston Rockets\n5. LA Lakers\n6. Milwaukee Bucks\n"+
                       "7. Oklahoma City Thunder\n8."+
                       "San Antonio Spurs\n9. Toronto Raptors");
    teams[0] = (scan.nextInt())-1;
    System.out.println("Player 2 choice a team: ");
    System.out.println("1. Boston Celtics\n2. Cleveland Cavaliers\n3. Golden State "+
                       "Warriors\n4. Houston Rockets\n5. LA Lakers\n6. Milwaukee Bucks\n"+
                       "7. Oklahoma City Thunder\n8."+
                       "San Antonio Spurs\n9. Toronto Raptors");
    teams[1] = (scan.nextInt())-1;
    
    return teams;
  }
  
  //This method give intro information such as starting lineup and returns
  //the winner of the coin toss
  public static int intro(Player[] first, Player[] second){
    int winner = 0;
    System.out.println("==============================\n"
                         +"This is your starting Lineup:\n"+
                       "==============================");
    System.out.println("Team 1:             Team 2:");
    System.out.printf("PG-%-15s| PG-%s\n", first[0], second[0]);
    System.out.printf("SF-%-15s| SF-%s\n", first[1], second[1]);
    System.out.printf("PF-%-15s| PF-%s\n", first[2], second[2]);
    System.out.printf("C-%-16s| C-%s\n", first[3], second[3]);
    System.out.printf("SG-%-15s| SG-%s\n", first[4], second[4]);
    Scanner scan = new Scanner(System.in);
    System.out.println("==============================\n*Press enter to continue*");
    if((scan.nextLine()).length()>=0)
    {
      System.out.println("Player 1 pick:");
      System.out.println("1.\tHeads\n2.\tTails");
      int headsOrTails = scan.nextInt();
      Random rand = new Random();
      int coinFlip = rand.nextInt(2);
      if(headsOrTails == coinFlip){
        winner = 0;
      }
      else
        winner = 1;
    }
    System.out.println("The winner of the coin toss is... team "+(winner+1)+"!");
    return winner;
  }
  
  
  
  //This method is used everytime the court is displayed on the screen
  //SHOOTING GUARD = 4
  //POWER FORWARD = 2
  //POINT GUARD = 0
  //CENTER = 3
  //SMALL FORWARD = 1
  public static void printCourt(Player[] offense){
    System.out.printf("______________________________________________________________________\n"+
                      "|_____\n"+
                      "|     *****------\n"+
                      "|                ****-__|5.%-5s|\n"+
                      "|                        **--_\n"+
                      "||3.%-5s|                    +\n"+
                      "|__________________             |\n"+
                      "|              |   **--_        \\\n"+
                      "|              |        *_        |\n"+
                      "|              |         #        |1.%-5s\n"+
                      "|              |         #        |\n"+
                      "|              |         #        |\n"+
                      "|              |       _*        /\n"+
                      "|______________|____--*        |\n"+
                      "|              |4.%-5s|      +\n"+
                      "|                           _|\n"+
                      "|                       _-*\n"+
                      "|2.%-5s|            _---*\n"+
                      "|     ______--****\n"+
                      "|-----\n"+
                      "______________________________________________________________________"+
                      "\n",offense[4].printCourt(),offense[2].printCourt(),offense[0].printCourt(),offense[3].printCourt(),offense[1].printCourt());
  }
  
  //This method shows a menu of options for player objects:
  //(MYMETH(O))
  public static int playerOption(int team, int player, Player[][] game){
    Scanner scan = new Scanner(System.in);
    int num = 0;
    boolean key = false;
      while(key==false){
      System.out.println(game[team][player]+ " has the ball\n"+
                         "What would you like to do next?");
      System.out.println("1) Shoot\n2) Pass\n3) Drive");
      num = scan.nextInt();
      if(num==1|| num==2|| num==3)
        key = true;
    }
      return num;
  }
  
  //This methods finds the team that comes up with the ball after a missed shot
  public static int rebound(){
    Random rand = new Random();
    int coinFlip = rand.nextInt(2);
    System.out.println("Team "+(coinFlip+1)+" has recovered the ball");
    return coinFlip;
  }
  
  //This method is called after every point. It changes the possetion of the ball
  //to the other team as well as displayting a scoreboard with info on the game such as
  //the score and the time left in the game:
  public static int scoreBoard(int team, int[] score, int gameTime, String[] teamNames, int[] teamChoices){
    if(team == 0){
      team = 1;
    }
    else if(team == 1){
      team = 0;
    }
    if(gameTime == -1)
      gameTime = 0;
    System.out.printf("============================================\n"+
                       "||%-20s%-20s||\n"+
                       "||%-20d%-20d||\n"+
                       "||Game Time Left:  "+gameTime+"                      ||\n"+
                       "============================================\n",teamNames[teamChoices[0]],teamNames[teamChoices[1]],score[0],score[1]);
    return team;
  }
  
  //Team usgae
  public static void teamUsageAdd(int n, int[] teams) throws FileNotFoundException {
    File file = new File("usage.txt");
    PrintWriter writer = new PrintWriter(file);
    for(int i = 0; i<9; i++){
      writer.println(teams[i]);
    }
    writer.close();
  }
  public static int[] teamUsageGet() throws FileNotFoundException {
    File file = new File("usage.txt");
    Scanner reader = new Scanner(file);
    int[] teams = new int[9];
    for(int i = 0; i<9; i++){
      teams[i] = reader.nextInt();
    }
    reader.close();
    return teams;
  }
  
  public static void winner(int team) throws FileNotFoundException {
    File file = new File("winner.txt");
    Scanner reader = new Scanner(file);
    int[] teams = new int[9];
    for(int i = 0; i<9; i++){
      teams[i] = reader.nextInt();
    }
    reader.close();
    PrintWriter writer = new PrintWriter(file);
    for(int i = 0; i<9; i++){
      writer.println(teams[i]);
    }
    writer.close();
  }
  //sort
  public static String[] bubbleSort(int[] winning, String[] teams) 
  { 
    int startIndex;
    int i;
    
    for (startIndex = 0;startIndex<=winning.length-2; startIndex++) 
    { 
      for (i=winning.length-2; i>=startIndex; i--) 
        if (winning[i]>winning[i+1])
      {  
        int temp; 
        temp = winning[i]; 
        winning[i]=winning[i+1]; 
        winning[i+1]=temp; 
        String temp2;
        temp2 = teams[i];
        teams[i] = teams[i+1];
        teams[i+1] = temp2;
      }
    }
    return teams;
  }
  public static int[] getRecord() throws FileNotFoundException {
    File file = new File("winner.txt");
    Scanner reader = new Scanner(file);
    int[] teams = new int[9];
    for(int i = 0; i<9; i++){
      teams[i] = reader.nextInt();
    }
    return teams;
  }
  
}
 
