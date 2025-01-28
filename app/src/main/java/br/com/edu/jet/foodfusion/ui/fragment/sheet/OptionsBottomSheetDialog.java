package br.com.edu.jet.foodfusion.ui.fragment.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.ItemsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.BasicItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.CondensedItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.ConfigurableItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.GridItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.ListItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.LogoItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.MessageItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.SimpleCondensedItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;

public class OptionsBottomSheetDialog extends BottomSheetDialogFragment {

    public static final String TAG = OptionsBottomSheetDialog.class.getSimpleName();

    private final String dialogTitle;
    private final List<Item> items;

    private OptionsBottomSheetDialog(String dialogTitle, List<Item> items) {
        this.dialogTitle = dialogTitle;
        this.items = items;
    }

    public static OptionsBottomSheetDialog create(String dialogTitle, List<Item> items) {
        return new OptionsBottomSheetDialog(dialogTitle, items);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.options_bottom_sheet_dialog_layout, container, false);
        TextView title = view.findViewById(R.id.options_title);

        title.setText(dialogTitle);

        RecyclerView optionsList = view.findViewById(R.id.options_list);
        ItemsAdapter<Item> adapter = new ItemsAdapter<>(items);
        registerAdapter(adapter);
        optionsList.setAdapter(adapter);

        return view;

    }

    private void registerAdapter(ItemsAdapter<Item> adapter) {
        adapter.registerDelegate(PropertyType.KEY_VALUE.getCode(), new BasicItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.KEY_LIST.getCode(), new ListItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.CONFIGURABLE.getCode(), new ConfigurableItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.MESSAGE.getCode(), new MessageItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.CONDENSED.getCode(), new CondensedItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.SIMPLE_CONDENSED.getCode(), new SimpleCondensedItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.LOGO.getCode(), new LogoItemViewHolderDelegate());
        adapter.registerDelegate(PropertyType.GRID_ITEM.getCode(), new GridItemViewHolderDelegate());
    }
}
