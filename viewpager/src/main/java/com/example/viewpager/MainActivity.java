package com.example.viewpager;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private TextView mTitleTV;
    private LinearLayout mIndicatorVP;
    // 图片资源ID
    private final int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e};
    // 图片标题集合
    private final String[] imageDescriptions = {
            "尚硅谷波河争霸赛！",
            "凝聚你我，放飞梦想！",
            "抱歉没座位了！",
            "7月就业名单全部曝光！",
            "平均起薪11345元"
    };
    private ArrayList<ImageView> imageViews;
    private int mTotalNum;
    private int prePosition = 0;
    private Handler mHandler = new Handler();
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.banner_vp);
        mTitleTV = findViewById(R.id.banne_title);
        mIndicatorVP = findViewById(R.id.banner_indicator);
        mTitleTV.setText(imageDescriptions[0]);
        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);

            //添加到集合中
            imageViews.add(imageView);

            //添加点-----------------------
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            //point.setImageResource(R.drawable.point_selector);
            //在代码中设置的都是像数-问题，在所有的手机上都是8个像数
            //把8px当成是dp-->px
            int width = DensityUtil.dip2px(this, 8);
            Toast.makeText(MainActivity.this, "width==" + width, Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i == 0) {
                point.setEnabled(true); //显示红色
            } else {
                point.setEnabled(false);//显示灰色
                params.leftMargin = width;
            }


            point.setLayoutParams(params);

            mIndicatorVP.addView(point);
        }
        mTotalNum = imageViews.size();

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(new BannerAdapter());
        //设置中间位置
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();//要保证imageViews的整数倍
        mViewPager.setCurrentItem(item);
        r = new Runnable() {
            @Override
            public void run() {
                int item = mViewPager.getCurrentItem() + 1;
                mViewPager.setCurrentItem(item);
                mHandler.postDelayed(r, 5000);
            }
        };
        mHandler.postDelayed(r, 5000);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int realPosition = position % mTotalNum;

        //把上一个高亮的设置默认-灰色
        mIndicatorVP.getChildAt(prePosition).setEnabled(false);
        //当前的设置为高亮-红色
        mIndicatorVP.getChildAt(realPosition).setEnabled(true);
        mTitleTV.setText(imageDescriptions[realPosition]);
        prePosition = realPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//实现无限轮播，这个地方要返回一个很大的数
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int pos = position % mTotalNum;
            ImageView imageView = imageViews.get(pos);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }
}
