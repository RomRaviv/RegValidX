package com.example.regvalidx;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ViewsValidator {
    private Activity activity;

    public ViewsValidator(Activity activity) {
        this.activity = activity;
    }

    public boolean validate() throws IllegalArgumentException {
        List<View> views = getAllViews();
        for (View view : views) {
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                int inputType = editText.getInputType();

                if (inputType == InputType.TYPE_CLASS_TEXT) {
                    // Check for non-empty text field
                    if (TextUtils.isEmpty(editText.getText())) {
                        String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                        throw new IllegalArgumentException("Invalid " + fieldName + ": field cannot be empty");
                    }
                } else if ((inputType & InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    // Check for password field
                    if (TextUtils.isEmpty(editText.getText())) {
                        String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                        throw new IllegalArgumentException("Invalid " + fieldName + ": password cannot be empty");
                    }
                } else if ((inputType & InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                    // Check for email field
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches()) {
                        String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                        throw new IllegalArgumentException("Invalid " + fieldName + ": please enter a valid email address");
                    }
                } else if ((inputType & InputType.TYPE_CLASS_PHONE) == InputType.TYPE_CLASS_PHONE) {
                    // Check for phone number field
                    if (!android.util.Patterns.PHONE.matcher(editText.getText()).matches()) {
                        String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                        throw new IllegalArgumentException("Invalid " + fieldName + ": please enter a valid phone number");
                    }
                } else if ((inputType & InputType.TYPE_CLASS_NUMBER) == InputType.TYPE_CLASS_NUMBER) {
                    // Check for digits only field
                    if (TextUtils.isEmpty(editText.getText())) {
                        String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                        throw new IllegalArgumentException("Invalid " + fieldName + ": digits cannot be empty");
                    } else if (!TextUtils.isDigitsOnly(editText.getText())) {
                        String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                        throw new IllegalArgumentException("Invalid " + fieldName + ": please enter digits only");
                    }
                } else {
                    // Unsupported input type
                    String fieldName = activity.getResources().getResourceEntryName(editText.getId());
                    throw new IllegalArgumentException("Invalid " + fieldName + ": unsupported input type");
                }
            }
        }
        return true;
    }

    public List<View> getAllViews() {
        List<View> views = new ArrayList<>();
        View rootView = activity.getWindow().getDecorView();
        addViews(views, rootView);
        return views;
    }

    private void addViews(List<View> views, View parentView) {
        if (parentView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parentView;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                views.add(childView);
                addViews(views, childView);
            }
        } else {
            views.add(parentView);
        }
    }
}
