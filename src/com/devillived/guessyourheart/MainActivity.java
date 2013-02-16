package com.devillived.guessyourheart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.devillived.guessyourheart.dlgs.QuestionDlg;

public class MainActivity extends Activity {
	private AlertDialog mDlg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_PROGRESS);
		super.setTitle("Your soul is mine");
		setContentView(R.layout.activity_main);

		if (mDlg == null) {
			QuestionDlg.Builder bld = new QuestionDlg.Builder(this);
			mDlg = bld.create();
		}
		mDlg.setMessage("messege");
		mDlg.show();
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
}
