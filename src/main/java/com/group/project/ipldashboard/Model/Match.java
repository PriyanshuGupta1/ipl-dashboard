package com.group.project.ipldashboard.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    private Long id;
    private String city;
    private LocalDate date;
    private String season;
    private String matchNumber;
    private String team1;
    private String team2;
    private String venue;
    private String tossWinner;
    private String tossDecision;
    private String superOver;
    private String winningTeam;
    private String wonBy;
    private String resultMargin;
    private String playerOfMatch;
    private String umpire1;
    private String umpire2;
}
