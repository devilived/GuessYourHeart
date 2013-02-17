package com.devillived.guessyourheart.arithmancy;

import org.json.JSONException;
import org.json.JSONObject;

public class ArithResponse {
	private static final int NO_EXIST = -1;
	public static final int NoError = 0;
	public static final int UnknownError = 1;
	public static final int InvaildAnswer = 2;
	public static final int InvaildName = 3;
	public static final int InvaildKeyword = 4;
	public static final int InvaildQuestion = 5;
	public static final int InvaildQA = 6;

	private int questionNum = NO_EXIST;
	private int errNo = NO_EXIST;
	private String question;
	private String robotState;
	private boolean getResult;

	public ArithResponse(String json) throws JSONException, CodeException {
		JSONObject obj = new JSONObject(json);
		errNo = obj.optInt("errNo", NO_EXIST);
		if (errNo != NoError) {
			throw new CodeException(errNo);
		}
		question = obj.optString("question");
		if (question.matches("^\\d+:")) {
			question = question.substring(question.indexOf(':') + 1);
		}

		questionNum = obj.optInt("questionNum", NO_EXIST);
		robotState = obj.optString("normalRobot");
		getResult = obj.optBoolean("GetResult", false);
	}

	public Integer getQuestionNum() {
		if (questionNum == NO_EXIST) {
			return null;
		}
		return questionNum;
	}

	public String getQuestion() {
		return question;
	}

	public String getRobotState() {
		return robotState;
	}

	public boolean isGetResult() {
		return getResult;
	}

}
