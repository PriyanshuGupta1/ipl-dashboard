package com.group.project.ipldashboard.Data;
import com.group.project.ipldashboard.Model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;

// Match input here is the current "MatchInput" entity and "Match" is the output entity in which we want our data to be transformed
public class MatchDataProcessor implements ItemProcessor <MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);
    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match=new Match();
        match.setId(matchInput.getID());
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setSeason(matchInput.getSeason());
        match.setMatchNumber(matchInput.getMatchNumber());

        //we are setting them on their batting order
        String firstInningsTeam,secondInningsTeam;

        String tossDecision=matchInput.getTossDecision();
        if("bat".equals(tossDecision)) {
            firstInningsTeam=matchInput.getTossWinner();
            secondInningsTeam=matchInput.getTossWinner().equals(matchInput.getTeam1()) ? matchInput.getTeam2(): matchInput.getTeam1();
        }
        else
        {
            firstInningsTeam=matchInput.getTossWinner().equals(matchInput.getTeam1()) ? matchInput.getTeam2(): matchInput.getTeam1();
            secondInningsTeam=matchInput.getTossWinner();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);

        match.setVenue(matchInput.getVenue());
        match.setTossWinner(matchInput.getTossWinner());
        match.setTossDecision(matchInput.getTossDecision());
        match.setSuperOver(matchInput.getSuperOver());
        match.setWinningTeam(matchInput.getWinningTeam());
        match.setWonBy(matchInput.getWonBy());
        match.setResultMargin(match.getResultMargin());
        match.setPlayerOfMatch(match.getPlayerOfMatch());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }
}