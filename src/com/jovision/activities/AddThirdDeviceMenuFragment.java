package com.jovision.activities;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jovetech.CloudSee.temp.R;
import com.jovision.Consts;
import com.jovision.adapters.PeripheralManageAdapter;
import com.jovision.commons.MyLog;
import com.jovision.utils.ConfigUtil;

public class AddThirdDeviceMenuFragment extends Fragment implements
		AddThirdDevActivity.OnMainListener {
	private View rootView;// 缓存Fragment view
	private GridView manageGridView;
	private PeripheralManageAdapter manageAdapter;
	DisplayMetrics disMetrics;
	private WebView mWebView;
	private MyHandler myHandler;
	private String mDevType = "";
	private LinearLayout loadinglayout;
	private ImageView loadingBar;
	private boolean loadFailed = false;

	public interface OnDeviceClassSelectedListener {
		public void OnDeviceClassSelected(int index);
	}

	private String webUrlZH = "http://182.92.242.230:8081/device.html?lan=ch";
	private String webUrlEN = "http://182.92.242.230:8081/device.html?lan=en";

	private OnDeviceClassSelectedListener mListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnDeviceClassSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnDeviceClassSelectedListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.web_add_exdev_menu_layout,
					container, false);
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		myHandler = new MyHandler();
		mWebView = (WebView) rootView.findViewById(R.id.webview);

		loadingBar = (ImageView) rootView.findViewById(R.id.loadingbar);
		loadinglayout = (LinearLayout) rootView
				.findViewById(R.id.loadinglayout);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		if (ConfigUtil.getLanguage2(getActivity()) == Consts.LANGUAGE_ZH
				|| ConfigUtil.getLanguage2(getActivity()) == Consts.LANGUAGE_ZHTW) {
			mWebView.loadUrl(webUrlZH);
		} else {
			mWebView.loadUrl(webUrlEN);
		}

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Log.e("webv", "webView load failed");
				loadFailed = true;
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
				view.loadUrl(newUrl);
				Log.e("webv", "newUrl:" + newUrl);
				if (newUrl.contains("device=")) {

					String param_array[] = newUrl.split("\\?");
					HashMap<String, String> resMap;
					resMap = ConfigUtil.genMsgMapFromhpget(param_array[1]);

					mDevType = resMap.get("device");
				}
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				loadinglayout.setVisibility(View.VISIBLE);
				Animation anim = AnimationUtils.loadAnimation(getActivity(),
						R.anim.rotate);
				loadingBar.setAnimation(anim);
				Log.v("Test", "webView start load");
				// mHandler.sendEmptyMessage(1);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				loadinglayout.setVisibility(View.GONE);
				Log.e("webv", "webView finish load");
				if (loadFailed) {
					Log.e("webv", "url:" + url + " load failed");
					getActivity().finish();
				} else {
					if (url.contains("device=")) {

						String param_array[] = url.split("\\?");
						HashMap<String, String> resMap;
						resMap = ConfigUtil.genMsgMapFromhpget(param_array[1]);

						mDevType = resMap.get("device");
						if (mDevType != null && !mDevType.equals("")) {
							mListener.OnDeviceClassSelected(Integer
									.parseInt(mDevType));
						}
					}
				}

			}
		});

		// manageGridView = (GridView) rootView
		// .findViewById(R.id.third_alarm_gridview);
		// disMetrics = new DisplayMetrics();
		// getActivity().getWindowManager().getDefaultDisplay()
		// .getMetrics(disMetrics);
		// manageAdapter = new PeripheralManageAdapter(this);
		// manageAdapter.SetData(disMetrics.widthPixels);
		// manageGridView.setAdapter(manageAdapter);
		// manageAdapter.notifyDataSetChanged();

		return rootView;
	}

	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// if (rootView == null) {
	// rootView = inflater.inflate(
	// R.layout.new_add_thirddev_menu_fragment, container, false);
	// }
	// ViewGroup parent = (ViewGroup) rootView.getParent();
	// if (parent != null) {
	// parent.removeView(rootView);
	// }
	// myHandler = new MyHandler();
	// manageGridView = (GridView) rootView
	// .findViewById(R.id.third_alarm_gridview);
	// disMetrics = new DisplayMetrics();
	// getActivity().getWindowManager().getDefaultDisplay()
	// .getMetrics(disMetrics);
	// manageAdapter = new PeripheralManageAdapter(this);
	// manageAdapter.SetData(disMetrics.widthPixels);
	// manageGridView.setAdapter(manageAdapter);
	// manageAdapter.notifyDataSetChanged();
	// // doorBtn = (Button) rootView.findViewById(R.id.add_door_btn);
	// // doorBtn.setOnClickListener(this);
	// // braceletBtn = (Button) rootView.findViewById(R.id.add_bracelet_btn);
	// // braceletBtn.setOnClickListener(this);
	// // telecontrolBtn = (Button) rootView
	// // .findViewById(R.id.add_telecontrol_btn);
	// // telecontrolBtn.setOnClickListener(this);
	//
	// return rootView;
	// }

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	class MyHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			MyLog.e("PERIP", "what:" + msg.what + ", arg1:" + msg.arg1);
			switch (msg.what) {
			case Consts.WHAT_PERI_ITEM_CLICK: {
				// arg1:外设功能编号从1开始
				// 1:门磁设备 2:手环设备 3:遥控 4:烟感 5:幕帘 6:红外探测器 7:燃气泄露
				mListener.OnDeviceClassSelected(msg.arg1);
			}
				break;
			default:
				break;
			}
		}
	}

	public void MyOnNotify(int what, int arg1, int arg2, Object obj) {
		Message msg = myHandler.obtainMessage(what, arg1, arg2, obj);
		myHandler.sendMessage(msg);
	}

	@Override
	public void onMainAction(int action) {
		// TODO Auto-generated method stub
		if (action == 0) {
			if (ConfigUtil.getLanguage2(getActivity()) == Consts.LANGUAGE_ZH
					|| ConfigUtil.getLanguage2(getActivity()) == Consts.LANGUAGE_ZHTW) {
				mWebView.loadUrl(webUrlZH);
			} else {
				mWebView.loadUrl(webUrlEN);
			}
		}
	}
}
