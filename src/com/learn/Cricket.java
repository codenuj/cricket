package com.learn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Cricket {

    void takeInput() throws FileNotFoundException {

        File text = new File("score.txt");
        System.out.print("No of players for each team:");
        int players;
        ArrayList<Team> teamArrayList;
        Match match;
        boolean flagWon = false;
        boolean flag = false;
        try (Scanner sc = new Scanner(text)) {
            players = sc.nextInt();
            System.out.println(players);
            System.out.print("No of overs:");
            int overs = sc.nextInt();
            System.out.println(overs);
            match = new Match();
            teamArrayList = new ArrayList<>();
            for (int i = 1; i <= 2; i++) {

                System.out.println("Batting order of team " + i + ":");
                Team team = new Team();
                team.setName("Team " + i);
                ArrayList<Player> playerArrayList = new ArrayList<>(players);
                for (int j = 0; j < players; j++) {
                    String name = sc.next();
                    Player player = new Player();
                    player.setName(name);
                    playerArrayList.add(j, player);
                    System.out.println(player.getName());
                }
                team.setPlayerList(playerArrayList);
                Player currentPlayer = playerArrayList.get(0);
                currentPlayer.setNotOut(true);
                Player otherPlayer = playerArrayList.get(1);
                otherPlayer.setNotOut(true);
                int nextPlayer = 2;
                flag = false;
                flagWon = false;
                for (int k = 1; k <= overs; k++) {
                    int ballCount = 6;
                    Player temp;
                    System.out.println("Over " + k + ":");
                    for (int l = 0; l < ballCount; l++) {
                        if (flag || flagWon)
                            break;
                        String score = sc.next();
                        System.out.println(score);
                        switch (score) {
                            case "1":
                                currentPlayer.setScore(currentPlayer.getScore() + 1);
                                currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                team.setScore(team.getScore() + 1);
                                team.setBalls(team.getBalls() + 1);
                                temp = currentPlayer;
                                currentPlayer = otherPlayer;
                                otherPlayer = temp;
                                break;
                            case "2":
                                currentPlayer.setScore(currentPlayer.getScore() + 2);
                                currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                team.setScore(team.getScore() + 2);
                                team.setBalls(team.getBalls() + 1);
                                break;
                            case "3":
                                currentPlayer.setScore(currentPlayer.getScore() + 3);
                                currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                team.setScore(team.getScore() + 3);
                                team.setBalls(team.getBalls() + 1);
                                temp = currentPlayer;
                                currentPlayer = otherPlayer;
                                otherPlayer = temp;
                                break;
                            case "4":
                                currentPlayer.setScore(currentPlayer.getScore() + 4);
                                currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                currentPlayer.setFours(currentPlayer.getFours() + 1);
                                team.setScore(team.getScore() + 4);
                                team.setBalls(team.getBalls() + 1);
                                break;
                            case "5":
                                currentPlayer.setScore(currentPlayer.getScore() + 5);
                                currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                team.setScore(team.getScore() + 5);
                                team.setBalls(team.getBalls() + 1);
                                temp = currentPlayer;
                                currentPlayer = otherPlayer;
                                otherPlayer = temp;
                                break;
                            case "6":
                                currentPlayer.setScore(currentPlayer.getScore() + 6);
                                currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                currentPlayer.setSixs(currentPlayer.getSixs() + 1);
                                team.setScore(team.getScore() + 6);
                                team.setBalls(team.getBalls() + 1);
                                break;
                            case "W":
                                if (nextPlayer < players) {
                                    currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                    currentPlayer.setNotOut(false);
                                    currentPlayer = playerArrayList.get(nextPlayer);
                                    nextPlayer++;
                                    currentPlayer.setNotOut(true);
                                    team.setWickets(team.getWickets() + 1);
                                    team.setBalls(team.getBalls() + 1);
                                } else if (nextPlayer == players) {
                                    currentPlayer.setBalls(currentPlayer.getBalls() + 1);
                                    currentPlayer.setNotOut(false);
                                    team.setWickets(team.getWickets() + 1);
                                    team.setBalls(team.getBalls() + 1);
                                    flag = true;
                                }
                                break;
                            case "Wd":
                            case "Nb":
                                team.setScore(team.getScore() + 1);
                                l--;
                                break;
                            default:
                                l--;
                                throw new IllegalStateException("Unexpected value: " + score);
                        }
                        if (match.getTeamList() != null && team.getScore() > match.getTeamList().get(0).getScore()) {
                            flagWon = true;
                        }
                    }
                    if (flag || flagWon)
                        break;
                    temp = currentPlayer;
                    currentPlayer = otherPlayer;
                    otherPlayer = temp;
                    System.out.println("ScoreCard for Team " + i + ":");
                    System.out.println("Player Name\tScore\t4s\t6s\tBalls");
                    for (Player p : playerArrayList) {
                        System.out.print(p.getName());
                        if (p.isNotOut())
                            System.out.print("*");
                        System.out.println("\t" + p.getScore() + "\t" + p.getFours() + "\t" + p.getSixs() + "\t" + p.getBalls());
                    }
                    System.out.print("Total: ");
                    System.out.println(team.getScore() + "/" + team.getWickets());
                    System.out.print("Overs: ");
                    System.out.println((team.getBalls() / 6) + "." + team.getBalls() % 6);
                }
                if (flag || flagWon) {
                    System.out.println("ScoreCard for Team " + i + ":");
                    System.out.println("Player Name\tScore\t4s\t6s\tBalls");
                    for (Player p : playerArrayList) {
                        System.out.print(p.getName());
                        if (p.isNotOut())
                            System.out.print("*");
                        System.out.println("\t" + p.getScore() + "\t" + p.getFours() + "\t" + p.getSixs() + "\t" + p.getBalls());
                    }
                    System.out.print("Total: ");
                    System.out.println(team.getScore() + "/" + team.getWickets());
                    System.out.print("Overs: ");
                    System.out.println((team.getBalls() / 6) + "." + team.getBalls() % 6);
                }
                teamArrayList.add(team);
                match.setTeamList(teamArrayList);
            }
        }

        System.out.print("Result: ");
        if (flagWon) {
            System.out.println("Team 2 won by " + (players - 1 - match.getTeamList().get(1).getWickets()) + " wickets");
        } else if (flag) {
            int score = match.getTeamList().get(0).getScore() - match.getTeamList().get(1).getScore();
            if (score == 0)
                System.out.println("Match Tied");
            else
                System.out.println("Team 1 won the match by " + score);
        } else {
            int score = match.getTeamList().get(0).getScore() - match.getTeamList().get(1).getScore();
            if (score == 0)
                System.out.println("Match Tied");
            else if (score > 0)
                System.out.println("Team 1 won the match by " + score);
            else {
                System.out.println("Team 2 won by " + (players - 1 - match.getTeamList().get(1).getWickets()) + " wickets");
            }
        }
    }
}
