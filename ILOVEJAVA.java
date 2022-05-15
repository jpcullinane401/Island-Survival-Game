package funnygame;
import java.util.Scanner;
import java.util.Random;

public class theGameFruit {
	
	
	// intregal vars
	static int day = 0;
	static int choice;
	static int userResponse;
	static int rng;
	static int fruitRng;
	static int toxic;
	static boolean isToxic;
	static int chanceForDisaster;
	static int userChoice;
	
	static boolean fuckThisShit = false;
	
	static int playerHP = 100;
	static int playerStamina = 100;
	static int foodSupply = 0;
	static int internalSector = 0;
	static int sector = 0;
	
	static int playerAtk = 10;
	static int playerDef = 5;
	
	//
	static int playerLevel = 1;
	static int playerExp = 0;
	static int expToNext = 0;
	
	
	static int enemyRNG;
	static int enemyID;
	static int missChance;
	static int dmgRNG;
	static int damage;
	static boolean fightWin = false;
	static boolean pBlock = false;
	static int escapeOdds;
	//static boolean curlUp = false;
	static boolean wasInCombat = false;
	
	
	static String[] areaNames = {"Thicket", "Tropics", "Deep Woods", "Mountain" };
	static String area;
	
	// static int woodSupply = 0;
	// static int rockSupply = 0;
	
	
	
	
	static int numbFruit;
	
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
		printMenu();
		while (gaming == true) {
			
			rng = random.nextInt(10);
			userResponse = keyboard.nextInt();
			line();
			switch(userResponse) {
			
			//SEARCH FOR FRUITS
			
			case 2:
				System.out.println("You chose to search for fruits.");
				System.out.println("Would you like to search along the beach or deeper in the jungle? (1 or 2)");
				userResponse = keyboard.nextInt();
				
				// SEARCH FOR FRUIT ON BEACH
				
				if (userResponse == 1) {
					System.out.println("You decide to search along the beach");
					if (rng > 3 && rng < 8) {
						fruitRng = random.nextInt(4);
						System.out.println("You found " + fruitRng + " coconuts(s)");
						System.out.println("Each coconut is 5 food points.");
						foodSupply = foodSupply + (fruitRng*5);
						System.out.println("Your food supply is now " + foodSupply);
						
					} else if (rng >= 8) {
						System.out.println("Uh oh, you couldn't find anything.");
						System.out.println("Better luck next time I guess");
						
					} else {
						fruitRng = random.nextInt(11);
						System.out.println("You found " + fruitRng + " pineapples!!");
						System.out.println("Each pineapple is 8 food points.");
						foodSupply = foodSupply + (fruitRng*8);
						System.out.println("Your food supply is now " + foodSupply);
					}
				}
				
				// SEARCH FOR FRUIT ON JUNGLE
				
				if (userResponse == 2) {
					System.out.println("You decide to search deeper into the jungle.");
					if (rng >= 7) {
						fruitRng = random.nextInt(60);
						System.out.println("You found " + fruitRng + " wild berries!");
						System.out.println("Each wild berry is 1 food point.");
						foodSupply = foodSupply + (fruitRng*1);
						System.out.println("Your food supply is now " + foodSupply);
					}
					if (rng < 7 && rng != 0 && rng != 1) {
						fruitRng = random.nextInt(14);
						System.out.println("You found " + fruitRng + " strawberries.");
						System.out.println("Each strawberry is 2 food points.");
						foodSupply = foodSupply + (fruitRng*2);
						System.out.println("Your food supply is now " + foodSupply);
					}
					if (rng == 0 || rng == 1) {
						fruitRng = random.nextInt(70);
						toxic = random.nextInt(2);
						System.out.println("You found " + fruitRng + " wild berries!");
						System.out.println("...however, you suspect that they may be toxic.");
						System.out.println("Do you take them? (1 yes/2 no)");
						userResponse = keyboard.nextInt();
						if (userResponse == 1) {
							if (toxic == 1) {
								System.out.println("Thankfully, the berries were not toxic.");
								System.out.println("Each wild berry is 1 food point.");
								foodSupply = foodSupply + (fruitRng*1);
								System.out.println("Your food supply is now " + foodSupply);
							} else {
								System.out.println("Oh no! The berries were toxic!");
								System.out.println("You feel sick....");
								playerHP = playerHP - (fruitRng / 2);
								System.out.println("- " + (fruitRng / 2) + " HP");
								System.out.println("Your food supply is " + foodSupply);
							}
							
							
						} else {
							System.out.println("You decide to leave the berries.");
							System.out.println("Your food supply is " + foodSupply);
						}
						
					}
					
				}
				sleepy();
				break;
				
				
				// OPTION 2 ENDS HERE
				
				
				// -------------------------------------------------- 
				//						THE FOLLOWING
				//						IS THE CODE 
				//						FOR THE ADVENTURE
				//
				// -------------------------------------------------- 
				
			case 1:
				System.out.println("You chose to adventure.");
				fuckThisShit = false;
				inDialogue = true;
				if (internalSector <= 10) {
					area = areaNames[0];
				}
				if (internalSector <= 20 && internalSector > 10) {
					area = areaNames[1];
				}
				
				
				
				System.out.println("You step deeper into the jungle, as the leaves seem to close behind you...");
				if (wasInCombat == false) {
					while (inDialogue == true) {
						line();
						System.out.println(area + " " + sector);
						//userResponse = keyboard.nextInt();
						thicketEvents();
					}
					
					
					
					
				}
				
				
				sleepy();
				break;
			
				
				
				
				
				
			case 4:
				System.out.println("HP: " + playerHP);
				System.out.println("Attack: " + playerAtk);
				System.out.println("Defense: " + playerDef);
				System.out.println("LVL: " + playerLevel);
				System.out.println("XP: " + playerExp + "/" + expToNext);
				
				menu();
				break;
			
				
				
				
				
				
				
			case 5:
				System.out.println("You chose to gather stone.");
				sleepy();
				break;
				
				
				
				
				
				
				
			case 3: // Player chooses to rest in the sand
				System.out.println("You chose to lay facedown in the sand.");
				System.out.println("..............");
				
				// low chance to sleep great, which recovers more stamina and HP
				if (rng >= 6) {
					System.out.println("You slept great! Your HP has recovered by 30.");
					playerHP = playerHP + 30;
				} else {
					System.out.println("You slept fine. Your HP has stayed the same");
				}
				//HP and stamina cap
				if (playerHP > 100) {
					playerHP = 100;
				}
			
				sleepy();
				break;
				
				
				
				
				
			case 7:
				
			
			default:
				System.out.println("Who are you talking to?");
				System.exit(0);
				break;

			} 
		}
			
		}

	
	
	public static void printMenu() {
		day++;
		System.out.println("DAY " + day);
		System.out.println("You live to see another day.");
		menu();
		
		
	}
	
	public static void epilogue() {
		System.out.println("You wake up on an island, covered in sand and seaweed. "
				+ "You have no idea how you got here.");
		System.out.println("Might as well try to survive.");
		System.out.println("You have a few options right now. You can:");
		
		
	}
	public static void sleepy() {
		wasInCombat = true;
		battle = false;
		chanceForDisaster = random.nextInt(80);
		System.out.println("It's getting late...");
		foodSupply = foodSupply - 5;
		if (foodSupply <= 0) {
			System.out.println("Your stomach growls. You have no food.");
			System.out.println("- 10 HP");
			foodSupply = 0;
			playerHP = playerHP - 10;

		} else {
			System.out.println("You eat some of your saved food.");
		}
		
		if (gameOver == true) {
			System.out.println("Your health hit 0. You have died...");
			System.out.println("GAME OVER");
			System.exit(0);

		}
		
		if (chanceForDisaster > 54) {
			System.out.println("While you were sleeping, the island was hit by a storm!"); 
			System.out.println("It seems like you lost your supplies");
			foodSupply = 0;
		}
		
		
		line();
		line();
		printMenu();
	}
	public static void menu() {
		battle = false;
		line();
		System.out.println("HP: " + playerHP + "/100");
		System.out.println("Food supply: " + foodSupply);
		
		line();
		System.out.println("What will you do?");
		System.out.println("1. Adventure");
		System.out.println("2. Search for fruits");
		System.out.println("3. Rest in the sand");
		System.out.println("4. Check inventory");
		System.out.println("(Enter a number to pick)");
		
		
	}
	public static void line() {
		System.out.println("-----------------------------");
	}
	
	public static void thicketEvents() {
		int rngThicket = random.nextInt(10);
		if (rngThicket < 5 && rngThicket > 1 ) {
			enemyID = 1;
			battle = true;
			battle("Hot Pepper Snail", 25, 10, 0);
			
			/*String enemyName = "Hot Pepper Snail";
			int enemyHP = 25;
			int enemyAtk = 5;
			int speed = 1; */
		}
		if (rngThicket >= 5 && rngThicket< 10) {
			enemyID = 2;
			battle = true;
			battle("Caterpilla", 20, 5, 0);
			
		}
		
		
		
		
	}
	public static void battle (String enemyName, int enemyHP, int enemyAtk, int enemySpeed) {
		pBlock = false;
		pTurn = true;
		eTurn = false;
		wasInCombat = true;
		
		System.out.println(enemyName + " attacks!");
		
		//players turn
	
		while (battle == true) {
			
			pBlock = false;
	
			while (pTurn == true && eTurn == false && battle == true) {
				pBlock = false;
				System.out.println("1. Attack");
				System.out.println("2. Block");
				System.out.println("3. Run");
				userResponse = keyboard.nextInt();
				switch (userResponse) {
				// player attacks 
				case 1: 
					System.out.println("You attack!");
					pBlock = false;
					missChance = random.nextInt(10);
					if (missChance < 3) {
						System.out.println("...but you missed.");
						pTurn = false;
						eTurn = true;
						break;
						
					} else {
						damage = (playerAtk);
		
					
						System.out.println(enemyName + " takes " + damage + " damage!");
						enemyHP = enemyHP - damage;
						if (enemyHP <= 0) {
							System.out.println(enemyName + " dies.");
							fightWin = true;
							battle = false;
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
							System.out.println("You ran all the back to camp");
							battle = false;
							sleepy();
							
						} else {
							System.out.println("...but you tripped.");
							pTurn = false;
							eTurn = true;
							
						}
						
						
						
					}
					
					break;
					
					
					
					}
				
				}
				
				
			 // end of battle 
			
		
			// enemy turn
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
		// end of the actual battle code
		if (battle == false) {
			sector++;
			goOn();
		}
		
		
		
		
	} // end of battle module
	
	public static void goOn () {
		 	
			System.out.println("Would you like to progress to the next sector? (1y/2n)");
			userChoice = keyboard.nextInt();
			if (userChoice == 2) {
				System.out.println("You record your findings and report back to camp");
				fuckThisShit = true;
				sleepy();
				
			
			
			}
		
		
		

		
	} // end of public static void goOn
	
	
}
		
		
	
