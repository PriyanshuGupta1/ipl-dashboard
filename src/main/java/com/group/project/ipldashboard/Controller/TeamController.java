package com.group.project.ipldashboard.Controller;

import com.group.project.ipldashboard.Model.Match;
import com.group.project.ipldashboard.Model.Team;
import com.group.project.ipldashboard.Service.MatchService;
import com.group.project.ipldashboard.Service.TeamService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private MatchService matchService;
    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable("teamName") String teamName){
         Team team= teamService.getTeam(teamName);
         team.setMatches(matchService.getByTeam1OrTeam2OrderByDateDesc(teamName,4));
         return team;
    }
}
