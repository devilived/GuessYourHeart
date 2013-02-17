package com.devillived.guessyourheart.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.devil.arithmancy.Client;
import static com.devillived.guessyourheart.Consts.*;
import com.devillived.guessyourheart.R;
import com.devillived.guessyourheart.utils.Util;

public class QuestionActivity extends Activity {
	private Client client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle args = getIntent().getExtras();
		String uid = args.getString(EXTRAS_UID);
		if (uid == null) {
			uid = com.devil.arithmancy.utils.Util.randNumber(7) + "";
		}
		client = new Client(uid);

		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.setContentView(R.layout.acitivity_question);
		initView();
		super.onCreate(savedInstanceState);
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

	private void initView() {
		Spannable s = new SpannableString("your soul is mine");
		int start = 0;
		int end = s.length();
		int flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
		s.setSpan(new ForegroundColorSpan(R.color.theme_color), start, end,
				flags);
		s.setSpan(new StyleSpan(Typeface.BOLD), start, end, flags);

		this.setTitle(s);

		// 汉仪雪峰体简
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
}
