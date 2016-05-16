package puyuntech.com.beihai.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * @param <T>
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	public BaseListAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}
	
	public void setDatas(List<T> mDatas) {
		this.mDatas.clear();
		this.mDatas.addAll(mDatas);
		notifyDataSetChanged();
	}
	
	/**
	 * 过滤
	 * @param mDatas
	 */
	public void setDatasFilter(List<T> mDatas) {
		mDatas.removeAll(this.mDatas);
		this.mDatas.addAll(mDatas);
		notifyDataSetChanged();
	}
	
	/**
	 * 删除消息
	 * @param position
	 */
	public void delete(int position) {
		mDatas.remove(position);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if (mDatas == null) {
			return 0;
		}
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		convert(viewHolder, getItem(position));
		convert(viewHolder, getItem(position), position);
		return viewHolder.getConvertView();

	}

	public abstract void convert(ViewHolder helper, T item);
	
	public void convert(ViewHolder helper, T item, int position) {
	}

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent) {
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}

}
