package com.hjy.baseutil.enumclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 9:07
 * 描述: 自建Enum -- 官方Enum消耗内存过大，不推荐使用
 */
public abstract class Enum<K, V> implements EnumImp<K, V> {

    /**
     * 添加所有Enum
     *
     * @return
     */
    protected abstract Map<K, V> addEnum();

    private Map<K, V> kvMap;

    private Map<K, V> initEnum() {
        if (kvMap == null) {
            kvMap = addEnum();
        }
        return kvMap;
    }

    /**
     * 根据key查value
     *
     * @param
     * @return
     */
    @Override
    public V getValue(K k) {
        Map<K, V> kvMap = initEnum();
        return kvMap.get(k);

    }

    /**
     * 根据value查 key
     *
     * @param
     * @return
     */
    @Override
    public K getKey(V v) {
        Map<K, V> kvMap = initEnum();
        K _k = null;
        for (K k : kvMap.keySet()) {
            if (kvMap.get(k).equals(v)) {
                _k = k;
                break;
            }
        }
        return _k;
    }

    @Override
    public List<K> getKey() {
        Map<K, V> kvMap = initEnum();
        List<K> ks = new ArrayList<>(kvMap.keySet());
        return ks;
    }

    @Override
    public List<V> getValue() {
        Map<K, V> kvMap = initEnum();
        List<V> vs = new ArrayList<>(kvMap.values());
        return vs;
    }

    /**
     * 根据key查脚标
     *
     * @param
     * @return
     */
    @Override
    public int indexOfK(K k) {
        Map<K, V> kvMap = initEnum();
        int index = -1;
        Object[] toArray = kvMap.keySet().toArray();
        for (int i = 0; i < toArray.length; i++) {
            if (toArray[i].equals(k)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 根据value查脚标
     *
     * @param
     * @return
     */
    @Override
    public int indexOfV(V v) {
        Map<K, V> kvMap = initEnum();
        int index = -1;
        Object[] toArray = kvMap.values().toArray();
        for (int i = 0; i < toArray.length; i++) {

            if (toArray[i].equals(v)) {
                index = i;
                break;
            }


        }
        return index;
    }

    @Override
    public int size() {
        Map<K, V> kvMap = initEnum();
        return kvMap.size();
    }

    @Override
    public String toString() {
        Map<K, V> kvMap = initEnum();
        return kvMap.toString();
    }


}


