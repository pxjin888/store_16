package cn.btttech.service.comm;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.entity.Department;
import cn.btttech.entity.User;
import cn.btttech.service.base.AbstractService;

@Service("userService")
@Transactional(readOnly = true)
public class UserService extends AbstractService<User> {  
    
    public User login(User user) {  
        List<User> list = getBaseDao().findAll(  
                "from User where userName = ? and userPassword = ?", User.class,  
                new Object[] { user.getUserName(), user.getUserPassword() });  
        if (list.size() == 1) {  
            return list.get(0);  
        }  
        return null;  
    }
    @Transactional(readOnly = true)
    public Set<Department> getDepartment(User user){
    	
    	return user.getDepartments();
    }
}  