package com.vblearning.processget.service;

import java.util.ArrayList;
import java.util.List;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.JProcessesResponse;
import org.jutils.jprocesses.model.ProcessInfo;
import org.springframework.stereotype.Service;

@Service
public class SystemProcessService extends ProcessService {

	List<ProcessInfo> systemProcessList;
	String myUserName = System.getProperty("user.name");

	@Override
	public List<ProcessInfo> getProcessesListWhole() {
		List<ProcessInfo> localList;
		if (systemProcessList != null)
			localList = systemProcessList;
		else
			localList = getSystemProcessList();
		return localList;
	}

	private List<ProcessInfo> getSystemProcessList() {
		// TODO Auto-generated method stub
		List<ProcessInfo> localList = getProcessesList();
		List<ProcessInfo> systemLocalList = new ArrayList<ProcessInfo>();
		for (ProcessInfo localProcess : localList) {
			if (localProcess.getUser() != null && !(localProcess.getUser().equals(myUserName))) {
				systemLocalList.add(localProcess);
			}

		}
		systemProcessList = systemLocalList;
		return systemLocalList;
	}

	@Override
	public List<ProcessInfo> getUserProcessList() {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public boolean killProcess(String pid) {
		// TODO Auto-generated method stub
		int intPid = Integer.parseInt(pid);
		JProcessesResponse success = JProcesses.killProcess(intPid);
		System.out.println(success.isSuccess());

		return success.isSuccess();
	}

}
