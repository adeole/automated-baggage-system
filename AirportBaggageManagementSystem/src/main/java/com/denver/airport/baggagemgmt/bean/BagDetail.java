package com.denver.airport.baggagemgmt.bean;

/**
 * 
 * This class hold Bag detail
 *
 */
public class BagDetail{

	//<bag_number> <entry_point> <flight_id>
	private  String bagNumber;
	private String entryPoint;
	private String flightId;

	public BagDetail(){}
	
	public BagDetail(String bagNumber, String entryPoint, String flightId) {
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}
	/**
	 * @return the bagNumber
	 */
	public String getBagNumber() {
		return bagNumber;
	}
	/**
	 * @param bagNumber the bagNumber to set
	 */
	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}
	/**
	 * @return the entryPoint
	 */
	public String getEntryPoint() {
		return entryPoint;
	}
	/**
	 * @param entryPoint the entryPoint to set
	 */
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	/**
	 * @return the flightId
	 */
	public String getFlightId() {
		return flightId;
	}
	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	/*public int compareTo(Object o) {
		if(this == o) return 0;
		BagDetail bd = (BagDetail)o;
		return this.getEntryPoint().compareTo(bd.getEntryPoint());
	}*/
}
