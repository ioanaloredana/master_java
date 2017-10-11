package ro.tirzuman.ioana.fx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class Sender implements Runnable {
	private String url;
	private String requestType;

	public Sender(String url, String requestType) {
		this.url = url;
		this.requestType = requestType;
	}

	@Override
	public void run() {
		try {
			if (requestType.equalsIgnoreCase("get")) {
				sendGet();
			} else if (requestType.equalsIgnoreCase("post")) {
				sendPost();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendGet() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		//System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		//System.out.println(result);
	}

	private void sendPost() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		// post.setHeader("User-Agent", "");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		String keyValue = UUID.randomUUID().toString();
		urlParameters.add(new BasicNameValuePair("key", keyValue));
		urlParameters.add(new BasicNameValuePair("value", keyValue));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		//System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		//System.out.println(result);
	}
}
