package com.denver.airport.baggagemgmt.util;

/**
 * 
 * Constant file, to hold application and exception constant
 *
 */
public final class BaggageManagementConstant {

	public static final String ARRIVAL = "ARRIVAL";
	public static final String BAGGAGE_CLAIM = "BaggageClaim";
	
	/**
	 * Exception message constant
	 */
	public static final String NO_FOUND_SOURCE_DESTINATION = "Not found source and destination detail in conveyour system graph";
	public static final String SOURCE_OR_DESTINATION_NOT_PRESENT = "Provided Source or Destination are not present in conveyour system graph";
	
	private BaggageManagementConstant()
	{
		
	}
}
