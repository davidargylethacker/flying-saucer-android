package com.dat.flyingsaucer.data.dao;

import com.dat.flyingsaucer.data.model.BaseModel;
import com.dat.flyingsaucer.util.extensions.FSArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidthacker on 5/23/15.
 */
public abstract class BaseDao<T extends BaseModel> {

    public List<T> modelList;

    public BaseDao() {
        modelList = new ArrayList<>();
    }

}
