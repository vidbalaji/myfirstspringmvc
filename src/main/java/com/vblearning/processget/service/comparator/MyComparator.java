package com.vblearning.processget.service.comparator;

import java.util.Comparator;

import org.jutils.jprocesses.model.ProcessInfo;

import com.vblearning.processget.service.constants.SortType;

public class MyComparator {
	// Class to compare Movies by ratings
	public class CpuCompare implements Comparator<ProcessInfo> {
		@Override
		public int compare(ProcessInfo m1, ProcessInfo m2) {
			if (Float.parseFloat(m1.getCpuUsage()) < Float.parseFloat(m2.getCpuUsage()))
				return -1;
			if (Float.parseFloat(m1.getCpuUsage()) > Float.parseFloat(m2.getCpuUsage()))
				return 1;
			else
				return 0;
		}
	}

	public class MemoryCompare implements Comparator<ProcessInfo> {
		@Override
		public int compare(ProcessInfo m1, ProcessInfo m2) {
			if (Float.parseFloat(m1.getVirtualMemory()) < Float.parseFloat(m2.getVirtualMemory()))
				return -1;
			if (Float.parseFloat(m1.getVirtualMemory()) > Float.parseFloat(m2.getVirtualMemory()))
				return 1;
			else
				return 0;
		}
	}

	// Class to compare Movies by name
	public class NameCompare implements Comparator<ProcessInfo> {
		@Override
		public int compare(ProcessInfo m1, ProcessInfo m2) {
			return m1.getName().compareTo(m2.getName());
		}
	}

	public Comparator<ProcessInfo> getRequiredCompare(int type) {
		if (type == (SortType.ByCPU.getNumberType())) {
			return new CpuCompare();
		}
		if (type == (SortType.ByName.getNumberType())) {
			return new NameCompare();
		} else if (type == (SortType.ByMem.getNumberType())) {
			return new MemoryCompare();
		}
		return null;
	}
}
