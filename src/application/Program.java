package src.application;

import src.model.entities.CarRental;
import src.model.entities.Vehicle;
import src.model.services.BrazilTaxService;
import src.model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter with the rental data");
        System.out.print("Car Model: ");
        String carModel = sc.nextLine();
        System.out.println("Pickup (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("Returns (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Enter with price per hour: ");
        Double pricePerHour = sc.nextDouble();
        System.out.print("Enter with price per day: ");
        Double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(cr);

        System.out.println("INVOICE:");
        System.out.println("Basic Payment: " + cr.getInvoice().getBasicPayment());
        System.out.println("Tax: " + cr.getInvoice().getTax());
        System.out.println("Total Payment: " + cr.getInvoice().getTotalPayment());

        sc.close();
    }
}
