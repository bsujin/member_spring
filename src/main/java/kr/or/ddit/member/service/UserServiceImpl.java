package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageSearchVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.repository.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserServiceImpl() {
		
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}
	@Override
	public UserVo loginUser(UserVo userVo) {
		return userDao.loginUser(userVo);
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		   Map<String, Object> map = new HashMap<String, Object>();
		   List<UserVo> userList = userDao.selectPagingUser(pageVo);
		   
		   
		   int userCnt = userDao.selectAllUserCnt();
		   map.put("pageVo", pageVo);
		   map.put("userList", userList);
		   map.put("pagination", (int)Math.ceil( (double)userCnt / pageVo.getPageSize()));
		   
		   return map;
	}
	
	@Override
	public int registUser(UserVo userVo) {
		return userDao.registUser(userVo);
	}
	
	@Override
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}
	
	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}

	@Override
	public Map<String, Object> searchIdPagingUser(PageSearchVo psVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		   List<UserVo> userList = userDao.searchIdPagingUser(psVo);
		   int userCnt = userDao.searchIdUserCount(psVo);
		   
		   map.put("pageVo", psVo);
		   map.put("userList", userList);
		   map.put("userCnt", userCnt);
		   map.put("pagination", (int)Math.ceil( (double)userCnt / psVo.getPageSize()));
		   
		   return map;
	}

	@Override
	public Map<String, Object> searchNamePagingUser(PageSearchVo psVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		   List<UserVo> userList = userDao.searchNamePagingUser(psVo);
		   int userCnt = userDao.searchNameUserCount(psVo);
		   
		   map.put("pageVo", psVo);
		   map.put("userList", userList);
		   map.put("userCnt", userCnt);
//		   map.put("pagination", (int)Math.ceil( (double)userCnt / psVo.getPageSize()));
		   
		   return map;
	}

	@Override
	public Map<String, Object> searchAliasPagingUser(PageSearchVo psVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		   List<UserVo> userList = userDao.searchAliasPagingUser(psVo);
		   int userCnt = userDao.searchAliasUserCount(psVo);
		   
		   map.put("pageVo", psVo);
		   map.put("userList", userList);
		   map.put("userCnt", userCnt);
//		   map.put("pagination", (int)Math.ceil( (double)userCnt / psVo.getPageSize()));
		   
		   return map;
	}

	

}
