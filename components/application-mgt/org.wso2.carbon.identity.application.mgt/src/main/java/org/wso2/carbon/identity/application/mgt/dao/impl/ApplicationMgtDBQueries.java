/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.org).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.application.mgt.dao.impl;

/**
 * This class contains default SQL queries
 * <p/>
 * TODO : Make the queries configurable from a file TODO : Use transactions and joins
 * SQL queries are internal implementation of the DAO layer and should not be exposed to components outside
 */
public class ApplicationMgtDBQueries {

    // STORE Queries
    public static final String STORE_BASIC_APPINFO = "INSERT INTO SP_APP (TENANT_ID, APP_NAME, USER_STORE, USERNAME, " +
            "DESCRIPTION, AUTH_TYPE, IS_USE_TENANT_DOMAIN_SUBJECT, ENABLE_AUTHORIZATION,IS_USE_USER_DOMAIN_SUBJECT, " +
            "UUID, IMAGE_URL, ACCESS_URL) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_BASIC_APPINFO = "UPDATE SP_APP SET APP_NAME=:APP_NAME;, " +
            "DESCRIPTION=:DESCRIPTION;, IS_SAAS_APP=:IS_SAAS_APP;, IS_DISCOVERABLE=:IS_DISCOVERABLE;, " +
            "IMAGE_URL=:IMAGE_URL;, ACCESS_URL=:ACCESS_URL; WHERE TENANT_ID=:TENANT_ID; AND ID=:ID;";
    public static final String UPDATE_BASIC_APPINFO_WITH_OWNER_UPDATE = "UPDATE SP_APP SET APP_NAME=:APP_NAME;, " +
            "DESCRIPTION=:DESCRIPTION;, IS_SAAS_APP=:IS_SAAS_APP;, IS_DISCOVERABLE=:IS_DISCOVERABLE;, " +
            "USERNAME=:USERNAME;, USER_STORE=:USER_STORE;, IMAGE_URL=:IMAGE_URL;, " +
            "ACCESS_URL=:ACCESS_URL; WHERE TENANT_ID=:TENANT_ID; AND ID=:ID;";
    public static final String UPDATE_BASIC_APPINFO_WITH_ROLE_CLAIM = "UPDATE SP_APP SET ROLE_CLAIM=? WHERE TENANT_ID" +
            "= ? AND ID = ?";
    public static final String UPDATE_BASIC_APPINFO_WITH_CLAIM_DIALEECT_AND_SEND_LOCAL_SUB_ID =
            "UPDATE SP_APP SET IS_LOCAL_CLAIM_DIALECT=?, IS_SEND_LOCAL_SUBJECT_ID=? WHERE TENANT_ID= ? AND ID = ?";
    public static final String UPDATE_BASIC_APPINFO_WITH_LOCAL_AND_OUTBOUND_CONFIGURATION = "UPDATE SP_APP SET " +
            "IS_SEND_AUTH_LIST_OF_IDPS=?, IS_USE_TENANT_DOMAIN_SUBJECT=?, IS_USE_USER_DOMAIN_SUBJECT=?, " +
            "ENABLE_AUTHORIZATION=?, SUBJECT_CLAIM_URI=?, AUTH_TYPE=? WHERE TENANT_ID= ? AND ID = ?";
    public static final String UPDATE_CERTIFICATE = "UPDATE IDN_CERTIFICATE SET CERTIFICATE_IN_PEM = ? WHERE " +
            "ID = ?";
    public static final String ADD_CERTIFICATE = "INSERT INTO IDN_CERTIFICATE(NAME, CERTIFICATE_IN_PEM, TENANT_ID) " +
            "VALUES(?, ?, ?)";
    public static final String UPDATE_BASIC_APPINFO_WITH_PRO_PROPERTIES = "UPDATE SP_APP SET PROVISIONING_USERSTORE_" +
            "DOMAIN=?, IS_DUMB_MODE=? WHERE TENANT_ID= ? AND ID = ?";
    public static final String UPDATE_SP_PERMISSIONS = "UPDATE UM_PERMISSION SET UM_RESOURCE_ID=? WHERE UM_ID=?";

    public static final String STORE_CLIENT_INFO = "INSERT INTO SP_INBOUND_AUTH (TENANT_ID, INBOUND_AUTH_KEY," +
            "INBOUND_AUTH_TYPE,PROP_NAME, PROP_VALUE, APP_ID,INBOUND_CONFIG_TYPE) VALUES (?,?,?,?,?,?,?)";
    public static final String STORE_STEP_INFO = "INSERT INTO SP_AUTH_STEP (TENANT_ID, STEP_ORDER, APP_ID, " +
            "IS_SUBJECT_STEP, IS_ATTRIBUTE_STEP) VALUES (?,?,?,?,?)";
    public static final String STORE_STEP_IDP_AUTH = "INSERT INTO SP_FEDERATED_IDP (ID, TENANT_ID, AUTHENTICATOR_ID) " +
            "VALUES (?,?,?)";
    public static final String STORE_SP_AUTH_SCRIPT = "INSERT INTO SP_AUTH_SCRIPT (TENANT_ID, APP_ID, TYPE, " +
            " CONTENT, IS_ENABLED)" +
            " VALUES (?,?,?,?,?)";
    public static final String STORE_CLAIM_MAPPING = "INSERT INTO SP_CLAIM_MAPPING (TENANT_ID, IDP_CLAIM, SP_CLAIM, " +
            "APP_ID, IS_REQUESTED, IS_MANDATORY, DEFAULT_VALUE) VALUES (?,?,?,?,?,?,?)";
    public static final String STORE_ROLE_MAPPING =
            "INSERT INTO SP_ROLE_MAPPING (TENANT_ID, IDP_ROLE, SP_ROLE, APP_ID)" +
                    " VALUES (?,?,?,?)";
    public static final String STORE_REQ_PATH_AUTHENTICATORS = "INSERT INTO SP_REQ_PATH_AUTHENTICATOR (TENANT_ID, " +
            "AUTHENTICATOR_NAME, APP_ID) VALUES (?,?,?)";
    public static final String STORE_PRO_CONNECTORS = "INSERT INTO SP_PROVISIONING_CONNECTOR (TENANT_ID, IDP_NAME, " +
            "CONNECTOR_NAME, APP_ID,IS_JIT_ENABLED, BLOCKING, RULE_ENABLED) VALUES (?,?,?,?,?,?,?)";

    // LOAD Queries
    public static final String LOAD_APP_ID_BY_APP_NAME = "SELECT ID FROM SP_APP WHERE APP_NAME = ? AND TENANT_ID = ?";
    public static final String LOAD_APP_NAMES_BY_TENANT = "SELECT ID, APP_NAME, DESCRIPTION FROM SP_APP WHERE " +
            "TENANT_ID = ? AND APP_NAME != ? ORDER BY ID DESC";
    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER = "SELECT SP_APP.ID, SP_APP.APP_NAME, " +
            "SP_APP.DESCRIPTION FROM SP_APP LEFT JOIN SP_INBOUND_AUTH ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE " +
            "SP_APP.TENANT_ID = ? AND SP_APP.APP_NAME != ? AND (%s) ORDER BY SP_APP.ID DESC";
    public static final String LOAD_APP_COUNT_BY_TENANT = "SELECT COUNT(*) FROM SP_APP WHERE TENANT_ID = ? AND " +
            "APP_NAME != ? ";

    public static final String LOAD_APP_COUNT_BY_TENANT_AND_FILTER = "SELECT COUNT(SP_APP.APP_NAME) FROM SP_APP " +
            "LEFT JOIN SP_INBOUND_AUTH ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE SP_APP.TENANT_ID = ? " +
            "AND SP_APP.APP_NAME != ? AND (%s)";
    public static final String LOAD_APP_BY_TENANT_AND_NAME = "SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, " +
            "ACCESS_URL, IS_DISCOVERABLE, USERNAME, USER_STORE, TENANT_ID FROM SP_APP WHERE TENANT_ID = :TENANT_ID; " +
            "AND APP_NAME = :APP_NAME;";

    // Load application basic information for listing with pagination
    public static final String LOAD_APP_NAMES_BY_TENANT_MYSQL = "SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, " +
            "ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM SP_APP WHERE TENANT_ID = ? AND APP_NAME != ? ORDER BY " +
            "ID DESC LIMIT ?, ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER_MYSQL = "SELECT SP_APP.ID, SP_APP.APP_NAME, " +
            "SP_APP.DESCRIPTION, SP_APP.UUID, SP_APP.IMAGE_URL, SP_APP.ACCESS_URL, SP_APP.USERNAME, " +
            "SP_INBOUND_AUTH.INBOUND_AUTH_KEY, SP_APP.USER_STORE, SP_APP.TENANT_ID FROM SP_APP " +
            "LEFT JOIN SP_INBOUND_AUTH ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE SP_APP.TENANT_ID = ? " +
            "AND SP_APP.APP_NAME != ? AND (%s) ORDER BY SP_APP.ID DESC LIMIT ?, ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_ORACLE = "SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, " +
            "ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "(SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, " +
            "TENANT_ID, rownum AS rnum FROM " +
            "(SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID " +
            "FROM SP_APP ORDER BY ID DESC) WHERE TENANT_ID = ? AND APP_NAME != ? rownum <= ?) WHERE rnum > ? ";

    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER_ORACLE = "SELECT ID, APP_NAME, DESCRIPTION, " +
            "UUID, IMAGE_URL, ACCESS_URL, USERNAME, INBOUND_AUTH_KEY, USER_STORE, TENANT_ID FROM (SELECT ID, " +
            "APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, INBOUND_AUTH_KEY, USER_STORE, TENANT_ID, " +
            "ROWNUM AS rn from (SELECT SP_APP.ID, SP_APP.APP_NAME, SP_APP.DESCRIPTION, SP_APP.UUID, " +
            "SP_APP.IMAGE_URL, SP_APP.ACCESS_URL, SP_APP.USERNAME, SP_INBOUND_AUTH.INBOUND_AUTH_KEY, " +
            "SP_APP.USER_STORE, SP_APP.TENANT_ID FROM SP_APP " +
            "LEFT JOIN SP_INBOUND_AUTH on SP_APP.ID = SP_INBOUND_AUTH.APP_ID " +
            "WHERE SP_APP.TENANT_ID = ? AND SP_APP.APP_NAME <> ? AND (%s) ORDER BY SP_APP.ID DESC) " +
            "WHERE ROWNUM <= ?) WHERE rn > ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_DB2SQL = "SELECT ID, APP_NAME, DESCRIPTION, " +
            "UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM (SELECT " +
            "ROW_NUMBER() OVER(ORDER BY ID DESC) AS rn,SP_APP.* FROM SP_APP WHERE TENANT_ID = ? AND APP_NAME != ? ) " +
            "WHERE rn BETWEEN ? AND ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER_DB2SQL = "SELECT ID, APP_NAME, DESCRIPTION, " +
            "UUID, IMAGE_URL, ACCESS_URL, USERNAME, INBOUND_AUTH_KEY, USER_STORE, TENANT_ID FROM (SELECT " +
            "SP_APP.ID, SP_APP.APP_NAME, SP_APP.DESCRIPTION, SP_APP.UUID, SP_APP.IMAGE_URL, SP_APP.ACCESS_URL, " +
            "SP_APP.USERNAME, SP_INBOUND_AUTH.INBOUND_AUTH_KEY, SP_APP.USER_STORE, SP_APP.TENANT_ID, " +
            "ROW_NUMBER() OVER (ORDER BY SP_APP.ID DESC) AS rn FROM SP_APP LEFT JOIN SP_INBOUND_AUTH ON " +
            "SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE SP_APP.TENANT_ID = ? AND " +
            "SP_APP.APP_NAME <> ? AND (%s) ORDER BY SP_APP.ID DESC ) WHERE rn > ? AND rn <= ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_MSSQL = "SELECT ID, APP_NAME, DESCRIPTION, " +
            "UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = ? AND APP_NAME != ? ORDER BY ID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER_MSSQL = "SELECT SP_APP.ID, SP_APP.APP_NAME, " +
            "SP_APP.DESCRIPTION, SP_APP.UUID, SP_APP.IMAGE_URL, SP_APP.ACCESS_URL, SP_APP.USERNAME, " +
            "SP_INBOUND_AUTH.INBOUND_AUTH_KEY, SP_APP.USER_STORE, SP_APP.TENANT_ID FROM SP_APP LEFT JOIN " +
            "SP_INBOUND_AUTH ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE SP_APP.TENANT_ID = ? AND " +
            "SP_APP.APP_NAME != ? AND (%s) ORDER BY SP_APP.ID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    public static final String LOAD_APP_NAMES_BY_TENANT_POSTGRESQL = "SELECT ID, APP_NAME, DESCRIPTION, " +
            "UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = ? AND APP_NAME != ? ORDER BY ID DESC LIMIT ? OFFSET ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER_POSTGRESQL = "SELECT SP_APP.ID, " +
            "SP_APP.APP_NAME, SP_APP.DESCRIPTION, SP_APP.UUID, SP_APP.IMAGE_URL, SP_APP.ACCESS_URL, " +
            "SP_APP.USERNAME, SP_INBOUND_AUTH.INBOUND_AUTH_KEY, SP_APP.USER_STORE, SP_APP.TENANT_ID FROM SP_APP " +
            "LEFT JOIN SP_INBOUND_AUTH ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE SP_APP.TENANT_ID = ? " +
            "AND SP_APP.APP_NAME != ? AND (%s) ORDER BY SP_APP.ID DESC LIMIT ? OFFSET ?";

    public static final String LOAD_APP_NAMES_BY_TENANT_INFORMIX = "SELECT SKIP ? FIRST ? ID, APP_NAME, " +
            "DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM SP_APP " +
            "WHERE TENANT_ID = ? AND APP_NAME != ? ORDER BY ID DESC";

    public static final String LOAD_APP_NAMES_BY_TENANT_AND_FILTER_INFORMIX = "SELECT SKIP ? FIRST ? SP_APP.ID, " +
            "SP_APP.APP_NAME, SP_APP.DESCRIPTION, SP_APP.UUID, SP_APP.IMAGE_URL, SP_APP.ACCESS_URL, SP_APP.USERNAME, " +
            "SP_INBOUND_AUTH.INBOUND_AUTH_KEY, SP_APP.USER_STORE, SP_APP.TENANT_ID FROM SP_APP " +
            "LEFT OUTER JOIN SP_INBOUND_AUTH ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID WHERE SP_APP.TENANT_ID = ? " +
            "AND SP_APP.APP_NAME <> ? AND (%s) ORDER BY SP_APP.ID desc";

    public static final String LOAD_APP_ID_BY_CLIENT_ID_AND_TYPE =
            "SELECT APP_ID FROM SP_AUTH_STEP WHERE CLIENT_ID = ? "
                    + "AND CLIENT_TYPE= ? AND TENANT_ID = ?";
    public static final String LOAD_APPLICATION_NAME_BY_CLIENT_ID_AND_TYPE = "SELECT APP_NAME "
            + "FROM SP_APP INNER JOIN SP_INBOUND_AUTH "
            + "ON SP_APP.ID = SP_INBOUND_AUTH.APP_ID "
            + "WHERE INBOUND_AUTH_KEY = ? AND INBOUND_AUTH_TYPE = ? AND SP_APP.TENANT_ID = ? "
            + "AND SP_INBOUND_AUTH.TENANT_ID=?";

    public static final String LOAD_BASIC_APP_INFO_BY_APP_NAME = "SELECT ID, TENANT_ID, APP_NAME, USER_STORE, " +
            "USERNAME, DESCRIPTION, ROLE_CLAIM, AUTH_TYPE, PROVISIONING_USERSTORE_DOMAIN, IS_LOCAL_CLAIM_DIALECT," +
            "IS_SEND_LOCAL_SUBJECT_ID, IS_SEND_AUTH_LIST_OF_IDPS, IS_USE_TENANT_DOMAIN_SUBJECT, " +
            "IS_USE_USER_DOMAIN_SUBJECT, ENABLE_AUTHORIZATION, " +
            "SUBJECT_CLAIM_URI, IS_SAAS_APP, UUID, IMAGE_URL, " +
            "ACCESS_URL, IS_DISCOVERABLE " +
            "FROM SP_APP WHERE APP_NAME " +
            "= ? AND TENANT_ID= ?";

    public static final String LOAD_BASIC_APP_INFO_BY_APP_ID = "SELECT ID, TENANT_ID, APP_NAME, USER_STORE, " +
            "USERNAME, DESCRIPTION, ROLE_CLAIM, AUTH_TYPE, PROVISIONING_USERSTORE_DOMAIN, IS_LOCAL_CLAIM_DIALECT," +
            "IS_SEND_LOCAL_SUBJECT_ID, IS_SEND_AUTH_LIST_OF_IDPS, IS_USE_TENANT_DOMAIN_SUBJECT, " +
            "IS_USE_USER_DOMAIN_SUBJECT, ENABLE_AUTHORIZATION, " +
            "SUBJECT_CLAIM_URI, IS_SAAS_APP, UUID, IMAGE_URL, " +
            "ACCESS_URL, IS_DISCOVERABLE " +
            "FROM SP_APP WHERE ID = ?";

    public static final String LOAD_AUTH_TYPE_BY_APP_ID = "SELECT AUTH_TYPE FROM SP_APP WHERE ID = ? AND TENANT_ID = ?";
    public static final String LOAD_APP_NAME_BY_APP_ID = "SELECT APP_NAME FROM SP_APP WHERE ID = ? AND TENANT_ID = ?";
    public static final String LOAD_CLIENTS_INFO_BY_APP_ID = "SELECT INBOUND_AUTH_KEY, INBOUND_AUTH_TYPE, PROP_NAME, " +
            "PROP_VALUE,INBOUND_CONFIG_TYPE FROM  SP_INBOUND_AUTH WHERE APP_ID = ? AND TENANT_ID = ?";
    public static final String LOAD_STEPS_INFO_BY_APP_ID = "SELECT STEP_ORDER, AUTHENTICATOR_ID, IS_SUBJECT_STEP, " +
            "IS_ATTRIBUTE_STEP "
            + "FROM SP_AUTH_STEP INNER JOIN SP_FEDERATED_IDP "
            + "ON SP_AUTH_STEP.ID=SP_FEDERATED_IDP.ID "
            + "WHERE APP_ID = ?";
    public static final String LOAD_STEP_ID_BY_APP_ID = "SELECT ID FROM SP_AUTH_STEP WHERE APP_ID = ?";
    public static final String LOAD_HUB_IDP_BY_NAME =
            "SELECT IS_FEDERATION_HUB FROM IDP WHERE NAME = ? AND TENANT_ID = ?";

    public static final String LOAD_CLAIM_MAPPING_BY_APP_ID = "SELECT IDP_CLAIM, SP_CLAIM, IS_REQUESTED, " +
            "IS_MANDATORY, DEFAULT_VALUE " +
            "FROM SP_CLAIM_MAPPING WHERE APP_ID = ? AND TENANT_ID = ?";
    public static final String LOAD_CLAIM_MAPPING_BY_APP_NAME = "SELECT IDP_CLAIM, SP_CLAIM, IS_REQUESTED," +
            " IS_MANDATORY, DEFAULT_VALUE "
            + "FROM SP_CLAIM_MAPPING WHERE APP_ID = (SELECT ID FROM SP_APP WHERE APP_NAME = ? AND TENANT_ID = ?)";
    public static final String LOAD_ROLE_MAPPING_BY_APP_ID =
            "SELECT IDP_ROLE, SP_ROLE FROM SP_ROLE_MAPPING WHERE APP_ID" +
                    " = ? AND TENANT_ID = ?";

    public static final String LOAD_CLAIM_CONIFG_BY_APP_ID = "SELECT ROLE_CLAIM, IS_LOCAL_CLAIM_DIALECT, " +
            "IS_SEND_LOCAL_SUBJECT_ID FROM SP_APP WHERE TENANT_ID= ? AND ID = ?";

    public static final String LOAD_SP_DIALECTS_BY_APP_ID = "SELECT SP_DIALECT FROM SP_CLAIM_DIALECT WHERE" +
            " TENANT_ID= ? AND APP_ID = ?";
    public static final String STORE_SP_DIALECTS_BY_APP_ID =
            "INSERT INTO SP_CLAIM_DIALECT (TENANT_ID, SP_DIALECT, APP_ID)" +
                    " VALUES (?,?,?)";
    public static final String DELETE_SP_DIALECTS_BY_APP_ID = "DELETE FROM SP_CLAIM_DIALECT WHERE" +
            " APP_ID = ? AND TENANT_ID= ?";
    public static final String LOAD_LOCAL_AND_OUTBOUND_CONFIG_BY_APP_ID = "SELECT IS_USE_TENANT_DOMAIN_SUBJECT, " +
            "IS_USE_USER_DOMAIN_SUBJECT, ENABLE_AUTHORIZATION, IS_SEND_AUTH_LIST_OF_IDPS, SUBJECT_CLAIM_URI FROM " +
            "SP_APP WHERE TENANT_ID= ? AND ID = ?";
    public static final String LOAD_SCRIPT_BY_APP_ID_QUERY = "SELECT CONTENT, IS_ENABLED" +
            " FROM SP_AUTH_SCRIPT WHERE APP_ID = ?";
    public static final String LOAD_REQ_PATH_AUTHENTICATORS_BY_APP_ID = "SELECT AUTHENTICATOR_NAME FROM " +
            "SP_REQ_PATH_AUTHENTICATOR WHERE APP_ID = ? AND TENANT_ID = ?";
    public static final String LOAD_PRO_PROPERTIES_BY_APP_ID =
            "SELECT PROVISIONING_USERSTORE_DOMAIN, IS_DUMB_MODE FROM " +
                    "SP_APP WHERE TENANT_ID= ? AND ID = ?";
    public static final String LOAD_PRO_CONNECTORS_BY_APP_ID = "SELECT IDP_NAME, CONNECTOR_NAME, IS_JIT_ENABLED, " +
            "BLOCKING, RULE_ENABLED FROM " +
            "SP_PROVISIONING_CONNECTOR WHERE " +
            "APP_ID = ? AND TENANT_ID = ?";
    public static final String LOAD_UM_PERMISSIONS = "SELECT UM_ID, UM_RESOURCE_ID FROM UM_PERMISSION WHERE " +
            "UM_RESOURCE_ID LIKE ?";
    public static final String LOAD_UM_PERMISSIONS_W = "SELECT UM_ID FROM UM_PERMISSION WHERE UM_RESOURCE_ID = ?";

    // DELETE queries
    public static final String REMOVE_APP_FROM_APPMGT_APP = "DELETE FROM SP_APP WHERE APP_NAME = ? AND TENANT_ID = ?";
    public static final String REMOVE_APP_FROM_APPMGT_APP_WITH_ID = "DELETE FROM SP_APP WHERE ID = ? AND TENANT_ID = ?";
    public static final String REMOVE_APPS_FROM_APPMGT_APP_BY_TENANT_ID = "DELETE FROM SP_APP WHERE TENANT_ID = ?";
    public static final String REMOVE_CLIENT_FROM_APPMGT_CLIENT = "DELETE FROM SP_INBOUND_AUTH WHERE APP_ID = ? " +
            "AND TENANT_ID = ?";
    public static final String REMOVE_STEP_FROM_APPMGT_STEP = "DELETE FROM SP_AUTH_STEP WHERE APP_ID = ? AND " +
            "TENANT_ID = ?";
    public static final String REMOVE_AUTH_SCRIPT = "DELETE FROM SP_AUTH_SCRIPT WHERE APP_ID = ?";
    public static final String REMOVE_CLAIM_MAPPINGS_FROM_APPMGT_CLAIM_MAPPING = "DELETE FROM SP_CLAIM_MAPPING " +
            "WHERE APP_ID = ? AND TENANT_ID = ?";
    public static final String REMOVE_ROLE_MAPPINGS_FROM_APPMGT_ROLE_MAPPING = "DELETE FROM SP_ROLE_MAPPING " +
            "WHERE APP_ID = ? AND TENANT_ID = ?";
    public static final String REMOVE_REQ_PATH_AUTHENTICATOR = "DELETE FROM SP_REQ_PATH_AUTHENTICATOR WHERE " +
            "APP_ID = ? AND TENANT_ID = ?";
    public static final String REMOVE_PRO_CONNECTORS = "DELETE FROM SP_PROVISIONING_CONNECTOR WHERE APP_ID = ? AND " +
            "TENANT_ID = ?";
    public static final String REMOVE_UM_PERMISSIONS = "DELETE FROM UM_PERMISSION WHERE UM_ID = ?";
    public static final String REMOVE_UM_ROLE_PERMISSION = "DELETE FROM UM_ROLE_PERMISSION WHERE UM_PERMISSION_ID = ?";

    // DELETE query - Oauth
    public static final String REMOVE_OAUTH_APPLICATION = "DELETE FROM IDN_OAUTH_CONSUMER_APPS WHERE CONSUMER_KEY=?";

    public static final String LOAD_IDP_AUTHENTICATOR_ID = "SELECT A.ID FROM IDP_AUTHENTICATOR A JOIN IDP B ON A" +
            ".IDP_ID= B.ID WHERE A.NAME =? AND B.NAME=? AND ((A.TENANT_ID =? AND B.TENANT_ID =?) OR (B.TENANT_ID=? " +
            "AND B.NAME LIKE 'SHARED_%'))";
    public static final String LOAD_IDP_AND_AUTHENTICATOR_NAMES = "SELECT A.NAME, B.NAME, " +
            "B.DISPLAY_NAME FROM IDP A JOIN IDP_AUTHENTICATOR B ON A.ID = B.IDP_ID WHERE B.ID =? AND ((A.TENANT_ID =?" +
            " AND B.TENANT_ID =?) OR  (A.TENANT_ID=? AND A.NAME LIKE 'SHARED_%' AND B.TENANT_ID=?))";
    public static final String STORE_LOCAL_AUTHENTICATOR = "INSERT INTO IDP_AUTHENTICATOR (TENANT_ID, IDP_ID, NAME," +
            "IS_ENABLED, DISPLAY_NAME) VALUES (?, (SELECT ID FROM IDP WHERE IDP.NAME=? AND IDP.TENANT_ID =?), ?, ?, ?)";

    public static final String GET_SP_METADATA_BY_SP_ID = "SELECT ID, NAME, VALUE, DISPLAY_NAME FROM SP_METADATA " +
            "WHERE SP_ID = ?";

    public static final String GET_SP_METADATA_BY_SP_ID_H2 = "SELECT ID, NAME, `VALUE`, DISPLAY_NAME FROM " +
            "SP_METADATA WHERE SP_ID = ?";

    public static final String ADD_SP_METADATA = "INSERT INTO SP_METADATA (SP_ID, NAME, VALUE, DISPLAY_NAME, " +
            "TENANT_ID) VALUES (?, ?, ?, ?, ?)";

    public static final String ADD_SP_METADATA_H2 = "INSERT INTO SP_METADATA (SP_ID, NAME, `VALUE`, DISPLAY_NAME, " +
            "TENANT_ID) VALUES (?, ?, ?, ?, ?)";

    public static final String DELETE_SP_METADATA = "DELETE FROM SP_METADATA WHERE SP_ID = ?";

    public static final String GET_CERTIFICATE_BY_ID = "SELECT CERTIFICATE_IN_PEM FROM IDN_CERTIFICATE WHERE ID = ?";

    public static final String GET_CERTIFICATE_ID_BY_NAME = "SELECT ID FROM IDN_CERTIFICATE WHERE NAME = ? AND " +
            "TENANT_ID = ?";
    public static final String REMOVE_CERTIFICATE = "DELETE FROM IDN_CERTIFICATE WHERE ID = ?";
    public static final String REMOVE_CERTIFICATES_BY_TENANT_ID = "DELETE FROM IDN_CERTIFICATE WHERE TENANT_ID = ?";
    public static final String CHECK_AVAILABILITY_OF_IDN_CERTIFICATE_TABLE_MYSQL = "SELECT ID FROM IDN_CERTIFICATE " +
            "LIMIT 1";
    public static final String CHECK_AVAILABILITY_OF_IDN_CERTIFICATE_TABLE_DB2SQL = "SELECT ID FROM IDN_CERTIFICATE " +
            "FETCH FIRST 1 ROWS ONLY";
    public static final String CHECK_AVAILABILITY_OF_IDN_CERTIFICATE_TABLE_MSSQL = "SELECT TOP 1 ID FROM " +
            "IDN_CERTIFICATE";
    public static final String CHECK_AVAILABILITY_OF_IDN_CERTIFICATE_TABLE_INFORMIX = "SELECT FIRST 1 ID " +
            "FROM IDN_CERTIFICATE";
    public static final String CHECK_AVAILABILITY_OF_IDN_CERTIFICATE_TABLE_ORACLE = "SELECT ID FROM " +
            "IDN_CERTIFICATE WHERE ROWNUM < 2";

    public static final String ADD_SP_CONSENT_PURPOSE = "INSERT INTO SP_CONSENT_PURPOSE_ASSOC (APP_ID, PURPOSE_ID, " +
            "DISPLAY_ORDER, TENANT_ID) VALUES (?, ?, ?, ?)";

    public static final String DELETE_SP_CONSENT_PURPOSES =
            "DELETE FROM SP_CONSENT_PURPOSE_ASSOC WHERE APP_ID = ? AND " +
                    "TENANT_ID = ?";

    public static final String LOAD_SP_CONSENT_PURPOSES = "SELECT APP_ID, PURPOSE_ID, DISPLAY_ORDER, TENANT_ID FROM " +
            "SP_CONSENT_PURPOSE_ASSOC WHERE APP_ID = ? AND TENANT_ID = ?";

    public static final String UPDATE_BASIC_APP_INFO_WITH_CONSENT_ENABLED = "UPDATE SP_APP SET IS_CONSENT_MGT_ENABLED" +
            " = ? WHERE TENANT_ID= ? AND ID = ?";

    public static final String ADD_SP_TEMPLATE = "INSERT INTO SP_TEMPLATE (TENANT_ID, NAME, DESCRIPTION, " +
            "CONTENT) VALUES (?, ?, ?, ?)";
    public static final String GET_SP_TEMPLATE = "SELECT DESCRIPTION, CONTENT FROM SP_TEMPLATE WHERE NAME = ? AND " +
            "TENANT_ID = ?";
    public static final String IS_SP_TEMPLATE_EXISTS = "SELECT COUNT(*) FROM SP_TEMPLATE WHERE NAME = ? AND " +
            "TENANT_ID = ?";
    public static final String GET_ALL_SP_TEMPLATES_BASIC_INFO = "SELECT NAME, DESCRIPTION FROM SP_TEMPLATE WHERE " +
            "TENANT_ID = ?";
    public static final String DELETE_SP_TEMPLATE_BY_NAME = "DELETE FROM SP_TEMPLATE WHERE NAME = ? AND TENANT_ID= ?";
    public static final String UPDATE_SP_TEMPLATE_BY_NAME = "UPDATE SP_TEMPLATE SET NAME= ?,DESCRIPTION = ?," +
            "CONTENT = ? WHERE NAME = ? AND TENANT_ID = ?";

    /**
     * DB Queries for default authentication sequence management.
     */
    public static final String ADD_DEFAULT_SEQ = "INSERT INTO SP_DEFAULT_AUTH_SEQ(NAME, DESCRIPTION, SEQ_CONTENT, " +
            "TENANT_ID) VALUES (?, ?, ?, ?)";
    public static final String GET_DEFAULT_SEQ = "SELECT NAME, DESCRIPTION, SEQ_CONTENT FROM SP_DEFAULT_AUTH_SEQ " +
            "WHERE NAME = ? AND TENANT_ID = ?";
    public static final String GET_DEFAULT_SEQ_INFO = "SELECT NAME, DESCRIPTION FROM SP_DEFAULT_AUTH_SEQ WHERE " +
            "NAME = ? AND TENANT_ID = ?";
    public static final String GET_DEFAULT_SEQ_ID = "SELECT ID FROM SP_DEFAULT_AUTH_SEQ WHERE NAME = ? AND " +
            "TENANT_ID = ?";
    public static final String UPDATE_DEFAULT_SEQ = "UPDATE SP_DEFAULT_AUTH_SEQ SET NAME = ?,DESCRIPTION = ?," +
            "SEQ_CONTENT = ? WHERE NAME = ? AND TENANT_ID = ?";
    public static final String DELETE_DEFAULT_SEQ = "DELETE FROM SP_DEFAULT_AUTH_SEQ WHERE NAME = ? AND TENANT_ID = ?";

    // DB queries for application management using the resourceId
    public static final String LOAD_APP_BY_TENANT_AND_UUID = "SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, " +
            "ACCESS_URL, IS_DISCOVERABLE, USERNAME, USER_STORE, TENANT_ID FROM SP_APP WHERE TENANT_ID = :TENANT_ID; " +
            "AND UUID = :UUID;";

    public static final String LOAD_APP_ID_BY_UUID = "SELECT ID FROM SP_APP WHERE UUID = :UUID; " +
            "AND TENANT_ID = :TENANT_ID;";

    public static final String LOAD_UUID_BY_APP_ID = "SELECT UUID FROM SP_APP WHERE ID = :ID; " +
            "AND TENANT_ID = :TENANT_ID;";

    public static final String REMOVE_APP_FROM_SP_APP_WITH_UUID = "DELETE FROM SP_APP WHERE UUID=:UUID; " +
            "AND TENANT_ID=:TENANT_ID;";

    public static final String LOAD_APP_BY_NAME_AND_TENANT = "SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, " +
            "ACCESS_URL, IS_DISCOVERABLE, USERNAME, USER_STORE, TENANT_ID FROM SP_APP " +
            "WHERE TENANT_ID = :TENANT_ID; AND APP_NAME = :APP_NAME;";

    // DB queries to manage application discoverability
    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_MYSQL = "SELECT ID, APP_NAME, DESCRIPTION, UUID, " +
            "IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM SP_APP WHERE TENANT_ID = :TENANT_ID; AND " +
            "IS_DISCOVERABLE = '1' ORDER BY ID DESC LIMIT :OFFSET;, :LIMIT;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_ORACLE = "SELECT ID, APP_NAME, " +
            "DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "(SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID, rownum " +
            "AS rnum FROM " +
            "(SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, " +
            "TENANT_ID, IS_DISCOVERABLE FROM SP_APP ORDER BY ID DESC) WHERE TENANT_ID = :TENANT_ID; AND " +
            "rownum <= :END_INDEX; AND IS_DISCOVERABLE = '1') WHERE rnum > :ZERO_BASED_START_INDEX;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_MSSQL = "SELECT ID, APP_NAME, " +
            "DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = :TENANT_ID; AND IS_DISCOVERABLE = '1' ORDER BY ID " +
            "DESC OFFSET :OFFSET; ROWS FETCH NEXT :LIMIT; ROWS ONLY";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_POSTGRESQL = "SELECT ID, " +
            "APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = :TENANT_ID; AND IS_DISCOVERABLE = '1' ORDER BY ID " +
            "DESC LIMIT :LIMIT; OFFSET :OFFSET;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_DB2SQL = "SELECT ID, " +
            "APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM "
            + "(SELECT ROW_NUMBER() OVER(ORDER BY ID DESC) AS rn,SP_APP.* FROM SP_APP WHERE TENANT_ID = :TENANT_ID; " +
            "AND IS_DISCOVERABLE = '1') WHERE rn BETWEEN :ONE_BASED_START_INDEX; AND :END_INDEX;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_INFORMIX = "SELECT SKIP :OFFSET; FIRST " +
            ":LIMIT; ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = :TENANT_ID; AND IS_DISCOVERABLE = '1' ORDER BY ID DESC";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_AND_APP_NAME_MYSQL =
            "SELECT ID, APP_NAME, DESCRIPTION" +
                    " , UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM SP_APP " +
                    "WHERE TENANT_ID = :TENANT_ID; " +
                    "AND APP_NAME LIKE :APP_NAME; AND IS_DISCOVERABLE = '1' ORDER BY ID DESC LIMIT :OFFSET;, :LIMIT;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_AND_APP_NAME_ORACLE = "SELECT ID, APP_NAME, " +
            "DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "(SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID, rownum " +
            "AS rnum FROM " +
            "(SELECT ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, " +
            "TENANT_ID FROM SP_APP ORDER BY ID DESC) WHERE TENANT_ID = :TENANT_ID; AND APP_NAME LIKE :APP_NAME; AND " +
            "rownum <= :END_INDEX; AND IS_DISCOVERABLE = '1') WHERE rnum > :ZERO_BASED_START_INDEX;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_AND_APP_NAME_MSSQL = "SELECT ID, APP_NAME, " +
            "DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = :TENANT_ID; AND APP_NAME LIKE :APP_NAME; AND IS_DISCOVERABLE = '1' ORDER BY ID " +
            "DESC OFFSET :OFFSET; ROWS FETCH NEXT :LIMIT; ROWS ONLY";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_AND_APP_NAME_POSTGRESQL = "SELECT ID, " +
            "APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = :TENANT_ID; AND APP_NAME LIKE :APP_NAME; AND IS_DISCOVERABLE = '1' ORDER BY ID " +
            "DESC LIMIT :LIMIT; OFFSET :OFFSET;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_AND_APP_NAME_DB2 = "SELECT ID, " +
            "APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM "
            + "(SELECT ROW_NUMBER() OVER(ORDER BY ID DESC) AS rn,SP_APP.* FROM SP_APP WHERE TENANT_ID = :TENANT_ID; " +
            "AND APP_NAME LIKE :APP_NAME; AND IS_DISCOVERABLE = '1)WHERE rn BETWEEN :ONE_BASED_START_INDEX; AND " +
            ":END_INDEX;";

    public static final String LOAD_DISCOVERABLE_APPS_BY_TENANT_AND_APP_NAME_INFORMIX = "SELECT SKIP :OFFSET; FIRST " +
            ":LIMIT; ID, APP_NAME, DESCRIPTION, UUID, IMAGE_URL, ACCESS_URL, USERNAME, USER_STORE, TENANT_ID FROM " +
            "SP_APP WHERE TENANT_ID = :TENANT_ID; AND APP_NAME LIKE :APP_NAME; AND IS_DISCOVERABLE = '1' ORDER BY ID " +
            "DESC";

    public static final String LOAD_DISCOVERABLE_APP_COUNT_BY_APP_NAME_AND_TENANT = "SELECT COUNT(UUID) FROM SP_APP " +
            "WHERE TENANT_ID = :TENANT_ID; AND APP_NAME LIKE :APP_NAME; AND IS_DISCOVERABLE = '1'";

    public static final String LOAD_DISCOVERABLE_APP_COUNT_BY_TENANT = "SELECT COUNT(UUID) FROM SP_APP " +
            "WHERE TENANT_ID = :TENANT_ID; AND IS_DISCOVERABLE = '1'";

    public static final String IS_APP_BY_TENANT_AND_UUID_DISCOVERABLE = "SELECT COUNT(UUID) FROM SP_APP WHERE " +
            "TENANT_ID = :TENANT_ID; AND UUID = :UUID; AND IS_DISCOVERABLE = '1'";

    public static final String GET_TOTAL_SP_CLAIM_USAGES = "SELECT COUNT(*) FROM SP_CLAIM_MAPPING WHERE TENANT_ID = ?" +
            " AND IDP_CLAIM = ?";
}