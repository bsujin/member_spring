package kr.or.ddit.user.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.test.config.WebTestConfig;


public class loginTest extends WebTestConfig{
	
	@Autowired
	private WebApplicationContext context;
	
	@Test
	public void viewTest()throws Exception {

		// localhost/login/view + enter ==> GET
		mockMvc.perform(get("/login/view")).andExpect(status().isOk()).andExpect(view().name("login")).andDo(print());
	}
	
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid","con")
				.param("pass", "conPass")) 
		.andExpect(view().name("member/allUser"))
		.andDo(print());
	}
	
	@Test
	public void processFailTest() throws Exception {
		// perform - post/get방식 기입
		mockMvc.perform(post("/login/process")
				.param("userid","brown")
				.param("pass", "failPass"))	//param은 문자열
		.andExpect(view().name("redirect:/login/view"))
		.andDo(print());
	}
	

}
