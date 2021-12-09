package lk.ysk.simplecombo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ComboBoxAdapter extends BaseAdapter implements SpinnerAdapter {

    private final List<String> mList;
    private final Context mContext;
    private ComboBoxAttributes mAttributes;

    public ComboBoxAdapter(Context context, ComboBoxAttributes attributes) {
        mList = new ArrayList<>();
        mContext = context;
        mAttributes = attributes;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.combobox_item, null);
        TextView tv = (TextView)v;
        tv.setText(mList.get(i));

        v.setPadding(mAttributes.getPaddingStart(), mAttributes.getPaddingTop(), mAttributes.getPaddingEnd(), mAttributes.getPaddingBottom());
        if (mAttributes.getPadding() != 0)
            v.setPadding(mAttributes.getPadding(),mAttributes.getPadding(),mAttributes.getPadding(),mAttributes.getPadding() );
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = mAttributes.getMarginStart();
        lp.rightMargin = mAttributes.getMarginEnd();
        lp.topMargin = mAttributes.getMarginTop();
        lp.bottomMargin = mAttributes.getMarginBottom();
        if (mAttributes.getMargin() != 0) {
            lp.leftMargin = mAttributes.getMargin();
            lp.rightMargin = mAttributes.getMargin();
            lp.topMargin = mAttributes.getMargin();
            lp.bottomMargin = mAttributes.getMargin();
        }
        tv.setTextSize(mAttributes.getTextSize());
        tv.setTextColor(mAttributes.getTextColor());
        v.setLayoutParams(lp);
        return v;
    }

    public void addItem(String item) {
        mList.add(item);
    }

    public List<String> getList() {
        return mList;
    }

    public void removeItem(String item) {
        mList.remove(item);
    }
}
