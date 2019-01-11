package com.example.zqd.itemtouch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v4.content.SharedPreferencesCompat;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SPUtil {

    private static SPUtil sEasySP;
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;
    private static SharedPreferencesCompat.EditorCompat editorCompat = SharedPreferencesCompat.EditorCompat.getInstance();

    private static final String DEFAULT_SP_NAME = "SharedData";
    private static final int DEFAULT_INT = 0;
    private static final float DEFAULT_FLOAT = 0.0f;
    private static final String DEFAULT_STRING = "";
    private static final boolean DEFAULT_BOOLEAN = false;
    private static final Set<String> DEFAULT_STRING_SET = new HashSet<>(0);

    private static String mCurSPName = DEFAULT_SP_NAME;
    private static Context mContext;

    private SPUtil(Context context) {
        this(context, DEFAULT_SP_NAME);
    }

    private SPUtil(Context context, String spName) {
        mContext = context.getApplicationContext();
        sSharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
        mCurSPName = spName;
    }

    public static SPUtil init(Context context) {
        if (sEasySP == null || !mCurSPName.equals(DEFAULT_SP_NAME)) {
            sEasySP = new SPUtil(context);
        }
        return sEasySP;
    }

    public static SPUtil init(String spName) {
        if (sEasySP == null) {
            sEasySP = new SPUtil(BaseApplication.getApp(), spName);
        } else if (!spName.equals(mCurSPName)) {
            sEasySP = new SPUtil(BaseApplication.getApp(), spName);
        }
        return sEasySP;
    }

    public SPUtil put(int key, Object value) {
        return put(mContext.getString(key), value);
    }

    public SPUtil put(String key, Object value) {
        if (value instanceof String) {
            sEditor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            sEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            sEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            sEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            sEditor.putLong(key, (Long) value);
        } else {
            sEditor.putString(key, value.toString());
        }
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public Object get(int key, Object defaultObject) {
        return get(mContext.getString(key), defaultObject);
    }

    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sSharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sSharedPreferences.getInt(key, (int) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sSharedPreferences.getBoolean(key, (boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sSharedPreferences.getFloat(key, (float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sSharedPreferences.getLong(key, (long) defaultObject);
        }
        return null;
    }

    public SPUtil putInt(String key, int value) {
        sEditor.putInt(key, value);
        editorCompat.apply(sEditor);
        return this;
    }

    public SPUtil putInt(int key, int value) {
        return putInt(mContext.getString(key), value);
    }

    public int getInt(int key) {
        return getInt(mContext.getString(key));
    }

    public int getInt(int key, int defValue) {
        return getInt(mContext.getString(key), defValue);
    }

    public int getInt(String key) {
        return getInt(key, DEFAULT_INT);
    }


    public int getInt(String key, int defValue) {
        return sSharedPreferences.getInt(key, defValue);
    }

    public SPUtil putFloat(int key, float value) {
        return putFloat(mContext.getString(key), value);
    }

    public SPUtil putFloat(String key, float value) {
        sEditor.putFloat(key, value);
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public float getFloat(String key) {
        return getFloat(key, DEFAULT_FLOAT);
    }

    public float getFloat(String key, float defValue) {
        return sSharedPreferences.getFloat(key, defValue);
    }

    public float getFloat(int key) {
        return getFloat(mContext.getString(key));
    }

    public float getFloat(int key, float defValue) {
        return getFloat(mContext.getString(key), defValue);
    }

    public SPUtil putLong(int key, long value) {
        return putLong(mContext.getString(key), value);
    }

    public SPUtil putLong(String key, long value) {
        sEditor.putLong(key, value);
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public long getLong(String key) {
        return getLong(key, DEFAULT_INT);
    }

    public long getLong(String key, long defValue) {
        return sSharedPreferences.getLong(key, defValue);
    }

    public long getLong(int key) {
        return getLong(mContext.getString(key));
    }

    public long getLong(int key, long defValue) {
        return getLong(mContext.getString(key), defValue);
    }

    public SPUtil putString(@StringRes int key, String value) {
        return putString(mContext.getString(key), value);
    }

    public SPUtil putString(String key, String value) {
        sEditor.putString(key, value);
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public String getString(String key) {
        return getString(key, DEFAULT_STRING);
    }

    public String getString(String key, String defValue) {
        return sSharedPreferences.getString(key, defValue);
    }

    public String getString(int key) {
        return getString(mContext.getString(key), DEFAULT_STRING);
    }

    public String getString(int key, String defValue) {
        return getString(mContext.getString(key), defValue);
    }

    public SPUtil putBoolean(int key, boolean value) {
        return putBoolean(mContext.getString(key), value);
    }

    public SPUtil putBoolean(String key, boolean value) {
        sEditor.putBoolean(key, value);
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sSharedPreferences.getBoolean(key, defValue);
    }

    public boolean getBoolean(int key) {
        return getBoolean(mContext.getString(key));
    }

    public boolean getBoolean(int key, boolean defValue) {
        return getBoolean(mContext.getString(key), defValue);
    }

    public SPUtil putStringSet(int key, Set<String> value) {
        return putStringSet(mContext.getString(key), value);
    }

    public SPUtil putStringSet(String key, Set<String> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            sEditor.putStringSet(key, value);
            editorCompat.apply(sEditor);
        }
        return sEasySP;
    }

    public Set<String> getStringSet(String key) {
        return getStringSet(key, DEFAULT_STRING_SET);
    }


    public Set<String> getStringSet(String key, Set<String> defValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return sSharedPreferences.getStringSet(key, defValue);
        } else {
            return DEFAULT_STRING_SET;
        }
    }

    public Set<String> getStringSet(int key) {
        return getStringSet(mContext.getString(key));
    }

    public Set<String> getStringSet(int key, Set<String> defValue) {
        return getStringSet(mContext.getString(key), defValue);
    }


    public boolean contains(String key) {
        return sSharedPreferences.contains(key);
    }

    public boolean contains(int key) {
        return contains(mContext.getString(key));
    }

    public Map<String, ?> getAll() {
        return sSharedPreferences.getAll();
    }

    public SPUtil remove(int key) {
        return remove(mContext.getString(key));
    }

    public SPUtil remove(String key) {
        sEditor.remove(key);
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public SPUtil clear() {
        sEditor.clear();
        editorCompat.apply(sEditor);
        return sEasySP;
    }

    public SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }
}
