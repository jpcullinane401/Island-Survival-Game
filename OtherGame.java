package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// "ISLAND ADVENTURE GAME"
// CODE BY: John "JJ" Cullinane
// With assistance by @FloatingMilkshake



// The program is currently unfinsihed and will not proceed past floor 25

public class OtherGame {
	
	
	static int day = 0;
	static int choice;
	static int userResponse;
	static int rng;
	static int userChoice;
	
	static int areaRn;
	static int playerHPmax = 100;
	static int playerHP = playerHPmax;
	static int startingOption;
	
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
	static int wantsFish;
	static int dropChance;
	static int goldfishCount;
	static int fishRNG;
	static int bombbushCount;
	static boolean bananaSlamma;
	
	static boolean glove = false;
	static boolean glasses = false;
	static boolean landi = false;
	static boolean hook = false;
	static boolean spiky;
	static boolean userChoicePending;
	
	static boolean forestRune = false;
	static boolean bugEgg = false;
	static boolean pepper = false;
	
	static boolean fightWin = false;
	static boolean pBlock = false;
	static int escapeOdds;
	//static boolean curlUp = false;
	static boolean wasInCombat = false;
	
	
	// Determines the name of the area, every 10 floors the player reaches a new area
	
	static String[] items = {"Small HP Potion", "Glasses", "Bladed Glove", "Grappling Hook", "Fishing Rod", "HP Potion"};
	static String[] inventory = {""};
	static List<String> tboi= new ArrayList<>();
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
		
		//List<String> mylist = new ArrayList<>();
		
		//mylist.add("wow");
		//mylist.add("wdfdf");
		
		//for(String str : mylist) {
		//System.out.println(str);
		//}
		
		System.out.println("Would you like to choose a starting item? (1y/2n)");
		startingOption = promptForInt();
		if (startingOption == 1) {
			System.out.println("What would you like?");
			System.out.println("1. Small HP Potion");
			System.out.println("2. Glasses");
			System.out.println("3. Bladed Glove");
			System.out.println("4. Grappling Hook");
			System.out.println("5. Fishing Rod");
			startingOption = promptForInt();
			if (startingOption == 1) {
				inventory[0] = items[0];
				System.out.println("You got a Small HP Potion");
			} else if (startingOption ==2 ) {
				inventory[0] = items[1];
				System.out.println("You got Glasses");
			} else if (startingOption ==3) {
				inventory[0] = items[2];
				System.out.println("You got a Bladed Glove");
			} else if (startingOption ==4) {
				inventory[0] = items[3];
				System.out.println("You got a Grappling Hook");
			} else if (startingOption ==5) {
				inventory[0] = items[4];
				System.out.println("You got a Fishing Rod");
			}
		}
		
		while (gaming == true) {
			glasses = false;
			glove = false;
			expMachine();
		
			internalSector++;
			sector++;
			
			if (pepper == true) {
				dropChance = random.nextInt(10);
				if (dropChance == 2) {
					internalSector++;
					sector++;
				}
			}
			
			line();
			if (internalSector <= 9) { // FOREST
				forestEvent();
			} else if (internalSector >= 10 && internalSector < 21) { // THICKET
				thicketEvent();
			} else if (internalSector > 20 && internalSector <= 35) { // CAVE
				caveEvent();
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
		bananaSlamma = false;
		spiky = false;

		bombbushCount = 3;
		goldfishCount = 5;
		
		wasInCombat = true;
		System.out.println(enemyName + " attacks!");
		
		//players turn
		while (battle == true) {
			line();
			expMachine();
			pBlock = false;
			hook = false;
			if (playerHP <= 0) {
				System.out.println("You died.");
				gaming = false;
				System.exit(0);
			}
			while (pTurn == true && eTurn == false && battle == true) {
				if (bugEgg == true) {
					System.out.println("Your Caterpilla Egg healed you for 2 HP.");
					playerHP = playerHP + 2;
					capHP();
				}
				pBlock = false;
				System.out.println("Your HP: "+playerHP + "/"+playerHPmax);
				System.out.println("Inventory: "+inventory[0]);
				System.out.println("Level: " + playerLevel);
				System.out.println("EXP: "+ expAmt + "/" + expCap);
				System.out.println("1. Attack");
				System.out.println("2. Block");
				System.out.println("3. Run");
				System.out.println("4. Item");
				System.out.println("5. Pray");
				System.out.println("6. Gear");
				userResponse = promptForInt();
				line();
				switch (userResponse) {
				// player attacks 
				case 1: 
					System.out.println("!!!You attack!!!");
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
							System.out.println("!!!!!" + enemyName + " takes " + damage + " damage!!!!!");
							enemyHP = enemyHP - damage;
							if (spiky == true) {
								System.out.println("The enemy's spikes poke into you!");
								System.out.println("- 5 HP");
								playerHP = playerHP - 5;
							}
							if (enemyHP <= 0) {
								System.out.println(enemyName + " dies.");
								
								
								if (enemyName == "Shark") {
									droppedItem(8);
									
								} else if (enemyName == "Caterpilla" || enemyName == "Spiked Caterpilla" ) {
									droppedItem(2);
								} else if (enemyName == "Hot Pepper Snail") {
									droppedItem(1);
								}
								
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
					
					
				case 5:
					if (userResponse ==5) {
						rng = random.nextInt(3);
						if (rng == 2) {
							System.out.println("Your prayers were answered!");
							playerHP = playerHP + 20;
							System.out.println("+ 20 HP");
							capHP();
						} else {
							System.out.println("But nobody heard...");
						}
						pTurn = false;
						eTurn = true;
					}
					break;
					
					
				case 6:
					if (userResponse == 6) {
						inventory();
						line();
					}
					
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
						
						
						damage = ((enemyAtk + enemyRNG) / 2);
						if (pBlock = true) {
							damage = damage / 2;
						}
						playerHP = playerHP - (damage);
						System.out.println("- " + (damage) + " HP");
						System.out.println("Your HP is " + playerHP);
						
						
					} else if (enemyRNG == 4) {
						System.out.println("Hot Pepper Snail shot a super spicy blast!");
						damage = enemyAtk * 3;
						if (pBlock == true ) {
							damage = damage / 2;
						}
						System.out.println("- " + (damage) + " HP");
						playerHP = playerHP - (damage);
						System.out.println("Your HP is " + playerHP);
					}
					
					pTurn = true;
					eTurn = false;
					pBlock = false;
				}
				
				// CATERPILLA
				
				if (enemyID == 2) {
					if (enemyHP > 0) {
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
					else {
						//rng = random.nextInt(7);
						rng = 4;
						if (rng == 4) {
							newItems(1);
						}
						
					}
				}
				
				// BANDIT
				
				if (enemyID == 4) {
					enemyRNG = random.nextInt(12);
					rng = random.nextInt(4);
					if (enemyRNG < 7) {
						System.out.println("Bandit shanked you!");
						System.out.println("- " + ((enemyAtk *rng) + 1 ) + " HP");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - ((enemyAtk * rng) + 1);
						System.out.println("Your HP is " + playerHP);

					}
					if (enemyRNG >= 7 && enemyRNG < 10) {
						rng = random.nextInt(3);
						if (inventory[0].isEmpty()) {
							System.out.println("Bandit tried to steal your item!");
							System.out.println("..but you don't have anything!");
						}
						if (rng >= 2) {
							System.out.println("Bandit stole your " + inventory[0]);
							inventory[0] = "";
							
						} else {
							System.out.println("Bandit flashed a rude gesture.");
						}
						
						
					} else if (enemyRNG >= 10){
						System.out.println("Bandit ate a banana.");
						enemyHP = enemyHP + 15;
						
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
				}
				
				
				
				// POISON IVY STICKER
				
				if (enemyID == 3) {
					enemyRNG = random.nextInt(10);
					rng = random.nextInt(10);
					if (enemyRNG <= 5) {
						System.out.println("Poison Ivy Sticker spat out poison!");
						System.out.println("- " + (enemyAtk *rng)  + " HP");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - (enemyAtk * rng);
						System.out.println("Your HP is " + playerHP);
						
					} else if (enemyRNG > 5) {
						System.out.println("Poison Ivy Sticker is sizing you up.");
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
				}
				
				
				// SPINED CATERPILLA
				
				if (enemyID == 5) {
					spiky = true;
					enemyRNG = random.nextInt(10);
					rng = random.nextInt(4);
					if (enemyRNG <= 7) {
						System.out.println("Spined Caterpilla rolled into you!");
						System.out.println("- " + (enemyAtk *rng)  + " HP");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - (enemyAtk * rng);
						System.out.println("Your HP is " + playerHP);
						
					} else if (enemyRNG >7) {
						System.out.println("Spined Caterpilla is rolling around.");
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
				}
				
				
				// BOMBBUSH
				
				if (enemyID == 6) {
					if (bombbushCount != 0) {
						System.out.println("The Bombbush counts: " + bombbushCount);
						bombbushCount--;
					} else {
						System.out.println("BOMBBUSH EXPLODES!");
						System.out.println("- " + (enemyAtk) + " HP");
						if (pBlock = true) {
							enemyRNG = enemyRNG / 2;
						}
						playerHP = playerHP - (enemyAtk);
						System.out.println("Your HP is " + playerHP);
						battle=false;
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
					
				}
				
				
				// GOLDFISH
				
				if (enemyID ==7) {
					if (goldfishCount != 0) {
						System.out.println("The goldfish is flopping around.");
						goldfishCount--;
					} else {
						System.out.println("The goldfish jumped back in the water and swam away...");
						battle=false;
					}
					pTurn = true;
					eTurn = false;
					pBlock = false;
					
				}
				
				// SHARK
				
				if (enemyID ==8) {
					if (enemyHP >0) {
						
					
						enemyRNG = random.nextInt(11);
						if (enemyRNG < 4) {
							System.out.println("Shark brandished a tooth!");
							System.out.println("- " + (enemyAtk * 2) + " HP");
							damage = enemyAtk * 2;
							if (pBlock = true) {
								damage = damage / 2;
							}
							playerHP = playerHP - damage;
							System.out.println("Your HP is " + playerHP);
	
						}
						if (enemyRNG > 4) {
							System.out.println("Shark came out chomping!");
							if (pBlock = true) {
								enemyRNG = enemyRNG / 2;
							}
							playerHP = playerHP - (enemyAtk);
							System.out.println("- " + (enemyAtk) + " HP");
							System.out.println("Your HP is " + playerHP);
							
							
						} else if (enemyRNG == 4) {
							System.out.println("Shark said something nasty.");
							
						}
						pTurn = true;
						eTurn = false;
						pBlock = false;
					} else {
						rng = random.nextInt(2);
						if (rng == 0) {
							newItems(0);
						} else {
							
						}
					}
					
					
				}
				
				// FOREST GUARDIAN
				
				if (enemyID == 9) {
					if (enemyHP >0) {
						if (bananaSlamma = false) {
							enemyRNG = random.nextInt(11);
							if (enemyRNG < 6) {
								System.out.println("Forest Guardian strikes!");
								System.out.println("- " + (enemyAtk * 2));
								damage = enemyAtk * 2;
								if (pBlock = true) {
									damage = damage / 2;
								}
								playerHP = playerHP - damage;
							}
							
							if (enemyRNG > 5 && enemyRNG < 10) {
								System.out.println("Forest Guardian heals itself.");
								System.out.println("Forest Guardian heals for 25 HP.");
								enemyHP = enemyHP + 25;
								
							} else {
								System.out.println("Forest Guardian is preparing to do something...");
								bananaSlamma = true;
								
							}
						} else {
							System.out.println("Forest Guardian used a super powerful attack!");
							damage = 100;
							System.out.println("- " + damage);
							if (pBlock = true) {
								damage = damage / 2;
							}
							playerHP = playerHP - damage;
						}
						
						pTurn = true;
						eTurn = false;
						pBlock = false;
						
					} else {
						droppedItem(9);
					}
				}
				
				
			}
		
		
		}

	} // end of battle module
	
	public static void forestEvent() {
		System.out.println("Area #" + sector + " (Forest)");
		areaRng = random.nextInt(12);
		if (areaRng < 4) {
			battle("Hot Pepper Snail", 25, 10, 1, 25);
		} else if (areaRng > 4 && areaRng < 9) {
			battle("Caterpilla", 20, 10, 2, 15);
		} else if (areaRng == 9) {
			fishingActivity();
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
		System.out.println("Area #" + sector + " (Thicket)");
		areaRng = random.nextInt(12);
		if (sector == 20) {
				battle("Forest Guardian", 100, 5, 9, 150);
		} else {
			if (areaRng < 2) {
				battle("Poison Ivy Sticker", 50, 5, 3, 30);
			
			} else if (areaRng > 4 && areaRng < 7) {
				battle("Bandit", 30, 5, 4, 5);
				
			} else if (areaRng == 4) {
				System.out.println("You found a hot spring in a forest clearing.");
				System.out.println("The warm water nourishes your soul. Full HP!");
				line();
				playerHP = playerHPmax;
			} else if (areaRng >= 10) {
				System.out.println("You found a treasure chest.");
				loot();
				line();
			
			} else if (areaRng == 2) {
				battle("Bombbush", 40, 50, 6, 20);
		
			} else if (areaRng == 3) {
				fishingActivity();
				
			} else {
				spiky = true;
				battle("Spined Caterpilla", 40, 5, 5, 20);
			}
		}
		
	}
	
	public static void caveEvent() {
		System.out.println("Area #" + sector + " (Cave)");		
		areaRng = random.nextInt(15);
		

		
	}

	public static void line() { 
		System.out.println("--------------------------");		
	}

	public static int promptForInt() {
		int returnValue = 0;
		userChoicePending = true;
		while (userChoicePending) {
			try {
				returnValue = keyboard.nextInt();
				userChoicePending = false;
			}
			catch (java.util.InputMismatchException ime) {
				System.out.println("Please type the number that corresponds with the action you would like to perform.");
				keyboard.nextLine();
			}
		}
		return returnValue;
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
		rng = random.nextInt(6);
		System.out.println("You found a " + items[rng]);
		
		if (inventory[0].isEmpty()) {
			System.out.println("You have a " + items[rng] + " in your inventory now");
			inventory[0] = items[rng];
		} else {
			System.out.println("Do you want to replace your current item? (" + inventory[0] + ") (1y/2n)");
			userResponse = promptForInt();
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
			inventory[0] = "";
			playerHP = playerHP + 50;
			if (playerHP > playerHPmax) {
				playerHP = playerHPmax;
			}
			System.out.println("You used your Small HP potion and regained 50 HP points.");
			
		} else if (inventory[0] == items[1]) { //Glasses
			inventory[0] = "";
			glasses = true;
			System.out.println("You put on your pair of very breakable glasses.");
			System.out.println("They increase your accuracy for the current battle!");
			
		} else if (inventory[0] == items[2]) { // Bladed Glove
			inventory[0] = "";
			glove = true;
			System.out.println("You put on the pair of sharp gloves");
			System.out.println("They increase your attack damage for the current battle!");
			
		} else if (inventory[0] == items[3]) { // Grappling Hook
			inventory[0] = "";
			System.out.println("You used your grappling hook to flee!");
			System.out.println("You skipped several sectors!");
			hook = true;
			battle = false;
			rng = (random.nextInt(5)) + 1;
			
			sector = sector + rng;
			
		} else if (inventory[0] == items[5]) { // HP Potion
			inventory[0] = "";
			playerHP = playerHPmax;
			System.out.println("You used your HP potion and healed back to full HP");
		} else if (inventory[0] == items[4]) {
			System.out.println("You used your fishing rod!");
			System.out.println("...and nothing happened because its a fishing rod.");
		}
		
		
		
		
	}
	
	
	public static void capHP () {
		if(playerHP > playerHPmax) {
			playerHP = playerHPmax;
		}
		
	}
	
	public static void inventory() {
		System.out.println("Your Inventory:");
		System.out.println("Held Item: " + inventory[0]);
		System.out.println("Passive Items: ");
		for(String str : tboi) {
			System.out.println(str);
		}
		
		
	}
	
	public static void newItems(int itemID) {
		if (itemID == 0) {			// SHARK TOOTH (+3 ATK)
			playerAtk = playerAtk + 3;
			System.out.println("Shark dropped a very sharp tooth!");
			System.out.println("Your ATK has increased by 3 permanently!");
			tboi.add("Shark Tooth (+3 ATK)");
		}
		if (itemID == 1) {			// CATERPILLA EGG (HEAL +2 per TURN)
			bugEgg = true;
			System.out.println("The Caterpilla dropped a strange egg.");
			System.out.println("You can feel a warmth emanating from it.");
			tboi.add("Caterpilla Egg (+2 HP per TURN)");
		}
		if (itemID == 2) {			// HOT PEPPER (CHANCE TO SKIP SECTOR)
			pepper = true;
			System.out.println("The Hot Pepper Snail dropped a hot pepper");
			System.out.println("It's very warm.");
			tboi.add("Hot Pepper (OCASSIONALLY SKIP AREAS)");
		}
		
		if (itemID == 3) {
			forestRune = true;		// FOREST RUNE ( +25 MAX HP)
			System.out.println("The Forest Guardian dropped a mysterious rune...");
			System.out.println("You feel healthier. + 25 max HP!");
			tboi.add("Forest Rune (+25 MAX HP!)");
		}
	}
	
	public static void fishingActivity() {

		System.out.println("You find a pond in the middle of a forest clearing.");
		if (inventory[0] == items[4]) {
			System.out.println("Would you like to go fishing? (1y/2n");
			wantsFish = keyboard.nextInt();
			if (wantsFish == 1) {
				System.out.println("You decide that this is a good time to go fishing.");
				fishRNG = random.nextInt(5);
				if (fishRNG == 0) {
					System.out.println("You couldn't get anything.");
				} else if (fishRNG == 1) {
					System.out.println("You fished up a cooked salmon");
					System.out.println("+ 30 HP!");
					playerHP = playerHP + 30;
					capHP();
				} else if (fishRNG == 2) {
					System.out.println("You fished up....");
					System.out.println("A rare goldfish!");
					battle("Goldfish", 55, 1, 7, (100*playerLevel) );
				} else if (fishRNG == 3) {
					System.out.println("You fished up....");
					System.out.println("A shark!");
					battle("Shark", 40, 10, 8, 50);
				} else if (fishRNG == 4) {
					System.out.println("You fished up a raw salmon.");
					System.out.println("+ 10 HP!");
					playerHP = playerHP + 10;
					capHP();
				} else if (fishRNG == 5) {
					
				}

			} else {
				
			}
		} else {
			System.out.println("If only you had something to use here...\nYou decide to leave.");
		}
		
		
	}
	
	public static void droppedItem(int enemyID) {
		if (enemyID == 8) {
			dropChance = random.nextInt(2);  // Shark drop Shark Tooth
			if (dropChance == 0) {
				newItems(0);
			}
		}
		
		if (enemyID == 2) {
			dropChance = random.nextInt(7); // Caterpilla drop Caterpilla Egg
			if (dropChance == 4) {
				newItems(1);
			}
			
		}
		
		if (enemyID == 1) {
			dropChance = random.nextInt(6); // Hot Pepper Snail drop Hot Pepper
			if (dropChance == 3) {
				newItems(2);
			}
		}
		
		if (enemyID == 9) {
			dropChance = random.nextInt(4); // Forest Guardian drops Forest Rune
			if (dropChance == 2) {
				newItems(3);
			}
		}
		
	}
	
	
	
	
}
