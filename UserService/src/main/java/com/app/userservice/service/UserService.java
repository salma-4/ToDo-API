package com.app.userservice.service;

import com.app.userservice.model.request.UserRequestDTO;

public interface UserService {

     String activateUser(long id);
     String deactivateUser(long id);
     String checkTokenValidty(String headerToken);
     String updateUser(UserRequestDTO user,long id );
     String deleteUser(long id);


}
