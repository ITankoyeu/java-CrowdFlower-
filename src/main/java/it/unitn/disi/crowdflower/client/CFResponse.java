package it.unitn.disi.crowdflower.client;
import it.unitn.disi.crowdflower.client.model.CrowdFlowerJob;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CFResponse {

	public HashMap<Integer,CrowdFlowerJob> parseJsonMassJobResponse(String inputStr) throws IOException, ParseException{ 
		HashMap<Integer,CrowdFlowerJob> jobMap = new HashMap<Integer,CrowdFlowerJob>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(inputStr);
		JSONArray arr = (JSONArray) obj;
		for (int i=0; i<arr.size(); i++){
			JSONObject jObj = (JSONObject) arr.get(i);
			long jobIdL = (Long) jObj.get("id");
			int id = (int) jobIdL;
			CrowdFlowerJob cfJob = parseJSONObjJob(jObj);
			jobMap.put(id, cfJob);
		}

		return jobMap;
	}

	public ArrayList<String> parseJSONArr(JSONArray arr){
		ArrayList<String> parsedArr = new ArrayList<String>();
		if (arr != null) { 
			int len = arr.size();
			for (int i=0;i<len;i++){ 
				parsedArr.add(arr.get(i).toString());
			} return parsedArr; }
		else return null;
	}

	public 	CrowdFlowerJob  parseJSONObjJob(String inStr) throws ParseException{

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(inStr);
		JSONObject jObj = (JSONObject) obj; 	
		CrowdFlowerJob cfJob = parseJSONObjJob(jObj);
		return cfJob;

	}

	public CrowdFlowerJob parseJSONObjJob(JSONObject jObj) throws ParseException{


		long jobIdL = (Long) jObj.get("id");
		int id = (int) jobIdL;
		//System.out.println(" id= "+id);
		Boolean  auto_order =  (Boolean)  jObj.get("auto_order");
		String  auto_order_threshold = (String) jObj.get("auto_order_threshold");
		String  auto_order_timeout = (String) jObj.get("auto_order_timeout");
		String  cml = (String) jObj.get("cml");
		String  cml_fields = (String) jObj.get("cml_fields");

		JSONArray  conf_flds_JSONarr = (JSONArray) jObj.get("confidence_fields");
		ArrayList<String> confidence_fields = parseJSONArr(conf_flds_JSONarr);
		String  css = (String) jObj.get("css");
		String  custom_key = (String) jObj.get("custom_key");

		JSONArray  exl_cntrs_JSONarr = (JSONArray) jObj.get("excluded_countries");
		ArrayList<String>  excluded_countries = parseJSONArr(exl_cntrs_JSONarr);
		long  gold_per_assignment = (Long) jObj.get("gold_per_assignment");

		JSONArray  incl_cntrs = (JSONArray) jObj.get("included_countries");
		ArrayList<String>  included_countries = parseJSONArr(incl_cntrs);

		String  instructions = (String) jObj.get("instructions");
		String  js = (String) jObj.get("js");
		long  judgments_per_unit = (Long) jObj.get("judgments_per_unit");
		String  language = (String) jObj.get("language");

		long  max_judgments_per_unit;
		if (jObj.get("max_judgments_per_unit")!=null){
			max_judgments_per_unit = (Long) jObj.get("max_judgments_per_unit");}
		else {max_judgments_per_unit=-1;}

		long  max_judgments_per_worker;
		if (jObj.get("max_judgments_per_worker")!=null){
			max_judgments_per_worker = (Long) jObj.get("max_judgments_per_worker");}
		else {max_judgments_per_worker=-1;}
		;
		String  min_unit_confidence = (String) jObj.get("min_unit_confidence");

		JSONObject optionsJSONObj = (JSONObject) jObj.get("options");

		Boolean track_clones = (Boolean) optionsJSONObj.get("track_clones");
		Boolean include_unfinished =(Boolean) optionsJSONObj.get("include_unfinished");
		Boolean front_load = (Boolean) optionsJSONObj.get("front_load");
		String mail_to = (String) optionsJSONObj.get("mail_to");

		long after_gold;
		if (optionsJSONObj.get("after_gold") instanceof String)
		{after_gold = Long.parseLong((String) optionsJSONObj.get("after_gold"));}
		else if(optionsJSONObj.get("after_gold")!=null)
		{after_gold = (Long) optionsJSONObj.get("after_gold");}
		else after_gold=-1;

		long calibrated_unit_time;
		if (optionsJSONObj.get("calibrated_unit_time") instanceof String){
			calibrated_unit_time = Long.parseLong((String) optionsJSONObj.get("calibrated_unit_time"));}
		else if (optionsJSONObj.get("calibrated_unit_time")!=null)
		{calibrated_unit_time=(Long) optionsJSONObj.get("calibrated_unit_time");}	
		else calibrated_unit_time=-1;

		String keywords = (String) optionsJSONObj.get("keywords");

		JobOptions options = new JobOptions(track_clones, include_unfinished, front_load, 
				mail_to, after_gold, calibrated_unit_time,keywords);

		String  pages_per_assignment = (String) jObj.get("pages_per_assignment");
		String  problem = (String) jObj.get("problem");
		String  send_judgments_webhook = (String) jObj.get("send_judgments_webhook");
		String  state = (String) jObj.get("state");
		String  title = (String) jObj.get("title");
		long  units_per_assignment = (Long) jObj.get("units_per_assignment");
		String  webhook_uri = (String) jObj.get("webhook_uri");

		Boolean  completed = (Boolean) jObj.get("completed");
		String  completed_at = (String) jObj.get("completed_at");
		String  created_at = (String) jObj.get("created_at");
		// must be JSON obj	  gold = (Long) jObj.get("gold");

		long  golds_count = (Long) jObj.get("golds_count");
		long  judgments_count = (Long) jObj.get("judgments_count");
		long  units_count = (Long) jObj.get("units_count");
		String  updated_at = (String) jObj.get("updated_at");

		CrowdFlowerJob cfJob = new CrowdFlowerJob();
		cfJob.setAuto_order(auto_order);
		cfJob.setAuto_order_threshold(auto_order_threshold);
		cfJob.setAuto_order_timeout(auto_order_timeout);
		cfJob.setCml(cml);
		cfJob.setCml_fields(cml_fields);
		cfJob.setConfidence_fields(confidence_fields);
		cfJob.setCss(css);
		cfJob.setCustom_key(custom_key);
		cfJob.setExcluded_countries(excluded_countries);
		cfJob.setGold_per_assignment(gold_per_assignment);
		cfJob.setIncluded_countries(included_countries);
		cfJob.setInstructions(instructions);
		cfJob.setJs(js);
		cfJob.setJudgments_per_unit(judgments_per_unit);
		cfJob.setLanguage(language);
		cfJob.setMax_judgments_per_unit(max_judgments_per_unit);
		cfJob.setMax_judgments_per_worker(max_judgments_per_worker);
		cfJob.setMin_unit_confidence(min_unit_confidence);
		cfJob.setOptions(options);
		cfJob.setPages_per_assignment(pages_per_assignment);
		cfJob.setProblem(problem);
		cfJob.setSend_judgments_webhook(send_judgments_webhook);
		cfJob.setState(state);
		cfJob.setTitle(title);
		cfJob.setUnits_per_assignment(units_per_assignment);
		cfJob.setWebhook_uri(webhook_uri);
		cfJob.setId(id);
		cfJob.setCompleted(completed);
		cfJob.setCompleted_at(completed_at);
		cfJob.setCreated_at(created_at);
		cfJob.setGolds_count(golds_count);
		cfJob.setJudgments_count(judgments_count);
		cfJob.setUnits_count(units_count);
		cfJob.setUpdated_at(updated_at);
		return cfJob;}

	public int parseJSONObjMessage(String inputStr) throws ParseException{

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(inputStr);
		JSONObject jObj = (JSONObject) obj; 	
		if (jObj.get("success")!=null){
		//	System.out.println("success");
			return 1;
		}
		else  if (jObj.get("notice")!=null){
			System.out.println("notice");
			return 0;}
		else  if (jObj.get("error")!=null){
			//JSONObject jo = (JSONObject) jObj.get("error");
			System.out.println(inputStr);
			// System.out.println("error");
			return -1;}
		return  2;
	}

	public Boolean isMessage(String inputStr) throws ParseException{
		JSONParser parser = new JSONParser();
		System.out.println(inputStr);
		Object obj = parser.parse(inputStr);
		if (obj instanceof JSONArray){
			return false;}//if the object is JSONarray there is no message a priori
		JSONObject jObj = (JSONObject) obj; 	
		if ((jObj.get("error")!=null)&&(jObj.get("success")!=null)&&(jObj.get("notice")!=null))
		{ return true;	 }
		else return false;

	}

	public String parseLegend(String inputStr) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(inputStr);
		JSONObject jObj = (JSONObject) obj; 
		String phoneNum = (String) jObj.get("phone_number");
		String websiteUrl = (String) jObj.get("website_url");
		String email = (String) jObj.get("email_address");
		String legend = "Phone number: "+phoneNum+"; "+
				"Website url: "+websiteUrl+"; "+
				"Email: "+email+";";
		System.out.println(legend);
				return legend;
	}
	
	public String parseChannelView(String inputStr) throws ParseException{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(inputStr);
			JSONObject jObj = (JSONObject) obj; 
			JSONArray availableChannelsArr = (JSONArray)jObj.get("available_channels");
			JSONArray enabledChannelsArr = (JSONArray)jObj.get("enabled_channels");
			//JSONObject enabledChannel =(JSONObject) enabledChannelsArr.get(0);
			return enabledChannelsArr.toJSONString();
	}
	
	
	public Long parseStatus(String inStr) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(inStr);
		JSONObject jObj = (JSONObject) obj; 
		Long neededGudgements = (Long)jObj.get("needed_judgments");
		return neededGudgements;
	} 
	
	public String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
