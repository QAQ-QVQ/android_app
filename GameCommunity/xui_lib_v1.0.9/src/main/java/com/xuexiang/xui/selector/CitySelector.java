package com.xuexiang.xui.selector;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.xuexiang.xui.ProvinceInfo;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xutil.net.JsonUtil;
import com.xuexiang.xutil.net.type.TypeToken;
import com.xuexiang.xutil.resource.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class CitySelector {

    /**
     * 加载城市数据
     */
    private List<ProvinceInfo> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();

    private boolean loadData(Context context) {
        if (options1Items.size() > 0) {
            return true;
        }

        String JsonData = ResourceUtils.readStringFromAssert("province.json");
        List<ProvinceInfo> provinceInfos = JsonUtil.fromJson(JsonData, new TypeToken<List<ProvinceInfo>>() {
        }.getType());
        if (provinceInfos == null) {
            Toast.makeText(context, "城市加载失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
        /**
         * 添加省份数据
         */
        options1Items = provinceInfos;

        for (ProvinceInfo provinceInfo : provinceInfos) { //遍历省份
            List<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            List<List<String>> areaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (ProvinceInfo.City city : provinceInfo.getCityList()) {
                String CityName = city.getName();
                cityList.add(CityName);//添加城市

                List<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (city.getArea() == null || city.getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(city.getArea());
                }
                areaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(areaList);
        }
        return true;


    }

    /**
     * @return 获取默认城市的索引
     */
    private int[] getDefaultCity() {
        int[] res = new int[3];
        ProvinceInfo provinceInfo;
        List<ProvinceInfo.City> cities;
        ProvinceInfo.City city;
        List<String> ares;
        for (int i = 0; i < options1Items.size(); i++) {
            provinceInfo = options1Items.get(i);
            if ("江苏省".equals(provinceInfo.getName())) {
                res[0] = i;
                cities = provinceInfo.getCityList();
                for (int j = 0; j < cities.size(); j++) {
                    city = cities.get(j);
                    if ("南京市".equals(city.getName())) {
                        res[1] = j;
                        ares = city.getArea();
                        for (int k = 0; k < ares.size(); k++) {
                            if ("雨花台区".equals(ares.get(k))) {
                                res[2] = k;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }

    /**
     * 城市选择器
     *
     * @param isDialog
     */
    public void showPickerView(final Context context, final boolean isDialog, final SelectListener selectListener) {
        // 弹出选择器
        final MiniLoadingDialog mMiniLoadingDialog = WidgetUtils.getMiniLoadingDialog(context);

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                //  在这里可以加载LoadingView提示用户
                mMiniLoadingDialog.show();
            }

            protected Object doInBackground(Object[] objects) {
                loadData(context);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mMiniLoadingDialog.dismiss();

                int[] defaultSelectOptions = getDefaultCity();
                OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
//                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
//                                options2Items.get(options1).get(options2) + "-" +
//                                options3Items.get(options1).get(options2).get(options3);
                        if (selectListener != null) {
                            selectListener.onSelect(options1Items.get(options1).getPickerViewText(), options2Items.get(options1).get(options2), options3Items.get(options1).get(options2).get(options3));
                        }
                    }
                })

                        .setTitleText("城市选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .isDialog(isDialog)
                        .setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1], defaultSelectOptions[2])
                        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();

            }
        };

        asyncTask.execute();

//        ThreadUtils.executeByIo(new ThreadUtils.Task<String>() {
//            @Override
//            public String doInBackground() throws Throwable {
//
//
//                return null;
//
//            }
//
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onFail(Throwable t) {
//
//            }
//        });


    }

    public interface SelectListener {
        void onSelect(String options1, String options2, String options3);
    }
}
