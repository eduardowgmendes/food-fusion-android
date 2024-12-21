package br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.generic.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.MessageProperty;

public class MessagePropertyViewHolderDelegate implements ViewHolderDelegate<MessageProperty> {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.message_property_layout, parent, false);
        return new MessagePropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, MessageProperty item) {
        if (viewHolder instanceof MessagePropertyViewHolder) {
            ((MessagePropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class MessagePropertyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView icon;
        private final TextView title, message;
        private final MaterialCardView messageContainer;
        private MaterialButton actionButton;

        public MessagePropertyViewHolder(@NonNull View itemView) {
            super(itemView);

            messageContainer = itemView.findViewById(R.id.message_container);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
            actionButton = itemView.findViewById(R.id.action_button);
        }

        public void bind(MessageProperty property) {
            icon.setImageResource(property.getIcon());
            title.setText(property.getKey());
            message.setText(property.getDescription());
            messageContainer.setOnClickListener(property.getActionListener());

            actionButton.setIconResource(property.getActionIcon());
            actionButton.setText(property.getActionLabel());
            actionButton.setOnClickListener(property.getActionListener());
        }
    }
}
