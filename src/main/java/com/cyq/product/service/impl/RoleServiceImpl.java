package com.cyq.product.service.impl;

import com.cyq.product.mapper.RoleMapper;
import com.cyq.product.mapper.UserMapper;
import com.cyq.product.model.RoleVo;
import com.cyq.product.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if (roleVo.getId() == 1 || roleVo.getId() == 2) {
            returnMsg.append("根用户或项目管理员角色不允许停用");
            return 0;
        }
        roleMapper.updateRole(roleVo);
        return 1;
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
        Set<Integer> set = new HashSet<>();
        set.add(id);
        roleMapper.deleteRoleForUser(set);
    }
}
