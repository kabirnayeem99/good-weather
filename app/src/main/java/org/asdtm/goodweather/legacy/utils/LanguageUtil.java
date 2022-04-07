package org.asdtm.goodweather.legacy.utils;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import java.util.Locale;

public class LanguageUtil {


    public static void setLanguage(final ContextWrapper contextWrapper, String locale) {
        Locale sLocale;
        if (TextUtils.isEmpty(locale)) {
            sLocale = Locale.getDefault();
        } else {
            String[] localeParts = locale.split("_");
            if (localeParts.length > 1) {
                sLocale = new Locale(localeParts[0], localeParts[1]);
            } else {
                sLocale = new Locale(locale);
            }
        }

        Resources resources = contextWrapper.getBaseContext().getResources();
        Configuration configuration = resources.getConfiguration();
        Locale.setDefault(sLocale);
        configuration.setLocale(sLocale);

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static void forceChangeLanguage(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent == null) {
            return;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.finish();
        activity.overridePendingTransition(0, 15);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    public static String getLanguageName(String locale) {
        if (TextUtils.isEmpty(locale)) {
            locale = Locale.getDefault().toString();
        }
        if (locale.contains("_")) {
            return locale.split("_")[0];
        }
        return locale;
    }
}