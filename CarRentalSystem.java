import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String Carid;
    private String brand;
    private String model;
    private double PricePerDay;
    private boolean isAvailable;

    Car(String Carid, String brand,String model, double PricePerDay)
    {
        this.Carid = Carid;
        this.brand = brand;
        this.model = model;
        this.PricePerDay = PricePerDay;
        this.isAvailable = true;
    }
    
    public String getCarid(){
        return Carid;
    }

    public String getbrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public double price(int rentalDays)
    {
        return  PricePerDay * rentalDays;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void rent(){
        isAvailable = false;
    }

    public void returnCar(){
        isAvailable = true;
    }
}

class Customer{
    private String CustomerID;
    private String name;

    Customer(String CustomerID, String name)
    {
        this.CustomerID = CustomerID;
        this.name = name;
    }

    public String getCustomerID(){
        return CustomerID;
    }

    public String getname(){
        return name;
    }

    public void add(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}

class Rental{
    private Car car;
    private Customer customer;
    private int days;

    Rental(Car car , Customer customer , int days)
    {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getcar(){
        return car;
    }

    public Customer getcustomer(){
        return customer;
    }

    public int getdays(){
        return days;
    }
}

class System {
    private static Object out;
    private List<Car> cars;
    private List<Customer> customer;
    private List<Rental> rentals;

    System(){
        cars = new ArrayList<>();
        customer = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car)
    {
        cars.add(car);
    }

    public void addCustomer(Customer customer)
    {
        customer.add(customer);
    }

    public void rentCar(Car car , Customer customer, int days)
    {
        if(car.isAvailable())
        {
            car.rent();
            rentals.add(new Rental(car , customer , days));
        }
        else{
            System.out.println("Car is not avialable for the rent.");
        }
    }

    public void returncar(Car car)
    {
        car.returnCar();
        Rental rentalToRemove = null;
        for(Rental rental : rentals)
        {
            if(rental.getcar() == car)
            {
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null)
        {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned Successfully");
        }else{
            System.out.println("Car was not rented.");
        }
    }

    public void menu(){
        while(true)
        {
            System.out.println(" === CAR RENTAL SERVICES === ");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("1. Exit");
            System.out.println("Enter your choice: ");    
            
            Scanner scanner;
            int choice = scanner.nextInt();

            if(choice == 1)
            {
                System.out.println("\n== Rent a car ==\n");
                System.out.println("Enter your name");
                String name = scanner.nextLine();

                System.out.println("\n Available Cars:");
                for(Car car : cars)
                {
                    if(car.isAvailable())
                    {
                        System.out.println(car.getCarid() + " - " + car.getbrand() + " " + car.getModel());

                    }
                }

                System.out.println("\n Enter your car id you want to rent: ");
                String carid = scanner.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentaldays = scanner.nextInt();

                List<Car> customers;
                Customer newcustomer = new Customer("CUS" + (customers.size()+1), name);
                addCustomer(newcustomer);

                Car selectCar = null;
                for (Car car : cars)
                {
                    if(car.getCarid().equals(carid) && car.isAvailable()){
                        selectCar = car;
                        break;
                    }
                }

                if(selectCar != null)
                {
                    double totalprice = selectCar.price(rentaldays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newcustomer.getCustomerID());
                    System.out.println("Customer Name: "+ newcustomer.getname());
                    System.out.println("Car: " +selectCar.getbrand() + " " + selectCar.getModel());
                    System.out.println("Rental days: " + rentaldays);
                    System.out.println("Total Price: $%.2f%n", totalprice);

                    System.out.println("Conform Rent (Y/n): ");
                    String confirm = scanner.nextLine();

                    if(confirm.equalsIgnoreCase("Y"))
                    {
                        rentCar(selectCar, newcustomer, rentaldays);
                        System.out.println("Car rented Successfully.");
                    }else{
                        System.out.println("\n Rental Cancel \n");
                    }
                }else{
                    System.out.println("\n Invalid car Selection or car not available for rent");
                }
            }else if(choice == 2)
            {
                System.out.println("\n== Return a Car ==\n");
                System.out.println("Enter the car ID you want to return: ");
                String carid = scanner.nextLine();

                Car toReturn = null;
                for(Car car : cars)
                {
                    if(car.getCarid().equals(carid) && !car.isAvailable())
                    {
                        toReturn = car;
                        break;
                    }
                }
                if(toReturn != null)
                {
                    Customer customer = null;
                    for(Rental rental : rentals)
                    {
                        if(rental.getcar() == toReturn)
                        {
                            customer = rental.getcustomer();
                            break;
                        }
                    }

                    if(customer != null)
                    {
                        returncar(toReturn);
                        System.out.println("Car returned Succesfully" + customer.getname());
                    }else{
                        System.out.println("Car was not returned or rental information is missing");
                    }
                }else{
                    System.out.println("Invalid Car ID or car is not rental");
                }

            }else if(choice == 3)
            {
                break;
            }else {
            System.out.println("Invalid choice.Please enter a valid option");
        }

        System.out.println("Thank you ");
    }
    
}
}

public class CarRentalSystem {
    public static void main(String[] args)
    {
        CarRentalSystem S = new CarRentalSystem();
        System s = new System();
        Car car1 = new Car("C001", "Toyota", "Camry", 234.32);
        Car car2 = new Car("C002", "Honda", "Accord", 124.32);
        Car car3 = new Car("C003", "Mahindra", "Thar", 321.32);

        s.addCar(car1);

    }
}

