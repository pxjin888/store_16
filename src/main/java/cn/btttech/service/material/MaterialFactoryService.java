package cn.btttech.service.material;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.service.base.AbstractService;


@Service("materialFactoryService")
@Transactional(readOnly = true)
public class MaterialFactoryService extends AbstractService<MaterialFactory> {


}