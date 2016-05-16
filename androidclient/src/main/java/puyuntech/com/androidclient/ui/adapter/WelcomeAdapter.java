package puyuntech.com.androidclient.ui.adapter;

import android.content.Context;

import java.util.List;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.model.ListItemModel;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:49
 * @描述 WelcomeAdapter
 * @修改时间 2016-05-16 下午 13:49
 * @修改描述
 * @修改者 Administrator
 **/
public abstract class WelcomeAdapter extends BaseRecAdapter<ListItemModel> {

    public WelcomeAdapter(Context context, List mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }


    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        //这一句可以统一设置每项的点击事件
        super.onBindViewHolder(holder, position);
        ListItemModel model = mDatas.get(position);
        holder.setText(R.id.text_tv, position + "、" + model.value);
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }
}
