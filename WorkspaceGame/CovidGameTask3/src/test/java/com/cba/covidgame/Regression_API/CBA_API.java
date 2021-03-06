package com.cba.covidgame.Regression_API;

import java.io.IOException;
import org.testng.annotations.Test;

import com.cba.coivdgame.API_Regression.Library.GenericMethods;
import com.cba.coivdgame.API_Regression.Library.HttpClientHelper;
import com.cba.coivdgame.API_Regression.Library.TestHelper;
import com.cba.coivdgame.Web_Regression.Library.Reusable;
import com.reports.CustomReporter;

/**
 * The Class CBA_API.
 */
public class CBA_API extends com.cba.coivdgame.Web_Regression.Library.SuperTestNG_API
{	
	//static Integer COVIDGameScore;
		 
	/**
	 * 01 - GET    - GET Method - Get User Details of the COVID Game.
 	 * 02 - POST   - POST METHOD - Create User for COVID Game
 	 * 03 - PUT    -  PUT METHOD - Update User Details for COVID Game
 	 * 04 - DELETE - DELETE METHOD - Delete User Details for COVID Game	
	 ** 
	 */
	
	//01 - GET Method - Get User Details of the COVID Game.
	@Test 
	public static void GetUserDetailsCovidGame() throws Exception
	{
		System.out.println("GET=================");
		//Step 01 - Verify EndPoint URL
		GenericMethods.FormatURL(Reusable.getproperty("covid19thegame_Url"));
		CustomReporter.log("URL: "+Reusable.getproperty("covid19thegame_Url")+"Verified EndPoint Successfully");
				
		//Step 02 - Get Response And Put Response In To DTO-CovidgameApplication Class
		com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication requirements = TestHelper.gsonBuilder ().fromJson (HttpClientHelper.MygetUrl(Reusable.getproperty("covid19thegame_Url")), com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication.class);
		CustomReporter.log("Successfully Captured Response For GET URL: "+Reusable.getproperty("covid19thegame_Url")+" And Stored In Respective Data Objects");
						
		//Step 03 - Verify the COVID Game Score of the User On Leadership Board
		//COVIDGameScore = requirements.getScore();		
	//	CustomReporter.log("Successfully Captured COVIDGameScore From Response From URL: "+Reusable.getproperty("covid19thegame_Url")+COVIDGameScore);
		
	}
	
	//POST METHOD - Create User for COVID Game
	@Test 
	public static void PostCreateUserCovidGame() throws Exception
	{	
		System.out.println("POST =================");
		//Step 01 - Verify EndPoint URL
		GenericMethods.FormatURL(Reusable.getproperty("covid19thegame_Url"));
		CustomReporter.log("URL: "+Reusable.getproperty("covid19thegame_Url")+"Verified EndPoint Successfully");
		
		//Step 02 - Add Static Header For API
		HttpClientHelper.Addheader(Reusable.getproperty("covid19thegame_Url_Post_Header1"));
		CustomReporter.log("Added Static Header :"+Reusable.getproperty("covid19thegame_Url_Post_Header1")+" Successfully", true);
				
		//Step 03 - Get Response And Put Response In To DTO-CovidgameApplication Class
		com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication requirements = TestHelper.gsonBuilder ().fromJson(HttpClientHelper.MyPostUrlNN(Reusable.getproperty("covid19thegame_Url"),Reusable.getproperty("covid19thegame_Url_Post_Body0"),null), com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication.class);
		CustomReporter.log("Successfully Captured Response For POST URL: "+Reusable.getproperty("covid19thegame_Url")+" And Stored In Respective Data Objects");
						
	}
		
		
	//PUT METHOD - Update User Details for COVID Game
	@Test 
	public static void PutUserDetailsCovidGame() throws Exception	
	{
		System.out.println("PUT =================");
		//Step 01 - Verify EndPoint URL
		GenericMethods.FormatURL(Reusable.getproperty("covid19thegame_Url"));
		CustomReporter.log("URL: "+Reusable.getproperty("covid19thegame_Url")+"Verified EndPoint Successfully");
				
		//Step 02 - Add Static Header For API
		HttpClientHelper.Addheader(Reusable.getproperty("covid19thegame_Url_Put_Header1"));
		CustomReporter.log("Added Static Header :"+Reusable.getproperty("covid19thegame_Url_Put_Header1")+" Successfully", true);
				
		//Step 02 - Get Response And Put Response In To DTO-CreateTrustedApp Class
		com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication requirements = TestHelper.gsonBuilder ().fromJson(HttpClientHelper.MyPutUrl(Reusable.getproperty("covid19thegame_Url"),Reusable.getproperty("covid19thegame_Url_Put_Body"),null), com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication.class);
		CustomReporter.log("Successfully Captured Response For PUT URL: "+Reusable.getproperty("covid19thegame_Url")+" And Stored In Respective Data Objects");						
			
	}
				
	//Delete METHOD - Delete User Details for COVID Game	
	@Test
	public void DeleteUserDetailsCovidGame() throws InterruptedException, IOException 
	{
		System.out.println("DELETE =================");
		//Step 01 - Verify EndPoint URL
		GenericMethods.FormatURL(Reusable.getproperty("covid19thegame_Url"));
		CustomReporter.log("URL: "+Reusable.getproperty("covid19thegame_Url")+"Verified EndPoint Successfully");
		
		//Step 02 - Get Response And Put Response In To DTO-DeleteDeveloperApp Class
		com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication requirements = TestHelper.gsonBuilder ().fromJson (HttpClientHelper.MyDeleteUrl(Reusable.getproperty("covid19thegame_Url")), com.cba.coivdgame.API_Regression.DTOs.CovidgameApplication.class);
		//CustomReporter.log("Successfully Captured Response For Delete URL: "+Reusable.getproperty("covid19thegame_Url"));
		
		
	}
			
	
	


}