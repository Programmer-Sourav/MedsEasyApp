package com.portfolio.apps.pharmeasymeds;

import android.util.Log;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}