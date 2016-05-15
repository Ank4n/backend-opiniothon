package com.backend.opiniothon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import requestResponseHelper.GeoLocation;
import requestResponseHelper.PostStatus;

@RestController
public class AppController {

	private UserRepo userRepo;
	private DeliveryRequestRepo deliveryRequestRepo;
	private LocationPointRepo locationPointRepo;

	@Autowired
	public AppController(UserRepo userRepo) {
		this.userRepo = userRepo;
		init();
	}

	private void init() {
		UserEntity deliveryGuy = new UserEntity("Vaibhav Jani", "Saroj Apartments", "1111", "3030",
				UserEntity.USER_TYPE_DELIVERY_AGENT);
		UserEntity consumer = new UserEntity("Ankan", "Saroj", "1111", "4040", UserEntity.USER_TYPE_CONSUMER);
		userRepo.save(deliveryGuy);
		userRepo.save(consumer);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> login(@RequestBody UserEntity user) {
		List<UserEntity> dbUsers = userRepo.findByPhoneNumber(user.getPhoneNumber());
		UserEntity dbUser = null;
		if (dbUsers != null && !dbUsers.isEmpty())
			dbUser = dbUsers.get(0);

		if (dbUser != null) {
			System.out.println("Username:" + dbUser.getPhoneNumber() + "Password:" + dbUser.getPassword());
			if (dbUser.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok(dbUser);
			}

		}

		return new ResponseEntity<UserEntity>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/delivery/findOrder", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeliveryRequestEntity> findNewOrder(
			@RequestParam(value = "driver_id", required = true) long id) {
		DeliveryRequestEntity entity = deliveryRequestRepo.findOne(id);
		if (entity == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return ResponseEntity.ok(entity);

	}

	@RequestMapping(value = "/addFootPrint", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addFootPrint(@RequestBody LocationPointsEntity locationPoint) {

		locationPointRepo.save(locationPoint);

	}

	@RequestMapping(value = "/postStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postStatus(@RequestBody PostStatus status) {

		DeliveryRequestEntity entity = deliveryRequestRepo.findOne(Long.valueOf(status.getDelivery_request_id()));
		entity.setStatus(status.getStatus());
		deliveryRequestRepo.save(entity);

	}

	@RequestMapping(value = "/postGeoLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postGeoLocation(@RequestBody GeoLocation location) {

		UserEntity user = userRepo.findOne(Long.valueOf(location.getId()));
		user.setLat(location.getLat());
		user.setLon(location.getLon());
		userRepo.save(user);

	}
	
	@RequestMapping(value = "/postOrderRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeliveryRequestEntity> postOrderRequest(@RequestBody UserEntity userEntity) {

		userRepo.save(userEntity);
		DeliveryRequestEntity request = new DeliveryRequestEntity();
		request.setCustomerDetails(userEntity);
		deliveryRequestRepo.save(request);
		return ResponseEntity.ok(request);

	}
	
	@RequestMapping(value = "/updateUserLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserLocation(@RequestBody GeoLocation location) {

		UserEntity user = userRepo.findOne(Long.valueOf(location.getId()));
		user.setLat(location.getLat());
		user.setLon(user.getLon());
		userRepo.save(user);
	}

	@RequestMapping(value = "/user/status", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOrderStatus(
			@RequestParam(value = "user_id", required = true) long id) {
		DeliveryRequestEntity entity = null;
		List<DeliveryRequestEntity> entities = deliveryRequestRepo.findByCustomerDetails(id);
		if (entities != null && !entities.isEmpty())
			entity = entities.get(0);
		if (entity == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return ResponseEntity.ok(entity.getStatus());

	}

	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser() {
		init();

	}

}
