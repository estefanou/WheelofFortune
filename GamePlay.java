
//Game Play
import java.util.*;
import java.io.*;
public class GamePlay
{
  static boolean playersTurn = true; //when true it is user's turn //4. (BOOL)
  static boolean letterInPhrase = false;
  static boolean playAgain = true;
  static boolean phraseComplete = false;
  static double computer1Money=0.00;
  static double computer2Money=0.00;
  static int userWins = 0;
  static int comp1Wins = 0;
  static int comp2Wins = 0;

  public static void main(String[] args) throws IOException
  {
    //money count values
    double userMoney= 0.00;
    Display display = new Display();
    //Display the rules from text file
    Scanner scan = new Scanner(System.in);
    Scanner rules = new Scanner(new File("Rules.txt")); //1. (I/O) file read
    while(rules.hasNextLine())
    {System.out.print(rules.nextLine()+"\n");}

    int category=0;
    System.out.println();
    System.out.println("Enter User name : ");
    String userName = scan.nextLine();
    while (playAgain)
    {
      //picks category for guessing
      System.out.println("Pick a number (1-5) for round 1: ");
      category = scan.nextInt();
      scan.nextLine();


      Scanner file;
      while (true)
      {
        if (category==1)
        {System.out.println("You chose the category of Fictional Characters!");
          file = new Scanner(new File("FictCharacters.txt"));
          break;}
        else if (category==2)
        {System.out.println("You chose the category of Song Titles!");
          file = new Scanner(new File("SongTitle.txt"));
          break;}
        else if (category==3)
        {System.out.println("You chose the category of Food and Drink!");
          file = new Scanner(new File("FoodAndDrink.txt"));
          break;}
        else if (category==4)
        {System.out.println("You chose the category of Phrases!");
          file = new Scanner(new File("Phrase.txt"));
          break;}
        else if (category==5)
        {System.out.println("You chose the category of Places!");
          file = new Scanner(new File("Places.txt"));
          break;}
        else
        {System.out.println("Nice Try...Pick a valid Number"); // 3. interactive input output against bad input
          category = scan.nextInt();
          scan.nextLine();}
      }//end while loop for setting a file to read


      Player user = new Player(userName);
      Player computer1 = new Player("Computer Player Tom");
      Player computer2 = new Player("Computer Player Ally");
      System.out.println();
      System.out.println(userName+"\t "+computer1.getPlayerName()+" \t "+computer2.getPlayerName());
      System.out.printf("$ %,.2f \t$ %,.2f \t\t\t$ %,.2f\n",user.getPlayerMoney(), computer1.getPlayerMoney(), computer2.getPlayerMoney());
      System.out.println();
      System.out.print("Start Game Play\n");

      //Start the game
      //Pick the phrase from the category
      String phrase = pickAPhrase(file);
      display = new Display(phrase);
      //Show the phrase with blanks and spaces instead of letters
      display.showPhrase();
      System.out.println();
      //reads alphabet into changable arrayList
      display.useAlphabet();

      Boolean turn = true; // True when it is still the current user's turn
      Boolean complete = display.phrase.toUpperCase().equals(String.valueOf(display.screen));


      //when the phrase doesn't equal what is showing on the screen continue asking for letter/phrase
      while (!complete)
      {

        while (turn)
        {
          moneyWheel usermoney = new moneyWheel();
          usermoney.spin();
          System.out.println();
          if (usermoney.getMoney() == 0.00)
          {System.out.println("Bankrupt! Computer's Turn");
            turn = false;
            userMoney = 0.00;
          }
          else
          {System.out.println("Guessing for $" +usermoney.getMoney());}

          System.out.println("User: Guess your letter or phrase:");
          String input = scan.nextLine();
          if(input.length() > 1) //if input is longer than a character check against the phrase
          {display.process(input);
            if (letterInPhrase)
            {user.setPlayerMoney(user.getPlayerMoney()+usermoney.getMoney());
              complete = true;
              turn = false;}
            else
            {turn = false;}
          }
          else if(input.length() == 0)
          {System.out.println("Invalid entry, Next users turn");
            turn = false;}
          else
          {
            boolean validLetter = display.checkAlpha(input.charAt(0));
            if (validLetter) //when letter has not been chosen then process letter
            {display.process(input.charAt(0));
              if (letterInPhrase)
              {
                user.setPlayerMoney(user.getPlayerMoney()+usermoney.getMoney());
                if (phraseComplete)
                {
                  complete = true;
                  turn = false;
                  System.out.println("You guessed the Phrase!");
                  break;
                }
                turn = true;
              }
              else
              {turn = false;}
            }
            else
            {System.out.println("Invalid Letter");
              turn = false;} //if letter has been chosen continue to computer's turn //4. (BOOL) used
          }
          System.out.println(userName+"\t "+computer1.getPlayerName()+" \t "+computer2.getPlayerName());
          System.out.printf("$ %,.2f \t$ %,.2f \t\t\t$ %,.2f\n",user.getPlayerMoney(), computer1.getPlayerMoney(), computer2.getPlayerMoney());
        }
        turn = true;
        complete = (display.phrase.toUpperCase()).equals(String.valueOf(display.screen));
        while (turn && !complete)
        {
          System.out.println();
          System.out.println(computer1.getPlayerName()+" is going to play now");
          System.out.println();
          display.computerTurn(computer1);
          turn = false; //changes turn if letter not found in phrase //4. (BOOL) used
          System.out.println(userName+"\t "+computer1.getPlayerName()+" \t "+computer2.getPlayerName());
          System.out.printf("$ %,.2f \t$ %,.2f \t\t\t$ %,.2f\n",user.getPlayerMoney(), computer1.getPlayerMoney(), computer2.getPlayerMoney());
        }
        turn = true;
        complete = (display.phrase.toUpperCase()).equals(String.valueOf(display.screen));
        while (turn && !complete)
        {
          System.out.println();
          System.out.println(computer2.getPlayerName()+" is going to play now");
          System.out.println();
          display.computerTurn(computer2);
          turn = false; //changes turn if letter not found in phrase //4. (BOOL) used
          System.out.println(userName+"\t "+computer1.getPlayerName()+" \t "+computer2.getPlayerName());
          System.out.printf("$ %,.2f \t$ %,.2f \t\t\t$ %,.2f\n",user.getPlayerMoney(), computer1.getPlayerMoney(), computer2.getPlayerMoney());

        }
        turn = true;
        complete = (display.phrase.toUpperCase()).equals(String.valueOf(display.screen));
      } //when while loop finishes someone guessed the phrase

      PrintWriter writer = new PrintWriter("GamerData.txt");
      Player [] arrayofPlayers = {user, computer1, computer2}; //6. ArrayList of objects of a class of your own design
      user.sort(arrayofPlayers);
      System.out.println("\tPlayers in order of points:");
      for (int k=0;k<arrayofPlayers.length;k++)
      {
        int num = k+1;
        System.out.println(num+". "+arrayofPlayers[k].getPlayerName());
      }


      if (arrayofPlayers[0].getPlayerName().equals("Computer Player Ally"))
      {comp2Wins = comp2Wins+ 1;}
      else if (arrayofPlayers[0].equals("Computer Player Tom"))
      {comp1Wins = comp1Wins+ 1;}
      else
      {userWins = userWins+1;}
      writer.println("How many times each run a player wins"); //2. text output file (I/O)
      writer.print(user.getPlayerName()+"\t"+userWins+"\n");
      writer.print(computer1.getPlayerName()+" \t "+comp1Wins+"\n");
      writer.print(computer2.getPlayerName()+"\t "+comp2Wins+"\n");
      writer.close();

      System.out.println("Would you like to play again?");

      char answer = scan.next().charAt(0);
      if (answer == 'Y' || answer == 'y')
      {playAgain = true;}
      else
      {
        playAgain = false;
        System.out.println("How many times each player has won");
        System.out.println(user.getPlayerName()+"\t"+userWins);
        System.out.print(computer1.getPlayerName()+" \t "+comp1Wins+"\n");
        System.out.print(computer2.getPlayerName()+"\t "+comp2Wins+"\n");
      }


    }
  } //end main

  public static String pickAPhrase(Scanner file) //pick a phrase from a certain file
  {
    ArrayList<String> filePhrases = new ArrayList<String>();
    while(file.hasNext())
    {filePhrases.add(file.nextLine());} //adds each line in file to a arraylist

    Random gen = new Random();
    int index = gen.nextInt(filePhrases.size()); //pick a random index to pick the phrase

    String phrase = filePhrases.get(index);
    return phrase; //return the phrase
  }//end pickAPhrase method


}//end GamePlay
