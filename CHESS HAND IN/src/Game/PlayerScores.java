/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  Group 22
 * @author bdg7335 + mjh6326
 */
public class PlayerScores {
    public Map set;
    
    public PlayerScores() {
        set = new HashMap();
    }
    
    public String getPlayerColor(Color color) {
        if(color.equals(Color.BLACK)) {
            return "BLACK";
        }
        else {
            return "WHITE";
        }
    }
    
    public void addPlayer(Player player, int score){
        Scanner option = new Scanner(System.in);
        int index = 0;
        if(set.containsKey(player.playerName)) {
            System.out.println(player.playerName + " has existing score, type yes to overwrite or no to restart");
            if(option.nextLine().equals("yes")) {
                set.replace(player.playerName, score);
            }
        }
        else {
            set.put(player.playerName, score);
            System.out.println("player saved");
        }
        
        try(FileWriter fw = new FileWriter("ExistingPlayers.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(player.playerName + player.playerScore);
            
        } catch (IOException e) {}
    }
    
    public void load() {
        try{
            FileReader fr = new FileReader("ExistingPlayers.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            
            while((line = inputStream.readLine()) != null) {
                int current = 0;
                String newString1 = "";
                current = Integer.parseInt(line.replaceAll("[\\D]", ""));
                newString1 += line.replaceAll("[^A-Za-z]+", "");
                set.put(newString1, current);
            }
            fr.close();
            inputStream.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("Error reading from file scores.txt");
        }
        catch(NumberFormatException e) {}
    }
    
    public Player loadSavedPlayer(Player playerToLoad) {
        try{
            FileReader fr = new FileReader("ExistingPlayers.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            
            while((line = inputStream.readLine()) != null) {
                if(line.contentEquals(playerToLoad.playerName)) {
                    return playerToLoad;
                }
            }
            System.out.println("Player not found");
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("Error reading from file scores.txt");
        }
        return null;
    }
}