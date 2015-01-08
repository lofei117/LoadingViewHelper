package info.lofei.viewloadinghelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by lofei on 15/1/8.
 */
public class ViewHelper {

    /**
     * Set loading State for the specific view.
     * <p>
     * This method would create a new {@link android.widget.ProgressBar} with same {@link android.view.ViewGroup.LayoutParams} with the <b><span style="color:blue">view</span></b>.
     * And saved the reference of the ProgressBar using the method {@link android.view.View#setTag(Object obj)}.
     * <p>If you want to use the original tag, see below sample code:</p>
     *     <code>
     *         <pre>
     *             if(view.getTag() instanceof WrappedTag) {
     *                 WrappedTag wrappedTag = (WrappedTag)view.getTag();
     *                 Object tag = wrappedTag.getOriginTag();
     *
     *                 // do something with the original tag...
     *
     *             } else {
     *                 // do something with the original tag...
     *             }
     *
     *         </pre>
     *     </code>
     * @param context
     * @param view
     * @param isLoading
     */
    public static void setViewLoadingState(final Context context, final View view, boolean isLoading) {
        if(context == null || view == null) {
            return;
        }

        if(isLoading) {
            if(view.getTag() instanceof WrappedTag) {
                WrappedTag wrappedTag = (WrappedTag) view.getTag();
                ProgressBar progressBar = wrappedTag.getProgressBar();
                if(progressBar != null) {
                    view.setEnabled(false);
                    view.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    return;
                }
            }
            if(view.getParent() != null) {
                ViewGroup parent = (ViewGroup) view.getParent();
                Object originTag = view.getTag();

                ProgressBar progressBar = new ProgressBar(context);
                progressBar.setIndeterminate(true);

                // set your custom indetermindate drawable here
//                Drawable drawable = context.getResources().getDrawable(R.drawable.progress);
//                progressBar.setIndeterminateDrawable(drawable);

                progressBar.setLayoutParams(view.getLayoutParams());

                parent.addView(progressBar);

                WrappedTag wrappedTag = new WrappedTag();
                wrappedTag.setOriginTag(originTag);
                wrappedTag.setProgressBar(progressBar);

                view.setTag(wrappedTag);
                view.setEnabled(false);
                view.setVisibility(View.INVISIBLE);
            }

        } else {
            if(view.getTag() instanceof WrappedTag) {
                WrappedTag wrappedTag = (WrappedTag) view.getTag();
                ProgressBar progressBar = wrappedTag.getProgressBar();
                if(progressBar != null) {
                    view.setEnabled(true);
                    view.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    public static class WrappedTag {

        Object mOriginTag;
        ProgressBar mProgressBar;

        public WrappedTag(){}

        public Object getOriginTag() {
            return mOriginTag;
        }

        public void setOriginTag(Object originTag) {
            this.mOriginTag = originTag;
        }

        public ProgressBar getProgressBar() {
            return mProgressBar;
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.mProgressBar = progressBar;
        }
    }
}
