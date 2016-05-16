package puyuntech.com.androidclient.ui.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import puyuntech.com.androidclient.R;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:49
 * @描述 WelcomeAdapter
 * @修改时间 2016-05-16 下午 13:49
 * @修改描述
 * @修改者 Administrator
 **/
public abstract class WelcomeAdapter extends BaseRecAdapter<RecViewHolder> {

    public WelcomeAdapter(Context context, List mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    public abstract void onItemClick(View v, int position);

    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        String ss = String.valueOf(mDatas.get(position));
        holder.setText(R.id.text_tv, ss);
        holder.setRootClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }
}
