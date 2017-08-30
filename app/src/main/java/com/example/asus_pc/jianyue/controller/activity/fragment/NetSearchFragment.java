package com.example.asus_pc.jianyue.controller.activity.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.SearchResultAdapter;
import com.example.asus_pc.jianyue.controller.activity.MusicActivity;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.HomeActivity;
import com.example.asus_pc.jianyue.engine.GetDownloadInfo;
import com.example.asus_pc.jianyue.engine.SearchMusic;
import com.example.asus_pc.jianyue.engine.SongsRecommendation;
import com.example.asus_pc.jianyue.pojo.SearchResult;
import com.example.asus_pc.jianyue.utils.Constants;
import com.example.asus_pc.jianyue.utils.MobileUtils;
import com.example.asus_pc.jianyue.utils.MusicUtils;

import java.io.File;
import java.util.ArrayList;


/**
 * 2015年8月15日 16:34:37
 * 博文地址：http://blog.csdn.net/u010156024
 */
public class NetSearchFragment extends Fragment
					implements OnClickListener {
	protected static final String TAG = NetSearchFragment.class.getSimpleName();

	private static MusicActivity mActivity;

	private LinearLayout mSearchShowLinearLayout;
	private LinearLayout mSearchLinearLayout;
	private ImageButton mSearchButton;
	private EditText mSearchEditText;
	private ListView mSearchResultListView;
	private ProgressBar mSearchProgressBar;
	private TextView mFooterView;
	private View mPopView;

	private PopupWindow mPopupWindow;

	private SearchResultAdapter mSearchResultAdapter;
	private ArrayList<SearchResult> mResultData = new ArrayList<>();

	private int mPage = 0;
	private int mLastItem;
	private boolean hasMoreData = true;
	/**
	 * 该类是android系统中的下载工具类，非常好用
	 */
	private DownloadManager mDownloadManager;

	private boolean isFirstShown = true;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (MusicActivity) activity;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.search_music_layout, null);

		setupViews(layout);

		mDownloadManager = (DownloadManager) mActivity
				.getSystemService(Context.DOWNLOAD_SERVICE);
		return layout;
	}

	/**
	 * 该方法实现的功能是： 当该Fragment不可见时，isVisibleToUser=false
	 * 当该Fragment可见时，isVisibleToUser=true
	 * 该方法由系统调用，重写该方法实现用户可见当前Fragment时再进行数据的加载
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		// 当Fragment可见且是第一次加载时
		if (isVisibleToUser && isFirstShown) {
			mSearchProgressBar.setVisibility(View.VISIBLE);
			mSearchResultListView.setVisibility(View.GONE);
			SongsRecommendation
				.getInstance()
				.setListener(
					new SongsRecommendation.OnRecommendationListener() {
						@Override
						public void onRecommend(
							ArrayList<SearchResult> results) {
							if (results == null || results.isEmpty())
								return;
							mSearchProgressBar.setVisibility(View.GONE);
							mSearchResultListView
									.setVisibility(View.VISIBLE);
							mResultData.clear();
							mResultData.addAll(results);
							mSearchResultAdapter.notifyDataSetChanged();
						}
					}).get();
			isFirstShown = false;
		}
	}

	private void setupViews(View layout) {
		mSearchShowLinearLayout = (LinearLayout) layout
				.findViewById(R.id.ll_search_btn_container);
		mSearchLinearLayout = (LinearLayout) layout
				.findViewById(R.id.ll_search_container);
		mSearchButton = (ImageButton) layout.findViewById(R.id.ib_search_btn);
		mSearchEditText = (EditText) layout
				.findViewById(R.id.et_search_content);
		mSearchResultListView = (ListView) layout
				.findViewById(R.id.lv_search_result);
		mSearchProgressBar = (ProgressBar) layout
				.findViewById(R.id.pb_search_wait);
		mFooterView = buildFooterView();

		mSearchShowLinearLayout.setOnClickListener(this);
		mSearchButton.setOnClickListener(this);

		mSearchResultListView.addFooterView(mFooterView);

		mSearchResultAdapter = new SearchResultAdapter(mResultData);
		mSearchResultListView.setAdapter(mSearchResultAdapter);
		mSearchResultListView.setOnScrollListener(mListViewScrollListener);
		mSearchResultListView.setOnItemClickListener(mResultItemClickListener);
	}

	private TextView buildFooterView() {
		TextView footerView = new TextView(mActivity);
		footerView.setText("加载下一页...");
		footerView.setGravity(Gravity.CENTER);
		footerView.setVisibility(View.GONE);
		return footerView;
	}
	/**
	 * 列表中每一列的点击时间监听器
	 */
	private OnItemClickListener mResultItemClickListener
							= new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (position >= mResultData.size() || position < 0)
				return;
			Toast.makeText(mActivity, "只能欣赏本地音乐哦!在线已停止服务！", Toast.LENGTH_SHORT).show();
		}
	};
	/**
	 * 底部对话框
	 * @param position
	 */
	private void showDownloadDialog(final int position) {
		mActivity.onPopupWindowShown();

		if (mPopupWindow == null) {

			mPopupWindow = new PopupWindow(mPopView, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			mPopupWindow.setBackgroundDrawable(new ColorDrawable(
					Color.TRANSPARENT));
			mPopupWindow.setAnimationStyle(R.style.popwin_anim);
			mPopupWindow.setFocusable(true);
			mPopupWindow.setOnDismissListener(new OnDismissListener() {
				@Override
				public void onDismiss() {
					mActivity.onPopupWindowDismiss();
				}
			});
		}

		mPopView.findViewById(R.id.tv_pop_cancel).setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					dismissDialog();
				}
			});
		/**
		 * 设置对话框展示的位置
		 */
		mPopupWindow.showAtLocation(mActivity.getWindow().getDecorView(),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	private void dismissDialog() {
		if (mPopupWindow != null && mPopupWindow.isShowing()) {
			mPopupWindow.dismiss();
		}
	}

	private GetDownloadInfo.OnDownloadGettedListener mDownloadUrlListener =
			new GetDownloadInfo.OnDownloadGettedListener() {
		@Override
		public void onMusic(int position, String url) {
			if (position == -1 || url == null) {
				Toast.makeText(mActivity, "歌曲链接失效",
						Toast.LENGTH_SHORT).show();
				return;
			}

			String musicName = mResultData.get(position).getMusicName();
		;
		}

		@Override
		public void onLrc(int position, String url) {
			if (url == null)
				return;

			String musicName = mResultData.get(position).getMusicName();
			Request request = new Request(
					Uri.parse(Constants.MUSIC_URL + url));
			request.setVisibleInDownloadsUi(false);
			request.setNotificationVisibility(Request.VISIBILITY_HIDDEN);
			// request.setShowRunningNotification(false);
			request.setDestinationUri(Uri.fromFile(new File(MusicUtils
					.getLrcDir() + musicName + ".lrc")));
			mDownloadManager.enqueue(request);
		}
	};
	public static boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == event.KEYCODE_BACK) {
			Toast.makeText(mActivity, "aa", Toast.LENGTH_SHORT).show();
		}
		return true;
	}
	private OnScrollListener mListViewScrollListener =
			new OnScrollListener() {
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (mLastItem == mSearchResultAdapter.getCount() && hasMoreData
					&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
				String searchText = mSearchEditText.getText().toString().trim();
				if (TextUtils.isEmpty(searchText))
					return;

				mFooterView.setVisibility(View.VISIBLE);
				startSearch(searchText);
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// 计算可见列表的最后一条的列表是不是最后一个
			mLastItem = firstVisibleItem + visibleItemCount;
		}
	};

	private void search() {
		MobileUtils.hideInputMethod(mSearchEditText);
		String content = mSearchEditText.getText().toString().trim();
		if (TextUtils.isEmpty(content)) {
			Toast.makeText(mActivity, "请输入关键词", Toast.LENGTH_SHORT).show();
			return;
		}
		mPage = 0;
		mSearchProgressBar.setVisibility(View.VISIBLE);
		mSearchResultListView.setVisibility(View.GONE);
		startSearch(content);
	}

	private void startSearch(String content) {
		SearchMusic.getInstance()
			.setListener(new SearchMusic.OnSearchResultListener() {
				@Override
				public void onSearchResult(ArrayList<SearchResult> results) {
					if (mPage == 1) {
						hasMoreData = true;
						mSearchProgressBar.setVisibility(View.GONE);
						mSearchResultListView.setVisibility(View.VISIBLE);
					}
					mFooterView.setVisibility(View.GONE);
					if (results == null || results.isEmpty()) {
						hasMoreData = false;
						return;
					}
					if (mPage == 1)
						mResultData.clear();
					mResultData.addAll(results);
					mSearchResultAdapter.notifyDataSetChanged();
				}
			}).search(content, ++mPage);
	}

	@Override
	public void onResume() {
		super.onResume();
      getFocus();
	}
	private void getFocus() {
		getView().setFocusable(true);
		getView().setFocusableInTouchMode(true);
		getView().requestFocus();
		getView().setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
					// 监听到返回按钮点击事件
					Log.e("TAG", "点击了返回键");
					// 创建退出对话框
					AlertDialog isExit = new AlertDialog.Builder(getActivity()).create();
					// 设置对话框标题
					isExit.setTitle("系统提示");
					// 设置对话框消息
					isExit.setMessage("确定要退出吗");
					// 添加选择按钮并注册监听
					isExit.setButton("确定",listener);

					isExit.setButton2("取消", listener);
					// 显示对话框
					isExit.show();

					return true;// 未处理
				}
				return false;
			}
		});
	}
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
	{
		public void onClick(DialogInterface dialog, int which)
		{
			switch (which)
			{
				case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    getActivity().finish();
                    System.exit(0);
					Intent intent=new Intent();
					intent.setAction("nihao");

					intent.putExtra("key","好啊，你厉害");
					getActivity().sendBroadcast(intent);


					break;
				case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
					break;
				default:
					break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_search_btn_container:
			mActivity.hideIndicator();
			mSearchShowLinearLayout.setVisibility(View.GONE);
			mSearchLinearLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.ib_search_btn:
			mActivity.showIndicator();
			mSearchShowLinearLayout.setVisibility(View.VISIBLE);
			mSearchLinearLayout.setVisibility(View.GONE);
			search();
			break;
		}
	}

}