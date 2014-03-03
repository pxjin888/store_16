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
import cn.btttech.entity.TableColumns;
import cn.btttech.entity.User;
import cn.btttech.service.base.AbstractService;

@Service("tableColumnsService")
@Transactional(readOnly = true)
public class TableColumnsService extends AbstractService<TableColumns>{

	
	@Transactional(readOnly = false)
	public Set<TableColumns> list(User user, Menu menu) {

		
		Set<TableColumns> tableColumnsesMenu = menu.getTableColumnses();
		
		Set<Department> departments = user.getDepartments();
//		Hibernate.initialize(departments);
		
		Set<Role> roles = user.getRoles();
		Set<Project> projects = user.getProjects();
		
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
		
		
		Set<TableColumns> tableColumnsesUser = new HashSet<TableColumns>(0);
		for (Privilege privilege : privileges) {
			tableColumnsesUser.add(privilege.getTableColumns());
		}
		
		
		Set<TableColumns> tableColumnsesCom = new HashSet<TableColumns>(0);
		for (TableColumns tableColumns : tableColumnsesMenu) {
			int pre = tableColumnsesUser.size();
			tableColumnsesUser.add(tableColumns);
			if(pre != tableColumnsesUser.size()){
				tableColumnsesCom.add(tableColumns);
			}
		}
		
		return tableColumnsesCom;
	}

}
