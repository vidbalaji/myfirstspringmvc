package com.vblearning.processget.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.JProcessesResponse;
import org.jutils.jprocesses.model.ProcessInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vblearning.processget.service.comparator.MyComparator;
import com.vblearning.processget.service.constants.SortOrderType;

@Service
public abstract class ProcessService {
	@Value("${external.file}")
	String extFile;
	List<ProcessInfo> processesList;

	List<ProcessInfo> pInfoList10;
	int numberOfProcesses = 10;
	int index = 0;
	String myUserName = System.getProperty("user.name");

	public void doGenericUpdates() {

	}

	public List<ProcessInfo> fetchListForNumber(List<ProcessInfo> localList) {
		// TODO Auto-generated method stub
		int number = getNumberOfProcesses();
		int startNo = getIndex() * number;
		int endNo = getIndex() * number + number;
		int numberOfUserPid = localList.size();
		List<ProcessInfo> pLInfoList10 = new ArrayList<>();
		for (int i = startNo; i < endNo; i++) {
			if (i >= numberOfUserPid)
				break;
			pLInfoList10.add(localList.get(i));

		}
		setIndex(getIndex() + 1);
		return pLInfoList10;
	}

	public List<ProcessInfo> getDummyProcessesListFor10() {
		pInfoList10 = new ArrayList<>();
		ProcessInfo p1 = new ProcessInfo();
		ProcessInfo p2 = new ProcessInfo();
		ProcessInfo p3 = new ProcessInfo();
		p1.setPid("1");
		p3.setPid("9568");
		p2.setPid("5280");
		p1.setName("process1");
		p3.setName("Process3");
		p2.setName("Process2");
		pInfoList10.add(p3);
		pInfoList10.add(p2);
		pInfoList10.add(p1);

		return pInfoList10;
	}

	public String getExtFile() {
		return extFile;
	}

	public int getIndex() {
		return index;
	}

	public int getNumberOfProcesses() {
		return numberOfProcesses;
	}

	public List<ProcessInfo> getProcessesList() {
		// TODO Auto-generated method stub
		processesList = JProcesses.getProcessList();

		return processesList;
	}

	public List<ProcessInfo> getProcessesListFor(int number) {
		List<ProcessInfo> localList;
		setNumberOfProcesses(number);
		localList = getProcessesListWhole();

		pInfoList10 = fetchListForNumber(localList);

		System.out.println(myUserName);
		return pInfoList10;
	}

	public List<ProcessInfo> getProcessesListFor(int number, String typeSplit) {
		List<ProcessInfo> localList;

		// TODO Auto-generated method stub
		setNumberOfProcesses(number);
		String[] typeArr = typeSplit.split(":");
		System.out.println(typeSplit);
		for (String str : typeArr) {
			System.out.println(str);

		}
		String type = typeArr[0];
		String ordertype = typeArr[1];
		System.out.println(ordertype);
		System.out.println(type);
		localList = getProcessesListWhole();

		int iOrderType = Integer.parseInt(ordertype);
		int iType = Integer.parseInt(type);
		MyComparator myComparator = new MyComparator();
		Collections.sort(localList, myComparator.getRequiredCompare(iType));
		if (iOrderType == SortOrderType.ByDesc.getNumberType()) {
			Collections.reverse(localList);
		}
		getProcessesListFor(number);
		return pInfoList10;
	}

	abstract public List<ProcessInfo> getProcessesListWhole();

	public abstract List<ProcessInfo> getUserProcessList();

	public boolean killProcess(String pid) {
		// TODO Auto-generated method stub
		int intPid = Integer.parseInt(pid);
		JProcessesResponse success = JProcesses.killProcess(intPid);
		System.out.println(success.isSuccess());

		return success.isSuccess();
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setNumberOfProcesses(int numberOfProcesses) {
		this.numberOfProcesses = numberOfProcesses;
	}

}
