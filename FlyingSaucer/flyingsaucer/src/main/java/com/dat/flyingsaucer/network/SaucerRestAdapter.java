package com.dat.flyingsaucer.network;

import com.dat.flyingsaucer.data.dao.BaseDao;
import com.dat.flyingsaucer.data.dao.BeerDao;
import com.dat.flyingsaucer.data.dao.StoreBeerItemDao;
import com.dat.flyingsaucer.data.dao.StoreDao;
import com.dat.flyingsaucer.data.model.Beer;
import com.dat.flyingsaucer.data.model.Store;
import com.dat.flyingsaucer.data.model.StoreBeerItem;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by davidthacker on 5/23/15.
 */
public class SaucerRestAdapter {

    private static SaucerService mService;

    private static SaucerService getInstance() {
        if (mService == null) {
            initialize();
        }

        return mService;
    }

    private static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(SaucerService.BASE_ENDPOINT).build();
        mService = restAdapter.create(SaucerService.class);
    }

    public static void getAllStores(final RestCallback<StoreDao> callback){

        getInstance().listStores(new Callback<List<Store>>() {
            @Override
            public void success(List<Store> stores, Response response) {

                if (stores != null) {

                    StoreDao dao = new StoreDao();
                    dao.modelList = stores;

                    callback.onSuccess(dao);

                } else {
                    callback.onSuccess(null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onError();
            }
        });
    }


    public static void getBeer(String beerId, final RestCallback<BeerDao> callback) {

        getInstance().getBeer(beerId, new Callback<Beer>() {
            @Override
            public void success(Beer beer, Response response) {

                if (beer != null) {

                    BeerDao dao = new BeerDao();
                    dao.modelList.add(beer);

                    callback.onSuccess(dao);

                } else {
                    callback.onSuccess(null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onError();
            }
        });
    }

    public static void getStoreBeerItems(String storeSlug, final RestCallback<StoreBeerItemDao> callback){

        getInstance().listStoreBeers(storeSlug, new Callback<List<StoreBeerItem>>() {

            @Override
            public void success(List<StoreBeerItem> storeBeerItems, Response response) {

                if (storeBeerItems != null) {

                    StoreBeerItemDao dao = new StoreBeerItemDao();
                    dao.modelList = storeBeerItems;

                    callback.onSuccess(dao);

                } else {
                    callback.onSuccess(null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onError();
            }

        });
    }


    public interface RestCallback<T extends BaseDao>{

        void onSuccess(T dao);
        void onError();

    }
}
