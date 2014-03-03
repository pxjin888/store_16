package cn.btttech.util;

import java.util.HashSet;
import java.util.Set;

import cn.btttech.entity.Department;
import cn.btttech.entity.Privilege;
import cn.btttech.entity.Project;
import cn.btttech.entity.Role;


public class PrivilegeUtil {

	public static Set<Privilege> combinePrivilege(Set<Department> departments, Set<Project> projects, Set<Role> roles){
		
		Set<Privilege> privileges = new HashSet<Privilege>(0);
		for (Department department : departments) {
			privileges.addAll(department.getPrivileges());
			for (Project project : projects) {
				privileges.addAll(project.getPrivileges());
			}
			for (Role role : roles) {
				privileges.addAll(role.getPrivileges());
			}
		}
		
		return privileges;
	}
}
