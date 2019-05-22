package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools;

import android.support.design.widget.AppBarLayout;

/**
 * 使用方式
 * appbarlayout.addOnOffsetChangedListener(new AppBarStatusChangeListener() {})
 */

public abstract class AppBarStatusChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStatusExpanded(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStatusCollapsed(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onStatusExpanded(AppBarLayout appBarLayout, State state);

    public abstract void onStatusCollapsed(AppBarLayout appBarLayout, State state);

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
