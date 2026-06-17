
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CarRentalSystem {
	private List<Car> cars;
	private List<Customer> customers;
	private List<Rental> rentals;
	
	public CarRentalSystem() {
		cars=new ArrayList<>();
		customers=new ArrayList<>();
		rentals=new ArrayList<>();
	}
	
	public void addCar(Car car) {
		cars.add(car);
	}
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	public void rentCar(Car car,Customer customer,int days) {
		if(car.getIsAvailable()) {
			car.rent();
			rentals.add(new Rental(car,customer,days));
		}
		else {
			System.out.println("Car is not availabe");
		}
	}
	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove=null;
		for(Rental rental:rentals) {
			if(rental.getCar()==car) {
				rentalToRemove=rental;
				break;
			}
		}
		if(rentalToRemove != null) {
			rentals.remove(rentalToRemove);
		}
		else {
			System.out.println("Car was not rented");
		}
	}
	public void menu() {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("\n================ Welecome to Car Rental System ==================\n");
			System.out.println("\t1. Rent a car");
			System.out.println("\t2. Return a car");
			System.out.println("\t3. Exit");
			System.out.print("\tEnter your choice: ");
			int choice=sc.nextInt();
			if(choice==1) {
				System.out.println("\n================== Rent a Car ==================\n");
				System.out.print("\tEnter your name: ");
				String customerName=sc.next();
				System.out.println("\tAvailable Cars: ");
				for(Car car:cars) {
					if(car.getIsAvailable()) {
						System.out.println("\t\t"+car.getCarId()+" - "+car.getBrand()+" - "+car.getModel()+" - "+"Rs"+car.getBasePricePerDay()+"/Day");
					}
					
				}
				System.out.print("        Enter the car ID you want to rent: ");
				String carId=sc.next();
				System.out.print("        Enter the number of days for rental: ");
				int rentalDays=sc.nextInt();
				sc.nextLine();				
				Customer newCustomer=new Customer("CUS"+(customers.size()+1),customerName);
				addCustomer(newCustomer);
				Car selectedCar=null;
				for(Car car:cars) {
					if(car.getCarId().equals(carId) && car.getIsAvailable()) {
						selectedCar=car;
						break;
					}
				}
				if(selectedCar != null) {
					double totalPrice=selectedCar.calculatePrice(rentalDays);
					System.out.println("\n================== Rental Information ==================\n");
					System.out.println("\tCustomer ID: "+newCustomer.getCustomerId());
					System.out.println("\tCustomer Name: "+newCustomer.getName());
					System.out.println("\tCar: "+selectedCar.getBrand()+" "+selectedCar.getModel());
					System.out.println("\tRental Days: "+rentalDays);
					System.out.printf("\tTotal Price: Rs%.2f%n ",totalPrice);
					System.out.print("\tConfirm rental (Y/N): ");
					String confirm=sc.nextLine();
					if(confirm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar, newCustomer, rentalDays);
						System.out.println("Car rented successfully");
					}
					else {
						System.out.println("Rental canceled");
					}
				}
				else {
					System.out.println("Invalid car selection or car not available for rent");
				}
			}
			else if(choice==2) {
				System.out.println("================== Return a Car ==================");
				System.out.print("Enter the car ID you want to return: ");
				String carId=sc.next();
				Car carToReturn =null;
				for(Car car:cars) {
					if(car.getCarId().equals(carId) && !car.getIsAvailable()) {
						carToReturn=car;
						break;
					}
				}
				if(carToReturn!=null) {
					Customer customer=null;
					for(Rental rental:rentals) {
						if(rental.getCar()==carToReturn) {
							customer=rental.getCustomer();
							break;
						}
					}
					if(customer!=null) {
						returnCar(carToReturn);
						System.out.println("car returned by customer "+customer.getName());
					}
					else {
						System.out.println("Car was not rented or rental information is missing");
					}
				}
				else {
					System.out.println("Invalid car Id or car is not rented.");
				}
			}
			else if(choice==3) {
				break;
			}
			else {
				System.out.println("Invalid option. please select a valid option");
			}
		}
		System.out.println("Thank you for using the Car Rental System");
	}
	
	
	public static void main(String[] args) {
		CarRentalSystem rentalSystem=new CarRentalSystem();
		Car car1 = new Car("C001", "Toyota", "Camry", 1000);
		Car car2 = new Car("C002", "Honda", "City", 1200);
		Car car3 = new Car("C003", "Hyundai", "Verna", 1100);
		Car car4 = new Car("C004", "Maruti", "Swift", 800);
		Car car5 = new Car("C005", "Tata", "Nexon", 1500);
		Car car6 = new Car("C006", "Mahindra", "XUV700", 2000);
		Car car7 = new Car("C007", "Kia", "Seltos", 1700);
		Car car8 = new Car("C008", "Volkswagen", "Virtus", 1600);
		Car car9 = new Car("C009", "Skoda", "Slavia", 1550);
		Car car10 = new Car("C010", "MG", "Hector", 1800);
		rentalSystem.addCar(car1);
		rentalSystem.addCar(car2);
		rentalSystem.addCar(car3);
		rentalSystem.addCar(car4);
		rentalSystem.addCar(car5);
		rentalSystem.addCar(car6);
		rentalSystem.addCar(car7);
		rentalSystem.addCar(car8);
		rentalSystem.addCar(car9);
		rentalSystem.addCar(car10);
		rentalSystem.menu();

	}

}
