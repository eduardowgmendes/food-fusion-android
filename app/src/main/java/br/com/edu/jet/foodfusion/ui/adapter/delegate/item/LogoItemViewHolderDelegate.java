package br.com.edu.jet.foodfusion.ui.adapter.delegate.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.LogoItem;

public class LogoItemViewHolderDelegate implements ViewHolderDelegate<LogoItem> {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.logo_item_layout, parent, false);
        return new LogoItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, LogoItem item) {
        if (viewHolder instanceof LogoItemViewHolder) {
            ((LogoItemViewHolder) viewHolder).bind(item);
        }
    }

    static class LogoItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView label;
        private final ImageView logo;

        public LogoItemViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.item_label);
            logo = itemView.findViewById(R.id.item_logo);
        }

        public void bind(LogoItem item) {
            label.setText(item.getLabel());
            if (item.getLogoUrl() != null) {
                if (!item.getLogoUrl().isEmpty()) {
                    Picasso.get()
                            .load(item.getLogoUrl())
                            .fit().centerCrop()
                            .error(R.drawable.baseline_error_outline_24)
                            .into(logo);
                }
            } else {
                logo.setImageResource(R.drawable.twotone_broken_image_24);
            }
        }
    }
}
