// Author: Hunter Lawrence
// Date: 11/30/2021
// File: Game.java

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// handles the game
public class Game
{
	//Fields
	private final String gameSavesLocation = "gameSaveFiles\\";
	private final String opponentFileLocation = "opponentFiles\\";
	private final String[] namingPrefixes = {"Grand Champion", "Beggar", "Peasant", "Black Belt", "Boxer", "Fighter", "Sir", "Knight", "Slayer", "Gladiator"};
	private final String[] namingPostfixes = {"The Grand Champion", "Wart Hog", "Lion", "Wild Cat", "Bear", "Elephant", "Tiger"};
	private final String[] namingFirstNames = {"James", "William", "Taylor", "Judith", "Tom", "Miles", "Cassey", "Hunter", "Bruce"};
	private final String[] namingSurnames = {"Tanner", "Smith", "Lawrence", "Lee", "Baker", "Fletcher", "Foreman", "Cheeseman"};
	private Player player;
	
	// constructor for game
	public Game() throws IOException
	{
		// Ask the user if they want to create a new game or resume an existing game
		String gameNewOrLoad = newOrLoad();
		
		//if new game:
		if(gameNewOrLoad.equals("NEW"))
		{
			//create scanner for input
			Scanner keyboard = new Scanner(System.in);
			
			
			while(true)
			{
				//ask user for the name of the new game
				System.out.println("What would you like to name the game file? (File name will also be character name)");
				String input = keyboard.nextLine();
				
				// Check if the file already exists
				boolean fileExist = checkFileAvailability(input);
				boolean back = false;
				
				
				// if file exists ask the user if they want to override the game file
				if(fileExist && back == false)
				{					
					while(true)
					{
						System.out.println("File " + input + " already exists. Would you like to replace it with a new game? (Y/N)");
						String yOrN = keyboard.nextLine().toUpperCase();
						
						//if the user wants to override the file with a new game
						if(yOrN.equals("Y"))
						{
							createNewGameFile(input);
							back = true;
                     String fileLocation = new String(gameSavesLocation + input);
							player = new Player(fileLocation);
							
							inTown();
						}
						//if the user want to enter another new game name
						else if(yOrN.equals("N"))
						{				
							back = true;
							break;
						}
						else
						{
							System.out.println("Please try again.");
						}
					}
				}
				else if(!fileExist)
				{
				// create new game file with given name
				createNewGameFile(input);
				player = new Player(gameSavesLocation + input);
					
				inTown();
				}
			}
		}
		//if load game:
		else if(gameNewOrLoad.equals("LOAD"))
		{
			//ask the user what game they would like to resume
			String userGameFile = getUserGameFileName();
			
			player = new Player(gameSavesLocation + userGameFile);
			
			if(player.isDefeated())
			{
				System.out.println("This character has been defeated in combat and cannot be played.");
			}
			else
			{
				inTown();
			}
		}
	}
	
	// checks if the player wants to load an existing or start a new one
	public String newOrLoad()
	{
		Scanner keyboard = new Scanner(System.in);
		while(true)
		{
			// ask user if they want to start a new game or resume an existing one
			System.out.println("Would you like to start a new game or load an existing one? (NEW or LOAD)");
			String input = keyboard.nextLine();
			String inputToUpperCase = input.toUpperCase();
			
			//if user wants to start a new game return value "NEW"
			if(inputToUpperCase.equals("NEW"))
			{
				return inputToUpperCase;
			}
			//if user wants to resume an existing game return value "LOAD"
			else if(inputToUpperCase.equals("LOAD"))
			{
				return inputToUpperCase;
			}
			//if user enters end terminate program
			else if(inputToUpperCase.equals("END"))
			{
				System.out.println("Terminateing program...");
				System.exit(0);
			}
			System.out.println("Please try again enter either NEW or LOAD");
		}
	}
	
	// checks if the file is available within the directory of saved games
	public boolean checkFileAvailability(String fileName)
	{
		File gameSaves = new File(gameSavesLocation + fileName + ".txt");
		boolean exist = gameSaves.exists();
		return exist;
	}
	
	// creates a new game file with default stats
	public  void createNewGameFile(String newFileName) throws IOException
	{
		String newGameInfo = new String("Name:" + newFileName + "\n" +
					"Health:100.00\n" + 
					"Strength:1.00\n" +
					"Defense:1.00\n" +
					"Dexterity:1.00\n" + 
					"Armor:RG1\n" +
					"Weapon:FT1\n" + 
					"ArmorLight:1\n" + 
					"ArmorMedium:1\n" + 
					"ArmorHeavy:1\n" +
					"WeaponSword:1\n" +
					"WeaponMace:1\n" +
					"WeaponKnife:1\n" +
					"TrainHealth:0\n" +
					"TrainStrength:0\n" +
					"TrainDefense:0\n" +
					"TrainDexterity:0\n" +
					"PomotionLevel:0\n" +
					"Balance:0\n" +
					"Inventory:\n" +
					"GrandChampion:" + randomName(5) +"\n" +
					"Defeat:FALSE");
		
		File newGameFile = new File(gameSavesLocation + newFileName + ".txt");
		FileWriter newGame = new FileWriter(newGameFile);
		newGame.write(newGameInfo);
		newGame.close();
	}
	
	// creates random names for non player characters based on fixed naming conventions
	public String randomName(int level)
	{
		Random random = new Random();
		int randomInt;
		String prefix;
		String postfix;
		String firstName;
		String surname;
		String name = "";
		switch(level)
		{
		case 0:
			// peasant
			// prefix from index 1 or 2
			// any fist name
			randomInt = random.nextInt(2);
			prefix = namingPrefixes[randomInt + 1];
			
			randomInt = random.nextInt(namingFirstNames.length);
			firstName = namingFirstNames[randomInt];
			
			name = prefix + " " + firstName;
			break;
			
		case 1:
			// commoner
			// any first name
			// any surname
			randomInt = random.nextInt(namingFirstNames.length);
			firstName = namingFirstNames[randomInt];
			
			randomInt = random.nextInt(namingSurnames.length);
			surname = namingSurnames[randomInt];
			
			name = firstName + " " + surname;
			break;
		case 2:
			// fighter
			// prefix from index 3, 4, or 5
			// any first name
			// any surname
			randomInt = random.nextInt(3);
			prefix = namingPrefixes[randomInt + 3];
			
			randomInt = random.nextInt(namingFirstNames.length);
			firstName = namingFirstNames[randomInt];
			
			randomInt = random.nextInt(namingSurnames.length);
			surname = namingSurnames[randomInt];
			
			name = prefix + " " + firstName + " " + surname;
			break;
			
		case 3:
			// knight
			// prefix from index 6 or 7
			// random surname
			randomInt = random.nextInt(2);
			prefix = namingPrefixes[randomInt + 6];
			
			randomInt = random.nextInt(namingSurnames.length);
			surname = namingSurnames[randomInt];
			
			name = prefix + " " + surname;
			break;
			
		case 4:
			// gladiator
			// random first name
			// prefix from index 8 or 9 (or postfix from index 1 - 6)
			randomInt = random.nextInt(namingFirstNames.length -1);
			firstName = namingFirstNames[randomInt];
			
			randomInt = random.nextInt(2);
			if(randomInt == 0)
			{
				randomInt = random.nextInt(namingPostfixes.length - 2);
				postfix = namingPostfixes[randomInt + 1];
				
				name = firstName + " The " + postfix;
			}
			else if(randomInt == 1)
			{
				randomInt = random.nextInt(2);
				prefix = namingPrefixes[randomInt + 8];
				
				name = prefix + " " + firstName;
			}
			
			break;
		case 5:
			// grand champion
			// any fist name
			// any last name
			// prefix or postfix from index 0
			
			randomInt = random.nextInt(namingFirstNames.length - 1);
			firstName = namingFirstNames[randomInt];
			
			randomInt = random.nextInt(namingSurnames.length - 1);
			surname = namingSurnames[randomInt];
			
			randomInt = random.nextInt(2);
			if(randomInt == 0)
			{
				prefix = namingPrefixes[0];
				
				name = prefix + " " + firstName + " " + surname;
			}
			else if (randomInt == 1)
			{
				postfix = namingPostfixes[0];
				
				name = firstName + " " + surname + " " + postfix;
			}
			
			break;
		}
		return name;
	}

	// gets the file name of the game the player wants to resume
	public String getUserGameFileName()
	{
		Scanner keyboard = new Scanner(System.in);
		displayAvailableGameFiles();
		while(true)
		{
			// ask user the name of the file they would like to resume
			System.out.println("What game would you like to resume? (enter name exactly as it appears)");
			String input = keyboard.nextLine();
			
			//check if given file is valid
			boolean valid = checkFileAvailability(input);
			
			//if input is = "end" terminate program
			if(input.equals("END"))
			{
				System.out.println("Terminating program...");
			}
			//if given file is valid return the name of the give file
			if(valid)
			{
				return input;
			}
			//if game file is not valid try again
			if(!valid)
			{
				System.out.println("File not found, please try again.");
			}
		}
	}
	
	// prints a list of available saved games
	public void displayAvailableGameFiles()
	{
		System.out.println("Available Games:");
		// create directory
		File gameSaves = new File(gameSavesLocation);
		
		// create an array for the string names
		String[] gameNames = gameSaves.list();
		String gameName;
		for(int i = 0; i < gameNames.length; i++)
		{
			gameName = gameNames[i];
			System.out.println(gameName.substring(0, gameName.length() - 4));
		}
	}
	
	// main menu for what the player can do in the game
	public void inTown() throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
		while(true)
 		{
			//ask the user what they want to do
			System.out.println("What would you like to do? \n" + 
								"COLOSSEUM \n" +
								"TRAIN \n" +
								"SHOP \n" +
								"PROMOTE \n" +
								"SHOW INVENTORY \n" +
								"EQUIP \n" + 
								"SHOW STATS \n" + 
								"END GAME \n");
			
			//get the user's input
			input = keyboard.nextLine();
			input = input.toUpperCase();
			
			// run corresponding method to appropriate input with an else if they input is wrong
			if(input.equals("COLOSSEUM"))
			{
				// run colosseum battle method
				colosseum();
			}
			else if(input.equals("TRAIN"))
			{
				// run training method
				train();
			}
			else if(input.equals("SHOP"))
			{
				// run shop method
				shop();
			}
			else if(input.equals("PROMOTE"))
			{
				//run promote method
				promotionOffice();
			}
			else if(input.equals("SHOW INVENTORY"))
			{
				//print player inventory
				player.printInventory();
			}
			else if(input.equals("EQUIP"))
			{
				// run the equip method
				equipItem();
			}
			else if(input.equals("SHOW STATS"))
			{
				// run show stats method
				player.printStats();
			}
			else if(input.equals("END GAME"))
			{
				// save and end game
				player.saveGame();
				System.exit(0);
			}
			else
			{
				// ask the user to try again
				System.out.println("input not recognized please try again");
			}
		}
	}
	
	// opens the general shop menu for the player
	public void shop()
	{
		
		Scanner keyboard = new Scanner(System.in);
		String input;
		
		System.out.println("Welcome to the market");
		
		while(true)
		{
			// ask the player if they want to go to the weapon or armor shop
			System.out.println("Which shop would you like to go to? \n" +
						       "ARMOR\n" +
						       "WEAPON\n" + 
						       "Type BACK to go back to town");
			input = keyboard.nextLine().toUpperCase();
			
			if(input.equals("ARMOR"))
			{
				// go to armor shop
				armorShop();
			}
			else if(input.equals("WEAPON"))
			{
				// go to weapon shop
				weaponShop();
			}
			else if(input.equals("BACK"))
			{
				// go back to town
				break;
			}
			else
			{
				System.out.println("Pease enter either ARMOR, WEAPON, or BACK.");
			}
		}
	}
	
	// primary colosseum method that displays opponents and allows the user to pick one
	public void colosseum() throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
		System.out.println("Welcome to The Colosseum!");
		// get a random array of 6 Opponents (including grand champion as last value)
		// ask the player who they want to battle or if they want to go back
		// display the array of Opponents
		// when the user picks one begin the battle method.
			
		Player[] opponents = getOpponentList();
		while(true)
		{
			printOpponents(opponents);
			System.out.println("Which opponent do you want to fight? (Enter opponent nubmer) or type BACK to go back to town");
			input = keyboard.nextLine();
			if(input.equals("1"))
			{
				battle(opponents[0]);
				break;
			}
			else if(input.equals("2"))
			{
				battle(opponents[1]);
				break;
			}
			else if(input.equals("3"))
			{
				battle(opponents[2]);
				break;
			}
			else if(input.equals("4"))
			{
				battle(opponents[3]);
				break;
			}
			else if(input.equals("5"))
			{
				battle(opponents[4]);
				break;
			}
			else if(input.equals("6"))
			{
				battle(opponents[5]);
				break;
			}
			else if(input.equals("back") || input.equals("BACK"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter a number between 1 and 6 or type BACK.");
			}
		}
	}
	
	// a standard battle in the colossium
	public void battle(Player opponent) throws IOException
	{
		String playerName = player.getName();
		String opponentName = opponent.getName();
		Random random = new Random();
		
		// welcome the player to the battle
		System.out.println("Announcer: Today we have " + playerName + " vs. " + opponentName);
		
		// get fixed number for player health and opponent health to deduct during the fight
		double playerHealth = player.getHealth();
		double opponentHealth = opponent.getHealth();
		
		// create booleans for the opponent and player's defending status
		boolean playerDefend = false;
		boolean opponentDefend = false;
		
		// create a boolean for player focus
		boolean playerFocus = false;
		
		Scanner keyboard = new Scanner(System.in);
		String input = "";
		while(true)
		{
			
			// if player health is less than 1
			if(playerHealth < 1)
			{
				// print that the player has lost
				System.out.println("Announcer: It looks like " + playerName + " has been defeated by " + opponentName);
				player.setDefeat();
				printGameScore();
				end();
			}
			// if opponent health is less than 1
			else if(opponentHealth < 1)
			{
				// if opponent was the grand champion
				if(opponent.getBalance() == 10000)
				{
					System.out.println("Announcer: This is unbeleiveable! " + playerName + " has defeated " + opponentName);
					//calculate the player's winnings and print
					int opponentGold = opponent.getBalance();
					int playerWinnings = opponentGold + opponentGold/10*player.getPromotionLevel();
					player.increaseBalance(playerWinnings);
					System.out.println("You have won " + playerWinnings + "G!");
					
					// end game
					printGameScore();
					end();
				}
				else
				{
					// print that the opponent has been defeated
					System.out.println("Announcer: And with that " + playerName + " has defeated " + opponentName + " in a stunning battle!");
					
					//calculate the player's winnings and print
					int opponentGold = opponent.getBalance();
					int playerWinnings = opponentGold + opponentGold/10*player.getPromotionLevel();
					player.increaseBalance(playerWinnings);
					System.out.println("You have won " + playerWinnings + "G!");
					
				}
				
				break;
			}
			else
			{
				// player turn
				while(true)
				{
					if(!playerFocus)
						{
							// ask the player if they want to attack, defend or focus
							System.out.println("Announcer: Is " + playerName + " going to ATTACK, DEFEND, or takes this time to FOCUS?\n" + 
											   playerName + "'s Health: " + playerHealth + "\n" + 
											   opponentName + "'s Health: " + opponentHealth);
							input = keyboard.nextLine().toUpperCase();
						}
					
					if(input.equals("ATTACK") || playerFocus)
					{
						System.out.println(playerName + "s Health: " + playerHealth + "\n" + 
										   opponentName + "'s Health: " + opponentHealth);
						while(true)
						{
							// ask the player where they are going to attack (torso, head, or arms)
							System.out.println("Announcer: Where is " + playerName + " going to attack? The TORSO, the HEAD, or the ARMS?");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("HEAD"))
							{
								// player has a 50% chance to hit
								double hitChance = player.calculateHitChance(50);
								int hitCalculate = random.nextInt(101);
								
								if(hitChance >= hitCalculate)
								{
									System.out.println("Announcer: Wow " + playerName + " has dealt a massive blow to " + opponentName + "'s head!");
									
									// calculate damage
									double playerDamage = player.calculateDamage(10);
									if(playerFocus)
									{
										// double damage for focusing
										playerDamage *= 2;
									}
									if(opponentDefend)
									{
										// deal 40% less damage if opponent is defending
										playerDamage *= 0.6;
									}
									
									// run damage against opponent's defense
									double opponentDefense = opponent.calculateDefense(playerDamage);
									
									// deduct damage from opponnent's health pool
									opponentHealth -= opponentDefense;
									
									// set player focus and opponent defense to false
									playerFocus = false;
									opponentDefend = false;
									break;
								}
								else
								{
									//set player focus and opponent defend to false
									playerFocus = false;
									opponentDefend = false;
									System.out.println("Announcer: Oh that was a close call for " + opponentName);
									break;
								}
							}
							else if(input.equals("TORSO"))
							{
								System.out.println("Announcer: Wow " + playerName + " has dealt a blow to " + opponentName + "'s torso!");
								
								// calculate damage
								double playerDamage = player.calculateDamage(5);
								if(playerFocus)
								{
									// double damage if player is focusing
									playerDamage *= 2;
								}
								if(opponentDefend)
								{
									// 40% less damage if opponent is defending
									playerDamage *= 0.6;
								}
								
								// run damage through opponent's defense
								double opponentDefense = opponent.calculateDefense(playerDamage);
								
								//deduct damage done from opponent's health pool
								opponentHealth -= opponentDefense;
								
								//set player focus and opponent defend to false
								playerFocus = false;
								opponentDefend = false;
								break;
							}
							else if(input.equals("ARMS"))
							{
								// get the chance of the player hitting the opponent
								double hitChance = player.calculateHitChance(75);
								int hitCalculate = random.nextInt(101);
								
								// if hit is successful
								if(hitChance >= hitCalculate)
								{
									System.out.println("Announcer: Wow " + playerName + " has dealt a stunning blow to " + opponentName + "'s arms!");
									
									// get player damage and check if the player is focuseing or the opponent is defending
									double playerDamage = player.calculateDamage(7);
									if(playerFocus)
									{
										// double damage for focus
										playerDamage *= 2;
									}
									if(opponentDefend)
									{
										// 40% less damage if opponent is defending
										playerDamage *= 0.6;
									}
									
									// run damage through opponent defense
									double opponentDefense = opponent.calculateDefense(playerDamage);
									
									// deduct damage done from opponent's health pool
									opponentHealth -= opponentDefense;
									
									//set player focus and opponent defend to false
									playerFocus = false;
									opponentDefend = false;
									break;
								}
								else
								{
									//set player focus and opponent defend to false
									playerFocus = false;
									opponentDefend = false;
									System.out.println("Announcer: Oh that was a close call for" + opponentName);
									break;
								}
								
								
							}
							else
							{
								// ask the player to try again
								System.out.println("Announcer: I dont know what " + playerName + " is doing, but they can only attack the TORSO, HEAD, or ARMS.");
							}
						}
						break;
					}
					else if(input.equals("DEFEND"))
					{
						// player will receive 40% less damage from opponent attacks
						System.out.println("Announcer: It looks like " + playerName + " is going to brace for impact");
						playerDefend = true;
						break;
					}
					else if(input.equals("FOCUS"))
					{
						// the player will deal 2x damage during their next attack
						System.out.println("Announcer: " + playerName + " is taking this time to focus and prepare a big attack");
						playerFocus = true;
						break;
					}
					else if(input.equals("BACK") || input.equals("RUN"))
					{
						// the player cannot go back
						System.out.println("THERE IS NO RUNNING FROM THE COLOSSIUM!!!");
					}
					else
					{
						// ask the player to try input again
						System.out.println("Announcer: I dont know what " + playerName + " is doing but they can only ATTACK, DEFEND, or FOCUS.");
					}
				}
				
				// opponent's turn
				int opponentDecision = random.nextInt(4);
				
				// opponent attack
				if(opponentDecision <= 2)
				{
					int opponentAttackLocation = random.nextInt(3);
					
					//attack to head
					if(opponentAttackLocation == 0)
					{
						double hitChance = opponent.calculateHitChance(50);
						int hitCalculate = random.nextInt(101);
						if(hitChance > hitCalculate)
						{
							System.out.println("Announcer: Wow " + opponentName + " has dealt a massive blow to " + playerName + "'s head!");
							double opponentDamage = opponent.calculateDamage(10);
							if(playerDefend)
							{
								opponentDamage *= 0.6;
							}
							
							double playerDefense = player.calculateDefense(opponentDamage);
							
							playerHealth -= playerDefense;
						}
						else
						{
							System.out.println("Announcer: Oh that was a close call for " + playerName);
						}
						
						playerDefend = false;
					}
					// attack to torso
					else if(opponentAttackLocation == 1)
					{
						System.out.println("Announcer: Wow " + opponentName + " has dealt a blow to " + playerName + "'s torso!");
						double opponentDamage = opponent.calculateDamage(5);
						
						if(playerDefend)
						{
							opponentDamage *= 0.6;
						}
						
						double playerDefense = player.calculateDefense(opponentDamage);
						
						playerHealth -= playerDefense;
						
						playerDefend = false;
					}
					//attack to arms
					else if(opponentAttackLocation == 2)
					{
						double hitChance = opponent.calculateHitChance(75);
						int hitCalculate = random.nextInt(101);
						if(hitChance > hitCalculate)
						{
							System.out.println("Announcer: Wow " + opponentName + " has dealt a stunning blow to " + playerName + "'s arms!");
							double opponentDamage = opponent.calculateDamage(7);
							if(playerDefend)
							{
								opponentDamage *= 0.6;
							}
							
							double playerDefense = player.calculateDefense(opponentDamage);
							
							playerHealth -= playerDefense;
						}
						else
						{
							System.out.println("Announcer: Oh that was a close call for " + playerName);
						}
					}
				}
				// opponent defend
				else if(opponentDecision == 3)
				{
					System.out.println("Announcer: It looks like " + opponentName + " is going to brace for impact");
					opponentDefend = true;
				}
			}
		}
	}
	
	
	// prints the score when the game is ended
	public void printGameScore()
	{
		int gameScore = player.getBalance();
		gameScore += 1000*(player.getDefenseTrainingLevel() + player.getDexterityTrainingLevel() + player.getStrengthTrainingLevel() + player.getHealthTrainingLevel());
		gameScore += 1000*(player.getPromotionLevel());
		gameScore += 1000*(player.getLightArmorLevel() + player.getMediumArmorLevel() + player.getHeavyArmorLevel());
		gameScore += 1000*(player.getKnifeWeaponLevel() + player.getSwordWeaponLevel() + player.getMaceWeaponLevel());
		System.out.println("GAME OVER; GAME SCORE: " + gameScore);
	}
	
	// prints an array of characters
	public void printOpponents(Player[] opponents)
	{
		for(int i = 0; i < opponents.length; i++)
		{
			System.out.println((i+1) + ": " + opponents[i].getName());
		}
	}
	
	// creates a random Player[] using fixed opponent values
	public Player[] getOpponentList() throws IOException
	{
		// create a Player[]
		// create a file for the character information
		// create an object Random and an int to hold random values
		Player[] opponentList = new Player[6];
		Random random = new Random();
		int randomInt;
		
		for(int i = 0; i < 5; i++)
		{
			randomInt = random.nextInt(5);
			switch(randomInt)
			{
			case 0:
				// create a peasant
				Player peasant = new Player(opponentFileLocation + "peasant");
				peasant.setName(randomName(0));
				opponentList[i] = peasant;
				break;
				
			case 1:
				// create a commoner
				Player commoner = new Player(opponentFileLocation + "commoner");
				commoner.setName(randomName(1));
				commoner.equipOpponentGear();
				opponentList[i] = commoner;
				break;
				
			case 2:
				// create a fighter
				Player fighter = new Player(opponentFileLocation + "fighter");
				fighter.setName(randomName(2));
				fighter.equipOpponentGear();
				opponentList[i] = fighter;
				break;
				
			case 3:
				// create a knight
				Player knight = new Player(opponentFileLocation + "knight");
				knight.setName(randomName(3));
				knight.equipOpponentGear();
				opponentList[i] = knight;
				break;
				
			case 4:
				// create agladiator
				Player gladiator = new Player(opponentFileLocation + "gladiator");
				gladiator.setName(randomName(4));
				gladiator.equipOpponentGear();
				opponentList[i] = gladiator;
				break;
				
			}
		}
		
		Player grandChampion = new Player(opponentFileLocation + "grandChampion");
		grandChampion.setName(player.getGrandChampionName());
		grandChampion.equipOpponentGear();
		opponentList[5] = grandChampion;
		
		return opponentList;
	}
	
	// weapon shop
	
	// weapon shop where characters can buy better weapons
	public void weaponShop()
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
		while(true)
		{
			// get the costs and player levels for each weapon type
			// print player balance
			// list available weapons (knife, sword, and mace) with corresponding costs
			// ask the user which they want to buy KNIFE, SWORD, or MACE
			// check if the player has enough money to buy the weapon
			// if true
			//		add the item to the inventory
			//		ask the player if they want to equip the item right away
			//		if yes
			// 			equip the item
			// if false, tell the player they do not have enough.
			
			int playerBalance = player.getBalance();
			int knifeLevel = player.getKnifeWeaponLevel();
			int knifeCost = knifeLevel*200;
			int swordLevel = player.getSwordWeaponLevel();
			int swordCost = swordLevel*400;
			int maceLevel = player.getMaceWeaponLevel();
			int maceCost = maceLevel*500;
			
			// create a string newItem to add to the inventory.
			String newItem;
			
			System.out.println(player.getName() + "'s Balance: " + playerBalance + "G\n" +
							   "What would you like to purchase?\n" + 
							   "Level " + knifeLevel + " KNIFE: " + knifeCost + "G\n" +
							   "Level " + swordLevel + " SWORD: " + swordCost + "G\n" +
							   "Level " + maceLevel + " MACE: " + maceCost + "G\n" + 
							   "Type BACK to go back to the market");
			input = keyboard.nextLine().toUpperCase();
			
			if(input.equals("KNIFE"))
			{
				if(player.getKnifeWeaponLevel() < 10)
				{
					if(playerBalance >= knifeCost)
					{
						// create the new item code
						// add item code to inventory
						// increase weapon level
						// ask the player if they want to equip the new item.
						newItem = "WK" + knifeLevel;
						player.addToInventory(newItem);
						player.increaseKnifeWeaponLevel();
						player.increaseBalance(-knifeCost);
						while(true)
						{
							System.out.println("Would you like to equip your new gear now? (Y/N)");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("Y"))
							{
								player.equip(player.inventoryLength()-1);
								break;
							}
							else if(input.equals("N"))
							{
								break;
							}
							else
							{
								System.out.println("Please enter either Y for yes or N for no");
							}
						}
					}
					else
					{
						System.out.println("You do not have enough money to buy a knife");
					}
				}
				else
				{
					System.out.println("Sorry, this weapon is reserved for annother customer.");
				}
			}
			else if(input.equals("SWORD"))
			{
				if(player.getSwordWeaponLevel() < 10)
				{
					if(playerBalance >= swordCost)
					{
						// create the new item code
						// add item code to inventory
						// increase weapon level
						// ask the player if they want to equip the new item.
						
						newItem = "WS" + swordLevel;
						player.addToInventory(newItem);
						player.increaseSwordWeaponLevel();
						player.increaseBalance(-swordCost);
						while(true)
						{
							System.out.println("Would you like to equip your new gear now? (Y/N)");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("Y"))
							{
								player.equip(player.inventoryLength()-1);
								break;
							}
							else if(input.equals("N"))
							{
								break;
							}
							else
							{
								System.out.println("Please enter either Y for yes or N for no");
							}
						}
					}
					else
					{
						System.out.println("You do not have enough money to buy a sword");
					}
				}
				else
				{
					System.out.println("Sorry, this weapon is reserved for annother customer.");
				}
			}
			else if(input.equals("MACE"))
			{
				if(player.getMaceWeaponLevel() < 10)
				{
					if(playerBalance >= maceCost)
					{
						// create the new item code
						// add item code to inventory
						// increase weapon level
						// ask the player if they want to equip the new item.
						newItem = "WM" + maceLevel;
						player.addToInventory(newItem);
						player.increaseMaceWeaponLevel();
						player.increaseBalance(-maceCost);
						while(true)
						{
							System.out.println("Would you like to equip your new gear now? (Y/N)");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("Y"))
							{
								player.equip(player.inventoryLength()-1);
								break;
							}
							else if(input.equals("N"))
							{
								break;
							}
							else
							{
								System.out.println("Please enter either Y for yes or N for no");
							}
						}
					}
					else
					{
						System.out.println("You do not have enough money to buy a mace");
					}
				}
				else
				{
					System.out.println("Sorry, this weapon is reserved for annother customer.");
				}
			}
			else if(input.equals("BACK"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter either KNIFE, SWORD, MACE, or BACK");
			}
		}
	}
	
	// armor shop
	
	// armor shop where characters can buy better armor
	public void armorShop()
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
		// get the costs and player levels for each armor type
		// print player balance
		// list available armor (light, medium, and heavy) with corresponding costs
		// ask the user which they want to buy LIGHT ARMOR, MEDIUM ARMOR, or HEAVY ARMOR
		// check if the player has enough money to buy the armor
		// if true
		//		add the item to the inventory
		//		ask the player if they want to equip the item right away
		//		if yes
		// 			equip the item
		// if false, tell the player they dont have enough.
		while(true)
		{
			int playerBalance = player.getBalance();
			int lightArmorLevel = player.getLightArmorLevel();
			int lightArmorCost = lightArmorLevel*200;
			int mediumArmorLevel = player.getMediumArmorLevel();
			int mediumArmorCost = mediumArmorLevel*400;
			int heavyArmorLevel = player.getHeavyArmorLevel();
			int heavyArmorCost = heavyArmorLevel*500;
			
			String newItem;
			
			System.out.println(player.getName() + "'s Balance: " + playerBalance + "G\n" +
							   "What would you like to pruchase?\n" + 
							   "Level " + lightArmorLevel + " LIGHT ARMOR: " + lightArmorCost + "G\n" +
							   "Level " + mediumArmorLevel + " MEDIUM ARMOR: " + mediumArmorCost + "G\n" +
							   "Level " + heavyArmorLevel + " HEAVY ARMOR: " + heavyArmorCost + "G\n" + 
							   "Type BACK to go back to the market");
			input = keyboard.nextLine().toUpperCase();
			
			if(input.equals("LIGHT ARMOR"))
			{
				if(player.getLightArmorLevel() < 10)
				{
					if(playerBalance >= lightArmorCost)
					{
						// create the new item code
						// add item code to inventory
						// increase weapon level
						// ask the player if they want to equip the new item.
						newItem = "AL" + lightArmorLevel;
						player.addToInventory(newItem);
						player.increaseLightArmorLevel();
						player.increaseBalance(-lightArmorCost);
						while(true)
						{
							System.out.println("Would you like to equip your new gear now? (Y/N)");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("Y"))
							{
								player.equip(player.inventoryLength()-1);
								break;
							}
							else if(input.equals("N"))
							{
								break;
							}
							else
							{
								System.out.println("Please enter either Y for yes or N for no");
							}
						}
					}
					else
					{
						System.out.println("You do not have enough money to buy light armor.");
					}
				}
				else
				{
					System.out.println("Sorry, this armor is reserved for annother customer.");
				}
			}
			else if(input.equals("MEDIUM ARMOR"))
			{
				if(player.getMediumArmorLevel() < 10)
				{
					if(playerBalance >= mediumArmorCost)
					{
						// create the new item code
						// add item code to inventory
						// increase weapon level
						// ask the player if they want to equip the new item.
						newItem = "AM" + mediumArmorLevel;
						player.addToInventory(newItem);
						player.increaseMediumArmorLevel();
						player.increaseBalance(-mediumArmorCost);
						while(true)
						{
							System.out.println("Would you like to equip your new gear now? (Y/N)");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("Y"))
							{
								player.equip(player.inventoryLength()-1);
								break;
							}
							else if(input.equals("N"))
							{
								break;
							}
							else
							{
								System.out.println("Please enter either Y for yes or N for no");
							}
						}
					}
					else
					{
						System.out.println("You do not have enough money to buy medium armor.");
					}
				}
				else
				{
					System.out.println("Sorry, this armor is reserved for annother customer.");
				}
			}
			else if(input.equals("HEAVY ARMOR"))
			{
				if(player.getHeavyArmorLevel() < 10)
				{
					if(playerBalance >= heavyArmorCost)
					{
						// create the new item code
						// add item code to inventory
						// increase weapon level
						// ask the player if they want to equip the new item.
						newItem = "AH" + heavyArmorLevel;
						player.addToInventory(newItem);
						player.increaseHeavyArmorLevel();
						player.increaseBalance(-heavyArmorCost);
						while(true)
						{
							System.out.println("Would you like to equip your new gear now? (Y/N)");
							input = keyboard.nextLine().toUpperCase();
							
							if(input.equals("Y"))
							{
								player.equip(player.inventoryLength()-1);
								break;
							}
							else if(input.equals("N"))
							{
								break;
							}
							else
							{
								System.out.println("Please enter either Y for yes or N for no");
							}
						}
					}
					else
					{
						System.out.println("You do not have enough money to buy heavy armor.");
					}
				}
				else
				{
					System.out.println("Sorry, this armor is reserved for annother customer.");
				}
			}
			else if(input.equals("BACK"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter either LIGHT ARMOR, MEDIUM ARMOR, HEAVY ARMOR, or BACK.");
			}
		}
	}
	
	// training for player health
	
	// training for the player's health
	public void trainHealth(int playerBalance)
	{
		// health has no maximum training level
		
		// get player health training level, player health, and set cost to train
		// print player balance, cost to train, and health and ask the player if they want to train
		// ask the player if they want to train or not
		// 		if Y
		//			check if the player has enough money to train or if the player has reached maximum stat level
		//				if true
		//					deduct cost from player
		//					increase player stat
		//				if false
		//					tell player they reached the maximum stat level and go back to training menu
		//		if N
		//			go back to the training menu
		Scanner keyboard = new Scanner(System.in);
		String input;
		// get player health, training level and cost to train
		double playerHealth = player.getHealth();
		int playerHealthLevel = player.getHealthTrainingLevel();
		int costToTrain = 250 + playerHealthLevel*250/10;
		while(true)
		{
			System.out.println(playerHealthLevel);
			System.out.println(player.getName() + "'s Ballance: " + playerBalance + "G\n" + 
							   "Cost to train: " + costToTrain + "G\n" + 
							   "Health: " + playerHealth + "\n" + 
							   "Would you like to train HEALTH? (Y/N)");
			input = keyboard.nextLine();
			input = input.toUpperCase();
			if(input.equals("Y"))
			{
				if(playerBalance >= costToTrain)
				{
					player.increaseHealth();
					player.increaseBalance(-costToTrain);
					System.out.println(player.getName() + "'s health is now " + player.getHealth());
					break;
				}
				else
				{
					System.out.println("You do not have enough money to train in health");
					break;
				}
			}
			else if (input.equals("N"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter either Y for yes or N for No");
			}
		}
	}
	
	// training for player strength
	public void trainStrength(int playerBalance)
	{
		// check if the player has reached maximum training
		if(player.getStrengthTrainingLevel() >= 99)
		{
			System.out.println("You have reached maximum strength");
		}
		else
		{	
			// get player strength training level, player strength, and set cost to train
			// print player balance, cost to train, and strength
			// ask the player if they want to train or not
			// 		if Y
			//			check if the player has enough money to train or if the player has reached maximum stat level
			//				if true
			//					deduct cost from player
			//					increase player stat
			//				if false
			//					tell player they reached the maximum stat level and go back to training menu
			//		if N
			//			go back to the training menu
			
			int playerStrengthLevel = player.getStrengthTrainingLevel();
			double playerStrength = player.getStrength();
			int costToTrain = 250 + playerStrengthLevel*250/10;
			Scanner keyboard = new Scanner(System.in);
			String input;
			while(true)
			{
				System.out.println(player.getName() + "'s Balance: " + playerBalance + "G\n" +
								   "Cost to train: " + costToTrain + "G\n" +
								   "Strength: " + playerStrength + "\n" + 
								   "Would you like to train? (Y/N)");
				input = keyboard.nextLine().toUpperCase();
				
				if(input.equals("Y"))
				{
					if(playerBalance >= costToTrain)
					{
						player.increaseStrength();
						player.increaseBalance(-costToTrain);
						System.out.println(player.getName() + "'s is now " + player.getStrength());
						break;
					}
					else
					{
						System.out.println("You do not have enough money to train in strength");
						break;
					}
				}
				else if(input.equals("N"))
				{
					break;
				}
				else
				{
					System.out.println("Please enter either Y for yes or N for no.");
				}
			}
			
		}
	}
	
	// training for player defense
	public void trainDefense(int playerBalance)
	{
		// check if the player has reached maximum training
		if(player.getDefenseTrainingLevel() >= 99)
		{
			System.out.println("You have reached maximum defense");
		}
		else
		{
			// get player defense training level, player defense, and set cost to train
			// print player balance, cost to train, and defense
			// ask the player if they want to train or not
			// 		if Y
			//			check if the player has enough money to train or if the player has reached maximum stat level
			//				if true
			//					deduct cost from player
			//					increase player stat
			//				if false
			//					tell player they reached the maximum stat level and go back to training menu
			//		if N
			//			go back to the training menu
			
			int playerDefenseLevel = player.getDefenseTrainingLevel();
			double playerDefense = player.getDefense();
			int costToTrain = 250 + playerDefenseLevel*250/10;
			Scanner keyboard = new Scanner(System.in);
			String input;
			
			while(true)
			{
				System.out.println(player.getName() + "'s Balance: " + playerBalance + "G\n" +
						   "Cost to train: " + costToTrain + "G\n" +
						   "Defense: " + playerDefense + "\n" + 
						   "Would you like to train? (Y/N)");
				input = keyboard.nextLine().toUpperCase();
				
				if(input.equals("Y"))
				{
					if(playerBalance >= costToTrain)
					{
						player.increaseDefense();
						player.increaseBalance(-costToTrain);
						System.out.println(player.getName() + "'s defesne is now " + player.getDefense());
						break;
					}
					else
					{
						System.out.println("You do not have enough money to train in defense");
						break;
					}
				}
				else if(input.equals("N"))
				{
					break;
				}
				else
				{
					System.out.println("Please enter either Y for yes or N for no.");
				}
			}
			
		}
	}
	
	// training for player dexterity
	public void trainDexterity(int playerBalance)
	{
		// check if the player has reached maximum training
		if(player.getDexterityTrainingLevel() >= 99)
		{
			System.out.println("You have reached maximum dexterity");
		}
		else
		{
			// get player dexterity training level, player dexterity, and set cost to train
			// print player balance, cost to train, and dexterity
			// ask the player if they want to train or not
			// 		if Y
			//			check if the player has enough money to train or if the player has reached maximum stat level
			//				if true
			//					deduct cost from player
			//					increase player stat
			//				if false
			//					tell player they reached the maximum stat level and go back to training menu
			//		if N
			//			go back to the training menu
			
			int playerDexterityLevel = player.getDexterityTrainingLevel();
			double playerDexterity = player.getDexterity();
			int costToTrain = 250 + playerDexterityLevel*250/10;
			Scanner keyboard = new Scanner(System.in);
			String input;
			
			while(true)
			{
				System.out.println(player.getName() + "'s Balance: " + playerBalance + "G\n" +
						   "Cost to train: " + costToTrain + "G\n" +
						   "Dexterity: " + playerDexterity + "\n" + 
						   "Would you like to train? (Y/N)");
				input = keyboard.nextLine().toUpperCase();
				
				if(input.equals("Y"))
				{
					if(playerBalance >= costToTrain)
					{
						player.increaseDexterity();
						player.increaseBalance(-costToTrain);
						System.out.println(player.getName() + "'s dexterity is now " + player.getDexterity());
						break;
					}
					else
					{
						System.out.println("You do not have enough money to train in dexterity");
						break;
					}
				}
				else if(input.equals("N"))
				{
					break;
				}
				else
				{
					System.out.println("Please enter either Y for yes or N for no.");
				}
			}
		}
	}
	
	// allows the player to train their fighting stats
	public void train()
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
		
		while(true)
		{
			// get player balance
			int playerBalance = player.getBalance();
			
			//ask the player what they want to train
			System.out.println("What would you like to train? \n" + 
							   "HEALTH\n" +
							   "STRENGTH\n" +
							   "DEFENSE\n" +
							   "DEXTERITY\n" +
							   "type BACK to go back to town.");
			input = keyboard.nextLine();
			input = input.toUpperCase();
			
			if(input.equals("BACK"))
			{
				System.out.println("Returning to town");
				break;
			}
			
			// for each case:
			// get player stat and player stat training level
			// print player balance, current stat, current stat training level
			// ask the player if they want to train or not
			// 		if Y
			//			check if the player has enough money to train or if the player has reached maximum stat level
			//				if true
			//					deduct cost from player
			//					increase player stat
			//				if false
			//					tell player they reached the maximum stat level and go back to training menu
			//		if N
			//			go back to the training menu
			switch(input)
			{
			case"HEALTH":
				trainHealth(playerBalance);
				break;
			case"STRENGTH":
				trainStrength(playerBalance);
				break;
			case"DEFENSE":
				trainDefense(playerBalance);
				break;
			case"DEXTERITY":
				trainDexterity(playerBalance);
				break;
			default:
				System.out.println("Please enter one of the available training types.");
			}
		}
	}
	
	// allows the player to increase their promotion level if they have enough Gold
	public void promotionOffice()
	{
		// create Scanner and String for user input
		Scanner keyboard = new Scanner(System.in);
		String input;
		
		while(true)
		{
			// get the player's balance and promotion level
			int playerBalance = player.getBalance();
			int playerPromotionLevel = player.getPromotionLevel();
			int promotionCost = 250 + 250*playerPromotionLevel;
			// print that the player is in the promotion office, the cost of promotion,
			// the player's balance, and ask the player if they want to promote.
			System.out.println("Promotion Office:\n" + 
							   "Promotion Cost: " + promotionCost + "G\n" +
							   player.getName() + "'s Balance: " + playerBalance + "G\n" +
							   "Your curent promotion Level is " + playerPromotionLevel + "\n" +
							   "Woudl you like to upgrade your promotion level? (Y/N)");
			//get input
			input = keyboard.nextLine();
			input = input.toUpperCase();
			
			// if the player inputs y
				// check if they have enough gold to promote
				// if the player has enough to promote
					//increase pomotion level and remove the cost from 
					// the palyer's balance
				//if the player does not have enough to promote
					// tell the player they do not have enough gold to upgrade and return to town
			// if the player inputs n
				// return to town
			// else prompt the player to try again
			if(input.equals("Y"))
			{
				if(playerBalance >= promotionCost)
				{
					player.increasePromotionLevel();
					player.increaseBalance(-promotionCost);
					System.out.println("Your new ballance is " + player.getBalance() + "G\n" +
									   "Your new promotion level is " + player.getPromotionLevel());
					break;
				}
				else
				{
					System.out.println("You dont have enough Gold to purchase a promotion level. \nReturning to town");
					break;
				}
			}
			else if(input.equals("N"))
			{
				System.out.println("Going back to town.");
				break;
			}
			else
			{
				System.out.println("Please enter either Y for yes or N for no.");
			}
		}
	}

	// equips an item from the player's inventory (if they have anything in their inventory
	public void equipItem()
	{
		//create a scanner for keyboard, create a string for the input
		Scanner keyboard = new Scanner(System.in);
		int input;
		
		player.printInventory();
		if(player.inventoryLength() != 0)
		{
			
			while(true)
			{
				// ask the user what item the want to equip
				System.out.println("What item do you want to equip? (type 0 to go back)");
				input = keyboard.nextInt();
				
				//get length of player inventory
				int playerInventoryLength = player.inventoryLength();
				
				//check if the item number is valid
				if(input > playerInventoryLength || input < 0)
				{
					// ask the user to try again
					System.out.println("Please enter a valid item number");
				}
				else if(input == 0)
				{
					break;
				}
				else
				{
					// e1uip item at the index of input - 1
					player.equip(input - 1);
					break;
				}
			}
		}	
	}
	
	public void end() throws IOException
	{
		player.saveGame();
		System.exit(0);
	}
}
