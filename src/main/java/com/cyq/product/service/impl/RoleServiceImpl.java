package com.cyq.product.service.impl;

import com.cyq.product.mapper.RoleMapper;
import com.cyq.product.model.RoleVo;
import com.cyq.product.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务层接口实现类
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleVo> findRoles() {
        return roleMapper.findRoles();
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.addRole(roleVo);
    }

    @Override
    public int updateRole(RoleVo roleVo, StringBuilder returnMsg) {
        int enabled = roleVo.getEnabled();
        if (enabled == 2) {
            int result = roleMapper.getUserRoleByRoleId(roleVo.getId());
            if (result > 0) {
                returnMsg.append("该角色有配置的用户,不允许设置失效");
                return 0;
            }
        }
        roleMapper.updateRole(roleVo);
        return 1;
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
    }
}
