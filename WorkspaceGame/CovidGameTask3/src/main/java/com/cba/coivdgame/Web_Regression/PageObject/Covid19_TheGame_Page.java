package com.cba.coivdgame.Web_Regression.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Covid19_TheGame_Page
{
	//Constructor
	public Covid19_TheGame_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
		
	//<<Variables>>
	//<<Home Page>>
	//============//
	//UserName
	@FindBy(id="worrior_username")
	private WebElement UserName;

	//Warrior Button
	@FindBy(id="warrior")
	private WebElement WarriorBtn;
	
	//startBtn
	@FindBy(id="start")
	private WebElement startBtn;
	
	//newsBtn
	@FindBy(id="news")
	private WebElement newsBtn;
	
	//NewsStartBTN
	@FindBy(id="start")
	private WebElement NewsStartBTN;
	

	//NewsAnswer1
	@FindBy(xpath="//div//a[text()='Use your superheroes Mask and sanitizer while traveling on public transport and clean your hands regularly.']")
	private WebElement NewsAnswer1;


	//NewsStartBTN
	@FindBy(id="continue")
	private WebElement ContinuetoNextPage;

	//NewsAnswer2
	@FindBy(xpath="//div//a[text()='Use your superhero Social Distance and notify your Manager.']")
	private WebElement NewsAnswer2;
	
	//NewsAnswer3
	@FindBy(xpath="//div//a[text()='Use your super hero sanitizer, keep a safe distance and advise they should keep 1.5 metres away from others.']")
	private WebElement NewsAnswer3;
	
	
	
	//leaderboardLink
	@FindBy(id="leaderboard_link")
	private WebElement leaderboardLink;

	
	//BusBtn
	@FindBy(id="bus")
	private WebElement BusBtn;
	
	//BusPagestart
	@FindBy(id="bus_timer_start")
	private WebElement BusPagestart;
	
	
	//BusLevelTaskAnswer
	@FindBy(xpath="//div//a[text()='Use your superheroes Mask and sanitizer while traveling on public transport and clean your hands regularly.']")
	private WebElement BusLevelTaskAnswer;
	
	//NextBattle
	@FindBy(id="close_correct_modal_btn")
	private WebElement NextBattleBtn;
	
	//RestaurantPageStart
	@FindBy(id="restaurant_timer_start")
	private WebElement RestaurantPageStart;
	
	//RestaurantLevelTaskAnswer
	@FindBy(xpath="//div//a[text()='Use your super hero sanitizer, keep a safe distance and advise they should keep 1.5 metres away from others.']")
	private WebElement RestaurantLevelTaskAnswer;
	
	
	//InsideofficeLevelTaskAnswer
	@FindBy(xpath="//div//a[text()='Use your superhero Social Distance and notify your Manager.']")
	private WebElement InsideofficeLevelTaskAnswer;
			
	//LeaderboardPoints
	@FindBy(xpath="//p[@id='showData']//tbody//tr[td[text()='Sowmya']]/td[3]")
	private WebElement LeaderboardPoints;
	
	
	//<<Methods>>
	//<<Home Page>>
	//UserName
	public WebElement UserName()
	{
	   return this.UserName;
	}
	
	public WebElement WarriorBtn()
	{
	   return this.WarriorBtn;
	}
	
	public WebElement startBtn()
	{
	   return this.startBtn;
	}
		
	
	public WebElement newsBtn()
	{
	   return this.newsBtn;
	}
	
	
	public WebElement NewsStartBTN()
	{
	   return this.NewsStartBTN;
	}
	
	
	public WebElement ContinuetoNextPage()
	{
	   return this.ContinuetoNextPage;
	}
	
	
	public WebElement NewsAnswer1()
	{
	   return this.NewsAnswer1;
	}
	
	public WebElement NewsAnswer2()
	{
	   return this.NewsAnswer2;
	}
	
	public WebElement NewsAnswer3()
	{
	   return this.NewsAnswer3;
	}
	
	public WebElement leaderboardLink()
	{
	   return this.leaderboardLink;
	}
		
	public WebElement BusBtn()
	{
	   return this.BusBtn;
	}
	
	public WebElement BusPagestart()
	{
	   return this.BusPagestart;
	}
	
	public WebElement BusLevelTaskAnswer()
	{
	   return this.BusLevelTaskAnswer;
	}
	
	public WebElement NextBattleBtn()
	{
	   return this.NextBattleBtn;
	}
	
	public WebElement RestaurantPageStart()
	{
	   return this.RestaurantPageStart;
	}
	
	
	public WebElement RestaurantLevelTaskAnswer()
	{
	   return this.RestaurantLevelTaskAnswer;
	}
	
	
	public WebElement InsideofficeLevelTaskAnswer()
	{
	   return this.InsideofficeLevelTaskAnswer;
	}

		
	public WebElement LeaderboardPoints()
	{
	   return this.LeaderboardPoints;
	}
	
	
		
}