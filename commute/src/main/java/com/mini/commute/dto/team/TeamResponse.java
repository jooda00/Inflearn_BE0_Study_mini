package com.mini.commute.dto.team;

import com.mini.commute.entity.team.Team;
import lombok.Getter;

@Getter
public class TeamResponse {
    private String name;
    private String manager;
    private int memberCount;

    public TeamResponse(Team team) {
        this.name = team.getName();
        this.manager = String.join(", ", team.pickManager()); // Join manager names with comma
        if(this.manager.isEmpty()) {
            this.manager = null;
        }
        this.memberCount = team.getEmployees().size();
    }
}
