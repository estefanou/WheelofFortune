//Money Wheel
import java.util.Random;

public class moneyWheel
{
  int value;
  double moneyValue;
  
  public moneyWheel()
  { value = 1;
    moneyValue = 2500.00;}
  
  public void display()
  {System.out.print(moneyValue+ "\t");}
  
  public void spin()
  {Random gen = new Random();
    value = gen.nextInt(24)+1;
    setMoney();}
  
  public void setMoney()
  {
    if(value==1)
    {moneyValue = 2500.00;}
    else if (value==2)
    {moneyValue = 200.00;}
    else if (value==3)
    {moneyValue = 900.00;}  
    else if (value==4)
    {moneyValue = 700.00;}
    else if (value==5)
    {moneyValue = 600.00;}
    else if (value==6)
    {moneyValue = 800.00;}
    else if (value==7)
    {moneyValue = 500.00;}
    else if (value==8)
    {moneyValue = 700.00;}
    else if (value==9)
    {moneyValue = 1000.00;}  
    else if (value==10)
    {moneyValue = 600.00;}
    else if (value==11)
    {moneyValue = 550.00;}  
    else if (value==12)
    {moneyValue = 200.00;}  
    else if (value==13)
    {moneyValue = 900.00;}  
    else if (value==14)
    {moneyValue = 300.00;}  
    else if (value==15)
    {moneyValue = 600.00;}  
    else if (value==16)
    {moneyValue = 200.00;}  
    else if (value==17)
    {moneyValue = 700.00;}  
    else if (value==18)
    {moneyValue = 400.00;}  
    else if (value==19)
    {moneyValue = 800.00;}  
    else if (value==20)
    {moneyValue = 200.00;}  
    else if (value==21)
    {moneyValue = 650.00;}  
    else if (value==22)
    {moneyValue = 200.00;}  
    else if (value==23)
    {moneyValue = 900.00;}  
    else if (value==24)
    {moneyValue = 0.00;}
  }
  
  public double getMoney()
  {
    return moneyValue;
  }
  
  public double getMoney(double playermoney)
  {
    playermoney = playermoney+moneyValue;
    return playermoney;
  }
  
  public int getValue()
  {
    return value;
  }
  
  
}
