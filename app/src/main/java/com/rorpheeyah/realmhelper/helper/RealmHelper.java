package com.rorpheeyah.realmhelper.helper;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @since 2021.11.08
 * @author Matt
 * Realm Database Helper
 */
@SuppressWarnings("unused")
public class RealmHelper {

    /**
     * Save or Update object
     * @param object object extend RealmObject
     */
    @SuppressWarnings("unused")
    public static <T extends RealmObject> void save(T object) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(object);
            realm.commitTransaction();
        }
    }

    /**
     * Save or Update list of objects
     * @param object object extend RealmObject
     */
    @SuppressWarnings("unused")
    public static <T extends RealmObject> void saveList(List<T> object) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(object);
            realm.commitTransaction();
        }
    }

    /**
     * Delete all records
     * @param clazz specific class extend RealmObject
     */
    @SuppressWarnings("unused")
    public static <T extends RealmObject> void deleteAll(Class<T> clazz){
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.delete(clazz);
            realm.commitTransaction();
        }
    }

    /**
     * Find record
     * @param where target object. eg: realm.where(Pet.class).equalTo("id", pet.getId())
     */
    @SuppressWarnings("unused")
    public static <T extends RealmObject> void deleteWhere(@NonNull Class<T> clazz, @NonNull OnQuerySearch<T> where){
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            RealmQuery<T> query = realm.where(clazz);
            query = where.onQuery(query);
            RealmResults<T> listResults = query.findAll();
            if (listResults != null && listResults.size() > 0)
                listResults.deleteAllFromRealm();
            realm.commitTransaction();
        }
    }

    @SuppressWarnings("unused")
    public static <T extends RealmObject> T findFirst(@NonNull Class<T> clazz){
        return findFirst(clazz, null);
    }

    @Nullable
    public static <T extends RealmObject> T findFirst(@NonNull Class<T> clazz, OnQuerySearch<T> querySearch){
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmQuery<T> query = realm.where(clazz);
            if (querySearch != null)
                query = querySearch.onQuery(query);
            T data = query.findFirst();
            if (data != null) return realm.copyFromRealm(data);
            return null;
        }
    }

    @SuppressWarnings("unused")
    public static <T extends RealmObject> List<T> findAll(Class<T> clazz){
        return findAll(clazz, null);
    }

    public static <T extends RealmObject> List<T> findAll(Class<T> clazz, String sort){
        return findAll(clazz, sort, -1);
    }

    public static <T extends RealmObject> List<T> findAll(Class<T> clazz, String sort, int limit){
        return findAll(clazz, sort, limit, null);
    }

    public static <T extends RealmObject> List<T> findAll(Class<T> clazz, String sort, int limit, OnQuerySearch<T> querySearch){
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmQuery<T> query = realm.where(clazz);
            if (querySearch != null)
                query = querySearch.onQuery(query);
            List<T> listData = TextUtils.isEmpty(sort) ? query.findAll() : query.sort(sort, Sort.DESCENDING).findAll();
            List<T> listResult = new ArrayList<>();
            if (listData != null && listData.size() > 0) {
                listResult = realm.copyFromRealm(listData);
            }
            if (limit > 0 && limit < listResult.size()) return listResult.subList(0, limit);
            return listResult;
        }
    }

    public interface  OnQuerySearch<E extends RealmObject>{
        RealmQuery<E> onQuery(RealmQuery<E> query);
    }
}
