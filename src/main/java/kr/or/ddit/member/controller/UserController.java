package kr.or.ddit.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageSearchVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource(name = "userService")
	private UserService userService;

	// 전체 사용자 조회- main
	@RequestMapping("allUser")
	public String main(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		return "member/allUser";
	}

	// 전체 사용자 페이징
	@RequestMapping(path = "userList", method = RequestMethod.GET)
	public String view(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {

		PageVo pageVo = new PageVo(page, pageSize);

		model.addAllAttributes(userService.selectPagingUser(pageVo));

		return "member/userList";
	}

	// 사용자 상세조회
	@RequestMapping(path = "userDetail", method = RequestMethod.GET)
	public String user(String userid, Model model) {
		model.addAttribute("user", userService.selectUser(userid));
		return "member/userDetail";
	}

	// 프로필사진
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("profile");

		UserVo userVo = userService.selectUser(userid);
		logger.debug("userVo: {} " ,userVo);
		String path = "";
		String filename = "";
		if (userVo.getRealfilename() == null) {
			filename = "unknown.png";
			// webapp에 넣기
			path = req.getServletContext().getRealPath("/profile/unknown.png");
		} else {
			path = userVo.getRealfilename();
			filename = userVo.getFilename();
		}

		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);

		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성

		logger.debug("path : {} ", path);

		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();

			byte[] buff = new byte[512];

			while (fis.read(buff) != -1) {
				sos.write(buff);
			}

			fis.close();
			sos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(path = "registUser", method = RequestMethod.GET)
	public String registUser() {
		return "member/userRegist";
	}

	@RequestMapping(path = "searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			PageSearchVo psVo, Model model) {
		model.addAttribute("searchType", psVo.getSearchType());
		model.addAttribute("keyword", psVo.getKeyword());
		Map<String, Object> map = null;
		if (psVo.getSearchType() == null || psVo.getKeyword() == null) {
			return "redirect:/user/userList?page=" + page + "&pageSize=" + pageSize;
		} else {
			if (psVo.getSearchType().equals("i")) {
				psVo = new PageSearchVo(page, pageSize, psVo.getKeyword());
				map = userService.searchIdPagingUser(psVo);
			} else if (psVo.getSearchType().equals("n")) {
				psVo = new PageSearchVo(page, pageSize, psVo.getKeyword());
				map = userService.searchNamePagingUser(psVo);
			} else {
				psVo = new PageSearchVo(page, pageSize, psVo.getKeyword());
				map = userService.searchAliasPagingUser(psVo);
			}

			int userCnt = (int) map.get("userCnt");
			logger.debug("member:{}", userCnt);
			// 페이징 계산
			int pagination = (int) Math.ceil((double) userCnt / psVo.getPageSize()); // 숫자 4라는 값을 만들어 낼 수 있다
			map.put("pagination", pagination);
			map.put("pageVo", psVo);
			model.addAllAttributes(map);

			return "member/userSearch";
		}
	}

	@RequestMapping(path = "idsearch", method = RequestMethod.GET)
	public String idsearch(String userid, Model model) {
		UserVo user = userService.selectUser(userid);
		if (user == null) {
			model.addAttribute("notice", "사용 가능 아이디입니다.");
		} else if (userid.equals(user.getUserid())) {
			model.addAttribute("notice", "중복된 아이디입니다");
		} else {
			model.addAttribute("notice", "아이디를 입력해주세요.");
		}
		return "member/userRegist";
	}

	// 등록
	@RequestMapping(path = "registUser", method = RequestMethod.POST)
	public String regist(UserVo userVo, BindingResult result, MultipartFile profile, String userid, Model model,
			@RequestParam("reg_dt2")String reg_dt2) {
		Date x = userVo.getReg_dt();
		if (result.hasErrors()|| x == null) {
//		if (result.hasErrors()|| userVo.getReg_dt() == null) {
			model.addAttribute("msg", "입력값을 확인해주세요");
			return "member/userRegist";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = null;
		try {
			parseDate = dateFormat.parse(reg_dt2);
		}catch (ParseException e) {
			parseDate = new Date();
			e.printStackTrace();
		}
		userVo.setReg_dt(parseDate);

		int insertCnt = 0;
		String originalFilename = "";
		String filename = "";
		logger.debug("profile.getSize()", profile.getSize());
		if (profile.getSize() > 0) {
			originalFilename = profile.getOriginalFilename();
			filename = UUID.randomUUID().toString() + "."
					+ originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			try {
				logger.debug("리얼파일{}", filename);
				logger.debug("파일{}", originalFilename);

				profile.transferTo(new File("d:\\upload\\" + filename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		userVo.setFilename(originalFilename);
		if (filename.equals("")) {
			userVo.setRealfilename(filename);
		} else {
			userVo.setRealfilename("d:\\upload\\" + filename);
		}
		insertCnt = userService.registUser(userVo);

		// 사용자 등록이 정상적으로 된 경우 ==> 사용자 페이징 리스트로 이동(1페이지)
		if (insertCnt == 1) {
			return "redirect:/user/userDetail?userid=" + userid;
		}
		// 사용자 수정이 비정상적으로 된 경우 ==> 사용자 등록 페이지로 이동(사용자가 입력한 값 설정)
		else {
			return "user/userRegist";
		}
	}

	@RequestMapping(path = "deleteUser", method = { RequestMethod.POST })
	public String delete(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
						 Model model, String userid) {
		int deleteCnt = 0;

		try {
			model.addAttribute("user", userService.deleteUser(userid));
		} catch (Exception e) {
			deleteCnt = -1;
		}
		if (deleteCnt == 1) {

			PageVo pageVo = new PageVo(page, pageSize);
			model.addAllAttributes(userService.selectPagingUser(pageVo));
//			model.addAttribute("msg", "삭제되었습니다.");
			return "redirect:/user/userList?page=1&pageSize=5";
		} else {
			return "redirect:/user/userList";
		}
	}

	@RequestMapping(path = "userModify", method = { RequestMethod.GET })
	public String modify(Model model, String userid) {

		model.addAttribute("user", userService.selectUser(userid));

		return "member/userModify"; // jsp 주소
	}

	@RequestMapping(path="userModify", method= {RequestMethod.POST})
	public String modify(UserVo userVo, MultipartFile profile, RedirectAttributes ra, Model model,
			@RequestParam("reg_dt")String reg_dt2) {
		
		logger.debug("수정페이지");
		String originalFilename = ""; //realfilename
		String filename = "";	//filename
		int updateCnt = 0;
		if(profile.getSize() > 0) {
			 originalFilename = profile.getOriginalFilename();
			 
			 filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			try {
				//파일 저장
				profile.transferTo(new File("d:\\upload\\" + filename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = null;
		try {
			parseDate = dateFormat.parse(reg_dt2);
		}catch (ParseException e) {
			parseDate = new Date();
			e.printStackTrace();
		}
		userVo.setReg_dt(parseDate);
		
		userVo.setFilename(originalFilename);
		if(filename.equals("") || filename==null) {
		userVo.setRealfilename(filename);
		//파일이 없을때 오류가 나기때문에 없을 때는 null값 들어가게함
		}
		else {
		userVo.setRealfilename("d:\\upload\\" + filename);
		//파일이 불러오기 위해 경로와 파일명으로 값을 젖아
		}
		
		//객체생성 후 수정
		updateCnt = userService.modifyUser(userVo);
		
		//사용자 수정이 정상적으로 된 경우	==> 해당 사용자의 상세조회 페이지로 이동
		if(updateCnt == 1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/userDetail";
		}
		//사용자 수정이 비정상적으로 된 경우 ==> 해당 사용자의 정보 수정 페이지로 이동
		else {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/userModify";
		}
	}
	
}