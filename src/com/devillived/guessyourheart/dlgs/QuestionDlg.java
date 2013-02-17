package com.devillived.guessyourheart.dlgs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;

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
//			WindowManager.LayoutParams lp = wdw.getAttributes();
//			 params.gravity=
//			lp.width = LayoutParams.FILL_PARENT;
//			lp.height=Util.dp2px(200);
//			lp.alpha=0.6f;
//			wdw.setAttributes(lp);
			wdw.setGravity(Gravity.CENTER_VERTICAL);
			dlg.setIcon(null);
			return dlg;
		}
		
	}

}
