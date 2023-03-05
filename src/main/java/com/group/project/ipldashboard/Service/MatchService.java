package com.group.project.ipldashboard.Service;

import com.group.project.ipldashboard.Model.Match;
import com.group.project.ipldashboard.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;
    public List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName,int count) {
        return matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName,teamName, PageRequest.of(0,count));
    }
}
