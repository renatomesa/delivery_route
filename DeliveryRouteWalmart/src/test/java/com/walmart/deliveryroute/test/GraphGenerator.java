/**
 * 
 */
package com.walmart.deliveryroute.test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.junit.Test;

import scala.testing.SUnit.TestCase;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class GraphGenerator {


	private static final long NODE_NUMBER = 500;
	
	private static final long MAX_TOTAL_NODES = 10000;
	
	private static final long MAX_OUTGOING_EDGE_NUMBER = 20;
	
	private static final int MAX_DISTANCE = 100;
	
	public boolean generateGraphFile() throws IOException {
		
		File f = new File("C:\\tmp\\testGraphSample.txt");
		OutputStream outputStream = null;
		
		try {
			f.createNewFile();
			outputStream = new FileOutputStream(f);
			
			for(long i = 0; i< MAX_TOTAL_NODES; i++) {
				String origin = "N_" + ((int) (Math.random() * NODE_NUMBER) );
				String destination = "N_" + ((int) (Math.random() * NODE_NUMBER));
				int distance = (int) (Math.random() * MAX_DISTANCE);
				
				if(origin.equals(destination))
					continue;
				
				outputStream.write(new String(origin + " " + destination + " " + distance + "\n").getBytes());
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(outputStream != null) {
				outputStream.close();
			}
		}
		
		return true;

	}

	@Test
	public void runTest(){
		try {
			generateGraphFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
