package com.example.qsys.yousi.common.widget.dialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.activity.BaseActivity;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.ToastUtils;
/**
 * @author hanshaokai
 * @date 2017/10/31 15:14
 */
public class AppStyleDialog extends Dialog {
    public int gender;
    private static int dialogStyle = R.style.AlertDialogStyle;
    private Context context;
    public AppStyleDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * 选择日志和读后感
     *
     * @param context
     * @param style
     * @param title
     * @param contentStr
     * @param confirmStr
     * @param cancleStr
     */
    public AppStyleDialog(Context context, int style, String title, String contentStr, String confirmStr, String cancleStr) {
        super(context, style == -1 ? dialogStyle : style);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_is_quit_edit, null, false);
        setContentView(view);
        TextView dialog_title = (TextView) view.findViewById(R.id.dialog_title);
        dialog_title.setText(title);
        TextView dialog_content = (TextView) view.findViewById(R.id.dialog_content);
        dialog_content.setText(contentStr);
        TextView dialog_quit = (TextView) view.findViewById(R.id.dialog_quit);
        dialog_quit.setText(confirmStr);
        TextView dialog_continue_edit = (TextView) view.findViewById(R.id.dialog_continue_edit);
        dialog_continue_edit.setText(cancleStr);
        dialog_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConfirm();
            }
        });
        dialog_continue_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCancle();
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        this.getWindow().setLayout(getWidth() / 5 * 4, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 编辑名字 性别
     *
     * @param context
     * @param style
     * @param type_edit
     */
    public AppStyleDialog(final BaseActivity context, int style, int type_edit) {
        super(context, style == -1 ? dialogStyle : style);
        this.context = context;
        switch (type_edit) {
            //编辑昵称
            case Constant.EDITE_NICK:
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_nick_name_edit, null, false);
                setContentView(view);
                TextView tv_edit_nick_cancel = view.findViewById(R.id.tv_edit_nick_cancel);
                tv_edit_nick_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doCancle();
                    }
                });
                final EditText et_nick_name_edit = view.findViewById(R.id.et_nick_name_edit);
                TextView tv_edit_nick_positive = view.findViewById(R.id.tv_edit_nick_positive);

                tv_edit_nick_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String trim = et_nick_name_edit.getText().toString().trim();
                        if (trim.equals("")) {
                            ToastUtils.showShort(context.getString(R.string.not_null));
                        } else {
                            doGetdata(trim);
                        }
                    }
                });
                Window window = getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = Gravity.CENTER;
                window.setAttributes(params);
                this.getWindow().setLayout(getWidth() / 5 * 4, RelativeLayout.LayoutParams.WRAP_CONTENT);
                break;

            case Constant.EDITE_SEX:
                //编辑性别
                View view2 = LayoutInflater.from(context).inflate(R.layout.dialog_mine_gender_edit, null, false);
                setContentView(view2);
                RadioButton rbt_man = view2.findViewById(R.id.rbt_man);
                RadioButton rbt_female = view2.findViewById(R.id.rbt_female);
                TextView tv_positive = view2.findViewById(R.id.tv_positive);
                tv_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doGetGender(gender);
                        doCancle();
                    }
                });
                RadioGroup rgp_gender = view2.findViewById(R.id.rgp_gender);
                rgp_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.rbt_female:
                                gender = Constant.FEAMALE;
                                break;
                            case R.id.rbt_man:
                                gender = Constant.MAN;
                                break;
                            default:
                        }

                    }
                });

                Window window2 = getWindow();
                WindowManager.LayoutParams params2 = window2.getAttributes();
                params2.gravity = Gravity.CENTER;
                window2.setAttributes(params2);
                this.getWindow().setLayout(getWidth() / 5 * 4, RelativeLayout.LayoutParams.WRAP_CONTENT);
                break;
            default:

            case Constant.EDITE_BIO:
                View view3 = LayoutInflater.from(context).inflate(R.layout.dialog_mine_bio_edit, null, false);
                final EditText et_bio_detail = view3.findViewById(R.id.et_bio_detail);
                TextView tv_positive_bio = view3.findViewById(R.id.tv_positive);
                tv_positive_bio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bio = et_bio_detail.getText().toString();
                        doGetBioDetail(bio);
                        dismiss();
                    }
                });
                setContentView(view3);
                Window window3 = getWindow();
                WindowManager.LayoutParams params3 = window3.getAttributes();
                params3.gravity = Gravity.CENTER;
                window3.setAttributes(params3);
                this.getWindow().setLayout(getWidth() / 5 * 4, RelativeLayout.LayoutParams.WRAP_CONTENT);
                break;

            case Constant.EDITE_BLOG_URL:
                View view4 = LayoutInflater.from(context).inflate(R.layout.dialog_mine_blog_edit, null, false);
                final EditText et_blog_detail = view4.findViewById(R.id.et_blog_detail);
                TextView tv_positive_blog = view4.findViewById(R.id.tv_positive);
                tv_positive_blog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String blog = et_blog_detail.getText().toString();
                        doGetBlogDetail(blog);
                        dismiss();
                    }
                });
                setContentView(view4);
                Window window4 = getWindow();
                WindowManager.LayoutParams params4 = window4.getAttributes();
                params4.gravity = Gravity.CENTER;
                window4.setAttributes(params4);
                this.getWindow().setLayout(getWidth() / 5 * 4, RelativeLayout.LayoutParams.WRAP_CONTENT);
                break;

        }
    }

    /**
     * 注册账户
     *
     * @param baseFragmentActivity
     * @param style
     * @param account
     */
    public AppStyleDialog(BaseActivity baseFragmentActivity, int style, String account) {
        super(baseFragmentActivity, style == -1 ? dialogStyle : style);
        this.context = baseFragmentActivity;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_register, null, false);
        setContentView(view);
        TextView viewById = view.findViewById(R.id.et_account);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCancle();
            }
        });
        TextView agree = view.findViewById(R.id.agree);
        viewById.setText(account);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConfirm();
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        this.getWindow().setLayout(getWidth() / 5 * 4, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    public void doGetdata(String nick) {

        dismiss();
    }

    public void doGetGender(int gender) {

    }

    public void doGetBioDetail(String bio) {

    }
    public void doGetBlogDetail(String blog) {

    }

    public int getWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        /*  densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）*/
        int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）
        //px 不起作用 返回的必须是dip
        //int screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：720px）
        return screenWidthDip;
    }


    public int getHidth() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        /*  densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）*/
        int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）
        //int screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：720px）
        return screenHeightDip;
    }

    public void doCancle() {
        dismiss();
    }

    public void doConfirm() {
        dismiss();
    }
}
