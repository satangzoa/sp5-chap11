package survey;

import java.util.List;

public class AnsweredData {

	private List<String> responses;  //resonses[0],resonses[1]등으로 요청 파라미터의 이름으로 구분 사용할 수 있다.
	private Respondent res;// res  프로퍼티는 다시 age와 location 프로퍼티를 갖는다 이를 중첩된 형식으로
                           // 표시하면 res.age 프로퍼티 res.location 프로퍼티로 표현할 수 있다.
	public List<String> getResponses() {
		return responses;
	}

	public void setResponses(List<String> responses) {
		this.responses = responses;
	}

	public Respondent getRes() {
		return res;
	}

	public void setRes(Respondent res) {
		this.res = res;
	}

}
