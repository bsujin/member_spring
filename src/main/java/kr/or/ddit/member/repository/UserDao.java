package kr.or.ddit.member.repository;

import java.util.List;

import kr.or.ddit.common.model.PageSearchVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;

public interface UserDao {

	// 전체 사용자 정보 조회
	List<UserVo> selectAllUser();

	// userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	UserVo loginUser(UserVo userVo);

	// 페이징처리
	List<UserVo> selectPagingUser(PageVo vo);

	// 사용자 전체 수 조회
	int selectAllUserCnt();

	// 사용자 정보 수정
	int modifyUser(UserVo userVo);

	// 사용자 신규 등록
	int registUser(UserVo userVo);

	// 사용자 삭제
	int deleteUser(String userid);

	// 아이디검색 페이징 
	List<UserVo> searchIdPagingUser(PageSearchVo psVo);

	// 아이디검색 수 조회
	int searchIdUserCount(PageSearchVo psVo);

	// 이름검색 페이징
	List<UserVo> searchNamePagingUser(PageSearchVo psVo);

	// 이름검색 수 조회
	int searchNameUserCount(PageSearchVo psVo);

	// 별명검색  페이징
	List<UserVo> searchAliasPagingUser(PageSearchVo psVo);

	// 별명검색 수 조회
	int searchAliasUserCount(PageSearchVo psVo);

	// 조건 쿼리문 사용 
//	int serchCount(PageSearchVo vo);
//	List<UserVo> searchPageMember(PageSearchVo vo);

}
