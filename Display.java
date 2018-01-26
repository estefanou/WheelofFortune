import java.util.ArrayList;
import java.util.Random;
public class Display //5. (CL)
{
  String phrase;
  char[] screen;
  char[] alphabet = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
  ArrayList<Character> changeAlphabet = new ArrayList<Character>();
  ArrayList<Character> phraseLetterList = new ArrayList<Character>();

  //9. MYMETH methods of own design
   public Display()
   {
     phrase = "0";
     screen = new char[1];
   }//end constructor


  public Display(String s)
  {
    phrase = s;
    screen = new char[phrase.length()];
    char[] phraseArray = s.toCharArray();
    for (int i=0; i<screen.length; i++)
    {
      if (phraseArray[i] == ' ')
        screen[i] = ' ';
      else
        screen[i] = '_';
    }
  }//end display method

  public void showPhrase()
  {
    for(char each:screen)
    {System.out.print(each+" ");}
    System.out.println();
  }//end showPhrase method

  public boolean checkActual()
  {
    for (char each:screen)
    {if (each == '_')
      {GamePlay.phraseComplete = false;
    break;}
    else
    {GamePlay.phraseComplete = true;}

    }
    return GamePlay.phraseComplete;

  }

  // debugging purposes
  public void actualPhrase()
  {System.out.println(phrase);}//end actualPhrase method


  public void useAlphabet()
  {
    for (char each:alphabet)
    {changeAlphabet.add(each);}
  }//end use alphabet

  public boolean checkAlpha(char c) //checks to see if letter was already chosen
  {//search
    boolean validLetter = false;
    char letter = Character.toUpperCase(c);
    if (changeAlphabet.contains(letter)) //if letter in alphabet arraylist, remove from arraylist and return true
    {System.out.println("valid letter");
      int value = changeAlphabet.indexOf(letter);
      changeAlphabet.remove(value);
      validLetter = true;}
    else
    {System.out.println("letter already chosen OR not a letter"); //if letter not in alphabet arraylist, return false
      validLetter = false;}
    return validLetter;
  }//end checkAlpha

  public void process(char c) //checks for a character in the phrase
  {
    char letter = Character.toUpperCase(c);
    char[] phraseArray = phrase.toUpperCase().toCharArray(); //compare letter to the phrase
    ArrayList<Integer> indeces = getAllIndeces(phraseArray, letter);

    if (indeces.size() != 0)
      //when size of indeces array doesn't equal 0, change the screen view of phrase to show letter at those indeces
    {
      System.out.println("Good Guess! Letter Found, Guess again!");
      for (int i = 0; i<indeces.size(); i++)
      {
        int index = indeces.get(i);
        screen[index] = letter;
      }
      checkActual();
      GamePlay.letterInPhrase = true;
      showPhrase();//reprint the phrase with the characters searched for once all instances of character are found
    }
    else
    {System.out.println("Sorry, Letter not in Phrase Computer's turn");
      checkActual();
      GamePlay.letterInPhrase = false;
      showPhrase();}
  }//end process a character

  public void process(String s)//check if the phrase guess equals the actual phrase
  {
    String phraseGuess = s.toUpperCase();
    String phraseUp = phrase.toUpperCase();
    //comparing guess and actual
    if (phraseGuess.equals(phraseUp))
    {
      System.out.println("You Guessed correctly!");
      screen = phraseUp.toCharArray();
      GamePlay.letterInPhrase = true;
      showPhrase();
//      System.out.println("The phrase is: "+phrase);
    }
    else
    {System.out.println("Incorrect Guess Next Player's turn");
      GamePlay.letterInPhrase = false;
    }
  }//end process a string

  //8. SEARCH method
  public static ArrayList<Integer> getAllIndeces(char[] array, char target) //searches for indexes of letter in the array
  {
    ArrayList<Integer> indeces = new ArrayList<Integer>();
    for(int  i = 0; i<array.length; i++)
    {
      if (array[i] == target)
      {
        indeces.add(i); //adds the index where target was found to the arraylist indeces
      }
    }

    return indeces;
  }//end getAllIndeces

  public void computerTurn(Player p)
  {
    boolean computerGuess = true;
    while (computerGuess) //to continue playing if letter was found in phrase
    {

      moneyWheel computer = new moneyWheel();
      computer.spin();
      if (computer.getMoney() == 0.00)
      {System.out.println(p.getPlayerName()+" went Bankrupt!");
        p.setPlayerMoney(0.00);
        computerGuess = false;
        GamePlay.playersTurn = true;}
      else
      {System.out.println(p.getPlayerName()+" Guessing for $"+computer.getMoney());}
      int a = changeAlphabet.size();
      Random randGen = new Random();
      int c = randGen.nextInt(a); //pick a random index in the alphabet arraylist //11. (RANDOM)
      char letterGuess = changeAlphabet.get(c);
      System.out.println(p.getPlayerName()+" Guess: \n\t\t"+letterGuess);

      char[] phraseArray = phrase.toUpperCase().toCharArray();
      ArrayList<Integer> indeces = getAllIndeces(phraseArray, letterGuess);

      if (indeces.size() != 0) //when letter found in phrase
      {
        System.out.println("Good Guess! Letter Found,"+p.getPlayerName()+" guess again!");
        for (int i = 0; i<indeces.size(); i++) //change all indeces where letter is found to the letter
        {
          int index = indeces.get(i);
          screen[index] = letterGuess;
        }
        p.setPlayerMoney(p.getPlayerMoney() + computer.getMoney());
        int indexToRemove = changeAlphabet.indexOf(letterGuess);
        changeAlphabet.remove(indexToRemove);
        showPhrase();
      }
      else
      {System.out.println("Sorry, Letter not in Phrase Next Player's turn"); //when letter not in phrase
        GamePlay.letterInPhrase = false;
        showPhrase();
        int indexToRemove = changeAlphabet.indexOf(letterGuess);
        changeAlphabet.remove(indexToRemove);
        computerGuess = false;
        break;
      }//end computers guess
      if ((phrase.toUpperCase()).equals(String.valueOf(screen)))
      {System.out.println(p.getPlayerName()+" guessed the Phrase!");
        break;}
    }
  }//end computerTurnTurn

} //end Display
