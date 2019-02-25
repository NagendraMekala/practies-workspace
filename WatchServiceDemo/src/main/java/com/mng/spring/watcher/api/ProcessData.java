package com.mng.spring.watcher.api;

import org.springframework.stereotype.Component;

@Component
public class ProcessData {
	
	private String filterDataTemps;

	public String getFilterDataTemps() {
		return filterDataTemps;
	}

	public void setFilterDataTemps(String filterDataTemps) {
		this.filterDataTemps = filterDataTemps;
	}
}
