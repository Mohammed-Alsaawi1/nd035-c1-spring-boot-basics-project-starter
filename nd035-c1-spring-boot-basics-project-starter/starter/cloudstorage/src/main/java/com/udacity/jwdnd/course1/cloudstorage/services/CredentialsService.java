package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class CredentialsService {
    @Autowired
    CredentialsMapper CredentialsMapper;
    UsersMapper UsersMapper;

    public CredentialsService(CredentialsMapper CredentialsMapper, UsersMapper UsersMapper ) {
        this.CredentialsMapper = CredentialsMapper;
        this.UsersMapper = UsersMapper;
    }

    public int createCredentials(Credentials Credentials) {
        return CredentialsMapper.insert(Credentials);

    }
    public int updateCredentials(Credentials Credentials) {
        return CredentialsMapper.update(Credentials);

    }
    public void deleteCredentials(Integer credentialId) {
         CredentialsMapper.delete(credentialId);

    }
    public String generateKey() {

        final String CHARACTER_SET = "0123456789abcdefghijklmnopqrstuvwxyz";
        int keyLength = 16;
        SecureRandom random = new SecureRandom();
        StringBuffer buff = new StringBuffer(keyLength);
        for (int i = 0; i < keyLength; i++) {
            int offset = random.nextInt(CHARACTER_SET.length());
            buff.append(CHARACTER_SET.charAt(offset));
        }
        return buff.toString();
    }

    public String[] getCredentials(Integer userId){
        return CredentialsMapper.getUserCredentials(userId);
    }

    public List<Credentials>getAllCredentials(Integer userId){
        return CredentialsMapper.getAllCredentials(userId);
    }





}
