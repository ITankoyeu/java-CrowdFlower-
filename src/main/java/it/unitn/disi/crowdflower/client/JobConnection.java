package it.unitn.disi.crowdflower.client;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



public class JobConnection {
	public String buildURI( ArrayList<NameValuePair> pairs, String type, String id, String command, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+id+"/"+command+"."+out+"?";
		String paramString = URLEncodedUtils.format(pairs, "utf-8");
		url += paramString;
		return url;
	}
	public String buildURIGetChannel( ArrayList<NameValuePair> pairs, String type, String id, String command){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+id+"/"+command+"?";
		String paramString = URLEncodedUtils.format(pairs, "utf-8");
		url += paramString;
		return url;
	}
	//without type
	public String buildURI( ArrayList<NameValuePair> pairs, String type, String id, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+id+"."+out+"?";
		String paramString = URLEncodedUtils.format(pairs, "utf-8");
		url += paramString;
		return url;
	}
	//without type and ID
	public String buildURIGet( ArrayList<NameValuePair> pairs, String type, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"."+out+"?";
		//	System.out.println(url);
		String paramString = URLEncodedUtils.format(pairs, "utf-8");
		url += paramString;
		return url;
	}

	//without type
	public String buildURIUpload(String type,  ArrayList<NameValuePair> pairs,   String command, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+command+"."+out+"?";
		String paramString = URLEncodedUtils.format(pairs, "utf-8");
		url += paramString;
		return url;
	}

	//without type and ID
	//	public String buildURIPost(ArrayList<NameValuePair> pairs,String type, String out){
	//		String url;
	//		String st = "https://api.crowdflower.com/v1";
	//		url = st+"/"+type+"."+out+"?";
	//		System.out.println(url);
	//		return url;
	//	}

	public String buildURIPost(String type, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"."+out+"?";
		//System.out.println(url);
		return url;
	}

	public String buildUriP( String type, String out, String param){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+param+"."+out+"?";
		//System.out.println(url);
		//String paramString = URLEncodedUtils.format(pairs, "utf-8");
		//	url += paramString;
		return url;
	}

	public String buildUriP( String type, String param, String command, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+param+"/"+command+"."+out+"?";
		//System.out.println(url);
		//String paramString = URLEncodedUtils.format(pairs, "utf-8");
		//	url += paramString;
		return url;
	}

	public String buildUriChannel( String type, String param, String command){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+param+"/"+command+"?";
		//System.out.println(url);
		//String paramString = URLEncodedUtils.format(pairs, "utf-8");
		//	url += paramString;
		return url;
	}

	public String buildUriUpload( String type, String param, String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/"+param+"/upload"+out+"?";
		return url;
	}

	public String buildUriUpload( String type,  String out){
		String url;
		String st = "https://api.crowdflower.com/v1";
		url = st+"/"+type+"/upload"+out+"?";
		return url;
	}

	public InputStream HTTPmethodGet(String Url) throws ClientProtocolException, IOException  {

		HttpClient httpclient = new DefaultHttpClient();

		//System.out.println(Url);
		HttpGet httpGet = new HttpGet(Url);
		HttpResponse response = httpclient.execute(httpGet);	
		//check the status of request
		int code = response.getStatusLine().getStatusCode();
		if (code!=200){
			System.out.println("Response from CrowdFlower: "+decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}		
		HttpEntity entity =	response.getEntity();
		InputStream is=null;
		if (entity != null) {
			is = entity.getContent();
		}
		return is;


	}

	public InputStream HTTPmethodPost(List<NameValuePair> pairs, String url) throws ClientProtocolException, IOException, URISyntaxException{
		HttpClient httpclient = new DefaultHttpClient();
		UrlEncodedFormEntity ent = new UrlEncodedFormEntity(pairs);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(ent);
		HttpResponse response = httpclient.execute(httpPost);
		//check the status of request
		int code = response.getStatusLine().getStatusCode();
		if (code!=200){
			System.out.println("Response from CrowdFlower: "+decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}
		HttpEntity entity =	response.getEntity();
		InputStream is=null;
		if (entity != null) {
			is = entity.getContent();
		}
		return is;
	} 




	public InputStream HTTPmethodPostUpload (File file, String url, String format) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		//	System.out.println("https://api.crowdflower.com/v1/jobs"+param+"json?key="+authKey);
		HttpPost httpPost = new HttpPost(url);

		FileEntity fEntity = new FileEntity(file, format);
		//fEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"text/csv"));
		fEntity.setContentType(format);
		httpPost.setEntity(fEntity);

		HttpResponse response = httpclient.execute(httpPost);
		//check the status of request
		
		int code = response.getStatusLine().getStatusCode();
		if (code!=200){
			System.out.println("Response from CrowdFlower: "+decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}
		HttpEntity entityResponse =	response.getEntity();
		InputStream is=null;
		if (entityResponse != null) {
			is = entityResponse.getContent();
		}
		return is;
	} 


	//	public InputStream HTTPmethodPost(String authKey, String param) throws ClientProtocolException, IOException{
	//		HttpClient httpclient = new DefaultHttpClient();
	//		HttpPost httpPost = new HttpPost("https://api.crowdflower.com/v1/jobs"+param+"json?key="+authKey);
	//		HttpResponse response = httpclient.execute(httpPost);
	//		HttpEntity entity =	response.getEntity();
	//		InputStream is=null;
	//		if (entity != null) {
	//			is = entity.getContent();
	//		}
	//		return is;
	//	} 





	public InputStream HTTPmethodDelete(String url) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpDelete = new HttpDelete(url);
		HttpResponse response = httpclient.execute(httpDelete);
		//check the status of request
		int code = response.getStatusLine().getStatusCode();
		if (code!=200){
			System.out.println("Response from CrowdFlower: "+decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}
		HttpEntity entity =	response.getEntity();
		InputStream is=null;
		if (entity != null) {
			is = entity.getContent();
		}
		return is;
	}

	public InputStream HTTPmethodPut(List<NameValuePair> pairs, String url) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		UrlEncodedFormEntity ent = new UrlEncodedFormEntity(pairs,"utf-8");
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(ent);
		HttpResponse response = httpclient.execute(httpPut);
		int code = response.getStatusLine().getStatusCode();
		if (code!=200){
			System.out.println("Response from CrowdFlower: "+decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}
		HttpEntity entity =	response.getEntity();
		InputStream is=null;

		if (entity != null) {
			is = entity.getContent();
		}
		return is;
	} 




	public InputStream HTTPmethodPut(String authKey, String param) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		//System.out.println("https://api.crowdflower.com/v1/jobs/"+param+"json?key="+authKey);
		HttpPut httpPut = new HttpPut("https://api.crowdflower.com/v1/jobs/"+param+"json?key="+authKey);
		HttpResponse response = httpclient.execute(httpPut);
		int code = response.getStatusLine().getStatusCode();
		if (code!=200){
			System.out.println("Response from CrowdFlower: "+decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}
		HttpEntity entity =	response.getEntity();
		InputStream is=null;
		if (entity != null) {
			is = entity.getContent();
		}
		return is;
	}

	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public  String getAuthKey(){
		Properties prop = new Properties();
		String authKey=null;

		try {
			//load a properties file
			prop.load(new FileInputStream("config.properties"));

			//get the authentication key from properties file 
			authKey = prop.getProperty("authKey");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return authKey;
	}
	public InputStream HTTPmethodPostUpload(String jsonTask, String url,
			String format) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		//	System.out.println("https://api.crowdflower.com/v1/jobs"+param+"json?key="+authKey);
		HttpPost httpPost = new HttpPost(url);

		StringEntity sEntity = new StringEntity(jsonTask);
		//fEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"text/csv"));
		sEntity.setContentType(format);
		httpPost.setEntity(sEntity);

		HttpResponse response = httpclient.execute(httpPost);
		//check the status of request
		int code = response.getStatusLine().getStatusCode();
		if (code==200)
			System.out.println(response.getStatusLine().getReasonPhrase());
		if (code!=200){
			System.out.println(decodeMessage(code));
			if (code!=202){
				if (code==500){
					System.out.println("Reason: "+response.getStatusLine().getReasonPhrase());
				}
				System.exit(0); 
			}
		}

		HttpEntity entityResponse =	response.getEntity();
		InputStream is=null;
		if (entityResponse != null) {
			is = entityResponse.getContent();
		}
		return is;
	} 

	/** The method translates status code given with response from to 
	 * @param code
	 * @return
	 */
	public String decodeMessage(int code){
		String message=null;
		switch (code) {
		case 202:  message = "202 Accepted";
		break;
		case 302:  message = "302 Redirect";
		break;
		case 400:  message = "400 Bad Request";
		break;
		case 401:  message = "401 Unauthenticated. "
				+ "Authorization failed, check your key.";
		break;
		case 402:  message = "402 Payment Required."
				+ " The operation you are trying to complete requires payment.";
		break;
		case 404:  message = "404 Not Found."
				+ " The resource you are referencing does not exist, or you don't own it.";
		break;
		case 405:  message = "405 Method Not Allowed."
				+ " The method you are using (GET, POST, PUT, DELETE) is not available from this URL.";
		break;
		case 406:  message = "406 Not Acceptable. "
				+ "The format you requested is not available.";
		break;
		case 500:  message = "500 Internal Server Error. An error occurred on the server.";
		break;

		default: message = Integer.toString(code);
		break;

		}
		return message;	
	}

}
