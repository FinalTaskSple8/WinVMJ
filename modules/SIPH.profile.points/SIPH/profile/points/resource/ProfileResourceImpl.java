package SIPH.profile.points;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.profile.core.ProfileResourceDecorator;
import SIPH.profile.core.ProfileImpl;
import SIPH.profile.core.ProfileResourceComponent;

public class ProfileResourceImpl extends ProfileResourceDecorator {
    public ProfileResourceImpl (ProfileResourceComponent record) {
        super(record);
    }

    // @Restriced(permission = "")
    @Route(url="call/points/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		ProfilePoints profilepoints = createProfilePoints(vmjExchange);
		profilepointsRepository.saveObject(profilepoints);
		return getAllProfilePoints(vmjExchange);
	}

    public Profile createProfilePoints(VMJExchange vmjExchange){
		String pointStr = (String) vmjExchange.getRequestBodyForm("point");
		int point = Integer.parseInt(pointStr);
		String milestoneLevel = (String) vmjExchange.getRequestBodyForm("milestoneLevel");
		
		ProfilePoints profilepoints = record.createProfilePoints(vmjExchange);
		ProfilePoints profilepointsdeco = ProfilePointsFactory.createProfilePoints("SIPH.points.core.ProfileImpl", profilepoints, user, user
		point, milestoneLevel
		);
			return profilepointsdeco;
	}


    public Profile createProfilePoints(VMJExchange vmjExchange, int id){
		String pointStr = (String) vmjExchange.getRequestBodyForm("point");
		int point = Integer.parseInt(pointStr);
		String milestoneLevel = (String) vmjExchange.getRequestBodyForm("milestoneLevel");
		ProfilePoints profilepoints = profilepointsRepository.getObject(id);
		int recordProfilePointsId = (((ProfilePointsDecorator) savedProfilePoints.getRecord()).getId();
		
		ProfilePoints profilepoints = record.createProfilePoints(vmjExchange);
		ProfilePoints profilepointsdeco = ProfilePointsFactory.createProfilePoints("SIPH.points.core.ProfileImpl", id, profilepoints, user, user
		point, milestoneLevel
		);
			return profilepointsdeco;
	}

	// @Restriced(permission = "")
    @Route(url="call/points/update")
    public HashMap<String, Object> updateProfilePoints(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		
		ProfilePoints profilepoints = profilepointsRepository.getObject(id);
		profilepoints = createProfilePoints(vmjExchange, id);
		
		profilepointsRepository.updateObject(profilepoints);
		profilepoints = profilepointsRepository.getObject(id);
		//to do: fix association attributes
		
		return profilepoints.toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/points/detail")
    public HashMap<String, Object> getProfilePoints(VMJExchange vmjExchange){
		return record.getProfilePoints(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/points/list")
    public List<HashMap<String,Object>> getAllProfilePoints(VMJExchange vmjExchange){
		List<ProfilePoints> profilepointsList = profilepointsRepository.getAllObject("profilepoints_impl");
		return transformProfilePointsListToHashMap(profilepointsList);
	}

    public List<HashMap<String,Object>> transformProfilePointsListToHashMap(List<ProfilePoints> ProfilePointsList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < ProfilePointsList.size(); i++) {
            resultList.add(ProfilePointsList.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/points/delete")
    public List<HashMap<String,Object>> deleteProfilePoints(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		profilepointsRepository.deleteObject(id);
		return getAllProfilePoints(vmjExchange);
	}



	
}
