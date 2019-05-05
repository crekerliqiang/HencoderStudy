package demo.com.touch.view;

import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import static android.hardware.radio.V1_0.PinState.DISABLED;

public class ViewDoc {
    public boolean onTouchEvent(MotionEvent event) {
        //获取基本信息 点击的坐标值(x.y)
        final float x = event.getX();
        final float y = event.getY();
        final int viewFlags = mViewFlags;
        //只考虑单点触控的action  多点触控需要使用event.getActionMasked()
        final int action = event.getAction();

        //判断是否是 CLICKABLE LONG_CLICKABLE CONTEXT_CLICKABLE
        //解释一下，CONTEXT_CLICKABLE：早期的android手机有长按菜单，或者设备外接鼠标，然后点击右键
        //两种点击事件都称为 CONTEXT_CLICKABLE
        final boolean clickable = ((viewFlags & CLICKABLE) == CLICKABLE
                || (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE)
                || (viewFlags & CONTEXT_CLICKABLE) == CONTEXT_CLICKABLE;

        //如果是不可用状态，返回 clickable
        //不可用状态：当按钮处于被禁用状态时，任然需要消费掉点击事件，否则会被传递到下面的按钮中，产生BUG
        if ((viewFlags & ENABLED_MASK) == DISABLED) {
            //重置按下状态
            if (action == MotionEvent.ACTION_UP && (mPrivateFlags & PFLAG_PRESSED) != 0) {
                setPressed(false);
            }
            mPrivateFlags3 &= ~PFLAG3_FINGER_DOWN;
            // A disabled view that is clickable still consumes the touch
            // events, it just doesn't respond to them.
            return clickable;
        }

        //设置用户代理
        //解释：按钮比较小的时候，会点不着，可以设置一个代理，手动把按钮方法（现在都用设计解决该问题）
        if (mTouchDelegate != null) {
            if (mTouchDelegate.onTouchEvent(event)) {
                return true;
            }
        }

        //判断逻辑，四种MotionEvent事件处理
        //TOOLTIP[8.0新加入，解释性工具]：在xml里面设置(android:tooltipText="tooltipText")，可实现长按文本提示
        if (clickable || (viewFlags & TOOLTIP) == TOOLTIP) {

            //下面的四个点击事件，按照时间顺序 down move up cancel 看
            switch (action) {
                case MotionEvent.ACTION_UP:
                    mPrivateFlags3 &= ~PFLAG3_FINGER_DOWN;
                    //手指松开后，如果有 TOOLTIP 事件，则做一个延时，让TOOLTIP消失[handleTooltipUp 1500ms]
                    if ((viewFlags & TOOLTIP) == TOOLTIP) {
                        handleTooltipUp();
                    }

                    //不可点击状态：做各种状态的移除 ，然后结束[break]
                    if (!clickable) {
                        removeTapCallback();
                        removeLongPressCallback();
                        mInContextButtonPress = false;
                        mHasPerformedLongPress = false;
                        mIgnoreNextUpEvent = false;
                        break;
                    }
                    boolean prepressed = (mPrivateFlags & PFLAG_PREPRESSED) != 0;
                    //已按下 或者 预按下[概念在DOWN事件里面有讲到]
                    if ((mPrivateFlags & PFLAG_PRESSED) != 0 || prepressed) {
                        // take focus if we don't have it already and we should in
                        // touch mode.
                        //isFocusable() && isFocusableInTouchMode() && !isFocused()三个条件
                        //isFocusableInTouchMode ：点击按钮前，需要先有焦点，然后才能执行点击[和电脑鼠标指到文件是变色类似]
                        //辅助理解：一个按钮的点击流程：获取焦点-->点击
                        boolean focusTaken = false;
                        if (isFocusable() && isFocusableInTouchMode() && !isFocused()) {
                            focusTaken = requestFocus();
                        }

                        //如果为预按下 设置为按下
                        if (prepressed) {
                            // The button is being released before we actually
                            // showed it as pressed.  Make it show the pressed
                            // state now (before scheduling the click) to ensure
                            // the user sees it.
                            setPressed(true, x, y);
                        }

                        if (!mHasPerformedLongPress && !mIgnoreNextUpEvent) {
                            // This is a tap, so remove the longpress check
                            removeLongPressCallback();

                            // Only perform take click actions if we were in the pressed state
                            //触发点击
                            if (!focusTaken) {
                                // Use a Runnable and post this rather than calling
                                // performClick directly. This lets other visual state
                                // of the view update before click actions start.
                                if (mPerformClick == null) {
                                    mPerformClick = new PerformClick();
                                }
                                if (!post(mPerformClick)) {
                                    performClickInternal();
                                }
                            }
                        }

                        if (mUnsetPressedState == null) {
                            mUnsetPressedState = new UnsetPressedState();
                        }

                        //按键按下然后瞬间松手，会有一个延时 postDelayed[64ms]
                        //一般人可能手速也没这么快......
                        if (prepressed) {
                            postDelayed(mUnsetPressedState,
                                    ViewConfiguration.getPressedStateDuration());
                        } else if (!post(mUnsetPressedState)) {
                            // If the post failed, unpress right now
                            mUnsetPressedState.run();
                        }

                        removeTapCallback();
                    }
                    mIgnoreNextUpEvent = false;
                    break;

                case MotionEvent.ACTION_DOWN:
                    //判断是否摸到了屏幕
                    //摸到屏幕？：就是带实体按键的android设备
                    if (event.getSource() == InputDevice.SOURCE_TOUCHSCREEN) {
                        mPrivateFlags3 |= PFLAG3_FINGER_DOWN;
                    }
                    mHasPerformedLongPress = false;

                    //如果不可点击，则检查长按
                    //不可点击还检查长按？：为上面的TOOLTIP设计，TOOLTIP就是长按触发的
                    if (!clickable) {
                        checkForLongClick(0, x, y);
                        break;
                    }

                    //检查是否是鼠标右边点击(针对于外接鼠标设备的情况)
                    //该方法(performButtonActionOnTouchDown)的源码中就是判断SOURCE_MOUSE 和 BUTTON_SECONDARY ，
                    // 如果满足则 showContextMenu
                    if (performButtonActionOnTouchDown(event)) {
                        break;
                    }
                    // Walk up the hierarchy to determine if we're inside a scrolling container.
                    //是否在滑动控件里
                    //注意：在 isInScrollingContainer 方法中可以看到，View认为所有的控件都是可滑动的
                    //所以在自己写View的时候，如果不是可滑动的View 需要重写 shouldDelayChildPressedState
                    //可以自己看一下 isInScrollingContainer 的源码[就几行]
                    boolean isInScrollingContainer = isInScrollingContainer();

                    // For views inside a scrolling container, delay the pressed feedback for
                    // a short period in case this is a scroll.
                    //在滑动控件zai(比如ListView)里
                    if (isInScrollingContainer) {
                        //设置为预按下（按下不松手，列表会变色；按下立即滑动，列表不会变色；可以拿微信的聊天列表试试）
                        mPrivateFlags |= PFLAG_PREPRESSED;
                        //实例化点击等待器
                        if (mPendingCheckForTap == null) {
                            mPendingCheckForTap = new CheckForTap();
                        }
                        mPendingCheckForTap.x = event.getX();
                        mPendingCheckForTap.y = event.getY();
                        //延时 ViewConfiguration.getTapTimeout()[100ms] 后再触发点击
                        postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
                    }

                    //不在滑动控件里面：1.把自己设置为按下状态 2.设置长按等待器
                    else {
                        // Not inside a scrolling container, so show the feedback right away
                        setPressed(true, x, y);
                        checkForLongClick(0, x, y);
                    }
                    break;

                case MotionEvent.ACTION_CANCEL:
                    //把各种状态全部清空，把各种延时都清零
                    //CANCEL 事件都是被动收到的，由父View发送给子View
                    if (clickable) {
                        setPressed(false);
                    }
                    removeTapCallback();
                    removeLongPressCallback();
                    mInContextButtonPress = false;
                    mHasPerformedLongPress = false;
                    mIgnoreNextUpEvent = false;
                    mPrivateFlags3 &= ~PFLAG3_FINGER_DOWN;
                    break;

                case MotionEvent.ACTION_MOVE:

                    //波纹效果
                    //手指移动的时候，波纹的中心会跟着手指一起移动
                    if (clickable) {
                        drawableHotspotChanged(x, y);
                    }
                    // Be lenient about moving outside of buttons
                    //移动到按钮的外部，把所有的状态都清空
                    //mTouchSlop : 溢出
                    //溢出？手滑动时，往边缘出去了一点点又回到了按钮内部，也认为没有出去；因为用户可能认为自己的手没有出去
                    if (!pointInView(x, y, mTouchSlop)) {
                        // Outside button
                        // Remove any future long press/tap checks
                        removeTapCallback();
                        removeLongPressCallback();
                        if ((mPrivateFlags & PFLAG_PRESSED) != 0) {
                            setPressed(false);
                        }
                        mPrivateFlags3 &= ~PFLAG3_FINGER_DOWN;
                    }
                    break;
            }

            return true;
        }

        return false;
    }

}
