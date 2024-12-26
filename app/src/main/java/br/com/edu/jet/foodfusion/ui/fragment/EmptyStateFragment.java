package br.com.edu.jet.foodfusion.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.emptystate.EmptyState;

public class EmptyStateFragment extends Fragment {

    private static final String TAG = EmptyStateFragment.class.getSimpleName();

    private EmptyState emptyState;

    public EmptyStateFragment() {
        // Required empty public constructor
    }

    public static EmptyStateFragment newInstance(EmptyState emptyState) {
        EmptyStateFragment fragment = new EmptyStateFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, emptyState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            emptyState = getArguments().getParcelable(TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootLayout = inflater.inflate(R.layout.fragment_empty_state, container, false);

        ImageView backdrop = rootLayout.findViewById(R.id.empty_state_backdrop);
        backdrop.setImageResource(emptyState.getBackdropRes());

        TextView title = rootLayout.findViewById(R.id.empty_state_message_title);
        title.setText(emptyState.getTitle());

        TextView description = rootLayout.findViewById(R.id.empty_state_message_description);
        description.setText(emptyState.getDescription());

        MaterialButton actionButton = rootLayout.findViewById(R.id.empty_state_action_button);

        if (emptyState.getOnClickListener() != null) {
            actionButton.setOnClickListener(emptyState.getOnClickListener());
            actionButton.setText(emptyState.getActionLabel());
            actionButton.setIconResource(emptyState.getActionIconRes());
        } else {
            actionButton.setVisibility(View.GONE);
        }

        return rootLayout;
    }
}