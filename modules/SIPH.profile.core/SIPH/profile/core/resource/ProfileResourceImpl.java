package SIPH.profile.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.profile.ProfileFactory;
import vmj.auth.annotations.Restricted;
//add other required packages

public class ProfileResourceImpl extends ProfileResourceComponent{
	
	private ProfileServiceImpl profileServiceImpl = new ProfileServiceImpl();

	@Restricted(permissionName  = "")
    @Route(url="call/profile")
    public HashMap<String,Object> createProfile(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
			String email = vmjExchange.getAuthPayload().getEmail();
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Profile result = profileServiceImpl.createProfile(requestBody, email);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}
	
	@Restricted(permissionName = "")
	@Route(url = "call/profile/by-email")
	public HashMap<String, Object> getProfileByEmail(VMJExchange vmjExchange) {
		if (!vmjExchange.getHttpMethod().equals("POST")) {
			throw new NotFoundException("Invalid method");
		}

		String email = vmjExchange.getAuthPayload().getEmail();
		HashMap<String, Object> profile = profileServiceImpl.getProfileByEmail(email);

		if (profile == null) {
			HashMap<String, Object> error = new HashMap<>();
			error.put("message", "Profile not found");
			error.put("vmjErrorCode", 4006);
			return error;
		}

		return profile;
	}
	
	@Restricted(permissionName = "")
	@Route(url = "call/profile/update-info")
	public HashMap<String, Object> updateProfileNameAndPhoneNumber(VMJExchange vmjExchange) {
		if (!vmjExchange.getHttpMethod().equals("POST")) {
			throw new NotFoundException("Invalid method");
		}

		String email = vmjExchange.getAuthPayload().getEmail();
		Map<String, Object> requestBody = vmjExchange.getPayload();

		String newName = (String) requestBody.get("name");
		String newPhoneNumber = (String) requestBody.get("phone_number");

		if (newName == null || newPhoneNumber == null) {
			throw new BadRequestException("Name and phone number must be provided");
		}

		HashMap<String, Object> updatedProfile = profileServiceImpl.updateNameAndPhoneNumber(email, newName, newPhoneNumber);
		return updatedProfile;
	}
   
    // @Restriced(permission = "")
    @Route(url="call/profile/update")
    public HashMap<String, Object> updateProfile(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return profileServiceImpl.updateProfile(requestBody);
		
	}

	// @Restriced(permission = "")
    @Route(url="call/profile/detail")
    public HashMap<String, Object> getProfile(VMJExchange vmjExchange){
		System.out.println("============================");
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		System.out.println("************************************");
		String idStr = (String) requestBody.get("id");
		UUID id = UUID.fromString(idStr);

//		Profile profile = profileServiceImpl.getProfileById(id);
//		if (profile == null) {
//			HashMap<String, Object> error = new HashMap<>();
//			error.put("message", "Profile not found");
//			error.put("vmjErrorCode", 4006);
//			return error;
//		}

		return null;
	}

	// @Restriced(permission = "")
    @Route(url="call/profile/list")
    public List<HashMap<String,Object>> getAllProfile(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return profileServiceImpl.getAllProfile();
	}

    
	// @Restriced(permission = "")
    @Route(url="call/profile/delete")
    public List<HashMap<String,Object>> deleteProfile(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return profileServiceImpl.deleteProfile(requestBody);
	}

	public String showProfile(int userId) {
		// TODO: implement this method
		return "";
	}

	public boolean editProfile(int userId, String name, String email, String phoneNum) {
		// TODO: implement this method
		return false;
	}
}
