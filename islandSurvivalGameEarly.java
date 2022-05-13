package funnygame;
import java.util.Scanner;
import java.util.Random;

public class theGameFruit {
	
	//TODO Finish each option
	// Uncomplete options: 1, 2, 3, 4, 5,7
	// Kinda complete options: 6
	// totally complete options: none lol
	
	
	//from there i will figure something out
	
	
	
	
	
	
	
	// misc vars
	static int day = 0;
	static int choice;
	static int userResponse;
	static int rng;
	static int fruitRng;
	static int toxic;
	static boolean isToxic;
	static int chanceForDisaster;
	
	static int playerHP = 100;
	static int playerStamina = 100;
	static int foodSupply = 0;
	static int numbFruit;
	static String takeFruit;
	
	static boolean gaming = true;
	static boolean fishingRod = false;
	static boolean fruitLoop = true;
	
	
	
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
			
			case 1:
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
				
				
				
			case 2:
				System.out.println("You chose to go hunting.");
				sleepy();
				break;
				
				
				
				
			case 3:
				System.out.println("You chose to go fishing.");
				if (fishingRod == false) {
					System.out.println("....but you don't have a fishing rod.");
					System.out.println("You try to grab fish in the water, to low avail.");
				}
				sleepy();
				break;
				
				
				
				
				
			case 4:
				System.out.println("You chose to gather wood.");
				sleepy();
				break;
				
				
				
				
				
				
			case 5:
				System.out.println("You chose to gather stone.");
				sleepy();
				break;
				
				
				
				
				
				
				
			case 6: // Player chooses to rest in the sand
				System.out.println("You chose to lay facedown in the sand.");
				System.out.println("..............");
				
				// low chance to sleep great, which recovers more stamina and HP
				if (rng >= 6) {
					System.out.println("You slept great! Your HP has recovered by 15. Your stamina has recovered by 50.");
					playerHP = playerHP + 15;
					playerStamina = playerStamina + 50;
				} else {
					System.out.println("You slept fine. Your stamina has increased by 30");
					playerStamina = playerStamina + 20;
				}
				//HP and stamina cap
				if (playerHP > 100) {
					playerHP = 100;
				}
				if (playerStamina > 100) {
					playerStamina = 100;
				}
				sleepy();
				break;
				
				
				
				
				
			case 7:
				sleepy();
				break;
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
		chanceForDisaster = random.nextInt(80);
		System.out.println("It's getting late...");
		foodSupply = foodSupply - 5;
		if (foodSupply <= 0) {
			System.out.println("Your stomach growls. You have no food.");
			System.out.println("- 10 HP, -30 Stamina");
			foodSupply = 0;
			playerHP = playerHP - 10;
			playerStamina = playerStamina - 30;
		} else {
			System.out.println("You eat some of your saved food.");
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
		System.out.println("HP: " + playerHP + "/100");
		System.out.println("Stamina: " + playerStamina + "/100");
		System.out.println("Food supply: " + foodSupply);
		System.out.println("What will you do?");
		System.out.println("1. Search for fruits");
		System.out.println("2. Go hunting");
		System.out.println("3. Go fishing");
		System.out.println("4. Gatcher wood");
		System.out.println("5. Gather stone");
		System.out.println("6. Rest in the sand");
		System.out.println("7. Craft");
		System.out.println("(Enter a number to pick)");
		
	}
	public static void line() {
		System.out.println("-----------------------------");
	}
	
	
}
