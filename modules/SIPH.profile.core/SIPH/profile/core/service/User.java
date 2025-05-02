package SIPH.profile.core;
import java.util.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.logging.Logger;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.profile.User;
import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class User extends User{

    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		  = create(vmjExchange);
		Repository.saveObject();
		return getAll(vmjExchange);
	}

    public  create(Map<String, Object> requestBody){
		String userIdStr = (String) requestBody.get("userId");
		int userId = Integer.parseInt(userIdStr);
		String name = (String) requestBody.get("name");
		String email = (String) requestBody.get("email");
		String phoneNum = (String) requestBody.get("phoneNum");
		
		//to do: fix association attributes
		  = Factory.create(
			"SIPH.profile.core.User",
		userId
		, name
		, email
		, phoneNum
		);
		Repository.saveObject();
		return ;
	}

    public  create(Map<String, Object> requestBody, int id){
		String userIdStr = (String) vmjExchange.getRequestBodyForm("userId");
		int userId = Integer.parseInt(userIdStr);
		String name = (String) vmjExchange.getRequestBodyForm("name");
		String email = (String) vmjExchange.getRequestBodyForm("email");
		String phoneNum = (String) vmjExchange.getRequestBodyForm("phoneNum");
		
		//to do: fix association attributes
		
		  = Factory.create("SIPH.profile.core.User", userId, name, email, phoneNum);
		return ;
	}

    public HashMap<String, Object> update(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("userId");
		int id = Integer.parseInt(idStr);
		  = Repository.getObject(id);
		
		String userIdStr = (String) requestBody.get("userId");
		.setUserId(Integer.parseInt(userIdStr));
		.setName((String) requestBody.get("name"));
		.setEmail((String) requestBody.get("email"));
		.setPhoneNum((String) requestBody.get("phoneNum"));
		
		Repository.updateObject();
		
		//to do: fix association attributes
		
		return .toHashMap();
		
	}

    public HashMap<String, Object> get(Map<String, Object> requestBody){
		List<HashMap<String, Object>> List = getAll("_impl");
		for (HashMap<String, Object>  : List){
			int record_id = ((Double) .get("record_id")).intValue();
			if (record_id == id){
				return ;
			}
		}
		return null;
	}

	public HashMap<String, Object> getById(int id){
		String idStr = vmjExchange.getGETParam("userId"); 
		int id = Integer.parseInt(idStr);
		  = Repository.getObject(id);
		return .toHashMap();
	}

    public List<HashMap<String,Object>> getAll(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<> List = Repository.getAllObject(table);
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> delete(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("id"));
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAll(requestBody);
	}

}
