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

package com.azureyjt.smartrest.common.model;

import java.util.Map;
import java.util.Objects;

/**
 * Data model of the customized REST API.
 */
public class CustomizedRestApiConfig {

    private String apiUri;

    private String tableName;

    private String idColumnName;

    private Map<String, String> attributeMapping;

    /**
     * Constructor of CustomizedRestApiConfig.
     */
    public CustomizedRestApiConfig() {
    }

    /**
     * Constructor of CustomizedRestApiConfig with fields.
     */
    public CustomizedRestApiConfig(
            String apiUri,
            String tableName,
            String idColumnName,
            Map<String, String> attributeMapping) {
        this.apiUri = apiUri;
        this.tableName = tableName;
        this.idColumnName = idColumnName;
        this.attributeMapping = attributeMapping;
    }

    /**
     * Override equals interface.
     *
     * @param o Object comapared to the current instance.
     * @return {@code true} if the two instance is equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomizedRestApiConfig that = (CustomizedRestApiConfig) o;
        return Objects.equals(apiUri, that.apiUri)
                && Objects.equals(tableName, that.tableName)
                && Objects.equals(idColumnName, that.idColumnName)
                && Objects.equals(attributeMapping, that.attributeMapping);
    }

    /**
     * Override hashcode interface.
     *
     * @return Hashcode generated by the default hash algorithm.
     */
    @Override
    public int hashCode() {
        return Objects.hash(apiUri, tableName, idColumnName, attributeMapping);
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    public Map<String, String> getAttributeMapping() {
        return attributeMapping;
    }

    public void setAttributeMapping(Map<String, String> attributeMapping) {
        this.attributeMapping = attributeMapping;
    }
}
