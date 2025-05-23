package SIPH.profile.points;

import java.util.*;

import SIPH.profile.core.Profile;
import SIPH.profile.core.ProfileComponent;
import SIPH.profile.core.ProfileResourceDecorator;
import SIPH.profile.core.ProfileResourceComponent;
import SIPH.profile.points.ProfileImpl;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.hibernate.integrator.RepositoryUtil;

public class ProfileResourceImpl extends ProfileResourceDecorator {

    private RepositoryUtil repository = new RepositoryUtil(ProfileImpl.class);

    public ProfileResourceImpl(ProfileResourceComponent record) {
        super(record);
    }

    @Route(url = "call/points/save")
    public List<HashMap<String, Object>> save(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        Profile profile = create(vmjExchange);
        repository.saveObject(profile);
        return getAll(vmjExchange);
    }

    public Profile create(VMJExchange vmjExchange) {
        String pointStr = (String) vmjExchange.getRequestBodyForm("point");
        int point = Integer.parseInt(pointStr);
        String milestoneLevel = (String) vmjExchange.getRequestBodyForm("milestoneLevel");

        HashMap<String, Object> resultMap = record.createProfile(vmjExchange); 
        UUID id = UUID.fromString((String) resultMap.get("id"));             

        ProfileComponent base = (ProfileComponent) repository.getObject(id);

        ProfileImpl deco = new ProfileImpl(base, point, milestoneLevel);
        return deco;
    }

    public Profile create(VMJExchange vmjExchange, int id) {
        String pointStr = (String) vmjExchange.getRequestBodyForm("point");
        int point = Integer.parseInt(pointStr);
        String milestoneLevel = (String) vmjExchange.getRequestBodyForm("milestoneLevel");

        Profile base = (Profile) repository.getObject(id);
        ProfileImpl deco = new ProfileImpl((ProfileComponent) base, point, milestoneLevel);
        return deco;
    }

    @Route(url = "call/points/update")
    public HashMap<String, Object> update(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        int id = Integer.parseInt(idStr);

        Profile saved = (Profile) repository.getObject(id);
        Profile updated = create(vmjExchange, id);

        repository.updateObject(updated);

        Profile refreshed = (Profile) repository.getObject(id);
        return refreshed.toHashMap();
    }

    @Route(url = "call/points/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange) {
        return record.getProfile(vmjExchange);
    }

    @Route(url = "call/points/list")
    public List<HashMap<String, Object>> getAll(VMJExchange vmjExchange) {
        List<Profile> list = repository.getAllObject("_impl");
        return transformListToHashMap(list);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Profile> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Profile p : list) {
            resultList.add(p.toHashMap());
        }
        return resultList;
    }

    @Route(url = "call/points/delete")
    public List<HashMap<String, Object>> deleteProfile(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        int id = Integer.parseInt(idStr);
        repository.deleteObject(id);
        return getAll(vmjExchange);
    }

    @POST
    @Path("/{id}/points/add")
    public Response addPoints(@PathParam("id") String id, @QueryParam("amount") int amount) {
        profileService.addPoints(id, amount);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/points/redeem")
    public Response redeemPoints(@PathParam("id") String id, @QueryParam("amount") int amount) {
        profileService.redeemPoints(id, amount);
        return Response.ok().build();
    }
}
