package com.cba.covidgame.Regression_WEB;

import org.testng.annotations.Test;

import com.cba.coivdgame.Web_Regression.Library.Implementation;
import com.cba.coivdgame.Web_Regression.Library.Reusable;
import com.cba.coivdgame.Web_Regression.Library.SuperTestNG_WEB;
import com.cba.coivdgame.Web_Regression.PageObject.Covid19_TheGame_Page;
import com.reports.CustomReporter;

/**
 * The Class Registration.
 */
public class CBA_Web extends com.cba.coivdgame.Web_Regression.Library.SuperTestNG_WEB
{		 
  	 
	 /**
 	 * #001 - Super Villian - Web Automation
 	 * 01 - Launch Browser SuperHeros and SuperVillain WebSite
 	 * 02 - Enter User name & Click create Warrior Button and Start Journey  Button 
 	 * 03 - Click Public Place Battle Button,Start Battle and Answer the questions
 	 * 04 - Click Bus Battle Button,Start Battle and Answer the questions 	
 	 * 05 - Click Restaurant Battle Button,Start Battle and Answer the questions
 	 * 06 - Click Inside Office Battle Button,Start Battle and Answer the questions
 	 * 07 - Verify the Leadership Board Points
 	 * */
 	 @Test
	 public void CovidGame() throws Exception
	 {	
 				 
 		//Step 01 - Launch Browser SuperHeros and SuperVillain WebSite
 		SuperTestNG_WEB.OpenUrl(driver,Reusable.getproperty("SuperHeros_SuperVillain_Url")); 
 		CustomReporter.log("Successfully Opened SuperHeros and SuperVillain Home Page");
 		CustomReporter.logWithScreenShot("Successfully Opened SuperHeros and SuperVillain Home Page");
 		
 		//Step 02 - Enter User name & Click create Warrior Button and Start Journey  Button 
 		Covid19_TheGame_Page pageobject = new Covid19_TheGame_Page(driver);
 		Reusable.waitForPageToLoad(driver);
 		Implementation.AddText(driver,pageobject.UserName(),Reusable.getproperty("UserName"));
 		Implementation.ClickObject(driver,pageobject.WarriorBtn());
 		Implementation.ClickObject(driver,pageobject.startBtn());
 		
 		//Step 03 - Click Public Place Battle Button,Start Battle and Answer the questions
 		Reusable.waitForPageToLoad(driver);
 		Implementation.ClickObject(driver,pageobject.newsBtn());
 		
 		Implementation.ClickObject(driver,pageobject.startBtn());
 		Implementation.ClickObject(driver,pageobject.NewsAnswer1()); 		
 		Implementation.ClickObject(driver,pageobject.ContinuetoNextPage());
 		
 		Reusable.waitForPageToLoad(driver);
 		Implementation.ClickObject(driver,pageobject.NewsAnswer2());
		Implementation.ClickObject(driver,pageobject.ContinuetoNextPage());
		
		Reusable.waitForPageToLoad(driver);
 		Implementation.ClickObject(driver,pageobject.NewsAnswer3());
		Implementation.ClickObject(driver,pageobject.ContinuetoNextPage());
		
		Implementation.ClickObject(driver,pageobject.leaderboardLink());
	
		//Step 04 - Click Bus Battle Button,Start Battle and Answer the questions
		Reusable.waitForPageToLoad(driver);
		Implementation.ClickObject(driver,pageobject.BusBtn());
		Implementation.ClickObject(driver,pageobject.BusPagestart());
		Implementation.ClickObject(driver,pageobject.BusLevelTaskAnswer());
		
		Implementation.ClickObject(driver,pageobject.NextBattleBtn());
		
		//Step 05 - Click Restaurant Battle Button,Start Battle and Answer the questions
		Reusable.waitForPageToLoad(driver);
		Implementation.ClickObject(driver,pageobject.RestaurantPageStart());
		Implementation.ClickObject(driver,pageobject.RestaurantLevelTaskAnswer());
		
		Implementation.ClickObject(driver,pageobject.NextBattleBtn());
		
		//Step 06 - Click Inside Office Battle Button,Start Battle and Answer the questions
		Reusable.waitForPageToLoad(driver);
		Implementation.ClickObject(driver,pageobject.startBtn());
		Implementation.ClickObject(driver,pageobject.InsideofficeLevelTaskAnswer());
 		
		Implementation.ClickObject(driver,pageobject.leaderboardLink());
		
		//Step 07 - Verify the Leadership Board Points		
		String LeaderboardPts=Implementation.GetText(driver, pageobject.LeaderboardPoints()); 		 		 
		CustomReporter.log("Points on Leadership Board  ::"+LeaderboardPts,true);				
		  if (LeaderboardPts.equalsIgnoreCase("4000") )	  {
		  CustomReporter.log("Leadership Board reached to 4000,  ,Actual value from board is "+LeaderboardPts,true); 
		  } else	  {
			  CustomReporter.log("Leadership Board not reached to 4000 ,Actual value from board is "+LeaderboardPts,true); 
		 }			  
 		
	 }
}
