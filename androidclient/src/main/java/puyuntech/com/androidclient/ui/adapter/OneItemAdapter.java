package puyuntech.com.androidclient.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.model.ListItemModel;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:49
 * @描述
 * @修改时间 2016-05-16 下午 13:49
 * @修改描述
 * @修改者 Administrator
 **/
public class OneItemAdapter extends BaseQuickAdapter<ListItemModel> {


    public OneItemAdapter(Context context, int itemLayoutId, List mDatas) {
        super(context, itemLayoutId, mDatas);
    }


    @Override
    protected void convert(BaseViewHolder helper, ListItemModel item) {
        helper.setText(R.id.text_tv, item.value);
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);
    }
}
