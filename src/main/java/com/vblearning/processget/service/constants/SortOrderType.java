package com.vblearning.processget.service.constants;

public enum SortOrderType {
	// EnumVariableName("description", noOfLegs)
	ByAsc("Ascending", 1), ByDesc("Descending", 2);

	// Final variables
	private final String DESC;
	private final int NumberType;

	// constructor
	SortOrderType(String description, int numberType) {
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
