package com.devillived.guessyourheart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.devillived.guessyourheart.activities.QuestionActivity;

public class MainActivity extends Activity {
	private AlertDialog mDlg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_PROGRESS);
		super.setTitle("Your soul is mine");
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(this, QuestionActivity.class);
		startActivity(intent);

//		if (mDlg == null) {
//			QuestionDlg.Builder bld = new QuestionDlg.Builder(this);
//			mDlg = bld.create();
//		}
//		mDlg.setMessage("messege");
//		mDlg.show();
	}

	@Override
	protected void onDestroy() {
//		mDlg.dismiss();
		super.onDestroy();
	}
}
