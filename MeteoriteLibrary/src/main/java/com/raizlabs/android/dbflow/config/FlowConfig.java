package com.raizlabs.android.dbflow.config;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description: The main configuration instance for DBFlow. This
 */
public final class FlowConfig {

    private final Set<Class<? extends DatabaseHolder>> databaseHolders;
    private final Map<Class<?>, DatabaseConfig> databaseConfigMap;
    private final Context context;
    private final boolean openDatabasesOnInit;
    private final String databaseDir;

    FlowConfig(Builder builder) {
        databaseHolders = Collections.unmodifiableSet(builder.databaseHolders);
        databaseConfigMap = builder.databaseConfigMap;
        context = builder.context;
        openDatabasesOnInit = builder.openDatabasesOnInit;
        databaseDir=builder.databaseDir;
    }

    public String getDatabaseDir() {
        return databaseDir;
    }

    @NonNull
    public Set<Class<? extends DatabaseHolder>> databaseHolders() {
        return databaseHolders;
    }

    @NonNull
    public Map<Class<?>, DatabaseConfig> databaseConfigMap() {
        return databaseConfigMap;
    }

    @Nullable
    public DatabaseConfig getConfigForDatabase(@NonNull Class<?> databaseClass) {
        return databaseConfigMap().get(databaseClass);
    }

    @NonNull
    public Context getContext() {
        return context;
    }

    public boolean openDatabasesOnInit() {
        return openDatabasesOnInit;
    }

    public static class Builder {

        final Context context;
        Set<Class<? extends DatabaseHolder>> databaseHolders = new HashSet<>();
        final Map<Class<?>, DatabaseConfig> databaseConfigMap = new HashMap<>();
        boolean openDatabasesOnInit;
        String databaseDir;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        public Builder setDatabaseDir(String databaseDir) {
            this.databaseDir = databaseDir;
            return this;
        }

        @NonNull
        public Builder addDatabaseHolder(@NonNull Class<? extends DatabaseHolder> databaseHolderClass) {
            databaseHolders.add(databaseHolderClass);
            return this;
        }

        @NonNull
        public Builder addDatabaseConfig(@NonNull DatabaseConfig databaseConfig) {
            databaseConfigMap.put(databaseConfig.databaseClass(), databaseConfig);
            return this;
        }

        /**
         * @param openDatabasesOnInit true if we want all databases open.
         * @return True to open all associated databases in DBFlow on calling of {@link FlowManager#init(FlowConfig)}
         */
        @NonNull
        public Builder openDatabasesOnInit(boolean openDatabasesOnInit) {
            this.openDatabasesOnInit = openDatabasesOnInit;
            return this;
        }

        @NonNull
        public FlowConfig build() {
            return new FlowConfig(this);
        }
    }
}
