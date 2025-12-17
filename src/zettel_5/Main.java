package zettel_5;

import java.util.LinkedList;
import java.util.Scanner;

import zettel_5.data.Euro2008;

public class Main {
    static HashTable playerToClub, clubToPlayers;
    static boolean initialized = false;

    public static void main(String[] args) throws Exception {
        // Initialize hash tables
        init();

        /*
         * This verifies that all players are correctly mapped to their clubs
         * and vice versa
         */
        autoTester();

        /*
         * This allows querying the hash tables interactively
         * e.g., finding a player's club and listing all players in that club
         * Here's a list of names you can try:
         * - Marco Streller
         * - Alexander Frei
         * - Petr Cech
         * - Jaromir Blazek
         */
        userInputTester();
    }

    private static void init() {
        if (initialized)
            return;
        initialized = true;
        System.out.println("Initializing hash tables...");
        playerToClub = new HashTable("Player to Club");
        clubToPlayers = new HashTable("Club to Players");

        System.out.println("Filling hash tables... ");
        int i = 0;
        for (Player player : Euro2008.allTeamPlayer) {
            ++i;
            playerToClub.put(player.getName(), player.getClub());
            clubToPlayers.put(player.getClub(), player.getName());
            if (playerToClub.size() != i)
                throw new AssertionError("Error: playerToClub size mismatch at insertion of " + player.getName());
        }
        playerToClub.debugTable();
        clubToPlayers.debugTable();
        System.out.println("Initialization complete.");
    }

    private static void autoTester() {
        System.out.println("Running automatic tests...");
        init();
        Player[] players = Euro2008.allTeamPlayer;
        for (Player p : players) {
            String club = playerToClub.getFirst(p.getName());
            assert club != null && club.equals(p.getClub())
                    : "Error: Player " + p.getName() + " has club " + p.getClub()
                            + ", but in hash table got " + club;
            if (club == null)
                throw new AssertionError("Club not found for player " + p.getName());
            LinkedList<String> clubPlayers = clubToPlayers.get(club);
            assert clubPlayers != null && clubPlayers.contains(p.getName())
                    : "Error: Club " + club + " does not contain player " + p.getName();
        }
        System.out.println("All automatic tests passed.");
    }

    private static void userInputTester() {
        System.out.println("Starting user input tester...");
        init();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player names to get their clubs (type 'exit' to quit):");
        while (true) {
            System.out.print("Player Name: ");
            String playerName = scanner.nextLine();
            if (playerName.equalsIgnoreCase("exit")) {
                break;
            }
            String club = playerToClub.getFirst(playerName);
            if (club != null) {
                System.out.println("Club: " + club);
            } else {
                System.out.println("Player not found.");
                continue;
            }

            LinkedList<String> players = clubToPlayers.get(club);
            System.out.println("Players playing for club " + club + ":");
            for (String p : players) {
                if (p.equals(playerName))
                    continue;
                System.out.println("- " + p);
            }
        }
        scanner.close();
    }
}
