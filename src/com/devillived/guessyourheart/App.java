package com.devillived.guessyourheart;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Application;
import android.util.Log;

import com.devillived.guessyourheart.utils.Util;

public class App extends Application {
	public static final String TAG="arith-";
	private static App sInstance;

	public static App get() {
		return sInstance;
	}

	@Override
	public void onCreate() {
		sInstance = this;
		Thread.setDefaultUncaughtExceptionHandler(mExceptionHandler);
		super.onCreate();
	}

	private static UncaughtExceptionHandler mExceptionHandler = new UncaughtExceptionHandler() {
		private static final String TAG_EXPT = "exception";

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			Log.e(TAG_EXPT,
					"thread:" + thread.getName() + " exception:"
							+ Util.formatException(ex));
//			Thread.getDefaultUncaughtExceptionHandler().uncaughtException(
//					thread, ex);

		}
	};

}
