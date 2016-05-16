package puyuntech.com.androidclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @param <T>
 */
public abstract class BaseRecAdapter<T> extends RecyclerView.Adapter<RecViewHolder> {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public BaseRecAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
        return new RecViewHolder(v);
    }


    public void setDatas(List<T> mDatas) {
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    public void addDatas(List<T> mDatas) {
//        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    /**
     * 过滤
     *
     * @param mDatas
     */
    public void setDatasFilter(List<T> mDatas) {
        mDatas.removeAll(this.mDatas);
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    /**
     * 删除消息
     *
     * @param position
     */
    public void delete(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



}
