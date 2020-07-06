package com.hjy.gamecommunity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;


/**
 * @author xuexiang
 * @date 2017/11/21 上午10:44
 */
public class FlowTagAdapter extends BaseTagAdapter<String, TextView> {
    private int height;

    public FlowTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView newViewHolder(View convertView) {
        height = convertView.getHeight();
        return (TextView) convertView.findViewById(R.id.bind_role_item_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bind_role;
    }


    @Override
    protected void convert(TextView textView, String item, int position) {
        textView.setText(item);
    }

    public int getHeight() {
        return height;
    }
}
