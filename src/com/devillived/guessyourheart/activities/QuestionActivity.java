package com.devillived.guessyourheart.activities;

import static com.devillived.guessyourheart.Consts.EXTRAS_TYPE;
import static com.devillived.guessyourheart.Consts.EXTRAS_UID;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devil.arithmancy.inners.Const.Choice;
import com.devil.arithmancy.inners.Const.Type;
import com.devillived.guessyourheart.App;
import com.devillived.guessyourheart.R;
import com.devillived.guessyourheart.arithmancy.ArithClientProxy;
import com.devillived.guessyourheart.arithmancy.ArithResponse;
import com.devillived.guessyourheart.async.MfAsyncTask.MfAsyncCallback;
import com.devillived.guessyourheart.utils.Util;

public class QuestionActivity extends Activity implements OnClickListener {
	private static final String TAG = App.TAG
			+ QuestionActivity.class.getSimpleName();
	private ArithClientProxy mClient;
	private TextView mMessageView;

	private int mQuestionNum = -1;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle args = getIntent().getExtras();
		String uid = args.getString(EXTRAS_UID);
		if (uid == null) {
			uid = com.devil.arithmancy.utils.Util.randNumber(7) + "";
			;
		}
		String typeStr = args.getString(EXTRAS_TYPE);
		Type type = Type.valueOf(typeStr);

		mClient = new ArithClientProxy(uid);
		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.setContentView(R.layout.acitivity_question);
		initView();
		startGame(type);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onBackPressed() {
		goBack();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_settings:
			Toast.makeText(this, "setting", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_exit:
			Toast.makeText(this, "exit", Toast.LENGTH_LONG).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// /////////////////////////////////////////
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_yes:

			break;
		case R.id.btn_no:
			break;
		case R.id.btn_unknown:
			break;
		}

	}

	// //////////////////////////////////////////
	private void initView() {
		Spannable s = new SpannableString("your soul is mine");
		int start = 0;
		int end = s.length();
		int flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
		s.setSpan(new ForegroundColorSpan(R.color.theme_color), start, end,
				flags);
		s.setSpan(new StyleSpan(Typeface.BOLD), start, end, flags);

		this.setTitle(s);

		Typeface tf = Util.getTypeFace();
		ViewGroup vg = (ViewGroup) findViewById(R.id.vg_btns);
		int cnt = vg.getChildCount();
		for (int i = 0; i < cnt; i++) {
			View child = vg.getChildAt(i);
			if (child instanceof Button) {
				((Button) child).setTypeface(tf);
			}
		}
	}

	private void startGame(Type type) {
		mClient.startGame(type, mUpdateMsgCbk);
	}

	private void answerQuestion(Choice choice) {
		mClient.answerQuestion(choice, mUpdateMsgCbk);
	}

	private void goBack() {
		if (mQuestionNum < 2) {
			super.onBackPressed();
			return;
		}
		mClient.goToPreQuestion(mUpdateMsgCbk);
	}

	private MfAsyncCallback<ArithResponse> mUpdateMsgCbk = new MfAsyncCallback<ArithResponse>() {

		public boolean onPreExecute() {
			QuestionActivity.this.setProgressBarIndeterminate(true);
			return true;
		};

		@Override
		public void onSucess(ArithResponse resp) {
			QuestionActivity.this.setProgressBarIndeterminate(false);
			mQuestionNum = resp.getQuestionNum();
			mMessageView.setText(resp.getQuestionNum() + ":"
					+ resp.getQuestion());
		}

		public void onFail(Throwable e) {
			QuestionActivity.this.setProgressBarIndeterminate(false);
			Log.e(TAG, Util.formatException(e));
			mMessageView.setText(R.string.error_unknown);
		}
	};
}
