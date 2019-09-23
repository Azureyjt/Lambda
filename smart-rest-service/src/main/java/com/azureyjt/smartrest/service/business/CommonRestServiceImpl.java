/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.azureyjt.smartrest.service.business;

import com.azureyjt.smartrest.common.model.CustomizedRestApiConfig;
import com.azureyjt.smartrest.common.utility.JsonUtils;
import com.azureyjt.smartrest.common.utility.UrlUtils;
import com.azureyjt.smartrest.dao.CommonDao;
import com.azureyjt.smartrest.service.CommonRestService;
import com.azureyjt.smartrest.service.config.CacheProperties;
import com.azureyjt.smartrest.service.exception.NoSuchResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Map;

/**
 * CommonRestService implementation class.
 */
public class CommonRestServiceImpl implements CommonRestService {

    private final CommonDao commonDao;

    private final CacheManager cacheManager;

    private final CacheProperties cacheProperties;

    /**
     * Constructor of CommonRestServiceImpl.
     *
     * @param commonDao CommonDao bean.
     */
    @Autowired
    public CommonRestServiceImpl(CommonDao commonDao,
                                 CacheManager cacheManager,
                                 CacheProperties cacheProperties) {
        this.commonDao = commonDao;
        this.cacheManager = cacheManager;
        this.cacheProperties = cacheProperties;
    }

    /**
     * Execute GET request.
     *
     * @param uri Request uri.
     * @return Response body data.
     */
    @Override
    public String executeGet(String uri) throws NoSuchResourceException {
        return UrlUtils.isBaseUri(uri) ? executeGetAll(uri) : executeGetById(uri);
    }

    /**
     * Execute POST request.
     *
     * @param uri  Request uri.
     * @param body Request body.
     * @return Response body data.
     */
    @Override
    public String executePost(String uri, String body) {
        return null;
    }

    /**
     * Execute PUT request.
     *
     * @param uri  Request uri.
     * @param body Request body.
     * @return Response body data.
     */
    @Override
    public String executePut(String uri, String body) {
        return null;
    }

    /**
     * Execute DELETE request.
     *
     * @param uri Request uri.
     * @return Response body data.
     */
    @Override
    public String executeDelete(String uri) {
        return null;
    }

    /**
     * Execute GET all request.
     *
     * @param uri Request uri.
     * @return Response body data.
     */
    private String executeGetAll(String uri) throws NoSuchResourceException {
        CustomizedRestApiConfig apiConfig = getApiConfig(uri);
        if (apiConfig == null) {
            throw new NoSuchResourceException();
        }
        commonDao.initDbSchema(apiConfig.getTableName(), apiConfig.getIdColumnName());
        List<Map<String, Object>> queryResult = commonDao.getAll();
        String responseData = JsonUtils.toJson(queryResult);
        return responseData;
    }

    /**
     * Execute GET by id request.
     *
     * @param uri Request uri.
     * @return Response body data.
     */
    private String executeGetById(String uri) throws NoSuchResourceException {
        CustomizedRestApiConfig apiConfig = getApiConfig(uri);
        if (apiConfig == null) {
            throw new NoSuchResourceException();
        }
        commonDao.initDbSchema(apiConfig.getTableName(), apiConfig.getIdColumnName());
        String id = UrlUtils.getIdentity(uri);
        Map<String, Object> qureyResult = commonDao.getById(id);
        String responseData = JsonUtils.toJson(qureyResult);
        return responseData;
    }

    /**
     * Get pre-configured api setting from cache.
     *
     * @param uri Request uri.
     * @return CustomizedRestApiConfig.
     */
    private CustomizedRestApiConfig getApiConfig(String uri) {
        Cache cache = cacheManager.getCache(cacheProperties.getApiMapName());
        String baseUri = UrlUtils.getBaseUri(uri);
        CustomizedRestApiConfig apiConfig = (CustomizedRestApiConfig) cache.get(baseUri);
        return apiConfig;
    }
}
