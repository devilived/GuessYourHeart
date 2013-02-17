package com.devillived.guessyourheart.arithmancy;

import org.json.JSONException;

import com.devil.arithmancy.Client;
import com.devil.arithmancy.inners.Const.Choice;
import com.devil.arithmancy.inners.Const.Type;
import com.devillived.guessyourheart.async.MfAsyncTask;
import com.devillived.guessyourheart.async.MfAsyncTask.MfAsyncCallback;

public class ArithClientProxy {
	private Client mClient;

	public ArithClientProxy(String uid) {
		mClient = new Client(uid);
	}

	public void startGame(final Type type, MfAsyncCallback<ArithResponse> cbk) {
		MfAsyncTask<ArithResponse> task = new MfAsyncTask<ArithResponse>(cbk) {
			@Override
			public ArithResponse runInBg() throws CodeException, JSONException {
				String json = mClient.startGame(type);
				return new ArithResponse(json);
			}
		};
		task.execute();
	}

	public void answerQuestion(final Choice choice,
			MfAsyncCallback<ArithResponse> cbk) {
		MfAsyncTask<ArithResponse> task = new MfAsyncTask<ArithResponse>(cbk) {
			@Override
			public ArithResponse runInBg() throws CodeException, JSONException {
				String json = mClient.answerQuestion(choice);
				return new ArithResponse(json);
			}
		};
		task.execute();
	}

	public void answerQuestion(final Choice choice, final int questfd,
			MfAsyncCallback<ArithResponse> cbk) {
		MfAsyncTask<ArithResponse> task = new MfAsyncTask<ArithResponse>(cbk) {
			@Override
			public ArithResponse runInBg() throws CodeException, JSONException {
				String json = mClient.answerQuestion(choice, questfd);
				return new ArithResponse(json);
			}
		};
		task.execute();
	}

	public void goToPreQuestion(MfAsyncCallback<ArithResponse> cbk) {
		MfAsyncTask<ArithResponse> task = new MfAsyncTask<ArithResponse>(cbk) {
			@Override
			public ArithResponse runInBg() throws CodeException, JSONException {
				String json = mClient.goToPreQuestion();
				return new ArithResponse(json);
			}
		};
		task.execute();
	}

}
