package module.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import module.dao.RoleDAO;
import module.entity.Role;
import module.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO rdao;

	@Override
	public List<Role> findAll() {
		return rdao.findAll();
	}
}
