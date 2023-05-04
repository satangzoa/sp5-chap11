package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {

	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(
			MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	
	@RequestMapping("/register/main")
	public String handleStep0() {
		return "register/main";
	}
    

	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}

	
	//Model 사용 안하고 @RequestParam 사용 시  요청 파라미터 접근 방식2
	/*
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree){
			
		if (!agree) {
			return "register/step1";
		}
		
		return "register/step2";
	}
	
	*/
	/* 요청 파라미터 접근 방식1
	@PostMapping("/register/step2")
	public String handleStep2(HttpServletRequest request) {
		String agreeParam = request.getParameter("agree");
			
			
		if (agreeParam == null || !agreeParam.equals("true")){
			return "register/step1";
		}
		
		return "register/step2";
	}
	
	*/
	//Model 대신에 직접 커맨드 객체를 파라미터로 추가하면 아래 Model을 쓰는 방식보다 간단해진다.
	
/*
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			
			RegisterRequest registerRequest) {
		if (!agree) {
			return "register/step1";
		}
		//model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
	
*/	

	
	 // Model사용 하고 @RequestParam와 커맨드 객체 사용 시
	
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			
			Model model) {
		if (!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
 
	
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";//이동경로는 웹 애플리케이션 경로 /sp5-chap11/register/step1이 된다
	}


  
	//종전  방식 HttpServletRequest 사용방식
	/*
	 @PostMapping("/register/step3")
	 public String handleStep3(HttpServletRequest request) {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		RegisterRequest regReq = new RegisterRequest();
		regReq.setEmail(email);
		regReq.setName(name);
		regReq.setPassword(password);
		regReq.setConfirmPassword(confirmPassword);
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			return "register/step2"; 
			 //현재 경로를 기준으로 상대경로를 사용한다. "http://localhost:8181/sp5-chap11/register/step2"를 기준으로
			//상대경로인  "http://localhost:8181/sp5-chap11/register/step3을 리다이렉트  경로로 사용한다.
		
		}
		
		
	}
	*/

	//커맨드 객체 사용 방식  register/step2.jsp도 커맨드 객체 전달이 가능한 <form:form> tag등을 사용하여야 한다.
	//기존의 커맨드 객체의 속성이름(registerRequest)을 바꾸는 경우(formData)는 @ModelAttribute  애노테이션 사용하면 됨.
	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq) {
	//public String handleStep3(@ModelAttribute ("formData")RegisterRequest regReq) {	 //커맨드 객체 속성 이름 변경
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			
			return "register/step2"; 
			 //현재 경로를 기준으로 상대경로를 사용한다. "http://localhost:8181/sp5-chap11/register/step2"를 기준으로
			//상대경로인  "http://localhost:8181/sp5-chap11/register/step3을 리다이렉트  경로로 사용한다.
		}
	}
	
  
}
