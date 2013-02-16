package com.devillived.guessyourheart.dlgs;

import com.devillived.guessyourheart.utils.Util;

import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class QuestionDlg extends AlertDialog {

	protected QuestionDlg(Context context) {
		super(context);
	}

	public static class Builder extends AlertDialog.Builder {

		public Builder(Context arg0) {
			super(arg0);
		}

		@Override
		public AlertDialog create() {
			super.setTitle("YOUR SOUL IS MINE... ...");
			AlertDialog dlg = super.create();
			Window wdw = dlg.getWindow();
			WindowManager.LayoutParams params = wdw.getAttributes();
			// params.gravity=
			params.width = LayoutParams.FILL_PARENT;
			params.height=Util.dp2px(200);
			wdw.setAttributes(params);
			wdw.setGravity(Gravity.CENTER_VERTICAL);
			return dlg;
		}

	}

}
