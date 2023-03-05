package com.group.project.ipldashboard.Service;

import com.group.project.ipldashboard.Model.Team;
import com.group.project.ipldashboard.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    public Team getTeam(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }
}
