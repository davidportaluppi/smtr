package dispositivomovil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Track {
	// "Device Time"
	private String time;
	// "Longitude"
	private String kff1005;
	// "Latitude"
	private String kff1006;
	// "Acceleration Sensor(Total)(g)"
	private String kff1223;
	// "Speed (GPS)(km/h)"
	private String kff1001;
	// "GPS Accuracy(m)"
	private String kff1239;
	// "GPS Satellites"
	private String kff123a;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		
		this.time = convertToMilliseconds(time);
	}

	public String getKff1005() {
		return kff1005;
	}

	public void setKff1005(String kff1005) {
		this.kff1005 = kff1005;
	}

	public String getKff1006() {
		return kff1006;
	}

	public void setKff1006(String kff1006) {
		this.kff1006 = kff1006;
	}

	public String getKff1223() {
		return kff1223;
	}

	public void setKff1223(String kff1223) {
		this.kff1223 = kff1223;
	}

	public String getKff1001() {
		return kff1001;
	}

	public void setKff1001(String kff1001) {
		this.kff1001 = kff1001;
	}

	public String getKff1239() {
		return kff1239;
	}

	public void setKff1239(String kff1239) {
		this.kff1239 = kff1239;
	}

	public String getKff123a() {
		return kff123a;
	}

	public void setKff123a(String kff123a) {
		this.kff123a = kff123a;
	}

	private String convertToMilliseconds(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyy HH:mm:ss.SSS"); 
	    Date convertedDate;
		try {
			convertedDate = dateFormat.parse(time);
			return convertedDate.getTime() + "";
		} catch (ParseException e) {
			System.out.println("Imposible parsear el time: " + time);
		}
		return time;
	}
}
