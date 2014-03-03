package cn.btttech.service.comm;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.entity.Department;
import cn.btttech.entity.Menu;
import cn.btttech.entity.Privilege;
import cn.btttech.entity.Project;
import cn.btttech.entity.Role;
import cn.btttech.entity.User;
import cn.btttech.service.base.AbstractService;
import cn.btttech.util.PrivilegeUtil;

@Service("menuService")
@Transactional(readOnly = true)
public class MenuService extends AbstractService<Menu> {

	
	
	public Set<Menu> list(User user) {
		Set<Department> departments = user.getDepartments();
		Set<Project> projects = user.getProjects();
		Set<Role> roles = user.getRoles();
		
		Set<Privilege> privileges = PrivilegeUtil.combinePrivilege(departments, projects, roles);
		Set<Menu> menus = new HashSet<Menu>(0);
		for (Privilege privilege : privileges) {
			menus.add(privilege.getMenu());
		}
		
		return menus;
	}
	
	

}
