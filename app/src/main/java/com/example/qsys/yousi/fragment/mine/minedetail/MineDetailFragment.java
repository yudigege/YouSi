package com.example.qsys.yousi.fragment.mine.minedetail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.qsys.yousi.BuildConfig;
import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.FileUtils;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.dialog.AppStyleDialog;
import com.example.qsys.yousi.common.widget.updatelisenner.UpdateMIneDetailObserver;
import com.example.qsys.yousi.fragment.BaseFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static android.graphics.BitmapFactory.decodeFile;
import static android.support.v4.content.ContextCompat.checkSelfPermission;

/**
 * @author hanshaokai
 * @date 2017/10/31 18:01
 */


public class MineDetailFragment extends BaseFragment implements MineDetailView {
    public MineDetailPresenterExtend mPresnter;
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.iv_detail_name_mine)
    ImageView ivDetailNameMine;
    @BindView(R.id.avatar_arrow)
    ImageView nameArrow;
    @BindView(R.id.tv_detail_nick_mine)
    TextView tvDetailNickMine;
    @BindView(R.id.avatar_arrow2)
    ImageView nickArrow2;
    @BindView(R.id.tv_detail_sex_mine)
    TextView tvDetailSexMine;
    @BindView(R.id.avatar_arrow3)
    ImageView sexArrow3;
    @BindView(R.id.tv_bio)
    TextView tvBio;
    @BindView(R.id.tv_detail_bio_mine)
    TextView tvDetailBioMine;
    @BindView(R.id.avatar_arrow4)
    ImageView bioArrow4;
    @BindView(R.id.tv_blog)
    TextView tvBlog;
    @BindView(R.id.tv_detail_blog_mine)
    TextView tvDetailBlogMine;
    @BindView(R.id.avatar_arrow5)
    ImageView blogArrow5;
    public UserResponse.ResultsBean user;
    public AppStyleDialog appStyleDialogNick2;
    public AppStyleDialog appStyleDialogNick3;
    public AppStyleDialog appStyleDialogNick4;
    public AppStyleDialog appStyleDialogNick5;
    public String path = Environment.getExternalStorageDirectory().getPath() + "/yousipic";
    public String name = File.separator + CustomApplication.userEntity.getId() + ".jpg";


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_mine_detail, container, false);
        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresnter = new MineDetailPresenterExtend();
        mPresnter.setPresenterView(this);
        initToolBar();
    }

    private void initToolBar() {
        setHasOptionsMenu(true);
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置溢出图标
        //toolbarInclude.setOverflowIcon(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_launcher));
        actionBar.setTitle(getResources().getString(R.string.mine_infor));
        showMineDetailData();

        RequestBuilder<Drawable> load = Glide.with(baseFragmentActivity)
                .load(getString(R.string.url) + "users/avatar/" + CustomApplication.userEntity.getId());
        LogUtils.d(getString(R.string.url) + "users/avatar/" + CustomApplication.userEntity.getId());
    }

    private void showMineDetailData() {
        tvDetailBioMine.setText(CustomApplication.userEntity.getBio());
        bioArrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStyleDialogNick4 == null) {
                    appStyleDialogNick4 = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_BIO) {
                        @Override
                        public void doGetBioDetail(String bio) {
                            super.doGetBioDetail(bio);
                            user = new UserResponse.ResultsBean();
                            Map<String, String> map = new HashMap(1);
                            map.put("bio", bio);
                            user.setBio(bio);
                            mPresnter.updateUserInfor(map, Constant.EDITE_BIO);

                        }
                    };
                    appStyleDialogNick4.show();
                } else {
                    appStyleDialogNick4.show();
                }
            }
        });
        tvDetailBlogMine.setText(CustomApplication.userEntity.getBlog());
        blogArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStyleDialogNick5 == null) {
                    appStyleDialogNick5 = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_BLOG_URL) {
                        @Override
                        public void doGetBlogDetail(String blog) {
                            super.doGetBlogDetail(blog);
                            user = new UserResponse.ResultsBean();
                            Map<String, String> map = new HashMap(1);
                            map.put("blog", blog);
                            user.setBlog(blog);
                            mPresnter.updateUserInfor(map, Constant.EDITE_BLOG_URL);
                            //修改的弹窗用的同一个 不置空 会显示同一个
                        }
                    };
                    appStyleDialogNick5.show();
                } else {
                    appStyleDialogNick5.show();
                }

            }
        });


        nameArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

            }
        });

        tvDetailNickMine.setText(CustomApplication.userEntity.getNick_name());
        nickArrow2.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              if (appStyleDialogNick2 == null) {
                                                  appStyleDialogNick2 = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_NICK) {
                                                      @Override
                                                      public void doGetdata(String nick) {
                                                          super.doGetdata(nick);
                                                          user = new UserResponse.ResultsBean();
                                                          Map<String, String> map = new HashMap(1);
                                                          map.put("nick_name", nick);
                                                          user.setNick_name(nick);
                                                          mPresnter.updateUserInfor(map, Constant.EDITE_NICK);
                                                      }
                                                  };
                                                  appStyleDialogNick2.show();
                                              } else {
                                                  appStyleDialogNick2.show();
                                              }
                                          }
                                      }
        );
        tvDetailSexMine.setText(CustomApplication.userEntity.getGender() == Constant.MAN ? getString(R.string.man) : getString(R.string.female));
        sexArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStyleDialogNick3 == null) {
                    appStyleDialogNick3 = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_SEX) {
                        @Override
                        public void doGetGender(int gender) {
                            super.doGetGender(gender);
                            if (gender == Constant.FEAMALE || gender == Constant.MAN) {
                                user = new UserResponse.ResultsBean();
                                Map<String, String> map = new HashMap(1);
                                map.put("gender", gender + "");
                                user.setGender(gender);
                                mPresnter.updateUserInfor(map, Constant.EDITE_SEX);
                            }
                        }
                    };
                    appStyleDialogNick3.show();
                } else {
                    appStyleDialogNick3.show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                removeFragment();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResponseData(BaseResponse response) {

    }

    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override
    public void showEmptyViewByCode(int code, String smg) {

    }

    @Override
    public void showProgressView(Boolean b) {
        if (b) {
            showProgressDialog(getString(R.string.on_loade));
        } else {
            dismissProgressDialog();
        }

    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }

    public static MineDetailFragment newInstance() {

        return new MineDetailFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresnter.detacheView();
    }

    @Override
    public void setUserInfor(int typeEdit) {
        switch (typeEdit) {
            case Constant.EDITE_NICK:
                tvDetailNickMine.setText(user.getNick_name());
                //把保存的类 属性信息也改了
                CustomApplication.userEntity.setNick_name(user.getNick_name());
                ArrayList<UpdateMIneDetailObserver.UpdateMineDetailLisener> baseFragments = UpdateMIneDetailObserver.baseFragments;
                for (UpdateMIneDetailObserver.UpdateMineDetailLisener updateMineDetailLisener : baseFragments) {
                    updateMineDetailLisener.updateMineDetail(user.getNick_name());
                }
                break;
            case Constant.EDITE_SEX:
                tvDetailSexMine.setText(user.getGender() == 1 ? baseFragmentActivity.getString(R.string.man) : baseFragmentActivity.getString(R.string.female));
                //把保存的类 属性信息也改了
                CustomApplication.userEntity.setGender(user.getGender());
                break;
            case Constant.EDITE_BIO:
                tvDetailBioMine.setText(user.getBio());
                //把保存的类 属性信息也改了
                CustomApplication.userEntity.setBio(user.getBio());
                break;
            case Constant.EDITE_BLOG_URL:
                tvDetailBlogMine.setText(user.getBlog());
                CustomApplication.userEntity.setBlog(user.getBlog());
            default:
        }
    }

    static final int INTENTFORCAMERA = 1;
    static final int INTENTFORPHOTO = 2;
    static final int PHOTO_REQUEST_CUT = 3;

    public void toCamera() {
        Intent intentNow = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;

        if (!FileUtils.createOrExistsDir(path)) {
            ToastUtils.showShort(path + "路径未创建成功");
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //针对Android7.0，需要通过FileProvider封装过的路径，提供给外部调用
            //通过FileProvider创建一个content类型的Uri，进行封装
            uri = FileProvider.getUriForFile(baseFragmentActivity, BuildConfig.APPLICATION_ID, FileUtils.getFileByPath(path + name));
        } else {
            uri = Uri.fromFile(FileUtils.getFileByPath(path + name));
        }
        intentNow.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intentNow, INTENTFORCAMERA);
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
                // toCamera();
                toPhoto();
            }
        } else {
            //低于6.0的版本 如果拒绝了权限 怎么判断是否有权限
            Log.i("wytings", "------------- Build.VERSION.SDK_INT < 23 ------------");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //申请权限结果
        toCamera();// 每个请求 requestCode 对应一个 code 结果
        if (requestCode == 300) {
            Log.i("wytings", "--------------requestCode == 300->" + requestCode + "," + permissions.length + "," + grantResults.length);
        } else {
            Log.i("wytings", "--------------requestCode != 300->" + requestCode + "," + permissions + "," + grantResults);
        }
    }

    /**
     *    * 获取SDCARD剩余存储空间
     *    *
     *    * @return 0 sd已被挂载占用 1 sd卡内存不足 2 sd可用
     *    
     */
    public static int getAvailableExternalStorageSize() {
        if (isSDCardExist()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            long memorySize = availableBlocks * blockSize;
            if (memorySize < 10 * 1024 * 1024) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }
    /**
     * 是否挂载
     * @return
     */
    public static boolean isSDCardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *    * 读取图片属性：旋转的角度
     *    *
     *    * @param path 源信息
     *    *      图片绝对路径
     *    * @return degree旋转的角度
     *    
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     *    * 照片路径
     *    * 压缩后 宽度的尺寸
     *    * @param path
     *    * @param specifiedWidth
     *    
     */
    public static Bitmap resizeImage(String path, int specifiedWidth) throws Exception {


        Bitmap bitmap = null;
        FileInputStream inStream = null;
        File f = new File(path);
        System.out.println(path);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        try {
            inStream = new FileInputStream(f);
            int degree = readPictureDegree(path);
            BitmapFactory.Options opt = new BitmapFactory.Options();
//照片不加载到内存 只能读取照片边框信息
            opt.inJustDecodeBounds = true;
// 获取这个图片的宽和高
            decodeFile(path, opt); // 此时返回bm为空


            int inSampleSize = 1;
            final int width = opt.outWidth;
//      width 照片的原始宽度 specifiedWidth 需要压缩的宽度
//      1000 980
            if (width > specifiedWidth) {
                inSampleSize = (int) (width / (float) specifiedWidth);
            }
// 按照 565 来采样 一个像素占用2个字节
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
//      图片加载到内存
            opt.inJustDecodeBounds = false;
// 等比采样
            opt.inSampleSize = inSampleSize;
//      opt.inPurgeable = true;
// 容易导致内存溢出
            bitmap = BitmapFactory.decodeStream(inStream, null, opt);
// bitmap = BitmapFactory.decodeFile(path, opt);
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    inStream = null;
                }
            }

            if (bitmap == null) {
                return null;
            }
            Matrix m = new Matrix();
            if (degree != 0) {
//给Matrix 设置旋转的角度
                m.setRotate(degree);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            }
            float scaleValue = (float) specifiedWidth / bitmap.getWidth();
//      等比压缩
            m.setScale(scaleValue, scaleValue);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *    * 打开相册
     *    
     */
    public void toPhoto() {
        try {
            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
            getAlbum.setType("image/*");
            startActivityForResult(getAlbum, INTENTFORPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INTENTFORPHOTO:
//相册
                try {
// 必须这样处理，不然在4.4.2手机上会出问题
                    Uri originalUri = data.getData();
                    File f = null;
                    if (originalUri != null) {
                        f = new File(path + name);
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor actualimagecursor = baseFragmentActivity.getContentResolver().query(originalUri, proj, null, null, null);
                        if (null == actualimagecursor) {
                            if (originalUri.toString().startsWith("file:")) {
                                File file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                if (!file.exists()) {
                                    //地址包含中文编码的地址做utf-8编码
                                    originalUri = Uri.parse(URLDecoder.decode(originalUri.toString(), "UTF-8"));
                                    file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                }
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                            // 系统图库
                        } else {
                            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            actualimagecursor.moveToFirst();
                            String img_path = actualimagecursor.getString(actual_image_column_index);
                            if (img_path == null) {
                                InputStream inputStream = baseFragmentActivity.getContentResolver().openInputStream(originalUri);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            } else {
                                File file = new File(img_path);
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        }
                        Bitmap bitmap = resizeImage(f.getAbsolutePath(), 1080);
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
                        if (bitmap != null) {

                            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                                fos.close();
                                fos.flush();
                            }
                            if (!bitmap.isRecycled()) {
                                bitmap.isRecycled();
                            }
                            ivDetailNameMine.setImageBitmap(bitmap);
                            System.out.println("f = " + f.length());
                            mPresnter.upLoadeAvatar(path + name);
                            //TODO 上传
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case INTENTFORCAMERA:
//        相机
                try {

//file 就是拍照完 得到的原始照片
                    File file = new File(path + name);
                    Bitmap bitmap = resizeImage(file.getAbsolutePath(), 1080);
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
                    if (bitmap != null) {
                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                            fos.close();
                            fos.flush();
                        }
                        ivDetailNameMine.setImageBitmap(bitmap);
                        if (!bitmap.isRecycled()) {
//通知系统 回收bitmap
                            bitmap.isRecycled();
                        }
                        System.out.println("f = " + file.length());
                        //TODO 上传图片
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    //拷贝输入输出流
    public static void copyStream(InputStream inputStream, OutputStream outStream) throws Exception {
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT); // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
    }


}
