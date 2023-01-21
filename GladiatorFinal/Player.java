// Author: Hunter Lawrence
// Date: 11/30/2021
// File: Player.java

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

// class for player stores all player information and can save information before exiting a game
public class Player {
	private String name;
	private double healthStat;
	private double strengthStat;
	private double defenseStat;
	private double dexterityStat;
	private String armor;
	private String weapon;
	private int lightArmorLevel;
	private int mediumArmorLevel;
	private int heavyArmorLevel;
	private int swordWeaponLevel;
	private int maceWeaponLevel;
	private int knifeWeaponLevel;
	private int healthTrainingLevel;
	private int strengthTrainingLevel;
	private int defenseTrainingLevel;
	private int dexterityTrainingLevel;
	private int promotionLevel;
	private int balance;
	private String[] inventory;
	private String grandChampionName;
	private boolean defeat;
	private String gameFileName;
	
	public Player(String fileName) throws IOException
	{
      // Read a given game file to construct a player
		gameFileName = fileName + ".txt";
		File gameFile = new File(gameFileName);
		Scanner fileInfo = new Scanner(gameFile);
		String info;
		
		// for each line of a player file remove the description and save the information
		// as its corresponding value
		for(int i = 0; i < 22; i++)
		{
			info = fileInfo.nextLine();
			switch(i)
			{
			case 0:
				name = info.substring(5);
				break;
			case 1:
				info = info.substring(7);
				healthStat = Double.parseDouble(info);
				break;
			case 2:
				info = info.substring(9);
				strengthStat = Double.parseDouble(info);
				break;
			case 3:
				info = info.substring(8);
				defenseStat = Double.parseDouble(info);
				break;
			case 4:
				info = info.substring(10);
				dexterityStat = Double.parseDouble(info);
				break;
			case 5:
				armor = info.substring(6);
				break;
			case 6:
				weapon = info.substring(7);
				break;
			case 7:
				info = info.substring(11);
				lightArmorLevel = Integer.parseInt(info);
				break;
			case 8:
				info = info.substring(12);
				mediumArmorLevel = Integer.parseInt(info);
				break;
			case 9:
				info = info.substring(11);
				heavyArmorLevel = Integer.parseInt(info);
				break;
			case 10:
				info = info.substring(12);
				swordWeaponLevel = Integer.parseInt(info);
				break;
			case 11:
				info = info.substring(11);
				maceWeaponLevel = Integer.parseInt(info);
				break;
			case 12:
				info = info.substring(12);
				knifeWeaponLevel = Integer.parseInt(info);
				break;
			case 13:
				info = info.substring(12);
				healthTrainingLevel = Integer.parseInt(info);
				break;
			case 14:
				info = info.substring(14);
				strengthTrainingLevel = Integer.parseInt(info);
				break;
			case 15: 
				info = info.substring(13);
				defenseTrainingLevel = Integer.parseInt(info);
				break;
			case 16:
				info = info.substring(15);
				dexterityTrainingLevel = Integer.parseInt(info);
				break;
			case 17:
				info = info.substring(14);
				promotionLevel = Integer.parseInt(info);
				break;
			case 18:
				info = info.substring(8);
				balance = Integer.parseInt(info);
				break;
			case 19:
				info = info.substring(10);
				String itemCode;
				inventory = new String[(info.length() + 2)/5];
				for(int j = 0; j < (info.length() + 2)/5; j++)
				{
					itemCode = info.substring(j*5, 3+j*5);
					inventory[j] = itemCode;
				}
				break;
			case 20:
				grandChampionName = info.substring(14);
				break;
			case 21:
				info = info.substring(7);
				defeat = Boolean.parseBoolean(info);
				break;
			}
		}
		
		// close the fileInfo Scanner
		fileInfo.close();
		
		//read lines from the save game file and save its values to variables
	}
	
	//returns name
	public String getName()
	{
		return name;
	}
	
	// sets the name (in the case of oppoenent)
	public void setName(String newName)
	{
		name = newName;
	}
	
	//returns health stat
	public double getHealth()
	{
		return healthStat;
	}
	
	//returns strength stat
	public double getStrength()
	{
		return strengthStat;
	}
	
	//returns defense stat
	public double getDefense()
	{
		return defenseStat;
	}
	
	//returns dexterity stat
	public double getDexterity()
	{
		return dexterityStat;
	}
	
	//returns equipped armor
	public String getArmor()
	{
		return armor;
	}
	
	//returns equipped weapon
	public String getWeapon()
	{
		return weapon;
	}
	
	//returns light armor level
	public int getLightArmorLevel()
	{
		return lightArmorLevel;
	}
	
	//adds 1 to light armor level
	public void increaseLightArmorLevel()
	{
		lightArmorLevel++;
	}
	
	//returns medium armor level
	public int getMediumArmorLevel()
	{
		return mediumArmorLevel;
	}
	
	//adds 1 to medium armor level
	public void increaseMediumArmorLevel()
	{
		mediumArmorLevel++;
	}
	
	//returns heavy armor level
	public int getHeavyArmorLevel()
	{
		return heavyArmorLevel;
	}
	
	//adds 1 to heavy armor level
	public void increaseHeavyArmorLevel()
	{
		heavyArmorLevel++;
	}
	
	//returns sword weapon level
	public int getSwordWeaponLevel()
	{
		return swordWeaponLevel;
	}
	
	//adds 1 to sword weapon level
	public void increaseSwordWeaponLevel()
	{
		swordWeaponLevel++;
	}
	
	//returns mace weapon level
	public int getMaceWeaponLevel()
	{
		return maceWeaponLevel;
	}
	
	//adds 1 to mace weapon level
	public void increaseMaceWeaponLevel()
	{
		maceWeaponLevel++;
	}
	
	//returns knife weapon level
	public int getKnifeWeaponLevel()
	{
		return knifeWeaponLevel;
	}
	
	//adds 1 to knife weapon level
	public void increaseKnifeWeaponLevel()
	{
		knifeWeaponLevel++;
	}
	
	//returns health training level
	public int getHealthTrainingLevel()
	{
		return healthTrainingLevel;
	}
	
	//adds 1 health training level and increases health by 5
	public void increaseHealth()
	{
		healthTrainingLevel++;
		healthStat += 5;
	}
	
	//returns strength training level
	public int getStrengthTrainingLevel()
	{
		return strengthTrainingLevel;
	}
	
	//adds 1 to strength training level and increases strength by .01
	public void increaseStrength()
	{
		strengthTrainingLevel++;
		strengthStat += 0.01;
	}
	
	//returns defense training level
	public int getDefenseTrainingLevel()
	{
		return defenseTrainingLevel;
	}
	
	//adds 1 to defense training level and increases defense by .01
	public void increaseDefense()
	{
		defenseTrainingLevel++;
		defenseStat += 0.01;
	}
	
	//returns dexterity training level
	public int getDexterityTrainingLevel()
	{
		return dexterityTrainingLevel;
	}
	
	//adds 1 to dexterity training level and increases dexterity by .01
	public void increaseDexterity()
	{
		dexterityTrainingLevel++;
		dexterityStat += 0.01;
	}
	
	//returns promotion level
	public int getPromotionLevel()
	{
		return promotionLevel;
	}
	
	//adds 1 to promotion level
	public void increasePromotionLevel()
	{
		promotionLevel++;
	}
	
	//returns balance
	public int getBalance()
	{
		return balance;
	}
	
	//increases balance by a given int
	public void increaseBalance(int amount)
	{
		balance += amount;
	}
	
	//prints inventory
	public void printInventory()
	{
		if(inventoryLength() == 0)
		{
			System.out.println("Your Inventory is currently empty.");
		}
		else
		{
			System.out.println("Inventory: ");
			for(int i = 0; i < inventory.length; i++)
			{
				//System.out.println((i+1) + ": " + inventory[i]);
				System.out.println((i+1) + ": " + translateItemCode(inventory[i]));
			}
		}
	}
	
	//gets the length of the player's inventory
	public int inventoryLength()
	{
		return inventory.length;
	}
	
	//adds item to inventory
	public void addToInventory(String itemToAdd)
	{
		// create a new string array 1 object longer than the exisitng inventory
		String[] newInventory = new String[inventoryLength() + 1];
		
		// set new inventory values to existing inventory values
		for(int i = 0; i < inventoryLength(); i++)
		{
			newInventory[i] = inventory[i];
		}
		
		// add the new item to the final index of the new inventory
		newInventory[newInventory.length-1] = itemToAdd;
		
		// make the inventory the new inventory
		inventory = newInventory;
	}
	
	// for opponent use to equip a random pice of gear from the first 3 and and last 3 indeces of the inventory
	public void equipOpponentGear()
	{
		// equip one of the first 3 and last 3 of the fixed inventory of the opponent
		Random random = new Random();
		int randomInt = random.nextInt(3);
		equip(randomInt);
		equip(randomInt + 3);
	}
	
	//equips item from inventory
	public void equip(int inventoryLocation)
	{	
		// create a string value for the current item
		// create a string value for the item to be equiped
		String newItem = inventory[inventoryLocation];
		
		// Create a string value for the item type
		String itemType = newItem.substring(0,1);
		
		// check if the new item is armor or weapon
				//A and R item types are armor
				//W and F item types are weapons
		if(itemType.equals("A") || itemType.equals("R"))
		{
			// set the item that was in the inventory to the item that was equiped
			inventory[inventoryLocation] = armor;
			// set the currently equiped item to the new item from the inventory
			armor = newItem;
		}
		else if(itemType.equals("W") || itemType.equals("F"))
		{
			// set the item that was in the inventory to the item that was equiped
			inventory[inventoryLocation] = weapon;
			// set the currently equiped item to the new item from the inventory
			weapon = newItem;
		}
	}
	
	//returns grand champion name
	public String getGrandChampionName()
	{
		return grandChampionName;
	}
	
	//returns whether or not the player has been defeated.
	public boolean isDefeated()
	{
		return defeat;
	}
	
	//updates player defeat status to true.
	public void setDefeat()
	{
		defeat = true;
		
	}
	
	// translate gear to readable String
	public String translateItemCode(String itemCode)
	{
		// get the item type (A for armor, W for weapon, R for ragged Cloth, F for Fist)
		String itemType = itemCode.substring(0,1);
		
		//get the item subtype:
			//Armor
				// L for light armor
				// M for medium Armor
				// H for heavy armor
			//Weapon
				// K for knife
				// S for sword
				// M for mace
			// ragged cloth and fist are fixed types with no subtype
		String itemSubType = itemCode.substring(1,2);
		
		// get the item level 1-5
		int itemLevel = Integer.parseInt(itemCode.substring(2, 3));
		
		String item = new String();
		
		// check if the item is armor or weapon
		// armor
		if(itemType.equals("A"))
		{
			//check what kind of armor the item is
			//light
			if(itemSubType.equals("L"))
			{
				item = "Light Armor Level " + itemLevel;
			}
			//medium
			else if(itemSubType.equals("M"))
			{
				item = "Medium Armor Level " + itemLevel;
			}
			//heavy
			else if(itemSubType.equals("H"))
			{
				item = "Heavy Armor Level " + itemLevel;
			}
		}
		// weapon
		else if(itemType.equals("W"))
		{
			// check what kind of weapon the item is
			//knife
			if(itemSubType.equals("K"))
			{
				item = "Knife Level " + itemLevel;
			}
			//sword
			else if(itemSubType.equals("S"))
			{
				item = "Sword Level " + itemLevel;
			}
			//mace
			else if(itemSubType.equals("M"))
			{
				item = "Mace Level " + itemLevel;
			}
		}
		else if(itemType.equals("F"))
		{
			return "Fists";
		}
		else if(itemType.equals("R"))
		{
			return "Ragged Cloth";
		}
		
		return item;
	}
	
	public void printStats()
	{
		System.out.print("Name:" + name + " \n" +
				"Health: " + healthStat + " \n" + 
				"Strength: " + strengthStat + " \n" +
				"Defense: " + defenseStat + " \n" +
				"Dexterity: " + dexterityStat + " \n" +
				"Equiped Armor: " + translateItemCode(armor) + " \n" +
				"Equiped Weapon: " + translateItemCode(weapon) + " \n" +
				"Light Armor Level: " + lightArmorLevel + " \n" +
				"Medium Armor Level: " + mediumArmorLevel + " \n" +
				"Heavy Armor Level: " + heavyArmorLevel + " \n" +
				"Sword Level: " + swordWeaponLevel + " \n" +
				"Mace Level: " + maceWeaponLevel + " \n" +
				"Knife Level: " + knifeWeaponLevel + " \n" +
				"Health Training Level: " + healthTrainingLevel + " \n" +
				"Strength Training Level: " + strengthTrainingLevel + " \n" +
				"Defense Training Level: " + defenseTrainingLevel + " \n" +
				"Dexterity Training Level: " + dexterityTrainingLevel + " \n" +
				"Pomotion Level: " + promotionLevel + " \n" +
				"Balance: " + balance + "G" + " \n");
	}
	
	//saves game information to file by overwriting the existing one.
	public void saveGame() throws IOException
	{
		System.out.println("Saving game...");
		
		// create a string to save the inventory if the inventory has items or not
		String inventoryToSave = "";
		if(inventoryLength() != 0)
		{
			inventoryToSave = Arrays.toString(inventory).substring(1, (inventory.length*5-1)) ;
		}
		// values in string form
		String newGameInfo = new String("Name:" + name + "\n" +
				"Health:" + healthStat + "\n" + 
				"Strength:" + strengthStat + "\n" +
				"Defense:" + defenseStat + "\n" +
				"Dexterity:" + dexterityStat + "\n" +
				"Armor:" + armor + "\n" +
				"Weapon:" + weapon + "\n" +
				"ArmorLight:" + lightArmorLevel + "\n" +
				"ArmorMedium:" + mediumArmorLevel + "\n" +
				"ArmorHeavy:" + heavyArmorLevel + "\n" +
				"WeaponSword:" + swordWeaponLevel + "\n" +
				"WeaponMace:" + maceWeaponLevel + "\n" +
				"WeaponKnife:" + knifeWeaponLevel + "\n" +
				"TrainHealth:" + healthTrainingLevel + "\n" +
				"TrainStrength:" + strengthTrainingLevel + "\n" +
				"TrainDefense:" + defenseTrainingLevel + "\n" +
				"TrainDexterity:" + dexterityTrainingLevel + "\n" +
				"PomotionLevel:" + promotionLevel + "\n" +
				"Balance:" + balance + "\n" +
				"Inventory:" + inventoryToSave + "\n" +
				"GrandChampion:" + grandChampionName +"\n" +
				"Defeat:" + defeat);
		
		// put newGameInfo into the existing file by overwriting existing information
		File newGameFile = new File(gameFileName);
		FileWriter newGame = new FileWriter(newGameFile);
		newGame.write(newGameInfo);
		newGame.close();
		
		System.out.println("Game Saved!");
		
	}
	
	//calculates player hit chance
	public double calculateHitChance(int hitDifficulty)
	{
		// calculate hit difficulty player dexterity then by by armor and weapon dexterity.
		double hitChance = hitDifficulty*dexterityStat;
		hitChance *= calculateArmorDexterity();
		hitChance *= calculateWeaponDexterity();
		
		return hitChance;
	}
	
	// calculates damage done based on weapons and attack stat
	public double calculateDamage(int baseDamage)
	{
		// multiply base damage by strength then by weapon attack
		double totalDamage = baseDamage*strengthStat;
		totalDamage *= getWeaponAttack();
		
		return totalDamage;
	}
	
	//calculates defense based on armor and defense stat
	public double calculateDefense(double damageDealt)
	{
		// multiply damage dealt by defense stat then by armor defense
		double totalDefense = damageDealt*(2-defenseStat);
		totalDefense *= getArmorDefense();
		return totalDefense;
	}
	
	// returns how a weapon will affect damage
	public double getWeaponAttack()
	{
		double weaponAttack = 1;
		// get the weapon subType and weapon Level
		String itemSubType = weapon.substring(1,2);
		int itemLevel = Integer.parseInt(weapon.substring(2, 3));
		
		// find the weapon attack amount times the item level and corresponding level changes
		//knife
		if(itemSubType.equals("K"))
		{
			weaponAttack += 0.05 + 0.01*itemLevel;
		}
		//sword
		else if(itemSubType.equals("S"))
		{
			weaponAttack += 0.1 + 0.02*itemLevel;
		}
		//mace
		else if(itemSubType.equals("M"))
		{
			weaponAttack += 0.15 + 0.03*itemLevel;
		}
		
		return weaponAttack;
	}
	
	// returns how armor will affect defense
	public double getArmorDefense()
	{
		double armorDefense = 1;
		// get armor type and level
		String itemSubType = armor.substring(1,2);
		int itemLevel = Integer.parseInt(armor.substring(2, 3));
		
		//light
		if(itemSubType.equals("L"))
		{
			armorDefense += 0.1 + 0.01*itemLevel;
		}
		//medium
		else if(itemSubType.equals("M"))
		{
			armorDefense -= 0.1 + 0.01*itemLevel;
		}
		//heavy
		else if(itemSubType.equals("H"))
		{
			armorDefense -= 0.15 + 0.025*itemLevel;
		}
		return armorDefense;
	}
	
	//returns how weapons will affect dexterity
	public double calculateArmorDexterity()
	{
		// initialize armor dexterity
		double armorDexterity = 1;
		
		// get armor type and level
		String itemSubType = armor.substring(1,2);
		int itemLevel = Integer.parseInt(armor.substring(2, 3));
		
		if(itemSubType.equals("L"))
		{
			armorDexterity += 0.15 + 0.025*itemLevel;
		}
		else if(itemSubType.equals("H"))
		{
			armorDexterity -= 0.05 + 0.01*itemLevel;
		}
		
		return armorDexterity;
	}
	
	// returns how armor will affect dexterity
	public double calculateWeaponDexterity()
	{
		// initialize weapon dexterity
		double weaponDexterity = 1;
		
		// get the weapon subType and weapon Level
		String itemSubType = weapon.substring(1,2);
		int itemLevel = Integer.parseInt(weapon.substring(2, 3));
		
		if(itemSubType.equals("K"))
		{
			weaponDexterity += 0.05 + 0.03*itemLevel;
		}
		else if(itemSubType.equals("M"))
		{
			weaponDexterity -= 0.05 + 0.01*itemLevel;
		}
		return weaponDexterity;
	}

}
