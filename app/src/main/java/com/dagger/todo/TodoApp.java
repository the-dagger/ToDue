package com.dagger.todo;

import android.app.Application;
import android.content.Context;

import com.dagger.todo.utils.FontsOverride;

/**
 * Created by Harshit on 23/01/17
 */

public class TodoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/" + FontsOverride.FONT_ROBOTO);

    }

    public static void setFontOnFragment(Context context){
        FontsOverride.setDefaultFont(context, "SANS_SERIF", "fonts/" + FontsOverride.FONT_ROBOTO);
    }
}
