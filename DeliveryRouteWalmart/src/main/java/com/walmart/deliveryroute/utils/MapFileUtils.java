package com.walmart.deliveryroute.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.walmart.deliveryroute.Constants;
import com.walmart.deliveryroute.exception.FileNotCreatedException;

public class MapFileUtils {

	public static File saveMapStream(String mapName, String stream) throws FileNotCreatedException {
		File dir = new File(Constants.MAP_STORAGE_DIR);
		try {
			System.out.println(dir.getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean fileCreationResult = false;
		
		if (!dir.exists()) {
			fileCreationResult = dir.mkdir();
			if(!fileCreationResult) {
				throw new FileNotCreatedException();
			}
		}

		long timestamp = System.currentTimeMillis();
		File map = new File(dir.getPath() + "/" + mapName + "_"+timestamp + ".txt");
		FileOutputStream outputStream = null;
		try {			
			fileCreationResult = map.createNewFile();
			outputStream = new FileOutputStream(map);
			outputStream.write((mapName + "\n").getBytes());
			outputStream.write(stream.getBytes());			
		} catch (FileNotFoundException e) {
			throw new FileNotCreatedException();
		} catch(IOException e) {
			throw new FileNotCreatedException(e);
		} finally {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
				
		return map;
	}
	
}
