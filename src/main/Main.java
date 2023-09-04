package main;

import java.util.Scanner;

import admin.Counter;
import order.Orders;
import person.Admin;
import person.Customer;
import person.Manager;
import person.Staff;
import revenue.Revenue;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("1. for Employee\n2. for Customer\n3. for Exit");
			int choise = sc.nextInt();
			if(choise==1) {
				System.out.println("Enter ID: ");
				int emp_id = sc.nextInt();
				System.out.println("Enter Password: ");
				String pass = sc.next();
				Admin adminObj =new Admin(emp_id);
				if(adminObj.login(emp_id, pass)) {
					String role = adminObj.checkEmployeeRole(emp_id);
//					System.out.println(role);
					if(role.equals("admin")) {
						Admin ad_full = adminObj.adminObject(emp_id);
						Counter counter = new Counter();
						Revenue re = new Revenue();
						while(true) {
							System.out.println("\n1. Update Password\n2. Update Phone Number\n"
									+ "3. Add Counter\n4. Delete Counter\n5. View Counter\n"
									+ "6. Update Counter Detail\n"
									+ "7. Add Manager\n8. Delete Manager\n9. View Manager\n"
									+ "10. Add Staff\n11. Delete Staff\n12. View Staff\n13. to gate whole revenue\n14. to get counter wise revenue\n0. Go back to the Previous menu");
							int choise_ad =sc.nextInt();
							if(choise_ad ==1) {
								ad_full.updatePassword(ad_full);
							} else if(choise_ad ==2) {
								ad_full.updatePhoneNum(ad_full);
							} else if(choise_ad ==3) {
								System.out.println("Enter Counter Id: ");
								String id=sc.next();
								System.out.println("Enter Counter Name: ");
								sc.nextLine();
								String name=sc.nextLine();
								System.out.println("Enter No. of Staff in that Counter: ");
								int stNo = sc.nextInt();
								counter =new Counter(id,name,stNo);
								counter.createCounter(id, name, stNo);
							} else if(choise_ad ==4) {
								System.out.println("Enter Counter Id To be Deleated: ");
								String id=sc.next();
								counter.deleateCounter(id);	
							} else if(choise_ad ==5) {
								counter.displayCounter();
							} else if(choise_ad ==6) {
								System.out.println("1. Update Counter Name\n2. Update Counter Staff\n3. Update Both");
								int choiceForUpdateCounter = sc.nextInt();
								System.out.println("Enter Counter Id: ");
								String id=sc.next();
								if(choiceForUpdateCounter == 1) {
									System.out.println("Enter New Counter Name: ");
									sc.nextLine();
									String name=sc.nextLine();
									counter.updateCounterName(id, name);
								} else if(choiceForUpdateCounter == 2) {
									System.out.println("Enter New No. of Staff in that Counter: ");
									int stNo = sc.nextInt();
									counter.updateCounterStaff(id, stNo);
								} else if(choiceForUpdateCounter == 3) {
									System.out.println("Enter New Counter Name: ");
									sc.nextLine();
									String name=sc.nextLine();
									System.out.println("Enter New No. of Staff in that Counter: ");
									int stNo = sc.nextInt();
									counter.updateCounter(id, name, stNo);
								} else {
									System.out.println("...Wrong Entry...");	
								}
							} else if(choise_ad ==7) {
								System.out.println("Enter Manager ID: ");
								int e_id = sc.nextInt();
								System.out.println("Enter Manager Name: ");
								sc.nextLine();
								String name=sc.nextLine();
								System.out.println("Enter Manager Phone Number: ");
								String num=sc.nextLine();
								ad_full.createManager(e_id, name, num, "password");
							} else if(choise_ad ==8) {
								System.out.println("Enter Manager ID: ");
								int m_id = sc.nextInt();
								ad_full.deleateStaff(m_id);
							} else if(choise_ad ==9) {
								ad_full.viewManager();
							} else if(choise_ad ==10) {
								System.out.println("Enter Staff ID: ");
								int e_id = sc.nextInt();
								System.out.println("Enter Staff Name: ");
								sc.nextLine();
								String name=sc.nextLine();
								System.out.println("Enter Staff Phone Number: ");
								String num=sc.nextLine();
								ad_full.createStaff(e_id, name, num, "password");
							} else if(choise_ad ==11) {
								System.out.println("Enter Staff ID: ");
								int e_id = sc.nextInt();
								ad_full.deleateStaff(e_id);
							} else if(choise_ad ==12) {
								ad_full.viewStaff();
							} else if(choise_ad ==13) {
								re.DisplayTotalRevenue();
							} else if(choise_ad ==14) {
								re.DisplayTotalRevenueCounterWise();
							}
							else if(choise_ad ==0) {
								System.out.println("...Logged out...");
								break;
							}
						}
					}
					else if(role.equals("manager")) {
						Manager mg_full = adminObj.managerObject(emp_id);
						while(true) {
							System.out.println("1. Update Password\n2. Update Phone Number\n"
									+ "3. Add Staff\n4. Delete Staff\n5. View Staff\n6. Go back to the Previous menu");
							int choise_mg =sc.nextInt();
							if(choise_mg ==1) {
								mg_full.updatePassword(mg_full);
							}
							else if(choise_mg ==2) {
								mg_full.updatePhoneNum(mg_full);
							} else if(choise_mg ==3) {
								System.out.println("Enter Staff ID: ");
								int e_id = sc.nextInt();
								System.out.println("Enter Staff Name: ");
								sc.nextLine();
								String name=sc.nextLine();
								System.out.println("Enter Staff Phone Number: ");
								String num=sc.nextLine();
								mg_full.createStaff(e_id, name, num, "password");
							} else if(choise_mg ==4) {
								System.out.println("Enter Staff ID: ");
								int e_id = sc.nextInt();
								mg_full.deleateStaff(e_id);
							} else if(choise_mg ==5) {
								mg_full.viewStaff();
							} else if(choise_mg ==6) {
								System.out.println("...Logged out...");
								System.out.println("...Re-directing To Previous Menu...");
								break;
							}				
						}
					}
					else if(role.equals("staff")) {
						Staff st_full = adminObj.staffObject(emp_id);
						while(true) {
							System.out.println("1. Update Password\n2. Update Phone Number\n3. Prepare food\n0. Go back to the Previous menu");
							int choise_st =sc.nextInt();
							if(choise_st ==1) {
								st_full.updatePassword(st_full);
							}
							else if(choise_st ==2) {
								st_full.updatePhoneNum(st_full);
							}
							else if(choise_st ==0) {
								System.out.println("...Logged out...");
								System.out.println("...Re-directing To Previous Menu...");
								break;
							}
							else {
								System.out.println("...Plese Enter a Correct Choice...");
							}
						}
					}
					else {
						
					}
				}
				else {
					System.out.println("...retry employee...");
				}
			}
			else if(choise==2) {
				System.out.println("Press \n0 For login\n1 For SignUp");
				int choise_temp = sc.nextInt();
				if(choise_temp == 0) {
					System.out.println("Enter ID: ");
					int cus_id = sc.nextInt();
					System.out.println("Enter Password: ");
					String pass = sc.next();
					Customer cu = new Customer(cus_id);
					if(cu.login(cus_id, pass)) {
						while(true) {
							Customer cus_full = cu.object(cus_id);
							System.out.println("1. Update Password\n2. Update Phone Number\n3. Order Food\n0. Go back to the Previous menu");
							int choise_cus =sc.nextInt();
							if(choise_cus ==1) {
								cus_full.updatePassword(cus_full);
							}
							else if(choise_cus ==2) {
								cus_full.updatePhoneNum(cus_full);
							}
							else if(choise_cus ==3) {
								Orders ToOrderFood = new Orders();
								ToOrderFood.orderFromMenu(cus_id);
							}
							else if(choise_cus ==0) {
								System.out.println("...Logged out...");
								System.out.println("...Re-directing To Previous Menu...");
								break;
							}
							else {
								System.out.println("...Plese Enter a Correct Choice...");
							}
						}
					}
				}
//				System.out.println("Enter ID: ");
//				int cus_id = sc.nextInt();
//				System.out.println("Enter Password: ");
//				String pass = sc.next();
//				Customer cu = new Customer(cus_id);
//				System.out.println("Press \n0 For login\n1 For SignUp");
//				int choise_temp = sc.nextInt();
//				if(choise_temp == 0) {
//					
//				}
//				if(cu.login(cus_id, pass)) {
//					while(true) {
//						Customer cus_full = cu.object(cus_id);
//						System.out.println("1. Update Password\n2. Update Phone Number\n3. Go back to the Previous menu");
//						int choise_cus =sc.nextInt();
//						if(choise_cus ==1) {
//							cus_full.updatePassword(cus_full);
//						}
//						else if(choise_cus ==2) {
//							cus_full.updatePhoneNum(cus_full);
//						}
//						else if(choise_cus ==3) {
//							System.out.println("...Logged out...");
//							System.out.println("...Re-directing To Previous Menu...");
//							break;
//						}
//						else {
//							System.out.println("...Plese Enter a Correct Choice...");
//						}
//					}
//				}
				else if (choise_temp == 1){
					System.out.println();
					System.out.println("Enter ID: ");
					int cus_id = sc.nextInt();
					Customer cu = new Customer(cus_id);
					cu.signUp();
				}
				else {
					System.out.println("...Wrong Choice...");
				}
			}
			else if(choise == 3) {
				System.out.println("...Exiting...");
				break;
			}
			else {
				System.out.println("...Try Again...");				
			}
			
		}
		
	}

}
