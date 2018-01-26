public class Player
{
  String playerName = "0";
  double playerMoney = 0.00;
  
  public Player(String name)
  {
    playerName = name;
  }//end Player method
  
  public String getPlayerName()
  {
    return playerName;
  }
  
  public void setPlayerMoney(double num)
  {
    playerMoney = num;
  }
  
  public double getPlayerMoney()
  {
    return playerMoney;
  }
  
  public void sort(Player [] arrayofPlayers) //7. Sort Method //10. MYMETH(O)
  {
    for (int i=0;i<arrayofPlayers.length-1;i++)
    {
      for (int j=i+1;j<arrayofPlayers.length;j++)
      {
        if (arrayofPlayers[i].getPlayerMoney() < arrayofPlayers[j].getPlayerMoney())
        {
          Player temp = arrayofPlayers[i];
          arrayofPlayers[i] = arrayofPlayers[j];
          arrayofPlayers[j] = temp; 
        }
      }
    }
  }//end sort
  
  
  
}//end Player