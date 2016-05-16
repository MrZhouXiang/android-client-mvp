package puyuntech.com.beihai.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.context = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 鎷垮埌涓�涓猇iewHolder瀵硅薄
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, layoutId, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
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
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setText(int viewId, CharSequence text, int color) {
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
    public ViewHolder setText(int viewId, int text) {
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
    public ViewHolder setImageResource(int viewId, int drawableId) {
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
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
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
    public ViewHolder setImageByUrl(int viewId, String url) {
//        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//                (ImageView) getView(viewId));
//        Glide.with(context).load(url);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}
