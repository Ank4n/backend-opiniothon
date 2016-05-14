package com.backend.opiniothon;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DeliveryRequestEntity {
	public static final String STATUS_WAITING = "Waiting";
	public static final String STATUS_PICKED_UP = "Picked up";
	public static final String STATUS_READY_ON_WAY = "On the way";
	public static final String STATUS_DELIVERED = "Delivered";
	public static final String STATUS_CANCELLED = "Cancelled";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private double startLat;

	private double startLon;

	private double destinationLat;

	private double destinationLon;

	private ArrayList<LocationPointsEntity> recommendedPathPoints;

	private ArrayList<LocationPointsEntity> driverFootPrints;

	@OneToOne
	private UserEntity driverDetails;

	@OneToOne
	private UserEntity customerDetails;

	private String status = STATUS_WAITING;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getStartLat() {
		return startLat;
	}

	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}

	public double getStartLon() {
		return startLon;
	}

	public void setStartLon(double startLon) {
		this.startLon = startLon;
	}

	public double getDestinationLat() {
		return destinationLat;
	}

	public void setDestinationLat(double destinationLat) {
		this.destinationLat = destinationLat;
	}

	public double getDestinationLon() {
		return destinationLon;
	}

	public void setDestinationLon(double destinationLon) {
		this.destinationLon = destinationLon;
	}

	public ArrayList<LocationPointsEntity> getRecommendedPathPoints() {
		return recommendedPathPoints;
	}

	public void setRecommendedPathPoints(ArrayList<LocationPointsEntity> recommendedPathPoints) {
		this.recommendedPathPoints = recommendedPathPoints;
	}

	public ArrayList<LocationPointsEntity> getDriverFootPrints() {
		return driverFootPrints;
	}

	public void setDriverFootPrints(ArrayList<LocationPointsEntity> driverFootPrints) {
		this.driverFootPrints = driverFootPrints;
	}

	public UserEntity getDriverDetails() {
		return driverDetails;
	}

	public void setDriverDetails(UserEntity driverDetails) {
		this.driverDetails = driverDetails;
	}

	public UserEntity getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(UserEntity customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
