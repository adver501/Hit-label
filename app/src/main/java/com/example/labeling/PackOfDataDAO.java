package com.example.labeling;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class PackOfDataDAO {
    public static Realm realm;

    public PackOfDataDAO(Context context){
        realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void save(final PackOfData packOfData, final Context context){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.beginTransaction();
                PackOfData realmObj = realm.createObject(PackOfData.class, packOfData.getNoPack());
                realmObj.setText(packOfData.getText());
                realmObj.setImageUri(packOfData.getImageUri());
                realmObj.setVideoUri(packOfData.getVideoUri());
                realmObj.setLabels(packOfData.getLabels());
                realm.commitTransaction();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "Saved successfully", Toast.LENGTH_LONG).show();
                Log.i("Saved", packOfData.getText().get(0).getText());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public RealmResults<PackOfData> findall(){
        RealmResults<PackOfData> realmResults = realm.where(PackOfData.class).findAll();
        return realmResults;
    }

    public PackOfData findByNoPack(int noPack){
        PackOfData packOfData = realm.where(PackOfData.class).equalTo("noPack", noPack).findFirst();
        return packOfData;
    }

    public void close(){

        realm.close();
    }
}
