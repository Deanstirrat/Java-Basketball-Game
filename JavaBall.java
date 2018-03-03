import java.util.*;
import java.io.*;

public class JavaBall{
  public static void main(String [] args) throws IOException{
    
    //VARIABLES:
    
    int[] teamChoices = new int[1];
    char menuChoice = 'a';
    String firstChoice = "A";
    
    //value that will tell main when to start the game
    boolean startGame = false;
    
    player[] team1 = new Player[4];
    player[] team2 = new Player[4];
    
    //file names for each team
    String team1FileName = "";
    String team2FileName = "";
    //File scanner of team file names:
    File text = new File("teamFileNames.txt");
    Scanner scan = new Scanner(text);
    //Array of strings holding each team file name:
    String[] teamFiles = new String[8];
    int flag = 0;
    while(scan.hasNextLine()){
      teamFiles[flag] = scan.nextLine();
      flag++;
    }
    scan.close();
    //scanner setup:
    Scanner scan = new Scanner(System.in);
    
    
    
    
    //GAMEPLAY
    
    //menus & game-prep:
    while(!startGame){
    System.out.println("Welcome to Java Basketball!\n---------"+
                         "---------------------\nA) Player vs."+ 
                         "Computer\nB) Player vs. Player\nC) View Stats");
    firstChoice = scan.next();
    while(firstChoice.charAt(0)<'A' || firstChoice.charAt(0)>'C'){
      System.out.println("Please only input upercase letters A, B or C");
      firstChoice = scan.next();}
    menuChoice = Character.toUpperCase(firstChoice.charAt(0));
    if(menuChoice=='A'){
      //teamChoices = playerVsComputer();
      System.out.println("*THIS HAS NOT BEEN CLOMPLETED YET*");
    }
    if(menuChoice=='B'){
      //selects teams and places number values inputted
      //to the teamChoices array of ints
      teamChoices = playerVsPlayer();
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
      
      File text = new File(team1FileName+".txt");
      Scanner scan = new Scanner(text);
      for(int i = 0; i<5; i++){
        firstName = scan.next();
        lastName = scan.next();
        thrpp = scan.next();
        twopp = scan.next();
        scan.nextLine();
        team1[i] = new player(firstName, lastName, thrpp, twopp);
      }
      //same process for the second team
      scan.close();
      File text = new File(team2FileName+".txt");
      Scanner scan = new Scanner(text);
      for(int i = 0; i<5; i++){
        firstName = scan.next();
        lastName = scan.next();
        thrpp = scan.next();
        twopp = scan.next();
        scan.nextLine();
        team2[i] = new player(firstName, lastName, thrpp, twopp);
      }
      scan.close();
      
      //the game is now set up with both teams selected and
      //the teams loaded into the player arrays
      
      //This value will allow main to continue into playing the game:
      gameStart = true;
    }
    if(menuChoice=='C'){
    }
    }
  
    
    //GAME STARTS:
    intro(team1, team2);
    
  }
  
  public static int[] playerVsPlayer(){
    Scanner scan = new Scanner(System.in);
    int[] teams = new int[1];
    System.out.println("================================\n"+
                       "=====Player =  VS  = Player=====\n"+
                       "================================");
    
    System.out.println("Player 1 choice a team: ");
    System.out.println("1. Boston Celtics\n2. Cleveland Cavaliers\n3. Golden State "+
                       "Warriors\n4. Houston Rockets\n5. LA Lakers\n6. Milwaukee Bucks\n"+
                       "7. Oklahoma City Thunder\n8."+
                       "San Antonio Spurs\n9. Toronto Raptors");
    teams[0] = scan.nextInt();
    System.out.println("Player 2 choice a team: ");
    System.out.println("1. Boston Celtics\n2. Cleveland Cavaliers\n3. Golden State "+
                       "Warriors\n4. Houston Rockets\n5. LA Lakers\n6. Milwaukee Bucks\n"+
                       "7. Oklahoma City Thunder\n8."+
                       "San Antonio Spurs\n9. Toronto Raptors");
    teams[1] = scan.nextInt();
    
    return teams;
  }
  
  //This method give intro information such as starting lineup and returns
  //the winner of the coin toss
  public static int intro(Player[] first, Player[] second){
    System.out.printf("PG-%-s15PG-%-s\n", firstTeam[0], secondTeam[0]);
    System.out.printf("SF-%-s15SF-%-s\n", firstTeam[1], secondTeam[1]);
    System.out.printf("PF-%-s15PF-%-s\n", firstTeam[2], secondTeam[2]);
    System.out.printf("C-%-s15C-%-s\n", firstTeam[3], secondTeam[3]);
    System.out.printf("SG-%-s15SG-%-s\n", firstTeam[4], secondTeam[4]);
    Scanner scan = new Scanner(System.in);
    System.out.println("*Press enter to continue*");
    if((scan.nextline()).length()>=0)
    {
      System.out.println("Player 1 pick:");
      System.out.println("1.\tHeads\n2.\tTails");
      int headsortail = scan.nextInt();
      Random rand = new Random();
      int coinFlip = rand.nextInt(1);
      int winner;
      if(headsortails = coinFlip)
        winner = 1;
      else
        winner = 2;
    }
    return winner;
  }
  
  
  
  //This method is used everytime the court is displayed on the screen
  public static void printCourt(offense, defense){
    System.out.printf("______________________________________________________________________\n"+
                      "|_____\n|     *****------\n|                ****-__|%-5s|\n|                        #####_\n"+
                      "||%-5s|                       \\","curry","Thomson");
  }
}
 
