package br.com.edu.jet.foodfusion.ui.utils;

import android.content.res.Resources;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.emptystate.EmptyState;

public class EmptyStateRepository {

    public static EmptyState create(int backdropRes, String title, String message) {
        return new EmptyState(backdropRes, title, message);
    }

    static class Error {
        
        private static Resources resources;
        
        public static EmptyState generalIssue() {
            return new EmptyState(R.drawable.unknown_error_empty_state, resources.getString(R.string.general_issue_title), "We ran into an issue. Try again or restart the app.");
        }

        public static EmptyState connectionIssue() {
            return new EmptyState(R.drawable.abstract_logo_placeholder, resources.getString(R.string.connection_issue_title), resources.getString(R.string.connection_issue_message));
        }

        public static EmptyState serverIssue() {
            return new EmptyState(R.drawable.abstract_logo_placeholder, "Server is Unreachable", "We could’t connect to the server. Please try again later.");
        }

        public static EmptyState dataFetchIssue() {
            return new EmptyState(R.drawable.error_empty_state, "Data Not Available", "Something went wrong while loading. Pull down to refresh.");
        }

        public static EmptyState emptyListIssue() {
            return new EmptyState(R.drawable.not_found_empty_state, "No Items Found", "It looks like there's nothing here yet. Check back later!");
        }

        public static EmptyState locationIssue() {
            return new EmptyState(R.drawable.not_found_empty_state, "Location Unavailable", "We could’t access your location. Please enable it in settings.");
        }
    }
}
