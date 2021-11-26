package com.vblearning.processget.service;

import java.util.ArrayList;
import java.util.List;

import org.jutils.jprocesses.model.ProcessInfo;
import org.springframework.stereotype.Service;

@Service
public class UserProcessService extends ProcessService {

	List<ProcessInfo> userProcessList;
	String myUserName = System.getProperty("user.name");

	@Override
	public List<ProcessInfo> getProcessesListWhole() {
		List<ProcessInfo> localList;
		if (userProcessList != null)
			localList = userProcessList;
		else
			localList = getUserProcessList();
		return localList;
	}

	@Override
	public List<ProcessInfo> getUserProcessList() {
		// TODO Auto-generated method stub
		List<ProcessInfo> localList = getProcessesList();
		List<ProcessInfo> userLocalList = new ArrayList<ProcessInfo>();
		for (ProcessInfo localProcess : localList) {
			if (localProcess.getUser() != null && localProcess.getUser().equals(myUserName)) {
				userLocalList.add(localProcess);
			}

		}
		userProcessList = userLocalList;
		return userLocalList;
	}

}
