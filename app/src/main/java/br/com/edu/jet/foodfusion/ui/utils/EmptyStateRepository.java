package br.com.edu.jet.foodfusion.ui.utils;

import android.content.res.Resources;
import android.view.View;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.emptystate.EmptyState;

public class EmptyStateRepository {

    private Resources resources;

    public EmptyStateRepository(Resources resources) {
        this.resources = resources;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public static EmptyState create(int backdropRes, String title, String message) {
        return new EmptyState(backdropRes, title, message);
    }

    public EmptyState generalIssue() {
        return new EmptyState(R.drawable.not_connected, resources.getString(R.string.general_issue_title), resources.getString(R.string.general_issue_message));
    }

    public EmptyState connectionIssue(int actionIconRes, String actionLabel, View.OnClickListener actionListener) {
        return new EmptyState(R.drawable.not_connected,
                resources.getString(R.string.connection_issue_title),
                resources.getString(R.string.connection_issue_message),
                actionIconRes, actionLabel, actionListener);
    }

    public EmptyState serverIssue(int actionIconRes, String actionLabel, View.OnClickListener actionListener) {
        return new EmptyState(
                R.drawable.not_connected,
                resources.getString(R.string.server_is_unreachable_title),
                resources.getString(R.string.server_is_unreachable_message),
                actionIconRes,
                actionLabel,
                actionListener);
    }

    public EmptyState dataFetchIssue(int actionIconRes, String actionLabel, View.OnClickListener actionListener) {
        return new EmptyState(R.drawable.not_connected, resources.getString(R.string.data_not_available_title), resources.getString(R.string.data_not_available_message), actionIconRes, actionLabel, actionListener);
    }

    public EmptyState emptyListIssue() {
        return new EmptyState(R.drawable.nothing_found, resources.getString(R.string.no_items_found_title), resources.getString(R.string.no_items_found_message));
    }

    public EmptyState emptyListIssue(int actionIconRes, String actionLabel, View.OnClickListener actionListener) {
        return new EmptyState(R.drawable.nothing_found, resources.getString(R.string.no_items_found_title), resources.getString(R.string.no_items_found_message));
    }

    public EmptyState locationIssue() {
        return new EmptyState(R.drawable.nothing_found, resources.getString(R.string.location_unavailable_title), resources.getString(R.string.location_unavailable_message));
    }
}
