package dispositivomovil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DispositivoTest {
	
	@Before
	public void setUp() throws Exception {
	}
	
	
	@Test
	public void test() throws ClientProtocolException, IOException, ParseException {
		// Para un un asset
		Dispositivo dispositivo = new Dispositivo("EZF305");
		dispositivo.runDevice(-1);
	}
	
	@Ignore
	@Test	
	public void test5Assets() throws IOException, ParseException, InterruptedException {
		List<Thread> dispositivos = new ArrayList<Thread>();
				
		for (int i = 0; i < 10; i++) {				
			DeviceExecutor deviceExecutor = new DeviceExecutor("XXX"+i);
			Thread t = new Thread(deviceExecutor);
			dispositivos.add(t);
			t.start();
		}
		
		DeviceExecutor deviceExecutor = new DeviceExecutor("EZF305");
		Thread t = new Thread(deviceExecutor);
		dispositivos.add(t);
		t.start();
		
		for (Thread thread : dispositivos) {
			thread.join();
		}
	}
	
	@Ignore
	@Test
	public void readScvFileTest() throws IOException, ParseException {
		Dispositivo dispositivo = new Dispositivo("EZF305");
		dispositivo.readScvFile();
	}
	
	@Ignore
	@Test
	public void dateConvertTest() throws ParseException {
		String time = "16-abr-2013 20:22:09.510";
		SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyy HH:mm:ss.SSS"); 
	    Date convertedDate = dateFormat.parse(time); 
	    System.out.println(dateFormat.format(convertedDate));
	}

}
