package it.unitn.disi.crowdflower.client;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FormJSON {

	public JSONObject formJsonObjCountry(String input){

		JSONObject obj=new JSONObject();
		  obj.put("name",input);
		return obj;
	}

	public JSONArray formJsonArray(ArrayList<JSONObject> jsonArr){
		 JSONArray list = new JSONArray();
		for (int i =0; i>jsonArr.size(); i++){
			list.add(jsonArr.get(i));
		
		}
		return list; 
	}
	
	public JSONArray formJsonArrayCountries(){
		
		ArrayList<JSONObject> jsonArr = new ArrayList<JSONObject>();
		jsonArr.add(formJsonObjCountry("Argentina"));
		JSONArray jArr = formJsonArray(jsonArr);
		return jArr;
	}
	
	
}
