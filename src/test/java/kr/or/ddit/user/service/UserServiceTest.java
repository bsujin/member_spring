package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;
import kr.or.ddit.test.config.ModelTestConfig;

public class UserServiceTest extends ModelTestConfig {
	
	@Resource(name="userService")
	private UserService userService;
	
	
//	@Test
//	public void getUserTest() {
//		/***Given***/
//		String userid = "brown";
//
//		/***When***/
//		UserVo userVo = userService.selectUser(userid);
//
//		/***Then***/
//		assertEquals("수진", userVo.getUsernm());
//		
//	}
	@Before
	public void setup() {
		UserVo userVo = new UserVo("test","대덕인재22","test",new Date(), "개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png");
		userService.registUser(userVo);
		// 신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제 
		// "ddit_n"이라는 사용자는 무조건 삭제를 한다 
//		userService.deleteUser("ddit_n");
		
	}
	@After
	public void teadDown() {
		userService.deleteUser("test");
	}
	
//	@Test
//	public void registUser() {
//		UserVo userVo = new UserVo("aaaaa","대덕인재","test",new Date(), "개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png");
//		int cnt = userService.registUser(userVo);
//		
//		assertEquals(1, cnt);
//		
//	}
	
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "sujin";

		/***When***/
		UserVo userVo = userService.selectUser(userid);

		/***Then***/
		
		//db에서 값을 가져오기 - 성공하면 값을 가져옴 
//		assertEquals("브라운", userVo.getUsernm());
		assertEquals("수진", userVo.getUsernm());
		
	}
	
	@Test
	public void getAllUserTest() {
		List<UserVo> selectAllUser =  new ArrayList<UserVo>();
				selectAllUser= userService.selectAllUser();
		assertEquals(17, selectAllUser.size());
	}
	
//	@Test
//	public void getPagingTest() {
//		PageVo vo = new PageVo(1, 5);
//		List<UserVo> selectPaging = userService.selectPagingUser(vo);
//		assertEquals(19, selectPaging.size());
//	}
//	
//	// 사용자 페이징 조회 테스트 
	@Test
	public void selectPaging() {
		
		/***Given***/
		PageVo pageVo = new PageVo(2, 5);
		
		/***When***/
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		List<UserVo> pageList = (List<UserVo>)map.get("userList");
//		int userCnt = (int)map.get("userCnt");

		/***Then***/
		assertEquals(5, pageList.size());
//		assertEquals(20, userCnt);
		
	}
	
	
	@Test
	public void modifyUserTest() {
		
		UserVo userVo = new UserVo("test","대덕인재22","test",new Date(), "개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png");
		int modifyCnt = userService.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, modifyCnt);
		
	}
	
	// 삭제 테스트
		@Test
		public void deleteUserTest() {
			/***Given***/
			// 해당 테스트가 실행 될 때는 testUser 라는 사용자가 before 메소드에 의해 등록이 된 상태 
			String userid = "test";
			
			/***When***/
			int delteCnt = userService.deleteUser(userid);
			
			/***Then***/
			assertEquals(1, delteCnt);
		}
	
}
