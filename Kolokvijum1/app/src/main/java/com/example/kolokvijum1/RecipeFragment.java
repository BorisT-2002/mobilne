package com.example.kolokvijum1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeFragment extends Fragment {

    public static final String ACTION_RECIPE_ADDED = "com.example.kolokvijum1.RECIPE_ADDED";
    public static final String ACTION_CAMERA_GRANTED = "com.example.kolokvijum1.CAMERA_GRANTED";
    public static final String EXTRA_PREP_TIME = "EXTRA_PREP_TIME";

    private RecipeAdapter adapter;
    private ImageView imageView;

    private final BroadcastReceiver cameraReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (imageView != null) {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        imageView = view.findViewById(R.id.image_view);
        Button btnAdd = view.findViewById(R.id.btn_add);

        adapter = new RecipeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> showAddRecipeDialog());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ACTION_CAMERA_GRANTED);
        LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(cameraReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(requireContext())
                .unregisterReceiver(cameraReceiver);
    }

    private void showAddRecipeDialog() {
        View dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_add_recipe, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        EditText etName = dialogView.findViewById(R.id.et_recipe_name);
        EditText etPrepTime = dialogView.findViewById(R.id.et_prep_time);
        CheckBox cbFavorite = dialogView.findViewById(R.id.cb_favorite);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnConfirm.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String prepTimeStr = etPrepTime.getText().toString().trim();

            if (name.isEmpty() || prepTimeStr.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Unesite sve podatke!", Toast.LENGTH_SHORT).show();
                return;
            }

            int prepTime = Integer.parseInt(prepTimeStr);
            boolean isFavorite = cbFavorite.isChecked();

            Recipe recipe = new Recipe(name, prepTime, isFavorite);
            adapter.addRecipe(recipe);

            
            Intent broadcastIntent = new Intent(ACTION_RECIPE_ADDED);
            broadcastIntent.setPackage(requireContext().getPackageName());
            broadcastIntent.putExtra(EXTRA_PREP_TIME, prepTime);
            requireContext().sendBroadcast(broadcastIntent);

            dialog.dismiss();
        });

        dialog.show();
    }
}
