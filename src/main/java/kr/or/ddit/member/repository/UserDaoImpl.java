package kr.or.ddit.member.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageSearchVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<UserVo> selectAllUser() {
		
		return template.selectList("users.selectAllUser");
	}

	@Override
	public UserVo selectUser(String userid) {
		
		return template.selectOne("users.selectUser", userid);
	}
	@Override
	public UserVo loginUser(UserVo userVo) {
		
		return template.selectOne("users.selectUser", userVo);
	}
	
	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return template.selectList("users.selectPagingUser", vo);
	}
	
	@Override
	public int selectAllUserCnt() {
		return template.selectOne("users.selectAllUserCnt");
	}
	
	@Override
	public int registUser(UserVo userVo) {
		return template.insert("users.registerUser", userVo);
	}
	
	@Override
	public int deleteUser(String userid) {
		return template.delete("users.deleteUser", userid);
	}
	
	@Override
	public int modifyUser(UserVo userVo) {
		return template.update("users.modifyUser", userVo);
	}

	@Override
	public List<UserVo> searchIdPagingUser(PageSearchVo psVo) {
		return template.selectList("users.searchIdPagingUser", psVo);
	}

	@Override
	public int searchIdUserCount(PageSearchVo psVo) {
		return template.selectOne("users.searchIdUserCount", psVo);
	}

	@Override
	public List<UserVo> searchNamePagingUser(PageSearchVo psVo) {
		return template.selectList("users.searchNamePagingUser", psVo);
	}

	@Override
	public int searchNameUserCount(PageSearchVo psVo) {
		return template.selectOne("users.searchNameUserCount", psVo);
	}

	@Override
	public List<UserVo> searchAliasPagingUser(PageSearchVo psVo) {
		return template.selectList("users.searchAliasPagingUser", psVo);
	}

	@Override
	public int searchAliasUserCount(PageSearchVo psVo) {
		return template.selectOne("users.searchAliasUserCount", psVo);
	}


}
