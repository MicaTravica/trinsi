package com.app.trinsi.service;

import com.app.trinsi.model.UserHealth;
import com.app.trinsi.model.UserPlanner;

public interface UserPlannerService {

    UserPlanner getUserPlanner(UserHealth userHealth);

}
