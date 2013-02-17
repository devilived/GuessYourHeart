package com.devillived.guessyourheart.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import com.devillived.guessyourheart.App;

public class Util {
	private static final String TAG = "utils";

	public static int dp2px(float dipValue) {
		final float scale = App.get().getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dp(float pxValue) {
		final float scale = App.get().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static String formatException(Throwable t) {
		Writer sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		Throwable cause = t.getCause();
		while (cause != null) {
			cause.printStackTrace(pw);
			cause = cause.getCause();
		}
		String result = sw.toString();
		pw.close();
		return result;
	}

	private static Typeface sTf;

	public static Typeface getTypeFace() {
		if (sTf == null) {
			sTf = Typeface.createFromAsset(App.get().getAssets(),
					"fonts/hanyixuefengjian.ttf");
		}
		return sTf;
	}

	private static Map<String, String> sDeviceInfo;

	public static Map<String, String> getDeviceInfo(Context ctx) {
		if (sDeviceInfo == null) {
			sDeviceInfo = new HashMap<String, String>();
			try {
				PackageManager pm = ctx.getPackageManager();
				PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
						PackageManager.GET_ACTIVITIES);
				if (pi != null) {
					sDeviceInfo
							.put("version_name",
									pi.versionName == null ? "not set"
											: pi.versionName);
					sDeviceInfo.put("version_code", pi.versionCode + "");
				}
			} catch (NameNotFoundException e) {
				Log.e(TAG, "Error while collect package info", e);
			}
			Field[] fields = Build.class.getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					sDeviceInfo.put(field.getName(), "" + field.get(null));
				} catch (Exception e) {
					Log.e(TAG, "Error while collect crash info", e);
				}
			}
		}
		return sDeviceInfo;
	}

}
