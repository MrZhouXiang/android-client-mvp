package puyuntech.com.androidclient.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

public class RecViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    public RecViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
        this.mConvertView = itemView;
        this.mViews = new SparseArray<View>();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 閫氳繃鎺т欢鐨処d鑾峰彇瀵逛簬鐨勬帶浠讹紝濡傛灉娌℃湁鍒欏姞鍏iews
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public static <T extends View> T findViewById(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }


    /**
     * 涓篢extView璁剧疆瀛楃涓�
     *
     * @param viewId
     * @param text
     * @return
     */
    public RecViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public RecViewHolder setText(int viewId, CharSequence text, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        view.setText(text);
        return this;
    }

    /**
     * 涓篢extView璁剧疆瀛楃涓�
     *
     * @param viewId
     * @param text
     * @return
     */
    public RecViewHolder setText(int viewId, int text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 涓篒mageView璁剧疆鍥剧墖
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public RecViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 涓篒mageView璁剧疆鍥剧墖
     *
     * @param viewId
     * @return
     */
    public RecViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 涓篒mageView璁剧疆鍥剧墖
     *
     * @param viewId
     * @return
     */
    public RecViewHolder setImageByUrl(int viewId, String url) {
//        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//                (ImageView) getView(viewId));
//        Glide.with(context).load(url);
        return this;
    }

    /**
     * 设置控件点击事件
     *
     * @param viewId
     * @return
     */
    public RecViewHolder setClick(int viewId, View.OnClickListener l) {
        View view = getView(viewId);
        view.setOnClickListener(l);
        return this;
    }

    /**
     * 设置root点击事件
     *
     * @return
     */
    public RecViewHolder setRootClick(View.OnClickListener l) {
        mConvertView.setOnClickListener(l);
        return this;
    }
}
