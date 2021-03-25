package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageSearchVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;

public interface UserService {

	List<UserVo> selectAllUser();

	UserVo selectUser(String userid);
	UserVo loginUser(UserVo userVo);

	Map<String, Object> selectPagingUser(PageVo vo);

	int modifyUser(UserVo userVo);

	int registUser(UserVo userVo);

	// ����� ����
	int deleteUser(String userid);

	//���̵� �˻� ����¡ ó�� �� ��ȸ
	Map<String, Object> searchIdPagingUser(PageSearchVo psVo);
	
	//�̸��˻� ����¡ ó�� �� ��ȸ
	Map<String, Object> searchNamePagingUser(PageSearchVo psVo);
	
	//����˻� ����¡ ó�� �� ��ȸ
	Map<String, Object> searchAliasPagingUser(PageSearchVo psVo);

//	int serchCount(PageSearchVo vo);
//	Map<String, Object> searchPageUser(PageSearchVo psVo);
}
