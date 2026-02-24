package utilities;

import java.util.List;
import model.AssetStatus;
import model.AssetType;
import model.Role;
import model.dao.AssetStatusDAO;
import model.dao.AssetTypeDAO;
import model.dao.RoleDAO;

public class Validate {
    public static boolean validateUsername(String username) {
        return (username != null && username.matches("^[a-zA-Z0-9_.]{3,30}$"));
    }
    
    public static boolean validatePassword(String password) {
        return (password != null && password.matches("^[a-zA-Z0-9_.]{3,12}$"));
    }
    
    public static boolean validateRoleId(String role_id) {
//        return (role_id.equals("1") || role_id.equals("2") || role_id.equals("3"));
        
        List<Role> roles = new RoleDAO().getAllRoles();
        for(Role i : roles) {
            if(role_id.equals(Integer.toString(i.role_id))) return true;
        }
        return false;
    }
    
    public static boolean validateAssetName(String assetName) {
        if (assetName == null) {
            return false;
        }

        String trimmed = assetName.trim();

        if (trimmed.isEmpty()) {
            return false;
        }

        return trimmed.length() <= 255;
    }
    
    public static boolean validateTypeId(String typeId) {
        List<AssetType> list = new AssetTypeDAO().getAllAssetTypes();
        for(AssetType i : list) {
            if(typeId.equals(Integer.toString(i.typeId))) return true;
        }
        return false;
    }
    
    public static boolean validateStatusId(String statusId) {
        List<AssetStatus> list = new AssetStatusDAO().getAllAssetStatuses();
        for(AssetStatus i : list) {
            if(statusId.equals(Integer.toString(i.statusId))) return true;
        }
        return false;
    }
    
    public static boolean validateClassroomName(String classroomName) {
        if (classroomName == null) {
            return false;
        }

        String trimmed = classroomName.trim();

        if (trimmed.isEmpty()) {
            return false;
        }

        return trimmed.length() <= 255;
    }
    
    public static boolean validateLocation(String location) {
        if (location == null) {
            return false;
        }

        String trimmed = location.trim();

        if (trimmed.isEmpty()) {
            return false;
        }

        return trimmed.length() <= 255;
    }
}
