package kr.or.ddit.member.repository;

import java.util.List;

import kr.or.ddit.common.model.PageSearchVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;

public interface UserDao {

	// ��ü ����� ���� ��ȸ
	List<UserVo> selectAllUser();

	// userid�� �ش��ϴ� ����� �Ѹ��� ���� ��ȸ
	UserVo selectUser(String userid);
	UserVo loginUser(UserVo userVo);

	// ����¡ó��
	List<UserVo> selectPagingUser(PageVo vo);

	// ����� ��ü �� ��ȸ
	int selectAllUserCnt();

	// ����� ���� ����
	int modifyUser(UserVo userVo);

	// ����� �ű� ���
	int registUser(UserVo userVo);

	// ����� ����
	int deleteUser(String userid);

	// ���̵�˻� ����¡ 
	List<UserVo> searchIdPagingUser(PageSearchVo psVo);

	// ���̵�˻� �� ��ȸ
	int searchIdUserCount(PageSearchVo psVo);

	// �̸��˻� ����¡
	List<UserVo> searchNamePagingUser(PageSearchVo psVo);

	// �̸��˻� �� ��ȸ
	int searchNameUserCount(PageSearchVo psVo);

	// ����˻�  ����¡
	List<UserVo> searchAliasPagingUser(PageSearchVo psVo);

	// ����˻� �� ��ȸ
	int searchAliasUserCount(PageSearchVo psVo);

	// ���� ������ ��� 
//	int serchCount(PageSearchVo vo);
//	List<UserVo> searchPageMember(PageSearchVo vo);

}
