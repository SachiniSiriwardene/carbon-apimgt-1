package org.wso2.carbon.apimgt.rest.api.admin.utils.mappings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.apimgt.api.dto.KeyManagerConfigurationDTO;
import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.rest.api.admin.dto.KeyManagerDTO;
import org.wso2.carbon.apimgt.rest.api.admin.dto.KeyManagerInfoDTO;
import org.wso2.carbon.apimgt.rest.api.admin.dto.KeyManagerListDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyManagerMappingUtil {

    public static KeyManagerListDTO toKeyManagerListDTO(List<KeyManagerConfigurationDTO> keyManagerDTOList) {

        KeyManagerListDTO keyManagerListDTO = new KeyManagerListDTO();
        List<KeyManagerInfoDTO> keyManagerDTOS = new ArrayList<>();
        for (KeyManagerConfigurationDTO keyManagerConfigurationDTO : keyManagerDTOList) {
            keyManagerDTOS.add(toKeyManagerInfoDTO(keyManagerConfigurationDTO));
        }
        keyManagerListDTO.setList(keyManagerDTOS);
        keyManagerListDTO.setCount(keyManagerDTOS.size());
        return keyManagerListDTO;
    }

    public static KeyManagerInfoDTO toKeyManagerInfoDTO(KeyManagerConfigurationDTO keyManagerConfigurationDTO) {

        KeyManagerInfoDTO keyManagerInfoDTO = new KeyManagerInfoDTO();
        keyManagerInfoDTO.setId(keyManagerConfigurationDTO.getUuid());
        keyManagerInfoDTO.setName(keyManagerConfigurationDTO.getName());
        keyManagerInfoDTO.setDescription(keyManagerConfigurationDTO.getDescription());
        keyManagerInfoDTO.setType(keyManagerConfigurationDTO.getType());
        keyManagerInfoDTO.setEnabled(keyManagerConfigurationDTO.isEnabled());
        return keyManagerInfoDTO;
    }


    public static KeyManagerDTO toKeyManagerDTO(KeyManagerConfigurationDTO keyManagerConfigurationDTO) {

        KeyManagerDTO keyManagerDTO = new KeyManagerDTO();
        keyManagerDTO.setId(keyManagerConfigurationDTO.getUuid());
        keyManagerDTO.setName(keyManagerConfigurationDTO.getName());
        keyManagerDTO.setDescription(keyManagerConfigurationDTO.getDescription());
        keyManagerDTO.setType(keyManagerConfigurationDTO.getType());
        keyManagerDTO.setEnabled(keyManagerConfigurationDTO.isEnabled());
        JsonObject jsonObject = fromConfigurationMapToJson(keyManagerConfigurationDTO.getAdditionalProperties());
        JsonElement clientRegistrationElement = jsonObject.get(APIConstants.KeyManager.CLIENT_REGISTRATION_ENDPOINT);
        if (clientRegistrationElement != null) {
            keyManagerDTO.setClientRegistrationEndpoint(clientRegistrationElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.CLIENT_REGISTRATION_ENDPOINT);
        }
        JsonElement introspectionElement = jsonObject.get(APIConstants.KeyManager.INTROSPECTION_ENDPOINT);
        if (introspectionElement != null) {
            keyManagerDTO.setIntrospectionEndpoint(introspectionElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.INTROSPECTION_ENDPOINT);
        }
        JsonElement tokenEndpointElement = jsonObject.get(APIConstants.KeyManager.TOKEN_ENDPOINT);
        if (tokenEndpointElement != null) {
            keyManagerDTO.setTokenEndpoint(tokenEndpointElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.TOKEN_ENDPOINT);
        }
        JsonElement revokeEndpointElement = jsonObject.get(APIConstants.KeyManager.REVOKE_ENDPOINT);
        if (revokeEndpointElement != null) {
            keyManagerDTO.setRevokeEndpoint(revokeEndpointElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.REVOKE_ENDPOINT);
        }
        JsonElement scopeEndpointElement = jsonObject.get(APIConstants.KeyManager.SCOPE_MANAGEMENT_ENDPOINT);
        if (scopeEndpointElement != null) {
            keyManagerDTO.setScopeManagementEndpoint(scopeEndpointElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.SCOPE_MANAGEMENT_ENDPOINT);
        }
        JsonElement grantTypesElement = jsonObject.get(APIConstants.KeyManager.AVAILABLE_GRANT_TYPE);
        if (grantTypesElement != null && grantTypesElement instanceof JsonArray) {
            keyManagerDTO.setAvailableGrantTypes(new Gson().fromJson(grantTypesElement, List.class));
            jsonObject.remove(APIConstants.KeyManager.AVAILABLE_GRANT_TYPE);
        }
        JsonElement issuerElement = jsonObject.get(APIConstants.KeyManager.ISSUER);
        if (issuerElement != null){
            keyManagerDTO.setIssuer(issuerElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.ISSUER);
        }
        JsonElement jwksEndpointElement = jsonObject.get(APIConstants.KeyManager.JWKS_ENDPOINT);
        if (jwksEndpointElement != null){
            keyManagerDTO.setJwksEndpoint(jwksEndpointElement.getAsString());
            jsonObject.remove(APIConstants.KeyManager.JWKS_ENDPOINT);
        }
        JsonElement userInfoEndpoint = jsonObject.get(APIConstants.KeyManager.USERINFO_ENDPOINT);
        if (userInfoEndpoint != null){
            keyManagerDTO.setUserInfoEndpoint(userInfoEndpoint.getAsString());
            jsonObject.remove(APIConstants.KeyManager.USERINFO_ENDPOINT);
        }
        JsonElement authorizeEndpoint = jsonObject.get(APIConstants.KeyManager.AUTHORIZE_ENDPOINT);
        if (userInfoEndpoint != null){
            keyManagerDTO.setAuthorizeEndpoint(authorizeEndpoint.getAsString());
            jsonObject.remove(APIConstants.KeyManager.AUTHORIZE_ENDPOINT);
        }
        JsonElement enableOauthAppCreation = jsonObject.get(APIConstants.KeyManager.ENABLE_OAUTH_APP_CREATION);
        if (enableOauthAppCreation != null){
            keyManagerDTO.setEnableMapOauthConsumerApps(enableOauthAppCreation.getAsBoolean());
            jsonObject.remove(APIConstants.KeyManager.ENABLE_OAUTH_APP_CREATION);
        }
        JsonElement enableTokenEncryption = jsonObject.get(APIConstants.KeyManager.ENABLE_TOKEN_ENCRYPTION);
        if (enableTokenEncryption != null){
            keyManagerDTO.setEnableTokenEncryption(enableTokenEncryption.getAsBoolean());
            jsonObject.remove(APIConstants.KeyManager.ENABLE_TOKEN_ENCRYPTION);
        }
        JsonElement enableTokenHHashing = jsonObject.get(APIConstants.KeyManager.ENABLE_TOKEN_HASH);
        if (enableTokenEncryption != null){
            keyManagerDTO.setEnableTokenHashing(enableTokenHHashing.getAsBoolean());
            jsonObject.remove(APIConstants.KeyManager.ENABLE_TOKEN_HASH);
        }
        JsonElement enableTokenGeneration = jsonObject.get(APIConstants.KeyManager.ENABLE_TOKEN_GENERATION);
        if (enableTokenGeneration != null){
            keyManagerDTO.setEnableTokenGneration(enableTokenGeneration.getAsBoolean());
            jsonObject.remove(APIConstants.KeyManager.ENABLE_TOKEN_GENERATION);
        }
        keyManagerDTO.setAdditionalProperties(new Gson().fromJson(jsonObject, Map.class));
        return keyManagerDTO;
    }

    public static KeyManagerConfigurationDTO toKeyManagerConfigurationDTO(String tenantDomain,KeyManagerDTO keyManagerDTO) {

        KeyManagerConfigurationDTO keyManagerConfigurationDTO = new KeyManagerConfigurationDTO();
        keyManagerConfigurationDTO.setName(keyManagerDTO.getName());
        keyManagerConfigurationDTO.setDescription(keyManagerDTO.getDescription());
        keyManagerConfigurationDTO.setEnabled(keyManagerDTO.getEnabled());
        keyManagerConfigurationDTO.setType(keyManagerDTO.getType());
        keyManagerConfigurationDTO.setTenantDomain(tenantDomain);
        Map additionalProperties = new HashMap();
        if (keyManagerDTO.getAdditionalProperties() != null && keyManagerDTO.getAdditionalProperties() instanceof Map) {
            additionalProperties.putAll((Map) keyManagerDTO.getAdditionalProperties());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getClientRegistrationEndpoint())) {
            additionalProperties.put(APIConstants.KeyManager.CLIENT_REGISTRATION_ENDPOINT,
                    keyManagerDTO.getClientRegistrationEndpoint());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getIntrospectionEndpoint())) {
            additionalProperties.put(APIConstants.KeyManager.INTROSPECTION_ENDPOINT,
                    keyManagerDTO.getIntrospectionEndpoint());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getTokenEndpoint())) {
            additionalProperties.put(APIConstants.KeyManager.TOKEN_ENDPOINT, keyManagerDTO.getTokenEndpoint());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getRevokeEndpoint())) {
            additionalProperties.put(APIConstants.KeyManager.REVOKE_ENDPOINT, keyManagerDTO.getRevokeEndpoint());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getScopeManagementEndpoint())) {
            additionalProperties.put(APIConstants.KeyManager.SCOPE_MANAGEMENT_ENDPOINT, keyManagerDTO.getScopeManagementEndpoint());
        }
        if (keyManagerDTO.getAvailableGrantTypes() != null) {
            additionalProperties.put(APIConstants.KeyManager.AVAILABLE_GRANT_TYPE,
                    keyManagerDTO.getAvailableGrantTypes());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getIssuer())){
            additionalProperties.put(APIConstants.KeyManager.ISSUER,keyManagerDTO.getIssuer());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getJwksEndpoint())){
            additionalProperties.put(APIConstants.KeyManager.JWKS_ENDPOINT,keyManagerDTO.getJwksEndpoint());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getUserInfoEndpoint())){
            additionalProperties.put(APIConstants.KeyManager.USERINFO_ENDPOINT,keyManagerDTO.getUserInfoEndpoint());
        }
        if (StringUtils.isNotEmpty(keyManagerDTO.getAuthorizeEndpoint())){
            additionalProperties.put(APIConstants.KeyManager.AUTHORIZE_ENDPOINT,keyManagerDTO.getAuthorizeEndpoint());
        }
        additionalProperties
                .put(APIConstants.KeyManager.ENABLE_OAUTH_APP_CREATION, keyManagerDTO.getEnableMapOauthConsumerApps());
        additionalProperties
                .put(APIConstants.KeyManager.ENABLE_TOKEN_GENERATION, keyManagerDTO.getEnableTokenGneration());

        additionalProperties
                .put(APIConstants.KeyManager.ENABLE_TOKEN_HASH, keyManagerDTO.getEnableTokenHashing());
        additionalProperties
                .put(APIConstants.KeyManager.ENABLE_TOKEN_ENCRYPTION, keyManagerDTO.getEnableTokenEncryption());
        keyManagerConfigurationDTO.setAdditionalProperties(additionalProperties);
        return keyManagerConfigurationDTO;
    }


    public static JsonObject fromConfigurationMapToJson(Map configuration) {

        JsonObject jsonObject = (JsonObject) new JsonParser().parse(new Gson().toJson(configuration));
        return jsonObject;
    }

}
