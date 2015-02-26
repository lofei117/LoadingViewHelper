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
     * And saved the reference of the ProgressBar using the method {@link android.view.View#setTag(int key, Object obj)}.
     * @param context
     * @param view
     * @param isLoading
     */
    public static void setViewLoadingState(final Context context, final View view, boolean isLoading) {
        if(context == null || view == null) {
            return;
        }

        if(isLoading) {
            Object pgbTag = view.getTag(R.id.loadinghelper_progressbar_id);
            if(pgbTag instanceof ProgressBar) {
                ProgressBar progressBar = (ProgressBar) pgbTag;
                if(progressBar != null) {
                    view.setEnabled(false);
                    view.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    return;
                }
            }
            if(view.getParent() != null) {
                ViewGroup parent = (ViewGroup) view.getParent();

                ProgressBar progressBar = new ProgressBar(context);
                progressBar.setIndeterminate(true);
                
                progressBar.setLayoutParams(view.getLayoutParams());

                parent.addView(progressBar);

                view.setTag(R.id.loadinghelper_progressbar_id, progressBar);
                view.setEnabled(false);
                view.setVisibility(View.INVISIBLE);
            }

        } else {
            Object pgbTag = view.getTag(R.id.loadinghelper_progressbar_id);
            if(pgbTag instanceof ProgressBar) {
                ProgressBar progressBar = (ProgressBar) pgbTag;
                if(progressBar != null) {
                    view.setEnabled(true);
                    view.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }
}
