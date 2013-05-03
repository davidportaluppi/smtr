package dispositivomovil;

import java.io.IOException;
import java.text.ParseException;

public class DeviceExecutor implements Runnable {
	String assetID;
	public DeviceExecutor(String assetID) {
		this.assetID = assetID;
	}
	@Override
	public void run() {
		Dispositivo dispositivo = new Dispositivo(assetID);
		try {
			dispositivo.runDevice(-1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
