package cn.btttech.service.comm;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.entity.Department;
import cn.btttech.entity.Menu;
import cn.btttech.entity.Operation;
import cn.btttech.entity.Privilege;
import cn.btttech.entity.Project;
import cn.btttech.entity.Role;
import cn.btttech.entity.User;
import cn.btttech.service.base.AbstractService;
import cn.btttech.util.PrivilegeUtil;
@Service("operationService")
@Transactional(readOnly = true)
public class OperationService extends AbstractService<Operation> {

	public Set<Operation> list(User user, Menu menu) {

		Set<Operation> operationMenu = menu.getOperations();
		
		Set<Department> departments = user.getDepartments();
		Set<Role> roles = user.getRoles();
		Set<Project> projects = user.getProjects();
		
		Set<Privilege> privileges = PrivilegeUtil.combinePrivilege(departments, projects, roles);
		
		Set<Operation> operationsUser = new HashSet<Operation>(0);
		for (Privilege privilege : privileges) {
			operationsUser.add(privilege.getOperation());
		}
		
		Set<Operation> operationCom = new HashSet<Operation>(0);
		for (Operation operation : operationMenu) {
			int pre = operationsUser.size();
			operationsUser.add(operation);
			if(pre != operationsUser.size()){
				operationCom.add(operation);
			}
		}
		
		return operationCom;
	}

}
