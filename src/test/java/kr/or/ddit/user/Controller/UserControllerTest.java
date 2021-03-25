package kr.or.ddit.user.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;


public class UserControllerTest extends WebTestConfig{
	
	@Test
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser"))
				.andExpect(view().name("member/allUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andDo(print());
	}
	
	@Test
	public void PagingUserTest() throws Exception {
		mockMvc.perform(get("/user/userList"))
		.andExpect(view().name("member/userList"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(model().attributeExists("pagination"))
		.andDo(print());
	}
	@Test
	public void PagingUserTest2() throws Exception {
		mockMvc.perform(get("/user/searchUser").param("searchType","i").param("pageSize", "3").param("keyword", "s"))
		.andExpect(view().name("member/userSearch"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(model().attributeExists("pagination"))
		.andDo(print());
	}
	
	@Test
	public void test() {
//		인스턴스로 만들때에는 private타입이 초기화가 된다 - 초기화 0 
		PageVo pageVo = new PageVo();
		System.out.println("pageVo.getPage() : " + pageVo.getPage());
		System.out.println("pageVo.getPage() : " + pageVo.getPageSize());
		
//		지역변수로 만들면 초기화가 되지 않는다 
//		int page;
//		System.out.println("[age :" + page);
	}
	
	

}
