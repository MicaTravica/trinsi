package com.app.trinsi.service;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.model.UserPlanner;

public interface UserPlannerService {

    UserPlanner getUserPlanner(UserPlanner userPlanner, UserHealth userHealth);

    UserPlanner findByUser(User user) throws ResourceNotFoundException;

}
