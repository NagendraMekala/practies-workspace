package com.mng.spring.watcher.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class FileWatcher {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private FileWatcherThread fileMonitorThread;

	public void startMonitoring() {
		taskExecutor.execute(fileMonitorThread);
		stopThreads();	
	}
       
	public void stopMonitoring() {
		fileMonitorThread.stopMonitoring();
	}

	public void stopThreads() {
		taskExecutor.shutdown();	
	}

	public FileWatcherThread getFileMonitorThread() {
		return fileMonitorThread;
	}

	public void setFileMonitorThread(FileWatcherThread fileMonitorThread) {
		this.fileMonitorThread = fileMonitorThread;
	}
	
	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

}