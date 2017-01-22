package com.dagger.todo;

import android.app.Application;

/**
 * Created by Harshit on 23/01/17.
 */

public class TodoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/" + FontsOverride.FONT_ROBOTO);
    }
}
