package lk.ysk.simplecombo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class ComboBox extends androidx.appcompat.widget.AppCompatSpinner {

    private ComboBoxAdapter mAdapter;
    private int mItemsRes;
    private final ComboBoxAttributes attributes = new ComboBoxAttributes();

    /**
     * Construct a new spinner with the given context's theme.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public ComboBox(@NonNull Context context) {
        super(context);
        initializeAdapter(context);
    }

    /**
     * Construct a new spinner with the given context's theme and the supplied
     * mode of displaying choices. <code>mode</code> may be one of
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param mode    Constant describing how the user will select choices from the spinner.
     */
    public ComboBox(@NonNull Context context, int mode) {
        super(context, mode);
        initializeAdapter(context);
    }

    /**
     * Construct a new spinner with the given context's theme and the supplied attribute set.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public ComboBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ComboBoxStyle, 0, 0);
        loadAttributes(a);
        initializeAdapter(context);
    }

    /**
     * Construct a new spinner with the given context's theme, the supplied attribute set,
     * and default style attribute.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     */
    public ComboBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ComboBoxStyle, 0, 0);
        loadAttributes(a);
        initializeAdapter(context);
    }

    /**
     * Construct a new spinner with the given context's theme, the supplied attribute set,
     * and default style.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @param mode         Constant describing how the user will select choices from the spinner.
     */
    public ComboBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ComboBoxStyle, 0, 0);
        loadAttributes(a);
        initializeAdapter(context);
    }

    /**
     * Constructs a new spinner with the given context's theme
     * should be inflated.
     *
     * @param context      The context against which the view is inflated, which
     *                     provides access to the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default
     *                     values for the view. Can be 0 to not look for
     *                     defaults.
     * @param mode         Constant describing how the user will select choices from
     *                     the spinner.
     * @param popupTheme   The theme against which the dialog or dropdown popup
     *                     should be inflated. May be {@code null} to use the
     *                     view theme. If set, this will override any value
     *                     specified by
     *                     {@link R.styleable#Spinner_popupTheme}.
     */
    public ComboBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ComboBoxStyle, 0, 0);
        loadAttributes(a);
        initializeAdapter(context);
    }

    private void loadAttributes(TypedArray a) {
        attributes.setPadding((int)a.getDimension(R.styleable.ComboBoxStyle_itemPadding, 0));
        attributes.setPaddingStart((int)a.getDimension(R.styleable.ComboBoxStyle_itemPaddingStart, 0));
        attributes.setPaddingEnd((int)a.getDimension(R.styleable.ComboBoxStyle_itemPaddingEnd, 0));
        attributes.setPaddingTop((int)a.getDimension(R.styleable.ComboBoxStyle_itemPaddingTop, 0));
        attributes.setPaddingBottom((int)a.getDimension(R.styleable.ComboBoxStyle_itemPaddingBottom, 0));

        attributes.setMargin((int)a.getDimension(R.styleable.ComboBoxStyle_itemMargin, 0));
        attributes.setMarginStart((int)a.getDimension(R.styleable.ComboBoxStyle_itemMarginStart, 0));
        attributes.setMarginEnd((int)a.getDimension(R.styleable.ComboBoxStyle_itemMarginEnd, 0));
        attributes.setMarginTop((int)a.getDimension(R.styleable.ComboBoxStyle_itemMarginTop, 0));
        attributes.setMarginBottom((int)a.getDimension(R.styleable.ComboBoxStyle_itemMarginBottom, 0));

        attributes.setTextSize((int)a.getDimension(R.styleable.ComboBoxStyle_itemTextSize, 14));
        attributes.setTextColor(a.getColor(R.styleable.ComboBoxStyle_itemTextColor, Color.BLACK));

        mItemsRes = a.getResourceId(R.styleable.ComboBoxStyle_items, 0);
    }

    private void initializeAdapter(Context context) {
        mAdapter = new ComboBoxAdapter(context, attributes);
        if (mItemsRes != 0)
        {
            String[] items = context.getResources().getStringArray(mItemsRes);
            mAdapter.getList().addAll(Arrays.asList(items));
        }
        setAdapter(mAdapter);
    }

    public void addItem(String item) {
        mAdapter.addItem(item);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public String getSelectedItem() {
        return mAdapter.getItem(getSelectedItemPosition());
    }

    public int getSelectedIndex() {
        return getSelectedItemPosition();
    }

    public void removeItem(String item) {
        for (String i: mAdapter.getList()) {
            if (i.equals(item))
            {
                mAdapter.removeItem(i);
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    public List<String> getItems() {
        return mAdapter.getList();
    }

    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }
}
