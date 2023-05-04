package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
public class SurveyController {


	
	@GetMapping
	public String form() {
		return "survey/surveyForm";
	}
	
	
	/*
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
	
	
*/
	

	//ModelAndView를 통한 뷰 선택과 모델 전달
	/*	
	@GetMapping
	public ModelAndView form() {
		List<Question> questions = createQuestions();
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions", questions);// 뷰에 전달할 데이터를 설정한다
		mav.setViewName("survey/surveyForm");// 결과를 보여줄 뷰 이름을 설정한다
		return mav;
	}
	
*/
	
/*	
	@GetMapping
	public String form(Model model) {
		List<Question> questions = createQuestions();
		model.addAttribute("questions", questions);
		return "survey/surveyForm";
	}
*/
	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 무엇입니까?",
				Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
				Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}

	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}

}
