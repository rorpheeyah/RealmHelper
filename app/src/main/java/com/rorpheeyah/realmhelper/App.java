package com.rorpheeyah.realmhelper;

import android.app.Application;

import com.rorpheeyah.realmhelper.pet.Pet;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class App extends Application {
    private static App instance;
    public static App getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Realm.init(this.getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .name("Pet").name("User")
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .migration((realm, oldVersion, newVersion) -> {
                    RealmSchema schema = realm.getSchema();
                    if(oldVersion == 1){
                        RealmObjectSchema saveMessage = schema.get(Pet.class.getSimpleName());
                        if(saveMessage == null){
                            saveMessage = schema.create(Pet.class.getSimpleName());
                            saveMessage.addField("dob", Long.class);
                            oldVersion++;
                        }
                    }
                })
                .build();
        Realm.setDefaultConfiguration(config);
    }
}