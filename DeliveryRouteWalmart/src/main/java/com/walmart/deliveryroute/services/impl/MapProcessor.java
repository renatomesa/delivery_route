package com.walmart.deliveryroute.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.walmart.deliveryroute.exception.FileNotCreatedException;
import com.walmart.deliveryroute.model.MapInterpretationContainer;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.utils.MapFileUtils;

/**
 * Service class responsible for serialize map input and also start its parsing and database storing by lower layers
 * @author Renato
 *
 */
public class MapProcessor {

	@Autowired
	private IMapManagerService mapManager;
	
	ExecutorService service = Executors.newSingleThreadExecutor();
	
	public MapProcessor() {
	}
	
	public void processMap(String name, String data) throws FileNotCreatedException {
		File f = MapFileUtils.saveMapStream(name, data);
		service.submit(new SingleMapProcessorThread(f));
	}
	
	/**
	 * This private class is responsible for implementing a call inside a {@link Thread} of the method
	 * {@link MapManagerServiceImpl#performMapInterpretation(File)}, in a way that input maps can be processed
	 * in a parallel way
	 * @author renatomesa@gmail.com (Renato Vicari Mesa)
	 *
	 */
	private class SingleMapProcessorThread implements Runnable {

		File file;
		SingleMapProcessorThread(File file) {
			this.file= file;
		}
		
		@Override
		public void run() {
			try {
				MapInterpretationContainer container = mapManager.performMapInterpretation(file);
				mapManager.insertMapPoints(container.getMapPoints());
				
				int counter = 0;
				
				for (List<Route> routes : container.getRouteList()) {
					mapManager.insertRoutes(container.getMapPoints(), routes);
					counter += routes.size();
				}
				
				System.out.println(counter + " routes added.");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
