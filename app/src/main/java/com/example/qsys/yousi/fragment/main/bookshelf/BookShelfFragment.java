package com.example.qsys.yousi.fragment.main.bookshelf;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.adapter.bookshelf.BookShelfAdapter;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.FileUtils;
import com.example.qsys.yousi.common.util.OperationUtils;
import com.example.qsys.yousi.common.util.SizeUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.recyclerview.OnRecyclerItemClickListener;
import com.example.qsys.yousi.common.widget.recyclerview.SpacesItemDecoration;
import com.example.qsys.yousi.fragment.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

/**
 * @author hanshaokai
 * @date 2017/10/17 15:19
 */
public class BookShelfFragment extends BaseFragment implements BookShelfView {
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.img_btn_action_include)
    ImageButton imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.rlv_book_shelf_main)
    XRecyclerView rlvBookShelfMain;
    private BookShelfPresenterExtend mPresenter = null;
    private List<BookResponse.ResultsBean> mListBook = new ArrayList<>();
    public BookShelfAdapter bookShelfAdapter;
    public String textBookPath = Constant.BOOKPATH;
    public String bookname;
    public int layoutPosition;

    @Override
    public void showResponseData(BaseResponse response) {
        mListBook.clear();
        List<BookResponse.ResultsBean> results = ((BookResponse) response).getResults();
        mListBook.addAll(results);
        bookShelfAdapter.notifyDataSetChanged();
        rlvBookShelfMain.refreshComplete();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override
    public void showEmptyViewByCode(int code,String msg) {

        switch (code) {
            case Constant.SERVER_ERROR:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg,msg));
                break;
            case Constant.SERVER_UNREACH:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg2));
                break;
            case Constant.NET_UNABLE:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_net));
                break;
            case Constant.NO_CONTENT:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_content));
                break;
            default:
        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_book_shelf_main, container, false);
        return view;
    }

    private void initListener() {
        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llRoot.setVisibility(View.GONE);
                mPresenter.getBookData();
            }
        });
        rlvBookShelfMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBookData();
            }

            @Override
            public void onLoadMore() {

            }
        });

        rlvBookShelfMain.addOnItemTouchListener(new OnRecyclerItemClickListener(rlvBookShelfMain, baseFragmentActivity) {
            //XRecyclerView 肯能控件问题导致 layoutPosition 位置加一
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                layoutPosition = vh.getLayoutPosition() - 1;
                BookResponse.ResultsBean resultsBean = mListBook.get(layoutPosition);
                bookname = resultsBean.getBookname();
                checkPermission();
            }

            @Override
            public void onLongClick(final RecyclerView.ViewHolder vh) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(baseFragmentActivity);
                builder.setMessage("是否删除").setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String bookname = mListBook.get(vh.getLayoutPosition() - 1).getBookname();
                        String file = textBookPath + File.separator + bookname;
                        if (new File(file).exists()) {
                            if (FileUtils.deleteFile(file)) {
                                ToastUtils.showShort("成功删除" + bookname);
                            } else {
                                ToastUtils.showShort(bookname + "删除失败");
                            }
                        } else {
                            ToastUtils.showShort(bookname + "不存在");
                        }

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }

        });
    }

    private void toOpenTextBook() {
        //创建文件夹
        if (!FileUtils.createOrExistsDir(textBookPath)) {
            ToastUtils.showShort(textBookPath + "路径未创建成功");
            return;
        }
        String file = textBookPath + File.separator + bookname;
        if (FileUtils.isFileExists(file)) {
            //存在此文件 打开
            ToastUtils.showShort("打开" + bookname);
        } else {
            //从服务器中下载
            //mPresenter.toLoadBook(bookname);
            mPresenter.toLoadCouldContinueBook(bookname, layoutPosition);

        }

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //检查是否授权了读写权限
            int write = checkSelfPermission(baseFragmentActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read = checkSelfPermission(baseFragmentActivity, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (write != PackageManager.PERMISSION_GRANTED || read != PackageManager.PERMISSION_GRANTED) {
                //没有授权就去申请
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 300);
            } else {

                toOpenTextBook();
            }
        } else {
            //低于6.0的版本 如果拒绝了权限 怎么判断是否有权限
            Log.i("wytings", "------------- Build.VERSION.SDK_INT < 23 ------------");
            toOpenTextBook();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //申请权限结果
        // 每个请求 requestCode 对应一个 code 结果
        if (requestCode == 300) {
            Log.i("wytings", "--------------requestCode == 300->" + requestCode + "," + permissions.length + "," + grantResults.length);
            toOpenTextBook();

        } else {
            Log.i("wytings", "--------------requestCode != 300->" + requestCode + "," + permissions + "," + grantResults);
        }
    }
    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new BookShelfPresenterExtend();
        mPresenter.setPresenterView(this);
        initListener();
        initToolBar(toolbarInclude, false, getResources().getString(R.string.book_stock), -1, false);
        initAdapter();
        imgBtnActionInclude.setVisibility(View.GONE);
        //防止图书页面也显示图标
        setHasOptionsMenu(false);
        mPresenter.getBookData();
    }

    private void initAdapter() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rlvBookShelfMain.setLayoutManager(layoutManager);
        rlvBookShelfMain.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(5)));
        bookShelfAdapter = new BookShelfAdapter(baseFragmentActivity, mListBook);
        rlvBookShelfMain.setAdapter(bookShelfAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListBook.clear();
        //解除持有的弱引用
        mPresenter.detacheView();
    }



    @Override
    public void showProgressView(Boolean b) {
        if (b) {
            showProgressDialog(getResources().getString(R.string.loading));
        } else {
            dismissProgressDialog();
        }
    }
    @Override
    public Boolean isActive() {
        return isAdded();
    }

    public static Fragment newInstance() {
        BookShelfFragment bookShelfFragment=new BookShelfFragment();
        return bookShelfFragment;
    }

    @Override
    public void showLoadePercent(long readLength, long countLength, int positon) {
        double div = OperationUtils.div(readLength, countLength, 3);
        mListBook.get(positon).setPercent(div);
        mListBook.get(positon).setVIsiblePercent(div != 1.0);
        bookShelfAdapter.notifyItemChanged(positon + 1);
        showMessage(bookname + "下载成功");

    }
}
