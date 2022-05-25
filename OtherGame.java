package app;
import java.util.Scanner;
import java.util.Random;


public class OtherGame {
	
	
	static int day = 0;
	static int choice;
	static int userResponse;
	static int rng;
	static int userChoice;
	
	static int areaRn;
	static int playerHPmax = 100;
	static int playerHP = playerHPmax;
	
	static int internalSector = 0;
	static int sector = 0;
	static int areaRng;
	static int playerAtk = 10;
	static int playerDef = 5;
	static int playerLevel = 1;
	static int playerExp = 0;
	static int expToNext = 0;
	static int enemyRNG;
	static int enemyID;
	static int missChance;
	static int dmgRNG;
	static int damage;
	static int expCap;
	static int expLvl;
	static int expLeft;
	static int expAmt = 0;
	
	static boolean glove = false;
	static boolean glasses = false;
	static boolean landi = false;
	static boolean hook = false;
	
	
	static boolean fightWin = false;
	static boolean pBlock = false;
	static int escapeOdds;
	//static boolean curlUp = false;
	static boolean wasInCombat = false;
	
	
	// Determines the name of the area, every 10 floors the player reaches a new area
	
	static String[] items = {"Small HP Potion", "Glasses", "Bladed Glove", "Grappling Hook", "HP Potion" };
	static String[] inventory = {""};
	static String area;
	
	// static int woodSupply = 0;
	// static int rockSupply = 0;
	
	// various boolean variables  
	static boolean stopAdventure = false;
	static boolean gaming = true;
	static boolean fishingRod = false;
	static boolean fruitLoop = true;
	static boolean gameOver = false;
	static boolean inDialogue = false;
	static boolean battle = false;
	static boolean pTurn = true;
	static boolean eTurn = false;
	
	static Scanner keyboard = new Scanner(System.in);
	static Random random = new Random();
	
	
	

	public static void main(String[] args) {
		while (gaming == true) {
			glasses = false;
			glove = false;
			expMachine();
			getAreaName();
			internalSector++;
			sector++;
			if (internalSector <= 10) { // FOREST
				forestEvent();
			} else if (internalSector > 10 && internalSector < 20) { // THICKET
				
				
			} else if (internalSector > 20 && internalSector <= 35) { // CAVE
				
			}
			
			
		}
	}
	
	public static void battle (String enemyName, int enemyHP, int enemyAtk, int enemyID, int expDrop) {
		battle = true;
		pBlock = false;
		pTurn = true;
		eTurn = false;
		glasses = false;
		glove = false;
		wasInCombat = true;
		System.out.println(enemyName + " attacks!");
		
		//players turn
		while (battle == true) {
			line();
			expMachine();
			pBlock = false;
			glove = false;
			glasses = false;
			hook = false;
			if (playerHP <= 0) {
				System.out.println("You died.");
				gaming = false;
				System.exit(0);
			}
			while (pTurn == true && eTurn == false && battle == true) {
				pBlock = false;
				System.out.println("Your HP: "+playerHP + "/"+playerHPmax);
				System.out.println("Inventory: "+inventory[0]);
				System.out.println("Level: " + playerLevel);
				System.out.println("EXP: "+ expAmt + "/" + expCap);
				System.out.println("1. Attack");
				System.out.println("2. Block");
				System.out.println("3. Run");
				System.out.println("4. Item");
				userResponse = keyboard.nextInt();
				line();
				switch (userResponse) {
				// player attacks 
				case 1: 
					System.out.println("You attack!");
					pBlock = false;
					missChance = random.nextInt(10);
					if (glasses == true) {
						missChance = 10;
					}
					if (missChance < 3) {
						System.out.println("...but you missed.");
						pTurn = false;
						eTurn = true;
						break;
					} else {
						if (glove == true) {
							damage = playerAtk * 2;
						} else if (glove == false){
							damage = (playerAtk);
						}
							System.out.println(enemyName + " takes " + damage + " damage!");
							enemyHP = enemyHP - damage;
							if (enemyHP <= 0) {
								System.out.println(enemyName + " dies.");
								fightWin = true;
								battle = false;
								System.out.println("You gained " + expDrop + " experience points.");
								expAmt = expAmt + expDrop;
								break;
						}

						pTurn = false;
						eTurn = true;
						
						if(battle == false) {
							break;
						}
						
					}
				case 2:	
					if (userResponse ==2 ) {
					
						System.out.println("You block.");
						pBlock = true;
						pTurn = false;
						eTurn = true;
					}
					
					break;
				case 3:
					if (userResponse ==3) {
						System.out.println("You tried to run away.");
						escapeOdds = random.nextInt(100);
						if (escapeOdds > 70) {
							System.out.println("You got away!");
							System.out.println("You ran all the way to the next area!");
							battle = false;
							break;
							
						} else {
							System.out.println("...but you tripped.");
							pTurn = false;
							eTurn = true;
							
						}
						
						
						
					}
					
					break;
					
					
					
					
				case 4:
					if (userResponse ==4) {
						useItem();
					}
					break;
					
					
				}
				
			}
			
				
			
			
				
				
			 // end of battle 
			
		
			// enemy turn
			line();
			while (pTurn == false && eTurn == true) {
				// HOT PEPPER SNAIL
				if (enemyID == 1) {
				enemyRNG = random.nextInt(10);
					if (enemyRNG < 4) {
						System.out.println("Hot Pepper Snail just stood there, being amazing!");

					}
					if (enemyRNG > 4) {
						System.out.println("Hot Pepper Snail shot a blast of fire.");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - ((enemyAtk + enemyRNG) / 2);
						System.out.println("- " + ((enemyAtk + enemyRNG) / 2) + " HP");
						System.out.println("Your HP is " + playerHP);
						
						
					} else if (enemyRNG == 4) {
						System.out.println("Hot Pepper Snail shot a super spicy blast!");
						System.out.println("- " + (enemyAtk * 3) + " HP");
						playerHP = playerHP - (enemyAtk * 3);
						System.out.println("Your HP is " + playerHP);
						
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
				}
				
				// CATERPILLA
				
				if (enemyID == 2) {
					enemyRNG = random.nextInt(10);
					if (enemyRNG < 4) {
						System.out.println("Caterpilla rolled into you!");
						System.out.println("- " + (enemyAtk * 2) + " HP");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - (enemyAtk * 2);
						System.out.println("Your HP is " + playerHP);

					}
					if (enemyRNG > 4) {
						System.out.println("Caterpilla released some spores.");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - ((enemyAtk + enemyRNG) / 2);
						System.out.println("- " + ((enemyAtk + enemyRNG) / 2) + " HP");
						System.out.println("Your HP is " + playerHP);
						
						
					} else if (enemyRNG == 4) {
						System.out.println("Caterpilla is hanging around!");
						
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
				}
				
		
			}
		
		
		}

	} // end of battle module
	
	public static void forestEvent() {
		System.out.println("Forest " + sector);
		areaRng = random.nextInt(13);
		if (areaRng < 4) {
			battle("Hot Pepper Snail", 25, 10, 1, 25);
		} else if (areaRng > 4 && areaRng < 10) {
			battle("Caterpilla", 20, 10, 2, 15);
		} else if (areaRng == 4) {
			System.out.println("You found a hot spring in a forest clearing.");
			System.out.println("The warm water nourishes your soul. Full HP!");
			line();
			playerHP = playerHPmax;
		} else if (areaRng >= 10) {
			System.out.println("You found a treasure chest.");
			loot();
			line();
			
		}
		
	}
	
	public static void thicketEvent() {
		System.out.println("Thicket " + sector);
		areaRng = random.nextInt(13);
		if (areaRng < 4) {
			battle("Hot Pepper Snail", 25, 10, 1, 25);
		} else if (areaRng > 4 && areaRng < 10) {
			battle("Caterpilla", 20, 10, 2, 15);
		} else if (areaRng == 4) {
			System.out.println("You found a hot spring in a forest clearing.");
			System.out.println("The warm water nourishes your soul. Full HP!");
			line();
			playerHP = playerHPmax;
		} else if (areaRng >= 10) {
			System.out.println("You found a treasure chest.");
			loot();
			line();
			
		}
		
	}
	
	public static void caveEvent() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void line() { 
		System.out.println("--------------------------");		
	}
	
	public static void expMachine() {
		
		expCap = 50*playerLevel;
		expLeft = expCap - expAmt;
		
		if (expAmt >= expCap) {
			playerLevel++;
			expAmt = 0;
			levelUp();
			
		}
		
	}
	
	public static void levelUp() {
		System.out.println("Wow! Level up!");
		line();
		playerAtk = playerAtk + 5;
		playerHPmax = playerHPmax + 10;
		playerHP = playerHPmax;
	}
	
	public static void loot() {
		rng = random.nextInt(4);
		System.out.println("You found a " + items[rng]);
		
		if (inventory[0].isEmpty()) {
			System.out.println("You have a " + items[rng] + " in your inventory now");
			inventory[0] = items[rng];
		} else {
			System.out.println("Do you want to replace your current item? (" + inventory[0] + ") (1y/2n)");
			userResponse = keyboard.nextInt();
			if (userResponse == 1) {
				inventory[0] = items[rng];
			} else {
				System.out.println("You tossed away the "+ items[rng]);
			}
		}
		
	}
	
	public static void useItem() {
		if (inventory[0].isEmpty() || inventory[0] == "") {
			System.out.println("You do not have an item!");
		} else if (inventory[0] == items[0]) { //Small HP Potion
			inventory[0] = null;
			playerHP = playerHP + 50;
			System.out.println("You used your Small HP potion and regained 50 HP points.");
			
		} else if (inventory[0] == items[1]) { //Glasses
			inventory[0] = null;
			glasses = true;
			System.out.println("You put on your pair of very breakable glasses.");
			System.out.println("They increase your accuracy for the current battle!");
			
		} else if (inventory[0] == items[2]) { // Bladed Glove
			inventory[0] = null;
			glove = true;
			System.out.println("You put on the pair of sharp gloves");
			System.out.println("They increase your attack damage for the current battle!");
			
		} else if (inventory[0] == items[3]) { // Grappling Hook
			inventory[0] = null;
			System.out.println("You used your grappling hook to flee!");
			hook = true;
			battle = false;
			rng = (random.nextInt(5)) + 1;
			sector = sector + rng;
			
		} else if (inventory[0] == items[4]) { // HP Potion
			inventory[0] = null;
			playerHP = playerHPmax;
			System.out.println("You used your HP potion and healed back to full HP");
		}
		
		
	}
	
	public static void getAreaName() {
		
		if (internalSector == 11) {
			sector = 1;
		} else if (internalSector == 21) {
			sector = 1;
		}
	}
	
	
	
}
