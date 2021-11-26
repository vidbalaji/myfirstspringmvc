package com.vblearning.processget.service.constants;

public enum SortType {
	// EnumVariableName("description", noOfLegs)
	ByName("By Name", 1), ByMem("By Memory", 2), ByCPU("By CPU", 3);

	// Final variables
	private final String DESC;
	private final int NumberType;

	// constructor
	SortType(String description, int numberType) {
		DESC = description;
		NumberType = numberType;
	}

	// method to get description
	public String getDesc() {
		return DESC;
	}

	// method to get no. of legs
	public int getNumberType() {
		return NumberType;
	}
}
