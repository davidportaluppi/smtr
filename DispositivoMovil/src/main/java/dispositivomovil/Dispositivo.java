package dispositivomovil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class Dispositivo {
	// conectarse al server
	// enviar datos cada 1 sg
	private DefaultHttpClient httpclient;
	private int postProcessedCount = 0;
	private List<Map<String, String>> valuesToSend;
	private String assetID;
	public Dispositivo(String assetID) {
		httpclient = new DefaultHttpClient();
		this.assetID = assetID;
	}
	
	
	public void runDevice(int postAenviar) throws IOException, ParseException {
		valuesToSend = this.readScvFile();
		if (postAenviar < 0) {
			postAenviar = valuesToSend.size()-1;
		}
		// Esperar hasta que termine la ejecucion del test
		while (postProcessedCount < postAenviar) {
			try {
				Map<String, String> values = buildParams(postProcessedCount);
				
				sendValues(values);
				postProcessedCount++;
				Thread.sleep(1000);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void sendValues(Map<String, String> values) throws ClientProtocolException, IOException {
		// Build Request
		HttpPost httpPost = buildRequest(values);		
		
		// Execute request
		try {
		    executePost(httpPost);
		} finally {
		    httpPost.releaseConnection();
		}
	}
	
	
	private HttpPost buildRequest(Map<String, String> values) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost("http://localhost:8080/SMTR/torque/update/" + assetID);
		//Build params
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		for (String k : values.keySet()) {
			nvps.add(new BasicNameValuePair(k, values.get(k)));
		}
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		return httpPost;
	}
	
	private void executePost(HttpPost httpPost) throws ClientProtocolException, IOException {
		HttpResponse response = httpclient.execute(httpPost);

//		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		// do something useful with the response body
		// and ensure it is fully consumed
		EntityUtils.consume(entity);
	}
	
	private Map<String, String> buildParams(int idx) {
		return this.valuesToSend.get(idx);		
	}
	
	public List<Map<String,String>> readScvFile() throws IOException, ParseException {				
		CsvToBean<Track> csvToBean = new CsvToBean<Track>();

		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put(" Device Time", "time");
		columnMapping.put(" Longitude", "kff1005");
		columnMapping.put(" Latitude", "kff1006");		
		columnMapping.put("Acceleration Sensor(Total)(g)", "kff1223");
		columnMapping.put("Speed (GPS)(km/h)", "kff1001");
		columnMapping.put("GPS Accuracy(m)", "kff1239");
		columnMapping.put("GPS Satellites", "kff123a");

		HeaderColumnNameTranslateMappingStrategy<Track> strategy = new HeaderColumnNameTranslateMappingStrategy<Track>();
		strategy.setType(Track.class);
		strategy.setColumnMapping(columnMapping);

		List<Track> list = null;
		CSVReader reader = new CSVReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("trackLog.csv")), ',');
		list = csvToBean.parse(strategy, reader);
		List<Map<String, String>> valueList = new ArrayList<Map<String,String>>();
		int index = 0;
		for (Track track : list) {
			Map<String, String> values = getValuesMap(track);
			valueList.add(index, values);
			index++;
		}
		return valueList;
	}


	private Map<String, String> getValuesMap(Track track) throws ParseException {
		ObjectMapper m = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,String> props = m.convertValue(track, Map.class);
		
		List<String> keyToRemove = new ArrayList<String>();
		
		for (String k : props.keySet()) {
			if (props.get(k).trim().equals("-")) {
				keyToRemove.add(k);
			}
		}
		
		for (String key : keyToRemove) {
			props.remove(key);
		}
		return props;
	}	
}
