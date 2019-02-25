package com.mng.spring.watcher.api;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FileWatcherThread implements Runnable {

	//private Long sleepTime = 1000L * 60; 
	private Long sleepTime = 10000l;

	private String directoryToWatch = "C:\\Users\\Mekala.Nagendra\\Desktop\\properties";
	
	private WatchKey watchKey;
	
	@Autowired
	private ProcessData processData;
	
	@PostConstruct
	public void setUpDirectory() {	
		processData.setFilterDataTemps("N");
	}

	 public void run() {
	        try {
	            // get the directory we want to watch
	            Path directory = Paths.get(directoryToWatch);

	            // creates a WatchService and registers the given directory
	            FileSystem fileSystem = directory.getFileSystem();
	            InputStream inputStream =null;

	            // make a new watch service that we can register interest in
	            // directories and files with.
	            WatchService watchService = fileSystem.newWatchService();
	            watchKey = directory.register(watchService, ENTRY_MODIFY,ENTRY_CREATE,ENTRY_DELETE);

	           while(true) {
				System.out.println("Waiting for key to be signalled...");

				watchKey = watchService.take();
				List<WatchEvent<?>> watchedEvents = watchKey.pollEvents();

				// we only registered for one kind of event and this is it
				for (WatchEvent<?> watchEvent : watchedEvents) {
					String fileName = watchEvent.context().toString();
					Kind<?> kind = watchEvent.kind();
					if (kind.toString().equals("ENTRY_DELETE") || kind.toString().equals("ENTRY_CREATE")) {
						System.out.printf("Received %s event for file: %s\n", watchEvent.kind(), watchEvent.context());
						break;
					} else {
						// modification events for the file
						System.out.printf("Received %s event for file: %s\n", watchEvent.kind(), watchEvent.context());
						inputStream = new FileInputStream(directoryToWatch + "/" + fileName);
						Properties prop = new Properties();
						prop.load(inputStream);
						processData.setFilterDataTemps(prop.getProperty("filterDataTemps"));
						System.out.println("Value getting: "+prop.getProperty("filterDataTemps"));
						break;
					}

				}
				boolean valid = watchKey.reset();
                if(!valid){
                    System.out.println(" watcher reset failed");
                    break;
                }
                Thread.sleep(sleepTime);
	         }
	        } catch (Exception e) {
	            System.out.println("Wather on file failed." + directoryToWatch + " " + e);
	        }
	    }
	 
	/**
	 * stopMonitoring
	 */
	public void stopMonitoring() {
		if (watchKey != null) {
			watchKey.cancel();
		}
	}
}
